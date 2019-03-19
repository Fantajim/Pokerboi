package poker.version_graphics.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.DeckOfCards;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.PlayerPane;
import poker.version_graphics.view.PokerGameView;

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
		view.getPlayButton().setOnAction(event -> play());
		view.getShuffleButton().setOnAction( e -> shuffle() );
		view.getDealButton().setOnAction( e -> deal() );
		view.getAddPlayer().setOnAction(event -> addPlayer() );
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
        		p.evaluateHand();
        		PlayerPane pp = view.getPlayerPane(i);
        		pp.updatePlayerDisplay();
        	}
    	}
    	else if (view.getShuffleButton().isDisable() == true){
    		if (trigger == false) {
				view.getDealButton().setText("Shuffle");
				trigger = !trigger;
			}
    		else {
				shuffle();
				view.getDealButton().setText("Deal");
				trigger = !trigger;
			}
		}
    	else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();

    	}
    }
    private void play(){
	PokerGame.NUM_PLAYERS = Integer.parseInt(view.getSelection());
	view.createGame();
	}

	private void autoshuffle(){
    view.Toggleautoshuffleview();
    if (view.getAutoshuffle().getText() == "Autoshuffle") {
    	view.getAutoshuffle().setText("Autoshuffle \u2713");
	}
    else {
		view.getAutoshuffle().setText("Autoshuffle");
	}
	}

	private void addPlayer() {
	if (PokerGame.NUM_PLAYERS < 4){
	model.addPlayer();
	view.getPlayers().getChildren().add(view.getArrayPp().get(PokerGame.NUM_PLAYERS));
	view.getArrayPp().get(PokerGame.NUM_PLAYERS).setPlayer(model.getPlayer(PokerGame.NUM_PLAYERS));
	model.getPlayer(PokerGame.NUM_PLAYERS).discardHand();
	view.getArrayPp().get(PokerGame.NUM_PLAYERS).updatePlayerDisplay();
	PokerGame.NUM_PLAYERS++;
	 }
	else {
		Alert alert = new Alert(AlertType.ERROR, "Max Player count reached (4)");
		alert.showAndWait();

	}

	}

	private void remPlayer() {
	if (PokerGame.NUM_PLAYERS > 1){
	model.remPlayer();
	view.getPlayers().getChildren().remove(view.getArrayPp().get(PokerGame.NUM_PLAYERS-1));
	PokerGame.NUM_PLAYERS--;
	}
	else {
		Alert alert = new Alert(AlertType.ERROR, "Min Player count reached (1)");
		alert.showAndWait();

	}
	}
}
