package poker.version_graphics.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Optionbar extends MenuBar {

private Menu options = new Menu("Options");
private MenuItem autoshuffle = new MenuItem("Autoshuffle");

public Optionbar(){

    options.getItems().add(autoshuffle);
    this.getMenus().add(options);



}

    public MenuItem getAutoshuffle() {
        return autoshuffle;
    }

}
