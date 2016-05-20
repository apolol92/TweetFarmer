package tweet_farmer_window;

import config_data.DatabaseConfigData;
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
import sql.DatabaseCreator;
import sql.DatabaseProcExecuter;
import sql.TweetConverter;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by apolol92 on 20.05.2016.
 * This is the controller for the TweetFarmerWindow
 */
public class TweetFarmerWindowController implements Initializable{
    private ArrayList<ClassIdPair> ClassIdPairs;
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
    DatabaseProcExecuter databaseProcExecuter;
    /**
     * Construcotr of TweetFarmer
     * @param farmername
     */
    public TweetFarmerWindowController(String farmername) {
        this.farmername = farmername;
        FileManager fileManager = new FileManager(farmername);
        this.farmerConfig = fileManager.readFarmer(farmername);
        System.out.println("pppppppppppppppppppppppppppooooooooooooooooooooooooostttttttttttttttgreeeeeeeeeeeeeee11111111");
        System.out.println(this.farmerConfig.getName()+ " " +this.farmerConfig.isDatabaseStorage());
        if(this.farmerConfig.isDatabaseStorage()) {
            ClassIdPairs = new ArrayList<ClassIdPair>();
            System.out.println("pppppppppppppppppppppppppppooooooooooooooooooooooooostttttttttttttttgreeeeeeeeeeeeeee222222222");
            DatabaseConfigData databaseConfigData = new DatabaseConfigData();
            databaseConfigData.readData(farmername);
            //Database Table Setup
            System.out.println(databaseConfigData.getIp()+ " " +Integer.parseInt(databaseConfigData.getPort())+ " " +
                    databaseConfigData.getDbTyp()+ " " +databaseConfigData.getDatabasename()+ " " +databaseConfigData.getUsername()+ " " +databaseConfigData.getPassword());
            DatabaseCreator databaseCreator = new DatabaseCreator(databaseConfigData.getIp(),Integer.parseInt(databaseConfigData.getPort()),
                    databaseConfigData.getDbTyp(),databaseConfigData.getDatabasename(),databaseConfigData.getUsername(),databaseConfigData.getPassword());
            databaseCreator.connect();
            databaseCreator.createTables();
            databaseCreator.createProcedures();
            databaseCreator.disconnect();
            databaseProcExecuter = new DatabaseProcExecuter(databaseConfigData.getIp(), Integer.parseInt(databaseConfigData.getPort()),
                    databaseConfigData.getDbTyp(), databaseConfigData.getDatabasename(), databaseConfigData.getUsername(), databaseConfigData.getPassword());
            databaseProcExecuter.connect();
            //Current Farmer setup in database
            long farmerId = databaseProcExecuter.execProcInsertFarmer(farmername);
            for(String c : this.farmerConfig.getClasses()) {
                try {
                    databaseProcExecuter.execProcInsertClass(farmerId, c);
                }
                catch (Exception ex) {
                }
            }
            databaseProcExecuter.disconnect();
            databaseProcExecuter.connect();
            ResultSet rs = databaseProcExecuter.execProcGetClassesFromFarmername(farmername);
            try {
                while(rs.next()) {
                    System.out.println(rs.getString(1));
                    ClassIdPairs.add(new ClassIdPair(rs.getString(1), rs.getInt(2)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            databaseProcExecuter.disconnect();
            System.out.println("ClassIdPairs-Size:"+ClassIdPairs.size());
        }
        this.hashtags = this.farmerConfig.getHashtags();
        this.classes = this.farmerConfig.getClasses();
        this.tweetHistoryReceiver = new TweetHistoryReceiver(TwitterConfigData.getTwitterConfigData().getTwitterTwitterConsumerKey(),
                TwitterConfigData.getTwitterConfigData().getTwitterTwitterConsumerSecret(), TwitterConfigData.getTwitterConfigData().getTwitterAccessToken(),
                TwitterConfigData.getTwitterConfigData().getTwitterAccessTokenSecret(),hashtags);
        this.tweetHistoryReceiver.setHistoryTweets(LocalStorager.readAllTweetsFromLocal(farmername));
        this.currentTweets = this.tweetHistoryReceiver.getNewTweets();
        System.out.println(this.currentTweets.size());
        //System.out.println(currentTweets.get(0).getUsername() + " @"+currentTweets.get(0).getScreenname());
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
                    //Remove tweet from currentTweets
                    if (currentTweets.size() > 1) {
                        LocalStorager.insertTweet(farmername, currentTweets.get(0));
                        //ADD TWEET TO DATABASE
                        if(farmerConfig.isDatabaseStorage()) {
                            databaseProcExecuter.connect();
                            //Insert Tweet to database
                            databaseProcExecuter.execProcInsertTweet(currentTweets.get(0).getId(),
                                    ClassIdPair.getIdByClassname(ClassIdPairs, currentTweets.get(0).getCl()), currentTweets.get(0).getText(),
                                    currentTweets.get(0).getLikes(), currentTweets.get(0).getRetweets());
                            //Add inserted Tweets to history(useful by multiple users)
                            tweetHistoryReceiver.addNewTweets2History(TweetConverter.getTweetsFromDatabase(databaseProcExecuter.execProcSelectTweetsFromFarmer(farmername),farmername));
                            databaseProcExecuter.disconnect();
                        }
                        currentTweets.remove(0);
                    } else {
                        try {
                            LocalStorager.insertTweet(farmername, currentTweets.get(0));
                            //ADD TWEET TO DATABASE
                            if(farmerConfig.isDatabaseStorage()) {
                                databaseProcExecuter.connect();
                                //Insert Tweet to database
                                databaseProcExecuter.execProcInsertTweet(currentTweets.get(0).getId(),
                                        ClassIdPair.getIdByClassname(ClassIdPairs, currentTweets.get(0).getCl()), currentTweets.get(0).getText(),
                                        currentTweets.get(0).getLikes(), currentTweets.get(0).getRetweets());
                                //Add inserted Tweets to history(useful by multiple users)
                                tweetHistoryReceiver.addNewTweets2History(TweetConverter.getTweetsFromDatabase(databaseProcExecuter.execProcSelectTweetsFromFarmer(farmername),farmername));
                                databaseProcExecuter.disconnect();
                            }
                            currentTweets.remove(0);
                        }
                        catch (Exception ex) {

                        }
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
                    try {
                        currentTweets.remove(0);
                    }
                    catch (Exception ex) {

                    }
                    if(farmerConfig.isDatabaseStorage()) {
                        tweetHistoryReceiver.addNewTweets2History(TweetConverter.getTweetsFromDatabase(databaseProcExecuter.execProcSelectTweetsFromFarmer(farmername),farmername));
                    }
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
