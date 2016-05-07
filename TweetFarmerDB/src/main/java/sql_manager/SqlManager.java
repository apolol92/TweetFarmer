package sql_manager;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by apolol92 on 07.05.2016.
 * This is the parent class of all sql-Classes.
 * It provides the convertion to any database and methods to connect and disconnect.
 */
public class SqlManager {

    /**
     * Database IP
     */
    protected String databaseIp;
    /**
     * Database Port Number
     */
    protected String databasePort;
    /**
     * Database name
     */
    protected String databasename;
    /**
     * Database Typ
     */
    protected String databaseTyp;
    /**
     * Database Username
     */
    protected String databaseUsername;
    /**
     * Database Password
     */
    protected String databasePassword;
    /**
     * Database Connection
     */
    protected Connection connection;

    /**
     * Create a DatabaseManager with following configurations
     * @param databaseIp
     * @param databasePort
     * @param databasename
     * @param databaseTyp
     * @param databaseUsername
     * @param databasePassword
     */
    public SqlManager(String databaseIp, String databasePort, String databasename, String databaseTyp, String databaseUsername, String databasePassword) {
        this.databaseIp = databaseIp;
        this.databasePort = databasePort;
        this.databasename = databasename;
        this.databaseTyp = databaseTyp;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
        this.connection = null;
    }

    /**
     * Connect to the configured database
     * @return true, if success
     */
    public boolean connectToDatabase() {
        this.connection = null;
        try {
            //if(this.databaseTyp.toLowerCase().compareTo("postgresql")==0) {
                //PostgreSQL
                Class.forName("org.postgresql.Driver");
                this.connection = DriverManager
                        .getConnection("jdbc:postgresql://"+this.databaseIp+":"+this.databasePort+"/"+this.databasename,
                                this.databaseUsername, this.databasePassword);
            //}
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
    }

    /**
     * Disconnect from database
     * @return true, if success
     */
    public boolean disconnect() {
        try {
            this.connection.close();
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
