package edu.iitb.tse.algo.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 *
 * @author Debabrata Tripathy
 */
class EdgeList {

    private Multimap<Node, Link> inEdgeList;
    private Multimap<Node, Link> outEdgeList;

    public EdgeList() {
        inEdgeList = ArrayListMultimap.create();
        outEdgeList = ArrayListMultimap.create();
    }

    public void addInEdgeList(Node node, Link link) {
        inEdgeList.put(node, link);
    }

    public void addOutEdgeList(Node node, Link link) {
        outEdgeList.put(node, link);
    }

    public Multimap<Node, Link> getInEdgeList() {
        return inEdgeList;
    }

    public Multimap<Node, Link> getOutEdgeList() {
        return outEdgeList;
    }
}
