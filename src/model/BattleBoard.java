package model;

public class BattleBoard {
	
	private int[][] matrix1;
	private int[][] matrix2;
	private int[][] result;
	
	public BattleBoard() {
	
	}
	
	public void setMatrix(int x, int[][] mtrx) {
		
		if(x == 1) {
			matrix1 = mtrx;
		}else if(x == 2) {
			matrix2 = mtrx; 
		}
		
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
	
	public void generateRandomMatrices(int m, int n, int mm, int nn) {
		
		matrix1 = new int[m][n];
		matrix2 = new int[mm][nn];
		result = new int[m][nn];
		int x = 0;
		
		for(int i = 0; i<m; i++) {
			for(int j = 0; j<n; j++) {
				x =(int) (Math.random() * 99) + 1; 
				matrix1[i][j] = x;
				
			}
		}
		
		for(int i = 0; i<mm; i++) {
			for(int j = 0; j<nn; j++) {
				x =(int) (Math.random() * 99) + 1; 
				matrix2[i][j] = x;
				
			}
		}
		
		
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

}
