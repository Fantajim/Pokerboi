package poker.version_graphics.view;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import poker.version_graphics.model.Card;

import java.util.ArrayList;
import java.util.Random;



public class CardLabel extends Label {
	public CardLabel() {
		super();
		this.getStyleClass().add("card");
	}

	public void setCard(Card card) {
		if (card != null) {
			//cardback Animation
			Image cardback = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/background/pokerback.jpg"));
			ImageView imvback = new ImageView(cardback);
			imvback.fitHeightProperty().bind(this.heightProperty());
			imvback.fitWidthProperty().bind(this.widthProperty());
			imvback.setPreserveRatio(true);
			this.setGraphic(imvback);

			ScaleTransition shrink = new ScaleTransition(Duration.millis(500));
			shrink.setToX(0);

			SequentialTransition sequence = new SequentialTransition(this,shrink);
			sequence.play();

			//new Card Animation
			String fileName = cardToFileName(card);
			String path = decks();
			Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/"+path+"/" + fileName));
			ImageView imv = new ImageView(image);
			imv.fitHeightProperty().bind(this.heightProperty());
			imv.fitWidthProperty().bind(this.widthProperty());
			imv.setPreserveRatio(true);

			sequence.setOnFinished(e ->{
				ScaleTransition grow = new ScaleTransition(Duration.millis(500));
				SequentialTransition sequence2 = new SequentialTransition(this,grow);
				this.setGraphic(imv);
				grow.setToX(1.0);
				sequence2.play();
			});
		}

		else {
			Image cardback = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/background/pokerback.jpg"));
			ImageView imvback = new ImageView(cardback);
			imvback.fitHeightProperty().bind(this.heightProperty());
			imvback.fitWidthProperty().bind(this.widthProperty());
			imvback.setPreserveRatio(true);
			this.setGraphic(imvback);
		}
	}

	private String cardToFileName(Card card) {
		String rank = card.getRank().toString();
		String suit = card.getSuit().toString();
		return rank + "_of_" + suit + ".png";
	}

	private String decks() {
		ArrayList<String> temp = ControlArea.getDecks();
		Random rand = new Random();
		int a = temp.size();
		int b = rand.nextInt((a-1)+1);
		return temp.get(b);
			}

		}

