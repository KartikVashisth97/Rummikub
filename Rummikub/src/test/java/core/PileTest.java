package core;

import junit.framework.TestCase;

public class PileTest extends TestCase {
	
	public void testDeal() {
		Pile pile = new Pile();
		assertEquals(104, pile.numTiles());
		pile.deal();
		assertEquals(103, pile.numTiles());
	}
	
	public void testScramble() {
		Pile pile = new Pile();
		assertEquals(true, pile.scramble());
	}
	
	public void testCount() {
		Pile pile = new Pile();
		assertEquals(104, pile.numTiles());
	}
}