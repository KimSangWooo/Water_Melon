import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sun.audio.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Mp3Maker {
	private MusicsDaoImpl dao = new MusicsDaoImpl();
	private File file;
	private String title;
	private String artist;
	
	public String getFilePath() {
		return file.getPath();
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public InputStream inMp3(Connection conn, int music_id) throws SQLException {
		Music mu = dao.read(conn, music_id);
		title = mu.getTitle();
		artist = mu.getArtist();
		return mu.getMp3();
	}
	
	public FileOutputStream outMp3(Connection conn, int music_id) throws SQLException, IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("d:\\testMusic\\");
		sb.append(music_id);
		sb.append(".mp3");
		String path = sb.toString();
		Files.createDirectories(Paths.get("d:\\testMusic\\"));
		file = new File(path);
		
		return new FileOutputStream(file);
	}
	
	public void oneMp3(Connection conn, int music_id) throws SQLException, IOException {
		InputStream in = inMp3(conn, music_id);
		FileOutputStream out = outMp3(conn, music_id);
		
		int b = 0;
		while((b = in.read()) != -1) {
			out.write(b);
		}
	}
	
//	public void oneMP3(Connection conn, List<Integer> ids) throws SQLException {
//		List<InputStream> in = new ArrayList<>();
//		for (int i = 0; i < ids.size(); i++) {
//			in.add(dao.read(conn, ids.get(i)).getMp3());
//		}
//		// 파일이름 : 제목 저장하게 하고(변수) 
//		// 경로지정 : 해당 파일의 경로에 Stringbuilder써서 경로 넣어주기 ( 확장자도)
//		// 
//		
//		List<FileOutputStream> out = new ArrayList<>();
//		for (int i = 0; i < ids.size(); i++) {
//			out.add(new FileOutputStream(new File("d:\\")))
//		}
//		
//		int b = 0;
//		while((b = is.read()) != -1) {
//			fs.write(b);
//		}
//		
//	}
	
	
}
