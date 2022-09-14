import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PlayListDao {

	public List<PlayList> PersonalPL(Connection conn, String userID) throws SQLException; // id검색
	// ------------------------------220726
	// 수정---------------------------------------------

	public void DeletePL(Connection conn, String userID, List<Integer> check) throws SQLException;

	// ------------------------------220726
	// 수정---------------------------------------------
	public void SetSaveCnt(Connection conn, int now_rank) throws SQLException;

	public String findUsersName(Connection conn, String user_id) throws SQLException;

	public void insertPL(Connection conn, String user_id, int now_rank) throws SQLException;

	public MusicPlayer printPlaylist(Connection conn, String user_id, Object[][] data, MusicPlayer mp)
			throws SQLException;

	public int findCnt(Connection conn, String user_id) throws SQLException;
	
	
	public int printMusicId(Connection conn, int i) throws SQLException;
	public void updateRank(Connection conn, int music_id, int rank) throws SQLException;

}
