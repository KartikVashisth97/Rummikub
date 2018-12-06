package core;
import junit.framework.TestCase;

public class MeldTest extends TestCase {
	public void testMeldInit() {
		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Meld m1 = new Meld();
		Meld m2 = new Meld(tArr);

		assertNotNull(m1);
		assertNotNull(m2);
	}
	public void testMeldString() {
		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Meld m1 = new Meld(tArr);
		assertEquals("{RED ONE BLUE TWO}",m1.toString());
	}
	public void testMeldEquals() {
		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Meld m1 = new Meld(tArr1);
		Meld m2 = new Meld(tArr2);
		assertEquals(m1, m2);
	}
	
	public void testMeldTotal() {
		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Meld m1 = new Meld(tArr1);
		
		assertEquals(3, m1.totalMeld());
	}
	
	public void testMeldValid() {
		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.RED, Tile.value.TWO),new Tile(Tile.colour.RED, Tile.value.THREE)};
		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.RED, Tile.value.TWO),new Tile(Tile.colour.RED, Tile.value.FOUR)};
		Tile[] tArr3 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.ONE),new Tile(Tile.colour.GREEN, Tile.value.ONE)};
		Tile[] tArr4 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.RED, Tile.value.ONE)};
		Tile[] tArr5 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.ONE),new Tile(Tile.colour.GREEN, Tile.value.ONE),new Tile(Tile.colour.ORANGE, Tile.value.ONE)};
		Tile[] tArr6 = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.THIRTEEN)};
		Meld m1 = new Meld(tArr1);
		Meld m2 = new Meld(tArr2);
		Meld m3 = new Meld(tArr3);
		Meld m4 = new Meld(tArr4);
		Meld m5 = new Meld(tArr5);
		Meld m6 = new Meld(tArr6);

		assertTrue(m1.validMeld());
		assertFalse(m2.validMeld());
		assertTrue(m3.validMeld());
		assertFalse(m4.validMeld());
		assertTrue(m5.validMeld());
		assertFalse(m6.validMeld());
	}
}
