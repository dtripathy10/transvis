
package edu.iit.tse.transvis.experimentui;

import edu.iitb.tse.algo.core.UserEqulibriumAssignment;
import edu.iitb.tse.algo.model.Link;
import edu.iitb.tse.algo.model.Network;
import edu.iitb.tse.algo.model.Node;
import edu.iitb.tse.algo.model.OD;
import edu.iitb.tse.algo.model.ODTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

@ActionID(
    category = "Edit",
id = "edu.iit.tse.transvis.experimentui.StochasticUserEquilibrium")
@ActionRegistration(
   // iconBase = "edu/iit/tse/transvis/experimentui/stochastic.png",
displayName = "#CTL_StochasticUserEquilibrium")
@ActionReference(path = "Menu/Experiment/TripAssignment", position = 3133)
@Messages("CTL_StochasticUserEquilibrium=Stochastic User Equilibrium")
public final class StochasticUserEquilibrium implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
        InputOutput io = IOProvider.getDefault().getIO("Hello", true);
        io.getErr().println("All or nothing experiment is startd");
        io.getErr().println("----------------------------------------------");
        io.select();
 //create the network
        Network network = new Network();
        Node node1 = new Node("Debu1");
        Node node2 = new Node("Debu2");
        Node node3 = new Node("Debu3");
        Node node4 = new Node("Debu4");
        network.addNode(node1);
        network.addNode(node2);
        network.addNode(node3);
        network.addNode(node4);
        Link link1 = new Link("Edge1", node1, node2, 20, 400);
        network.addLink(link1);
        Link link2 = new Link("Edge2", node1, node3, 30, 500);
        network.addLink(link2);
        Link link3 = new Link("Edge3", node2, node3, 10, 800);
        network.addLink(link3);
        Link link4 = new Link("Edge4", node2, node4, 40, 500);
        network.addLink(link4);
        Link link5 = new Link("Edge5", node3, node4, 20, 300);
        network.addLink(link5);
        ////////////////////////////////////////////////////////////////////
        //create the od table
        ODTable odTable = new ODTable();

        odTable.addOD(new OD(node1, node2, 100));
        odTable.addOD(new OD(node1, node3, 200));
        odTable.addOD(new OD(node1, node4, 500));
        odTable.addOD(new OD(node2, node3, 300));
        odTable.addOD(new OD(node2, node4, 400));

        //perform all or nothing assignment
        UserEqulibriumAssignment aon = new UserEqulibriumAssignment(network, odTable);
        aon.setPrinter(io);
        io.getOut().print("----------------Completed-------------------------------");
        for (Link link : network.getLinks()) {
            io.getErr().println("Link ID :\t" + link.getId() + "\tLink Volume" + link.getVolume());
        }
        //
        io.getOut().close();
        io.getErr().close();

    }
}
