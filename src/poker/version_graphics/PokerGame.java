package poker.version_graphics;

import javafx.application.Application;
import javafx.stage.Stage;
import poker.version_graphics.controller.PokerGameController;
import poker.version_graphics.controller.PokerMainController;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.PokerGameView;
import poker.version_graphics.view.PokerMainMenu;

public class PokerGame extends Application {
	public static int NUM_PLAYERS = 4;
	PokerGameModel model;
	PokerGameView view;
	PokerMainMenu main_view;
	PokerGameController controller;
	PokerMainMenu menu;
	PokerMainController main_controller;
	
    public static void main(String[] args) { launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	// Create and initialize the MVC components


    	main_view = new PokerMainMenu(primaryStage);
		main_controller = new PokerMainController(main_view);

    }

    public void startGame(){
		Stage primaryStage = new Stage();

		// Create and initialize the MVC components
		model = new PokerGameModel();
		view = new PokerGameView(primaryStage, model);
		controller = new PokerGameController(model, view);


	}


    public static void setNumPlayers(String setplayers){
    	NUM_PLAYERS = Integer.valueOf(setplayers);
	}



}
