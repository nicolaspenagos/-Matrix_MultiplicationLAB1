package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BattleBoard {

	public final static String PATH = "data/ListOfMatrices.txt";
	
	private int[][] matrix1;
	private int[][] matrix2;
	private int[][] result;


	public BattleBoard() {

	}

	public int[][] getMatrix1(){
		return matrix1;
	}

	public int[][] getMatrix2(){
		return matrix2;
	}

	public int[][] getResult(){
		return result;
	}

	public void generateRandomMatrix(int m, int n, int mm, int nn, boolean r) {

		matrix1 = new int[m][n];
		matrix2 = new int[mm][nn];
		result = new int[m][nn];
		int x = 0;

		if(r == false) {
			for(int i = 0; i<m; i++) {
				for(int j = 0; j<n; j++) {
					x =(int) (Math.random() * 150) + 1; 
					while(isRepeated(x, matrix1)) {
						x =(int) (Math.random() * 150) + 1; 
					}
					matrix1[i][j] = x;	
				}
			}
			for(int i = 0; i<mm; i++) {
				for(int j = 0; j<nn; j++) {
					x = (int) (Math.random() * 150) + 1;
					while(isRepeated(x, matrix1)) {
						x =(int) (Math.random() * 150) + 1; 
					}
					matrix2[i][j] = x;		
				}
			}
		}
		for(int i = 0; i<m; i++) {
			for(int j = 0; j<n; j++) {
				x =(int) (Math.random() * 150) + 1;
				matrix1[i][j] = x;	
			}
		}

		for(int i = 0; i<mm; i++) {
			for(int j = 0; j<nn; j++) {
				x =(int) (Math.random() * 150) + 1;  
				matrix2[i][j] = x;
			}
		}


	}
	
	public void generateRamdonMatrices(int x) throws FileNotFoundException {
		
		String msg = "-------------------- MATRIX LIST --------------------\n";
		int numberOfMatrices = x;
		int counter = 1;
		int temp = (int) (Math.random() * 9) + 1;
		int[][] auxM = new int[((int)Math.random() * 9) + 1][temp];
	
		
		for(int i = 0; i<auxM.length; i++) {
			for(int j = 0; j<temp; j++) {
				auxM[i][j] = (int) (Math.random() * 9) + 1;;	
				msg+= " "+auxM[i][j]+" ";
			}
			msg+="\n";
		}
		msg+="\n";
		while(counter<numberOfMatrices) {
			
			int m = temp;
			int n = (int) (Math.random() * 9) + 1;
			temp = n;
			
			int[][] matrixToMultiplier = new int[m][n];
			for(int i = 0; i<m; i++) {
				for(int j = 0; j<n; j++) {
					matrixToMultiplier[i][j] = (int) (Math.random() * 9) + 1;
					msg+= " "+matrixToMultiplier[i][j]+" ";
				}
				msg+="\n";
			}
			if(!(counter+1==numberOfMatrices)) 
				msg+="\n";
			if(counter+1==numberOfMatrices) 
				msg+="---------------------- RESULT ----------------------\n";
			
			auxM = componetToComponetMultiplier(auxM, matrixToMultiplier);
		
			
			if(!(counter+1==numberOfMatrices)) 
				msg+="\n";
			
		
			
			counter++;
		}
		
		for (int i = 0; i < auxM.length; i++) {
		for (int j = 0; j < auxM[0].length; j++) {
			msg+= " "+auxM[i][j]+" ";
		}
		msg+="\n";
	}
		result = auxM;
		printReport(msg);
	}

	public boolean isRepeated(int x, int[][] a) {
		boolean repeated = false;

		int n = a.length;
		int m = a[0].length;

		for(int i = 0; i < n && !repeated; i++) {
			for(int j = 0; j < m && !repeated; j++) {
				if(a[i][j] == x) {
					repeated = true;
				}
			}
		}

		return repeated;
	}

	public void componetToComponetMultiplier(){

		int m = matrix1.length;
		int n = matrix2[0].length;
		int[][] resultTemp = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				resultTemp[i][j] = auxMulti(i,j);
			}
		}

		result = resultTemp;
	}
	
	public int[][] componetToComponetMultiplier(int[][] a, int[][] b){

		int m = a.length;
		int n = b[0].length;
		int[][] resultTemp = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				resultTemp[i][j] = auxMulti(i,j, a, b);
			}
		}

		return resultTemp;
	}
	
	public int auxMulti(int i, int j) {

		int a[] = new int[matrix1[0].length];
		int b[] = new int[matrix2.length];

		for (int k = 0; k < a.length; k++) {
			a[k] = matrix1[i][k];
		}

		for (int k = 0; k < b.length; k++) {
			b[k] = matrix2[k][j];
		}

		return multi(a,b);
	}
	
	public int auxMulti(int i, int j, int[][] aa, int[][] bb) {
		int a[] = new int[aa[0].length];
		int b[] = new int[bb.length];
	
		for (int k = 0; k < a.length; k++) {
			a[k] = aa[i][k];
		}

		for (int k = 0; k < b.length; k++) {
			b[k] = bb[k][j];
		}

		return multi(a,b);
	}

	public int multi(int[] a, int[]b) {

		int n = a.length;
		int result = 0;
		System.out.println("");
		for (int i = 0; i < n; i++) {
			System.out.print("("+a[i]+" * "+b[i]+") + ");
			result = result + (a[i]*b[i]);
		}

		return result;

	}
	
	public boolean isPrimeNumber(int number) {
		
		boolean isPrime = true;
		int counter = 2;
		
		while(isPrime && counter != number) {
			if(number%counter==0) 
				isPrime=false;
			counter++;
		}
		
		return isPrime;
		
	}
	
	public void strassenAlgorithm() {
		result = strassenFirstStep();
	}
	
	public int[][] strassenFirstStep() {
		int[][] momResult;
		int[][] result;
		
		if(matrix1.length == matrix1[0].length && matrix2.length == matrix2[0].length && isPowerOfTwo(matrix1.length)) {
			momResult = strassenMulti(matrix1, matrix2);
		}else {
			int n = greaterToPowerOfTwo(matrix1.length, matrix1[0].length, matrix2[0].length);
			int[][] matrixTemp1 = completeMatrix(matrix1, n);
			int[][] matrixTemp2 = completeMatrix(matrix2, n);
			momResult = strassenMulti(matrixTemp1, matrixTemp2);
		}
		
		result = clearZeros(momResult, matrix1.length, matrix2[0].length);
		return result;
	}
	
	public int[][] strassenMulti(int[][] A, int[][] B) {
		int n = A.length;
		int[][] res =  new int[n][n];
		
		if(n == 1) {
			res[0][0] = A[0][0] * B[0][0];
		}else{
			
		//Create subdivision of matrix A
			int[][] a = new int[n/2][n/2];
			int[][] b = new int[n/2][n/2];
			int[][] c = new int[n/2][n/2];
			int[][] d = new int[n/2][n/2];
			
		//Create subdivision of matrix A
			int[][] e = new int[n/2][n/2];
			int[][] f = new int[n/2][n/2];
			int[][] g = new int[n/2][n/2];
			int[][] h = new int[n/2][n/2];
		
		// dividing matrix A into 4 parts
			divideArray(A, a, 0, 0);
			divideArray(A, b, 0, n / 2);
			divideArray(A, c, n / 2, 0);
			divideArray(A, d, n / 2, n / 2);

        // dividing matrix B into 4 parts
			divideArray(B, e, 0, 0);
			divideArray(B, f, 0, n / 2);
			divideArray(B, g, n / 2, 0);
			divideArray(B, h, n / 2, n / 2);

		/** 
        p1 = (a + d)(e + h)
        p2 = (c + d)e
        p3 = a(f - h)
        p4 = d(g - e)
        p5 = (a + b)h
        p6 = (c - a) (e + f)
        p7 = (b - d) (g + h)
        **/
		
			int[][] p1 = strassenMulti(addMatrices(a, d), addMatrices(e, h));
            int[][] p2 = strassenMulti(addMatrices(c,d),e);
            int[][] p3 = strassenMulti(a, subMatrices(f, h));           
            int[][] p4 = strassenMulti(d, subMatrices(g, e));
            int[][] p5 = strassenMulti(addMatrices(a,b), h);
            int[][] p6 = strassenMulti(subMatrices(c, a), addMatrices(e, f));
            int[][] p7 = strassenMulti(subMatrices(b, d), addMatrices(g, h));            
		
        /**
        C11 = p1 + p4 - p5 + p7
        C12 = p3 + p5
        C21 = p2 + p4
        C22 = p1 - p2 + p3 + p6
      **/
         
          int[][] C11 = addMatrices(subMatrices(addMatrices(p1, p4), p5), p7);
          int[][] C12 = addMatrices(p3, p5);
          int[][] C21 = addMatrices(p2, p4);
          int[][] C22 = addMatrices(subMatrices(addMatrices(p1, p3), p2), p6);
			
       // adding all subarray back into one
          copySubArray(C11, res, 0, 0);
          copySubArray(C12, res, 0, n / 2);
          copySubArray(C21, res, n / 2, 0);
          copySubArray(C22, res, n / 2, n / 2);
		}
		return res;
	}
	
	public int greaterToPowerOfTwo(int n, int m, int k) {
		int finalN = 0;
			if(n>= m && n >= k) {
				finalN = n;
			}else if(m >= n && m >= k) {
				finalN = m;
			}else if(k >= n && k >= m) {
				finalN = k;
			}
		return getNextPowerOfTwo(finalN);
	}
	
	public int[][] completeMatrix(int[][] A, int n){
		//n equals to the size of the matrix A turned into a squared matrix of the form 2^n x 2^n filled with 0´s in the missing rows and columns 
		int[][] B = new int[n][n];
			for(int i = 0 ; i < A.length ; i++) {
				for(int j = 0 ; j < A[0].length ; j++) {
					B[i][j] = A[i][j];
				}
			}
		return B;
	}
	
	public boolean isPowerOfTwo(int x) {
		boolean isPower = false;
			if((x%2) == 0) {
				if(x/2 == 1) {
					isPower = true;
				}else {
					isPowerOfTwo(x/2);
				}
			}else {
				isPower = false;
			}
		return isPower;
	}
	
	public int getNextPowerOfTwo(int value) {
	    int result = value;
	    result -= 1;
	    result |= result >> 16;
	    result |= result >> 8;
	    result |= result >> 4;
	    result |= result >> 2;
	    result |= result >> 1;
	    return result + 1;
	}
	
	public void divideArray(int[][] P, int[][] C, int iB, int jB) {
		for(int i1 = 0, i2 = iB; i1 < C.length ; i1++ , i2++ ){
			for(int j1 = 0, j2 = jB ; j1 <C.length ; j1++ , j2++){
					C[i1][j1] = P[i2][j2];
			}
		}
	}
	
	//Adding two matrices
	public int[][] addMatrices(int[][] a, int[][] b) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = a[i][j] + b[i][j];
            }
        }
        return res;
    }

    //Subtracting two matrices
    public int[][] subMatrices(int[][] a, int[][] b) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = a[i][j] - b[i][j];
            }
        }
        return res;
    }
    
    public void copySubArray(int[][] X, int[][] R, int iB, int jB) {
        for(int i1 = 0, i2 = iB; i1 < X.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < X.length; j1++, j2++)
                R[i2][j2] = X[i1][j1];
    }
	
	public int[][] clearZeros(int[][] A, int n, int m){
    	int[][] cleanMatrix = new int[n][m];
    		for(int i = 0 ; i < n ; i++) {
    			for(int j = 0 ; j < m ; j++) {
    				cleanMatrix[i][j] = A[i][j];
    			}
    		}
    	return cleanMatrix;
    }

	public void printReport(String msg) throws FileNotFoundException{
		
		PrintWriter pw = new PrintWriter(new File(PATH));
		pw.print(msg);
		pw.close();
		
	}
	
}


