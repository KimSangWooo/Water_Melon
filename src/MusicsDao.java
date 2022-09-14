import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MusicsDao {
	// C : 음원 생성(추가)
	int create(Connection conn, String title, String artist, String genre, InputStream mp3, int mp3Length) throws SQLException;
	
	// R : 전체 음원 목록
	List<Music> read(Connection conn) throws SQLException;
	
	// R : 음원 검색 (id)
	Music read(Connection conn, int music_id) throws SQLException;
	
	// U : 음원 정보(텍스트) 수정
	int update(Connection conn, String title, String artist, String genre, InputStream mp3, int mp3Length) throws SQLException;
	
	// D : 음원 삭제
	int delete(Connection conn, int music_id) throws SQLException;
}
