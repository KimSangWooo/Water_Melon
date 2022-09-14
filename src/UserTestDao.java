import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import kr.co.greenart.dbutil.DBUtil;

public class UserTestDao {

	public static void main(String[] args) throws SQLException {
		UserDaoImp dao = new UserDaoImp();
	
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();

			String rs = dao.getPW(conn, "ksw");
			System.out.println(rs);

		} finally {
			DBUtil.closeConn(conn);
		}

	}

}