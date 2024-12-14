/*
 * mojang_maps.common.main
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

package nl.abelkrijgtalles.mojangmaps.common.model;

import java.util.List;
import net.minecraft.core.Position;
import net.minecraft.world.level.storage.ServerLevelData;

public class Road {

    private String name;
    private String worldName;
    private List<Position> waypoints;

    public Road(String name, String worldName, List<Position> waypoints) {

        this.name = name;
        this.worldName = worldName;
        this.waypoints = waypoints;
    }

    public Road(String name, ServerLevelData worldData, List<Position> waypoints) {

        new Road(name, worldData.getLevelName(), waypoints);
    }

    public String getName() {

        return name;
    }

    public String getWorldName() {

        return worldName;
    }

    public List<Position> getWaypoints() {

        return waypoints;
    }

}
