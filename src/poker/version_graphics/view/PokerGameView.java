package poker.version_graphics.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.PokerGameModel;

import java.awt.*;
import java.awt.Menu;


public class PokerGameView {

	private FlowPane players;
	private ControlArea controls;
	private PokerGameModel model;
	private Stage stage;
	private Optionbar options;
	private Scene scene;
	private PlayerPane pp;


	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		this.stage = stage;
		this.options = options;
		this.controls = controls;
		this.scene = scene;
		this.players = players;
		this.pp = pp;

		options = new Optionbar();
		controls = new ControlArea();



		// Create all of the player panes we need, and put them into an HBox

		players = new FlowPane();
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			players.getChildren().add(pp);
		}
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		BorderPane root = new BorderPane(); // Put players and controls into a BorderPane
		root.setTop(options);
		root.setCenter(players);
		root.setBottom(controls);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screensize.getWidth();
		double height = screensize.getHeight();
		stage.setResizable(false); // Disallow resizing - which is difficult to get right with images
		players.setPrefSize(width,height/2);
		players.setMinSize(800,500);
		players.setMaxSize(1200,500);
		players.setAlignment(Pos.CENTER);
		players.setHgap(20);
		players.setVgap(20);

		players.setPadding(new Insets(10,0,10,0));
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());

        stage.setTitle("Poker Project");
        stage.setScene(scene);
        stage.show();







	}

	public void createGame(){

		getStage().close();
		getStage().setTitle("Pokerbooooi");
		getStage().setScene(getScene());
		getStage().show();

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

	public Button getAddPlayer() {
		return controls.addPlayer;
	}

	public Button getRemPlayer() {
		return controls.remPlayer;
	}

	public MenuItem getAutoshuffle(){
		return options.getAutoshuffle();
	}

	public void Toggleautoshuffleview(){
		controls.toggleAutoshuffle();
	}
	public Scene getScene(){
		return scene;
	}
	public Stage getStage(){
		return stage;
	}
	public FlowPane getPlayers() {
		return players;
	}
	public PlayerPane getPp() { return pp; }
}
