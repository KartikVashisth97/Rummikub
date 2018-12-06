package core;
import junit.framework.TestCase;

public class TileTest extends TestCase {
	public void testTileInit() {
		Tile t1 = new Tile(Tile.colour.RED, Tile.value.ONE);
		assertNotNull(t1);
	}
	public void testTileString() {
		Tile t1 = new Tile(Tile.colour.RED, Tile.value.ONE);
		assertEquals("RED ONE",t1.toString());
	}
	public void testTileEquals() {
		Tile t1 = new Tile(Tile.colour.RED, Tile.value.ONE);
		Tile t2 = new Tile(Tile.colour.RED, Tile.value.ONE);
		assertEquals(t1, t2);
	}
}
