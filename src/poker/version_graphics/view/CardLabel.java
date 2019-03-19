package poker.version_graphics.view;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
			String fileName = cardToFileName(card);
			String path = decks();
			Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/"+path+"/" + fileName));
			ImageView imv = new ImageView(image);
			imv.fitWidthProperty().bind(this.widthProperty());
			imv.fitHeightProperty().bind(this.heightProperty());
			imv.setPreserveRatio(true);
			this.setGraphic(imv);
		} else {
			this.setGraphic(null);
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

