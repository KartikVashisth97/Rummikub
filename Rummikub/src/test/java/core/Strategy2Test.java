package core;

import java.io.File;

import junit.framework.TestCase;

public class Strategy2Test extends TestCase {
	public void testHasSet() {
		Tile[] tArr = {new Tile(Tile.colour.BLUE, Tile.value.NINE), new Tile(Tile.colour.GREEN, Tile.value.NINE), new Tile(Tile.colour.RED, Tile.value.NINE), new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN)};
		Hand h = new Hand(tArr);
		Strategy2 p = new Strategy2(h);

		assertNotNull(p.playableSets());
	}

	public void testPlayTile() {
		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN)};
		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.NINE),new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.ELEVEN)};
//		Tile[] tArr3 = {new Tile(Tile.colour.RED, Tile.value.THREE),new Tile(Tile.colour.GREEN, Tile.value.THREE),new Tile(Tile.colour.BLUE, Tile.value.THREE)};
		Meld m1 = new Meld(tArr1);
		Meld m2 = new Meld(tArr2);
//		Meld m3 = new Meld(tArr2);
		Meld[] mArr = {m1,m2};
		Table t1 = new Table(mArr);
		Tile[] tArr = {new Tile(Tile.colour.GREEN, Tile.value.TEN)};
		Hand h = new Hand(tArr);
		Strategy2 p = new Strategy2(h,t1);
		p.setInitialMeld(false);
		
		p.play();
		assertTrue(p.getHand().isEmpty());
	}

	public void testPlay() {
		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN)};
		Hand h = new Hand(tArr);
		Strategy2 p = new Strategy2(h);

		p.play();
		System.out.println(p.getTable());
		assertTrue(p.getHand().isEmpty());
	}
	
	public void testNoPlayBeforeInitialMeld() {
		Pile pile = new Pile();
		
		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN)};
		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.NINE),new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.ELEVEN)};
//		Tile[] tArr3 = {new Tile(Tile.colour.RED, Tile.value.THREE),new Tile(Tile.colour.GREEN, Tile.value.THREE),new Tile(Tile.colour.BLUE, Tile.value.THREE)};
		Meld m1 = new Meld(tArr1);
		Meld m2 = new Meld(tArr2);
//		Meld m3 = new Meld(tArr2);
		Meld[] mArr = {m1,m2};
		Table t1 = new Table(mArr);
		Tile[] tArr = {new Tile(Tile.colour.GREEN, Tile.value.TEN)};
		Hand h = new Hand(tArr);
		Strategy2 p = new Strategy2(h,t1);
		p.pile = pile;
		p.setInitialMeld(true);
		
		p.play();
		assertFalse(p.getHand().isEmpty());
		assertEquals(p.getHand().sizeOfHand(), 2);
	}

	public void testPlayMeld() {
		Tile[] tArr = {new Tile(Tile.colour.BLUE, Tile.value.TEN), new Tile(Tile.colour.GREEN, Tile.value.TEN), new Tile(Tile.colour.RED, Tile.value.TEN)};
		Meld m = new Meld(new Tile[]{new Tile(Tile.colour.BLUE, Tile.value.TEN), new Tile(Tile.colour.GREEN, Tile.value.TEN), new Tile(Tile.colour.RED, Tile.value.TEN)});
		Hand h = new Hand(tArr);
		Strategy2 p = new Strategy2(h);
		p.playMeld(m);
		assertTrue(p.getHand().isEmpty());
	}

	public void testHasRuns() {
		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.ELEVEN),new Tile(Tile.colour.RED, Tile.value.TWELVE),new Tile(Tile.colour.RED, Tile.value.THIRTEEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TWELVE),new Tile(Tile.colour.ORANGE, Tile.value.ONE),new Tile(Tile.colour.ORANGE, Tile.value.TWO),new Tile(Tile.colour.ORANGE, Tile.value.THREE)};
		Hand h = new Hand(tArr);
		Strategy2 p = new Strategy2(h);
		assertNotNull(p.playableRuns());
	}
}
