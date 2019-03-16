package poker.version_graphics.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

public class Mainmenu extends VBox {

private Label menulabel =new Label ("Pokerboi");
private Label players_label = new Label("Players:  ");
final ToggleGroup radiotoggle = new ToggleGroup();
private RadioButton one = new RadioButton("1");
private RadioButton two = new RadioButton("2");
private  RadioButton three = new RadioButton("3");
private RadioButton four = new RadioButton("4");
Button play = new Button("Play");
Region spacer1 = new Region();



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
        playerbox.setHgrow(spacer1, Priority.ALWAYS);
        playerbox.setMargin(players,new Insets(0,0,4,-70));
        playerbox.setSpacing(2);
        playerbox.setId("center");
        players.setAlignment(Pos.BOTTOM_LEFT);
        playerbox.setAlignment(Pos.BOTTOM_LEFT);
        this.getChildren().addAll(menulabel,playerbox);
        this.setMargin(playerbox, new Insets(5,0,0,-70));
        /*this.setLeft(center);
        this.setMargin(center,new Insets(0,0,0,10));
        this.setBottom(play);
        this.setAlignment(play,Pos.BOTTOM_CENTER);*/



    }



}
