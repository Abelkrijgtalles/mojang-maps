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

package nl.abelkrijgtalles.MojangMaps.pathfinding.object;

import java.awt.Point;
import java.util.ArrayList;

public class Tile extends Node {

    public static int TILE_SIZE = 20;
    private int x, y;

    public Tile(int x, int y) {

        this.x = x;
        this.y = y;
        setValid(true);
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    @Override
    public void calculateNeighbours(Network network) {

        Grid grid = (Grid) network;

        ArrayList<Node> nodes = new ArrayList<>();

        int minX = 0;
        int minY = 0;
        int maxX = grid.getWidth() - 1;
        int maxY = grid.getHeight() - 1;

        if (x > minX) {
            nodes.add(grid.find(x - 1, y)); //west
        }

        if (x < maxX) {
            nodes.add(grid.find(x + 1, y)); //east
        }

        if (y > minY) {
            nodes.add(grid.find(x, y - 1)); //north
        }

        if (y < maxY) {
            nodes.add(grid.find(x, y + 1)); //south
        }

        if (x > minX && y > minY) {
            nodes.add(grid.find(x - 1, y - 1)); //northwest
        }

        if (x < maxX && y < maxY) {
            nodes.add(grid.find(x + 1, y + 1)); //southeast
        }

        if (x < maxX && y > minY) {
            nodes.add(grid.find(x + 1, y - 1)); //northeast
        }

        if (x > minY && y < maxY) {
            nodes.add(grid.find(x - 1, y + 1)); //southwest
        }

        setNeighbours(nodes);

    }

    @Override
    public double heuristic(Node dest) {

        return distanceTo(dest);
    }

    @Override
    public double distanceTo(Node dest) {

        Tile d = (Tile) dest;
        return new Point(x, y).distance(new Point(d.x, d.y));
    }

}

