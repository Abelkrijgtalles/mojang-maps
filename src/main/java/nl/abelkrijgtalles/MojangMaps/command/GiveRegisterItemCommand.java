package nl.abelkrijgtalles.MojangMaps.command;

import nl.abelkrijgtalles.MojangMaps.util.other.HiddenStringUtil;

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

            ItemStack registerItem = new ItemStack(Material.GOLDEN_AXE);
            List<String> lore = new ArrayList<>();
            lore.add(HiddenStringUtil.encodeString("RegisterItem"));

            ItemMeta registerItemMeta = registerItem.getItemMeta();
            assert registerItemMeta != null;
            registerItemMeta.setLore(lore);
            registerItem.setItemMeta(registerItemMeta);

            p.getInventory().addItem(registerItem);

        }

        return true;
    }
}
