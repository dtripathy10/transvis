package edu.iitb.tse.algo.core.tripgeneration;

import java.awt.Component;
import javax.swing.JOptionPane;


public class QuadraticRegression {

	private int nRows;
	private int nIndependentColumns;
	private int[] independentselect;
	private double[] estimate,output,residuals,stdResiduals,standardErrorOfEstimate,tStatistics;
	private double rsquare,standardError;

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

    public double getRsquare() {
        return rsquare;
    }

    public double getStandardError() {
        return standardError;
    }

    
	public QuadraticRegression(double[][] data,int Dependentselection,int[] Independentselection )
	{
        double[][] X, X_t, multi, b, Y;
        double[] multiXTY;
        int p;
		nRows = data.length;
		nIndependentColumns = Independentselection.length;
		independentselect=new int[nIndependentColumns];
		int c=0;
		for(int i=nIndependentColumns-1;i>=0;i--)
		{
			independentselect[c]=Independentselection[i];

			c++;
		}
		System.out.println("Dependent :"+Dependentselection);
		Y = new double[nRows-1][nIndependentColumns];
    	for (int i = 0; i < nRows-1; i++)
    	{
    		Y[i][0]=Double.valueOf(data[i+1][Dependentselection]);
    	}

    	p = nIndependentColumns*2+1;
    	X = new double[nRows-1][p];
    	for (int i = 0; i < nRows-1; i++)
    	{
    		X[i][0]=1;

    	}

    	for (int j = 0; j < nIndependentColumns; j++)
    	{
    	   	for (int i = 0; i < nRows-1; i++)
        	{

            	X[i][j+1] = Double.valueOf( data[i+1][Independentselection[j]]);
            	X[i][nIndependentColumns+j+1] = Math.pow(Double.valueOf( data[i+1][Independentselection[j]]),2);
        	}

    	}

    	X_t= new double[p][nRows-1];
    	for (int j = 0; j < p; j++)
    	{
    	   	for (int i = 0; i < nRows-1; i++)
        	{
    	   		X_t[j][i]=X[i][j];
    	   		//System.out.println(X_t[j][i]);
        	}
    	}


    	multi = new double[nRows-1][p];
    	for (int i = 0; i < p; i++)
    	{
    	   	for (int j = 0; j < nRows-1; j++)
        	{
    	    	for (int k = 0; k < p; k++)
    	    	{
    	    		double s=0;
    	    		s = X_t[i][j]*X[j][k];
    	    		multi[i][k]=multi[i][k]+s;

    	    	}
        	}
    	}


    	multiXTY = new double[p];
    	for (int i = 0; i < p; i++)
    	{
    	   	for (int j = 0; j < nRows-1; j++)
        	{
    	   		double r=0;
    	   		r= X_t[i][j]* Y[j][0];
    	   		multiXTY[i]=multiXTY[i]+r;

        	}

    	}
    	b= new double[p][p];
    	for (int i = 0; i < p; i++)
    	{
    		for (int j= 0; j < p; j++)
        	{
    			if(i==j)
    			{
    				b[i][j]=1;
    			}
    			else
    			{
    				b[i][j]=0;
    			}
        	}
    	}


    	for (int k = 0; k < p; k++)
    	{
    		for (int i= k+1; i < p; i++)
        	{
    			double q = multi[i][k];
    	    	for (int j = 0; j < p; j++)
    	    	{
                    multi[i][j] = multi[i][j] - (multi[k][j] * q / multi[k][k]);
                    b[i][j] = b[i][j] - (b[k][j] * q / multi[k][k]);
    	    	}
        	}
    	}


       //checking for determinant to be 0

    	for (int i = 0; i < p; i++)
    	{
	        if(b[i][i] == 0)
            {
	        		JOptionPane.showMessageDialog((Component)null, "Non Singular Matrix");
            }
       	}
    	for(int k =p-1;k>=0;k--)
    	{
    		for(int i=k-1;i>=0;i--)
    		{
    			double q = multi[i][k];
    			for(int j=p-1;j>=0;j--)
    			{
    				multi[i][j] = multi[i][j] - (multi[k][j] * q / multi[k][k]);
    				b[i][j] = b[i][j] - (b[k][j] * q / multi[k][k]);

    			}

    		}
    	}



    	for (int i = 0; i < p; i++)
    	{
    		double q = multi[i][i];
        	for (int k = 0; k < p; k++)
        	{
                b[i][k] = (b[i][k] / q);
                multi[i][k] = (multi[i][k] / q);

        	}
    	}



	   //matrix b is xtx-1
        estimate = new double[p];
    	for (int i = 0; i < p; i++)
    	{
        	for (int j = 0; j < p; j++)
        	{
        		double t=0;
                t=b[i][j]*multiXTY[j];
                estimate[i]=estimate[i]+t;

        	}

    	}

    	for (int i = 0; i < p; i++)
    	{
    		System.out.println(estimate[i]);
    	}

      //ESTIMATED VALUE OF OUTPUTS
    	output = new double[nRows-1];
	   	for (int i = 0; i < nRows-1; i++)
    	{
        	for (int j = 0; j < p; j++)
        	{
                double t=0;
                t=X[i][j]*estimate[j];
                output[i]=output[i]+t;
        	}

    	}


       // standard variables
	   	residuals = new double[nRows-1];
	   	for (int i = 0; i < nRows-1; i++)
    	{
	   		residuals[i]= Y[i][0]-output[i];
    	}




      	// standard variables
      	double deltaSum = 0;
	   	for (int i = 0; i < nRows-1; i++)
    	{
            deltaSum += residuals[i]*residuals[i] ;
        }

        double deltaSum1=deltaSum/((nRows)-(p));
        standardError = Math.sqrt(deltaSum1);



        stdResiduals = new double[nRows-1];
	   	for (int j = 0; j < nRows-1; j++)
    	{
        	stdResiduals[j]=residuals[j]/standardError;
        }





       // for r square value
          double SumC=0;
       for (int i = 0; i < nRows-1; i++)
       {
            SumC += Y[i][0];

       }
       double avg = SumC / (nRows-1);

       double deltaSum2 = 0;
       //

       double deltaPro1=0;
       for (int j = 0; j < nRows-1; j++)
       {
       	 	deltaPro1 =  (avg-Y[j][0]) * (avg-Y[j][0]);
       	 	deltaSum2 += deltaPro1;
       }


	    double v= deltaSum/deltaSum2;
    	rsquare=1-v;


    	standardErrorOfEstimate = new double[p];
    	for (int i = 0; i < p; i++)
    	{
    		standardErrorOfEstimate[i]=Math.sqrt(deltaSum1*b[i][i]);
    	}

    	tStatistics = new double[p];
    	for (int i = 0; i < p; i++)
    	{
            tStatistics[i]=estimate[i]/standardErrorOfEstimate[i];
        }




	}

}
