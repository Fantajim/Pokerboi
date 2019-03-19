package poker.version_graphics.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.PokerGameModel;

import java.awt.*;
import java.awt.Menu;
import java.util.ArrayList;


public class PokerGameView {

	private FlowPane players;
	private ControlArea controls;
	private Mainmenu mainmenu;
	private PokerGameModel model;
	private Stage stage;
	private Optionbar options;
	private Scene scene;
	private PlayerPane pp;
	private ArrayList<PlayerPane> ppArrayList = new ArrayList<>();

	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		this.stage = stage;
		this.mainmenu = mainmenu;
		this.options = options;
		this.controls = controls;
		this.scene = scene;
		this.players = players;
		this.pp = pp;
		this.ppArrayList = ppArrayList;

		options = new Optionbar();
		mainmenu = new Mainmenu();
		controls = new ControlArea();

		Scene mainscene = new Scene(mainmenu);
		mainscene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());

        stage.setTitle("Poker Project");
        stage.setScene(mainscene);
        stage.show();

		// Create all of the player panes we need, and put them into an HBox

		players = new FlowPane();
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			getPlayers().getChildren().add(pp);
			ppArrayList.add(pp);
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
		players.setMaxSize(1200,500);
		players.setAlignment(Pos.CENTER);
		players.setHgap(20);
		players.setVgap(20);
		players.setPadding(new Insets(10,0,10,0));
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());



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

	public Button getPlayButton(){ return mainmenu.getPlay();}

	public String getSelection(){
		ToggleButton temp = (ToggleButton)mainmenu.radiotoggle.getSelectedToggle();
		return temp.getText();
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

	public ArrayList<PlayerPane> getArrayPp () {
		return ppArrayList;
	}

	public FlowPane getPlayers() {
		return players;
	}
}
