package setup_window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by apolol92 on 19.05.2016.
 * Represents the SetupWindow
 */
public class SetupWindow {
    /**
     * Creates the SetupWindow
     */
    public SetupWindow() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            root = (Parent)fxmlLoader.load(getClass().getClassLoader().getResource("setup_window.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("TweetFarmer - Setup");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
