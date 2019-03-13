import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IteratingWithResultSets {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt  = null;
        ResultSet rs    = null;

        try {
            conn = DBUtil.getConnection(DBType.MYSQLDB);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM portal.tattoos";
            rs   = stmt.executeQuery(sql);

            String format = "%-4s%-20s\n";
            while (rs.next()) {
                System.out.format(format, rs.getString("tattoo_id"), rs.getString("tattoo_name"));
            }

        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
        finally {
            rs.close();
            stmt.close();
            conn.close();
        }
    }

}
