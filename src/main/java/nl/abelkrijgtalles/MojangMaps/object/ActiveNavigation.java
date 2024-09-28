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

package nl.abelkrijgtalles.MojangMaps.object;

import java.util.*;
import nl.abelkrijgtalles.MojangMaps.command.using.StartNavigationCommand;
import nl.abelkrijgtalles.MojangMaps.pathfinding.AStar;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.Grid;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.Node;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.Tile;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.LocationUtil;
import nl.abelkrijgtalles.MojangMaps.util.pathfinding.GridUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ActiveNavigation implements Observer {

    private final UUID playerID;
    private final Location beginning;
    private final Location destination;
    private final Location closestBeginning;
    private final Location closestDestination;

    public ActiveNavigation(UUID playerID, Location beginning, Location destination) {

        this.playerID = playerID;
        this.beginning = beginning;
        this.destination = destination;

        List<Location> locations = NodesConfigUtil.getLocations();

        closestBeginning = LocationUtil.getClosestLocation(locations, beginning);
        closestDestination = LocationUtil.getClosestLocation(locations, destination);

        Grid grid = GridUtil.generateGridWithLowestAndHighestLocations(locations);
        for (Tile tile : grid.getTiles()) {

            tile.calculateNeighbours(grid);
            System.out.println(tile.getX() + " " + tile.getZ() + " " + tile.isValid());

        }

        AStar aStar = new AStar(grid);
        aStar.setStart(grid.find(closestBeginning.getBlockX(), closestBeginning.getBlockZ()));
        aStar.setEnd(grid.find(closestDestination.getBlockX(), closestDestination.getBlockZ()));
        System.out.println("Balls");
        System.out.println(grid.find(closestBeginning.getBlockX(), closestBeginning.getBlockZ()));
        System.out.println(grid.find(closestDestination.getBlockX(), closestDestination.getBlockZ()));
        System.out.println("andere shit");

        int beginx = closestBeginning.getBlockX() + grid.getxOffset();
        int beginz = closestBeginning.getBlockZ() + grid.getzOffset();
        int endx = closestDestination.getBlockX() + grid.getxOffset();
        int endz = closestDestination.getBlockZ() + grid.getzOffset();

        System.out.println(closestBeginning.getBlockX() + " " + closestBeginning.getBlockZ());
        System.out.println(beginx + " " + beginz);
        System.out.println(closestDestination.getBlockX() + " " + closestDestination.getBlockZ());
        System.out.println(endx + " " + endz);

        aStar.addObserver(this);
        aStar.updateUI();
        aStar.solve();

    }

    public UUID getPlayerID() {

        return playerID;
    }

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("upadte");


        AStar aStar = (AStar) o;
        ArrayList<Node> path = aStar.getPath();
        Player p = Bukkit.getPlayer(playerID);

        if (path != null) {
            for (Node node : path) {
                if (node instanceof Tile tile) {
                    p.chat(tile.getX() + " " + tile.getZ());
                    System.out.println("testing");
                }
            }
        } else {
            p.chat("Womp womp where is the path");
            System.out.println("testing2");
        }

        StartNavigationCommand.activeNavigations.remove(this);

    }

}
