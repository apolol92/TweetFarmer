package farmer_window.scenes.panels;

import custom_tweet.Tweet;
import farmer_window.FarmerWindow;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.Program;

/**
 * Created by apolol92 on 11.05.2016.
 * Panel for showing all Tweets.. only one at once..
 */
public class TweetViewPanel extends GridPane {
    /**
     * Name of current Farmer
     */
    private String farmername;
    /**
     * Place Holder till Twitter-Pofil-Image
     */
    private Button btImageTest;
    /**
     * ImageView for User Profil-Image
     */
    private ImageView ivProfil;
    /**
     * Label with Username
     */
    private Label lbUsername;
    /**
     * Label Screenname
     */
    private Label lbScreenname;
    /**
     * Label with Date
     */
    private Label lbDate;
    /**
     * Label with Tweet Text
     */
    private Label lbTweetText;

    /**
     * Cronstructor for building TweetViewPanel
     */
    public TweetViewPanel(String farmername) {
        this.farmername = farmername;
        //Image img = new Image(getClass().getResource("dummy_profil.png").toExternalForm());
        //this.ivProfil = new ImageView(Program.class.getResource("dummy_profil.png").toExternalForm());
        //this.ivProfil.setImage(img);
        this.btImageTest = new Button("Test\nImage");
        this.btImageTest.setMinWidth(100);
        this.btImageTest.setMinHeight(100);
        this.lbUsername = new Label("Robert");
        this.lbScreenname = new Label("@r87");
        this.lbDate = new Label("02-23.2016");
        this.lbTweetText = new Label("In nec integre vivendo insolens, cum an dicat errem, ut amet cibo sit.Wisi quaeque mediocritatem vel an, vix accusam voluptua accusata ea. Eum at suas repudiare, est no quodsi causae deleniti.\n In feugait detracto conclusionemque duo. Molestiae eloquentiam usu in, sit an prompta meliore. Eum deleniti adversarium in.\n");
        //TODO: Add Buttons
        //Add to Grid
        this.add(this.btImageTest,0,0,1,3);
        this.add(this.lbUsername,1,0,1,1);
        this.add(this.lbScreenname,2,0,1,1);
        this.add(this.lbDate,3,0,1,1);
        this.add(this.lbTweetText,1,1,3,3);

    }
}
