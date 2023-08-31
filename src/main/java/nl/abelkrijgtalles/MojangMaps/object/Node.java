package nl.abelkrijgtalles.MojangMaps.object;

import nl.abelkrijgtalles.MojangMaps.util.file.MessageUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.NodeUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.RoadUtil;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// All this is from the following YouTube video: https://youtu.be/BuvKtCh0SKk

public class Node implements Comparable<Node> {

    private final String name;
    private final Map<Node, Integer> adjacentNodes = new HashMap<>();
    private Integer distance = Integer.MAX_VALUE;
    private List<Node> shortestPath = new LinkedList<>();

    public Node(String name) {
        this.name = name;
    }

    public void addAdjacentNode(Node node, int weight) {
        adjacentNodes.put(node, weight);
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }

    public static void calculateShortestPath(Node source) {
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));
        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll();
            currentNode.getAdjacentNodes()
                    .entrySet().stream()
                    .filter(entry -> !settledNodes.contains(entry.getKey()))
                    .forEach(entry -> {
                        evaluteDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });
            settledNodes.add(currentNode);
        }
    }

    private static void evaluteDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode) {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(
                    Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList()
            );
        }
    }

    public static void printPaths(List<Node> nodes, Player p) {
        nodes.forEach(node -> {
            String path = node.getShortestPath().stream()
                    .map(Node::getLocationText)
                    .collect(Collectors.joining(MessageUtil.getMessage("thengoto")));
            if (node.getDistance() != Integer.MAX_VALUE) {
                p.sendMessage((path.isBlank()
                        ? MessageUtil.getMessage("blocksprediction").formatted(node.getLocationText(), node.getDistance())
                        : MessageUtil.getMessage("thengoto").formatted(path) + MessageUtil.getMessage("blocksprediction").formatted(node.getLocationText(), node.getDistance())));
            } else {

                p.sendMessage(ChatColor.RED + MessageUtil.getMessage("pathnotfound"));

            }
        });
    }

    public static List<String> getLocationAndDistanceList(List<Node> nodes) {
        List<String> locationAndDistanceList = new ArrayList<>();
        for (Node node : nodes) {
            String locationText = node.getLocationText();
            int distance = node.getDistance();
            String entry = locationText + " (Distance: " + distance + ")";
            locationAndDistanceList.add(entry);
        }
        return locationAndDistanceList;
    }

    public String getLocationText() {

        Location location = NodeUtil.getLocationFromNode(this);

        if (RoadUtil.getRoadNameFromLocation(location) != null) {

            return "X: " + location.getBlockX() + " Z: " + location.getBlockZ() + " (" + RoadUtil.getRoadNameFromLocation(location) + ")";

        }

        return "X: " + location.getBlockX() + " Z: " + location.getBlockZ();

    }
}