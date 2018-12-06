package core;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {

	private ArrayList<Meld> arr;

	public Table(){
		arr = new ArrayList<Meld>();
	}
	
	public Table(Meld[] arr){
		this.arr = new ArrayList<Meld>(Arrays.asList(arr));
	}

	public Table(ArrayList<Meld> arr){
		this.arr = new ArrayList<Meld>();

		for(Meld m: arr) {
			this.arr.add(m);
		}
	}
	
	public int indexOf(Meld m) {
		return arr.indexOf(m);
	}
	
	public Meld getAt(int i) {
		Meld buf = null;
		try {
			buf = arr.get(i);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return buf;
	}
	
	public ArrayList<Meld> getTable(){
		return arr;
	}
	
	public Meld remove(int i) {
		Meld buf = null;
		try {
			buf = arr.remove(i);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		return buf;
	}
	
	public boolean remove(Meld t) {
		return arr.remove(t);
	}
	
	public void add(Meld t) {
		arr.add(t);
	}
	
    @Override
    public String toString(){
		String str = "";
		if(arr.size() == 0) {
			return str;
		}

		for(Meld t : arr) {
			str += t.toString() + " ";
		}

		return str.trim();
    }
	
    @Override
    public boolean equals(Object t){
    	if(((Table)t).size() != arr.size()) {
    		return false;
    	}

    	for(int i=0; i < arr.size(); i++) {

    		if(!((Table)t).getAt(i).equals(arr.get(i))) {
    			return false;
    		}

    	}
    	
    	return true;
    }

	public int size() {
		return arr.size();
	}
}
