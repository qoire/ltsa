package determinant;

import helper.FileInput;
import Jama.Matrix; 

public class Determinant{

    int dimension = 0; //the dimension of the original matrix, including the ERROR state
    int interval = -1; //in case it's been asked to calculate intervals of the matrix
//    Matrix mat;
    
    public void setDim(int dim){
       this.dimension = dim;
    };
    
    public int getDim(){
      return(dimension);
    };
    
    double[][] getArray(String matFile){
        FileInput in = new FileInput(matFile);
        setDim(in.readInteger());
        int d = getDim();
        double[][] arr  = new double[d][d];
        int count = 0;
        String[] row = new String[d];
        String aux;
        while (!in.eof())
        {
            aux = in.readString();
            if (aux != null){
                row = aux.split("\\s+");
                for (int c = 0; c<=d-1;c++){
                    arr[count][c] = Double.parseDouble(row[c]);
                }
                count++;
            };
        };
        return(arr);
    };
 /*   
    public void setMatrix(double[][] mat){
        this.mat = new Matrix(mat);
    };

    public Matrix getMatrix(){
        return(this.mat);
    };

   */ 
   
    public void setInterval(int interval){
       this.interval = interval;
    };
    
    public int getInterval(){
      return this.interval;
    };
    
    public double calculateReliability(String matFile){
        double reliability = 0.0;
        double[][] arr = getArray(matFile);
        Matrix matERROR = new Matrix(arr);
        int dim = getDim();
        Matrix mat = matERROR.getMatrix(0, dim-2, 0, dim-2); // dim-2 is because there are two final states in the FSP
                                                            // the one with endAction and the one with STOP
                                                            //the dimension of mat is dim(matERROR) -1, as we take the last column and row out (They are the ones of the ERROR state)
        int inter = getInterval();
        int j = 0;
        Matrix matInterval = new Matrix(matERROR.getArrayCopy());
        Matrix matResult = new Matrix(dim, dim);
        if (inter>0){
            for (int i=1; i<inter; i++){
                    matInterval = matInterval.times(matERROR);
            };
            matInterval.print(4,6);
            double unreliability = matInterval.get(0, dim-1);
            //Matrix rowState0 = matInterval.getMatrix(0, 0, 0, dim-1);
            //Matrix columnERROR = matInterval.getMatrix(0, dim-1, dim-1, dim-1);
            //rowState0.print(4,4);
            //columnERROR.print(4,4);
            // Matrix finalMat = rowState0.times(columnERROR);
            //finalMat.print(6,5);
            //double unreliability = finalMat.get(0,0);
            //System.out.println("The dot product is: "+ unreliability);
            reliability = 1-unreliability;
            //System.out.println("The reliability is (considering 1-dotProduct): "+ reliability);
            //int temp = dim-2;
            //unreliability = finalMat.get(0,temp);
            //reliability = 1-unreliability;
            //System.out.println("The reliability is (considering 1-element["+0+","+temp+"]): "+ reliability);            
        }
        else {
            int matDim = dim-1;
            Matrix identityMinus = Matrix.identity(matDim, matDim).minus(mat);
            double detValue1 = identityMinus.det();
            Matrix subMat = identityMinus.getMatrix(0, matDim-2, 1, matDim-1);
            //subMat.print(6,5);
            //System.out.println("The determinant of the identity minus matrix is: "+ detValue1);
            double detValue2 = subMat.det();
            //System.out.println("The determinant of the sub matrix is: "+ detValue2);
            reliability = Math.pow(-1,matDim+1)*(detValue2/detValue1); 
            //System.out.println("The computed reliability is: "+reliability);
        }
        
        return reliability;
    };
    
    //In case one wants to read the matrix from the memory, instead of reading from a file
    public double calculateReliability(int d, double[][] matrix){
	    double reliability = 0.0;
	    setDim(d);
	    double[][] arr = new double[d][d];
	    arr = matrix;
	    Matrix matERROR = new Matrix(arr);
	    Matrix mat = matERROR.getMatrix(0, d-2, 0, d-2); // dim-2 is because there are two final states in the FSP
	                                                        // the one with endAction and the one with STOP
	                                                        //the dimension of mat is dim(matERROR) -1, as we take the last column and row out (They are the ones of the ERROR state)
	    int inter = getInterval();
	    int j = 0;
	    Matrix matInterval = new Matrix(matERROR.getArrayCopy());
	    Matrix matResult = new Matrix(d, d);
	    if (inter>0){
	        for (int i=1; i<inter; i++){
	                matInterval = matInterval.times(matERROR);
	        };
	        matInterval.print(4,6);
	        double unreliability = matInterval.get(0, d-1);
	        //System.out.println("The dot product is: "+ unreliability);
	        reliability = 1-unreliability;
	        //System.out.println("The reliability is (considering 1-dotProduct): "+ reliability);
	         
	    }
	    else {
	        int matDim = d-1;
	        Matrix identityMinus = Matrix.identity(matDim, matDim).minus(mat);
	        double detValue1 = identityMinus.det();
	        Matrix subMat = identityMinus.getMatrix(0, matDim-2, 1, matDim-1);
	        double detValue2 = subMat.det();
	        reliability = Math.pow(-1,matDim+1)*(detValue2/detValue1); 
	        //System.out.println("The computed reliability is: "+reliability);
	    }
	    return reliability;
};
    

}
