import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableResultSet {

    public static void main(String[] args) throws SQLException {

        String sql = "SELECT tattoo_id, tattoo_name FROM portal.tattoos";

        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQLDB);

                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                ResultSet rs = stmt.executeQuery(sql);
                )
        {

            rs.absolute(32);

            rs.updateString("tattoo_name", "FOO BAR"); // New School
            rs.updateRow();

            System.out.println("Record Updated Successfully");

            rs.moveToInsertRow();
            rs.updateInt("tattoo_id", 33);
            rs.updateString("tattoo_name", "Blah Blah");
            rs.insertRow();

            System.out.println("Record Inserted Successfully");
        }
        catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
    }
}
