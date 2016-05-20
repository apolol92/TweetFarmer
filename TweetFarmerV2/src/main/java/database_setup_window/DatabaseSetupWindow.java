package database_setup_window;

import config_data.DatabaseConfigData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

/**
 * Created by apolol92 on 20.05.2016.
 */
public class DatabaseSetupWindow {
    public Stage stage;
    public DatabaseSetupWindow(DatabaseConfigData databaseConfigData) {
        this.stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("database_setup_window.fxml"));
            loader.setController(new DatabaseSetupWindowController(databaseConfigData));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("TweetFarmer - Database Setup");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
