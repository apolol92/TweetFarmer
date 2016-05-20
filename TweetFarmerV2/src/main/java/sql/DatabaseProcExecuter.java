package sql;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by apolol92 on 19.05.2016.
 */
public class DatabaseProcExecuter extends SqlManager {

    public DatabaseProcExecuter(String dbip, int dbport, String dbtyp, String dbname, String dbuser, String dbpassword) {
        super(dbip, dbport, dbtyp, dbname, dbuser, dbpassword);
    }

    public long execProcInsertFarmer(String farmername) {
        if(this.getDbtyp().compareTo(TYP_POSTGRESQL)==0) {
            try {
                Statement stmt = super.connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM insertFarmer('" + farmername + "');");
                if (rs.next()) {
                    long max_id = rs.getLong(1);
                    rs.close();
                    stmt.close();
                    return max_id;
                } else {
                    return -1;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public long execProcInsertClass(long farmerId, String className) {
        if(this.getDbtyp().compareTo(TYP_POSTGRESQL)==0) {
            try {
                Statement stmt = super.connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM insertClass(" +farmerId+",'" + className + "');");
                if (rs.next()) {
                    long max_id = rs.getLong(1);
                    rs.close();
                    stmt.close();
                    return max_id;
                } else {
                    return -1;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public long execProcInsertTweet(long tweet_id, long classId, String tweetText, int likes, int retweets) {
        if(this.getDbtyp().compareTo(TYP_POSTGRESQL)==0) {
            try {
                Statement stmt = super.connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM insertTweet("+tweet_id+", " +classId+",'" + tweetText + "',"+likes+","+retweets+");");
                if (rs.next()) {
                    long max_id = rs.getLong(1);
                    rs.close();
                    stmt.close();
                    return max_id;
                } else {
                    return -1;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public ResultSet execProcGetClassesFromFarmername(String farmername) {
        if(this.getDbtyp().compareTo(TYP_POSTGRESQL)==0) {
            try {
                Statement stmt = super.connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM getProcGetClassesFromFarmername('" + farmername + "');");
                return rs;

            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public ResultSet execProcSelectTweetsFromFarmer(String farmername) {
        if(this.getDbtyp().compareTo(TYP_POSTGRESQL)==0) {
            try {
                Statement stmt = super.connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM selectTweetsFrom('" + farmername + "');");
                return rs;

            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }


}
