package edu.iitb.tse.algo.core;

import edu.iitb.tse.algo.model.Link;
import edu.iitb.tse.algo.model.Network;
import edu.iitb.tse.algo.model.Node;
import edu.iitb.tse.algo.model.OD;
import edu.iitb.tse.algo.model.ODTable;
import edu.iitb.tse.algo.model.Path;
import java.util.Iterator;
import org.openide.windows.InputOutput;

public class AllOrNothingAlgorithm {

    private Network network;
    private ODTable odtable;

    public AllOrNothingAlgorithm(Network network, ODTable odtable) {
        this.network = network;
        this.odtable = odtable;
        network.reset();
    }

    private void run() {
        for (Iterator<OD> it = odtable.getOdTable().iterator(); it.hasNext();) {
            //get od pair
            OD od = it.next();
            Node source = od.getSource();
            Node destination = od.getDestination();
            double demand = od.getDemand();
            io.getOut().print(source.getId() + "\t");
            io.getOut().print(destination.getId() + "\t");
            io.getOut().print(demand + "\t----------");

            Path shortetstPath = network.getShortestPath(source, destination);
            if (shortetstPath == null) {
                //do nothing
            } else {
                for (Link link : shortetstPath.getLinks()) {
                    io.getOut().print("\t" + link.getId());
                }
                io.getOut().print("\n");
                for (Link link : shortetstPath.getLinks()) {
                    link.setVolume(link.getVolume()+demand);
                }
            }
        }
    }
    private InputOutput io;

    public void setPrinter(InputOutput io) {
        this.io = io;
        run();
    }
}
