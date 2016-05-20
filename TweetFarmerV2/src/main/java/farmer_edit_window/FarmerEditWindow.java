package farmer_edit_window;

import database_setup_window.DatabaseSetupWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tweet_farmer_window.TweetFarmerWindowController;

import java.io.IOException;

/**
 * Created by apolol92 on 21.05.2016.
 */
public class FarmerEditWindow {
    private final Stage stage;

    public FarmerEditWindow(String farmername) {
        this.stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("farmer_edit_window.fxml"));
            loader.setController(new FarmerEditWindowController(farmername));
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
