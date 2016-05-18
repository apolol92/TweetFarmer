package farmer_window.scenes.panels;

import custom_tweet.Tweet;
import custom_tweet.TweetHistoryReceiver;
import data_exporter.CsvExporter;
import farmer_file_manager.TwitterConfigData;
import farmer_window.FarmerWindow;
import farmer_window.scenes.FarmerScene;
import file_manager.FarmerConfig;
import file_manager.FileManager;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import local_storage.LocalStorager;
import main.Program;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by apolol92 on 11.05.2016.
 * Panel for showing all Tweets.. only one at once..
 */
public class TweetViewPanel extends VBox {
    private TextArea taTweeText;
    /**
     * Contains the farmer config
     */
    private FarmerConfig farmerConfig;
    /**
     * Ignore Button
     */
    private Button btIgnore;
    /**
     * All current Tweets
     */
    private ArrayList<Tweet> currentTweets;
    /**
     * The Tweet-Receiver
     */
    public TweetHistoryReceiver tweetHistoryReceiver;
    /**
     * Name of current Farmer
     */
    private String farmername;
    /**
     * Place Holder till Twitter-Pofil-Image
     */
   // private Button btImageTest;
    /**
     * ImageView for User Profil-Image
     */
    private ImageView ivProfil;
    /**
     * Label with Username
     */
    private Label lbUsername;
    /**
     * Label Screenname
     */
    private Label lbScreenname;
    /**
     * Label with Date
     */
    private Label lbDate;
    /**
     * Label with Tweet Text
     */
    private Label lbTweetText;

    public static final Font ITALIC_FONT =
            Font.font(
                    "Arial",
                    FontPosture.ITALIC,
                    Font.getDefault().getSize()
            );

    /**
     * Cronstructor for building TweetViewPanel
     */
    public TweetViewPanel(FarmerScene farmerScene, String farmername) {
        this.farmername = farmername;
        FileManager fileManager = new FileManager(farmername);
        this.farmerConfig = fileManager.readFarmer(farmername);
        //REAL HASHTAGS
        String[] hashtags = this.farmerConfig.getHashtags();
        //REAL CLASSES
        String[] classes = this.farmerConfig.getClasses();
        //Setup TweetHistoryReceiver
        this.tweetHistoryReceiver = new TweetHistoryReceiver(TwitterConfigData.getTwitterConfigData().getTwitterTwitterConsumerKey(),
                TwitterConfigData.getTwitterConfigData().getTwitterTwitterConsumerSecret(), TwitterConfigData.getTwitterConfigData().getTwitterAccessToken(),
                TwitterConfigData.getTwitterConfigData().getTwitterAccessTokenSecret(),hashtags);
        //READ OLD TWEETS
        this.tweetHistoryReceiver.setHistoryTweets(LocalStorager.readAllTweetsFromLocal(farmername));
        //Get Current Tweets
        this.currentTweets = this.tweetHistoryReceiver.getNewTweets();
        this.ivProfil = ImageViewBuilder.create()
                .image(new Image(this.currentTweets.get(0).getProfilImageUrl()))
                .build();
        //this.btImageTest = new Button("Test\nImage");
        //this.btImageTest.setMinWidth(100);
        //this.btImageTest.setMinHeight(100);
        GridPane gridPane = new GridPane();
        this.lbUsername = new Label("Robert");
        this.lbUsername.setFont(ITALIC_FONT);
        this.lbUsername.setMinWidth(120);
        this.lbUsername.setPadding(new Insets(0,0,0,10));
        this.lbScreenname = new Label("@r87");
        this.lbScreenname.setFont(ITALIC_FONT);
        this.lbScreenname.setMinWidth(120);
        this.lbScreenname.setPadding(new Insets(0,0,0,10));
        this.lbDate = new Label("02-23.2016");
        this.lbDate.setFont(ITALIC_FONT);
        this.lbDate.setMinWidth(200);
        this.lbDate.setMaxWidth(200);
        this.lbDate.setPadding(new Insets(0,0,0,10));
        this.lbTweetText = new Label(this.currentTweets.get(0).getText());
        this.lbTweetText.setWrapText(true);
        this.lbTweetText.setAlignment(Pos.TOP_LEFT);
        this.lbTweetText.setMinWidth(300);
        this.lbTweetText.setMaxWidth(300);
        this.lbTweetText.setMinHeight(100);
        this.lbTweetText.setMaxHeight(100);
        this.lbTweetText.setPadding(new Insets(10));
        //Add to Grid
        gridPane.add(this.ivProfil,0,0,1,3);
        gridPane.add(this.lbUsername,1,0,1,1);
        gridPane.add(this.lbScreenname,2,0,1,1);
        gridPane.add(this.lbDate,3,0,1,1);
        gridPane.add(this.lbTweetText,1,1,3,3);
        gridPane.setPadding(new Insets(10));
        //Add real text..
        lbUsername.setText(currentTweets.get(0).getUsername());
        lbScreenname.setText("@"+currentTweets.get(0).getScreenname());
        lbDate.setText(currentTweets.get(0).getDate());
        lbTweetText.setText(currentTweets.get(0).getText());
        //Add Buttons
        int i = 0;
        HBox hBox = new HBox();
        for(String c : classes) {
            Button btClasses = new Button(c);
            btClasses.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    currentTweets.get(0).setCl(btClasses.getText());
                    //TODO: ADD TWEET TO DATABASE
                    //ADD TWEET TO LOCAL STORAGE
                    LocalStorager.insertTweet(farmername,currentTweets.get(0));
                    //Remove tweet from currentTweets
                    if(currentTweets.size()>1) {
                        currentTweets.remove(0);
                    }
                    else {
                        currentTweets.remove(0);
                        currentTweets = tweetHistoryReceiver.getNewTweets();
                    }
                    //Reload TweetViewPanel
                    lbUsername.setText(currentTweets.get(0).getUsername());
                    lbScreenname.setText("@"+currentTweets.get(0).getScreenname());
                    lbDate.setText(currentTweets.get(0).getDate());
                    lbTweetText.setText(currentTweets.get(0).getText());
                    ivProfil.setImage(new Image(currentTweets.get(0).getProfilImageUrl()));
                    System.out.println(LocalStorager.readAllTweetsFromLocal(farmername).get(LocalStorager.readAllTweetsFromLocal(farmername).size()-1).getScreenname());
                }
            });
            final int k = i;
            farmerScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    int selection = -1;
                    try {
                        selection = Integer.parseInt(event.getText())-1;
                        if(selection>=classes.length) {
                            selection = -1;
                        }
                    }
                    catch (Exception ex) {
                        selection = -1;
                    }
                    if(selection!=-1) {
                        currentTweets.get(0).setCl(((Button) hBox.getChildren().get(selection)).getText());
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
                        lbUsername.setText(currentTweets.get(0).getUsername());
                        lbScreenname.setText("@" + currentTweets.get(0).getScreenname());
                        lbDate.setText(currentTweets.get(0).getDate());
                        lbTweetText.setText(currentTweets.get(0).getText());
                        ivProfil.setImage(new Image(currentTweets.get(0).getProfilImageUrl()));
                        System.out.println(LocalStorager.readAllTweetsFromLocal(farmername).get(LocalStorager.readAllTweetsFromLocal(farmername).size() - 1).getScreenname());
                    }
                }
            });

            hBox.getChildren().add(btClasses);
            i++;
        }
        //Add Ignore Button
        this.btIgnore = new Button("Ignore");
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
                lbUsername.setText(currentTweets.get(0).getUsername());
                lbScreenname.setText(currentTweets.get(0).getScreenname());
                lbDate.setText(currentTweets.get(0).getDate());
                lbTweetText.setText(currentTweets.get(0).getText());
                ivProfil.setImage(new Image(currentTweets.get(0).getProfilImageUrl()));
            }
        });
        hBox.getChildren().add(this.btIgnore);
        this.getChildren().addAll(gridPane,hBox);


    }
}
