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

package nl.abelkrijgtalles.MojangMaps.command;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import java.util.List;
import nl.abelkrijgtalles.MojangMaps.object.NewNode;
import nl.abelkrijgtalles.MojangMaps.util.other.AStarUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AStarTest {

    public AStarTest(Player p) throws WrapperCommandSyntaxException {

        NewNode start = new NewNode(new Location(null, 0, -61, 0), 0, null, null);
        NewNode end = new NewNode(new Location(null, 25, -61, 25), 0, start, null);

        start.setStart(start);
        start.setEnd(end);
        end.setEnd(end);

        List<NewNode> nodes = AStarUtil.findPath(start, end);

        if (nodes == null) {

            throw CommandAPI.failWithString("Nodes is empty");

        }

        for (NewNode node : nodes) {

            Location nodeLocation = node.getLocation();

            p.chat("""
                    000
                    G: %s
                    H: %s
                    F: %s
                    Location: %s %s %s
                    Parent: %s
                    UUID: %s
                    000
                    """.formatted(node.getgCost(), node.gethCost(), node.getfCost(), nodeLocation.getBlockX(), nodeLocation.getBlockY(), nodeLocation.getBlockZ(), node.getParent(), node.getId()));

        }

    }

}
