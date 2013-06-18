package edu.iitb.tse.algo.core.tripgeneration;

public class DescriptiveStats {

    private int nRows;
    private int nSelectedColumn;
    private int[] select;
    private double [] SumC;
    private double[] mean;
    private double[] max;
    private double[] min;
    private double[] stdDeviation;

    public DescriptiveStats(double [][] data, int[] selection) {
        double[][] data1, delta, deltaPro, sump, reg;
        double[] deltaSum;
        nRows = data.length;
        nSelectedColumn = selection.length;
        data1 = new double[nRows - 1][nSelectedColumn];
        delta = new double[nRows - 1][nSelectedColumn];
        deltaPro = new double[nRows - 1][nSelectedColumn];
        sump = new double[nRows - 1][nSelectedColumn];
        reg = new double[nRows - 1][nSelectedColumn];
        SumC = new double[nRows - 1];
        mean = new double[nRows - 1];
        max = new double[nRows - 1];
        min = new double[nRows - 1];
        deltaSum = new double[nSelectedColumn];
        stdDeviation = new double[nSelectedColumn];
        select = new int[nSelectedColumn];
        int c = 0;
        for (int i = nSelectedColumn - 1;
                i >= 0; i--) {
            select[c] = selection[i];
            c++;
        }
        for (int j = 0;
                j < nSelectedColumn;
                j++) {
            SumC[j] = 0;
            mean[j] = 0;
            for (int i = 0; i < nRows - 1; i++) {

                data1[i][j] = Double.valueOf(data[i + 1][selection[j]]);
                //System.out.println(data1[i][j]);
                SumC[j] += data1[i][j];
            }
            //System.out.println(SumC[j]);
            mean[j] = SumC[j] / (nRows - 1);
            //System.out.println(mean[j]);
        }
        for (int j = 0;
                j < nSelectedColumn;
                j++) {
            max[j] = 0;
            min[j] = data1[1][1];
            for (int i = 0; i < nRows - 1; i++) {
                if (data1[i][j] > max[j]) {
                    max[j] = data1[i][j];
                } else if (data1[i][j] < min[j]) {
                    min[j] = data1[i][j];
                }

            }
        }
        for (int j = 0;
                j < nSelectedColumn;
                j++) {
            for (int i = 0; i < nRows - 1; i++) {
                delta[i][j] = data1[i][j] - mean[j];
            }

        }
        for (int j = 0;
                j < nSelectedColumn;
                j++) {
            deltaSum[j] = 0;
            for (int i = 0; i < nRows - 1; i++) {
                deltaPro[i][j] = delta[i][j] * delta[i][j];
                deltaSum[j] += deltaPro[i][j];
            }
            stdDeviation[j] = (double) Math.sqrt((deltaSum[j]) / (nRows - 2));
        }
        for (int i = 0;
                i < nSelectedColumn;
                i++) {
            System.out.println((nRows - 1) + "    " + min[i] + "  " + max[i] + "   " + mean[i] + "   " + stdDeviation[i]);
        }
    }

    public int getnRows() {
        return nRows;
    }

    public int getnSelectedColumn() {
        return nSelectedColumn;
    }

    public int[] getSelect() {
        return select;
    }

    public double[] getSumC() {
        return SumC;
    }

    public double[] getMean() {
        return mean;
    }

    public double[] getMax() {
        return max;
    }

    public double[] getMin() {
        return min;
    }

    public double[] getStdDeviation() {
        return stdDeviation;
    }

}
