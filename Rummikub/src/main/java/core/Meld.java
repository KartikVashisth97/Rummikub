package core;
import java.util.ArrayList;
import java.util.Arrays;

public class Meld {
	//for future java projects when a data structure is needed should use composition
	//public class Meld<Tile> extends ArrayList<Tile>

	private ArrayList<Tile> arr;

	public Meld(){
		arr = new ArrayList<Tile>();
	}

	public Meld(Meld m){
		arr = new ArrayList<Tile>();
		for(Tile t: m.getMeld()) {
			arr.add(t);
		}
	}

	public Meld(Tile[] arr){
		this.arr = new ArrayList<Tile>(Arrays.asList(arr));
	}

	public Meld(ArrayList<Tile> arr){
		this.arr = new ArrayList<Tile>(arr);
	}

	public ArrayList<Tile> getMeld(){
		return arr;
	}

	public int indexOf(Tile t) {
		return arr.indexOf(t);
	}

	public Tile getAt(int i) {
		Tile buf = null;
		try {
			buf = arr.get(i);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return buf;
	}

	public Tile remove(int i) {
		Tile buf = null;
		try {
			buf = arr.remove(i);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return buf;
	}

	public boolean remove(Tile t) {
		return arr.remove(t);
	}

	public void add(Tile t) {
		arr.add(t);
	}

	public void add(Meld m) {
		arr.addAll(m.getMeld());
	}
	public void addFront(Meld m) {
		arr.addAll(0,m.getMeld());
	}
	public void addFront(Tile t) {
		arr.add(0,t);
	}

	public boolean checkFrontAdd(Meld m) {
		if(m.getAt(0).getValue().getVal() < getAt(0).getValue().getVal()) {
			return true;
		}
		return false;
	}

	public int totalMeld() {
		int total = 0;
		if(arr.size() == 0) {
			return total;
		}

		for(Tile t : arr) {
			total += t.getValue().getVal();
		}
		return total;
	}

    @Override
    public String toString(){
		String str = "{";
		if(arr.size() == 0) {
			return str;
		}

		for(Tile t : arr) {
			str += t.toString() + " ";
		}
		str = str.trim() + "}";

		return str;
    }

    @Override
    public boolean equals(Object t){
    	if(((Meld)t).size() != arr.size()) {
    		return false;
    	}

    	for(int i=0; i < arr.size(); i++) {

    		if(!((Meld)t).getAt(i).equals(arr.get(i))) {
    			return false;
    		}

    	}

    	return true;
    }

//	public Tile setAt(int i, Tile t) {
//		Tile buf = null;
//		try {
//			buf = arr.set(i, t);
//		} catch (IndexOutOfBoundsException e) {
//			e.printStackTrace();
//		}
//
//		return buf;
//	}

	public int size() {
		return arr.size();
	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean validMeld() {
		if(typeMeld() == 's') {
			Tile.value val = arr.get(0).getValue();
			Tile.colour[] colArr = {arr.get(0).getColour(),null,null,null};

			if(arr.size() > 4) {
				return false;
			}

			for(int i = 1; i < arr.size(); i++) {
				if(arr.get(i).getValue() != val) {
					return false;
				}
				if(arr.contains(arr.get(i).getColour())) {
					return false;
				}
				colArr[i] = arr.get(i).getColour();
			}
			return true;
		}

		if(typeMeld() == 'r') {
			Tile buf = arr.get(0);
			Tile.colour col = buf.getColour();

			for(int i = 1; i < arr.size(); i++) {
				if(arr.get(i).getColour() != col) {
					return false;
				}
				if(arr.get(i).getValue().getVal() -1 != buf.getValue().getVal()) {
					return false;
				}
				buf = arr.get(i);
			}
			return true;
		}

		return false;
	}

	public char typeMeld() {
		//{r : run | s : set | n : none}
		if(arr.size() < 3) {
			return 'n';
		}

		if(arr.get(0).getValue().getVal() == arr.get(1).getValue().getVal() && arr.get(0).getColour() != arr.get(1).getColour() ) {
			return 's';
		}
		if(arr.get(0).getValue().getVal() + 1 == arr.get(1).getValue().getVal() && arr.get(0).getColour() == arr.get(1).getColour() ) {
			return 'r';
		}

		return 'n';
	}

}
