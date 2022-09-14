
public class User {
	private String userID;
	private String userPW;
	private String userName;
	private String mainGenre;
	private int totallPlay;
	private int logDate;
	
	public User (String userID, String userPW, String userName) {
		this.userID = userID;
		this.userPW = userPW;
		this.userName = userName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMainGenre() {
		return mainGenre;
	}

	public void setMainGenre(String mainGenre) {
		this.mainGenre = mainGenre;
	}

	public int getTotallPlay() {
		return totallPlay;
	}

	public void setTotallPlay(int totallPlay) {
		this.totallPlay = totallPlay;
	}

	public int getLogDate() {
		return logDate;
	}

	public void setLogDate(int logDate) {
		this.logDate = logDate;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userPW=" + userPW + ", userName=" + userName + "]";
	}
	
	
}
