/*
 * MojangMaps
 * Copyright (C) 2023 Abel van Hulst/Abelkrijgtalles/Abelpro678
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.MojangMaps.listener;

import java.util.ArrayList;
import java.util.List;
import nl.abelkrijgtalles.MojangMaps.command.register.RoadCreationCommand;
import nl.abelkrijgtalles.MojangMaps.object.Road;
import nl.abelkrijgtalles.MojangMaps.util.file.MessageUtil;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.RoadUtil;
import nl.abelkrijgtalles.MojangMaps.util.other.ParticleUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class RoadCreationToolListener implements Listener {

    public static boolean isRegisterItem(Player p) {

        if (!p.getInventory().getItemInMainHand().hasItemMeta()) return false;
        if (!p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) return false;
        return p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().trim().equals(RoadCreationCommand.getRegisterItemName().trim());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (!isRegisterItem(event.getPlayer())) return;

        addPointLogic(event.getPlayer(), event.getBlock().getLocation());

        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player p) {

            if (!isRegisterItem(p)) return;

            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {

        Player p = event.getPlayer();

        if (!isRegisterItem(p)) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player p = event.getPlayer();

        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!isRegisterItem(p)) return;

        addPointLogic(p, event.getClickedBlock().getLocation());

        event.setUseItemInHand(Event.Result.DENY);
    }

    private void addPointLogic(Player p, Location clickedBlockLocation) {

        clickedBlockLocation.add(0.5, 1, 0.5);

        RoadCreationCommand.locations.add(clickedBlockLocation);
        p.sendMessage(ChatColor.GOLD + "Added point.");

        if (RoadCreationCommand.locations.size() > 1) {
            if (RoadCreationCommand.locations.size() > 2) {
                Bukkit.getScheduler().cancelTask(RoadCreationCommand.particleTaskId);
            }
            RoadCreationCommand.particleTaskId = ParticleUtil.spawnLine(RoadCreationCommand.locations, 1, 0.25);
        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {

        Player p = event.getPlayer();
        String name = RoadCreationCommand.roadName;

        if (!event.getItemDrop().getItemStack().hasItemMeta()) return;
        if (!event.getItemDrop().getItemStack().getItemMeta().hasDisplayName()) return;
        if (!event.getItemDrop().getItemStack().getItemMeta().getDisplayName().trim().equals(RoadCreationCommand.getRegisterItemName().trim()))
            return;

        if (RoadCreationCommand.locations.size() < 2) {

            p.sendMessage(ChatColor.RED + "You have to have at least 2 locations selected to create a road. Cancelling...");
            resetRoadCreation(event);
            return;

        }

        if (name == null) {
            p.sendMessage("Saving " + MessageUtil.getMessage("unnamedroad") + ".");
        } else {
            p.sendMessage("Saving " + name + ".");
        }

        // I had to look at registerroad to know how the saving system worked again

        List<Integer> locationsPointers = new ArrayList<>();

        RoadUtil.addMoreLocations(p, RoadCreationCommand.locations);

        for (Location location : RoadCreationCommand.locations) {

            NodesConfigUtil.addLocation(location);
            locationsPointers.add(NodesConfigUtil.getLocations().size() - 1);

        }

        if (name != null) {
            NodesConfigUtil.addRoad(new Road(name, locationsPointers));
        } else {
            NodesConfigUtil.addRoad(new Road(locationsPointers));
        }

        p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("registeredroad"));

        resetRoadCreation(event);

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        if (event.getPlayer().getUniqueId() == RoadCreationCommand.creatingRoadPlayer) {
            Bukkit.getScheduler().cancelTask(RoadCreationCommand.particleTaskId);
            RoadCreationCommand.particleTaskId = -1;
            RoadCreationCommand.creatingRoadPlayer = null;
            RoadCreationCommand.locations.clear();
            RoadCreationCommand.roadName = null;

            for (ItemStack item : event.getPlayer().getInventory().getContents()) {
                if (!item.hasItemMeta()) return;
                if (!item.getItemMeta().hasDisplayName()) return;
                if (item.getItemMeta().getDisplayName().equals(RoadCreationCommand.getRegisterItemName())) {
                    event.getPlayer().getInventory().remove(item);
                }
            }

        }

    }

    private void resetRoadCreation(PlayerDropItemEvent event) {

        Bukkit.getScheduler().cancelTask(RoadCreationCommand.particleTaskId);
        RoadCreationCommand.particleTaskId = -1;
        RoadCreationCommand.creatingRoadPlayer = null;
        RoadCreationCommand.locations.clear();
        RoadCreationCommand.roadName = null;
        event.getItemDrop().remove();

    }

}
