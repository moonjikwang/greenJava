package rockGameV3;

/**
 * 
 * @author 문지광
 *
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class MemberDAO {
//---------------------필드선언 및 초기화 --------------------
	private static MemberDAO instance = new MemberDAO();
	private static String rootFolder = "Members/";
	private static File folder = new File(rootFolder);
	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
	BufferedReader br;

//---------------------필드선언 및 초기화 끝 --------------------

	// -----------------싱글톤 작업 --------------------
	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		return instance;
	}
	// -----------------싱글톤 작업끝 --------------------
	
	//-------------------플레이어 카운트 ---------------
	public File[] fileList() {
		File[] fileList = folder.listFiles();
		return fileList;
	}
	//-------------------플레이어 카운트 끝 ---------------
	//-------------------전체 플레이어 승률---------------	//-------------------수정해야함. 여기이상함.---------------
	//-------------------전체 플레이어 승률---------------
	public TreeMap<String, TreeMap<String, Integer>> sortPlayers() { 
	TreeMap<String, TreeMap<String, Integer>> stats = new TreeMap<String, TreeMap<String,Integer>>();
	File[] fileList = folder.listFiles();
	for(int i = 0; i < fileList.length; i++) {
		stats.put(fileList[i].getName().substring(0, fileList[i].getName().length()-4), sortPlayersSub(fileList[i].getName()));
	}
return stats;
}
	//-------------------전체 플레이어 승률---------------
	public TreeMap<String, Integer> sortPlayersSub(String id) { // 메서드실행시 TreeMap을 리턴합니다. 구조는 {Count=4, Draw=2,
		// Lose=1, Win=1}
String[] key = { "Win", "Lose", "Draw", "Count" };
TreeMap<String, Integer> stats = new TreeMap<String, Integer>();
File file = new File(rootFolder, id);
try {
br = new BufferedReader(new FileReader(file));
String temp = null;
while ((temp = br.readLine()) != null) {
for (int i = 0; i < key.length; i++) {
if (temp.startsWith(key[i])) {
int data = Integer.parseInt(temp.substring(temp.indexOf(":") + 1, temp.length()));
stats.put(key[i], data);
}
}
}
} catch (Exception e) {
}
return stats;
}
//-------------------전체 플레이어 승률---------------	//-------------------수정해야함. 여기이상함.---------------
	// ------------------------ 회원가입메서드------------------------
	public int registerId(MemberDTO member) {
		int result = 0;// 문제없이 회원가입성공시 1을 리턴, 문제발생시 0을 리턴합니다.

		if (!folder.exists() || !folder.isDirectory())
			folder.mkdir();
		File newMember = new File(folder, member.getId() + ".dat");// Parent Folder 하위에 생성할 사용자 Email 정보 획득..
		if (!newMember.exists()) {
			if (br == null && fw == null) {
				try {
					fw = new FileWriter(newMember);
					bw = new BufferedWriter(fw);
					bw.write("Email:" + member.getEmail() + "\n");
					bw.write("Password:" + member.getPassword() + "\n");
					bw.write("LastLogIn:" + member.getLastLogIn() + "\n");
					bw.write("LastLogOut:" + member.getLastLogOut() + "\n");
					bw.write("Win:" + member.getWin() + "\n");
					bw.write("Lose:" + member.getLose() + "\n");
					bw.write("Draw:" + member.getDraw() + "\n");
					bw.write("Count:" + member.getCount() + "\n");
					bw.write("Rate:" + member.getRate() + "\n");
					bw.close();
					result = 1;
					bw.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

			}
		}
		loading();
		return result;
	}
	// ------------------------ 회원가입메서드 끝 ------------------------

	// ------------------------로그인 메서드------------------------
	public int logIn(MemberDTO member) {// 입력한 이메일이없으면 -1, 비밀번호 틀리면 0 , 정상로그인이면 1을 리턴합니다.
		System.out.print("멤버파일 ->");
		int result = -1;
		File[] fileList = folder.listFiles();
		String id = divideId(member.getEmail());
		String password = null;
		String lastLogIn = member.getLastLogIn();
		File thePlayer = null;
		for (int i = 0; i < fileList.length; i++) {
			thePlayer = fileList[i];
			System.out.println(thePlayer.getName());
			if (thePlayer.getName().equals(id)) {
				result = 0;
				break;
			}
		}
		if (result == 0) {
			try {
				cover("LastLogIn", lastLogIn, id);
				fr = new FileReader(thePlayer.getAbsolutePath());
				br = new BufferedReader(fr);
				String keySearch = null;
				while ((keySearch = br.readLine()) != null) {
					if (keySearch.startsWith("Password")) {
						password = keySearch.substring(keySearch.indexOf(":") + 1, keySearch.length());
						if (password.equals(member.getPassword())) {
							result = 1;
						}
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
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
		String id = divideId(member.getEmail());
		File file = new File(rootFolder, id);
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				for (int i = 0; i < key.length; i++) {
					if (temp.startsWith(key[i])) {
						int data = Integer.parseInt(temp.substring(temp.indexOf(":") + 1, temp.length()));
						stats.put(key[i], data);
					}
				}
			}
		} catch (Exception e) {
		}
		return stats;
	}
	// ------------------------전적 조회 메서드 끝 ------------------------

	// --------------------------비밀번호 변경 메서드--------------------------
	public int changePw(String nowPassword) {
		int result = -1; // -1 기존패스워드 틀림 .
		try {
			String id = divideId(Login.member.getEmail());
			File file = new File(rootFolder, id);
			br = new BufferedReader(new FileReader(file));
			String keySearch = null;
			while ((keySearch = br.readLine()) != null) {
				if (keySearch.startsWith("Password")) {
					String password = keySearch.substring(keySearch.indexOf(":") + 1, keySearch.length());
					if (password.equals(nowPassword)) {
						result = 0; // 0 기존 패스워드 일치
					}
				}
			}
			br.close();

		} catch (Exception e) {
		}
		return result;
	}

	public int newPasswoard(String newPassword) {
		int result = -1;
		String id = divideId(Login.member.getEmail());
		File file = new File(rootFolder, id);
		try {
			br = new BufferedReader(new FileReader(file));
			String newFile = ""; // 새 계정정보 DB값을 담을 문자열
			String temp;
			while ((temp = br.readLine()) != null) {
				if (temp.startsWith("Password")) {
					newFile += (temp.substring(0, temp.indexOf(":") + 1) + newPassword + "\n");
					result = 0;
					continue;
				}
				newFile += (temp + "\n");
			}

			fw = new FileWriter(file);
			fw.write(newFile);

			fw.close();
			br.close();

		} catch (Exception e) {
		}
		loading();
		return result;
	}
	// --------------------------비밀번호 변경 메서드끝 --------------------------

	// ------------------------로그아웃 메서드 ------------------------
	public void logOut(MemberDTO member, String date) {// 로그아웃 메서드.로그아웃시간과 게임기록을 db에업데이트함.
		String id = divideId(member.getEmail());
		String[] key = { "Win", "Lose", "Draw", "Count" };
		int[] newData = { member.getWin(), member.getLose(), member.getDraw(), member.getCount() };
		cover("LastLogOut", date, id);
		for (int i = 0; i < key.length; i++) {
			coverStats(key[i], newData[i], id);
		}
		//승률 계산 적용
		TreeMap<String, Integer> stats = myStats(member);
		int rate = (int)((double)stats.get("Win") / stats.get("Count") * 100);
		coverStats("Rate", rate, id);
	}
	// ------------------------로그아웃 메서드 끝------------------------
	
	
	
	// -----------------------아이디분리 메서드-------------------------------
	public String divideId(String email) { // 이메일을 넣으면 아이디만 분리해서 리턴해줍니다.
		String id = email.substring(0, email.indexOf('@')) + ".dat";
		return id;
	}
	// -----------------------아이디분리 메서드 끝------------------------------
	
	//------------------------데이터조회  메서드  ------------------------
	public String dataSearch(MemberDTO member,String key) { //멤버정보, 검색하고자하는 MemberDTO의 멤버필드를 입력하면 리턴해줍니다.
		String result = null;
		String id = divideId(member.getEmail());
		File file = new File(rootFolder, id);
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = null;
			while ((temp = br.readLine()) != null) {
					if (temp.startsWith(key)) {
						String data = temp.substring(temp.indexOf(":") + 1, temp.length());
						result = data;
					}
			}
			br.close();
		} catch (Exception e) {
		}
		return result;
	}
	//------------------------데이터조회  메서드 끝  ------------------------

	// 데이터 변경 메서드-----------------------------------------
	public void cover(String key, String newData, String id) {
		File file = new File(rootFolder, id);
		try {
			br = new BufferedReader(new FileReader(file));
			String newFile = ""; // 새 계정정보 DB값을 담을 문자열
			String temp;
			while ((temp = br.readLine()) != null) {
				if (temp.startsWith(key)) {
					newFile += (temp.substring(0, temp.indexOf(":") + 1) + newData + "\n");
					continue;
				}
				newFile += (temp + "\n");
			}

			fw = new FileWriter(file);
			fw.write(newFile);

			fw.close();
			br.close();

		} catch (Exception e) {
		}
	}
	// 데이터 변경메서드 끝----------------------------------

	// 전적데이터 변경 메서드-----------------------------------------
	public void coverStats(String key, int newData, String id) {
		File file = new File(rootFolder, id);
		try {
			br = new BufferedReader(new FileReader(file));
			String newFile = ""; // 새 계정정보 DB값을 담을 문자열
			String temp;
			while ((temp = br.readLine()) != null) {
				if (temp.startsWith(key)) {
					int oldData = Integer.parseInt(temp.substring(temp.indexOf(":") + 1, temp.length()));
					newFile += (temp.substring(0, temp.indexOf(":") + 1) + (oldData + newData) + "\n");
					continue;
				}
				newFile += (temp + "\n");
			}

			fw = new FileWriter(file);
			fw.write(newFile);

			fw.close();
			br.close();

		} catch (Exception e) {
		}
	}
	// 전적데이터 변경메서드 끝----------------------------------
	
	
	
	
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
		String[] msg = { "가", "위", "바", "위", "보", " ", "게", "임", "V", "3", ".", ".\n" };
		try {
			for (int i = 0; i <= msg.length - 1; i++) {
				System.out.print(msg[i]);
				TimeUnit.MILLISECONDS.sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	// ----------------------부팅 메서드 끝 -----------------------
}