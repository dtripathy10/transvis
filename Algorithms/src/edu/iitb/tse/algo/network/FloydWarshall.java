/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iitb.tse.algo.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Floyd-Warshall algorithm for determining the shortest path between
 * two nodes in a graph.
 * <p>
 * Thread Safety: immutable
 */
public final class FloydWarshall {

    private final double [][] D;
    private final Nodew[][] P;

    /**
     * Create an instance of this class by describing the graph
     * upon which it will operate.
     * <p>
     * Note <code>Nodew.name</code> must contain the index of the
     * node in the <code>nodes</code> parameter.  Thus <code>Nodew[1].name</code>
     * must equal one.
     * <p>
     * On small computers the practical maximum graph size with a 4-byte Nodew is
     * about 23,000, at which point the data size of an instance begins to exceed 4 GB.
     *
     * @param nodes Array of Nodew; must be completely populated
     * @param edges Array of Edge, completely populated; order is not important
     */

    public FloydWarshall ( final Nodew[] nodes, final Edge[] edges )  {
        final int maxNodews = 23000;  // roughly 4 GB
        assert nodes.length < maxNodews : "nodes.length cannot exceed "+ maxNodews
            +".\nSize of class data structures is at least (2*(node size)*nodes.length**2).";

        D = initializeWeight(nodes, edges);
        P = new Nodew[nodes.length][nodes.length];

        for( int k=0; k<nodes.length; k++ )  {
            for( int i=0; i<nodes.length; i++ )  {
                for( int j=0; j<nodes.length; j++ )  {
                    if( D[i][k] != Integer.MAX_VALUE
                     && D[k][j] != Integer.MAX_VALUE
                     && D[i][k]+D[k][j] < D[i][j] )
                    {
                        D[i][j] = D[i][k]+D[k][j];
                        P[i][j] = nodes[k];
                    }
                }
            }
        }
    }

    /**
     * Determines the length of the shortest path from vertex A (source)
     * to vertex B (target), calculated by summing the weights of the edges
     * traversed.
     * <p>
     * Note that distance, like path, is not commutative.  That is,
     * distance(A,B) is not necessarily equal to distance(B,A).
     *
     * @param source Start Nodew
     * @param target End Nodew
     * @return The path length as the sum of the weights of the edges traversed,
     * or <code>Integer.MAX_VALUE</code> if there is no path
     */
    public double getShortestDistance ( final Nodew source, final Nodew target )  {
        return D[source.index][target.index];
    }

    /**
     * Describes the shortest path from vertex A (source) to vertex B (target)
     * by returning a collection of the vertices traversed, in the order traversed.
     * If there is no such path an empty collection is returned.
     * <p>
     * Note that because each Edge applies only to one direction of traverse,
     * the path from A to B may not be the same as the path from B to A.
     *
     * @param source Start Nodew
     * @param target End Nodew
     * @return A List (ordered Collection) of Nodew, possibly empty
     */
    public List<Nodew> getShortestPath ( final Nodew source, final Nodew target )  {
        if(D[source.index][target.index] == Integer.MAX_VALUE){
            return new ArrayList<Nodew>(); // no path
        }
        final List<Nodew> path = getIntermediatePath( source, target );
        path.add( 0, source );
        path.add( target );
        return path;
    }

    private List<Nodew> getIntermediatePath ( final Nodew source, final Nodew target )  {
        if(P[source.index][target.index] == null)  {
            return new ArrayList<Nodew>();
        }
        final List<Nodew> path = new ArrayList<Nodew>();
        path.addAll( getIntermediatePath(source, P[source.index][target.index]));
        path.add( P[source.index][target.index]);
        path.addAll( getIntermediatePath(P[source.index][target.index], target));
        return path;
    }

    private double[][] initializeWeight ( final Nodew[] nodes, final Edge[] edges )  {
        double[][] Weight = new double[nodes.length][nodes.length];
        for(int i=0; i<nodes.length; i++)  {
            Arrays.fill( Weight[i], Integer.MAX_VALUE );
        }
        for( Edge e : edges )  {
            Weight[e.from.index][e.to.index] = e.weight;
            Weight[e.to.index][e.from.index] = e.weight; // Distance between AB = Distance between BA
        }
        return Weight;
    }
}