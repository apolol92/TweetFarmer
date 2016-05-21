package edit_tweets_window;

import config_data.DatabaseConfigData;
import custom_tweet.Tweet;
import file_manager.FarmerConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import local_storage.LocalStorager;
import sql.DatabaseProcExecuter;

/**
 * Created by apolol92 on 21.05.2016.
 * This class represents a row in EditTweetsWindow.. it contains a single tweet..
 */
public class EditTweetRow extends HBox {
    /**
     * Constructor
     * @param tweet
     * @param farmerConfig
     */
    public EditTweetRow(Tweet tweet, FarmerConfig farmerConfig) {
        super();
        this.setMaxHeight(300);
        TextArea taTweets = new TextArea(tweet.getText());
        taTweets.setPrefWidth(500);
        taTweets.setPrefHeight(500);
        taTweets.setMaxWidth(500);
        taTweets.setMaxHeight(500);
        taTweets.setEditable(false);
        ObservableList<String> classes =
                FXCollections.observableArrayList(
                        farmerConfig.getClasses()
                );
        ComboBox<String> cobClass = new ComboBox<String>(classes);
        System.out.println(tweet.getCl());
        cobClass.getSelectionModel().select(tweet.getCl());
        cobClass.setPrefWidth(300);
        cobClass.setMaxHeight(50);
        cobClass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Update tweet class
                if(farmerConfig.isDatabaseStorage()) {
                    DatabaseConfigData databaseConfigData = new DatabaseConfigData();
                    databaseConfigData.readData(farmerConfig.getName());
                    DatabaseProcExecuter databaseProcExecuter = new DatabaseProcExecuter(databaseConfigData.getIp(), Integer.parseInt(databaseConfigData.getPort()),
                            databaseConfigData.getDbTyp(), databaseConfigData.getDatabasename(), databaseConfigData.getUsername(), databaseConfigData.getPassword());
                    databaseProcExecuter.connect();
                    databaseProcExecuter.execProcUpdateTweetFromFarmer(farmerConfig.getName(),tweet.getId(),cobClass.getSelectionModel().getSelectedItem().toString());
                    databaseProcExecuter.disconnect();
                }
                LocalStorager.updateTweetClass(farmerConfig.getName(),tweet.getId(),cobClass.getSelectionModel().getSelectedItem().toString());
            }
        });
        Button btDelete = new Button("DELETE");
        btDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EditTweetRow.this.getChildren().removeAll(EditTweetRow.this.getChildren());
                //Delete tweet
                if(farmerConfig.isDatabaseStorage()) {
                    DatabaseConfigData databaseConfigData = new DatabaseConfigData();
                    databaseConfigData.readData(farmerConfig.getName());
                    DatabaseProcExecuter databaseProcExecuter = new DatabaseProcExecuter(databaseConfigData.getIp(), Integer.parseInt(databaseConfigData.getPort()),
                            databaseConfigData.getDbTyp(), databaseConfigData.getDatabasename(), databaseConfigData.getUsername(), databaseConfigData.getPassword());
                    databaseProcExecuter.connect();
                    databaseProcExecuter.execProcDeleteTweetFromFarmer(tweet.getId());
                    databaseProcExecuter.disconnect();
                }
                LocalStorager.deleteTweetById(farmerConfig.getName(),tweet.getId());
            }
        });
        btDelete.setPrefWidth(300);
        btDelete.setPrefHeight(300);
        btDelete.setMaxWidth(300);
        btDelete.setMaxHeight(300);
        this.getChildren().addAll(taTweets,cobClass,btDelete);
    }
}
