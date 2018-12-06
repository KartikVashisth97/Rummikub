package core;


import com.sun.javafx.tk.Toolkit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Paint;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class GUI implements UserInterface {

	Scanner userInput = new Scanner(System.in);

	//MVC cannot be accomplished with javafx as to profs email
	private double width = 1000;
	private double height = 650;
	Pane pane;
	public GUI(Pane p){
		pane = p;
	}
	Timer timer = new Timer();

	public StrategyHuman sh;
	public Game ga;

	String lastMessage="";
	public String eventResult="";
	int currentPlayerID=0;
	public int playerScores[] = {14,14,14,0};
	private final Object PAUSE_KEY = new Object();

	public  void setStrategyHuman(StrategyHuman sh)
	{
		this.sh=sh;
	}
	public  void setGame(Game ga)
	{
		this.ga=ga;
	}

	//System.out.print("Helper Functions");
	public void boardInit(Stage primaryStage)
	{
		pane = new Pane();
		pane.setStyle("-fx-background-color: grey;");

		CreatePanels();

		BoardView(3);	// 1-Basic View // 2-Index View // 3-GamePlay View

		CreateStockCards();
		SetScores();
		ClearBoardCards();
		ClearPlayerCards();

		primaryStage.setTitle("Rummikub");
		primaryStage.setScene(new Scene(pane, width, height));
	}

	void CreatePanels()
	{
		Rectangle board = new Rectangle(0,35,700,415);board.setFill(Color.RED);
		Rectangle scores = new Rectangle(700,0,300,500);scores.setFill(Color.BROWN);
		Rectangle playerCards = new Rectangle(0,450,1000,130);playerCards.setFill(Color.GREEN);
		Rectangle stock = new Rectangle(700,300,300,150);stock.setFill(Color.YELLOW);
		Rectangle msg = new Rectangle(0,580,1000,40);msg.setFill(Color.BLUE);
		pane.getChildren().add(scores);
		pane.getChildren().add(playerCards);
		pane.getChildren().add(board);
		pane.getChildren().add(stock);
		//pane.getChildren().add(msg);

		Text title1 = new Text(5,25, "Table");title1.setFont(new Font(width * height * 0.00004));
		Text title2 = new Text(705,25,"Player Scores");title2.setFont(new Font(width * height * 0.00004));
		Text title3 = new Text(5,475,"Player1 Hand");title3.setFont(new Font(width * height * 0.00004));
		Text title4 = new Text(705,325, "Stock");title4.setFont(new Font(width * height * 0.00004));
		Text title5 = new Text(5,605, "Message: ");title5.setFont(new Font(width * height * 0.00003));

		Text title6 = new Text(880,325, "Total=106");title6.setFont(new Font(width * height * 0.00003));
		Text title7 = new Text(880,475, "Time: 1:26");title7.setFont(new Font(width * height * 0.00003));
		Text title8 = new Text(150,605, "Player Human's turn.");title8.setFont(new Font(width * height * 0.00002));title8.setFill(Color.WHITE);

		pane.getChildren().add(title1);
		pane.getChildren().add(title2);
		pane.getChildren().add(title3);
		pane.getChildren().add(title4);
		//pane.getChildren().add(title5);
		pane.getChildren().add(title6);
		pane.getChildren().add(title7);
		//pane.getChildren().add(title8);

		message1("_____________________________________");
		message2("_____________________________________");
		message3("_____________________________________");
		message4("_____________________________________");

		CreateInputBtn();

		//System.out.println(board.getWidth()+" "+ board.getHeight());
	}

	void BoardView(int type)
	{
		if(type==1) // Basic View
		{
			BoardCardsBasic();
			PlayerCardsBasic();
		}
		if(type==2) // Index View
		{
			BoardCardsIndexes();
			PlayerCardsIndexes();
		}
		if(type==3) // GamePlay View
		{
			BoardCardsGamePlay();
			PlayerCardsGamePlay();
		}
	}

	void BoardCardsBasic()
	{
		int cardClrID=0;

		int cardW=getCardW();
		int cardH=getCardH();
		int gapX=10;
		int gapY=10;

		int X=10, Y=40;
		int x=X, y=Y;

		for(int i=0 ; i< 106 ; i++)
		{
			if(i%13==0 && i>10)  //Next Row
			{
				x=X;
				y+=cardH+gapY;
				cardClrID++; cardClrID%=4;
			}

			String cardVal ="1";if(i!=0)cardVal =(i%13+1)+"";
			if(i>103){cardVal=".O.";cardClrID=1;}

			Color cardClr[] = {Color.RED, Color.BLUE, Color.GREEN ,Color.ORANGE};
			CreateCard(x,y,cardVal,cardClr[cardClrID]);

			x+=cardW+gapX; // Next Column
		}
	}

	void BoardCardsIndexes()
	{
		int cardClrID=0;

		int cardW=getCardW();
		int cardH=getCardH();
		int gapX=10;
		int gapY=10;

		int X=10, Y=40;
		int x=X, y=Y;

		for(int i=0 ; i< 106 ; i++)
		{
			if(i%13==0 && i>10)  //Next Row
			{
				x=X;
				y+=cardH+gapY;
			}

			String cardVal =(i)+"";

			// Card Bg
			Color cardClrBg = Color.YELLOW;
			cardClrBg = Color.WHITE;
			Rectangle hand = new Rectangle(x,y,cardW,cardH);
			hand.setFill(cardClrBg);
			pane.getChildren().add(hand);

			// Card Text
			Color cardClr[] = {Color.RED, Color.BLUE, Color.GREEN ,Color.ORANGE};
			Text title1 = new Text(x,y+15, cardVal);
			title1.setFont(new Font(width * height * 0.00002));
			title1.setFill(cardClr[cardClrID]);
			pane.getChildren().add(title1);

			x+=cardW+gapX; // Next Column
		}
	}

	void BoardCardsGamePlay()
	{
		//System.out.print("For i="+i+ " x="+ x+" y="+y +" xX="+ ind[0]+" Y="+ind[1]+"\n");

		PutBoardCardAt(1,"1",Color.RED);
		PutBoardCardAt(2,"2",Color.RED);
		PutBoardCardAt(3,"3",Color.RED);

		PutBoardCardAt(13,"2",Color.BLUE);
		PutBoardCardAt(14,"3",Color.BLUE);
		PutBoardCardAt(15,"4",Color.BLUE);

		PutBoardCardAt(65,"5",Color.ORANGE);
		PutBoardCardAt(66,"6",Color.ORANGE);
		PutBoardCardAt(67,"7",Color.ORANGE);
		PutBoardCardAt(68,"8",Color.ORANGE);
		PutBoardCardAt(69,"9",Color.ORANGE);
		PutBoardCardAt(70,"10",Color.ORANGE);

		PutBoardCardAt(30,"5",Color.GREEN);
		PutBoardCardAt(31,"6",Color.GREEN);
		PutBoardCardAt(32,"7",Color.GREEN);
		PutBoardCardAt(33,"8",Color.GREEN);
		PutBoardCardAt(34,"9",Color.GREEN);
		PutBoardCardAt(35,"10",Color.GREEN);
		PutBoardCardAt(36,"11",Color.GREEN);
		PutBoardCardAt(37,"12",Color.GREEN);

		PutBoardCardAt(83,"5",Color.RED);
		PutBoardCardAt(84,"5",Color.BLUE);;
		PutBoardCardAt(85,"5",Color.ORANGE);
		PutBoardCardAt(86,"5",Color.GREEN);

		PutBoardCardAt(59,"2",Color.RED);
		PutBoardCardAt(60,".O.",Color.BLUE);;
		PutBoardCardAt(61,"2",Color.ORANGE);
		PutBoardCardAt(62,"2",Color.GREEN);
	}

	void PlayerCardsBasic()
	{
		int cardClrID=0;
		int cardW=getCardW();
		int cardH=getCardH();
		int gapX=10;
		int gapY=10;

		int X=10, Y=500;
		int x=X, y=Y;

		for(int i=0 ; i< 52 ; i++)
		{
			if(i%26==0 && i>10)
			{
				x=X;
				y+=cardH+gapY;
			}
			if(i%13==0 && i>10)
			{
				cardClrID++; cardClrID%=4;
			}

			String cardVal ="1";if(i!=0)cardVal =(i%13+1)+"";

			Color cardClr[] = {Color.RED, Color.BLUE, Color.GREEN ,Color.ORANGE};
			CreateCard(x,y,cardVal,cardClr[cardClrID]);

			x+=cardW+gapX;
		}
	}

	void PlayerCardsIndexes()
	{
		int cardClrID=0;
		int cardW=getCardW();
		int cardH=getCardH();
		int gapX=10;
		int gapY=10;

		int X=10, Y=485;
		int x=X, y=Y;

		for(int i=0 ; i< 52 ; i++)
		{
			if(i%26==0 && i>10)
			{
				x=X;
				y+=cardH+gapY;
			}
			String cardVal =(i)+"";

			// Card Bg
			Color cardClrBg = Color.YELLOW;
			cardClrBg = Color.WHITE;
			Rectangle hand = new Rectangle(x,y,cardW,cardH);
			hand.setFill(cardClrBg);
			pane.getChildren().add(hand);

			// Card Text
			Color cardClr[] = {Color.RED, Color.BLUE, Color.GREEN ,Color.ORANGE};
			Text title1 = new Text(x,y+15, cardVal);
			title1.setFont(new Font(width * height * 0.00002));
			title1.setFill(cardClr[cardClrID]);
			pane.getChildren().add(title1);

			x+=cardW+gapX;
		}
	}

	void PlayerCardsGamePlay()
	{
		PutPlayerCardAt(1,"1",Color.RED);
		PutPlayerCardAt(2,"2",Color.RED);
		PutPlayerCardAt(3,"5",Color.ORANGE);
		PutPlayerCardAt(5,"3",Color.RED);
		PutPlayerCardAt(6,"2",Color.BLUE);
		PutPlayerCardAt(7,"3",Color.BLUE);
		PutPlayerCardAt(8,"5",Color.ORANGE);
		PutPlayerCardAt(9,"4",Color.BLUE);
		PutPlayerCardAt(11,"6",Color.ORANGE);
		PutPlayerCardAt(12,"5",Color.GREEN);
		PutPlayerCardAt(13,"5",Color.ORANGE);
		PutPlayerCardAt(15,"2",Color.RED);
		PutPlayerCardAt(16,"5",Color.ORANGE);
		PutPlayerCardAt(25,"11",Color.GREEN);
		PutPlayerCardAt(26,"12",Color.GREEN);
		PutPlayerCardAt(27,"5",Color.RED);
		PutPlayerCardAt(39,"5",Color.BLUE);
		PutPlayerCardAt(45,"2",Color.RED);;
		PutPlayerCardAt(46,"5",Color.ORANGE);
		PutPlayerCardAt(47,"5",Color.GREEN);

	}

	//_______________________________________ GUI FUNCTIONS

	void CreateStockCards()
	{
		int cardClrID=0;

		int cardW=50;
		int cardH=70;
		int gapX=10;
		int gapY=10;

		int X=735, Y=360;
		int x=X, y=Y;

		for(int i=0 ; i< 4 ; i++)
		{
			// Card Bg
			Color cardClrBg = Color.YELLOW;
			cardClrBg = Color.WHITE;
			Rectangle hand = new Rectangle(x,y,cardW,cardH);
			hand.setFill(cardClrBg);
			pane.getChildren().add(hand);

			// Card Text
			//Color cardClr[] = {Color.RED, Color.BLACK, Color.GREEN ,Color.BLUE};
			Color cardClr[] = {Color.RED, Color.BLUE, Color.GREEN ,Color.ORANGE};
			hand.setFill(cardClr[i]);

			x+=cardW+gapX; // Next Column
		}
	}

	void SetScores()
	{
		int cardClrID=0;

		int cardW=100;
		int cardH=100;
		int gapX=10;
		int gapY=10;

		int X=735, Y=50;
		int x=X, y=Y;

		for(int i=0 ; i< 4 ; i++)
		{
			if(i%2==0 && i>1)
			{
				x=X;
				y+=cardH+gapY;
			}

			// Card Bg
			Color cardClrBg = Color.LIGHTCORAL;
			//cardClrBg = Color.WHITE;
			Rectangle hand = new Rectangle(x,y,cardW,cardH);
			hand.setFill(cardClrBg);
			pane.getChildren().add(hand);

			// Card Text
			Color cardClr[] = {Color.RED, Color.BLACK, Color.GREEN ,Color.BLUE};
			String player[] = {"Human","Player 1","Player 3","Player 2",};
			Text title1 = new Text(x,y+15, player[i]);
			title1.setFont(new Font(width * height * 0.00003));
			title1.setFill(cardClr[cardClrID]);
			pane.getChildren().add(title1);


			// Card Text
			Text title2 = new Text(x,y+35, "Score: ");
			title2.setFont(new Font(width * height * 0.00002));
			//title2.setFill(cardClr[cardClrID]);
			pane.getChildren().add(title2);

			// Card Text
			Text title3 = new Text(x,y+75, playerScores[i]+"");
			title3.setFont(new Font(width * height * 0.00006));
			//title2.setFill(cardClr[cardClrID]);
			pane.getChildren().add(title3);

			x+=cardW+gapX; // Next Column
		}
	}

	//_______________________________________ HELPER FUNCTIONS

	void CreateCard(int x,int y,String cardVal,Color cardClr)
	{
		int cardW=getCardW();
		int cardH=getCardH();

		// Card Bg
		Color cardClrBg = Color.YELLOW;
		cardClrBg = Color.WHITE;
		Rectangle hand = new Rectangle(x,y,cardW,cardH);
		hand.setFill(cardClrBg);
		pane.getChildren().add(hand);

		// Card Text
		//Color cardClr[] = {Color.RED, Color.BLACK, Color.GREEN ,Color.BLUE};
		Text title1 = new Text(x,y+15, cardVal);
		title1.setFont(new Font(width * height * 0.00003));
		title1.setFill(cardClr);
		pane.getChildren().add(title1);
	}

	void PutBoardCardAt(int i,String cardVal,Color cardClr)
	{
		int gapX=10;
		int gapY=10;
		int X=10, Y=40;
		int ind[] = getCardCoordinates(X,Y,gapX,gapY,13,i);
		int x=ind[0]; int y=ind[1];

		int cardW=getCardW();
		int cardH=getCardH();

		// Card Bg
		Color cardClrBg = Color.YELLOW;
		cardClrBg = Color.WHITE;
		Rectangle hand = new Rectangle(x,y,cardW,cardH);
		hand.setFill(cardClrBg);
		pane.getChildren().add(hand);

		// Card Text
		//Color cardClr[] = {Color.RED, Color.BLACK, Color.GREEN ,Color.BLUE};

		Text title1 = new Text(x,y+15, cardVal);
		title1.setFont(new Font(width * height * 0.00003));
		title1.setFill(cardClr);
		pane.getChildren().add(title1);
	}

	void PutPlayerCardAt(int i,String cardVal,Color cardClr)
	{
		int gapX=10;
		int gapY=10;
		int X=10, Y=485;
		int ind[] = getCardCoordinates(X,Y,gapX,gapY,26,i);
		int x=ind[0]; int y=ind[1];

		int cardW=getCardW();
		int cardH=getCardH();

		// Card Bg
		Color cardClrBg = Color.YELLOW;
		cardClrBg = Color.WHITE;
		Rectangle hand = new Rectangle(x,y,cardW,cardH);
		hand.setFill(cardClrBg);
		pane.getChildren().add(hand);

		// Card Text
		//Color cardClr[] = {Color.RED, Color.BLACK, Color.GREEN ,Color.BLUE};

		Text title1 = new Text(x,y+15, cardVal);
		title1.setFont(new Font(width * height * 0.00003));
		title1.setFill(cardClr);
		pane.getChildren().add(title1);
	}

	void ClearBoardCards()
	{
		Rectangle board = new Rectangle(0,35,700,415);board.setFill(Color.RED);
		pane.getChildren().add(board);
	}

	void ClearPlayerCards()
	{
		Rectangle playerCards = new Rectangle(0,450,1000,130);playerCards.setFill(Color.GREEN);
		pane.getChildren().add(playerCards);
	}

	int getCardW()
	{
		return 27;
	}

	int getCardH()
	{
		return 35;
	}

	int[] getCardCoordinates(int X, int Y,int gapX,int gapY,int rowL, int i )
	{
		gapX+= getCardW();
		gapY+= getCardH();
		//i+=1;


		int ind[]= new int[]{0,0};

		int x=X,y=Y;


		if(i!=0)
		{
			x +=(i%rowL)*gapX;
		}
		y += ((i/rowL)*gapY);


		ind[0]=x;ind[1]=y;

		return ind;
	}

	//________________________________________________________________________-- UI

	public void boardInit1(Stage primaryStage) {
		pane = new Pane();
		pane.setStyle("-fx-background-color: grey;");
		Rectangle board = new Rectangle(width * 0.25,height * 0.08,width * 0.73, height * 0.65);
		board.setFill(Color.SADDLEBROWN);
		Rectangle scores = new Rectangle(width * 0.02,height * 0.08,width * 0.22, height * 0.65);
		scores.setFill(Color.TAN);
		Rectangle controls = new Rectangle(width * 0.02,height * 0.74, width * 0.22, height * 0.24);
		controls.setFill(Color.TAN);
		Rectangle hand = new Rectangle(width * 0.25, height * 0.74, width * 0.73, height * 0.24);
		hand.setFill(Color.TAN);

		Text title = new Text(width * 0.02, height * 0.07, "Rummikub");
		title.setFont(new Font(width * height * 0.00006));

		pane.getChildren().add(title);
		pane.getChildren().add(scores);
		pane.getChildren().add(controls);
		pane.getChildren().add(board);
		pane.getChildren().add(hand);
		primaryStage.setTitle("Rummikub");
		primaryStage.setScene(new Scene(pane, width, height));
	}

	public String message1(String mes) {
		//System.out.println("message1" + mes);

		Rectangle msg = new Rectangle(0,580,400,30);msg.setFill(Color.BLUE);

		Text title5 = new Text(5,600, "Player: ");title5.setFont(new Font(width * height * 0.00003));
		//Text title8 = new Text(150,600, "O"+mes+"O");
		Text title8 = new Text(100,600, mes);
		title8.setFont(new Font(width * height * 0.00002));title8.setFill(Color.WHITE);

		pane.getChildren().add(msg);
		pane.getChildren().add(title5);
		pane.getChildren().add(title8);

		return mes;
	}
	public String message3(String mes) {

		//System.out.println("message2" + mes);

		Rectangle msg = new Rectangle(0,610,400,30);msg.setFill(Color.GREEN);

		Text title5 = new Text(5,630, "Message: ");title5.setFont(new Font(width * height * 0.00003));
		Text title8 = new Text(100,630, mes);
		title8.setFont(new Font(width * height * 0.00002));title8.setFill(Color.WHITE);

		pane.getChildren().add(msg);
		pane.getChildren().add(title5);
		pane.getChildren().add(title8);
		return mes;
	}
	public String message2(String mes) {
		//System.out.println("message3" + mes);

		Rectangle msg = new Rectangle(400,580,1000,30);msg.setFill(Color.RED);

		Text title5 = new Text(405,600, "Input: ");title5.setFont(new Font(width * height * 0.00003));
		Text title8 = new Text(500,600, mes);
		title8.setFont(new Font(width * height * 0.00002));title8.setFill(Color.WHITE);

		pane.getChildren().add(msg);
		pane.getChildren().add(title5);
		pane.getChildren().add(title8);
		return mes;
	}
	public String message4(String mes) {

		//System.out.println("message4" + mes);

		Rectangle msg = new Rectangle(400,610,1000,30);msg.setFill(Color.BLUE);

		Text title5 = new Text(405,630, "Tiles: ");title5.setFont(new Font(width * height * 0.00003));
		Text title8 = new Text(500,630, mes);
		title8.setFont(new Font(width * height * 0.00002));title8.setFill(Color.WHITE);

		pane.getChildren().add(msg);
		pane.getChildren().add(title5);
		pane.getChildren().add(title8);

		return mes;
	}

	void CreateInputBtn() {

		int x=150,y=5;
		int x1=580;

		//x=500;y=615;
		//x1=930;

		final TextField tfPost = new TextField("d");
		tfPost.setLayoutX(x);tfPost.setLayoutY(y);tfPost.setPrefWidth(430);
		final Button btTweet = new Button("Enter");
		btTweet.setLayoutX(x1);btTweet.setLayoutY(y);
		pane.getChildren().add(btTweet);
		pane.getChildren().add(tfPost);

		btTweet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				resume();

				eventResult = tfPost.getText();

				System.out.println("lastMessage.: " + lastMessage);

				if(btTweet.getText().equals("Enter"))
				{
					if(lastMessage.contains("Next Player"))
					{
						//btTweet.setText("Player1");
					}
					if(eventResult.equals("d"))
					{
						//sh.playOrDraw(eventResult);
						btTweet.setText("Player1");
					}
					if(eventResult.equals("p"))
					{
						//sh.playOrDraw(eventResult);
					}
					message2("");
				}
				else if(btTweet.getText().equals("Player1"))
				{
					currentPlayerID++;
					currentPlayerID%=3;

					btTweet.setText("Player2");
					message2("");message3("");
				}
				else if(btTweet.getText().equals("Player2"))
				{
					currentPlayerID++;
					currentPlayerID%=3;

					btTweet.setText("Me");
					message2("");message3("");
				}
				else if(btTweet.getText().equals("Me"))
				{
					currentPlayerID++;
					currentPlayerID%=3;

					btTweet.setText("Enter");
					//message2("");//
					message3("");
				}
				SetScores();
			}
		});
	}

	public String query(String mes, String[] choices) {

		pause();

		/*int counter = 0;
		pane.getChildren().clear();
		Text qStr = new Text(width * 0.022, height * 0.100, mes);
		qStr.setFont(new Font(width * height * 0.00002));
		qStr.setWrappingWidth(width * 0.215);
		pane.getChildren().add(qStr);

		for (String str : choices) {
			final Button button = new Button(str);
			button.setWrapText(true);
			button.setMinWidth(width * 0.220);
			button.setMinHeight(height * 0.100);
			button.setLayoutX(width * 0.022);
			button.setLayoutY(height * 0.180 + (counter * height * 0.100));

			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					//GUI.this.eventResult = button.getText();
					resume();
				}
			});
		}*/

		return eventResult;
	}

	private void pause() {
		//Platform.enterNestedEventLoop(PAUSE_KEY);
		Toolkit.getToolkit().enterNestedEventLoop(PAUSE_KEY);
	}

	private void resume() {
		//Platform.exitNestedEventLoop(PAUSE_KEY, null);
		Toolkit.getToolkit().exitNestedEventLoop( PAUSE_KEY,null);
	}

	//________________________________________________________________________-- TODO

	public void message(String mes) {
		// TODO Auto-generated method stub

		//message1(mes);
		/*message2(mes);
		message3(mes);
		message4(mes);*/

		System.out.println("Message: " + mes);

		if(mes.contains("turn"))
		{
			message1(mes);
		}
		else if(mes.contains("Play Table") ||mes.contains("Create")
			 )
		{
			message2(mes);
		}
		else if(mes.contains("No more cards in deck") ||
				mes.contains("Meld need to total") ||
				mes.contains("drew") ||
				mes.contains("Tile not in hand") ||
				mes.contains("Invalid") ||
				mes.contains("Tried to draw") ||
				mes.contains("Error no tilesl"))
		{
			message3(mes);
		}
		else if( mes.contains("}"))
		{
			message4(mes);
		}
	}

	public String responseStr(String mes) {

		System.out.println("responseStr" + mes);
		if(mes.contains("Error") || mes.contains("Invalid"))
		{
			message3(mes);
		}
		message2(mes);

		String s = "R1 B1 G1";
		//s += userInput.nextLine();
		s=query(mes, new String[]{"p","d"} );

		return s;
	}

	public char response(String mes) {
		// TODO Auto-generated method stub

		lastMessage=mes;

		System.out.println("Respons: " + mes);
		message2(mes);

		char u='p';
		//u= userInput.next().charAt(0);
		//userInput.nextLine(); //consume \n
		//userInput.close(); can't close due to System.in being unable to reopen

		String uInput=query(mes, new String[]{"p","d"} );
		if(uInput.equals(""))uInput="d";

		u = uInput.charAt(0);

		return u;
	}

	public void playerTurn(char player) {
		// TODO Auto-generated method stub
		String mes ="";
		switch (player) {
			case 'H':
			case 'h':
				//System.out.println("Player Human's turn.");
				message2("Player Human's turn.");
				mes="Player Human's turn.";
				break;
			case '1':
				//System.out.println("Player P1's turn.");
				message2("Player P1's turn.");
				mes="Player P1's turn.";
				break;
			case '2':
				//System.out.println("Player P2's turn.");
				message2("Player P2's turn.");
				mes="Player P2's turn.";
				break;
			case '3':
				//System.out.println("Player P3's turn.");
				message2("Player P3's turn.");
				mes="Player P3's turn.";
				break;
			default:
				//System.out.println("*Invalid player");
				message2("*Invalid player");
				mes="*Invalid player";
				break;
		}

		System.out.println("playerTurn" + mes);
	}

	public void displayMeld(Meld m) {
		// TODO Auto-generated method stub

		System.out.println("displayMeld" + m.toString());


		System.out.println(m.toString());
		message3(m.toString());

	}

	public void displayTable(Table t) {

		ClearBoardCards();

		int index =0;

		//ArrayList<Tile> tiles = h.getTiles();
		ArrayList<Meld> melds=t.getTable();

		for(int j=0 ; j<melds.size(); j++)
		{
			ArrayList<Tile> tiles=  melds.get(j).getMeld();

			for(int i=0 ; i<tiles.size(); i++) {

				if(index< 106)
				{
					Color cardClr[] = {Color.RED, Color.BLUE, Color.GREEN ,Color.ORANGE};
					int clrId=0;
					char val=tiles.get(i).getColour().getCol();
					if(val=='R')clrId=0;
					else if(val=='B')clrId=1;
					else if(val=='G')clrId=2;
					else if(val=='O')clrId=3;
					PutBoardCardAt(index,tiles.get(i).getValue().getVal()+"",cardClr[clrId]);
					index++;
					//PutPlayerCardAt(i,"1",cardClr[clrId]);
				}

			}
		}
	}

	public void outcome(char out) {
		// TODO Auto-generated method stub

		String mes ="";

		switch (out) {
			case 'H':
			case 'h':
				System.out.println("Human has won!");
				break;
			case '1':
				System.out.println("Player P1's has won!");
				break;
			case '2':
				System.out.println("Player P2's has won!");
				break;
			case '3':
				System.out.println("Player P3's has won!");
				break;
			default:
				System.out.println("*Invalid player");
				break;
		}

		System.out.println("outcome" + mes);
	}

	//______________________________________________________

	public void displayHand(Hand h) {
		// TODO Auto-generated method stub

		ClearPlayerCards();

		int index =0;

		//ArrayList<Tile> tiles = h.getTiles();
		ArrayList<Tile> tiles=h.sortByValueColor();

		for(int i=0 ; i<52; i++)
		{
			if(i< h.sizeOfHand())
			{
				Color cardClr[] = {Color.RED, Color.BLUE, Color.GREEN ,Color.ORANGE};
				int clrId=0;
				char val=h.getTile(i).getColour().getCol();
				if(val=='R')clrId=0;
				else if(val=='B')clrId=1;
				else if(val=='G')clrId=2;
				else if(val=='O')clrId=3;
				PutPlayerCardAt(i,h.getTile(i).getValue().getVal()+"",cardClr[clrId]);
				//PutPlayerCardAt(i,"1",cardClr[clrId]);
			}
			else
			{
				//PutPlayerCardAt(i,"0",Color.GREEN);
			}
		}
	}

}
