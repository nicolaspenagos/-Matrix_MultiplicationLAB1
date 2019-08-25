package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
		
		String msg = "";
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
			msg+="\n";
			if(counter+1==numberOfMatrices) {
				msg+="------------------------------------\n RESULT\n";
			}
			auxM = componetToComponetMultiplier(auxM, matrixToMultiplier);
			for (int i = 0; i < auxM.length; i++) {
				for (int j = 0; j < auxM[0].length; j++) {
					msg+= " "+auxM[i][j]+" ";
				}
				msg+="\n";
			}
			
			msg+="\n";
			
			counter++;
		}
	
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

	public void printReport(String msg) throws FileNotFoundException{
		
		PrintWriter pw = new PrintWriter(new File(PATH));
		pw.print(msg);
		pw.close();
		
	}
	
}


