package core;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
//		File file = new File("src/test/resources/testInit");
		Game g = new Game();
//		g.init(file);
		g.init(primaryStage);
		g.start();
		System.out.println("done");	
	}

}
