/*
 * mojang-maps.platform.main
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

package nl.abelkrijgtalles.mojangmaps.platform.world;

public class Vec3 {

    public final double x, y, z;

    public Vec3(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @return The x-value.
     */
    public double x() {

        return x;
    }

    /**
     * @return The y-value.
     */
    public double y() {

        return y;
    }

    /**
     * @return The z-value.
     */
    public double z() {

        return z;
    }

    /**
     * @param other The other {@link Vec3} to calculate the distance to.
     * @return The distance between the two {@link Vec3}s.
     */
    public double distanceTo(Vec3 other) {

        double x = other.x - this.x;
        double y = other.y - this.y;
        double z = other.z - this.z;

        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public String toString() {

        return "X: %s Y: %s Z: %s".formatted(x, y, z);
    }

    public String toCompactString() {

        return "%s|%s|%s".formatted(x, y, z);

    }

    public String toCompactFlooredString() {

        return "%s|%s|%s".formatted((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));

    }

}
