package database;

/**
 * Created by apolol92 on 13.05.2016.
 * Contains all Database Config-Data to access a database
 */
public class DatabaseConfigData {
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDbTyp() {
        return dbTyp;
    }

    public void setDbTyp(String dbTyp) {
        this.dbTyp = dbTyp;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabasename() {
        return databasename;
    }

    public void setDatabasename(String databasename) {
        this.databasename = databasename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Database-Ip
     */
    private String ip;
    /**
     * Database-Typ(POSTGRESQL/MYSQL/MSSQL/..)
     */
    private String dbTyp;
    /**
     * Database-Port
     */
    private String port;
    /**
     * Databasename
     */
    private String databasename;
    /**
     * Database-Username
     */
    private String username;
    /**
     * Dasbase-Password
     */
    private String password;

    /**
     * Default Constructor
     */
    public DatabaseConfigData() {

    }
    /**
     * Constructor
     * @param ip
     * @param dbTyp
     * @param port
     * @param databasename
     * @param username
     * @param password
     */
    public DatabaseConfigData(String ip, String dbTyp, String port, String databasename, String username, String password) {
        this.ip = ip;
        this.dbTyp = dbTyp;
        this.port = port;
        this.databasename = databasename;
        this.username = username;
        this.password = password;
    }


}
