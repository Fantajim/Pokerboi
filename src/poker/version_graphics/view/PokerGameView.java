package poker.version_graphics.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.PokerGameModel;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import poker.version_graphics.model.Card;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

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
	private ArrayList<PlayerPane> ppList = new ArrayList<>();

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
			ppList.add(pp);
		}

		//Backgrounds
		Image bg1 = new Image(getClass().getClassLoader().getResourceAsStream("poker/images/background/bg1.jpg"));
		BackgroundImage	background1 = new BackgroundImage(bg1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Image bg2 = new Image(getClass().getClassLoader().getResourceAsStream("poker/images/background/bg2.jpg"));
		BackgroundImage	background2 = new BackgroundImage(bg2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		//Control Area
		controls.setBackground(new Background(background2));
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic

		//root BorderPane (main)
		BorderPane root = new BorderPane(); // Put players and controls into a BorderPane
		root.setTop(options);
		root.setCenter(players);
		root.setBottom(controls);

		//Screen size
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screensize.getWidth();
		double height = screensize.getHeight();
		stage.setResizable(false); // Disallow resizing - which is difficult to get right with images

		//Player FlowPane
		players.setBackground(new Background(background1));
		players.setPrefSize(width,height/2);
		players.setMinSize(1200,500);
		//players.setMaxSize(1200,500);
		players.setAlignment(Pos.CENTER);
		players.setHgap(10);
		players.setVgap(10);
		players.setPadding(new Insets(10,0,10,0));

		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());

        stage.setTitle("Poker Project");
        stage.setScene(scene);
        stage.show();

	}

	//Getters and Shuffle Toggle
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
	public MenuItem getFastShuffle(){
		return options.getFastShuffle();
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
	public Label getResult() {return controls.result;}
	public Button getMusicPlay(){return controls.musicPlay;}
	public Button getMusicStop(){return controls.musicStop;}
	public MediaPlayer getlobbyMusicPlayer(){return controls.lobbyMusicPlayer;}
	public Slider getVolume(){return controls.volume;}
	public ArrayList<PlayerPane> getPpList(){return ppList;}
	public void ToggleFastShuffleview(){
		controls.toggleFastShuffle();
	}

	//PlayerPane resizing
	public void updatePpSize(){
		if(ppList.size() < 5){
			for(PlayerPane pp: ppList){
				pp.setPrefSize(579,236);
			}
		}
		else {
			for(PlayerPane pp: ppList){
				pp.setPrefSize(290,118);
			}
		}
	}
}
