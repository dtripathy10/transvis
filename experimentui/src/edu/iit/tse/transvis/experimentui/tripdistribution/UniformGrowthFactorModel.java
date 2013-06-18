/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui.tripdistribution;

import edu.iit.tse.transvis.experimentui.ProjectInformation;
import edu.iit.tse.transvis.experimentui.io.IO;
import edu.iitb.civil.tse.csv.table.model.Reading;
import edu.iitb.tse.algo.core.tripdistribution.growthfactor.UniformGrowthFactor;
import edu.iitb.tse.transvis.csvfile.OpenInterface;
import edu.iitb.tse.transvis.csvfile.OpenSupport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
id = "edu.iit.tse.transvis.experimentui.tripdistribution.GrowthFactorModel")
@ActionRegistration(
    displayName = "#CTL_GrowthFactorModel")
@ActionReference(path = "Menu/Experiment/TripDistribution", position = 3333)
@Messages("CTL_GrowthFactorModel=Uniform Growth Factor Model")
public final class UniformGrowthFactorModel implements ActionListener {

    String type;
    Integer NumberOfHeader;
    String[] headerType;
    String[] headerName;
    List<String[]> table;
    BufferedReader bufRdr = null;
    String line = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        table = new ArrayList();
        InputOutput io = IO.getInstance().getInputOutput();
        io.getErr().println("Uniform growth factor model is startd");
        io.getErr().println("----------------------------------------------");
        io.select();
        UniformGrowthFactorDialog d = new UniformGrowthFactorDialog(WindowManager.getDefault().getMainWindow(), true);
        d.setTitle("Uniform Growth Factor | Tripa Distribution");
        d.setLocationRelativeTo(null);
        d.setVisible(true);

        //get the data
        final FileObject baseFile = d.getNetworkFile();
        double growthFactor = d.getGrowthFactor();

        int[][] baseYear = null;
        //read the file
        try {
            bufRdr = new BufferedReader(new FileReader(FileUtil.toFile(baseFile)));
            while ((line = bufRdr.readLine()) != null) {
                //Check the header informataion.
                if (line.trim().startsWith("#")) {
                    String[] parts = line.trim().split("=");
                    String beforeEqual = parts[0].trim();
                    String afterEqual = parts[1].trim();
                    if (beforeEqual.equals("#type")) {
                        type = afterEqual;
                    }
                    if (beforeEqual.equals("#header")) {

                    }
                    if (beforeEqual.equals("#header_type")) {
                        headerType = afterEqual.trim().split(",");
                    }
                    if (beforeEqual.equals("#header_name")) {
                        headerName = afterEqual.trim().split(",");
                    }
                } else {

                    if (type.equals("matrix")) {
                        //read the all the data and store it into a array
                        readMatrix();
                        break;
                    }
                }
            }
            //close the file
            bufRdr.close();
        } catch (IOException | NumberFormatException ex) {
        }
        baseYear = new int[table.size()][table.size()];
        for (int i = 0; i < baseYear.length; i++) {
            int[] ds = baseYear[i];
            String[] st = table.get(i);
            for (int j = 0; j < ds.length && j < baseYear.length; j++) {
                baseYear[i][j] = new Integer(st[j].trim());
            }
        }

        //perform experiment
        UniformGrowthFactor uniformGrowthFactor = new UniformGrowthFactor(baseYear, growthFactor);
        //get data after exeriment
        int[][] basematrix = uniformGrowthFactor.getBasematrix();
        int[][] horizonatrix = uniformGrowthFactor.getHorizonatrix();
        double growthFacotr = uniformGrowthFactor.getGrowthFacotr();
        int[] baseYearrowSum = uniformGrowthFactor.getBaseYearrowSum();
        int[] baseYearcolumnSum = uniformGrowthFactor.getBaseYearcolumnSum();
        int[] horizonYearrowSum = uniformGrowthFactor.getHorizonYearrowSum();
        int[] horizonYearcolumnSum = uniformGrowthFactor.getHorizonYearcolumnSum();
        //print result
        io.getOut().println("Growth Factor:\t" + growthFacotr);
        io.getOut().println("Base year");
        for (int i = 0; i < basematrix.length; i++) {
            int[] f = basematrix[i];
            for (int j = 0; j < f.length; j++) {
                int g = f[j];
                io.getOut().print(g + "\t");
            }
            io.getOut().print(baseYearrowSum[i] + "\t");
            io.getOut().println();
        }
        for (int i = 0; i < baseYearcolumnSum.length; i++) {
            int f = baseYearcolumnSum[i];
            io.getOut().print(f + "\t");
        }
        io.getOut().println("\n--------------------------");
        io.getOut().println("Horizon year");
        for (int i = 0; i < horizonatrix.length; i++) {
            int[] f = horizonatrix[i];
            for (int j = 0; j < f.length; j++) {
                int g = f[j];
                io.getOut().print(g + "\t");
            }
            io.getOut().print(horizonYearrowSum[i] + "\t");
            io.getOut().println();
        }
        for (int i = 0; i < horizonYearcolumnSum.length; i++) {
            int f = horizonYearcolumnSum[i];
            io.getOut().print(f + "\t");
        }
        io.getOut().println("\n---------Completed-----------------");
        io.getOut().close();
        io.getErr().close();
        FileObject file, obj = null;
        try {
            //writiing to a file
            file = ProjectInformation.getCurrentProject().getProjectDirectory();
            obj = FileUtil.createData(file.getFileObject("input"), baseFile.getName() + "_basegf.csv");

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
        pw.println("#type=matrix");

        pw.println("#header=" + horizonYearcolumnSum.length);
        String str = "";
        int iii = 0;
        for (iii = 0; iii < horizonYearcolumnSum.length; iii++) {
            str = str + iii + ",";
        }
        str = str + "sum";
        pw.println("#header_name=" + str);
        for (int i = 0; i < basematrix.length; i++) {
            int[] f = basematrix[i];
            for (int j = 0; j < f.length; j++) {
                int g = f[j];
                pw.print(g + ",");
            }
            pw.print(baseYearrowSum[i]);
            pw.println();
        }
        for (int i = 0; i < baseYearcolumnSum.length; i++) {
            int f = baseYearcolumnSum[i];
            pw.print(f + ",");
        }
        pw.println(uniformGrowthFactor.getBaseYearrow()+"/"+uniformGrowthFactor.getBaseYearcolumn());
        pw.close();
        /////////////////
        try {
            //writiing to a file
            file = ProjectInformation.getCurrentProject().getProjectDirectory();
            obj = FileUtil.createData(file.getFileObject("input"), baseFile.getName() + "_horizongf.csv");

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        pw = null;
        try {
            pw = new PrintWriter(obj.getOutputStream());
        } catch (FileAlreadyLockedException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        pw.println("#type=matrix");

        pw.println("#header=" + horizonYearcolumnSum.length);
        str = "";
        for (iii = 0; iii < horizonYearcolumnSum.length; iii++) {
            str = str + iii + ",";
        }
        str = str + "sum";
        pw.println("#header_name=" + str);
        for (int i = 0; i < horizonatrix.length; i++) {
            int[] f = horizonatrix[i];
            for (int j = 0; j < f.length; j++) {
                int g = f[j];
                pw.print(g + ",");
            }
            pw.print(horizonYearrowSum[i]);
            pw.println();
        }
        for (int i = 0; i < horizonYearcolumnSum.length; i++) {
            int f = horizonYearcolumnSum[i];
            pw.print(f + ",");
        }
       pw.println(uniformGrowthFactor.getHorizonYearrow()+"/"+uniformGrowthFactor.getHorizonYearcolumn());
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
                    DataObject dob = DataObject.find(ProjectInformation.getCurrentProject().getProjectDirectory().getFileObject("input").getFileObject(baseFile.getName() + "_basegf.csv"));
                    OpenSupport oc = (OpenSupport) dob.getLookup().lookup(OpenInterface.class);
                    if (oc != null) {
                        oc.open1();
                    }
                    DataObject dob1 = DataObject.find(ProjectInformation.getCurrentProject().getProjectDirectory().getFileObject("input").getFileObject(baseFile.getName() + "_horizongf.csv"));
                     OpenSupport oc1 = (OpenSupport) dob1.getLookup().lookup(OpenInterface.class);
                    if (oc1 != null) {
                        oc1.open1();
                    }
                } catch (DataObjectNotFoundException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        });
    }

    private void readMatrix() {
        try {
            String[] parts = line.trim().split(",");
            table.add(parts);
            while ((line = bufRdr.readLine()) != null) {
                parts = line.trim().split(",");
                table.add(parts);
            }
        } catch (IOException ex) {
            Logger.getLogger(Reading.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
