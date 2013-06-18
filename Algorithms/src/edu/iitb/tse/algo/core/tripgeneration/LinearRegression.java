package edu.iitb.tse.algo.core.tripgeneration;

import java.awt.Component;
import javax.swing.JOptionPane;

public class LinearRegression {

    private int nRows;
    private int nIndependentColumns;
    private int[] independentselect;
    private double[] estimate, output, residuals, stdResiduals, standardErrorOfEstimate, tStatistics;
    private double standardError, rsquare;

    public LinearRegression(double[][] data, int Dependentselection, int[] Independentselection) {
        double[] multiXTY;
        double[][] X, Y, X_t, multi, b;
        nRows = data.length;
        nIndependentColumns = Independentselection.length;
        independentselect = new int[nIndependentColumns];
        int c = 0;
        for (int i = nIndependentColumns - 1; i >= 0; i--) {
            independentselect[c] = Independentselection[i];

            c++;
        }
        System.out.println("Dependent :" + Dependentselection);
        Y = new double[nRows - 1][nIndependentColumns];
        for (int i = 0; i < nRows - 1; i++) {
            Y[i][0] = Double.valueOf(data[i + 1][Dependentselection]);
        }

        X = new double[nRows - 1][nIndependentColumns + 1];
        for (int i = 0; i < nRows - 1; i++) {
            X[i][0] = 1;

        }

        for (int j = 0; j < nIndependentColumns; j++) {
            for (int i = 0; i < nRows - 1; i++) {

                X[i][j + 1] = Double.valueOf(data[i + 1][Independentselection[j]]);

            }

        }

        X_t = new double[nIndependentColumns + 1][nRows - 1];
        for (int j = 0; j < nIndependentColumns + 1; j++) {
            for (int i = 0; i < nRows - 1; i++) {
                X_t[j][i] = X[i][j];
                //System.out.println(X_t[j][i]);
            }
        }


        multi = new double[nRows - 1][nIndependentColumns + 1];
        for (int i = 0; i < nIndependentColumns + 1; i++) {
            for (int j = 0; j < nRows - 1; j++) {
                for (int k = 0; k < nIndependentColumns + 1; k++) {
                    double s = 0;
                    s = X_t[i][j] * X[j][k];
                    multi[i][k] = multi[i][k] + s;

                }
            }
        }


        multiXTY = new double[nIndependentColumns + 1];
        for (int i = 0; i < nIndependentColumns + 1; i++) {
            for (int j = 0; j < nRows - 1; j++) {
                double r = 0;
                r = X_t[i][j] * Y[j][0];
                multiXTY[i] = multiXTY[i] + r;

            }

        }
        b = new double[nIndependentColumns + 1][nIndependentColumns + 1];
        for (int i = 0; i < nIndependentColumns + 1; i++) {
            for (int j = 0; j < nIndependentColumns + 1; j++) {
                if (i == j) {
                    b[i][j] = 1;
                } else {
                    b[i][j] = 0;
                }
            }
        }


        for (int k = 0; k < nIndependentColumns + 1; k++) {
            for (int i = k + 1; i < nIndependentColumns + 1; i++) {
                double q = multi[i][k];
                for (int j = 0; j < nIndependentColumns + 1; j++) {
                    multi[i][j] = multi[i][j] - (multi[k][j] * q / multi[k][k]);
                    b[i][j] = b[i][j] - (b[k][j] * q / multi[k][k]);
                }
            }
        }


        //checking for determinant to be 0

        for (int i = 0; i < nIndependentColumns + 1; i++) {
            if (b[i][i] == 0) {
                JOptionPane.showMessageDialog((Component) null, "Non Singular Matrix");
            }
        }
        for (int k = nIndependentColumns; k >= 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                double q = multi[i][k];
                for (int j = nIndependentColumns; j >= 0; j--) {
                    multi[i][j] = multi[i][j] - (multi[k][j] * q / multi[k][k]);
                    b[i][j] = b[i][j] - (b[k][j] * q / multi[k][k]);

                }

            }
        }



        for (int i = 0; i < nIndependentColumns + 1; i++) {
            double q = multi[i][i];
            for (int k = 0; k < nIndependentColumns + 1; k++) {
                b[i][k] = (b[i][k] / q);
                multi[i][k] = (multi[i][k] / q);

            }
        }



        //matrix b is xtx-1
        estimate = new double[nIndependentColumns + 1];
        for (int i = 0; i < nIndependentColumns + 1; i++) {
            for (int j = 0; j < nIndependentColumns + 1; j++) {
                double t = 0;
                t = b[i][j] * multiXTY[j];
                estimate[i] = estimate[i] + t;

            }

        }



        //ESTIMATED VALUE OF OUTPUTS
        output = new double[nRows - 1];
        for (int i = 0; i < nRows - 1; i++) {
            for (int j = 0; j < nIndependentColumns + 1; j++) {
                double t = 0;
                t = X[i][j] * estimate[j];
                output[i] = output[i] + t;
            }

        }


        // standard variables
        residuals = new double[nRows - 1];
        for (int i = 0; i < nRows - 1; i++) {
            residuals[i] = Y[i][0] - output[i];
        }




        // standard variables
        double deltaSum = 0;
        for (int i = 0; i < nRows - 1; i++) {
            deltaSum += residuals[i] * residuals[i];
        }

        double deltaSum1 = deltaSum / ((nRows) - (nIndependentColumns + 1));
        standardError = Math.sqrt(deltaSum1);



        stdResiduals = new double[nRows - 1];
        for (int j = 0; j < nRows - 1; j++) {
            stdResiduals[j] = residuals[j] / standardError;
        }





        // for r square value
        double SumC = 0;
        for (int i = 0; i < nRows - 1; i++) {
            SumC += Y[i][0];

        }
        double avg = SumC / (nRows - 1);

        double deltaSum2 = 0;
        //

        double deltaPro1 = 0;
        for (int j = 0; j < nRows - 1; j++) {
            deltaPro1 = (avg - Y[j][0]) * (avg - Y[j][0]);
            deltaSum2 += deltaPro1;
        }


        double v = deltaSum / deltaSum2;
        rsquare = 1 - v;


        standardErrorOfEstimate = new double[nIndependentColumns + 1];
        for (int i = 0; i < nIndependentColumns + 1; i++) {
            standardErrorOfEstimate[i] = Math.sqrt(deltaSum1 * b[i][i]);
        }

        tStatistics = new double[nIndependentColumns + 1];
        for (int i = 0; i < nIndependentColumns + 1; i++) {
            tStatistics[i] = estimate[i] / standardErrorOfEstimate[i];
        }
    }

    public int getnRows() {
        return nRows;
    }

    public int getnIndependentColumns() {
        return nIndependentColumns;
    }

    public int[] getIndependentselect() {
        return independentselect;
    }

    public double[] getEstimate() {
        return estimate;
    }

    public double[] getOutput() {
        return output;
    }

    public double[] getResiduals() {
        return residuals;
    }

    public double[] getStdResiduals() {
        return stdResiduals;
    }

    public double[] getStandardErrorOfEstimate() {
        return standardErrorOfEstimate;
    }

    public double[] gettStatistics() {
        return tStatistics;
    }

    public double getStandardError() {
        return standardError;
    }

    public double getRsquare() {
        return rsquare;
    }

}
