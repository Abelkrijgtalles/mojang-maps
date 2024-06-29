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

import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import nl.abelkrijgtalles.MojangMaps.pathfinding.AStar;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.Grid;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.Node;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.Tile;
import org.bukkit.entity.Player;

public class AStarTest implements Observer {

    public AStarTest(Player p) throws WrapperCommandSyntaxException {

        // start 0,-61,0
        // end 25,-61,25

        Grid grid = generateGrid(25, 25);
        for (Tile t : grid.getTiles()) {
            t.calculateNeighbours(grid);
        }

        AStar aStar = new AStar(grid);
        aStar.setStart(grid.find(0, 0));
        aStar.setEnd(grid.find(25, 25));
        grid.find(12, 12).setValid(false);

    }

    private static Grid generateGrid(int width, int height) {

        ArrayList<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile t = new Tile(i, j);
                tiles.add(t);
            }
        }

        return new Grid(width, height, tiles);

    }

    @Override
    public void update(Observable o, Object arg) {

        AStar aStar = (AStar) o;
        ArrayList<Node> path = aStar.getPath();

        if (path != null) {
            for (Node node : path) {
                if (node instanceof Tile tile) {
                    System.out.println(tile.getX() + " " + tile.getY());
                }
            }
        } else {
            System.out.println("Womp womp no path found");
        }

    }

}
