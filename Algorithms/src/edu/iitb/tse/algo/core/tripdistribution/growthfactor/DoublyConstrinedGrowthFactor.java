package edu.iitb.tse.algo.core.tripdistribution.growthfactor;

/**
 *
 * @author Debabrata Tripathy Email : dtripathy10@gmail.com Web :
 * http://home.iitb.ac.in/~debabratatripathy/
 */
public class DoublyConstrinedGrowthFactor {

    private int[][] basematrix;
    private int[][] horizonatrix;
    private int[] baseYearrowSum;
    private int[] baseYearcolumnSum;
    private int[] horizonYearrowSum;
    private int[] horizonYearcolumnSum;
    private int baseYearrow, baseYearcolumn, horizonYearrow, horizonYearcolumn;
    private AccuracyLevel accuracyLevel;
    private double percentageofAccuracyLevel;
    private int MaximumNumberOfIteration;
    private int[] horizonoriginmatrix;
    private int[] horizondestinationmatrix;
    private double[] factor;

    public DoublyConstrinedGrowthFactor(int[][] basematrix, AccuracyLevel accuracyLevel, double percentageofAccuracyLevel, int[] horizonoriginmatrix, int[] horizondestinationmatrix) {
        this.basematrix = basematrix;
        this.accuracyLevel = accuracyLevel;
        this.percentageofAccuracyLevel = percentageofAccuracyLevel;
        this.horizonoriginmatrix = horizonoriginmatrix;
        this.horizondestinationmatrix = horizondestinationmatrix;
        process();
    }

    private int calculateSum(int[] list) {
        int sum = 0;
        for (int i = 0; i < list.length; i++) {
            int d = list[i];
            sum += d;
        }
        return sum;
    }

    private void process() {
        //temporary h
        double[][] temphorizonMatrix = new double[basematrix.length][basematrix.length];
        //calculating base year rowsum
        for (int i = 0; i < basematrix.length; i++) {
            int sum = 0;
            int[] ds = basematrix[i];
            for (int j = 0; j < ds.length; j++) {
                int d = ds[j];
                sum += d;
            }
            baseYearrowSum[i] = sum;
        }
        baseYearrow = calculateSum(baseYearrowSum);
        //calculating baseYear column sum
        for (int i = 0; i < basematrix.length; i++) {
            int[] ds = basematrix[i];
            for (int j = 0; j < ds.length; j++) {
                int d = ds[j];
                baseYearcolumnSum[j] += d;
            }
        }
        baseYearcolumn = calculateSum(baseYearcolumnSum);
        //caculating multiplication factor for horizon origin
        double[] factorOrigin = new double[horizonoriginmatrix.length];
        for (int i = 0; i < factor.length; i++) {
            factor[i] = (double) ((double) horizonoriginmatrix[i] / (double) baseYearrowSum[i]);
        }
        //caculating multiplication factor for horizon destination
        double[] factorDesination = new double[horizondestinationmatrix.length];
        for (int i = 0; i < factor.length; i++) {
            factor[i] = (double) ((double) horizondestinationmatrix[i] / (double) baseYearcolumnSum[i]);
        }

        //calculating horizon year matrix
        for (int i = 0; i < basematrix.length; i++) {
            int[] ds = basematrix[i];
            for (int j = 0; j < ds.length; j++) {
                int d = ds[j];
                temphorizonMatrix[i][j] = (int) Math.round(d * factor[i]);
            }
        }


        //calculating horizon year rowsum
        for (int i = 0; i < horizonatrix.length; i++) {
            int sum = 0;
            int[] ds = horizonatrix[i];
            for (int j = 0; j < ds.length; j++) {
                double d = ds[j];
                sum += d;
            }
            horizonYearrowSum[i] = sum;
        }
        //calculating horizon column sum
        for (int i = 0; i < horizonatrix.length; i++) {
            int[] ds = horizonatrix[i];
            for (int j = 0; j < ds.length; j++) {
                int d = ds[j];
                horizonYearcolumnSum[j] += d;
            }
        }
        horizonYearrow = calculateSum(horizonYearrowSum);
        horizonYearcolumn = calculateSum(horizonYearcolumnSum);
    }
}
