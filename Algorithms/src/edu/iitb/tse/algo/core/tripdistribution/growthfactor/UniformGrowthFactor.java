package edu.iitb.tse.algo.core.tripdistribution.growthfactor;

/**
 *
 * @author Debabrata Tripathy Email : dtripathy10@gmail.com Web :
 * http://home.iitb.ac.in/~debabratatripathy/
 */
public class UniformGrowthFactor {

    private int[][] basematrix;
    private int[][] horizonatrix;
    private double growthFacotr;
    private int[] baseYearrowSum;
    private int[] baseYearcolumnSum;
    private int[] horizonYearrowSum;
    private int[] horizonYearcolumnSum;
    private int baseYearrow, baseYearcolumn, horizonYearrow, horizonYearcolumn;

    public UniformGrowthFactor(int[][] basematrix, double growthFacotr) {
        this.basematrix = basematrix;
        this.growthFacotr = growthFacotr;
        this.horizonatrix = new int[basematrix.length][basematrix.length];
        this.baseYearcolumnSum = new int[basematrix.length];
        this.baseYearrowSum = new int[basematrix.length];
        this.horizonYearrowSum = new int[basematrix.length];
        this.horizonYearcolumnSum = new int[basematrix.length];
        process();
    }

    private void process() {
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
        //calculating baseYear column sum
        for (int i = 0; i < basematrix.length; i++) {
            int[] ds = basematrix[i];
            for (int j = 0; j < ds.length; j++) {
                int d = ds[j];
                baseYearcolumnSum[j] += d;
            }
        }
        //calculating horizon year matrix
        for (int i = 0; i < basematrix.length; i++) {
            int[] ds = basematrix[i];
            for (int j = 0; j < ds.length; j++) {
                int d = ds[j];
                horizonatrix[i][j] = (int) Math.round(d * growthFacotr);
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
        baseYearrow = calculateSum(baseYearrowSum);
        baseYearcolumn = calculateSum(baseYearcolumnSum);
        horizonYearrow = calculateSum(horizonYearrowSum);
        horizonYearcolumn = calculateSum(horizonYearcolumnSum);
    }

    private int calculateSum(int[] list) {
        int sum = 0;
        for (int i = 0; i < list.length; i++) {
            int d = list[i];
            sum += d;
        }
        return sum;
    }

    public int[][] getBasematrix() {
        return basematrix;
    }

    public int[][] getHorizonatrix() {
        return horizonatrix;
    }

    public double getGrowthFacotr() {
        return growthFacotr;
    }

    public int[] getBaseYearrowSum() {
        return baseYearrowSum;
    }

    public int[] getBaseYearcolumnSum() {
        return baseYearcolumnSum;
    }

    public int[] getHorizonYearrowSum() {
        return horizonYearrowSum;
    }

    public int[] getHorizonYearcolumnSum() {
        return horizonYearcolumnSum;
    }

    public int getBaseYearrow() {
        return baseYearrow;
    }

    public int getBaseYearcolumn() {
        return baseYearcolumn;
    }

    public int getHorizonYearrow() {
        return horizonYearrow;
    }

    public int getHorizonYearcolumn() {
        return horizonYearcolumn;
    }

}
