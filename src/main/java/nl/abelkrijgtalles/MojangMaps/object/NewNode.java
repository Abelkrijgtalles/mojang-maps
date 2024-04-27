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

import java.util.UUID;
import nl.abelkrijgtalles.MojangMaps.util.other.MathUtil;
import org.bukkit.Location;

public class NewNode {

    private UUID uuid;
    private Location location;
    private UUID parent;
    private int gCost;
    private int hCost;
    private int fCost;

    public NewNode(Location location, UUID parent, int gCost, int hCost, int fCost) {

        this.setUuid(UUID.randomUUID());
        this.setLocation(location);
        this.setParent(parent);
        this.setgCost(gCost);
        this.sethCost(hCost);
        this.setfCost(fCost);
    }

    public NewNode(Location location, UUID parent, NewNode start, NewNode end) {

        this.setUuid(UUID.randomUUID());
        this.setLocation(location);
        this.setParent(parent);

        Location startLocation = start.getLocation();
        Location endLocation = end.getLocation();

        int gCostX = Math.abs(startLocation.getBlockX() - location.getBlockX());
        int gCostY = Math.abs(startLocation.getBlockY() - location.getBlockY());
        int gCostZ = Math.abs(startLocation.getBlockZ() - location.getBlockZ());

        int hCostX = Math.abs(endLocation.getBlockX() - location.getBlockX());
        int hCostY = Math.abs(endLocation.getBlockY() - location.getBlockY());
        int hCostZ = Math.abs(endLocation.getBlockZ() - location.getBlockZ());

        double rawGCost = MathUtil.pythagoreanTheorem(MathUtil.pythagoreanTheorem(gCostX, gCostY), gCostZ);
        double rawHCost = MathUtil.pythagoreanTheorem(MathUtil.pythagoreanTheorem(hCostX, hCostY), hCostZ);

        this.setgCost((int) Math.round(rawGCost * 100));
        this.sethCost((int) Math.round(rawHCost * 100));
        this.setfCost(this.getgCost() + this.gethCost());
    }

    public UUID getUuid() {

        return uuid;
    }

    public void setUuid(UUID uuid) {

        this.uuid = uuid;
    }

    public Location getLocation() {

        return location;
    }

    public void setLocation(Location location) {

        this.location = location;
    }

    public UUID getParent() {

        return parent;
    }

    public void setParent(UUID parent) {

        this.parent = parent;
    }

    public int getgCost() {

        return gCost;
    }

    public void setgCost(int gCost) {

        this.gCost = gCost;
    }

    public int gethCost() {

        return hCost;
    }

    public void sethCost(int hCost) {

        this.hCost = hCost;
    }

    public int getfCost() {

        return fCost;
    }

    public void setfCost(int fCost) {

        this.fCost = fCost;
    }

}
