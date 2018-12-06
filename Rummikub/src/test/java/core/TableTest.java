package core;
import junit.framework.TestCase;

public class TableTest extends TestCase {
	public void testTableInit() {
		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.EIGHT),new Tile(Tile.colour.BLUE, Tile.value.ELEVEN)};
		Meld m1 = new Meld(tArr1);
		Meld m2 = new Meld(tArr2);
		Meld[] mArr = {m1,m2};
		Table t1 = new Table();
		Table t2 = new Table(mArr);

		assertNotNull(t1);
		assertNotNull(t2);
	}
	public void testTableString() {
		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.EIGHT),new Tile(Tile.colour.BLUE, Tile.value.ELEVEN)};
		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Meld m1 = new Meld(tArr1);
		Meld m2 = new Meld(tArr2);
		Meld[] mArr = {m1,m2};
		Table t1 = new Table(mArr);
		assertEquals("{RED EIGHT BLUE ELEVEN} {RED ONE BLUE TWO}",t1.toString());
	}
	public void testTableEquals() {
		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Meld m1 = new Meld(tArr1);
		Meld m2 = new Meld(tArr2);
		Meld[] mArr1 = {m1,m2};
		Meld[] mArr2 = {m1,m2};
		Table t1 = new Table(mArr1);
		Table t2 = new Table(mArr2);
		assertEquals(t1, t2);
	}
	
}
