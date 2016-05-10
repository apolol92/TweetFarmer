package database_config_window;

import database_config_window.scenes.ConfigScene;
import javafx.stage.Stage;
import menu_window.scenes.MenuScene;

/**
 * Created by apolol92 on 10.05.2016.
 */
public class ConfigWindow {

    public ConfigWindow() {
        Stage stage = new Stage();
        ConfigScene configScene = new ConfigScene(stage);
    }
}
