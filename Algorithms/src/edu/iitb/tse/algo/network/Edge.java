/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iitb.tse.algo.network;

import edu.iitb.tse.algo.model.Node;

public class Edge implements Comparable<Edge> {

   final Nodew from, to;
   final double weight;

   public Edge(final Nodew argFrom, final Nodew argTo, final double argWeight){
       from = argFrom;
       to = argTo;
       weight = argWeight;
   }

    @Override
    public int compareTo(Edge o) {
       return (int) (weight - o.weight);
    }
}
