package menu_window;

import javafx.scene.control.Menu;
import javafx.stage.Stage;
import menu_window.scenes.MenuScene;
import setup_window.scenes.SetupScene;

/**
 * Created by apolol92 on 10.05.2016.
 */
public class MenuWindow {
    /**
     * Stage
     */
    private Stage stage;

    /**
     * Creates the Menu Window
     * @param stage
     */
    public MenuWindow(Stage stage) {
        this.stage = stage;
        MenuScene setupScene = new MenuScene(this.stage);
    }
}
