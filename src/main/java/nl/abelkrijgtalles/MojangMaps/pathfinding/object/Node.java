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

public abstract class Node {

    private Node parent;
    private ArrayList<Node> neighbours;
    private double cost, heuristic, function;
    private boolean valid;

    public abstract void calculateNeighbours(Network network);

    public abstract double distanceTo(Node dest);

    public abstract double heuristic(Node dest);

    public double getCost() {

        return cost;
    }

    public void setCost(double cost) {

        this.cost = cost;
    }

    public double getHeuristic() {

        return heuristic;
    }

    public void setHeuristic(double heuristic) {

        this.heuristic = heuristic;
    }

    public double getFunction() {

        return function;
    }

    public void setFunction(double function) {

        this.function = function;
    }


    public ArrayList<Node> getNeighbours() {

        return neighbours;
    }

    public void setNeighbours(ArrayList<Node> neighbours) {

        this.neighbours = neighbours;
    }

    public Node getParent() {

        return parent;
    }

    public void setParent(Node parent) {

        this.parent = parent;
    }

    public boolean isValid() {

        return valid;
    }

    public void setValid(boolean valid) {

        this.valid = valid;
    }

    public void reverseValidation() {

        valid = !valid;
    }

}

