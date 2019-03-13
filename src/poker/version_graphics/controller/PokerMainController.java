package poker.version_graphics.controller;

import poker.version_graphics.PokerGame;
import poker.version_graphics.view.PokerMainMenu;

public class PokerMainController {

    private PokerMainMenu view;

    public PokerMainController(PokerMainMenu view) {
        this.view = view;

     view.getPlayButton().setOnAction(event -> {
         PokerGame.setNumPlayers(view.getSelection());
         PokerGame.startGame();
     });

    }

}
