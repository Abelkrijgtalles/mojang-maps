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
import nl.abelkrijgtalles.MojangMaps.util.other.HiddenStringUtil;

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

import java.util.List;

public class RegisterItemListener implements Listener {

    public static boolean isntItem(Player p) {
        if (!p.getInventory().getItemInMainHand().hasItemMeta()) return true;

        if (!p.getInventory().getItemInMainHand().getItemMeta().hasLore()) return true;

        List<String> lore = p.getInventory().getItemInMainHand().getItemMeta().getLore();

        if (lore != null && lore.size() > 0 && HiddenStringUtil.hasHiddenString(lore.get(0)))
            return HiddenStringUtil.extractHiddenString(lore.get(0)) != "RegisterItem";
        return false;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (isntItem(event.getPlayer())) return;

        event.getPlayer().sendMessage(ChatColor.RED + "Don't break stuff when using the register item (still need a better name).");
        event.setCancelled(true);


    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player p) {

            if (isntItem(p)) return;

            p.sendMessage(ChatColor.RED + "Don't damage entity when using the register item (still need a better name).");
            event.setCancelled(true);


        }

    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {

        Player p = event.getPlayer();

        if (isntItem(p)) return;

        event.getPlayer().sendMessage(ChatColor.RED + "Don't damage this item when using the register item (still need a better name).");
        event.setCancelled(true);

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if (event.getHand() != EquipmentSlot.HAND) return;

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (isntItem(p)) return;

        Location selectedLocation = event.getClickedBlock().getLocation();

        switch (event.getBlockFace()) {

            case UP -> selectedLocation.add(0, 1, 0);
            case DOWN -> selectedLocation.add(0, -1, 0);
            case EAST -> selectedLocation.add(1, 0, 0);
            case WEST -> selectedLocation.add(-1, 0, 0);
            case NORTH -> selectedLocation.add(0, 0, -1);
            case SOUTH -> selectedLocation.add(0, 0, 1);
            default -> {
                return;
            }

        }

        MojangMaps.creatingRoadLocations.add(selectedLocation);
        p.sendMessage(ChatColor.RED + "Added point.");

        event.setUseItemInHand(Event.Result.DENY);
    }

}
