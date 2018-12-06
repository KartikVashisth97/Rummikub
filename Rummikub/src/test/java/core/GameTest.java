package core;

import java.io.File;

import junit.framework.TestCase;

public class GameTest extends TestCase {
	public void testInit() {
		//pile : table { R1 } : hand0 : hand1 : hand2 : hand3
		File file = new File("src/test/resources/testInit");
		Game g = new Game();
		g.init(file);
	}

}
