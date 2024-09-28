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

import java.util.ArrayList;

public class Grid extends Network {

    private int width, height;
    private ArrayList<Tile> tiles;
    private int xOffset = 0;
    private int zOffset = 0;

    public Grid(int width, int height, ArrayList<Tile> tiles) {

        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    public int getWidth() {

        return width;
    }

    public int getHeight() {

        return height;
    }

    public ArrayList<Tile> getTiles() {

        return tiles;
    }

    public Tile find(int x, int z) {

        int gridX = x + xOffset;
        int gridZ = z + zOffset;

        for (Tile t : tiles) {
            if (t.getX() == gridX && t.getZ() == gridZ)
                return t;
        }
        return null;
    }

    public void setzOffset(int zOffset) {

        this.zOffset = zOffset;
    }

    public void setxOffset(int xOffset) {

        this.xOffset = xOffset;
    }

    public int getxOffset() {

        return xOffset;
    }

    public int getzOffset() {

        return zOffset;
    }

    @Override
    public Iterable<Node> getNodes() {

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.addAll(tiles);
        return nodes;
    }

}
