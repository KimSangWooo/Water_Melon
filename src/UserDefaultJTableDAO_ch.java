
//UserDefaultJTableDAO.java
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import kr.co.greenart.dbutil.DBUtil;


// 창희가 추가로 넣음 
public class UserDefaultJTableDAO_ch {
	/**
	 * 필요한 변수선언
	 * */
	private Connection conn = null;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	/**
	 * 로드 연결을 위한 생성자
	 * */

	/**
	 * 인수로 들어온 ID에 해당하는 레코드 검색하여 중복여부 체크하기 리턴값이 true =사용가능 , false = 중복임
	 * */
	public boolean getIdByCheck(String id) {
		boolean result = true;
		
		try {
			ps = conn.prepareStatement("SELECT * FROM musics WHERE id=?");
			ps.setString(1, id.trim());
			rs = ps.executeQuery(); //실행
			if (rs.next())
				result = false; //레코드가 존재하면 false

		} catch (SQLException e) {
			System.out.println(e + "=>  getIdByCheck fail");
		} finally {
			DBUtil.closeRS(rs);
			DBUtil.closeConn(conn);
		}

		return result;

	}//getIdByCheck()

	/**
	 * userlist 회원가입하는 기능 메소드
	 * */
	public int userListInsert(UserJDailogGUI_ch user) {
		int result = 0;
		try {
			ps = conn.prepareStatement("insert into musics values(?,?,?,?)");
			ps.setString(1, user.id.getText());
			ps.setString(2, user.name.getText());
			ps.setInt(3, Integer.parseInt(user.age.getText()));
			ps.setString(4, user.addr.getText());

			result = ps.executeUpdate(); //실행 -> 저장

		} catch (SQLException e) {
			System.out.println(e + "=> userListInsert fail");
		} finally {
			DBUtil.closeConn(conn);
		}

		return result;

	}//userListInsert()

	/**
	 * userlist의 모든 레코드 조회
	 * */
	public void userSelectAll(DefaultTableModel t_model) {
		try {
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from musics;");

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < t_model.getRowCount();) {
				t_model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4) ,rs.getBlob(5),  rs.getInt(6), rs.getInt(7) };

				t_model.addRow(data); //DefaultTableModel에 레코드 추가
			}

		} catch (SQLException e) {
//			System.out.println(e + "=> userSelectAll fail");
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn);
		}
	} //userSelectAll()

	/**
	 * ID에 해당하는 레코드 삭제하기
	 * */
	public int userDelete(String id) {
		int result = 0;
		try {
			ps = conn.prepareStatement("delete musics where id = ? ");
			ps.setString(1, id.trim());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> userDelete fail");
		}finally {
			DBUtil.closeConn(conn);
		}

		return result;
	}//userDelete()

	/**
	 * ID에 해당하는 레코드 수정하기
	 * */
	public int userUpdate(UserJDailogGUI_ch user) {
		int result = 0;
		String sql = "UPDATE musics SET NAME=?, age=? , addr=? WHERE id=?";

		try {
			ps = conn.prepareStatement(sql);
			// ?의 순서대로 값 넣기
			ps.setString(1, user.name.getText());
			ps.setString(2, user.age.getText());
			ps.setString(3, user.addr.getText());
			ps.setString(4, user.id.getText().trim());

			// 실행하기
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> userUpdate fail");
		} finally {
			DBUtil.closeConn(conn);
		}

		return result;
	}//userUpdate()

	/**
	 * 검색단어에 해당하는 레코드 검색하기 (like연산자를 사용하여 _, %를 사용할때는 PreparedStatemnet안된다. 반드시
	 * Statement객체를 이용함)
	 * */
	public void getUserSearch(DefaultTableModel dt, String fieldName, // 검색구현 (원하는 컬럼 선택후 검색가능) 메소드
			String word) {
		String sql = "SELECT * FROM musics WHERE " + fieldName.trim() 
				+  " LIKE '%" + word.trim() + "%'";
		try {
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}
			while (rs.next()) {
				Object data[] = { rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4) ,rs.getBlob(5),  rs.getInt(6), rs.getInt(7) };
				dt.addRow(data);
			}

		} catch (SQLException e) {
			System.out.println(e + "=> getUserSearch fail");
		} finally {
			DBUtil.closeConn(conn);
		}

	}//getUserSearch()

}// 클래스끝

