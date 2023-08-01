package nl.abelkrijgtalles.MojangMaps.command;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;
import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class NewGuiCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            openGUI((Player) commandSender);

        }

        return true;
    }

    private void openGUI(Player player) {

        SGMenu menu = MojangMaps.spiGUI.create("Cool Menu bro", 3);
        SGButton button = new SGButton(new ItemBuilder(Material.REDSTONE).build())
                .withListener((InventoryClickEvent e) -> {
                    e.getWhoClicked().sendMessage("HELLOOOOOO");
                });

        menu.addButton(button);
        player.openInventory(menu.getInventory());
    }

}