import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IteratingWithResultSetsUsingTryWithResource {

    public static void main(String[] args) throws SQLException {
        String sql = "SELECT * FROM portal.tattoos";

        // Try with resource automatically opens and closes resources so you don't need the finally block.
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
                Statement stmt = conn.createStatement();
                ResultSet rs   = stmt.executeQuery(sql);
                ) {

            String format = "%-4s%-20s\n";
            while (rs.next()) {
                System.out.format(format, rs.getString("tattoo_id"), rs.getString("tattoo_name"));
            }

        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
    }

}

