package setup_window.scenes;

import farmer_file_manager.TwitterConfigData;
import global.Global;
import javafx.beans.NamedArg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import menu_window.MenuWindow;

import static global.Global.FONT_FAMILY;

/**
 * Created by apolol92 on 09.05.2016.
 * This Class is been used for setting up twitter access.
 */
public class SetupScene extends Scene {
    /**
     * Stage
     */
    private Stage stage;
    /**
     * Scene Layout
     */
    private VBox vbLayout;
    /**
     * Headline Label
     */
    private Label lbJustMoment;
    /**
     * Window description
     */
    private Label lbDescription;
    /**
     * Label Twitter Access Input
     */
    private Label lbTwitterAccess;
    /**
     * Label Twitter Consumer Key Input
     */
    private Label lbTwitterConsumerKey;
    /**
     * Textfield Twitter Consumer Key
     */
    private TextField tfTwitterConsumerKey;
    /**
     * Label Twitter Consumer Secret
     */
    private Label lbTwitterConsumerSecret;
    /**
     * Textfield Twitter Consumer Secret
     */
    private TextField tfTwitterConsumerSecret;
    /**
     * Label Twitter Access Token
     */
    private Label lbTwitterAccessToken;
    /**
     * Textfield Twitter Access Token
     */
    private TextField tfTwitterAccessToken;
    /**
     * Label Twitter Access Token Secret
     */
    private Label lbTwitterAccessTokenSecret;
    /**
     * Textfield Twitter Access Token Secret
     */
    private TextField tfTwitterAccessTokenSecret;
    /**
     * Button Setup
     */
    private Button btSetup;

    /**
     * Constructor, which build the window
     * @param stage
     */
    public SetupScene(Stage stage) {
        super(new VBox());
        this.vbLayout = (VBox)getRoot();
        this.vbLayout.setPadding(new Insets(10, 10, 10, 10));
        this.stage = stage;
        this.stage.setTitle("TweetFarmer - Setup");
        this.stage.setScene(this);
        this.createUI();
        this.stage.show();
    }

    private void createUI() {
        this.lbJustMoment = new Label("Just a Moment..");
        this.lbJustMoment.setFont(new Font(Global.FONT_FAMILY, 36));
        this.lbJustMoment.setStyle("-fx-font-weight: bold;");
        this.lbDescription = new Label("To access Twitter, you need your Twitter-Keys");
        this.lbDescription.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.lbDescription.setStyle("-fx-font-style: italic;");
        this.lbDescription.setPadding(new Insets(10,0,0,0));
        this.lbTwitterAccess = new Label("Twitter-Access");
        this.lbTwitterAccess.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.lbTwitterAccess.setStyle("-fx-font-weight: bold");
        this.lbTwitterAccess.setPadding(new Insets(10,0,0,0));
        this.lbTwitterConsumerKey = new Label("Consumer Key");
        this.lbTwitterConsumerKey.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.tfTwitterConsumerKey = new TextField();
        this.tfTwitterConsumerKey.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkAllFilled();
            }
        });
        this.tfTwitterConsumerKey.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.lbTwitterConsumerSecret = new Label("Consumer Secret");
        this.lbTwitterConsumerSecret.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.tfTwitterConsumerSecret = new TextField();
        this.tfTwitterConsumerSecret.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.tfTwitterConsumerSecret.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkAllFilled();
            }
        });
        this.lbTwitterAccessToken = new Label("Access Token");
        this.lbTwitterAccessToken.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.tfTwitterAccessToken = new TextField();
        this.tfTwitterAccessToken.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.tfTwitterAccessToken.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkAllFilled();
            }
        });
        this.lbTwitterAccessTokenSecret = new Label("Access Token Secret");
        this.lbTwitterAccessTokenSecret.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.tfTwitterAccessTokenSecret = new TextField();
        this.tfTwitterAccessTokenSecret.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.tfTwitterAccessTokenSecret.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkAllFilled();
            }
        });
        this.vbLayout.setMargin(this.tfTwitterAccessTokenSecret,new Insets(0,0,50,0));
        this.btSetup = new Button("Setup");
        HBox.setHgrow(this.btSetup, Priority.ALWAYS);
        this.btSetup.setMaxWidth(Double.MAX_VALUE);
        this.btSetup.setDisable(true);
        this.btSetup.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.btSetup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TwitterConfigData.writeTwitterConfigData(tfTwitterConsumerKey.getText(),tfTwitterConsumerSecret.getText(),tfTwitterAccessToken.getText(),tfTwitterAccessTokenSecret.getText());
                MenuWindow menuWindow = new MenuWindow(stage);
            }
        });
        this.vbLayout.getChildren().addAll(this.lbJustMoment,this.lbDescription,this.lbTwitterAccess,this.lbTwitterConsumerKey,
                this.tfTwitterConsumerKey,this.lbTwitterConsumerSecret,this.tfTwitterConsumerSecret,this.lbTwitterAccessToken,
                this.tfTwitterAccessToken,this.lbTwitterAccessTokenSecret,this.tfTwitterAccessTokenSecret,this.btSetup);

    }

    private void checkAllFilled() {
        if(tfTwitterAccessToken.getText().trim().compareTo("")!=0 && tfTwitterAccessTokenSecret.getText().trim().compareTo("")!=0&&tfTwitterConsumerSecret.getText().trim().compareTo("")!=0&&tfTwitterAccessToken.getText().compareTo("")!=0) {
            btSetup.setDisable(false);
        }
        else {
            btSetup.setDisable(true);
        }
    }
}
