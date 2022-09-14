import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UsersDao {
//	C 회원 가입 INSERT INTO
//	R 회원 전체 리스트
//	R id검색 
//	U 회원 정보 수정(비밀번호 변경) UPDATE 
//	D 회원 탈퇴 
	public int create(Connection conn, String userID, String userPW, String userName) throws SQLException; // 회원가입

	List<User> read(Connection conn) throws SQLException; // 회원 전체 리스트

	User readID(Connection conn, String userID) throws SQLException; // id검색

	int update(Connection conn, String userPW) throws SQLException; // 비밀번호 변경

	public void delete(Connection conn, String userID) throws SQLException; // 회원 탈퇴

}
