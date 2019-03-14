import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCLOBDataToResume {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
        PreparedStatement pstmt = null;

        String sql = "UPDATE portal.artists SET resume = ? WHERE artist_id = 1";
        pstmt = conn.prepareStatement(sql);

        String resumeFile = "/Users/sebfaull/development/random_files_for_projects/someones_resume_2019.txt";
        File file = new File(resumeFile);
        FileReader reader = new FileReader(file);

        pstmt.setCharacterStream(1, reader, (int)file.length());
        pstmt.executeUpdate();

        System.out.println("Resume Updated Successfully...");
        pstmt.close();
        conn.close();

    }
}
