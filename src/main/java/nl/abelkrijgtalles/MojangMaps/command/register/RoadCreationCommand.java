/*
 * MojangMaps
 * Copyright (C) 2024 Abel van Hulst/Abelkrijgtalles/Abelpro678
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

package nl.abelkrijgtalles.MojangMaps.command.register;

import dev.jorel.commandapi.CommandAPI;
import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

// rip to /giveregisteritem                                                        , and welcome to /createroad (but 2.0). It's /giveregisteritem, but better!!!!!!!!!!1!!!!!!!!1!1 (this totally isn't an ad)

public class RoadCreationCommand {

    public RoadCreationCommand(Player p) {

        if (!MojangMaps.isCreatingARoad) {

            MojangMaps.isCreatingARoad = true;

            ItemStack registerItem = new ItemStack(Material.GOLDEN_AXE);
            ItemMeta registerItemMeta = registerItem.getItemMeta();
            registerItemMeta.setDisplayName(getRegisterItemName());
            registerItem.setItemMeta(registerItemMeta);

            p.getInventory().addItem(registerItem);
            // idk if this should also give the color. As of now, it kinda looks cool
            p.sendMessage(ChatColor.YELLOW + "Gave the " + getRegisterItemName());

        } else {

            CommandAPI.failWithString("Someone is already creating a road. Try again later.");

        }

    }

    public static String getRegisterItemName() {

        return ChatColor.GOLD + "Road Creation Tool";

    }

}
