/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui.tripassignment;

import au.com.bytecode.opencsv.CSVReader;
import edu.iit.tse.transvis.experimentui.ProjectInformation;
import edu.iit.tse.transvis.experimentui.io.IO;
import edu.iitb.tse.algo.core.UserEqulibriumAssignment;
import edu.iitb.tse.algo.model.Link;
import edu.iitb.tse.algo.model.Network;
import edu.iitb.tse.algo.model.Node;
import edu.iitb.tse.algo.model.OD;
import edu.iitb.tse.algo.model.ODTable;
import edu.iitb.tse.transvis.csvfile.OpenInterface;
import edu.iitb.tse.transvis.csvfile.OpenSupport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileAlreadyLockedException;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.InputOutput;
import org.openide.windows.WindowManager;

@ActionID(
    category = "Edit",
id = "edu.iit.tse.transvis.experimentui.UserEquilibrium")
@ActionRegistration(
   // iconBase = "edu/iit/tse/transvis/experimentui/usereuilibrium.png",
displayName = "#CTL_UserEquilibrium")
@ActionReference(path = "Menu/Experiment/TripAssignment", position = 3233)
@Messages("CTL_UserEquilibrium=User Equilibrium")
public final class UserEquilibrium implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
    //
//        //preprocess the input
//        /*Checking the file format and then map the file format to get a desired input
//         required the algorithm*/
        InputOutput io = IO.getInstance().getInputOutput();
        io.getErr().println("User Equilibrium experiment is startd");
        io.getErr().println("----------------------------------------------");
        io.select();
//
//        //create the network
        Network network = new Network();

        //create the od table
        ODTable odTable = new ODTable();
        UserEquilibriumDialog d = new UserEquilibriumDialog(WindowManager.getDefault().getMainWindow(), true);
        d.setTitle("User Equilibrium | Tripa Assignment");
        d.setLocationRelativeTo(null);
        d.setVisible(true);
        final FileObject networkFile = d.getNetworkFile();
        FileObject odFiel = d.getOdFile();

        //reading data from csv file
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(FileUtil.toFile(networkFile)));
            String[] row = null;

            while ((row = csvReader.readNext()) != null) {
                //do nothing for comment line (csv)
                if (row[0].startsWith("#")) {
                } else {
                    Node node1 = new Node(row[0]);
                    Node node2 = new Node(row[1]);
                    network.addNode(node1);
                    network.addNode(node2);
                    double freeFlowTravelTime = new Double(row[2]);
                    double capacity = new Double(row[3]);
                    Link link = new Link("" + network.getLinks().size() + "", node1, node2, freeFlowTravelTime,capacity);
                    network.addLink(link);
                }
            }
            csvReader.close();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        //reading data from csv file
        csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(FileUtil.toFile(odFiel)));
            String[] row = null;

            while ((row = csvReader.readNext()) != null) {
                //do nothing for comment line (csv)
                if (row[0].startsWith("#")) {
                } else {
                    Node node1 = network.getNode(row[0]);
                    Node node2 = network.getNode(row[1]);
                    double demand = new Double(row[2]);
                    odTable.addOD(new OD(node1, node2, demand));
                }
            }
            csvReader.close();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        //perform all or nothing assignment
        UserEqulibriumAssignment aon = new UserEqulibriumAssignment(network, odTable);
        aon.setConvergenceCriteria(d.getConvergence());
        aon.setALPHA(d.getAlpha());
        aon.setBETA(d.getBeta());
        aon.setMAX_ITERATIONS(d.getNumberOfIteration());
        aon.setPrinter(io);
        io.getOut().print("----------------Completed-------------------------------\n");

        //
        //io.getErr().println( ProjectInformation.getCurrentProject().getProjectDirectory().getName());
        io.getOut().close();
        io.getErr().close();
        FileObject file, obj = null;
        try {
            //writiing to a file
            file = ProjectInformation.getCurrentProject().getProjectDirectory();
            obj = FileUtil.createData(file.getFileObject("input"), networkFile.getName()+"_userequilibrium.csv");

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(obj.getOutputStream());
        } catch (FileAlreadyLockedException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        pw.println("#type=table");
        pw.println("#header=6");
        pw.println("#header_type=int,int,int,int,int,int");
        pw.println("#header_name=Link ID,Source Node ID,Destnation Node ID,Volume,Capacity,travel time");
        for (Link link : network.getLinks()) {
            pw.println(link.getId() + "," + link.getSource().getId() + "," + link.getDestination().getId() + "," + link.getVolume()+ "," + link.getCapacity()+ "," + link.getFreeFlowTravelTime());
        }
        pw.close();
        // some initialization method
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                try {
                    // code to be invoked when system UI is ready
                    //                TopComponent vtp = new TopComponent();
                    //                vtp.open();
                    //                vtp.requestActive();
                    DataObject dob = DataObject.find(ProjectInformation.getCurrentProject().getProjectDirectory().getFileObject("input").getFileObject(networkFile.getName()+"_userequilibrium.csv"));
                   OpenSupport oc = (OpenSupport) dob.getLookup().lookup(OpenInterface.class);
                    if (oc != null) {
                        oc.open1();
                    }
                } catch (DataObjectNotFoundException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        });
    }
}
