package farmer_window.scenes;

import custom_tweet.Tweet;
import data_exporter.CsvExporter;
import farmer_window.scenes.panels.TweetViewPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import local_storage.LocalStorager;

import java.util.ArrayList;

/**
 * Created by gross on 11.05.2016.
 * Users work mostly with this scene.
 */
public class FarmerScene extends Scene {
    /**
     * Name of current Farmer
     */
    private String farmername;
    /**
     * TweetView-Panel
     */
    private TweetViewPanel tweetViewPanel;
    private VBox layout;
    /**
     * The layout of scene
     */
    private HBox hbLayout;

    /**
     * Stage of Farmer
     */
    private Stage stage;

    /**
     * Constrcuts the Farmer Scene
     * @param stage
     */
    public FarmerScene(Stage stage, String farmername) {
        super(new VBox());
        this.farmername = farmername;
        this.layout = (VBox)this.getRoot();
        this.hbLayout = new HBox();
        //Menu
        Menu exportMenu = new Menu("Export");

        //Menubar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(exportMenu);
        this.tweetViewPanel = new TweetViewPanel(this,this.farmername);
        this.hbLayout.getChildren().addAll(this.tweetViewPanel);
        //Menu items
        MenuItem exportCsv = new MenuItem("CSV Export");
        exportMenu.getItems().add(exportCsv);
        exportCsv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Tweet> tweets = LocalStorager.readAllTweetsFromLocal(farmername);
                if(tweets.size()>0) {
                    CsvExporter.export(farmername, tweets);
                    System.out.println("written..");
                }
            }
        });
        this.layout.getChildren().addAll(menuBar,this.hbLayout);
        this.stage = stage;
        this.stage.setScene(this);
        this.stage.show();
    }
}
