package poker.version_graphics;

import javafx.application.Application;
import javafx.stage.Stage;
import poker.version_graphics.controller.PokerGameController;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.PokerGameView;

public class PokerGame extends Application {
	public static int NUM_PLAYERS = 2;
	PokerGameModel model;
	PokerGameView view;
	PokerGameController controller;
	
    public static void main(String[] args) { launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	// Create and initialize the MVC components

// Create and initialize the MVC components
		model = new PokerGameModel();
		view = new PokerGameView(primaryStage, model);
		controller = new PokerGameController(model, view);

    }

    public static void setNumPlayers(String setplayers){
    	NUM_PLAYERS = Integer.valueOf(setplayers);
	}



}
