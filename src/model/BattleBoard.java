package model;

public class BattleBoard {
	
	private int[][] matrix1;
	private int[][] matrix2;
	
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
	
	public void generateRandomMatrices(int m, int n, int mm, int nn) {
		
		matrix1 = new int[m][n];
		matrix2 = new int[mm][nn];
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

}
