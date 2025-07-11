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

public class Edge {

    private final Node node1, node2;
    private final String name;

    public Edge(Node node1, Node node2) {

        this(node1, node2, null);
    }

    public Edge(Node node1, Node node2, String name) {

        this.node1 = node1;
        this.node2 = node2;
        this.name = name;
    }

    public Edge(double x1, double y1, double z1, String worldIdentifier1, double x2, double y2, double z2, String worldIdentifier2) {

        this(x1, y1, z1, worldIdentifier1, x2, y2, z2, worldIdentifier2, null);

    }

    public Edge(double x1, double y1, double z1, String worldIdentifier1, double x2, double y2, double z2, String worldIdentifier2, String name) {

        this(new Node(x1, y1, z1, worldIdentifier1), new Node(x2, y2, z2, worldIdentifier2), name);

    }

    public static Edge fromCompactFlooredString(String edge) {

        return fromCompactFlooredString(edge, null);

    }

    public static Edge fromCompactFlooredString(String edge, String name) {

        String[] nodeCoordinates = edge.split("\\|");
        if (nodeCoordinates.length != 8) {
            throw new IllegalArgumentException();
        }
        double[] values = new double[7];
        for (int i = 0; i < 8; i++) {

            if (i == 3 || i == 7) {
                continue;
            }

            values[i] = Double.parseDouble(nodeCoordinates[i]); // TODO: maybe optimize so we don't have two empty fields in the array?

        }

        return new Edge(values[0], values[1], values[2], nodeCoordinates[3], values[4], values[5], values[6], nodeCoordinates[7], name);

    }

    public String getName() {

        return name;
    }

    public Node getNode1() {

        return node1;
    }

    @Override
    public String toString() {

        return node1.toCompactFlooredString() + "|" + node2.toCompactFlooredString();
    }

    public Node getNode2() {

        return node2;
    }

}
