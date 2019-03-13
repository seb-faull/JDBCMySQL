import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetScrolling {

    public static void main(String[] args) throws SQLException {

        String sql = "SELECT * FROM portal.tattoos WHERE tattoo_id <= 10";

        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(sql);
                ) {

            String format = "%-4s%-20s\n";

            rs.beforeFirst();

            System.out.println("First 10 Rows :");

            while (rs.next()) {
                System.out.format(format, rs.getString("tattoo_id"), rs.getString("tattoo_name"));
            }

            rs.afterLast();
            System.out.println("Last 10 Rows :");

            while (rs.previous()) {
                System.out.format(format, rs.getString("tattoo_id"), rs.getString("tattoo_name"));
            }

            rs.first();
            System.out.println("First Record :");
            System.out.format(format, rs.getString("tattoo_id"), rs.getString("tattoo_name"));

            rs.last();
            System.out.println("Last Record :");
            System.out.format(format, rs.getString("tattoo_id"), rs.getString("tattoo_name"));

            rs.absolute(4);
            System.out.println("Record at 4th Row :");
            System.out.format(format, rs.getString("tattoo_id"), rs.getString("tattoo_name"));

            rs.relative(2);
            System.out.println("Record at 6th Row :");
            System.out.format(format, rs.getString("tattoo_id"), rs.getString("tattoo_name"));

            rs.relative(-4);
            System.out.println("Record at 2nd Row :");
            System.out.format(format, rs.getString("tattoo_id"), rs.getString("tattoo_name"));

        }
        catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
    }

}
