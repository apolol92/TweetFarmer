package menu_window;

import config_data.DatabaseConfigData;
import database_setup_window.DatabaseSetupWindow;
import file_manager.FarmerConfig;
import file_manager.FileManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import setup_window.SetupWindow;
import setup_window.SetupWindowController;
import sql.DatabaseProcExecuter;
import tweet_farmer_window.TweetFarmerWindow;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by apolol92 on 20.05.2016.
 * This is the Menu Window Controller.
 */
public class MenuWindowController implements Initializable {
    @FXML
    public ComboBox cobLanguage;
    /**
     * ListViews with farmers
     */
    @FXML
    ListView<String> lvFarmers;
    /**
     * Textfield for Farmername
     */
    @FXML
    public TextField tfFarmername;
    /**
     * Textfield for Hashtags
     */
    @FXML
    public TextField tfHashtags;
    /**
     * Textfield for Classes
     */
    @FXML
    public TextField tfClasses;
    /**
     * Checkbox for Database Storage
     */
    @FXML
    public CheckBox cbDatabaseStorage;
    /**
     * Button for Farmer Creation
     */
    @FXML
    public Button btCreateFarmer;
    /**
     * Button for loading a Farmer
     */
    public Button btLoadFarmer;
    /**
     * Container for databaseConfig
     */
    DatabaseConfigData databaseConfigData = null;
    /**
     * Window for databaseConfig
     */
    DatabaseSetupWindow databaseSetupWindow;

    /**
     * First thing after Window was loaded..
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cobLanguage.setConverter(new StringConverter<String>() {

            @Override
            public String toString(String object) {
                return object == null ? null : object.toString();
            }

            @Override
            public String fromString(String string) {
                return null;
            }
        });
        cobLanguage.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                checkAllFilled();
            }
        });
        lvFarmers.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode());
                if(event.getCode().toString().compareTo("DELETE")==0) {
                    if(lvFarmers.getSelectionModel().getSelectedItem()!=null) {
                        FarmerConfig farmerConfig = new FarmerConfig();
                        FileManager fileManager = new FileManager(lvFarmers.getSelectionModel().getSelectedItem().toString());
                        System.out.println(lvFarmers.getSelectionModel().getSelectedItem().toString());
                        farmerConfig = fileManager.readFarmer(lvFarmers.getSelectionModel().getSelectedItem().toString());
                        if (farmerConfig.isDatabaseStorage()) {
                            DatabaseConfigData databaseConfigData = new DatabaseConfigData();
                            databaseConfigData.readData(farmerConfig.getName());
                            try {
                                DatabaseProcExecuter databaseProcExecuter = new DatabaseProcExecuter(databaseConfigData.getIp(), Integer.parseInt(databaseConfigData.getPort()),
                                        databaseConfigData.getDbTyp(), databaseConfigData.getDatabasename(), databaseConfigData.getUsername(), databaseConfigData.getPassword());
                                databaseProcExecuter.connect();
                                databaseProcExecuter.execProcDeleteFarmer(farmerConfig.getName());
                                databaseProcExecuter.disconnect();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        FileManager.deleteFarmer(lvFarmers.getSelectionModel().getSelectedItem().toString());
                        update();
                    }
                }
            }
        });
        update();
    }

    /**
     * Creates a new farmer
     *
     * @param actionEvent
     */
    @FXML
    public void btCreateFarmerClick(ActionEvent actionEvent) {
        FileManager fileManager = new FileManager(tfFarmername.getText());
        if (fileManager.write_farmer(tfHashtags.getText().split(","), databaseConfigData, tfClasses.getText().split(","),
                true,cobLanguage.getSelectionModel().getSelectedItem().toString())) {
            if (databaseConfigData != null) {
                System.out.println(this.databaseConfigData.getIp());
                databaseConfigData.writeData(tfFarmername.getText());

            }
            update();
            tfFarmername.setText("");
            tfClasses.setText("");
            tfHashtags.setText("");

        }
    }

    /**
     * Update MenuWindow
     */
    private void update() {
        ObservableList<String> items = FXCollections.observableArrayList(FileManager.listFarmers());
        this.lvFarmers.setItems(items);
    }

    /**
     * Controls if all textfields arent empty..
     */
    @FXML
    public void checkAllFilled() {
        if (tfFarmername.getText().trim().compareTo("") != 0 && tfHashtags.getText().trim().compareTo("") != 0 && tfClasses.getText().trim().compareTo("") != 0) {
            if (cobLanguage.getSelectionModel().getSelectedItem() != null) {
                this.btCreateFarmer.setDisable(false);
            }
        } else {
            this.btCreateFarmer.setDisable(true);
        }
    }

    /**
     * Is Database storage selected?
     */
    @FXML
    public void cbDatabaseStorageCheck(ActionEvent actionEvent) {
        if (cbDatabaseStorage.isSelected()) {
            this.databaseConfigData = new DatabaseConfigData();
            this.databaseSetupWindow = new DatabaseSetupWindow(this.databaseConfigData);
        } else {
            this.databaseConfigData = null;
            this.databaseSetupWindow.stage.close();
        }
    }

    /**
     * Open Twitter Key Settings..
     *
     * @param actionEvent
     */
    public void openTwitterKeys(ActionEvent actionEvent) {
        SetupWindowController.firstStart = false;
        SetupWindow setupWindow = new SetupWindow();
    }


    /**
     * After clicking on Load Farmer
     *
     * @param actionEvent
     */
    public void btLoadFarmerClick(ActionEvent actionEvent) {
        TweetFarmerWindow tweetFarmerWindow = new TweetFarmerWindow(lvFarmers.getSelectionModel().getSelectedItem().toString());
    }

    /**
     * Afer clicking on lvFarmers
     * @param event
     */
    public void lvFarmersClicked(Event event) {
        this.btLoadFarmer.setDisable(false);
    }
}
