import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExceptionHandling {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection(DBType.MYSQLDB);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM portal.tattoos");
            rs.last();

            System.out.println("Total No. of Rows:   " + rs.getRow());
        } catch (SQLException e) {
            //System.err.println(e);
            DBUtil.showErrorMessage(e);
        }
        finally {
            conn.close();
        }
    }
}
