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

import java.util.Objects;
import nl.abelkrijgtalles.MojangMaps.util.other.MathUtil;
import org.bukkit.Location;

public class NewNode {

    private Location location;
    private Integer parent;
    private int gCost = 0;
    private int hCost = 0;
    private int fCost = 0;

    public NewNode(Location location, Integer parent, int gCost, int hCost, int fCost) {

        this.setLocation(location);
        this.setParent(parent);
        this.setgCost(gCost);
        this.sethCost(hCost);

    }

    public NewNode(Location location, Integer parent, NewNode start, NewNode end) {

        this.setLocation(location);
        this.setParent(parent);

    }

    public void setStart(NewNode start) {

        double rawGCost = calculateCostToNode(start);
        setgCost((int) Math.round(rawGCost * 100));

    }

    public double calculateCostToNode(NewNode node) {

        Location nodeLocation = node.getLocation();

        int costX = Math.abs(nodeLocation.getBlockX() - location.getBlockX());
        int costY = Math.abs(nodeLocation.getBlockY() - location.getBlockY());
        int costZ = Math.abs(nodeLocation.getBlockZ() - location.getBlockZ());

        return MathUtil.pythagoreanTheorem(MathUtil.pythagoreanTheorem(costX, costY), costZ);

    }

    public void setEnd(NewNode end) {

        double rawHCost = calculateCostToNode(end);
        sethCost((int) Math.round(rawHCost * 100));

    }

    public Integer getId() {

        return Objects.hash(location);

    }

    public Location getLocation() {

        return location;
    }

    public void setLocation(Location location) {

        this.location = location;
    }

    public Integer getParent() {

        return parent;
    }

    public void setParent(Integer parent) {

        this.parent = parent;
    }

    public int getgCost() {

        return gCost;
    }

    public void setgCost(int gCost) {

        this.gCost = gCost;
        setfCost(gCost + gethCost());

    }

    public int gethCost() {

        return hCost;
    }

    public void sethCost(int hCost) {

        this.hCost = hCost;
        setfCost(getgCost() + gethCost());

    }

    public int getfCost() {

        return fCost;
    }

    public void setfCost(int fCost) {

        this.fCost = fCost;
    }

}
