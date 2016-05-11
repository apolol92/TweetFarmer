package edit_tweets_window.scenes;

import com.sun.javafx.scene.control.skin.TableViewSkin;
import javafx.beans.NamedArg;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by gross on 11.05.2016.
 */
public class EditTweetsScene extends Scene {
    /**
     * Table-Column for Classes
     */
    private TableColumn tcClass;
    /**
     * Table-Column for Tweets
     */
    private TableColumn tcTweet;
    /**
     * Table Column for Actions
     */
    private TableColumn tcAction;
    /**
     * TableView for all tweets
     */
    private TableView tableView;
    private TableColumn tcText;

    public EditTweetsScene() {
        super(new TableView());
        this.tableView = (TableView)getRoot();
        this.tcTweet = new TableColumn("Text");
        this.tcClass = new TableColumn("Class");
        this.tcAction = new TableColumn("Action");
        this.tableView.getColumns().addAll(this.tcTweet,this.tcClass,this.tcAction);
    }
}
