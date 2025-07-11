/*
 * mojang-maps.common.main
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

package nl.abelkrijgtalles.mojangmaps.common.model;

import java.util.Objects;
import nl.abelkrijgtalles.mojangmaps.platform.world.Vec3;

public class Node extends Vec3 {

    private final String worldIdentifier;

    public Node(double x, double y, double z, String worldIdentifier) {

        super(x, y, z);
        this.worldIdentifier = worldIdentifier;
    }

    public Node(Vec3 node, String worldIdentifier) {

        super(node.x(), node.y(), node.z());
        this.worldIdentifier = worldIdentifier;

    }

    public static Node fromCompactFlooredString(String node) {

        String[] nodeCoordinates = node.split("\\|");
        if (nodeCoordinates.length != 4) {
            throw new IllegalArgumentException();
        }
        double[] values = new double[3];
        for (int i = 0; i < 3; i++) {

            values[i] = Double.parseDouble(nodeCoordinates[i]);

        }

        return new Node(values[0], values[1], values[2], nodeCoordinates[3]);

    }

    public String worldIdentifier() {

        return worldIdentifier;
    }

    @Override
    public String toCompactString() {

        return super.toCompactString() + "|" + worldIdentifier;
    }

    @Override
    public String toCompactFlooredString() {

        return super.toCompactFlooredString() + "|" + worldIdentifier;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(x, node.x) &&
                Objects.equals(y, node.y) &&
                Objects.equals(z, node.z) &&
                Objects.equals(worldIdentifier, node.worldIdentifier);
    }

}
