package poker.version_graphics.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Mainmenu extends BorderPane {

private Label menulabel =new Label ("Pokerboi");
private Label players = new Label("Players:  ");
final ToggleGroup radiotoggle = new ToggleGroup();
private RadioButton one = new RadioButton("1");
private RadioButton two = new RadioButton("2");
private  RadioButton three = new RadioButton("3");
private RadioButton four = new RadioButton("4");
Button play = new Button("Play");



    public Mainmenu() {
        super();
        this.setTop(menulabel);
        menulabel.setId("menulabel");
        one.setToggleGroup(radiotoggle);
        two.setToggleGroup(radiotoggle);
        three.setToggleGroup(radiotoggle);
        four.setToggleGroup(radiotoggle);
        one.setSelected(true);
        VBox playerbox = new VBox(one,two,three,four);
        playerbox.setSpacing(2);
        HBox center = new HBox(players,playerbox);
        center.setId("center");
        center.setAlignment(Pos.TOP_LEFT);
        center.setMargin(playerbox, new Insets(5,0,0,-70));
        this.setCenter(center);
        this.setMargin(center,new Insets(0,0,0,10));
        this.setBottom(play);
        this.setAlignment(play,Pos.BOTTOM_CENTER);



    }



}
