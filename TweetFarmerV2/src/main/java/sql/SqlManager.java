package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by apolol92 on 19.05.2016.
 * Parent class of all SQL-Classes
 */
public class SqlManager {

    /**
     * Supported DBMS
     */
    public static final String TYP_POSTGRESQL = "PostgreSQL";

    public int getDbPort() {
        return dbport;
    }

    public void setDbPort(int port) {
        this.dbport = port;
    }

    /**
     * Database-Port
     */
    private int dbport;
    /**
     * Connection
     */
    protected Connection connection;
    public String getDbIp() {
        return dbip;
    }

    public void setDbIp(String ip) {
        this.dbip = ip;
    }

    public String getDbtyp() {
        return dbtyp;
    }

    public void setDbtyp(String dbtyp) {
        this.dbtyp = dbtyp;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbuser() {
        return dbuser;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    /**
     * Database-IP
     */
    private String dbip;
    /**
     * Database-Typ
     */
    private String dbtyp;
    /**
     * Database-Name
     */
    private String dbname;
    /**
     * Database-User
     */
    private String dbuser;
    /**
     * Database-Password
     */
    private String dbpassword;

    /**
     * Constructor
     * @param dbip
     * @param dbport
     * @param dbtyp
     * @param dbname
     * @param dbuser
     * @param dbpassword
     */
    public SqlManager(String dbip, int dbport, String dbtyp, String dbname, String dbuser, String dbpassword) {
        this.dbip = dbip;
        this.dbport = dbport;
        this.dbtyp = dbtyp;
        this.dbname =dbname;
        this.dbuser = dbuser;
        this.dbpassword = dbpassword;
        this.connection = null;
    }

    /**
     * Connect to database
     * @return
     */
    public boolean connect() {
        if(this.dbtyp.compareTo(SqlManager.TYP_POSTGRESQL)==0) {
            try {
                Class.forName("org.postgresql.Driver");
                this.connection = DriverManager.getConnection("jdbc:postgresql://"+this.dbip+":"+this.dbport+"/"+this.dbname,this.dbuser,this.dbpassword);
                return true;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * Disconnect from database
     * @return
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
