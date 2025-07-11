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

import com.carrotsearch.hppc.procedures.IntProcedure;
import com.graphhopper.routing.AStar;
import com.graphhopper.routing.Path;
import com.graphhopper.routing.ev.BooleanEncodedValue;
import com.graphhopper.routing.ev.DecimalEncodedValue;
import com.graphhopper.routing.ev.VehicleAccess;
import com.graphhopper.routing.ev.VehicleSpeed;
import com.graphhopper.routing.querygraph.QueryGraph;
import com.graphhopper.routing.util.EdgeFilter;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.TraversalMode;
import com.graphhopper.routing.weighting.TurnCostProvider;
import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.routing.weighting.custom.CustomModelParser;
import com.graphhopper.storage.BaseGraph;
import com.graphhopper.storage.NodeAccess;
import com.graphhopper.storage.RAMDirectory;
import com.graphhopper.storage.index.LocationIndexTree;
import com.graphhopper.storage.index.Snap;
import com.graphhopper.util.CustomModel;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.platform.world.WorldBorder;
import static com.graphhopper.json.Statement.If;
import static com.graphhopper.json.Statement.Op.LIMIT;
import static com.graphhopper.json.Statement.Op.MULTIPLY;

public class Navigator {

    private final String graphLocation = String.valueOf(java.nio.file.Path.of(MojangMaps.loaderInfo.getDataDirectory().toAbsolutePath().toString(), "graph"));
    private List<Node> nodes;

    public Navigator() {

        createAndSaveGraph();
    }

    private void createAndSaveGraph() {

        BooleanEncodedValue accessEnc = VehicleAccess.create("car");
        DecimalEncodedValue speedEnc = VehicleSpeed.create("car", 7, 2, false);
        EncodingManager em = EncodingManager.start().add(accessEnc).add(speedEnc).build();
        BaseGraph graph = new BaseGraph.Builder(em).setDir(new RAMDirectory(graphLocation, true)).set3D(true).create();

        NodeAccess na = graph.getNodeAccess();

        try {

            // so we don't have overlapping nodes
            Set<Node> actualNodes = new HashSet<>();

            // this is about to get filled with ugly function calls
            // after coding jesus' pirate software video, I am scared for bad looking/ugly function calls.
            for (Edge edge : MojangMaps.roadDatabase.getEdges()) {

                boolean alreadyContainsNode1 = false;
                boolean alreadyContainsNode2 = false;

                if (actualNodes.contains(edge.getNode1())) {
                    alreadyContainsNode1 = true;
                    addNodeToNodeAccess(actualNodes.stream().toList().indexOf(edge.getNode1()), edge.getNode1(), na);
                }

                if (actualNodes.contains(edge.getNode2())) {
                    alreadyContainsNode2 = true;
                    addNodeToNodeAccess(actualNodes.stream().toList().indexOf(edge.getNode2()), edge.getNode2(), na);
                }

                actualNodes.add(edge.getNode1());
                actualNodes.add(edge.getNode2());

                if (!alreadyContainsNode1) {
                    addNodeToNodeAccess(actualNodes.stream().toList().indexOf(edge.getNode1()), edge.getNode1(), na);
                }

                if (!alreadyContainsNode2) {
                    addNodeToNodeAccess(actualNodes.stream().toList().indexOf(edge.getNode2()), edge.getNode2(), na);
                }

                graph.edge(actualNodes.stream().toList().indexOf(edge.getNode1()), actualNodes.stream().toList().indexOf(edge.getNode2())).set(accessEnc, true).set(speedEnc, 10).setDistance(edge.getNode1().distanceTo(edge.getNode2()));

            }

            nodes = actualNodes.stream().toList();

        } catch (SQLException e) {
            MojangMaps.LOGGER.error("Couldn't retrieve edges from database.");
            throw new RuntimeException();
        }

        LocationIndexTree index = new LocationIndexTree(graph, graph.getDirectory());
        index.prepareIndex();

        graph.flush();
        index.flush();
        graph.close();
        index.close();

    }

    public Set<Node> calculatePath(Node from, Node to) {

        if (!from.worldIdentifier().equals(to.worldIdentifier())) {
            // TODO: IMPLEMENT IT
            throw new IllegalArgumentException("Multi-world/level/dimensional travel isn't implemented yet.");
        }

        BooleanEncodedValue accessEnc = VehicleAccess.create("car");
        DecimalEncodedValue speedEnc = VehicleSpeed.create("car", 7, 2, false);
        EncodingManager em = EncodingManager.start().add(accessEnc).add(speedEnc).build();
        BaseGraph graph = new BaseGraph.Builder(em).setDir(new RAMDirectory(graphLocation, true)).set3D(true).build();
        graph.loadExisting();

        LocationIndexTree index = new LocationIndexTree(graph.getBaseGraph(), graph.getDirectory());
        if (!index.loadExisting())
            throw new IllegalStateException("Location index cannot be loaded :((((");

        Snap fromSnap = index.findClosest(from.x(), from.z(), EdgeFilter.ALL_EDGES);
        Snap toSnap = index.findClosest(to.x(), to.z(), EdgeFilter.ALL_EDGES);
        QueryGraph queryGraph = QueryGraph.create(graph, fromSnap, toSnap);
        Weighting weighting = CustomModelParser.createWeighting(em, TurnCostProvider.NO_TURN_COST_PROVIDER,
                new CustomModel().addToPriority(If("!" + accessEnc.getName(), MULTIPLY, "0")).addToSpeed(If("true", LIMIT, speedEnc.getName())));
        Path path = new AStar(queryGraph, weighting, TraversalMode.NODE_BASED).calcPath(fromSnap.getClosestNode(), toSnap.getClosestNode());
        Set<Node> nodePath = new HashSet<>();
        path.calcNodes().forEach((IntProcedure) intCursor -> {
            System.out.println(intCursor);
            nodePath.add(nodes.get(intCursor));
        });
        return nodePath;

    }

    private Pair<Double, Double> getLatitudeAndLongitude(Node node) {

        // magic number
        float absoluteMaxWorldBorderSize = 5.999997E7F;

        // I think this is the best resemblance.
        // Minecraft directions:
        // +X = east, -X = west, +Z = south, -Z = north
        // Latitude & Longitude:
        // +lat = north, -lat = south, +lon = east, -lon = west
        double lat = -node.z();
        double lon = node.x();

        WorldBorder worldBorder = MojangMaps.loaderInfo.getPlatform().getLevel(node.worldIdentifier()).getWorldBorder();

        lat += worldBorder.getCenter().z();
        lon -= worldBorder.getCenter().x();

        return new Pair<>(lat * 90 / absoluteMaxWorldBorderSize, lon * 180 / absoluteMaxWorldBorderSize);

    }

    private void addNodeToNodeAccess(Integer i, Node node, NodeAccess na) {

        Pair<Double, Double> latitudeAndLongitude = getLatitudeAndLongitude(node);
        na.setNode(i, latitudeAndLongitude.component1(), latitudeAndLongitude.component2(), node.y());

    }

}
