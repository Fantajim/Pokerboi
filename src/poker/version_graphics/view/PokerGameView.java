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


public class PokerGameView {

	private FlowPane players;
	private ControlArea controls;
	private Mainmenu mainmenu;
	private PokerGameModel model;
	private Stage stage;
	private Optionbar options;

	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		this.stage = stage;
		this.mainmenu = mainmenu;
		this.options = options;
		this.controls = controls;
		options = new Optionbar();
		mainmenu = new Mainmenu();
		controls = new ControlArea();

		Scene mainscene = new Scene(mainmenu);
		mainscene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());

        stage.setTitle("Poker Project");
        stage.setScene(mainscene);
        stage.show();
	}

	public void createGame(){
		stage.close();

			// Create all of the player panes we need, and put them into an HBox
			players = new FlowPane();
			for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			players.getChildren().add(pp);


			controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic

			// Put players and controls into a BorderPane
			BorderPane root = new BorderPane();
			root.setTop(options);
			root.setCenter(players);
			root.setBottom(controls);
			Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			double width = screensize.getWidth();
			double height = screensize.getHeight();
			stage.setX(0);
			stage.setY(0);
			stage.setResizable(false);
			players.setPrefSize(width,height/2);
			players.setAlignment(Pos.CENTER);
			players.setHgap(20);
			players.setVgap(20);
			players.setPadding(new Insets(10,0,10,0));
			/*stage.setHeight(height/2);*/
			/*stage.maxWidthProperty().bind(stage.widthProperty());*/
			Scene scene = new Scene(root);
			// Disallow resizing - which is difficult to get right with images
			scene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
			stage.setTitle("Pokerbooooi");
			stage.setScene(scene);
			stage.show();
		}

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

}
