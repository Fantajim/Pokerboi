package poker.version_graphics.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class Mainmenu extends BorderPane {

private Label menulabel =new Label ("Pokerboi");
private Label players_label = new Label("Players:  ");
final ToggleGroup radiotoggle = new ToggleGroup();
private RadioButton one = new RadioButton("1");
private RadioButton two = new RadioButton("2");
private  RadioButton three = new RadioButton("3");
private RadioButton four = new RadioButton("4");
private Button play = new Button("Play");
private Region spacer1 = new Region();
private Label decks = new Label("Card Decks: ");
private static CheckBox classic = new CheckBox("classic");
private static CheckBox dog = new CheckBox("dog");
private static CheckBox old = new CheckBox ("old");





    public Mainmenu() {
        super();
        menulabel.setId("menulabel");
        one.setToggleGroup(radiotoggle);
        two.setToggleGroup(radiotoggle);
        three.setToggleGroup(radiotoggle);
        four.setToggleGroup(radiotoggle);
        one.setSelected(true);
        HBox players = new HBox(one,two,three,four);
        HBox playerbox = new HBox(players_label,players,spacer1,play);
        VBox deckvbox = new VBox(classic,dog,old);
        HBox deckhbox = new HBox(decks,deckvbox);
        VBox leftvbox = new VBox(deckhbox, playerbox);
        playerbox.setHgrow(spacer1, Priority.ALWAYS);
        playerbox.setMargin(players,new Insets(0,0,4,0));
        playerbox.setSpacing(2);
        playerbox.setId("center");
        players.setAlignment(Pos.BOTTOM_LEFT);
        playerbox.setAlignment(Pos.BOTTOM_LEFT);
        this.setTop(menulabel);
        this.setCenter(leftvbox);
        this.setMargin(playerbox, new Insets(5,0,0,0));




    }

    public Button getPlay(){return play;}
    public static ArrayList<String> getDecks(){
    ArrayList<String>temp = new ArrayList<>();
    if ((classic.isSelected()))temp.add(classic.getText());
    if ((dog.isSelected()))temp.add(dog.getText());
    if ((old.isSelected()))temp.add(old.getText());

    if (temp.size()==0)temp.add(classic.getText());
        return temp;


    }


}
