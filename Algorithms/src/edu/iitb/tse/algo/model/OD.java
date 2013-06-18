
package edu.iitb.tse.algo.model;

/**
 *
 * @author dtripathy10
 */
public class OD {
    private Node source;
    private Node destination;
    private double demand;

    public OD(Node source, Node destination, double demand) {
        this.source = source;
        this.destination = destination;
        this.demand = demand;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public double getDemand() {
        return demand;
    }

    @Override
    public String toString() {
        return "OD{" + "source=" + source + ", destination=" + destination + ", demand=" + demand + '}';
    }

}
