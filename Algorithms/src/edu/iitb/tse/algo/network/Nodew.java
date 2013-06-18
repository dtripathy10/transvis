/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iitb.tse.algo.network;

public class Nodew implements Comparable<Nodew> {

   int index = -1;            // used for Tarjan's algorithm
   public Nodew(final int argName) {
       index = argName;
   }

    @Override
   public int compareTo(final Nodew argNode) {
       return argNode == this ? 0 : -1;
   }
}
