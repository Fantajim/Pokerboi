package poker.version_graphics.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import poker.version_graphics.model.PokerGameModel;

public class PokerMainMenu {

    private Mainmenu mainmenu;

    public PokerMainMenu(Stage stage) {

        //Create Main menu
        mainmenu = new Mainmenu();
        Scene mainscene = new Scene(mainmenu);
        mainscene.getStylesheets().add(getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        stage.setScene(mainscene);
        stage.show();
    }

    public Button getPlayButton(){ return mainmenu.play;}
    public String getSelection(){
    ToggleButton temp = (ToggleButton)mainmenu.radiotoggle.getSelectedToggle();
    return temp.getText();
    }


}
