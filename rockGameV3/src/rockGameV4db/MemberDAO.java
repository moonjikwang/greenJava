package rockGameV4db;

/**
 * 
 * @author 문지광
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;


public class MemberDAO {
//---------------------필드선언 및 초기화 --------------------
	private static MemberDAO instance = new MemberDAO();
	private static Connection conn;
//---------------------필드선언 및 초기화 끝 --------------------
//--------------DB연결-------------------
	
	// -----------------싱글톤 작업 --------------------
	private MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@jikwang.net:15210/xe", "moonjikwang", "Moon20130315");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static MemberDAO getInstance() {
		return instance;
	}
	public static Connection getConnection() {
		return conn;
	}
	// -----------------싱글톤 작업끝 --------------------
	//-------------------회원수 조회  ---------------
	public int memberCount() {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as cnt from rockgamemembers");
			if(rs.next()) {
			result = rs.getInt("cnt");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("회원 카운트 예외 : " + e.getMessage());
		}
		return result;
	}
	//-------------------회원수 조회  끝 ---------------
	//-----------------회원 승률 리스트 -----------------
	public TreeMap<String, Integer> rateList() {
		TreeMap<String, Integer> memberRate = new TreeMap<String, Integer>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select userid,rate from rockgamemembers order by rate desc");
			while(rs.next()) {
				memberRate.put(rs.getString("userid"), rs.getInt("rate"));
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			System.out.println("회원승률 리스트 취합 예외입니다. : " + e.getMessage());
			System.out.println("회원리스트가 없습니다. 프로그램을 종료합니다.");
			System.exit(0);
		}
		return memberRate;
	}
	//-----------------회원 승률 리스트  끝-----------------
	// ------------------------회원가입메서드------------------------
	public int registerId(MemberDTO member) {
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into rockgamemembers (userid,userpassword) values (?,?)");
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("회원가입 예외발생 : " + e.getMessage());
		}
		loading();
		return result;//문제없이 회원가입성공시 1을 리턴, 문제발생시 0을 리턴합니다.
	}
	// ------------------------ 회원가입메서드 끝 ------------------------
	// ------------------------로그인 메서드------------------------
	public int logIn(MemberDTO member) {// 입력한 이메일이없으면 -1, 비밀번호 틀리면 0 , 정상로그인이면 1을 리턴합니다.
		int temp = 0;
		int result = -1;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) as cnt from rockgamemembers where userid = '"+member.getEmail()+"'");
			if(rs.next()) {
				temp = rs.getInt("cnt");
			}
			if(temp == 1) {
				rs = stmt.executeQuery("select count(*) as cnt from rockgamemembers where userid = '"+member.getEmail()+"' and userpassword = '"+member.getPassword()+"'");
				if(rs.next()) {
					result = rs.getInt("cnt");
				}else {
					result = 0;
				}
			}else {
				result = -1;
			}
		} catch (SQLException e) {
			System.out.println("로그인 예외 발생 : " + e.getMessage());
		}
		loading();
		return result;
	}
	// ------------------------로그인 메서드 끝------------------------
	// ------------------------전적 조회 메서드 ------------------------
	public TreeMap<String, Integer> myStats(MemberDTO member) { // 메서드실행시 TreeMap을 리턴합니다. 구조는 {Count=4, Draw=2,
																	// Lose=1, Win=1}
		String[] key = { "Win", "Lose", "Draw", "Count" };
		TreeMap<String, Integer> stats = new TreeMap<String, Integer>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from rockgamemembers where userid='"+member.getEmail()+"'");
			if(rs.next()) {
				stats.put(key[0], rs.getInt("win"));
				stats.put(key[1], rs.getInt("lose"));
				stats.put(key[2], rs.getInt("draw"));
				stats.put(key[3], rs.getInt("count"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("전적조회 예외 발생 : " + e.getMessage());
		}
		return stats;
	}
	// ------------------------전적 조회 메서드 끝 ------------------------
	// --------------------------비밀번호 변경 메서드--------------------------
	public int changePw(String nowPassword) {
		int result = 0; // 0 기존패스워드 틀림 .
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) cnt from rockgamemembers where userid='"+Login.member.getEmail()+"' and userpassword='"+Login.member.getPassword()+"'");
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("비밀번호 변경 예외 : "  + e.getMessage());
		}
		return result;
	}
	public int newPasswoard(String newPassword) {
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE rockgamemembers set userPassword=? where userid=?");
			pstmt.setString(1, newPassword);
			pstmt.setString(2, Login.member.getEmail());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("비밀번호 변경 예외 : "  + e.getMessage());
		}
		loading();
		return result;
	}
	// --------------------------비밀번호 변경 메서드끝 --------------------------
	// ------------------------로그아웃 메서드 ------------------------
	public void logOut(MemberDTO member, String date) {// 로그아웃 메서드.로그아웃시간과 게임기록을 DB에업데이트함.
		try {
		PreparedStatement pstmt = conn.prepareStatement("UPDATE rockgamemembers set win=win+?,lose=lose+?,draw=draw+?,count=count+?,lastlogout = ? where userid=?");
		pstmt.setInt(1, member.getWin());
		pstmt.setInt(2, member.getLose());
		pstmt.setInt(3, member.getDraw());
		pstmt.setInt(4, member.getCount());
		pstmt.setString(5, date);
		pstmt.setString(6, member.getEmail());
		pstmt.executeQuery();
		pstmt.close();
		TreeMap<String, Integer> stats = myStats(member);
		int rate = (int) ((double)stats.get("Win") / stats.get("Count") * 100);
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE rockgamemembers set rate='"+rate+"' where userid='"+member.getEmail()+"'");
		}catch (Exception e) {
			System.out.println("로그아웃 예외 발생 : " + e.getMessage());
		}
	}
	// ------------------------로그아웃 메서드 끝------------------------
	//-------------------데이터 서치 메서드 ----------------
	public String dataSearch(MemberDTO member,String data) {
		String result = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from rockgamemembers where userid='"+member.getEmail()+"'");
			if(rs.next()) {
				result = rs.getString(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	// ----------------------로딩 메서드 -----------------------
	public void loading() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				System.out.print("Loading");
				for (int i = 0; i <= 5; i++) {
					System.out.print(".");
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print("Ok!");
				System.out.println();

				super.run();
			}
		};
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// ----------------------로딩 메서드 끝 -----------------------
	// ----------------------부팅 메서드 -----------------------
	public void booting() {
		System.out.print("˚∧＿∧  　+        —̳͟͞͞💗\r\n"
				+ "(  •‿• )つ  —̳͟͞͞ 💗         —̳͟͞͞💗 +\r\n"
				+ "(つ　 <                —̳͟͞͞💗\r\n"
				+ "｜　 _つ      +  —̳͟͞͞💗         —̳͟͞͞💗 ˚\r\n"
				+ "`し´");
		String[] msg = { "가", "위", "바", "위", "보", " ", "게", "임", "V", "3", ".", ".\n" };
		try {
			for (int i = 0; i <= msg.length - 1; i++) {
				System.out.print(msg[i]);
				TimeUnit.MILLISECONDS.sleep(150);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	// ----------------------부팅 메서드 끝 -----------------------
	
	
}