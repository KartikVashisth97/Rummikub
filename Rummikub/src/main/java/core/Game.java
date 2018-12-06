package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class Game extends Observable{
	private Pile pile;
	private Table table;
//	private CLI ui;
	private Hand handArr[];
	private Player playerArr[];
	
	private Pane pane;
	private GUI ui;
	
	public void init(Stage primaryStage) {
		pile = new Pile();
		table = new Table();
//		ui = new CLI();
//		playerArr = new Player[4];
		playerArr = new Player[3];
		handArr = new Hand[3];
//		handArr = new Hand[4];
        ui = new GUI(pane);

        ui.boardInit(primaryStage);
        primaryStage.show();
        
		for(int i = 0; i<3; i++) {
			handArr[i] = new Hand();
		}

		playerArr[0] = new StrategyHuman(ui);
		playerArr[1] = new Strategy1(ui);
		playerArr[2] = new Strategy3(ui);
//		playerArr[3] = new StrategyHuman();
		pile.scramble();


		this.addObserver(playerArr[0]);
		this.addObserver(playerArr[1]);
		this.addObserver(playerArr[2]);
//		this.addObserver(playerArr[3]);
	}

	public void init(File file) {
		//TODO update init(File) for gui

		//pile;table;hand0;hand1;hand2;hand3
		pile = new Pile(false);
		table = new Table();
//		ui = new CLI();
//		playerArr = new Player[4];
		playerArr = new Player[3];
		handArr = new Hand[3];
//		handArr = new Hand[4];

		for(int i = 0; i<3; i++) {
			handArr[i] = new Hand();
		}

		playerArr[0] = new StrategyHuman();
		playerArr[1] = new Strategy1();
		playerArr[2] = new Strategy3();
//		playerArr[3] = new StrategyHuman();


		this.addObserver(playerArr[0]);
		this.addObserver(playerArr[1]);
		this.addObserver(playerArr[2]);
//		this.addObserver(playerArr[3]);
		
		String str;
		BufferedReader br;
		String[] tileStr;
		int arrCache = 0;
		Meld tempMeld = null;
		Tile tempTile;

		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			ui.message("File not found");
			return;
		}
		try {
			str = br.readLine();
		} catch (IOException e) {
			ui.message("File does not follow correct format");

			try {
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				ui.message("Could not close BufferedReader");
			}

			return;
		}
		if(closeBR(br) == -1) {
			return;
		}

		tileStr = str.split("\\s+");
		
		char test;
		for(String s: tileStr) {
		//pile : table { R1 } : hand0 : hand1 : hand2 : hand3 : E
			test = s.charAt(0);
			if((int)test == 58) {
				arrCache++;
				continue;
			}
			if((int)test == 123) {
				tempMeld = new Meld();
				continue;
			}
			if((int)test == 125) {
				if(tempMeld == null) {
					ui.message("*Error Unable to init, bad meld in table");
					return;
				}
				table.add(tempMeld);
				continue;
			}
			
			tempTile = stringToTile(s);
			
			switch(arrCache) {
			case 0:
				pile.add(tempTile);
				break;
			case 1:
				if(tempMeld == null) {
					ui.message("*Error Meld is null");
					return;
				}
				tempMeld.add(tempTile);
				break;
			case 2:
				handArr[0].addTileToHand(tempTile);
				break;
			case 3:
				handArr[1].addTileToHand(tempTile);
				break;
			case 4:
				handArr[2].addTileToHand(tempTile);
				break;
			case 5:
				System.out.println(pile);
				System.out.println(table);
				System.out.println(handArr[0]);
				System.out.println(handArr[1]);
				System.out.println(handArr[2]);
				return;
			default:
				System.out.println(pile);
				System.out.println(table);
				System.out.println(handArr[0]);
				System.out.println(handArr[1]);
				System.out.println(handArr[2]);
				return;
				
			}
		}
		ui.message("*Error not enough Tile info given");
		System.out.println(pile);
		System.out.println(table);
		System.out.println(handArr[0]);
		System.out.println(handArr[1]);
		System.out.println(handArr[2]);

	}

	private Tile stringToTile(String tile) {
		Tile t = new Tile();
		int tempVal = 0;
		
		for (Tile.colour c : Tile.colour.values()) {
			if(tile.charAt(0) == c.getCol()) {
				t.setColour(c);
			}
		}
		if(t.getColour() == null) {
			return null;
		}

		if(tile.length() == 2) {
			tempVal = Character.getNumericValue(tile.charAt(1));
		}else if(tile.length() == 3) {
			tempVal = Integer.parseInt(tile.substring(1,3));
		}
		if(tempVal < 1) {
			return null;
		}

		for (Tile.value v : Tile.value.values()) {
			if(tempVal == v.getVal()) {
				t.setValue(v);
			}
		}
		if(t.getValue() == null) {
			return null;
		}
		return t;
	}

	private int closeBR(BufferedReader br) {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			ui.message("Could not close BufferedReader");
			return -1;
		}
		return 0;
	}

	public void start() {
		ui.message("Welcome To Rummikub!");
		//Each Player is assigned a hand 0 for human and so on
		deal(handArr[0]);
		deal(handArr[1]);
		deal(handArr[2]);
//		deal(handArr[3]);

//		//testing
//		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN)};
//		handArr[0] = new Hand(tArr);
//		Meld tMeld = new Meld(new Tile[] {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN)});
//		table.add(tMeld);

		/*if(1==1) {
			return;
		}*/
		broadcast();

		turnLoop();
		
	}

	int noOfTurns=0;
	private Player turnLoop() {
		noOfTurns++;
		if(noOfTurns>2)return null;

		for(int i = 0; i<3; i++) {

			System.out.println("___________________________________________________");

			ui.message("Player " + playerArr[i].toString() + "'s turn.");

			playerArr[i].play();

			broadcast();

			ui.message("Player " + playerArr[i].toString() + "'s hand.");
			ui.message(handArr[i].toString());
			ui.displayHand(playerArr[i].hand);

			ui.message("Table: ");
			ui.message(playerArr[i].displayPlay());
			ui.displayTable(table);

			if(handArr[i].isEmpty()) {
				return playerArr[i];
			}

			ui.playerScores[i]=playerArr[i].getHand().sizeOfHand();
			ui.response("Next Player?: ");

			if(i==2) i=-1;
		}

		return null;
	}

	private void deal(Hand hand) {
		for(int i = 0; i<14; i++) {
			hand.addTileToHand(pile.deal());
		}
	}

	public Pile getPile() {
		return pile;
	}

	public void setPile(Pile pile) {
		this.pile = pile;
	}

	private void broadcast() {
		setChanged();
		notifyObservers();
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public ArrayList<Hand> gethands() {
		ArrayList<Hand> hands  = new ArrayList<Hand>();
		hands.add(handArr[0]);
		hands.add(handArr[1]);
		hands.add(handArr[2]);
//		hands.add(handArr[3]);
		return hands;

	}

	public Hand getH0() {
		return handArr[0];
	}

	public void setH0(Hand h0) {
		this.handArr[0] = h0;
	}

	public Hand getH1() {
		return handArr[1];
	}

	public void setH1(Hand h1) {
		this.handArr[1] = h1;
	}

	public Hand getH2() {
		return handArr[2];
	}

	public void setH2(Hand h2) {
		this.handArr[2] = h2;
	}

	public Hand getH3() {
		return handArr[3];
	}

	public void setH3(Hand h3) {
		this.handArr[3] = h3;
	}


}
