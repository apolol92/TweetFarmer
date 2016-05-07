package sql_manager;

import custom_twitter.Tweet;
import twitter4j.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apolol92 on 07.05.2016.
 */
public class SqlReader extends SqlManager {
    private String farmerName;

    /**
     * Create a DatabaseManager with following configurations
     *
     * @param databaseIp
     * @param databasePort
     * @param databasename
     * @param databaseTyp
     * @param databaseUsername
     * @param databasePassword
     */
    public SqlReader(String databaseIp, String databasePort, String databasename, String databaseTyp, String databaseUsername, String databasePassword, String farmerName) {
        super(databaseIp, databasePort, databasename, databaseTyp, databaseUsername, databasePassword);
        this.farmerName = farmerName;
    }

    public ArrayList<Tweet> getAllPairedTweetIdClass() {
        ArrayList<Tweet> tweetEntities = new ArrayList<Tweet>();
        Statement stmt = null;
        try {
            stmt = super.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+this.farmerName+"_Text");

            while(rs.next()) {
                tweetEntities.add(new Tweet(rs.getLong("id")));
            }
            rs.close();
            stmt.close();
            return tweetEntities;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
