package database_config_window.scenes;

import database.DatabaseConfigData;
import javafx.beans.NamedArg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by apolol92 on 10.05.2016.
 * This Window give the user the possiblity to configure his/her database-access.
 */
public class ConfigScene extends Scene{
    private Label lbDatabasename;
    private TextField tfDatabasename;
    private DatabaseConfigData databaseConfigData;
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
    private PasswordField pfPassword;
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
    public ConfigScene(Stage stage, DatabaseConfigData dConfigData) {
        super(new VBox());
        this.databaseConfigData = dConfigData;
        this.vbLayout = (VBox)getRoot();
        this.lbIpAddress = new Label("IP-Address");
        this.tfIpAddress = new TextField();
        this.tfIpAddress.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkAllFilled();
            }
        });
        this.lbDatabasename = new Label("Databasename");
        this.tfDatabasename = new TextField();
        this.tfDatabasename.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkAllFilled();
            }
        });

        this.lbPort = new Label("Port");
        this.tfPort = new TextField();
        this.tfPort.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkAllFilled();
            }
        });
        this.lbDatabaseTyp = new Label("Database-Typ");
        this.spDatabaseTyp = new Spinner<String>();
        this.lbUsername = new Label("Username");
        this.tfUsername = new TextField();
        this.tfUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkAllFilled();
            }
        });
        this.lbPassword = new Label("Password");
        this.pfPassword = new PasswordField();
        this.pfPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkAllFilled();
            }
        });
        this.btSetup = new Button("Setup");
        this.btSetup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                databaseConfigData.setIp(tfIpAddress.getText());
                databaseConfigData.setPort(tfPort.getText());
                databaseConfigData.setDatabasename(tfDatabasename.getText());
                databaseConfigData.setDbTyp(spDatabaseTyp.getValue());
                databaseConfigData.setUsername(tfUsername.getText());
                databaseConfigData.setPassword(pfPassword.getText());
                stage.close();
            }
        });
        this.btSetup.setDisable(true);
        this.vbLayout.getChildren().addAll(this.lbIpAddress,this.tfIpAddress,this.lbDatabasename,this.tfDatabasename,this.lbPort,this.tfPort,this.lbDatabaseTyp,this.spDatabaseTyp,this.lbUsername,
                this.tfUsername, this.lbPassword, this.pfPassword, this.btSetup);
        this.vbLayout.setPadding(new Insets(10,10,10,10));
        this.stage = stage;
        this.stage.setTitle("Database Setup");
        this.stage.setScene(this);
        this.stage.show();

    }

    /**
     * Are all textfields are fillled?
     */
    private void checkAllFilled() {
        if(this.tfDatabasename.getText().trim().compareTo("")!=0&&this.tfIpAddress.getText().trim().compareTo("")!=0 && this.pfPassword.getText().trim().compareTo("")!=0
                && this.tfPort.getText().trim().compareTo("")!=0 && this.tfUsername.getText().trim().compareTo("")!=0
                ) {
            this.btSetup.setDisable(false);
        }
        else {
            this.btSetup.setDisable(true);
        }
    }
}
