import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class RetrieveCLOBDataFromDB {

    public static void main(String[] args) throws SQLException, IOException {

        Connection conn = DBUtil.getConnection(DBType.MYSQLDB);

        String sql = "SELECT resume FROM portal.artists WHERE artist_id = 1";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            Clob resume = rs.getClob("Resume");
            Reader data = resume.getCharacterStream();

            int i;
            String resumeDetails = "";

            while ( (i =data.read()) != -1) {
                resumeDetails += ((char) i);
            }
            System.out.println("Resume Details for Artist 1: \n");
            System.out.println(resumeDetails);

        } else {
            System.err.println("No Record Found for Artist With the ID 1.");
        }

        rs.close();
        pstmt.close();
        conn.close();
    }
}
