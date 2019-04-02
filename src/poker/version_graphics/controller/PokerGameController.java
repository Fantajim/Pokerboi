package poker.version_graphics.controller;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.*;
import poker.version_graphics.view.CardLabel;
import poker.version_graphics.view.PlayerPane;
import poker.version_graphics.view.PokerGameView;

import java.util.ArrayList;
import java.util.Comparator;

public class PokerGameController {
	private PokerGameModel model;
	private PokerGameView view;
	private Boolean on;
	private Boolean trigger = false;

	public PokerGameController(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;
		this.on = on;
		view.getFastShuffle().setOnAction(event -> fastShuffle());
		view.getShuffleButton().setOnAction(e -> shuffle());
		view.getDealButton().setOnAction(e -> deal());
		view.getAddPlayer().setOnAction(event -> addPlayer());
		view.getRemPlayer().setOnAction(event -> remPlayer());
		view.getMusicPlay().setOnAction(event -> playMusic());
		view.getMusicStop().setOnAction(event -> stopMusic());
		view.getVolume().valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				view.getlobbyMusicPlayer().setVolume(view.getVolume().getValue());
			}
		});

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

	private void fastShuffle() {
		view.ToggleFastShuffleview();
		if (view.getFastShuffle().getText() == "Fastshuffle") {
			view.getFastShuffle().setText("Fastshuffle \u2713");
		} else {
			view.getFastShuffle().setText("Fastshuffle");
		}
	}

	private void addPlayer() {
		if (PokerGame.NUM_PLAYERS < 8) {
			model.addPlayer();
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(PokerGame.NUM_PLAYERS));
			view.getPlayers().getChildren().add(pp);
			view.getPpList().add(pp);
			view.updatePpSize();
			PokerGame.NUM_PLAYERS++;

			//animation
			ScaleTransition shrink = new ScaleTransition(Duration.millis(0));
			ScaleTransition grow = new ScaleTransition(Duration.millis(500));
			shrink.setToX(0);
			shrink.setToY(0);
			grow.setToX(1.0);
			grow.setToY(1.0);

			SequentialTransition sequence = new SequentialTransition(pp,shrink,grow);
			sequence.play();
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Max Player count reached (8)");
			alert.showAndWait();

		}

	}

	private void remPlayer() {
		if (PokerGame.NUM_PLAYERS > 1) {

			//animation
			ScaleTransition shrink = new ScaleTransition(Duration.millis(500));
			ScaleTransition grow = new ScaleTransition(Duration.millis(0));
			shrink.setToX(0);
			shrink.setToY(0);
			grow.setToX(1.0);
			grow.setToY(1.0);

			SequentialTransition sequence = new SequentialTransition(view.getPlayerPane(PokerGame.NUM_PLAYERS-1),grow, shrink);
			sequence.play();

			sequence.setOnFinished(event -> {
				model.remPlayer();
				view.getPlayers().getChildren().remove(PokerGame.NUM_PLAYERS - 1);
				view.getPpList().remove(PokerGame.NUM_PLAYERS-1);
				view.updatePpSize();
				PokerGame.NUM_PLAYERS--;
			});

		}

		else {
			Alert alert = new Alert(AlertType.ERROR, "Min Player count reached (1)");
			alert.showAndWait();

		}
	}

	private void playMusic(){
		if (view.getMusicPlay().getText() == "\u25B6")
		{  //Play
			view.getMusicPlay().setText("\u25B7");
			view.getlobbyMusicPlayer().play();
		}
		else if(view.getMusicPlay().getText() == "\u25B7")
		{  //Pause
			view.getMusicPlay().setText("\u25B6");
			view.getlobbyMusicPlayer().pause();
		}
	}

	private void stopMusic(){
		view.getlobbyMusicPlayer().stop();
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
        win = "Winner: ";
		for (Player p : players) {
			win = win.concat(p.getPlayerName()+" with "+p.getHandType()+" ");
		}
		return win;
	}
}