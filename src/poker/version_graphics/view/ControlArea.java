package poker.version_graphics.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import poker.version_graphics.model.DeckOfCards;

import java.util.ArrayList;

public class ControlArea extends VBox{
    private DeckLabel lblDeck = new DeckLabel();
    private Region spacer = new Region(); // Empty spacer
    Label result = new Label("Winner is: ");
    Button btnShuffle = new Button("Shuffle");
    Button btnDeal = new Button("Deal");
    Button addPlayer = new Button("+ Player");
    Button remPlayer = new Button("- Player");
    Boolean temp = false;
    private Label decks = new Label("Card Decks: ");
    private static CheckBox classic = new CheckBox("classic");
    private static CheckBox dog = new CheckBox("dog");
    private static CheckBox old = new CheckBox ("old");

    public ControlArea() {
    	super(); // Always call super-constructor first !!
        HBox deckbox = new HBox(classic,dog,old);
        HBox controlbox = new HBox(lblDeck,remPlayer,addPlayer, decks,deckbox, spacer, btnShuffle, btnDeal);
        deckbox.setSpacing(10);
        deckbox.setId("deckbox");
        this.getChildren().addAll(result,controlbox);
    	//this.getChildren().addAll(result,lblDeck,remPlayer,addPlayer, decks,deckbox, spacer, btnShuffle, btnDeal);
        controlbox.setMargin(deckbox, new Insets(0,0,4,-15));
        HBox.setHgrow(spacer, Priority.ALWAYS); // Use region to absorb resizing
        this.setId("controlArea"); // Unique ID in the CSS
        controlbox.setId("controlBox");

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
        if ((old.isSelected()))temp.add(old.getText());
        if (temp.size()== 0)temp.add(classic.getText());
        return temp;


    }

}
