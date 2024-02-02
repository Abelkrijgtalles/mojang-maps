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

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.command.GiveRegisterItemCommand;
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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.EquipmentSlot;

public class RegisterItemListener implements Listener {

    public static boolean isRegisterItem(Player p) {

        return p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == GiveRegisterItemCommand.getRegisterItemName();
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
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getAction() != Action.LEFT_CLICK_BLOCK) return;
        if (!isRegisterItem(p)) return;

        addPointLogic(p, event.getClickedBlock().getLocation());

        event.setUseItemInHand(Event.Result.DENY);
    }

    private void addPointLogic(Player p, Location clickedBlockLocation) {

        clickedBlockLocation.add(0.5, 1, 0.5);

        MojangMaps.creatingRoadLocations.add(clickedBlockLocation);
        p.sendMessage(ChatColor.RED + "Added point.");

        if (MojangMaps.creatingRoadLocations.size() > 1) {
            if (MojangMaps.creatingRoadLocations.size() > 2) {
                Bukkit.getScheduler().cancelTask(MojangMaps.creatingRoadParticleTaskId);
            }
            MojangMaps.creatingRoadParticleTaskId = ParticleUtil.spawnLine(MojangMaps.creatingRoadLocations, 1, 0.25);
        }

    }

}
