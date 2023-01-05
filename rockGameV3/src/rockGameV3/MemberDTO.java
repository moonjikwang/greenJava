package rockGameV3;

public class MemberDTO {
	private String email;
	private String id;
	private String password;
	private String lastLogIn;
	private String lastLogOut;
	private int win;
	private int lose;
	private int draw;
	private int count;

	public MemberDTO(String email, String password) {
		this.email = email;
		this.password = password;
		if(email.isEmpty() || email.indexOf('@') == -1) {
			System.out.println("잘못된 이메일 형식입니다.");
			System.exit(0);
		}
		this.id = email.substring(0,email.indexOf('@'));
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getWin() {
		return win;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getLastLogIn() {
		return lastLogIn;
	}
	public void setLastLogIn(String lastLogIn) {
		this.lastLogIn = lastLogIn;
	}
	public String getLastLogOut() {
		return lastLogOut;
	}
	public void setLastLogOut(String lastLogOut) {
		this.lastLogOut = lastLogOut;
	}

}
