import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertImageWithinDBUsingBLOB {

    public static void main(String[] args) throws SQLException, IOException {

        Connection conn = DBUtil.getConnection(DBType.MYSQLDB);

        String sql = "UPDATE portal.artists SET profile_picture = ? WHERE artist_id = 1";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        File imgFile = new File("/Users/sebfaull/development/random_files_for_projects/user_dp.jpg");

        FileInputStream fis = new FileInputStream(imgFile);

        pstmt.setBinaryStream(1, fis, fis.available());

        int count = pstmt.executeUpdate();

        System.out.println("Total Records Updated :" + count);
        pstmt.close();
        conn.close();
    }
}
