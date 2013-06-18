package edu.iitb.tse.algo.model;

import edu.iitb.tse.algo.network.DijkstraAlgorithm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Debabrata Tripathy
 */
public class Network {

    private Map<Node, EdgeList> nodes;
    private List<Link> links;

    public Network() {
        //Constructor
        nodes = new HashMap();
        links = new ArrayList();
    }

    public void addNode(Node node) {
        for (Node tempnode : nodes.keySet()) {
            if (tempnode.getId().endsWith(node.getId())) {
                return;
            }
        }
        EdgeList e = new EdgeList();
        nodes.put(node, e);
    }

    public void addLink(Link link) {
        links.add(link);
        EdgeList e1 = nodes.get(link.getSource());
        //If e1 is null, then create one node
        if (e1 == null) {
            Node node = link.getSource();
            addNode(node);
            e1 = nodes.get(link.getSource());
        }
        e1.addOutEdgeList(link.getDestination(), link);
        EdgeList e2 = nodes.get(link.getDestination());
        //If e2 is null, then create one node
        if (e2 == null) {
            Node node = link.getDestination();
            addNode(node);
            e2 = nodes.get(link.getDestination());
        }
        e2.addInEdgeList(link.getSource(), link);
    }

    public Link getLink(String id) {
        for (Link link : links) {
            if (link.getId() == null ? id == null : link.getId().equals(id)) {
                return link;
            }
        }
        return null;
    }

    public Node getNode(String id) {
        for (Node node : nodes.keySet()) {
            if (node.getId() == null ? id == null : node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }

    public List<Node> getNodes() {
        return new ArrayList(nodes.keySet());
    }

    public List<Link> getLinks() {
        return links;
    }

    //gives the number of Link+ number of node
    public int size() {
        return nodes.size() + links.size();
    }

    public int numberOfLinks() {
        return links.size();
    }

    public int numberOfNodes() {
        return nodes.size();
    }

    public Node getDestination(Link e) {
        return e.getDestination();
    }

    public Node getSource(Link e) {
        return e.getSource();
    }

    public boolean areAdjacent(Node v, Node w) {
        EdgeList e = nodes.get(v);
        for (Node node : e.getInEdgeList().keySet()) {
            if (node.equals(w)) {
                return true;
            }
        }
        for (Iterator<Node> it = e.getOutEdgeList().keySet().iterator(); it.hasNext();) {
            Node node = it.next();
            if (node.equals(w)) {
                return true;
            }
        }
        return false;
    }

    public List<Node> inIncidentEdges(Node v) {
        EdgeList e = nodes.get(v);
        List<Node> inIncidentEdges = new ArrayList();
        for (Node node : e.getInEdgeList().keySet()) {
            inIncidentEdges.add(node);
        }
        return inIncidentEdges;
    }

    public List<Node> outIncidentEdges(Node v) {
        EdgeList e = nodes.get(v);
        List<Node> outIncidentEdges = new ArrayList();
        for (Node node : e.getOutEdgeList().keySet()) {
            outIncidentEdges.add(node);
        }
        return outIncidentEdges;
    }

    public List<Node> incidentEdges(Node v) {
        EdgeList e = nodes.get(v);
        List<Node> incidentEdges = new ArrayList();
        for (Node node : e.getOutEdgeList().keySet()) {
            incidentEdges.add(node);
        }
        for (Node node : e.getOutEdgeList().keySet()) {
            incidentEdges.add(node);
        }
        return incidentEdges;
    }

    public int degree(Node v) {
        return nodes.get(v).getInEdgeList().size() + nodes.get(v).getOutEdgeList().size();
    }

    public int inDegree(Node v) {
        return nodes.get(v).getInEdgeList().size();
    }

    public int outDegree(Node v) {
        return nodes.get(v).getOutEdgeList().size();
    }

    public List<Link> getAllLinkFromRoute(List<Node> path) {
        List<Link> temp = new ArrayList();
        Iterator<Node> iterator = path.iterator();
        Node first = iterator.next();
        while (iterator.hasNext()) {
            Node last = iterator.next();
            EdgeList e = nodes.get(first);
            for (Node node : e.getOutEdgeList().keySet()) {
                if (node.getId().equals(last.getId())) {
                    Collection<Link> linkCollection = e.getOutEdgeList().get(node);
                    temp.add(getMinLink(linkCollection));
                }
            }
            first = last;
        }
        return temp;
    }

    @Override
    public String toString() {
        return "Network{" + "nodes=" + nodes.keySet() + "\n\n, links=" + links + '}';
    }

    public void resetLinkVolume() {
        for (Link link : links) {
            link.setVolume(0);
        }
    }

    public void resetLinkCapacity() {
        for (Link link : links) {
            link.setCapacity(0);
        }
    }

    public Path getShortestPath(Node source, Node destination) {
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(this);
        dijkstra.execute(source);
        Path path = new Path();
        List<Node> tempNodes = dijkstra.getPath(destination);
        List<Link> tempLinks = new ArrayList();
        if (tempNodes == null) {
            tempNodes = new ArrayList();
            path.setNodes(tempNodes);
            path.setLinks(tempLinks);
            return path;
        }
        tempLinks = getAllLinkFromRoute(tempNodes);
        path.setNodes(tempNodes);
        path.setLinks(tempLinks);
        return path;
    }

    public boolean isNodePresent(int x, int y) {
//        int radius = Node.DEFAULTRADIUS;
//        for (Node s : this.getNodes()) {
//            System.out.println(s);
//            Point2D check = new Point2D.Float(s.getxCordinate(), s.getyCordinate());
//            int D = (int) (Math.pow(check.getX() - x, 2) + Math.pow(check.getY() - y, 2));
//            if (D <= (radius * radius)) {
//                return true;
//            }
//        }
        return false;
    }

    public Node getNode(int x, int y) {
//        int radius = this.getNodes().get(0).getRadius();
//        for (Node s : this.getNodes()) {
//            Point2D check = new Point2D.Float(s.getxCordinate(), s.getyCordinate());
//            int D = (int) (Math.pow(check.getX() - x, 2) + Math.pow(check.getY() - y, 2));
//            if (D <= (radius * radius)) {
//                return s;
//            }
//        }
        return null;
    }

    public void reset() {
        resetLinkCapacity();
        resetLinkVolume();
    }

    private Link getMinLink(Collection<Link> paralalLink) {
        Link temp = null;
        double min = 0;
        boolean check = true;
        for (Iterator<Link> it = paralalLink.iterator(); it.hasNext();) {
            Link link = it.next();
            if (check) {
                check = false;
                min = link.getFreeFlowTravelTime();
                temp = link;
            } else {
                if (link.getFreeFlowTravelTime() < min) {
                    min = link.getFreeFlowTravelTime();
                    temp = link;
                }
            }
        }
        return temp;
    }
}
