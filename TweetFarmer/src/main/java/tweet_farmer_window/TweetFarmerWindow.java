package tweet_farmer_window;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by apolol92 on 20.05.2016.
 * Represents the Tweet Farmer Window
 */
public class TweetFarmerWindow {
    /**
     * Current Stage
     */
    Stage stage;

    /**
     * Constructor
     * @param farmername
     */
    public TweetFarmerWindow(String farmername) {
        this.stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("tweet_farmer_window.fxml"));
            loader.setController(new TweetFarmerWindowController(farmername));
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
