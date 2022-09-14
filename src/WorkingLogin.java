import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import kr.co.greenart.dbutil.DBUtil;

public class WorkingLogin {
	UsersDao wl = new UserDaoImp();

	public boolean isIDSame(String id) throws SQLException {
		int c = 0;
//		System.out.println("아이디입력");

		String query = "select count(*) from users WHERE user_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				c = rs.getInt("count(*)");
			}
			return c == 0;
		} finally {
			DBUtil.closeRS(rs);
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}

	}

	public boolean isPassWordSame(String pw1, String pw2) {

		return pw1.equals(pw2);
	}

	public static void main(String[] args) throws SQLException {
//		WorkingLogin w = new WorkingLogin();
//
//		System.out.println(w.isIDSame("vv"));
//		System.out.println(w.isPassWordSame("최진혁", "최진혁"));

	}

}
