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

package nl.abelkrijgtalles.mojangmaps.common.pathfinding.abstraction;

import org.jetbrains.annotations.NotNull;

public class RouteNode<T extends GraphNode> implements Comparable<RouteNode> {

    private final T current;
    private T previous;
    private double routeScore;
    private double estimatedScore;

    public RouteNode(T current) {

        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public RouteNode(T current, T previous, double routeScore, double estimatedScore) {

        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    @Override
    public int compareTo(@NotNull RouteNode o) {

        return Double.compare(this.estimatedScore, o.estimatedScore);

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
