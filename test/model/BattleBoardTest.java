package model;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import model.BattleBoard;

public class BattleBoardTest {

	private BattleBoard b;

	public void setupScenary1() {
		b = new BattleBoard();
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
}


