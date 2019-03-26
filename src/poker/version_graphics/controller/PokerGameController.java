package poker.version_graphics.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.*;
import poker.version_graphics.view.PlayerPane;
import poker.version_graphics.view.PokerGameView;

import java.util.ArrayList;

public class PokerGameController {
	private PokerGameModel model;
	private PokerGameView view;
	private Boolean on;
	private Boolean trigger = false;

	public PokerGameController(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;
		this.on = on;
		view.getAutoshuffle().setOnAction(event -> autoshuffle());
		view.getShuffleButton().setOnAction(e -> shuffle());
		view.getDealButton().setOnAction(e -> deal());
		view.getAddPlayer().setOnAction(event -> addPlayer());
		view.getRemPlayer().setOnAction(event -> remPlayer());
	}


	/**
	 * Remove all cards from players hands, and shuffle the deck
	 */
	private void shuffle() {
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			Player p = model.getPlayer(i);
			p.discardHand();
			PlayerPane pp = view.getPlayerPane(i);
			pp.updatePlayerDisplay();
		}

		model.getDeck().shuffle();
	}

	/**
	 * Deal each player five cards, then evaluate the two hands
	 */
	private void deal() {
		String playerwinner = "test";
		int cardsRequired = PokerGame.NUM_PLAYERS * Player.HAND_SIZE;
		DeckOfCards deck = model.getDeck();
		if (cardsRequired <= deck.getCardsRemaining()) {
			for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
				Player p = model.getPlayer(i);
				p.discardHand();
				for (int j = 0; j < Player.HAND_SIZE; j++) {
					Card card = deck.dealCard();
					p.addCard(card);
				}
				//method comparing
				PlayerPane pp = view.getPlayerPane(i);
				pp.updatePlayerDisplay();
			}
			playerwinner = tieFind();
            view.getResult().setText(playerwinner);

		} else if (view.getShuffleButton().isDisable() == true) {
			if (trigger == false) {
				view.getDealButton().setText("Shuffle");
				trigger = !trigger;
			} else {
				shuffle();
				view.getDealButton().setText("Deal");
				trigger = !trigger;
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
			alert.showAndWait();

		}
	}

	private void autoshuffle() {
		view.Toggleautoshuffleview();
		if (view.getAutoshuffle().getText() == "Autoshuffle") {
			view.getAutoshuffle().setText("Autoshuffle \u2713");
		} else {
			view.getAutoshuffle().setText("Autoshuffle");
		}
	}

	private void addPlayer() {
		if (PokerGame.NUM_PLAYERS < 4) {
			model.addPlayer();
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(PokerGame.NUM_PLAYERS));
			view.getPlayers().getChildren().add(pp);
			PokerGame.NUM_PLAYERS++;

		} else {
			Alert alert = new Alert(AlertType.ERROR, "Max Player count reached (4)");
			alert.showAndWait();

		}

	}

	private void remPlayer() {
		if (PokerGame.NUM_PLAYERS > 1) {
			model.remPlayer();
			view.getPlayers().getChildren().remove(PokerGame.NUM_PLAYERS - 1);
			view.getPp().updatePlayerDisplay();
			PokerGame.NUM_PLAYERS--;
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Min Player count reached (1)");
			alert.showAndWait();

		}
	}

	public String tieFind() {
		String win = "";
		//int result;
		ArrayList<Player> players = new ArrayList<>();
		if (PokerGame.NUM_PLAYERS > 1) for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			players.add(model.getPlayer(i));
		}

		int x = 0;
		while (x< players.size()-1){
        int result = players.get(x).compareTo(players.get(x+1));
		  if(result==0) {
              result = Tie.findWinner(players.get(x), players.get(x + 1));
              if (result == 100) {
                  x++;
                  result = 0;
              }
          }
		  if (result >=1) {
		          players.remove(x+1);
              }
		  if (result < 1) {
		          players.remove(x);
              }
        }
        win = "Winner is: ";
		for (Player p : players) {
			win = win.concat(p.getPlayerName());
		}
		return win;
	}
}