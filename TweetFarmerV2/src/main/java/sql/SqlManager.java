package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by apolol92 on 19.05.2016.
 */
public class SqlManager {

    public static final String TYP_POSTGRESQL = "PostgreSQL";

    public int getDbPort() {
        return dbport;
    }

    public void setDbPort(int port) {
        this.dbport = port;
    }

    private int dbport;
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

    private String dbip;
    private String dbtyp;
    private String dbname;
    private String dbuser;
    private String dbpassword;

    public SqlManager(String dbip, int dbport, String dbtyp, String dbname, String dbuser, String dbpassword) {
        this.dbip = dbip;
        this.dbport = dbport;
        this.dbtyp = dbtyp;
        this.dbname =dbname;
        this.dbuser = dbuser;
        this.dbpassword = dbpassword;
        this.connection = null;
    }

    public boolean connect() {
        if(this.dbtyp.compareTo(TYP_POSTGRESQL)==0) {
            System.out.println("HHIIIIIER");
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
