package model;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import model.SpaceShip;

public class SpaceShipTest {

	private int x;
	private int y;
	
	private SpaceShip sp;
	public void setupScenary1() {
		x = 2;
		y = 4;
	}
	@Test
	public void testSpaceShip() {
		setupScenary1();
		sp = new SpaceShip(x, y);
		assertTrue("Not the right attribute", sp.getX() == 2);
		assertTrue("Not the right attribute", sp.getY() == 4);
	}

}
