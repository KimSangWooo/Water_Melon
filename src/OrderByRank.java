import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.greenart.dbutil.DBUtil;

public class OrderByRank {

	public OrderByRank() {
	}

	public String showInfor(int rank) throws SQLException {

		String title = "";
		String artist = "";
		String genre = "";

		Connection conn = DBUtil.getConnection();
		String sql = "SELECT title, artist, genre  FROM musics WHERE now_rank = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, rank);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) { // 한 줄씩 가져옴
			title = rs.getString("title");
			artist = rs.getString("artist");
			genre = rs.getString("genre");

		}

		String sentence = title + " / " + artist + " / " + genre;

		DBUtil.closeRS(rs);
		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);

		return sentence;

	}
}
