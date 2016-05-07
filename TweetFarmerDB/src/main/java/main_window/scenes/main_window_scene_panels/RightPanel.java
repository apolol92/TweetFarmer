package main_window.scenes.main_window_scene_panels;

import farmer_file_manager.FarmerFileConfigData;
import farmer_file_manager.FarmerFileWriter;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBox;
import sql_manager.SqlTableCreator;

/**
 * Created by apolol92 on 06.05.2016.
 * This class give the possibility to create a new farmer.
 * The user have to enter the farmer-name, hashtags that he want to farm.., Database-Informations, Twitter-Data.
 * The farmer will be saved in a xml-file.
 */
public class RightPanel extends VBox {
    /**
     * This label is the headline of the right panel
     */
    private Label lbNewFarmer;
    /**
     * This constant String contains the Text of lbNewFarmer
     */
    private final String LB_NEW_FARMER_STR = "New Farmer";
    /**
     * This horizontal box will contain lbName and tfName
     */
    private VBox vbName;
    /**
     *  This is the label name
     */
    private Label lbName;
    /**
     * This constant String contains the text of the lbName
     */
    private final String LB_NAME_STR = "Name";
    /**
     * Enter the farmer name in this text field
     */
    private TextField tfName;
    /**
     * This horizontal box will contain lbHastags and tfHashtags
     */
    private VBox vbHashtags;
    /**
     *  This is the label name
     */
    private Label lbHashtags;
    /**
     * This constant String contains the text of the lbHashtags
     */
    private final String LB_HASHTAGS_STR = "Hashtags";
    /**
     * Enter the hashtags in this text field
     */
    private TextField tfHashtags;
    /**
     * This horizontal box will contain lbDatabaseIp and tfDatabaseIp
     */
    private VBox vbDatabaseIp;
    /**
     *  This is the label Database-IP
     */
    private Label lbDatabaseIp;
    /**
     * This constant String contains the text of the lbDatabaseIp
     */
    private final String LB_DATABASE_IP_STR = "Database-IP";
    /**
     * Enter the Database-IP in this text field
     */
    private TextField tfDatabaseIp;
    /**
     * This horizontal box will contain lbDatabaseName and tfDatabaseName
     */
    /**
     * This horizontal box will contain lbDatabasePort and tfDatabasePort
     */
    private VBox vbDatabasePort;
    /**
     *  This is the label Database-Port
     */
    private Label lbDatabasePort;
    /**
     * This constant String contains the text of the lbDatabasePort
     */
    private final String LB_DATABASE_PORT_STR = "Database-Port";
    /**
     * Enter the Database-Port in this text field
     */
    private TextField tfDatabasePort;
    /**
     * This horizontal box will contain lbDatabaseName and tfDatabaseName
     */
    private VBox vbDatabaseName;
    /**
     *  This is the label Database-Name
     */
    private Label lbDatabaseName;
    /**
     * This constant String contains the text of the lbDatabaseName
     */
    private final String LB_DATABASE_NAME_STR = "Database-Name";
    /**
     * Enter the Database-Name in this text field
     */
    private TextField tfDatabaseName;
    /**
     * This horizontal box will contain lbDatabaseName and tfDatabaseName
     */
    private VBox vbDatabaseUsername;
    /**
     *  This is the label Database-Username
     */
    private Label lbDatabaseUsername;
    /**
     * This constant String contains the text of the lbDatabaseUsername
     */
    private final String LB_DATABASE_USERNAME_STR = "Database-Username";
    /**
     * Enter the Database-Username in this text field
     */
    private TextField tfDatabaseUsername;
    /**
     * This horizontal box will contain lbDatabasePassword and tfDatabasePassword
     */
    private VBox vbDatabasePassword;
    /**
     *  This is the label Database-Password
     */
    private Label lbDatabasePassword;
    /**
     * This constant String contains the text of the lbDatabasePassword
     */
    private final String LB_DATABASE_PASSWORD_STR = "Database-Password";
    /**
     * Enter the Database-Password in this text field
     */
    private TextField tfDatabasePassword;
    /**
     * This horizontal box will contain lbDatabasePassword and tfDatabasePassword
     */
    private VBox vbDatabaseTyp;
    /**
     *  This is the label Database-Typ
     */
    private Label lbDatabaseTyp;
    /**
     * This constant String contains the text of the lbDatabaseTyp
     */
    private final String LB_DATABASE_TYP_STR = "Database-Typ";
    /**
     * Enter the Database-Typ in this spinner
     */
    private TextField tfDatabaseTyp;
    /**
     * This horizontal box will contain lbDatabasePassword and tfDatabaseTyp
     */
    private VBox vbTwitterAccessToken;
    /**
     *  This is the label Twitter-Token
     */
    private Label lbTwitterAccessToken;
    /**
     * This constant String contains the text of the lbTwitterToken
     */
    private final String LB_TWITTER_ACCESS_TOKEN_STR = "Twitter-Access-Token";
    /**
     * This text field will contain the twitter key
     */
    private TextField tfTwitterAccessToken;
    /**
     * This horizontal box will contain lbDatabasePassword and tfDatabaseTyp
     */
    private VBox vbTwitterAccessTokenSecret;
    /**
     *  This is the label Twitter-Token
     */
    private Label lbTwitterAccessTokenSecret;
    /**
     * This constant String contains the text of the lbTwitterToken
     */
    private final String LB_TWITTER_ACCESS_TOKEN_SECRET_STR = "Twitter-Access-Token-Secret";
    /**
     * Enter the Twitter-Token in this text field
     */
    private TextField tfTwitterAccessTokenSecret;
    /**
     * This horizontal box will contain lbClasses and tfClasses
     */
    private VBox vbClasses;
    /**
     *  This is the label Classes
     */
    private Label lbClasses;
    /**
     * This constant String contains the text of the lbClasses
     */
    private final String LB_CLASSES_STR = "Classes";
    /**
     * Enter the Twitter-Token in this text field
     */
    private TextField tfClasses;
    /**
     * Create-Button
     */
    private Button btCreate;
    /**
     * This constant String is the text of btCreate
     */
    private final String BT_FARMER_STR = "Create Farmer";

    private final LeftPanel leftPanel;
    /**
     * Constructor with init
     */
    public RightPanel(final LeftPanel leftPanel) {
        //Reference at leftPanel
        this.leftPanel = leftPanel;
        //New Farmer
        this.lbNewFarmer = new Label(this.LB_NEW_FARMER_STR);
        //Name
        this.vbName = new VBox();
        this.lbName = new Label(this.LB_NAME_STR);
        this.tfName = new TextField();
        this.vbName.getChildren().addAll(this.lbName,this.tfName);
        //Hashtags
        this.vbHashtags = new VBox();
        this.lbHashtags = new Label(this.LB_HASHTAGS_STR);
        this.tfHashtags = new TextField();
        this.vbHashtags.getChildren().addAll(this.lbHashtags,this.tfHashtags);
        //DatabaseIp
        this.vbDatabaseIp = new VBox();
        this.lbDatabaseIp = new Label(this.LB_DATABASE_IP_STR);
        this.tfDatabaseIp = new TextField();
        this.vbDatabaseIp.getChildren().addAll(this.lbDatabaseIp,this.tfDatabaseIp);
        //DatabasePort
        this.vbDatabasePort = new VBox();
        this.lbDatabasePort = new Label(this.LB_DATABASE_PORT_STR);
        this.tfDatabasePort = new TextField();
        this.vbDatabasePort.getChildren().addAll(this.lbDatabasePort,this.tfDatabasePort);
        //DatabaseName
        this.vbDatabaseName = new VBox();
        this.lbDatabaseName = new Label(this.LB_DATABASE_NAME_STR);
        this.tfDatabaseName = new TextField();
        this.vbDatabaseName.getChildren().addAll(this.lbDatabaseName,this.tfDatabaseName);
        //DatabasePassword
        this.vbDatabasePassword = new VBox();
        this.lbDatabasePassword = new Label(this.LB_DATABASE_PASSWORD_STR);
        this.tfDatabasePassword = new TextField();
        this.vbDatabasePassword.getChildren().addAll(this.lbDatabasePassword,this.tfDatabasePassword);
        //DatabaseTyp
        this.vbDatabaseTyp = new VBox();
        this.lbDatabaseTyp = new Label(this.LB_DATABASE_TYP_STR);
        this.tfDatabaseTyp = new TextField();
        this.vbDatabaseTyp.getChildren().addAll(this.lbDatabaseTyp,this.tfDatabaseTyp);
        //DatabaseUsername
        this.vbDatabaseUsername = new VBox();
        this.lbDatabaseUsername = new Label(this.LB_DATABASE_USERNAME_STR);
        this.tfDatabaseUsername = new TextField();
        this.vbDatabaseUsername.getChildren().addAll(this.lbDatabaseUsername,this.tfDatabaseUsername);
        //DatabasePassword
        this.vbDatabasePassword = new VBox();
        this.lbDatabasePassword = new Label(this.LB_DATABASE_PASSWORD_STR);
        this.tfDatabasePassword = new TextField();
        this.vbDatabasePassword.getChildren().addAll(this.lbDatabasePassword,this.tfDatabasePassword);
        //DatabaseTyp
        this.vbDatabaseTyp = new VBox();
        this.lbDatabaseTyp = new Label(this.LB_DATABASE_TYP_STR);
        this.tfDatabaseTyp = new TextField();
        this.vbDatabaseTyp.getChildren().addAll(this.lbDatabaseTyp,this.tfDatabaseTyp);
        //TwitterAccessToken
        this.vbTwitterAccessToken = new VBox();
        this.lbTwitterAccessToken = new Label(this.LB_TWITTER_ACCESS_TOKEN_STR);
        this.tfTwitterAccessToken = new TextField();
        this.vbTwitterAccessToken.getChildren().addAll(this.lbTwitterAccessToken,this.tfTwitterAccessToken);
        //TwitterAccessTokenSecret
        this.vbTwitterAccessTokenSecret = new VBox();
        this.lbTwitterAccessTokenSecret = new Label(this.LB_TWITTER_ACCESS_TOKEN_SECRET_STR);
        this.tfTwitterAccessTokenSecret = new TextField();
        this.vbTwitterAccessTokenSecret.getChildren().addAll(this.lbTwitterAccessTokenSecret,this.tfTwitterAccessTokenSecret);
        //Classes
        this.vbClasses = new VBox();
        this.lbClasses = new Label(this.LB_CLASSES_STR);
        this.tfClasses = new TextField();
        this.vbClasses.getChildren().addAll(this.lbClasses,this.tfClasses);
        //btCreate
        this.btCreate = new Button(this.BT_FARMER_STR);
        this.btCreate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FarmerFileConfigData farmerFileConfigData = new FarmerFileConfigData(tfName.getText(), tfHashtags.getText(), tfDatabaseIp.getText(), tfDatabasePort.getText(),tfDatabaseName.getText(), tfDatabaseName.getText(), tfDatabasePassword.getText(),
                        tfDatabaseTyp.getText(), tfClasses.getText(), tfTwitterAccessToken.getText(), tfTwitterAccessTokenSecret.getText());
                FarmerFileWriter farmerFileWriter = new FarmerFileWriter();
                //Write Farmer in file system
                boolean check = farmerFileWriter.writeFarmer(farmerFileConfigData);
                if(check) {
                    //SQL CREATE TABLE
                    SqlTableCreator sqlTableCreator = new SqlTableCreator(tfDatabaseIp.getText(), tfDatabasePort.getText(),tfDatabaseName.getText(), tfDatabaseTyp.getText(),tfDatabaseUsername.getText(), tfDatabasePassword.getText(),
                            tfName.getText());
                    sqlTableCreator.connectToDatabase();
                    sqlTableCreator.createTables();
                    sqlTableCreator.disconnect();
                    //Update ListView in LeftPanel
                    leftPanel.update();
                }
            }
        });
        this.getChildren().addAll(this.lbNewFarmer,this.vbName,this.vbHashtags,this.vbDatabaseIp,this.vbDatabasePort,this.vbDatabaseName,this.vbDatabaseUsername,this.vbDatabasePassword,this.vbDatabaseTyp,
                this.vbTwitterAccessToken,this.vbTwitterAccessTokenSecret,this.vbClasses,this.btCreate);
    }





}
