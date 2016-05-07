package sql_manager;

import custom_twitter.Tweet;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by apolol92 on 07.05.2016.
 */
public class SqlInserter extends SqlManager {
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
    public SqlInserter(String databaseIp, String databasePort, String databasename, String databaseTyp, String databaseUsername, String databasePassword) {
        super(databaseIp, databasePort, databasename, databaseTyp, databaseUsername, databasePassword);
    }

    public boolean insertClasses(String farmerName, String[] classes) {
        try {
            for(int i = 0; i < classes.length; i++) {
                Statement mStmt = super.connection.createStatement();
                System.out.println(farmerName);
                mStmt.executeUpdate("INSERT INTO " + farmerName + "_classes(id,classes) VALUES(" + i + ",'" + classes[i] + "')");
                mStmt.close();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertTweet(String farmerName, Tweet tweet, int classId) {
        try {
            Statement mStmt = super.connection.createStatement();
            System.out.println("INSERT INTO "+farmerName+"_Text(id,content,class_id) VALUES("+tweet.id+","+tweet.text+","+classId+")");
            tweet.text = tweet.text.replace("'","");
            mStmt.executeUpdate("INSERT INTO "+farmerName+"_Text(id,content,class_id) VALUES("+tweet.id+",'"+tweet.text+"',"+classId+")");

            mStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
