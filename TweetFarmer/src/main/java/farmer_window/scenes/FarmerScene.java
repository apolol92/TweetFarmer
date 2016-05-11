package farmer_window.scenes;

import farmer_window.scenes.panels.FarmerMenuPanel;
import farmer_window.scenes.panels.TweetViewPanel;
import javafx.beans.NamedArg;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by gross on 11.05.2016.
 * Users work mostly with this scene.
 */
public class FarmerScene extends Scene {
    private TweetViewPanel tweetViewPanel;
    /**
     * The layout of scene
     */
    private HBox hbLayout;
    /**
     * FarmerMenu Panel
     */
    private FarmerMenuPanel farmerMenuPanel;
    /**
     * Stage of Farmer
     */
    private Stage stage;

    /**
     * Constrcuts the Farmer Scene
     * @param stage
     */
    public FarmerScene(Stage stage) {
        super(new HBox());
        this.hbLayout = (HBox)this.getRoot();
        this.farmerMenuPanel = new FarmerMenuPanel();
        this.tweetViewPanel = new TweetViewPanel();
        this.hbLayout.getChildren().addAll(this.farmerMenuPanel,this.tweetViewPanel);
        this.stage = stage;
        this.stage.setScene(this);
        this.stage.show();
    }
}
