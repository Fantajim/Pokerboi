package poker.version_graphics.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.PokerGameModel;

public class PokerGameView {

	private HBox players;
	private ControlArea controls;
	private Mainmenu mainmenu;
	private PokerGameModel model;

	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		// Create all of the player panes we need, and put them into an HBox
		players = new HBox();
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			players.getChildren().add(pp);
		}




		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		root.setCenter(players);
		root.setBottom(controls);
		
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);

        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
		mainmenu = new Mainmenu();
		Scene mainscene = new Scene(mainmenu);
		mainscene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Project");
        stage.setScene(scene);
        stage.show();

	}


	public PlayerPane getPlayerPane(int i) {
		return (PlayerPane) players.getChildren().get(i);
	}
	
	public Button getShuffleButton() {
		return controls.btnShuffle;
	}
	
	public Button getDealButton() {
		return controls.btnDeal;
	}

	public Button getPlayButton(){ return mainmenu.play;}
	public String getSelection(){
		ToggleButton temp = (ToggleButton)mainmenu.radiotoggle.getSelectedToggle();
		return temp.getText();
	}



}
