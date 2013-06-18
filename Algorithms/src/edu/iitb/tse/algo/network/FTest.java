package edu.iitb.tse.algo.network;

import edu.iitb.tse.algo.model.Link;
import edu.iitb.tse.algo.model.Network;
import edu.iitb.tse.algo.model.Node;
import java.util.HashMap;

/**
 *
 * @author dtripathy10
 */
public class FTest {

    private Nodew[] nodes;
    private Edge[] edges;
    private HashMap<Node, Extra> customIndexNode = new HashMap<Node, Extra>();
    private FloydWarshall floyd;

    public FTest(Network network) {
        int counter = 0;
        nodes = new Nodew[network.getNodes().size()];
        edges = new Edge[network.getLinks().size()];

        for (Node node : network.getNodes()) {
            Nodew nodew = new Nodew(counter);
            nodes[counter] = nodew;
            customIndexNode.put(node, new Extra(counter, nodew));
            counter++;
        }
        counter = 0;
        for (Link link : network.getLinks()) {
            Nodew source = customIndexNode.get(link.getSource()).nodew;
            Nodew dest = customIndexNode.get(link.getDestination()).nodew;
            Edge edge = new Edge(source, dest, link.getFreeFlowTravelTime());
            edges[counter] = edge;
            counter++;
        }
        /////////////
        floyd = new FloydWarshall(nodes, edges);
    }
}
