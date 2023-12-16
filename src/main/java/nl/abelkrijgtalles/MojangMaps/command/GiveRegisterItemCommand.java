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
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GiveRegisterItemCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player p) {

            MojangMaps.isCreatingARoad = true;
            ItemStack registerItem = new ItemStack(Material.GOLDEN_AXE);
            List<String> lore = new ArrayList<>();
            lore.add(HiddenStringUtil.encodeString("RegisterItem"));

            ItemMeta registerItemMeta = registerItem.getItemMeta();
            assert registerItemMeta != null;
            registerItemMeta.setLore(lore);
            registerItem.setItemMeta(registerItemMeta);

            p.getInventory().addItem(registerItem);
            p.sendMessage(ChatColor.YELLOW + "Gave the register item (this needs a better name).");

        }

        return true;
    }
}
