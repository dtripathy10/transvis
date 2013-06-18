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
public class UserEqulibriumAssignment {

    private Network network;
    private ODTable odtable;
    private CONVERGENCECRITERIA convergenceCriteria;
    private double BETA;
    private double ALPHA;
    private double EPSLON;
    private  int MAX_ITERATIONS;

    public void setMAX_ITERATIONS(int MAX_ITERATIONS) {
        this.MAX_ITERATIONS = MAX_ITERATIONS;
    }

   

    public UserEqulibriumAssignment(Network network, ODTable odtable) {
        this.network = network;
        this.odtable = odtable;
        EPSLON = calculateMachineEpsilonFloat();
    }

    public void setConvergenceCriteria(CONVERGENCECRITERIA convergenceCriteria) {
        this.convergenceCriteria = convergenceCriteria;
    }

    public void setBETA(double BETA) {
        this.BETA = BETA;
    }

    public void setALPHA(double ALPHA) {
        this.ALPHA = ALPHA;
    }

    private HashMap<Link, Double> loadNetwork() {
        int odNumber = 0;
        HashMap<Link, Double> xVector = new HashMap<Link, Double>();
        for (Link link : network.getLinks()) {
            xVector.put(link, 0.0);
        }
        for (Iterator<OD> it = odtable.getOdTable().iterator(); it.hasNext();) {
            odNumber++;
            OD od = it.next();
            Node source = od.getSource(), destination = od.getDestination();
            double demand = od.getDemand();
            Path shortetstPath = network.getShortestPath(source, destination);
            if (shortetstPath == null) {
                //do nothing
            } else {
                for (Link link : shortetstPath.getLinks()) {
                    xVector.put(link, xVector.get(link) + demand);
                }
            }
        }
        return xVector;
    }
    private void run() {
        //load Network
        Map<Link, Double> xVector, yVector, baseTime;
        double alpha;
        double convergence;
        xVector = loadNetwork();
        baseTime = new HashMap();
        for (Link link : network.getLinks()) {
            baseTime.put(link, link.getFreeFlowTravelTime());
        }
        printTime(xVector, baseTime);
        int numberOfItration = 0;
        do {
            numberOfItration++;
            io.getOut().println("-------------------" + numberOfItration + "-Iteration---------------------");
            //updation
            for (Link link : network.getLinks()) {
                double updateTravelTime = time(link, xVector, baseTime);
                link.setFreeFlowTravelTime(updateTravelTime);
            }
            printTime(xVector, baseTime);
            //Direction finding
            yVector = loadNetwork();
            //Line search
            alpha = lineSearch(xVector, yVector, 0, 1, baseTime);
            printDirection(yVector, alpha);
            //move
            Map<Link, Double> newVector = move(alpha, xVector, yVector);
            //check for convergence
            boolean converge = checkForConvergence(newVector, xVector, alpha);
            if (converge || (numberOfItration >= MAX_ITERATIONS)) {
                for (Link link : network.getLinks()) {
                    link.setVolume(newVector.get(link));
                }
                break;
            }
            printMove(newVector);
            xVector = null;
            xVector = newVector;
        } while (numberOfItration < MAX_ITERATIONS);
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

    private Map<Link, Double> move(double alpha, Map<Link, Double> xVector, Map<Link, Double> yVector) {
        Map<Link, Double> tempVector = new HashMap<Link, Double>();
        for (int i = 0; i < network.getLinks().size(); i++) {
            Link link = network.getLinks().get(i);
            double xtemp = xVector.get(link);
            double ytemp = yVector.get(link);
            double flowChange = alpha * (ytemp - xtemp);
            tempVector.put(link, xtemp + flowChange);
        }
        return tempVector;
    }
    private InputOutput io;

    public void setPrinter(InputOutput io) {
        this.io = io;
        run();
    }

    private boolean checkForConvergence(Map<Link, Double> xnewVector, Map<Link, Double> xVector, double alpha) {
        boolean b = false;
        switch (convergenceCriteria) {
            case ALPHA:
                b = convergenceCriteriaForAlpha(alpha);
                break;
            case TRAVELTIME:
                b = convergenceCriteriaForTravelTime(xnewVector, xVector);
                break;
        }
        return b;
    }

    private boolean convergenceCriteriaForTravelTime(Map<Link, Double> xnewVector, Map<Link, Double> xVector) {
        double sumFlows = 0.0;
        double sumRootMeanDiffFlows = 0.0;
        for (int i = 0; i < network.getLinks().size(); i++) {
            Link link = network.getLinks().get(i);
            double xnew = xnewVector.get(link);
            double xold = xVector.get(link);
            double temp = (xnew - xold) * (xnew - xold);
            sumRootMeanDiffFlows += temp;
            sumFlows += xold;
        }
        // Compute convergence criterion here, since we have current and previous xFlow
        double result = Math.sqrt(sumRootMeanDiffFlows) / sumFlows;
        if (result < EPSLON) {
            return true;
        }
        return false;
    }

    private boolean convergenceCriteriaForAlpha(double alpha) {
        if (alpha <= EPSLON) {
            return true;
        }
        return false;
    }
}
