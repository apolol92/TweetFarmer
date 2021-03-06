package sql;


import java.sql.Statement;

/**
 * Created by apolol92 on 19.05.2016.
 * This class creates all in the database
 */
public class DatabaseCreator extends SqlManager {

    /**
     * Constructor
     * @param dbip
     * @param dbport
     * @param dbtyp
     * @param dbname
     * @param dbuser
     * @param dbpassword
     */
    public DatabaseCreator(String dbip, int dbport, String dbtyp, String dbname, String dbuser, String dbpassword) {
        super(dbip,dbport,dbtyp,dbname,dbuser,dbpassword);
    }

    /**
     * Creates all needed tables
     * @return
     */
    public boolean createTables() {
        String sqlFarmers ="";
        String sqlClasses = "";
        String sqlFarmerTweets = "";
        if(super.getDbtyp().compareTo(SqlManager.TYP_POSTGRESQL)==0) {
            sqlFarmers = "CREATE TABLE IF NOT EXISTS farmers" +
                    "(ID BIGSERIAL PRIMARY KEY, "+
                    "name TEXT UNIQUE);";
            sqlClasses = "CREATE TABLE IF NOT EXISTS farmer_classes"+
                    "(ID BIGSERIAL PRIMARY KEY, "+
                    "FARMER_ID BIGINT REFERENCES farmers (ID), "+
                    "NAME TEXT NOT NULL);";
            sqlFarmerTweets = "CREATE TABLE IF NOT EXISTS farmer_tweets"+
                    "(ID BIGINT PRIMARY KEY, "+
                    "tweet_text TEXT, "+
                    "class_id BIGINT REFERENCES farmer_classes(ID), "+
                    "likes INTEGER, "+
                    "retweets INTEGER);";
        }
        if(sqlFarmers.compareTo("")!=0&&sqlClasses.compareTo("")!=0&&sqlFarmerTweets.compareTo("")!=0) {
            try {
                Statement stmt = super.connection.createStatement();
                stmt.executeUpdate(sqlFarmers);
                stmt.executeUpdate(sqlClasses);
                stmt.executeUpdate(sqlFarmerTweets);
                stmt.close();
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    /**
     * Creates all needed procedures
     * @return
     */
    public boolean createProcedures() {
        try {
            Statement stmt = super.connection.createStatement();
            stmt.executeUpdate(getProcInsertFarmer());
            stmt.executeUpdate(getProcInsertClass());
            stmt.executeUpdate(getProcInsertTweet());
            stmt.executeUpdate(getProcSelectTweetsFrom());
            stmt.executeUpdate(getProcGetClassesFromFarmername());
            stmt.executeUpdate(getProcDeleteFarmer());
            stmt.executeUpdate(getProcDeleteTweetFromFarmer());
            stmt.executeUpdate(getProcUpdateTweetFromFarmer());
            stmt.close();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Creates the procedure for inserting farmers
     * @return
     */
    private String getProcInsertFarmer() {
        if(super.getDbtyp().compareTo(SqlManager.TYP_POSTGRESQL)==0) {
            return "CREATE OR REPLACE FUNCTION insertFarmer(farmername TEXT) RETURNS BIGINT AS $$ " +
                    "DECLARE max_id BIGINT;" +
                    "BEGIN " +
                    "INSERT INTO farmers(name) VALUES(farmername); " +
                    "SELECT MAX(id) INTO max_id FROM farmers; " +
                    "RETURN max_id; " +
                    "END; $$ LANGUAGE plpgsql;";
        }
        return "";
    }

    /**
     * Creates the procedure for inserting classes
     * @return
     */
    private String getProcInsertClass() {
        if(super.getDbtyp().compareTo(SqlManager.TYP_POSTGRESQL)==0) {
            return "CREATE OR REPLACE FUNCTION insertClass(farmerId BIGINT, classname TEXT) RETURNS BIGINT AS $$ " +
                    "DECLARE max_id BIGINT;" +
                    "BEGIN " +
                    "INSERT INTO farmer_classes(farmer_id,name) VALUES(farmerId, classname); " +
                    "SELECT MAX(id) INTO max_id FROM farmer_classes; " +
                    "RETURN max_id; " +
                    "END; $$ LANGUAGE plpgsql;";
        }
        return "";
    }

    /**
     * Creates the procedure for getting all classes of one farmer
     * @return
     */
    public String getProcGetClassesFromFarmername() {
        if(super.getDbtyp().compareTo(SqlManager.TYP_POSTGRESQL)==0) {
            return "CREATE OR REPLACE FUNCTION getProcGetClassesFromFarmername(farmername TEXT) RETURNS TABLE(class_name TEXT, class_id BIGINT) AS $$ " +
                    "DECLARE max_id BIGINT;" +
                    "BEGIN " +
                    "RETURN QUERY "+"SELECT farmer_classes.name, farmer_classes.id FROM farmer_classes " +
                    "INNER JOIN "+
                    "farmers ON farmers.id = farmer_classes.farmer_id WHERE farmers.name=farmername;"+
                    "END; $$ LANGUAGE plpgsql;";
        }
        return "";
    }

    /**
     * Creates the procedure for selecting tweets of a farmer
     * @return
     */
    private String getProcSelectTweetsFrom() {
        if(super.getDbtyp().compareTo(SqlManager.TYP_POSTGRESQL)==0) {
            return "CREATE OR REPLACE FUNCTION selectTweetsFrom(farmer_name TEXT) "+
                    "RETURNS TABLE(fname TEXT, ftext TEXT, flikes INTEGER, rtweets INTEGER, tid BIGINT) AS $$ "+
                    "BEGIN " +
                    " RETURN QUERY " + getSelectTweetsFrom() + " END; $$ LANGUAGE plpgsql;";

        }
        return "";
    }


    /**
     * Select-Query for getProcSelectTweetsFrom
     * @return
     */
    private String getSelectTweetsFrom() {
        return "SELECT farmer_classes.name, farmer_tweets.tweet_text, farmer_tweets.likes, farmer_tweets.retweets, farmer_tweets.id FROM farmers "+
                    "INNER JOIN " +
                    "farmer_classes ON farmers.id=farmer_classes.farmer_id "+
                    "INNER JOIN " +
                    "farmer_tweets ON farmer_classes.ID = farmer_tweets.class_id "+
                    "WHERE farmers.id IN (SELECT id FROM farmers WHERE name = farmer_name);";
    }

    public boolean createTriggers() {
        return false;
    }


    /**
     * Procedure for inserting a tweet
     * @return
     */
    public String getProcInsertTweet() {
        if(this.getDbtyp().compareTo(SqlManager.TYP_POSTGRESQL)==0) {
            return "CREATE OR REPLACE FUNCTION insertTweet(tweetId BIGINT, classId BIGINT, tweetTxt TEXT, liks INTEGER, rtweets INTEGER) RETURNS BIGINT AS $$ " +
                    "DECLARE max_id BIGINT;" +
                    "BEGIN " +
                    "INSERT INTO farmer_tweets(id, tweet_text,class_id,likes,retweets) VALUES(tweetId, tweetTxt, classId, liks, rtweets); " +
                    "SELECT MAX(id) INTO max_id FROM farmer_classes; " +
                    "RETURN max_id; " +
                    "END; $$ LANGUAGE plpgsql;";
        }
        return "";
    }

    /**
     * Procedure for deleting farmers
     * @return
     */
    public String getProcDeleteFarmer() {
        if(this.getDbtyp().compareTo(SqlManager.TYP_POSTGRESQL)==0) {
            return "CREATE OR REPLACE FUNCTION deleteFarmer(farmername TEXT) RETURNS BIGINT AS $$ " +
                    "DECLARE mid BIGINT;" +
                    "BEGIN " +
                    "SELECT id INTO mid FROM farmers WHERE name=farmername; " +
                    "DELETE FROM farmer_tweets WHERE class_id IN (SELECT id FROM farmer_classes WHERE farmer_id=mid); "+
                    "DELETE FROM farmer_classes WHERE  farmer_id=mid; "+
                    "DELETE FROM farmers WHERE id = mid;" +
                    "RETURN mid; " +
                    "END; $$ LANGUAGE plpgsql;";
        }
        return "";
    }

    /**
     * Procedure for deleting a Tweet
     * @return
     */
    public String getProcDeleteTweetFromFarmer() {
        if(this.getDbtyp().compareTo(SqlManager.TYP_POSTGRESQL)==0) {
            return "CREATE OR REPLACE FUNCTION deleteTweetFromFarmer(tweetId BIGINT) RETURNS BIGINT AS $$ " +
                    "DECLARE mid BIGINT;" +
                    "BEGIN " +
                    "DELETE FROM farmer_tweets WHERE  id=tweetId; "+
                    "RETURN mid; " +
                    "END; $$ LANGUAGE plpgsql;";
        }
        return "";
    }

    /**
     * Procedure for updating a Tweet
     * @return
     */
    public String getProcUpdateTweetFromFarmer() {
        if(this.getDbtyp().compareTo(SqlManager.TYP_POSTGRESQL)==0) {
            return "CREATE OR REPLACE FUNCTION updateTweetFromFarmer(farmername TEXT, tweetId BIGINT, nClass TEXT) RETURNS BIGINT AS $$ " +
                    "DECLARE cid BIGINT; DECLARE fid BIGINT;" +
                    "BEGIN " +
                    "SELECT id INTO fid FROM farmers WHERE name=farmername; " +
                    "SELECT id INTO cid FROM farmer_classes WHERE farmer_id=fid and name = nClass; "+
                    "UPDATE farmer_tweets SET class_id=cid WHERE id=tweetId;" +
                    "RETURN cid; " +
                    "END; $$ LANGUAGE plpgsql;";
        }
        return "";
    }
}
