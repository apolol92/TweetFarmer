package sql_manager;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by apolol92 on 07.05.2016.
 * This Class creates all needed tables in the database.
 */
public class SqlTableCreator extends SqlManager {
    /**
     * For which farmer?
     */
    private String farmerName;
    /**
     * Creates the instance of SqlTableCreator
     */
    public SqlTableCreator(String databaseIp, String databasePort, String databasename, String databaseTyp, String databaseUsername, String databasePassword, String farmerName) {
        super(databaseIp, databasePort, databasename, databaseTyp, databaseUsername, databasePassword);
        this.farmerName = farmerName;
    }

    /**
     * This method creates all needed tables
     * @return true, if success
     */
    public boolean createTables() {
        try {
            Statement mStmt = super.connection.createStatement();
            String sqlFarmerClasses = "CREATE TABLE IF NOT EXISTS "+ this.farmerName +"_Classes"+
                    "(id INTEGER NOT NULL, " +
                    " classes TEXT, " +
                    " PRIMARY KEY ( id ))";
            String sqlFarmerText = "CREATE TABLE IF NOT EXISTS "+this.farmerName+"_Text " +
                    "(id INTEGER NOT NULL, " +
                    " content TEXT, " +
                    " class_id INTEGER, "+
                    " PRIMARY KEY ( id ))";
            mStmt.executeUpdate(sqlFarmerClasses);
            mStmt.executeUpdate(sqlFarmerText);
            mStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
