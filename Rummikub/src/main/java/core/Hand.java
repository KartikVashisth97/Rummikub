
package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Hand {

		private ArrayList<Tile> hand;

		public Hand() {
			hand = new ArrayList<Tile>();
		}

		public Hand(Tile[] arr){
			this.hand = new ArrayList<Tile>(Arrays.asList(arr));
		}

		public class ValueSort implements Comparator<Tile> {
			public int compare(Tile x, Tile y) {
				return x.getValue().getVal() - y.getValue().getVal();
			}
		}

		public ArrayList<Tile> getTiles() {
			return this.hand;
		}

		public Tile getTile(int index) {
			return this.hand.get(index);
		}

		public int indexOf(Tile t) {
			return this.hand.indexOf(t);
		}

		public void addTileToHand(Tile newTile) {
			hand.add(newTile);
		}

		public int sizeOfHand() {
			return hand.size();
		}

		public boolean isEmpty() {
			return hand.isEmpty();
		}

		public void playTileFromHand(Tile tileToPlay) {

			hand.remove(tileToPlay);
			hand.trimToSize();
		}

		public ArrayList<Tile> sortByValueColor() {
			ArrayList<Tile> handDuplicate;
			ArrayList<Tile> handR = new ArrayList<Tile>();
			ArrayList<Tile> handB = new ArrayList<Tile>();
			ArrayList<Tile> handG = new ArrayList<Tile>();
			ArrayList<Tile> handO = new ArrayList<Tile>();
			handDuplicate = hand;

			for(Tile t : handDuplicate) {
				if (t.getColour().getCol() == 'R') {
					handR.add(t);
				} else if (t.getColour().getCol() == 'B') {
					handB.add(t);
				} else if (t.getColour().getCol() == 'G') {
					handG.add(t);
				} else if (t.getColour().getCol() == 'O') {
					handO.add(t);
				}
			}

			Collections.sort(handR, new ValueSort());
			Collections.sort(handB, new ValueSort());
			Collections.sort(handG, new ValueSort());
			Collections.sort(handO, new ValueSort());

			handR.addAll(handB);
			handR.addAll(handG);
			handR.addAll(handO);
			return handR;
		}

		public ArrayList<ArrayList<Tile>> sortByValueColorArrays() {
			ArrayList<Tile> handDuplicate;
			ArrayList<Tile> handR = new ArrayList<Tile>();
			ArrayList<Tile> handB = new ArrayList<Tile>();
			ArrayList<Tile> handG = new ArrayList<Tile>();
			ArrayList<Tile> handO = new ArrayList<Tile>();
			handDuplicate = hand;

			for(Tile t : handDuplicate) {
				if (t.getColour().getCol() == 'R') {
					handR.add(t);
				} else if (t.getColour().getCol() == 'B') {
					handB.add(t);
				} else if (t.getColour().getCol() == 'G') {
					handG.add(t);
				} else if (t.getColour().getCol() == 'O') {
					handO.add(t);
				}
			}

			Collections.sort(handR, new ValueSort());
			Collections.sort(handB, new ValueSort());
			Collections.sort(handG, new ValueSort());
			Collections.sort(handO, new ValueSort());

			ArrayList<ArrayList<Tile>> arr = new ArrayList<ArrayList<Tile>>();
			arr.add(handR);
			arr.add(handB);
			arr.add(handG);
			arr.add(handO);
			return arr;
		}

		@Override
		public String toString(){
			String str = "{";
			if(sortByValueColor().size() == 0) { return str; }
			for(Tile t : sortByValueColor()) { str += t.toString() + " "; }
			str = str.trim() + "}";
			return str;
		}
}
