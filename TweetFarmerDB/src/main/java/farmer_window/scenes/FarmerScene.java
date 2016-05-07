package farmer_window.scenes;

import custom_twitter.Tweet;
import custom_twitter.TweetCollector;
import farmer_file_manager.FarmerFileConfigData;
import farmer_file_manager.FarmerFileReader;
import farmer_window.scenes.farmer_windw_scene_panels.RightPanel;
import javafx.beans.NamedArg;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sql_manager.SqlReader;
import twitter4j.Query;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by apolol92 on 07.05.2016.
 */
public class FarmerScene extends Scene {
    /**
     * This is the main layout for this scene
     */
    private HBox hbLayout;
    /**
     * This is the stage for a farmer
     */
    private Stage stage;

    /**
     * This attribute contains all farmer configurations
     */
    FarmerFileConfigData farmerFileConfigData;
    /**
     * Tweets from Database
     */
    private ArrayList<Tweet> dbTweets;

    /**
     * Tweets which aren't classified.. (new from twitter)
     */
    private ArrayList<Tweet> tweets;

    RightPanel rightPanel;
    public FarmerScene(Stage stage, String farmerName) {
        super(new HBox());
        loadXmlConfig(farmerName);
        loadDbTweets(farmerName);
        TweetCollector tCollector = new TweetCollector(this.farmerFileConfigData,this.dbTweets);
        this.tweets = tCollector.getFirstTweets();
        this.rightPanel = new RightPanel(this.dbTweets,this.tweets,this.farmerFileConfigData);
        this.hbLayout = (HBox)getRoot();
        this.hbLayout.getChildren().addAll(this.rightPanel);
        this.stage = stage;
        this.stage.setScene(this);
        this.stage.show();

    }



    private void loadDbTweets(String farmerName) {
        SqlReader sqlReader = new SqlReader(this.farmerFileConfigData.getDatabaseip(),this.farmerFileConfigData.getDatabasePort(),this.farmerFileConfigData.getDatabasename(),
                this.farmerFileConfigData.getDatabaseTyp(),this.farmerFileConfigData.getDatabaseUsername(),this.farmerFileConfigData.getDatabasePassword(),farmerName);
        sqlReader.connectToDatabase();
        this.dbTweets = sqlReader.getAllPairedTweetIdClass();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++"+this.dbTweets.size());
        sqlReader.disconnect();
    }

    private void loadXmlConfig(String farmerName) {
        this.farmerFileConfigData = new FarmerFileConfigData();
        FarmerFileReader farmerFileReader = new FarmerFileReader();
        this.farmerFileConfigData = farmerFileReader.readFarmerConfig(farmerName);
        System.out.println("test:"+this.farmerFileConfigData.getDatabaseUsername());

    }
}
