package edu.iitb.tse.algo.network;

import edu.iitb.tse.algo.model.Link;
import edu.iitb.tse.algo.model.Network;
import edu.iitb.tse.algo.model.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {

    private final List<Node> nodes;
    private final List<Link> links;
    private Set<Node> settledNodes;
    private Set<Node> unSettledNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Double> distance;

    public DijkstraAlgorithm(Network network) {
        // Create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList(network.getNodes());
        this.links = new ArrayList(network.getLinks());
    }

    public void execute(Node source) {
        settledNodes = new HashSet();
        unSettledNodes = new HashSet();
        distance = new HashMap();
        predecessors = new HashMap();
        distance.put(source, 0.0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Node node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Node node) {
        List<Node> adjacentNodes = getNeighbors(node);
        for (Node target : adjacentNodes) {
            if (getShortestDistance1(target) > getShortestDistance1(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance1(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private double getDistance(Node node, Node target) {
        //link havaing same source and destination (parallal edge)
        ArrayList<Link> paralalLink = new ArrayList();
        for (Link link : links) {
            if (link.getSource().equals(node)
                    && link.getDestination().equals(target)) {
                paralalLink.add(link);
                link.paralla = true;
            }
        }
        return getMin(paralalLink);
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList();
        for (Link edge : links) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Node getMinimum(Set<Node> nodes) {
        Node minimum = null;
        for (Node node : nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance1(node) < getShortestDistance1(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Node node) {
        return settledNodes.contains(node);
    }

    private double getShortestDistance1(Node destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Node> getPath(Node target) {
        LinkedList<Node> path = new LinkedList();
        Node step = target;
        // Check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

    public double getShortestDistance(Node destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return 0;
        } else {
            return d;
        }
    }

    private double getMin(List<Link> paralalLink) {
        double min = paralalLink.get(0).getFreeFlowTravelTime();
        for (int i = 1; i < paralalLink.size(); i++) {
            if (paralalLink.get(i).getFreeFlowTravelTime() < min) {
                min = paralalLink.get(i).getFreeFlowTravelTime();
            }
        }
        return min;
    }
}