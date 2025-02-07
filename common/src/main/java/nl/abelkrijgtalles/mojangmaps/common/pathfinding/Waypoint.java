/*
 * mojang_maps.common.main
 * Copyright (C) 2025 Abel van Hulst/Abelkrijgtalles/Abelpro678
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

package nl.abelkrijgtalles.mojangmaps.common.pathfinding;

import nl.abelkrijgtalles.mojangmaps.common.pathfinding.abstraction.GraphNode;
import nl.abelkrijgtalles.mojangmaps.platform.world.Vec3;

public class Waypoint implements GraphNode {

    // TODO: include some sort of road specification
    // TODO if that is added: make more optimised version with road manager or some sorts. But if I use a index based system, and a road gets added, it could shift. Just don't forget that Abel. But maybe also it wouldn't if it got added to the back of the list, but if it was edited maybe it would idk I will see later.
    private final Vec3 position;

    public Waypoint(Vec3 position) {

        this.position = position;
    }

    @Override
    public String getIdentifier() {

        return "%s, %s, %s".formatted(position.x, position.y, position.z);
    }

    public Vec3 getPosition() {

        return position;
    }

    @Override
    public String toString() {

        return "Waypoint on X %s Y %s Z %s".formatted(position.x, position.y, position.z);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (!(obj instanceof Waypoint waypoint)) return false;

        else return position.equals(waypoint.position);

    }

    public double getX() {

        return position.x;

    }

    public double getY() {

        return position.y;

    }

    public double getZ() {

        return position.z;

    }

}
