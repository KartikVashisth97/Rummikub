package core;
import junit.framework.TestCase;

public class HandTest extends TestCase {


	Tile t1 = new Tile(Tile.colour.RED, Tile.value.ONE);
	Tile t2 = new Tile(Tile.colour.BLUE, Tile.value.TWO);
	Tile t3 = new Tile(Tile.colour.RED, Tile.value.TWO);
	Tile t4 = new Tile(Tile.colour.GREEN, Tile.value.TWO);
	Tile t5 = new Tile(Tile.colour.ORANGE, Tile.value.FOUR);
	Tile t6 = new Tile(Tile.colour.ORANGE, Tile.value.THREE);

	Tile[] tArr = {t1,t2};

	public void testisEmpty() {
		Hand h1 = new Hand();
		h1.addTileToHand(t1);
		h1.addTileToHand(t2);
		assertFalse(h1.isEmpty());
	}

	public void testGetTile() {
		Hand h1 = new Hand();
		h1.addTileToHand(t1);
		h1.addTileToHand(t2);
		assertEquals(h1.getTile(0), t1);
	}

	public void testSortByValue() {
		Hand h1 = new Hand();
		h1.addTileToHand(t3);
		h1.addTileToHand(t1);
		assertEquals(h1.getTile(0), t3);
		assertEquals(h1.toString(), "[R1 R2]");
	}

	public void testSortByValueColor() {
		Hand h1 = new Hand();
		h1.addTileToHand(t3);
		h1.addTileToHand(t2);
		h1.addTileToHand(t5);
		h1.addTileToHand(t4);
		h1.addTileToHand(t1);
		assertEquals(h1.getTile(0), t3);
		assertEquals(h1.toString(), "[R1 R2 B2 G2 O4]");
	}

	public void testGetSize() {
		Hand h1 = new Hand();
		h1.addTileToHand(t1);
		h1.addTileToHand(t2);
		assertEquals(h1.sizeOfHand(), 2);

		h1.playTileFromHand(t1);
		assertEquals(h1.sizeOfHand(), 1);
	}
	
}
