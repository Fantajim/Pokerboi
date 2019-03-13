package poker.version_text.view;

import javafx.scene.control.Label;
import poker.version_text.model.Card;

public class CardLabel extends Label {
	public CardLabel() {
		super();
		this.getStyleClass().add("card");
	}
	
	public void setCard(Card card) {
		if (card != null)
			this.setText(card.toString());
		else
			this.setText("");
	}
}
