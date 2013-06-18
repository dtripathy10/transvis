package edu.iitb.tse.algo.core.tripgeneration;


public class Correlation {

	private  int nRows;
	private  int nSelectedColumn;
	private int[] select;
	private double[] SumC,mean,stdDeviation,deltaRoot;
	private double[][] data1,delta,deltaPro,sump;
	private double[][] corelate;

	public Correlation(double [][] data,int[] selection)
	{

		nRows = data.length;
		nSelectedColumn = selection.length;

		data1 = new double[nRows-1][nSelectedColumn];
		delta = new double[nRows-1][nSelectedColumn];
		deltaPro = new double[nRows-1][nSelectedColumn];
		sump = new double[nRows-1][nSelectedColumn];
		corelate = new double[nRows-1][nSelectedColumn];
		SumC = new double[nRows-1];
		mean = new double[nRows-1];
		stdDeviation = new double[nRows-1];
		deltaRoot = new double[nRows-1];
		select = new int[nSelectedColumn];

		int c=0;
		for(int i=nSelectedColumn-1;i>=0;i--)
		{
			select[c]=selection[i];
			c++;
		}



    	for (int j = 0; j <nSelectedColumn; j++)
        {
        	SumC[j]=0;
        	mean[j]=0;
        	for (int i = 0; i < nRows-1; i++)
        	{

        		data1[i][j]=Double.valueOf(data[i+1][selection[j]]);
        		//System.out.println(data1[i][j]);
        		SumC[j]+=data1[i][j];
        	}
    		//System.out.println(SumC[j]);
        	mean[j] = SumC[j] / (nRows-1);
        	//System.out.println(mean[j]);
     	}


     	for (int j = 0; j < nSelectedColumn; j++)
     	{
     		for (int i = 0; i < nRows-1; i++)
        	{
            	delta[i][j] =  data1[i][j] - mean[j];
        	}
     	}

     	for (int j = 0; j < nSelectedColumn; j++)
     	{
        	stdDeviation[j] = 0;
        	for (int i = 0; i < nRows-1; i++)
        	{
            	deltaPro[i][j] =  delta[i][j] * delta[i][j];
            	stdDeviation[j] += deltaPro[i][j];
    		}
        	deltaRoot[j] = (double) Math.sqrt(stdDeviation[j]);
    	}

     	for (int j = 0; j < nSelectedColumn; j++)
     	{
         	for (int i = 0; i < nSelectedColumn; i++)
         	{
        		sump[j][i]=0;
            	for (int k = 0; k < nRows-1; k++)
            	{
        			sump[j][i]+=delta[k][j]*delta[k][i];
            	}
	        }
    	}

     	for (int j = 0; j < nSelectedColumn; j++)
     	{
         	for (int i = 0; i < nSelectedColumn; i++)
         	{
        		if(deltaRoot[j] != 0)
        		{
             		corelate[i][j] =  sump[j][i]/(deltaRoot[j]*deltaRoot[i]);
             		//System.out.println(corelate[i][j]);
        		}
    		}
    	}

	}
}
