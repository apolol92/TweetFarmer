package menu_window;

import config_data.DatabaseConfigData;
import database_setup_window.DatabaseSetupWindow;
import file_manager.FileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import setup_window.SetupWindow;
import setup_window.SetupWindowController;
import tweet_farmer_window.TweetFarmerWindow;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by apolol92 on 20.05.2016.
 * This is the Menu Window Controller.
 */
public class MenuWindowController implements Initializable{
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
        update();

    }
    /**
     * Creates a new farmer
     * @param actionEvent
     */
    @FXML
    public void btCreateFarmerClick(ActionEvent actionEvent) {
        FileManager fileManager = new FileManager(tfFarmername.getText());
        if(fileManager.write_farmer(tfHashtags.getText().split(","),databaseConfigData,tfClasses.getText().split(","),
                true)) {
            if(databaseConfigData!=null) {
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
        ObservableList<String> items = FXCollections.observableArrayList (FileManager.listFarmers());
        this.lvFarmers.setItems(items);
    }

    /**
     * Controls if all textfields arent empty..
     * @param event
     */
    @FXML
    public void checkAllFilled(KeyEvent event) {
        if(tfFarmername.getText().trim().compareTo("")!=0 && tfHashtags.getText().trim().compareTo("")!=0&&tfClasses.getText().trim().compareTo("")!=0) {
            this.btCreateFarmer.setDisable(false);
        }
        else {
            this.btCreateFarmer.setDisable(true);
        }
    }

    /**
     * Is Database storage selected?
     */
    @FXML
    public void cbDatabaseStorageCheck(ActionEvent actionEvent) {
        if(cbDatabaseStorage.isSelected()) {
            this.databaseConfigData = new DatabaseConfigData();
            this.databaseSetupWindow = new DatabaseSetupWindow(this.databaseConfigData);
        }
        else {
            this.databaseConfigData = null;
            this.databaseSetupWindow.stage.close();
        }
    }

    /**
     * Open Twitter Key Settings..
     * @param actionEvent
     */
    public void openTwitterKeys(ActionEvent actionEvent) {
        SetupWindowController.firstStart = false;
        SetupWindow setupWindow = new SetupWindow();
    }


    /**
     * After clicking on Load Farmer
     * @param actionEvent
     */
    public void btLoadFarmerClick(ActionEvent actionEvent) {
        TweetFarmerWindow tweetFarmerWindow = new TweetFarmerWindow(lvFarmers.getSelectionModel().getSelectedItem().toString());
    }

    public void lvFarmersClicked(Event event) {
        this.btLoadFarmer.setDisable(false);
    }
}
