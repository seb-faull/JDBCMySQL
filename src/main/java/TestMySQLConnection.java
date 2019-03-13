import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMySQLConnection {
    static String username = "root";
    static String password = "xxPASSWDxx";
    static String db = "jdbc:mysql://localhost:xxPORTNOxx/xxDBNAMExx";

    public static void main(String[] args) throws SQLException {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(db, username, password);

            System.out.println("Connection Established to MySQL Database");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            conn.close();
        }

    }
}
