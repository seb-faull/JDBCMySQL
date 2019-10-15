import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String mySqlUser = "root";
    private static final String mySqlPwd = "xxxxxx";
    private static final String mySQLCS = "jdbc:mysql://localhost:3306/portal";
    //private static final String mySqlPwd = "xxPASSWDxx";    // xxPASSWDxx"
    //private static final String mySQLCS = "jdbc:mysql://localhost:xxPORTNOxx/xxDBNAMExx";   // xxPORTNOxx/xxDBNAMExx"

    public static Connection getConnection(DBType dbType) throws SQLException {

        switch (dbType) {
            case MYSQLDB:
                return DriverManager.getConnection(mySQLCS, mySqlUser, mySqlPwd);

            default:
                return null;
        }

    }

    public static void showErrorMessage(SQLException e) {
        System.err.println("Error :" + e.getMessage());
        System.err.println("Error Code :" + e.getErrorCode());
    }
}
