package edu.iitb.tse.algo.core.tripdistribution.growthfactor;

/**
 *
 * @author Debabrata Tripathy Email : dtripathy10@gmail.com Web :
 * http://home.iitb.ac.in/~debabratatripathy/
 */
public class SinglyConstrinedGrowthFactor {

    private int[][] basematrix;
    private ConstrainedType conType;
    private int[][] horizonatrix;
    private int[] baseYearrowSum;
    private int[] baseYearcolumnSum;
    private int[] horizonYearrowSum;
    private int[] horizonYearcolumnSum;
    private int[] horizonarray;
    private double[] factor;
    private int baseYearrow, baseYearcolumn, horizonYearrow, horizonYearcolumn, horizonarraySum;

    public SinglyConstrinedGrowthFactor(int[][] basematrix, int[] horizonarray, ConstrainedType conType) {
        this.basematrix = basematrix;
        this.conType = conType;
        this.horizonatrix = new int[basematrix.length][basematrix.length];
        this.baseYearcolumnSum = new int[basematrix.length];
        this.baseYearrowSum = new int[basematrix.length];
        this.horizonYearrowSum = new int[basematrix.length];
        this.horizonYearcolumnSum = new int[basematrix.length];
        this.horizonarray = horizonarray;
        switch (conType) {
            case ORIGIN:
                processForOrigin();
                break;
            case DESTINATION:
                processForDestination();
                break;
        }
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

    public ConstrainedType getConType() {
        return conType;
    }

    private void processForOrigin() {
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

        //calculating horizon year sum
        horizonarraySum = calculateSum(horizonarray);
        //caculating multiplication factor
        factor = new double[horizonarray.length];
        for (int i = 0; i < factor.length; i++) {
            factor[i] = (double) ((double)horizonarray[i] / (double)baseYearrowSum[i]);
        }

        //calculating horizon year matrix
        for (int i = 0; i < basematrix.length; i++) {
            int[] ds = basematrix[i];
            for (int j = 0; j < ds.length; j++) {
                int d = ds[j];
                horizonatrix[i][j] = (int) Math.round(d * factor[i]);
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

    private void processForDestination() {
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

        //calculating horizon year sum
        horizonarraySum = calculateSum(horizonarray);
        //caculating multiplication factor
        factor = new double[horizonarray.length];
        for (int i = 0; i < factor.length; i++) {
           factor[i] = (double) ((double)horizonarray[i] / (double)baseYearcolumnSum[i]);
        }

        //calculating horizon year matrix
        for (int i = 0; i < basematrix.length; i++) {
            int[] ds = basematrix[i];
            for (int j = 0; j < ds.length; j++) {
                int d = ds[j];
                horizonatrix[i][j] = (int) Math.round(d * factor[j]);
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

    public int[] getHorizonarray() {
        return horizonarray;
    }

    public double[] getFactor() {
        return factor;
    }

    public int getHorizonarraySum() {
        return horizonarraySum;
    }
}
