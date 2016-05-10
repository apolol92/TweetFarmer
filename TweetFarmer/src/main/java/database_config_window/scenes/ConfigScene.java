package database_config_window.scenes;

import javafx.beans.NamedArg;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by apolol92 on 10.05.2016.
 * This Window give the user the possiblity to configure his/her database-access.
 */
public class ConfigScene extends Scene{
    /**
     * Textfield for Database Ip-Address
     */
    private TextField tfIpAddress;
    /**
     * Label for Port
     */
    private Label lbPort;
    /**
     * Textfield for Port
     */
    private TextField tfPort;
    /**
     * Label Database Typ
     */
    private Label lbDatabaseTyp;
    /**
     * Spinner DatabaseTyp
     */
    private Spinner<String> spDatabaseTyp;
    /**
     * Label Username
     */
    private Label lbUsername;
    /**
     * Textfield Username
     */
    private TextField tfUsername;
    /**
     * Label Password
     */
    private Label lbPassword;
    /**
     * Textfield Password
     */
    private TextField tfPassword;
    /**
     * Button Setup
     */
    private Button btSetup;
    /**
     * Label IP-Address
     */
    private Label lbIpAddress;
    /**
     * Stage
     */
    private Stage stage;
    /**
     * VBox
     */
    private VBox vbLayout;

    /**
     * Creates the ConfigScene
     * @param stage
     */
    public ConfigScene(Stage stage) {
        super(new VBox());
        this.vbLayout = (VBox)getRoot();
        this.lbIpAddress = new Label("IP-Address");
        this.tfIpAddress = new TextField();
        this.lbPort = new Label("Port");
        this.tfPort = new TextField();
        this.lbDatabaseTyp = new Label("Database-Typ");
        this.spDatabaseTyp = new Spinner<String>();
        this.lbUsername = new Label("Username");
        this.tfUsername = new TextField();
        this.lbPassword = new Label("Password");
        this.tfPassword = new TextField();
        this.btSetup = new Button("Setup");
        this.vbLayout.getChildren().addAll(this.lbIpAddress,this.tfIpAddress,this.lbPort,this.tfPort,this.lbDatabaseTyp,this.spDatabaseTyp,this.lbUsername,
                this.tfUsername, this.lbPassword, this.tfPassword, this.btSetup);
        this.vbLayout.setPadding(new Insets(10,10,10,10));
        this.stage = stage;
        this.stage.setTitle("Database Setup");
        this.stage.setScene(this);
        this.stage.show();

    }
}
