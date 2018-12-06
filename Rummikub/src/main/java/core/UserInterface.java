package core;

public interface UserInterface {
	public void message(String mes);
	
	public char response(String mes);
	
	public void playerTurn(char player);
	
	public void displayMeld(Meld m);

	public void displayTable(Table t);
	
	public void outcome(char out);
}