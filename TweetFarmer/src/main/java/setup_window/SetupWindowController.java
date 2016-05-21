package setup_window;

import config_data.TwitterConfigData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import menu_window.MenuWindow;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by apolol92 on 19.05.2016.
 * This is the Setup Window Controller..
 */
public class SetupWindowController implements Initializable{
    /**
     * Started from Beginning(true) or Menu(false)??
     */
    public static boolean firstStart = false;
    @FXML
    PasswordField tfTwitterConsumerKey;
    @FXML
    PasswordField tfTwitterConsumerSecret;
    @FXML
    PasswordField tfTwitterAccessToken;
    @FXML
    PasswordField tfTwitterAccessTokenSecret;
    @FXML
    Button btSetup;

    /**
     * After the Stage was created..
     * @param location
     * @param resources
     */
    public void initialize(URL location, ResourceBundle resources) {
        btSetup.setDisable(true);
    }

    /**
     * Button Setup Click Event
     * @param actionEvent
     */
    @FXML
    public void btSetupClick(ActionEvent actionEvent) {
        TwitterConfigData.writeTwitterConfigData(tfTwitterConsumerKey.getText(),tfTwitterConsumerSecret.getText(),tfTwitterAccessToken.getText(),tfTwitterAccessTokenSecret.getText());
        System.out.println(firstStart);
        if(firstStart) {
            MenuWindow menuWindow = new MenuWindow();
        }
        ((Stage)btSetup.getScene().getWindow()).close();
    }

    /**
     * Check if all passwordfield are filled.. then enable setup button..
     * @param event
     */
    @FXML
    public void checkAllFilled(KeyEvent event) {
        if(tfTwitterAccessToken.getText().trim().compareTo("")!=0 && tfTwitterAccessTokenSecret.getText().trim().compareTo("")!=0&&tfTwitterConsumerSecret.getText().trim().compareTo("")!=0&&tfTwitterAccessToken.getText().compareTo("")!=0) {
            btSetup.setDisable(false);
        }
        else {
            btSetup.setDisable(true);
        }
    }

}
