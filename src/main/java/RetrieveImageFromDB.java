import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class RetrieveImageFromDB {

    public static void main(String[] args) throws SQLException, IOException {
        Connection conn = DBUtil.getConnection(DBType.MYSQLDB);

        String sql = "SELECT profile_picture FROM portal.artists WHERE artist_id = 1";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            Blob imgBlob = rs.getBlob("profile_picture");

            FileOutputStream fos = new FileOutputStream("/Users/sebfaull/Downloads/jdbcProfilePic.jpg");

            fos.write(imgBlob.getBytes(1, (int)imgBlob.length()));

            fos.flush();
            fos.close();

            System.out.println("Photo of Artist 1 has been Downloaded successfully");

        } else {
            System.out.println("Employee Record Not Found");
        }

        rs.close();
        pstmt.close();
        conn.close();
    }
}
