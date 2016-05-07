package farmer_window.scenes.farmer_windw_scene_panels;

import custom_twitter.Tweet;
import farmer_file_manager.FarmerFileConfigData;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import sql_manager.SqlInserter;

import java.util.ArrayList;

/**
 * Created by apolol92 on 07.05.2016.
 */
public class RightPanel extends VBox {
    private final FarmerFileConfigData farmerFileConfigData;
    ArrayList<Tweet> dbTweets;
    ArrayList<Tweet> tweets;
    int currentIndex, max_index;
    public RightPanel(ArrayList<Tweet> dbTweets, ArrayList<Tweet> tweets, FarmerFileConfigData farmerFileConfigData) {
        this.dbTweets = dbTweets;
        this.tweets = tweets;
        this.farmerFileConfigData = farmerFileConfigData;
        this.currentIndex = 0;
        this.max_index = tweets.size();
        createFirstTweetClassification();

    }

    private void createFirstTweetClassification() {
        this.getChildren().removeAll(this.getChildren());
        Label lbTweetText = new Label(this.tweets.get(this.currentIndex).text);
        this.getChildren().add(lbTweetText);
        final Button[] buttons = new Button[this.farmerFileConfigData.getClasses().length+1];
        for(int i = 0; i < this.farmerFileConfigData.getClasses().length; i++) {
            final int clId = i;
            buttons[i] = new Button(this.farmerFileConfigData.getClasses()[i]);
            buttons[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    //SQL INSERT
                    SqlInserter sqlInserter = new SqlInserter(farmerFileConfigData.getDatabaseip(),farmerFileConfigData.getDatabasePort(),farmerFileConfigData.getDatabasename(),farmerFileConfigData.getDatabaseTyp(),farmerFileConfigData.getDatabaseUsername(),farmerFileConfigData.getDatabasePassword());
                    sqlInserter.connectToDatabase();
                    System.out.println(farmerFileConfigData.getName());
                    tweets.get(currentIndex).cl = buttons[clId].getText();
                    sqlInserter.insertTweet(farmerFileConfigData.getName(),tweets.get(currentIndex),clId);
                    sqlInserter.disconnect();
                    //ADD to dbTweets
                    System.out.println(tweets.size());
                    if(dbTweets==null) {
                        dbTweets = new ArrayList<Tweet>();
                    }
                    dbTweets.add(new Tweet(tweets.get(currentIndex).id,tweets.get(currentIndex).text,tweets.get(currentIndex).cl));
                    currentIndex++;
                    if(currentIndex==max_index) {
                        //TODO: Get new tweets

                    }
                    createFirstTweetClassification();
                }
            });
            this.getChildren().add(buttons[i]);
        }
        buttons[this.farmerFileConfigData.getClasses().length] = new Button("Ignore");
        buttons[this.farmerFileConfigData.getClasses().length].setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                currentIndex++;
                if(currentIndex==max_index) {
                    //TODO: Get new tweets

                }
                createFirstTweetClassification();
            }
        });
        this.getChildren().add(buttons[this.farmerFileConfigData.getClasses().length]);


    }




}
