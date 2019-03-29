package poker.version_graphics.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import poker.version_graphics.model.HandType;

public class Optionbar extends MenuBar {

private Menu options = new Menu("Options");
private MenuItem fastshuffle = new MenuItem("Fastshuffle");

public Optionbar()
    {
    options.getItems().add(fastshuffle);
    this.getMenus().add(options);
    }

    public MenuItem getFastShuffle() {
        return fastshuffle;
    }

}
