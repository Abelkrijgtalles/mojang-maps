package nl.abelkrijgtalles.MojangMaps.command;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.object.Node;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.LocationUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.NodeUtil;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class NewGuiCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            openGUI((Player) commandSender);

        }

        return true;
    }

    private void openGUI(Player p) {

        Location closestLocationToPlayer = LocationUtil.getClosestLocation(p.getLocation());

        List<Node> nodes = NodeUtil.addAdjacentNodes();
        Node.calculateShortestPath(findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToPlayer))));

        SGMenu navigationGUI = MojangMaps.spiGUI.create("Navigation", (int) (Math.ceil((double) nodes.size() / 9) + 1));

        for (String text : Node.getLocationAndDistanceList(nodes)) {

            SGButton button = new SGButton(

                    new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).name(text).build()

            ).withListener((InventoryClickEvent e) -> {

                e.getWhoClicked().sendMessage("Cool thingie innit");

            });

            navigationGUI.addButton(button);

        }

        p.openInventory(navigationGUI.getInventory());
    }

    private Node findNodeByName(List<Node> nodes, String name) {
        return nodes.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst()
                .orElse(null);
    }


}