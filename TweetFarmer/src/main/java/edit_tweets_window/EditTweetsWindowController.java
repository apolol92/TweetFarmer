package edit_tweets_window;

import config_data.DatabaseConfigData;
import config_data.TwitterConfigData;
import custom_tweet.Tweet;
import file_manager.FarmerConfig;
import file_manager.FileManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import local_storage.LocalStorager;
import sql.DatabaseProcExecuter;
import sql.TweetConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by apolol92 on 21.05.2016.
 * This is the Controller for the EditTweetsWindow
 */
public class EditTweetsWindowController implements Initializable{
    /**
     * Scrollpanel for all Tweets
     */
    @FXML
    public ScrollPane spTweets;
    /**
     * Verticalbox for Tweets
     */
    @FXML
    public VBox vbTweets;
    /**
     * This is the current farmername
     */
    private String farmername;


    /**
     * EditTweetsWindowController Constructor
     * @param farmername
     */
    public EditTweetsWindowController(String farmername) {
        this.farmername = farmername;

    }

    /**
     * UI Initializer
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        //Get Tweets
        FarmerConfig farmerConfig = new FarmerConfig();
        farmerConfig = new FileManager(farmername).readFarmer(farmername);
        if(farmerConfig.isDatabaseStorage()) {
            DatabaseConfigData databaseConfigData = new DatabaseConfigData();
            databaseConfigData.readData(farmername);
            DatabaseProcExecuter databaseProcExecuter = new DatabaseProcExecuter(databaseConfigData.getIp(),Integer.parseInt(databaseConfigData.getPort()),
                    databaseConfigData.getDbTyp(),databaseConfigData.getDatabasename(),databaseConfigData.getUsername(),databaseConfigData.getPassword());
            databaseProcExecuter.connect();
            tweets = TweetConverter.getTweetsFromDatabase(databaseProcExecuter.execProcSelectTweetsFromFarmer(farmername),farmername);
            databaseProcExecuter.disconnect();
        }
        else {
            tweets = LocalStorager.readAllTweetsFromLocal(farmername);
        }
        for(int i = 0; i < tweets.size(); i++) {
            EditTweetRow editTweetRow = new EditTweetRow(tweets.get(i),farmerConfig);
            vbTweets.getChildren().add(editTweetRow);
        }


    }
}
