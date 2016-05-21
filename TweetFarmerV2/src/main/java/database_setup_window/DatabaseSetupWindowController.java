package database_setup_window;

import config_data.DatabaseConfigData;
import file_manager.FileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by apolol92 on 20.05.2016.
 * Creates the DatabaseSetupWindowController.. it controls the UI
 */
public class DatabaseSetupWindowController implements Initializable{
    /**
     * ListView which contains all saved databases
     */
    public ListView lvDatabases;
    /**
     * With this button you can select a database
     */
    public Button btSelectDb;
    /**
     * Reference on databaseConfigData in MenuWindowController NOT OVERWRITE!!!
     */
    DatabaseConfigData databaseConfigData;
    /**
     * Textfield for Databasename
     */
    @FXML
    TextField tfDatabasename;
    /**
     * Textfield for Database IP
     */
    @FXML
    TextField tfDatabaseIp;
    /**
     * Textfield for Database Username
     */
    @FXML
    TextField tfDatabaseUsername;
    /**
     * PasswordField for Database Password
     */
    @FXML
    PasswordField tfDatabasePassword;
    /**
     * TextField for DatabasePort
     */
    @FXML
    TextField tfDatabasePort;
    /**
     * ComboBOx for DatabaseTyp
     */
    @FXML
    ComboBox<String> cobDatabaseTyps;
    /**
     * Button to create a Database
     */
    @FXML
    Button btCreateDatabase;

    /**
     * Constructor for DatabaseSetupWindowController
     */
    public DatabaseSetupWindowController(DatabaseConfigData databaseConfigData) {
        this.databaseConfigData = databaseConfigData;
    }

    /**
     * Checks if all textfields are field.. then enable btCreateDatabase
     */
    @FXML
    public void checkAllFilled(KeyEvent event) {
        if(tfDatabasename.getText().trim().compareTo("")!=0 && tfDatabaseIp.getText().trim().compareTo("")!=0&&tfDatabaseUsername.getText().trim().compareTo("")!=0
                &&tfDatabasePassword.getText().trim().compareTo("")!=0&&tfDatabasePort.getText().trim().compareTo("")!=0) {
            this.btCreateDatabase.setDisable(false);
        }
        else {
            this.btCreateDatabase.setDisable(true);
        }
    }

    /**
     * Enable btSelectDatabase under following condition
     */
    public void btSelectDatabase() {
        if(lvDatabases.getSelectionModel().getSelectedItem()!=null) {
            this.databaseConfigData.readData(lvDatabases.getSelectionModel().getSelectedItem().toString().split("\\(")[0]);
            System.out.println(this.databaseConfigData.getIp());
            System.out.println(lvDatabases.getSelectionModel().getSelectedItem().toString().split("\\(")[0]);
            ((Stage)tfDatabaseIp.getScene().getWindow()).close();
        }
    }

    /**
     * Action for click on btCreateDatabase
     * @param actionEvent
     */
    public void btCreateDatabaseClick(ActionEvent actionEvent) {
        databaseConfigData.setIp(tfDatabaseIp.getText());
        databaseConfigData.setPort(tfDatabasePort.getText());
        databaseConfigData.setDatabasename(tfDatabasename.getText());
        cobDatabaseTyps.setConverter(new StringConverter<String>() {

            @Override
            public String toString(String object) {
                return object == null ? null : object.toString();
            }

            @Override
            public String fromString(String string) {
                return null;
            }
        });
        String output = (String) cobDatabaseTyps.getSelectionModel().getSelectedItem().toString();
        System.out.println(output);
        databaseConfigData.setDbTyp(output);
        databaseConfigData.setUsername(tfDatabaseUsername.getText());
        databaseConfigData.setPassword(tfDatabasePassword.getText());
        ((Stage)tfDatabaseIp.getScene().getWindow()).close();
    }

    /**
     * Initialize FXML
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = FXCollections.observableArrayList(FileManager.listDatabaseFarmers());
        this.lvDatabases.setItems(items);

    }
}
