package menu_window;

import file_manager.FileManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by apolol92 on 20.05.2016.
 * This Class represents the MenuWindow
 */
public class MenuWindow {

    /**
     * Creates the Menu Window
     */
    public MenuWindow() {
        Parent root = null;
        try {
            File farmersFolder = new File(FileManager.FARMERS_PATH);
            if(!farmersFolder.exists()) {
                farmersFolder.mkdir();
            }
            Stage stage = new Stage();
            root = FXMLLoader.load(getClass().getClassLoader().getResource("menu_window.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("TweetFarmer - Menu");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
