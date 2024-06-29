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

// The A* algorithm is an edited version of this implementation: https://github.com/psikoi/AStar-Pathfinding

package nl.abelkrijgtalles.MojangMaps.pathfinding;

import java.util.ArrayList;
import java.util.Observable;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.Network;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.Node;

public class AStar extends Observable {

    private Network network;
    private ArrayList<Node> path;

    private Node start;
    private Node end;

    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;

    public AStar(Network network) {

        this.network = network;
    }

    public void solve() {

        if (start == null && end == null) {
            return;
        }

        if (start.equals(end)) {
            this.path = new ArrayList<>();
            return;
        }

        this.path = new ArrayList<>();

        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();

        this.openList.add(start);

        while (!openList.isEmpty()) {
            Node current = getLowestF();

            if (current.equals(end)) {
                retracePath(current);
                break;
            }

            openList.remove(current);
            closedList.add(current);

            for (Node n : current.getNeighbours()) {

                if (closedList.contains(n) || !n.isValid()) {
                    continue;
                }

                double tempScore = current.getCost() + current.distanceTo(n);

                if (openList.contains(n)) {
                    if (tempScore < n.getCost()) {
                        n.setCost(tempScore);
                        n.setParent(current);
                    }
                } else {
                    n.setCost(tempScore);
                    openList.add(n);
                    n.setParent(current);
                }

                n.setHeuristic(n.heuristic(end));
                n.setFunction(n.getCost() + n.getHeuristic());

            }

        }

        updateUI();
    }

    public void reset() {

        this.start = null;
        this.end = null;
        this.path = null;
        this.openList = null;
        this.closedList = null;
        for (Node n : network.getNodes()) {
            n.setValid(true);
        }
    }

    private void retracePath(Node current) {

        Node temp = current;
        this.path.add(current);

        while (temp.getParent() != null) {
            this.path.add(temp.getParent());
            temp = temp.getParent();
        }

        this.path.add(start);
    }

    private Node getLowestF() {

        Node lowest = openList.get(0);
        for (Node n : openList) {
            if (n.getFunction() < lowest.getFunction()) {
                lowest = n;
            }
        }
        return lowest;
    }

    public void updateUI() {

        setChanged();
        notifyObservers();
        clearChanged();
    }

    public Network getNetwork() {

        return network;
    }

    public ArrayList<Node> getPath() {

        return path;
    }

    public Node getStart() {

        return start;
    }

    public void setStart(Node start) {

        this.start = start;
    }

    public Node getEnd() {

        return end;
    }

    public void setEnd(Node end) {

        this.end = end;
    }

}
