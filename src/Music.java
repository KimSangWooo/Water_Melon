import java.io.InputStream;
import java.io.OutputStream;

public class Music {
	private int music_id;
	private String title;
	private String artist;
	private String genre;
	private InputStream mp3;
	private OutputStream Mp3;
	private int save_count;
	private int now_rank;

	public Music(int music_id, String title, String artist, String genre, InputStream mp3,
			int save_count, int now_rank) {
		super();
		this.music_id = music_id;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.mp3 = mp3;
		this.save_count = save_count;
		this.now_rank = now_rank;
	}

	public Music(String title, String artist, String genre) {
		super();
		this.title = title;
		this.artist = artist;
		this.genre = genre;
	}

	public int getMusic_id() {
		return music_id;
	}

	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}

	public InputStream getMp3() {
		return mp3;
	}

	public OutputStream getMP3() {
		return Mp3;
	}

	public void setMp3(InputStream mp3) {
		this.mp3 = mp3;
	}

	public int getSave_count() {
		return save_count;
	}

	public void setSave_count(int total_play) {
		this.save_count = total_play;
	}

	public int getNow_rank() {
		return now_rank;
	}

	public void setNow_rank(int now_rank) {
		this.now_rank = now_rank;
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

}
