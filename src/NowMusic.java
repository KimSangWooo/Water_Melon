

public class NowMusic {
	// 음악에 대한 정보만 저장할 수있는 Model -> Value Object (VO Class)

		// 노래제목, 가수이름, 노래의 플레이 타임, 노래의 경로

		private String title;
		private String Singer;
		private int playTime;
		private String musicPatch;

		// 정보들을 추가해 줄 수 있는 생성자메소드 만들기
		
		
		public NowMusic(String title, String Singer, int playTime, String musicPatch) {
			this.title = title;
			this.Singer = Singer;
			this.playTime = playTime;
			this.musicPatch = musicPatch;

		}

		public NowMusic(String title, String singer, String musicPatch) {
			super();
			this.title = title;
			Singer = singer;
			this.musicPatch = musicPatch;
		}

		// 정보를 가지고 올 수 있는 getter()생성
		public String getTilte() {
			return title;

		}

		public String getSinger() {
			return Singer;
		}

		public int getplayTime() {
			return playTime;
		}

		public String getmusicPatch() {
			return musicPatch;
		}

	}
