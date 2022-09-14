import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.greenart.dbutil.DBUtil;

public class UserDaoImp implements UsersDao {

	public User resultMapping(ResultSet rs) throws SQLException {
		String id = rs.getString("user_id");
		String pw = rs.getString("user_pw");
		String name = rs.getString("user_name");

		return new User(id, pw, name);
	}

	@Override
	public int create(Connection conn, String userID, String userPW, String userName) throws SQLException {
		String query = "INSERT INTO users (user_id, user_pw, user_name) VALUES (?,?,?)";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			pstmt.setString(3, userName);

			return pstmt.executeUpdate();
		} finally {
			DBUtil.closeStmt(pstmt);
//			DButil.closeConn(conn);

		}

	} // 회원가입

	@Override
	public List<User> read(Connection conn) throws SQLException {
		String query = "select * from users";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("user_id");
				String pw = rs.getString("user_pw");
				String name = rs.getString("user_name");

				list.add(new User(id, pw, name));
			}
		} finally {
			DBUtil.closeRS(rs);
			DBUtil.closeStmt(pstmt);
		}
		return list;
	} // 회원 전체 리스트

	@Override
	public User readID(Connection conn, String userID) throws SQLException {
		String query = "select * from users WHERE user_id = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			User temp = null;
			if (rs.next()) {
				temp = resultMapping(rs);
			}
			return temp;
		} finally {
			DBUtil.closeRS(rs);
			DBUtil.closeStmt(pstmt);
		}
	} // id검색

	public String getPW(Connection conn, String userID) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select user_pw from users where user_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			String temp = "";
			if (rs.next()) {
				temp = rs.getString("user_pw");
			}
			return temp;

		} finally {
			DBUtil.closeStmt(pstmt);

		}
	} // 아이디 검색으로 해당 아이디의 비번찾기

	public String getID(Connection conn, String userPW) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select user_id from users where user_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userPW);
			rs = pstmt.executeQuery();

			String temp = "";
			if (rs.next()) {
				temp = rs.getString("user_id");
			}
			return temp;

		} finally {
			DBUtil.closeStmt(pstmt);

		}
	} // 비번 검색으로 해당 비번의 아이디찾기

	public void pwChange(Connection conn, String newPW, String userID) throws SQLException {

		String sql = "update users set user_pw = ? where user_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, newPW);
		pstmt.setString(2, userID);

		pstmt.executeUpdate();

		DBUtil.closeStmt(pstmt);

		return;
	} // 비밀번호 변경메소드

	public User readPW(Connection conn, String userPW) throws SQLException {
		String query = "select * from users WHERE user_pw = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userPW);
			rs = pstmt.executeQuery();
			System.out.println(rs);
			User temp = null;
			if (rs.next()) {
				temp = resultMapping(rs);
			}
			return temp;
		} finally {
			DBUtil.closeRS(rs);
			DBUtil.closeStmt(pstmt);
		}
	} // 비밀번호 검색

//	public String readNgetName(Connection conn, String userID) throws SQLException {
//		String query = "select user_name from users WHERE user_id = ?";
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, userID);
//			rs = pstmt.executeQuery();
//			System.out.println(rs);
//			User temp = null;
//			String userName;
//			if (rs.next()) {
//				 userName = "user_name";
//			} 
//			
//			
//		} finally {
//			DBUtil.closeRS(rs);
//			DBUtil.closeStmt(pstmt);
//		}
//	} //이름알려주기

	@Override
	public int update(Connection conn, String userPW) throws SQLException {
		String query = "update users set user_pw = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userPW);

			return pstmt.executeUpdate();
		} finally {
			DBUtil.closeStmt(pstmt);
		}
	} // 비밀번호 변경

	@Override
	public void delete(Connection conn, String userID) throws SQLException {
		String query = "delete from users where user_id = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			 return;
		} finally {
			DBUtil.closeStmt(pstmt);
		}
		
	}
}
