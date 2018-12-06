package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Pile {
	
	private ArrayList<Tile> pile;
	
	public Pile() {
		pile = new ArrayList<Tile>();
		formPile(); formPile();
	}

	public Pile(boolean t) {
		if(t) {
			pile = new ArrayList<Tile>();
			formPile(); formPile();
		}else {
			pile = new ArrayList<Tile>();
		}
	}
	
	public boolean isEmpty() {
		return pile.isEmpty();
	}
	
	public void add(Tile t) {
		pile.add(t);
	}
	
	private void formPile() {
		for(int c = 0; c < 4; c++) {
			for(int v = 0; v < 13; v++) {
				Tile tile = new Tile(Tile.colour.values()[c], Tile.value.values()[v]);
				pile.add(tile); 
			}
		}
	}
	
	public int numTiles() { return pile.size(); }
	
	public Tile deal() {
		if(pile.size() > 0) {
			return pile.remove(0);
		} else {
			return null;
		}
	}
	
	public boolean scramble(){
		Collections.shuffle(pile);
		return true;
	}

	public ArrayList<Tile> sortByValueColor() {
		ArrayList<Tile> pileDuplicate;
		ArrayList<Tile> pileR = new ArrayList<Tile>();
		ArrayList<Tile> pileB = new ArrayList<Tile>();
		ArrayList<Tile> pileG = new ArrayList<Tile>();
		ArrayList<Tile> pileO = new ArrayList<Tile>();
		pileDuplicate = pile;

		for(Tile t : pileDuplicate) {
			if (t.getColour().getCol() == 'R') {
				pileR.add(t);
			} else if (t.getColour().getCol() == 'B') {
				pileB.add(t);
			} else if (t.getColour().getCol() == 'G') {
				pileG.add(t);
			} else if (t.getColour().getCol() == 'O') {
				pileO.add(t);
			}
		}

		Collections.sort(pileR, new ValueSort());
		Collections.sort(pileB, new ValueSort());
		Collections.sort(pileG, new ValueSort());
		Collections.sort(pileO, new ValueSort());

		pileR.addAll(pileB);
		pileR.addAll(pileG);
		pileR.addAll(pileO);
		return pileR;
	}

	public class ValueSort implements Comparator<Tile> {
		public int compare(Tile x, Tile y) {
			return x.getValue().getVal() - y.getValue().getVal();
		}
	}
	public String toStringSort(){
		String str = "{";
		if(sortByValueColor().size() == 0) { return str; }
		for(Tile t : sortByValueColor()) { str += t.toString() + " "; }
		str = str.trim() + "}";
		return str;
	}

	@Override
	public String toString(){
		String str = "{";
		if(pile.size() == 0) {
			return str;
		}

		for(Tile t : pile) {
			str += t.toString() + " ";
		}
		str = str.trim() + "}";

		return str;
	}
}
