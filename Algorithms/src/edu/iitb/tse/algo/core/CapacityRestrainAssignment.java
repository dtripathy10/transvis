package edu.iitb.tse.algo.core;

import edu.iitb.tse.algo.model.CONVERGENCECRITERIA;
import edu.iitb.tse.algo.model.Link;
import edu.iitb.tse.algo.model.Network;
import edu.iitb.tse.algo.model.Node;
import edu.iitb.tse.algo.model.OD;
import edu.iitb.tse.algo.model.ODTable;
import edu.iitb.tse.algo.model.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.openide.windows.InputOutput;

/**
 *
 * @author dtripathy10
 */
public class CapacityRestrainAssignment {

    private Network network;
    private ODTable odtable;
    private CONVERGENCECRITERIA convergenceCriteria = CONVERGENCECRITERIA.TRAVELTIME;
    private int BETA = 4;
    private double ALPHA = 0.15;
    private double EPSLON;
    private final double CRITERION = 0.0001;		// Convergence criterion: 1% change in flows (root mean square)
    private final int MAX_ITERATIONS = 1000;

    public CapacityRestrainAssignment(Network network, ODTable odtable) {
        this.network = network;
        this.odtable = odtable;
        EPSLON = calculateMachineEpsilonFloat();
        run();
    }

    private void run() {
        int odNumber = 0;
        for (Iterator<OD> it = odtable.getOdTable().iterator(); it.hasNext();) {
            odNumber++;
            //step -0
            //initialization
            double alpha;
            OD od = it.next();
            Node source = od.getSource(), destination = od.getDestination();
            double demand = od.getDemand();
            printOD(odNumber, od);
            double travelTime = 0;
            double newTravelTime = 0;
            Map<Link, Double> xVector, yVector, baseTime;
            double convergence;
            xVector = new HashMap();
            baseTime = new HashMap();
            for (Link link : network.getLinks()) {
                baseTime.put(link, link.getFreeFlowTravelTime());
            }
            //intialize x vector
            for (Link link : network.getLinks()) {
                xVector.put(link, link.getVolume());
            }
            printTime(xVector, baseTime);
            int numberOfItration = 0;
            //direction finding
            Path shortetstPath = network.getShortestPath(source, destination);
            if (shortetstPath == null) {
                //do nothing
            } else {
                for (Link link : shortetstPath.getLinks()) {
                    xVector.put(link, link.getVolume() + demand);
                    travelTime = link.getFreeFlowTravelTime() + travelTime;
                    newTravelTime = link.getFreeFlowTravelTime() + newTravelTime;
                }
            }
            printMove(xVector);
            do {
                numberOfItration++;
                io.getOut().println("-------------------" + numberOfItration + "-Iteration---------------------");
                //step -1
                //updation
                for (Link link : network.getLinks()) {
                    double updateTravelTime = time(link, xVector, baseTime);
                    link.setFreeFlowTravelTime(updateTravelTime);
                }
                printTime(xVector, baseTime);
                //step-2
                //Direction finding
                //intialize y vector
                yVector = new HashMap();
                for (Link link : network.getLinks()) {
                    yVector.put(link, link.getVolume());
                }
                shortetstPath = network.getShortestPath(source, destination);
                if (shortetstPath == null) {
                    //do nothing
                } else {
                    for (Link link : shortetstPath.getLinks()) {
                        yVector.put(link, link.getVolume() + demand);
                        newTravelTime = link.getFreeFlowTravelTime() + newTravelTime;
                    }
                }

                //step-3
                //Line search
                alpha = lineSearch(xVector, yVector, 0, 1, baseTime);
                printDirection(yVector, alpha);
                //step-4
                //Move
                //check = checkForConvergence(alpha, travelTime, newTravelTime, xVector, xNewVector);
                convergence = move(alpha, xVector, yVector);
                for (Link link : network.getLinks()) {
                    travelTime = newTravelTime;
                    newTravelTime = 0;
                }
                //final solution
                if (!((convergence > CRITERION && numberOfItration < MAX_ITERATIONS))) {
                    for (Link link : network.getLinks()) {
                        link.setVolume(xVector.get(link));
                    }
                }
                printMove(xVector);
            } while (convergence > CRITERION && numberOfItration < MAX_ITERATIONS);
            ///////////////////////////////////////////////////////////////////////////////////
            for (Link link : network.getLinks()) {
                io.getOut().println("Link ID :\t" + link.getId() + "\tLink Volume\t" + link.getVolume() + "\tLink TT\t" + link.getFreeFlowTravelTime());
            }
        }

    }

    private static double calculateMachineEpsilonFloat() {
        double machEps = 1.0;
        do {
            machEps /= 2.0f;
        } while ((1.0 + (machEps / 2.0)) != 1.0);
        return machEps;
    }

    public double derivative(Map<Link, Double> xVector, Map<Link, Double> yVector, double alpha, Map<Link, Double> baseTime) {
        final double TOLERANCE = EPSLON;		// Square root of machine precision
        double deriv = 0.0;
        for (Link link : network.getLinks()) {
            double time;
            double xtime = baseTime.get(link);
            double newFlow = xVector.get(link) + alpha * (yVector.get(link) - xVector.get(link));
            if (newFlow < TOLERANCE) {
                time = baseTime.get(link);
            } else {
                double delay = 1.0 + (ALPHA / Math.pow(link.getCapacity(), BETA)) * Math.pow(newFlow, BETA);
                time = xtime * delay;
            }
            deriv += (yVector.get(link) - xVector.get(link)) * time;
        }
        return deriv;
    }

    private double lineSearch(Map<Link, Double> xVector, Map<Link, Double> yVector, double a, double b, Map<Link, Double> base) {
        final double TOLERANCE = EPSLON;		// Square root of machine precision
        double m;							// Midpoint
        int counter = 0;
        for (m = (a + b) / 2.0; Math.abs(a - b) > TOLERANCE; m = (a + b) / 2.0) {
            counter++;
            if (derivative(xVector, yVector, m, base) < 0.0) {
                a = m;
            } // Use right subinterval
            else {
                b = m;
            }       // Use left subinterval
            if (counter > MAX_ITERATIONS) {
                break;
            }
        }
        return m;
    }

    private void printOD(int odNumber, OD od) {
        io.getOut().println("-------------------OD PAIR-" + odNumber + "---------------------");
        io.getOut().print(od.getSource().getId() + "\t");
        io.getOut().print(od.getDestination().getId() + "\t");
        io.getOut().print(od.getDemand() + "\n");
        io.getOut().println("---------------------------------------------------------------");
    }

    private void printTime(Map<Link, Double> xVector, Map<Link, Double> baseTime) {
        io.getOut().print("Update     t: \t");
        for (Link link : network.getLinks()) {
            io.getOut().printf(link.getId() + "%5.1f ", link.getFreeFlowTravelTime());
        }
        // Compute and output objective function value
        // Integral of a+bx^4 is ax+bx^5/5
        // Integral has no economic or physical meaning in this problem
        double obj = 0.0;
        for (int i = 0; i < network.getLinks().size(); i++) {
            Link link = network.getLinks().get(i);
            double xtemp = xVector.get(link);
            double time = baseTime.get(link);
            double x5 = xtemp * xtemp * xtemp * xtemp * xtemp / (BETA + 1);
            obj += time * xtemp + time * (ALPHA / Math.pow(link.getCapacity(), BETA)) * x5;
        }
        io.getOut().printf("%8.2f ", obj);
        io.getOut().println();
    }

    private void printDirection(Map<Link, Double> yVector, double a) {
        io.getOut().print("Direction  y: \t");
        for (int i = 0; i < network.getLinks().size(); i++) {
            Link link = network.getLinks().get(i);
            double xtemp = yVector.get(link);
            io.getOut().printf(link.getId() + "%5.2f ", xtemp);
        }
        io.getOut().printf("               %5.3f", a);
        io.getOut().println();
    }

    private void printMove(Map<Link, Double> xVector) {
        io.getOut().print("Move       x: \t");
        for (int i = 0; i < network.getLinks().size(); i++) {
            Link link = network.getLinks().get(i);
            double xtemp = xVector.get(link);
            io.getOut().printf(link.getId() + "\t%5.2f ", xtemp);
        }
        io.getOut().println("\n");
    }

    private double time(Link link, Map<Link, Double> xVector, Map<Link, Double> baseTime) {
        final double TOLERANCE = EPSLON;		// Square root of machine precision
        double time;
        double xtime = baseTime.get(link);
        double xFlow = xVector.get(link);
        if (xFlow < TOLERANCE) {
            time = baseTime.get(link);
        } else {
            double delay = 1.0 + (ALPHA / Math.pow(link.getCapacity(), BETA)) * Math.pow(xFlow, BETA);
            time = xtime * delay;
        }
        return time;
    }

    private double move(double alpha, Map<Link, Double> xVector, Map<Link, Double> yVector) {
        double sumFlows = 0.0;
        double sumRootMeanDiffFlows = 0.0;
        for (int i = 0; i < network.getLinks().size(); i++) {
            Link link = network.getLinks().get(i);
            double xtemp = xVector.get(link);
            double ytemp = yVector.get(link);
            double flowChange = alpha * (ytemp - xtemp);
            xVector.put(link, xtemp + flowChange);
            sumFlows += xVector.get(link);
            sumRootMeanDiffFlows += flowChange * flowChange;
        }
        // Compute convergence criterion here, since we have current and previous xFlow
        return Math.sqrt(sumRootMeanDiffFlows) / sumFlows;
    }
     private InputOutput io;

    public void setPrinter(InputOutput io) {
        this.io = io;
        run();
    }
}
