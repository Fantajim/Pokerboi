package poker.version_graphics.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import poker.version_graphics.model.PokerGameModel;

public class PokerMainMenu {

    private Mainmenu mainmenu;
    private PokerGameModel model;

    public PokerMainMenu(Stage stage, PokerGameModel model) {
        this.model = model;

        //Create Main menu
        mainmenu = new Mainmenu();
        Scene mainscene = new Scene(mainmenu);
        mainscene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        stage.setScene(mainscene);
        stage.show();
    }



}
