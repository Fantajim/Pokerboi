package poker.version_graphics.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import poker.version_graphics.model.DeckOfCards;

public class DeckLabel extends Label {
	public DeckLabel() {
		super();
		Image deckback = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/background/pokerback.jpg"));
		ImageView imvback = new ImageView(deckback);
		imvback.setFitHeight(80);
		imvback.setPreserveRatio(true);
		this.setGraphic(imvback);
		this.getStyleClass().add("deck");
	}
	
	// Bind the label to the CardsRemaining property of the deck
	public void setDeck(DeckOfCards deck) {
		this.textProperty().bind(deck.getCardsRemainingProperty().asString());
	}

}
