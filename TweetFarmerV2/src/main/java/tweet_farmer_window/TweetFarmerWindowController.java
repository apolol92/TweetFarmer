package tweet_farmer_window;

import config_data.TwitterConfigData;
import custom_tweet.Tweet;
import custom_tweet.TweetHistoryReceiver;
import data_exporter.CsvExporter;
import file_manager.FarmerConfig;
import file_manager.FileManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import local_storage.LocalStorager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by apolol92 on 20.05.2016.
 * This is the controller for the TweetFarmerWindow
 */
public class TweetFarmerWindowController implements Initializable{
    /**
     * Farmer Config
     */
    FarmerConfig farmerConfig;
    /**
     * Label which contains the twitter-username/screenname
     */
    @FXML
    Label lbName;
    /**
     * Label which contains the date of tweet-creation
     */
    @FXML
    Label lbDate;
    /**
     * Textarea which contains the Tweet-Text
     */
    @FXML
    TextArea taTweet;
    /**
     * ImageView which contains the Profil
     */
    @FXML
    ImageView ivProfil;
    /**
     * VerticalBox which contains all class buttons
     */
    /**
     * Ignore Button
     */
    @FXML
    Button btIgnore;
    /**
     * Current farmername
     */
    String farmername;
    /**
     * Farmer hashtags
     */
    String[] hashtags;
    /**
     * Farmer Classes
     */
    String[] classes;
    /**
     * Tweet Receiver
     */
    private TweetHistoryReceiver tweetHistoryReceiver;
    /**
     * Current Tweets
     */
    ArrayList<Tweet> currentTweets;
    /**
     * Horizontal Box for button classes
     */
    @FXML
    HBox hbClasses;

    /**
     * Construcotr of TweetFarmer
     * @param farmername
     */
    public TweetFarmerWindowController(String farmername) {
        this.farmername = farmername;
        FileManager fileManager = new FileManager(farmername);
        this.farmerConfig = fileManager.readFarmer(farmername);
        this.hashtags = this.farmerConfig.getHashtags();
        this.classes = this.farmerConfig.getClasses();
        this.tweetHistoryReceiver = new TweetHistoryReceiver(TwitterConfigData.getTwitterConfigData().getTwitterTwitterConsumerKey(),
                TwitterConfigData.getTwitterConfigData().getTwitterTwitterConsumerSecret(), TwitterConfigData.getTwitterConfigData().getTwitterAccessToken(),
                TwitterConfigData.getTwitterConfigData().getTwitterAccessTokenSecret(),hashtags);
        this.tweetHistoryReceiver.setHistoryTweets(LocalStorager.readAllTweetsFromLocal(farmername));
        this.currentTweets = this.tweetHistoryReceiver.getNewTweets();
        System.out.println(currentTweets.get(0).getUsername() + " @"+currentTweets.get(0).getScreenname());
    }

    /**
     * Initialize UI with first content..
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ivProfil.setImage(new Image(currentTweets.get(0).getProfilImageUrl()));
        this.lbName.setText(currentTweets.get(0).getUsername() + " @"+currentTweets.get(0).getScreenname());
        this.lbDate.setText(currentTweets.get(0).getDate());
        this.taTweet.setText(currentTweets.get(0).getText());
        int i = 0;
        for(String c : classes) {
            Button btClasses = new Button(c);
            btClasses.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    currentTweets.get(0).setCl(btClasses.getText());
                    //TODO: ADD TWEET TO DATABASE
                    //ADD TWEET TO LOCAL STORAGE
                    LocalStorager.insertTweet(farmername, currentTweets.get(0));
                    //Remove tweet from currentTweets
                    if (currentTweets.size() > 1) {
                        currentTweets.remove(0);
                    } else {
                        currentTweets.remove(0);
                        currentTweets = tweetHistoryReceiver.getNewTweets();
                    }
                    //Reload TweetViewPanel
                    lbName.setText(currentTweets.get(0).getUsername() + " @" + currentTweets.get(0).getScreenname());
                    lbDate.setText(currentTweets.get(0).getDate());
                    taTweet.setText(currentTweets.get(0).getText());
                    ivProfil.setImage(new Image(currentTweets.get(0).getProfilImageUrl()));
                }
            });
            hbClasses.getChildren().add(btClasses);
        }
        //Add Ignore Button
        this.btIgnore.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //NO STORAGE
                //Remove tweet from currentTweets
                if(currentTweets.size()>1) {
                    currentTweets.remove(0);
                }
                else {
                    currentTweets.remove(0);
                    currentTweets = tweetHistoryReceiver.getNewTweets();
                }
                //Reload TweetViewPanel
                lbName.setText(currentTweets.get(0).getUsername() + " @" + currentTweets.get(0).getScreenname());
                lbDate.setText(currentTweets.get(0).getDate());
                taTweet.setText(currentTweets.get(0).getText());
                ivProfil.setImage(new Image(currentTweets.get(0).getProfilImageUrl()));
            }
        });
    }

    /**
     * Export CSV
     * @param actionEvent
     */
    public void exportCSV(ActionEvent actionEvent) {
        ArrayList<Tweet> tweets = LocalStorager.readAllTweetsFromLocal(farmername);
        if(tweets.size()>0) {
            CsvExporter.export(farmername, tweets);
            System.out.println("written..");
        }
    }
}
