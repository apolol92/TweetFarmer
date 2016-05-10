package menu_window.scenes.panels;

import database_config_window.ConfigWindow;
import global.Global;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by apolol92 on 10.05.2016.
 * This class is used for showing the user create new farmer
 */
public class NewFarmerPanel extends VBox {
    /**
     * Textfield for FarmerName
     */
    private TextField tfFarmerName;
    /**
     * Label "Hashtags"
     */
    private Label lbHashtags;
    /**
     * Textfield Hashtags
     */
    private TextField tfHashtags;
    /**
     * Label Classes
     */
    private Label lbClasses;
    /**
     * Textfield Classes
     */
    private TextField tfClasses;
    /**
     * Checkbox for LocalStorage
     */
    private CheckBox cbLocalStorage;
    /**
     * Checkbox for DatabaseStorage
     */
    private CheckBox cbDatabaseStorage;
    /**
     * Button for creating a new Farmer
     */
    private Button btCreateNewFarmer;
    /**
     * Label for Farmer Name
     */
    private Label lbFarmerName;
    /**
     * Label for new Farmer
     */
    private Label lbNewFarmer;

    public NewFarmerPanel() {
        super();
        this.setPadding(new Insets(0,50,0,10));
        this.lbNewFarmer = new Label("New Farmer");
        this.lbNewFarmer.setFont(new Font(Global.FONT_FAMILY,24));
        this.lbFarmerName = new Label("Name");
        this.lbFarmerName.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.tfFarmerName = new TextField();
        this.tfFarmerName.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.lbHashtags = new Label("Hashtags");
        this.lbHashtags.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.tfHashtags = new TextField();
        this.tfHashtags.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.lbClasses = new Label("Classes");
        this.lbClasses.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.tfClasses = new TextField();
        this.tfClasses.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.cbLocalStorage = new CheckBox("Local Storage");
        this.cbLocalStorage.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.cbDatabaseStorage = new CheckBox("Database Storage");
        this.cbDatabaseStorage.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    //Open Database Config
                    ConfigWindow configWindow = new ConfigWindow();

                }
            }
        });
        this.cbDatabaseStorage.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.btCreateNewFarmer = new Button("New Farmer");
        this.btCreateNewFarmer.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.getChildren().addAll(this.lbNewFarmer,this.lbFarmerName,this.tfFarmerName,this.lbHashtags,this.tfHashtags,
                this.lbClasses,this.tfClasses,this.cbLocalStorage,this.cbDatabaseStorage,this.btCreateNewFarmer);
    }
}
