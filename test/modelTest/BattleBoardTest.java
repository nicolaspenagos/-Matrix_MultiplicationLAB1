package modelTest;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import model.BattleBoard;

public class BattleBoardTest {

	private BattleBoard b;
	private int[][] matrixA;
	private int[][] matrixB;
	private int[][] matrixC;
	private int[][] matrixD;
	private int[] aa;
	private int[] bb;

	public void setupScenary1() {
		
		b = new BattleBoard();
		
	}
	
	public void setupScenary2() {
		
		b = new BattleBoard();
		
		matrixA = new int[2][2];
		matrixA[0][0] = 1;
		matrixA[0][1] = -2;
	    matrixA[1][0] = 2;
	    matrixA[1][1] = 4;
	    
		matrixB = new int[3][3];
		matrixB[0][0] = 32;
		matrixB[0][1] = 1;
	    matrixB[0][2] = 72;
	    matrixB[1][0] = 4;
		matrixB[1][1] = 67;
		matrixB[1][2] = 8;
	    matrixB[2][0] = 23;
	    matrixB[2][1] = 42;
	    matrixB[2][2] = 2;
	    
	    matrixC = new int[3][2]; 
	    matrixC[0][0] = 1;
	    matrixC[0][1] = -1;
	    matrixC[1][0] = -2;
	    matrixC[1][1] = 3;
	    matrixC[2][0] = 2;
	    matrixC[2][1] = 4;
	    
	    matrixD = new int[2][1]; 
	    matrixD[0][0] = 4;
	    matrixD[1][0] = 12;
	  
	}

	public void setupScenary3() {
		
		b = new BattleBoard();
		
		aa = new int [3];
		bb = new int [3];
		
		aa[0] = 1;
		aa[1] = 1;
		aa[2] = 1;
		
		bb[0] = 1;
		bb[1] = 2;
		bb[2] = 3;	
	
	}

	@Test
	public void testgenerateRandomMatrix() {
		
		setupScenary1();
		int m = 4;
		int n = 4;
		int mm = 4;
		int nn = 4;
		boolean r = true;
		b.generateRandomMatrix(m, n, mm, nn, r);
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				assertTrue("The matrix generation is incorrect", (b.getMatrix1()[i][j] <= 150 && b.getMatrix1()[i][j] >= 0));
			}
		}
		
	}
	
	@Test
	public void testIsRepeated() {
		
		setupScenary1();
		int[][] a = new int[4][4];
		int x = 3;
		int y = 2;
		a[2][2] = 3;
		
		assertTrue("The boolean should be true", b.isRepeated(x, a));
		assertFalse("The boolean should be false", b.isRepeated(y, a));
		
	}
	
	@Test
	public void componetToComponetMultiplierTest() {
		
		setupScenary2();
		
		int[][] resultAD = new int[2][1];
	    resultAD[0][0] =-20;
	    resultAD[1][0] = 56;
	    assertTrue("The multiplication is incorrect.", Arrays.deepEquals(resultAD, b.componetToComponetMultiplier(matrixA, matrixD)));
	    
	    int[][] resultBC = new int[3][2];
	    resultBC[0][0] = 174;
	    resultBC[0][1] = 259;
	    resultBC[1][0] = -114;
	    resultBC[1][1] = 229;
	    resultBC[2][0] = -57;
	    resultBC[2][1] = 111;
	    int[][] A =  b.componetToComponetMultiplier(matrixB, matrixC);
	    assertTrue("The multiplication is incorrect.", Arrays.deepEquals(resultBC, b.componetToComponetMultiplier(matrixB, matrixC)));
	}
	
	@Test
	public void auxMultiTest() {
		
		setupScenary2();

		assertTrue("The multiplication is incorrect", b.auxMulti(0, 0, matrixA, matrixD)==-20);
		assertTrue("The multiplication is incorrect", b.auxMulti(1, 0, matrixA, matrixD)==56);
		
		assertTrue("The multiplication is incorrect", b.auxMulti(2, 0, matrixB, matrixC)==-57);
		assertTrue("The multiplication is incorrect", b.auxMulti(0, 0, matrixB, matrixC)==174);
		assertTrue("The multiplication is incorrect", b.auxMulti(1, 1, matrixB, matrixC)==229);
		assertTrue("The multiplication is incorrect", b.auxMulti(2, 1, matrixB, matrixC)==111);
	}
	
	@Test
	public void multiTest() {
		
		setupScenary3();

		assertTrue("The multiplication is incorrect", b.multi(aa,aa)==3);
		assertTrue("The multiplication is incorrect", b.multi(aa,bb)==6);
		assertTrue("The multiplication is incorrect", b.multi(bb,bb)==14);
	}
	
	@Test
	public void isPrimeTest() {
		
		setupScenary1();
		assertTrue("The number is prime", b.isPrimeNumber(7));
		assertTrue("The number is prime", b.isPrimeNumber(71));
		assertTrue("The number is prime", b.isPrimeNumber(89));
		assertTrue("The number is prime", b.isPrimeNumber(53));
		assertTrue("The number is prime", b.isPrimeNumber(59));
		
		assertTrue("The number is not prime", !b.isPrimeNumber(21));
		assertTrue("The number is not prime", !b.isPrimeNumber(140));
		assertTrue("The number is not prime", !b.isPrimeNumber(100));
		assertTrue("The number is not prime", !b.isPrimeNumber(25));
		assertTrue("The number is not prime", !b.isPrimeNumber(32));
		
	}
	
	@Test
	public void generateRamdonMatricesTest() {
		
		setupScenary1();
		assertTrue("The matrices were not generated", b.getResult()==null);
		
	}
}

