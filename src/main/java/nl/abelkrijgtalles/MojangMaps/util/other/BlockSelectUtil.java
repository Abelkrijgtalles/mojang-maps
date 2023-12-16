/*
 * MojangMaps
 * Copyright (C) 2023 Abel van Hulst/Abelkrijgtalles/Abelpro678
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

package nl.abelkrijgtalles.MojangMaps.util.other;

import org.bukkit.Axis;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class BlockSelectUtil {

    public static void getSelectedBlock(Location selectedLocation, Player p) {

        // btw, I don't even know what a rad is yet, because I haven't had/discussed it in school yet 👍


    }

    public static double calculateAngleOnAxis(Location location1, Location location2, Axis axis) {
        Vector vector1 = location1.toVector();
        Vector vector2 = location2.toVector();

        double dotProduct = vector1.dot(vector2);
        double magnitude1 = vector1.length();
        double magnitude2 = vector2.length();

        double cosTheta = dotProduct / (magnitude1 * magnitude2);

        double angle = Math.acos(cosTheta);

        angle = Math.toDegrees(angle);

        return switch (axis) {
            case X, Y -> angle;
            case Z -> 180 - angle;
        };
    }


}
