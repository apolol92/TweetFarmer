package edit_tweets_window;

import farmer_edit_window.FarmerEditWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by apolol92 on 21.05.2016.
 */
public class EditTweetsWindow {
    private final Stage stage;

    public EditTweetsWindow(String farmername) {
        this.stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edit_tweets_window.fxml"));
            loader.setController(new EditTweetsWindowController(farmername));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("TweetFarmer - Edit Tweets");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
