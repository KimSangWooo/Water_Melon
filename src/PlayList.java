
public class PlayList {
	private String userID;
	private int music_id;
	private String title;
	private String artist;
	private String genre;
	private int nowRank;
	
	public int getMusic_id() {
		return music_id;
	}

	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}

	public PlayList(String title, String artist, String genre) {
		super();
		this.title = title;
		this.artist = artist;
		this.genre = genre;
	}

	public PlayList(int music_id, String title, String artist, String genre) {
		super();
		this.music_id = music_id;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "PlayList [userID=" + userID + ", title=" + title + ", artist=" + artist + ", genre=" + genre
				+ ", nowRank=" + nowRank + "]";
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getNowRank() {
		return nowRank;
	}

	public void setNowRank(int nowRank) {
		this.nowRank = nowRank;
	}

}
