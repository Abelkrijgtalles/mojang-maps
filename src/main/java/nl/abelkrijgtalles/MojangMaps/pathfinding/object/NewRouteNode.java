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

import org.jetbrains.annotations.NotNull;

public class NewRouteNode<T extends NewGraphNode> implements Comparable<NewRouteNode> {

    private final T current;
    private T previous;
    private double routeScore;
    private double estimatedScore;

    NewRouteNode(T current) {

        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    NewRouteNode(T current, T previous, double routeScore, double estimatedScore) {

        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    @Override
    public int compareTo(@NotNull NewRouteNode o) {

        if (this.estimatedScore > o.estimatedScore) {
            return 1;
        } else if (this.estimatedScore < o.estimatedScore) {
            return -1;
        } else {
            return 0;
        }

    }

    public T getCurrent() {

        return current;
    }

    public T getPrevious() {

        return previous;
    }

    public void setPrevious(T previous) {

        this.previous = previous;
    }

    public double getRouteScore() {

        return routeScore;
    }

    public void setRouteScore(double routeScore) {

        this.routeScore = routeScore;
    }

    public void setEstimatedScore(double estimatedScore) {

        this.estimatedScore = estimatedScore;
    }

}
