import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.greenart.dbutil.DBUtil;

public class PlayListDaoImp implements PlayListDao {
	private List<Integer> playingIDs = new ArrayList<>();

	public PlayList resultMapping(ResultSet rs) throws SQLException {
		int music_id = rs.getInt("music_id");
		String title = rs.getString("title");
		String artist = rs.getString("artist");
		String genre = rs.getString("genre");

		return new PlayList(music_id, title, artist, genre);
	}

	@Override

	public List<PlayList> PersonalPL(Connection conn, String userID) throws SQLException {
		conn = DBUtil.getConnection();
		String query = "select p.music_id, p.title, p.artist, p.genre from perplay as p where user_id = ? ORDER BY p.title";

		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, userID);
		ResultSet rs = pstmt.executeQuery();
		List<PlayList> temp = new ArrayList<>();
		while (rs.next()) {
			temp.add(resultMapping(rs));

		} // id검색

		DBUtil.closeRS(rs);
		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);
		return temp;
	}

	@Override
	public void DeletePL(Connection conn, String userID, List<Integer> check) throws SQLException {

		conn = DBUtil.getConnection();

		int cnt = 0;
		String query = "DELETE FROM playlist  WHERE user_id = ? AND music_id = ? LIMIT 1";

		PreparedStatement pstmt = conn.prepareStatement(query);

		for (int i = 0; i < check.size(); i++) {
			pstmt.setString(1, userID);
			pstmt.setInt(2, check.get(i));
			pstmt.executeUpdate();

		}
		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);
	}

	@Override
	public void SetSaveCnt(Connection conn, int now_rank) throws SQLException {
		conn = DBUtil.getConnection();
		String sql = "UPDATE musics SET save_count = save_count + 1 WHERE now_rank = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, now_rank);
		pstmt.executeUpdate();

		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);

	}

	// 추가---------------------------------------------
	public String findUsersName(Connection conn, String user_id) throws SQLException {
		conn = DBUtil.getConnection();
		String sql = "SELECT user_name FROM users WHERE user_id = ?";
		String name = "";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_id);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			name = rs.getString("user_name");

		}
		DBUtil.closeRS(rs);
		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);
		return name;
	}

	public void insertPL(Connection conn, String user_id, int now_rank) throws SQLException {
		conn = DBUtil.getConnection();
		String sql = "INSERT INTO playlist VALUES(?, (SELECT music_id FROM musics WHERE now_rank = ?))";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, user_id);
		pstmt.setInt(2, now_rank);

		pstmt.executeUpdate();

		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);
	}

	public MusicPlayer printPlaylist(Connection conn, String user_id, Object[][] data, MusicPlayer mp)
			throws SQLException {
		conn = DBUtil.getConnection();

		PlayListDaoImp dao = new PlayListDaoImp();

		List<PlayList> plList = new ArrayList<>();
		plList = dao.PersonalPL(conn, user_id);

		for (int i = 0; i < plList.size(); i++) {
			data[i][0] = new Boolean(false);
			data[i][1] = plList.get(i).getMusic_id();
			data[i][2] = plList.get(i).getTitle();
			data[i][3] = plList.get(i).getArtist();
			data[i][4] = plList.get(i).getGenre();

			playingIDs.add(plList.get(i).getMusic_id());

		}
		DBUtil.closeConn(conn);

		return new MusicPlayer(getPlayingIDs());
	}

	public int findCnt(Connection conn, String user_id) throws SQLException {
		conn = DBUtil.getConnection();
		int cnt = 0;

		String sql = "SELECT COUNT(*) FROM playlist WHERE user_id = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_id);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			cnt = rs.getInt("COUNT(*)");

		}

		DBUtil.closeRS(rs);
		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);

		return cnt;
	}

	// ------------------------------220727 수정-----------------------------------
	public int printMusicId(Connection conn, int i) throws SQLException {
		conn = DBUtil.getConnection();
		int music_id = 0;
		String sql = "SELECT music_id FROM musics ORDER BY  save_count DESC, music_id LIMIT ?,1";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, i);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			music_id = rs.getInt("music_id");

		}

		DBUtil.closeRS(rs);
		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);

		return music_id;
	}

	public void updateRank(Connection conn, int music_id, int rank) throws SQLException {

		conn = DBUtil.getConnection();
		String sql = "UPDATE musics SET now_rank = ? WHERE music_id = ? ";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, rank);
		pstmt.setInt(2, music_id);

		pstmt.executeUpdate();

		DBUtil.closeStmt(pstmt);
		DBUtil.closeConn(conn);

	}

	// ------------------------------220727 수정-----------------------------------
	public List<Integer> getPlayingIDs() {
		return playingIDs;
	}

	public void setPlayingIDs(List<Integer> playingIDs) {
		this.playingIDs = playingIDs;
	}

}