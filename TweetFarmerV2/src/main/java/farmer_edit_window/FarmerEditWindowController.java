package farmer_edit_window;

import config_data.DatabaseConfigData;
import file_manager.FarmerConfig;
import file_manager.FileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by apolol92 on 21.05.2016.
 * This is the controller of the FarmerEditWindow
 */
public class FarmerEditWindowController implements Initializable {
    /**
     * This textfield is the input for hashtags
     */
    @FXML
    public TextField tfHashtags;
    /**
     * This combobox gives the user a language selection for his tweets
     */
    @FXML
    public ComboBox cobLanaguage;
    /**
     * Agree for changes
     */
    @FXML
    public Button btEdit;
    /**
     * Current farmername
     */
    private String farmername;

    /**
     * This is the constructor for the FarmerEditWindowController
     * @param farmername
     */
    public FarmerEditWindowController(String farmername) {
        this.farmername = farmername;
    }

    /**
     * Initialize method for UI
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //fill ui..
        FarmerConfig farmerConfig = new FarmerConfig();
        farmerConfig = new FileManager(farmername).readFarmer(farmername);
        for (String h : farmerConfig.getHashtags()) {
            tfHashtags.setText(tfHashtags.getText() + "," + h);
            tfHashtags.setText(tfHashtags.getText().substring(1));
        }
        cobLanaguage.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                return object == null ? null : object.toString();
            }

            @Override
            public String fromString(String string) {
                return null;
            }
        });
        cobLanaguage.getSelectionModel().select(farmerConfig.getLanguage());


    }

    /**
     * After click on btEdit
     * @param actionEvent
     */
    public void btEditClicked(ActionEvent actionEvent) {
        FarmerConfig farmerConfig = new FarmerConfig();
        farmerConfig = new FileManager(farmername).readFarmer(farmername);
        //Only modify main_config
        try {
            FileUtils.forceDelete(new File(FileManager.FARMERS_PATH + farmername + "/main_config.xml"));
            farmerConfig.setHashtags(tfHashtags.getText().split(","));
            farmerConfig.setLanguage(cobLanaguage.getSelectionModel().getSelectedItem().toString());
            DatabaseConfigData databaseConfigData = new DatabaseConfigData();
            if(farmerConfig.isDatabaseStorage()) {
                databaseConfigData.readData(farmername);
            }
            new FileManager(farmername).edit_write_farmer(farmerConfig.getHashtags(),databaseConfigData,farmerConfig.getClasses(),true,farmerConfig.getLanguage());
            ((Stage)cobLanaguage.getScene().getWindow()).close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
