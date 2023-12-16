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

package nl.abelkrijgtalles.MojangMaps.command;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.util.other.HiddenStringUtil;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import nl.abelkrijgtalles.ParticLib.ParticLib;
import nl.abelkrijgtalles.ParticLib.object.ParticleLine;

public class GiveRegisterItemCommand implements CommandExecutor {

    private final MojangMaps plugin;

    public GiveRegisterItemCommand(MojangMaps plugin) {

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player p && !MojangMaps.isCreatingARoad) {

            MojangMaps.isCreatingARoad = true;
            new BukkitRunnable() {
                @Override
                public void run() {

                    if (!MojangMaps.creatingRoadLocations.isEmpty()) {

                        ParticLib.spawnParticleLine(new ParticleLine(MojangMaps.creatingRoadLocations, Color.YELLOW), p);

                    }

                }
            }.runTaskTimer(plugin, 0, 20L);

            ItemStack registerItem = new ItemStack(Material.GOLDEN_AXE);
            List<String> lore = new ArrayList<>();
            lore.add(HiddenStringUtil.encodeString("RegisterItem"));

            ItemMeta registerItemMeta = registerItem.getItemMeta();
            assert registerItemMeta != null;
            registerItemMeta.setLore(lore);
            registerItem.setItemMeta(registerItemMeta);

            p.getInventory().addItem(registerItem);
            p.sendMessage(ChatColor.YELLOW + "Gave the register item (this needs a better name).");

        } else if (commandSender instanceof Player p) {

            p.sendMessage(ChatColor.RED + "Someone is already creating a road. Try again later.");

        }

        return true;
    }

}
