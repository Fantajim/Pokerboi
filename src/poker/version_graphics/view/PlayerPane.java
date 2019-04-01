package poker.version_graphics.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.HandType;
import poker.version_graphics.model.Player;

import java.awt.*;

public class PlayerPane extends VBox {
    private Label lblName = new Label();
    private HBox hboxCards = new HBox();
    private Label lblEvaluation = new Label("--");
    private CardLabel cl;
    
    // Link to player object
    private Player player;
    
    public PlayerPane() {
        super(); // Always call super-constructor first !!
        this.getStyleClass().add("player"); // CSS style class
        lblEvaluation.setId("evaluation");

        //Background
        Image bg3 = new Image(getClass().getClassLoader().getResourceAsStream("poker/images/background/bg3.jpg"));
        BackgroundImage	background3 = new BackgroundImage(bg3, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        this.setBackground(new Background(background3));

        // Add child nodes
        this.getChildren().addAll(lblName, hboxCards, lblEvaluation);
        // Add CardLabels for the cards
        for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabel();
            hboxCards.getChildren().add(lblCard);
        }

    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    	updatePlayerDisplay(); // Immediately display the player information
    }

    public void updatePlayerDisplay() {
    	lblName.setText(player.getPlayerName());
    	for (int i = 0; i < Player.HAND_SIZE; i++) {
    		Card card = null;
    		if (player.getCards().size() > i) card = player.getCards().get(i);
    		CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
    		cl.setCard(card);
    		HandType evaluation = player.evaluateHand();
    		if (evaluation != null)
    			lblEvaluation.setText(evaluation.toString());
    		else
    			lblEvaluation.setText("--");
    	}
    }

    public void clearDisplay() {
    cl.setCard(null);

    }

}
