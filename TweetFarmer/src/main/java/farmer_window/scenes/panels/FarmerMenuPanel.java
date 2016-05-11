package farmer_window.scenes.panels;

import edit_tweets_window.EditTweetsWindow;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Created by gross on 11.05.2016.
 * Contains a farmer navigation
 */
public class FarmerMenuPanel extends VBox {

    /**
     * Button to Edit all tweets
     */
    private Button btEditTweets;
    /**
     * Farmer Distribution as Headline
     */
    private Label lbFarmerDistribution;

    /**
     * Creates the FarmerMenuPanel
     */
    public FarmerMenuPanel() {
        this.lbFarmerDistribution = new Label("Farmer Distribution");
        //TODO: Histogram
        this.btEditTweets = new Button("Edit Farmers");
        this.btEditTweets.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EditTweetsWindow editTweetsWindow = new EditTweetsWindow();
            }
        });
        this.getChildren().addAll(this.lbFarmerDistribution,this.btEditTweets);
    }


}
