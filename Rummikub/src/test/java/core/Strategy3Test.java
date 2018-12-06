package core;

import java.io.File;

import junit.framework.TestCase;

public class Strategy3Test extends TestCase {
	public void testHasSet() {
		Tile[] tArr = {new Tile(Tile.colour.BLUE, Tile.value.NINE), new Tile(Tile.colour.GREEN, Tile.value.NINE), new Tile(Tile.colour.RED, Tile.value.NINE), new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN)};
		Hand h = new Hand(tArr);
		Strategy3 p = new Strategy3(h);

		assertNotNull(p.playableSets());
	}
	
	public void testPlayTile() {
		File file = new File("src/test/resources/testInit");
		Game g = new Game();
		g.init(file);
		
		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN)};
		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.NINE),new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.ELEVEN)};
		Meld m1 = new Meld(tArr1);
		Meld m2 = new Meld(tArr2);
		Meld[] mArr = {m1,m2};
		Table t1 = new Table(mArr);
		Tile[] tArr = {new Tile(Tile.colour.GREEN, Tile.value.TEN), new Tile(Tile.colour.RED, Tile.value.EIGHT),new Tile(Tile.colour.RED, Tile.value.TWELVE),new Tile(Tile.colour.RED, Tile.value.SEVEN)};
		Hand h = new Hand(tArr);
		Strategy3 p = new Strategy3(h,t1);
		p.game = g;
		p.setInitialMeld(false);
		
		p.play();
		System.out.println(p.getTable());
		assertTrue(p.getHand().isEmpty());
	}

	public void testPlayMeld() {
		Tile[] tArr = {new Tile(Tile.colour.BLUE, Tile.value.TEN), new Tile(Tile.colour.GREEN, Tile.value.TEN), new Tile(Tile.colour.RED, Tile.value.TEN)};
		Meld m = new Meld(new Tile[]{new Tile(Tile.colour.BLUE, Tile.value.TEN), new Tile(Tile.colour.GREEN, Tile.value.TEN), new Tile(Tile.colour.RED, Tile.value.TEN)});
		Hand h = new Hand(tArr);
		Strategy3 p = new Strategy3(h);
		p.playMeld(m);
		assertTrue(p.getHand().isEmpty());
	}
	
	public void testIsPlayMeldTrue() {
		File file = new File("src/test/resources/testInit");
		Game g = new Game();
		g.init(file);
		
		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.TWO),new Tile(Tile.colour.GREEN, Tile.value.TWO),new Tile(Tile.colour.BLUE, Tile.value.TWO),new Tile(Tile.colour.RED, Tile.value.TWO),new Tile(Tile.colour.GREEN, Tile.value.TWO),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.TWO),new Tile(Tile.colour.GREEN, Tile.value.TWO),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Hand h = new Hand(tArr);
		Hand h2 = new Hand(tArr2);
		Strategy3 p = new Strategy3(h);
		g.setH2(h2);
		p.game = g;
		
		assertTrue(p.isPlayMeld());
	}
	
	public void testIsPlayMeldFalse() {
		File file = new File("src/test/resources/testInit");
		Game g = new Game();
		g.init(file);
		
		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.TWO),new Tile(Tile.colour.GREEN, Tile.value.TWO),new Tile(Tile.colour.BLUE, Tile.value.TWO),new Tile(Tile.colour.RED, Tile.value.TWO),new Tile(Tile.colour.GREEN, Tile.value.TWO),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.TWO),new Tile(Tile.colour.GREEN, Tile.value.TWO),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Hand h = new Hand(tArr);
		Hand h2 = new Hand(tArr2);
		Strategy3 p = new Strategy3(h);
		g.setH2(h2);
		p.game = g;
		
		assertFalse(p.isPlayMeld());
	}

	public void testHasRuns() {
		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.ELEVEN),new Tile(Tile.colour.RED, Tile.value.TWELVE),new Tile(Tile.colour.RED, Tile.value.THIRTEEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TWELVE),new Tile(Tile.colour.ORANGE, Tile.value.ONE),new Tile(Tile.colour.ORANGE, Tile.value.TWO),new Tile(Tile.colour.ORANGE, Tile.value.THREE)};
		Hand h = new Hand(tArr);
		Strategy3 p = new Strategy3(h);
		assertNotNull(p.playableRuns());
	}
}
