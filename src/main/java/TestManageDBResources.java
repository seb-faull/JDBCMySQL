import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestManageDBResources {

    public static void main(String[] args) throws SQLException {

        Connection conn = null;

        try {
            // conn = DriverManager.getConnection(db, username, password);
            conn = DBUtil.getConnection(DBType.MYSQLDB);

            System.out.println("Connection Established to MySQL Database");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            conn.close();
        }

    }
}
