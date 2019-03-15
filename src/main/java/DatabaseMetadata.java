import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMetadata {

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DBUtil.getConnection(DBType.MYSQLDB);) {

            DatabaseMetaData dbmd = conn.getMetaData();

            //Retrieving Database Information
            System.out.println("Driver Name : " + dbmd.getDriverName());
            System.out.println("Driver Version : " + dbmd.getDriverVersion());
            System.out.println("Logged In User : " + dbmd.getUserName());
            System.out.println("Database Product Name : " + dbmd.getDatabaseProductName());
            System.out.println("Database Product Version : " + dbmd.getDatabaseProductVersion());
            System.out.println("URL : " + dbmd.getURL());

            //Retrieving all the tables names
            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = null;
            String schemaTypes[] = { "TABLE" };
            ResultSet rs = dbmd.getTables(catalog, schemaPattern, tableNamePattern, schemaTypes);

            System.out.println("\nTables");
            System.out.println("==========");
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
            }

            //Retrieving Columns Present within the Table
            String columnNamePattern = null;
            rs = dbmd.getColumns(catalog, schemaPattern, "artists", columnNamePattern);

            System.out.println("\nColumn Details");
            System.out.println("==========");

            while (rs.next()) {
                System.out.println(rs.getString("COLUMN_NAME"));
            }

            rs.close();
        }
        catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
    }
}
