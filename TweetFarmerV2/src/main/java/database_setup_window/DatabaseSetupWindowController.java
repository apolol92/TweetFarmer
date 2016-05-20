package database_setup_window;

import config_data.DatabaseConfigData;
import file_manager.FileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by apolol92 on 20.05.2016.
 */
public class DatabaseSetupWindowController implements Initializable{
    public ListView lvDatabases;
    public Button btSelectDb;
    DatabaseConfigData databaseConfigData;
    @FXML
    TextField tfDatabasename;
    @FXML
    TextField tfDatabaseIp;
    @FXML
    TextField tfDatabaseUsername;
    @FXML
    TextField tfDatabasePassword;
    @FXML
    TextField tfDatabasePort;
    @FXML
    ComboBox<String> cobDatabaseTyps;
    @FXML
    Button btCreateDatabase;

    public DatabaseSetupWindowController(DatabaseConfigData databaseConfigData) {
        this.databaseConfigData = databaseConfigData;
    }

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


    public void btSelectDatabase() {
        if(lvDatabases.getSelectionModel().getSelectedItem()!=null) {
            this.databaseConfigData = new DatabaseConfigData();
            this.databaseConfigData.readData(lvDatabases.getSelectionModel().getSelectedItem().toString().split("\\(")[0]);
            System.out.println(lvDatabases.getSelectionModel().getSelectedItem().toString().split("\\(")[0]);
            ((Stage)tfDatabaseIp.getScene().getWindow()).close();
        }
    }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = FXCollections.observableArrayList(FileManager.listDatabaseFarmers());
        this.lvDatabases.setItems(items);

    }
}
