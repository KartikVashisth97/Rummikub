package core;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public abstract class Player implements Observer{
	
	protected String name;
	protected Hand hand; 
	protected Pile pile;
	protected Table table; // player can see table itself
	protected Game game;
	protected boolean initialMeld = true;
	protected ArrayList<Meld> newMelds;
	protected ArrayList<Meld> modMelds;

	public boolean isInitialMeld() {
		return initialMeld;
	}

	public void setInitialMeld(boolean initialMeld) {
		this.initialMeld = initialMeld;
	}

	public Player (){
		newMelds = new ArrayList<Meld>();
		modMelds = new ArrayList<Meld>();
		hand = new Hand(); 
    	table = new Table();
	}
	
	public Player(Hand h) {
		newMelds = new ArrayList<Meld>();
		modMelds = new ArrayList<Meld>();
		hand = h;
		table = new Table();
	}
	
	public Hand getHand() {
		return hand;
	}

	public Table getTable() {
		return table;
	}

	public void update(Observable obs, Object x) {
		Game update = (Game) obs;
		game = update;
		table = update.getTable();
		pile = update.getPile();
		updateHand(update);
	}
	
	protected abstract void updateHand(Game update);
	
	protected abstract void play();
	
	@Override
	public String toString(){
		return name;
	}
	public Tile draw() {
		Tile temp;
		if(!pile.isEmpty()) {
			temp = pile.deal();
			hand.addTileToHand(temp);
		}else{
			return null;
		}
		return temp;
	}
	
	protected String displayPlay() {
		String str = "";
		for(Meld m: newMelds) {
			str += "*" + m + " ";
		}
		for(Meld m: modMelds) {
			str += "!" + m + " ";
		}
		for(Meld m: table.getTable()) {
			if(!newMelds.contains(m) && !modMelds.contains(m)) {
				str += m + " ";
			}
		}
		
		newMelds = new ArrayList<Meld>();
		modMelds = new ArrayList<Meld>();
		str = str.trim();
		return str;
	}
}
