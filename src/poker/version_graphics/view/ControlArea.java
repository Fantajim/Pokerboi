package poker.version_graphics.view;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import poker.version_graphics.model.DeckOfCards;

import java.util.ArrayList;

public class ControlArea extends VBox{
    private DeckLabel lblDeck = new DeckLabel();
    private Region spacer = new Region(); // Empty spacer
    private Region spacertop = new Region();
    Label result = new Label("Winner: ");
    Button btnShuffle = new Button("Shuffle");
    Button btnDeal = new Button("Deal");
    Button addPlayer = new Button("+ Player");
    Button remPlayer = new Button("- Player");
    Boolean temp = false;
    Button musicPlay = new Button("\u25B6");
    Button musicStop = new Button ("\u25A0");
    Slider volume = new Slider(0,1,0);
    Media lobbyMusic;
    MediaPlayer lobbyMusicPlayer;
    private Label decks = new Label("Card Decks: ");
    private static CheckBox classic = new CheckBox("classic");
    private static CheckBox dog = new CheckBox("dog");
    private static CheckBox bird = new CheckBox ("bird");

    public ControlArea() {
    	super(); // Always call super-constructor first !!
        volume.setPrefWidth(100);
        HBox mediaBox = new HBox(musicPlay,musicStop,volume);
        mediaBox.setSpacing(5);
        lobbyMusic = new Media(this.getClass().getClassLoader().getResource("poker/images/lobby.mp3").toExternalForm());
        lobbyMusicPlayer = new MediaPlayer(lobbyMusic);
        lobbyMusicPlayer.setAutoPlay(false);
        lobbyMusicPlayer.setVolume(0.0);
        HBox topBox = new HBox(result);
        HBox deckBox = new HBox(classic,dog,bird);
        HBox bottomBox = new HBox(lblDeck,remPlayer,addPlayer, decks,deckBox,mediaBox, spacer, btnShuffle, btnDeal);
        deckBox.setSpacing(10);
        deckBox.setId("deckbox");
        this.getChildren().addAll(topBox,bottomBox);
    	//this.getChildren().addAll(result,lblDeck,remPlayer,addPlayer, decks,deckbox, spacer, btnShuffle, btnDeal);
        bottomBox.setMargin(deckBox, new Insets(0,0,4,-15));
        HBox.setHgrow(spacer, Priority.ALWAYS); // Use region to absorb resizing
        this.setId("controlArea"); // Unique ID in the CSS
        bottomBox.setId("controlBox");

    }
    
    public void linkDeck(DeckOfCards deck) {
    	lblDeck.setDeck(deck);
    }
    public void toggleAutoshuffle(){

       temp = !temp;
       btnShuffle.setDisable(temp);

    }

    public static ArrayList<String> getDecks(){
        ArrayList<String>temp = new ArrayList<>();
        if ((classic.isSelected()))temp.add(classic.getText());
        if ((dog.isSelected()))temp.add(dog.getText());
        if ((bird.isSelected()))temp.add(bird.getText());
        if (temp.size()== 0)temp.add(classic.getText());
        return temp;


    }

}
