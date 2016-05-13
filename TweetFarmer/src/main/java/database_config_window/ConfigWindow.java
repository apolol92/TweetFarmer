package database_config_window;

import database.DatabaseConfigData;
import database_config_window.scenes.ConfigScene;
import javafx.stage.Stage;

/**
 * Created by apolol92 on 10.05.2016.
 * ConfigWindow for Database Access.
 */
public class ConfigWindow {

    /**
     * Creates the ConfigWindow
     * @param databaseConfigData
     */
    public ConfigWindow(DatabaseConfigData databaseConfigData) {
        Stage stage = new Stage();
        ConfigScene configScene = new ConfigScene(stage, databaseConfigData);
    }
}
