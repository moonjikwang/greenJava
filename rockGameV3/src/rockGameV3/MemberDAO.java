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
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class MemberDAO {
//---------------------필드선언 및 초기화 --------------------
	private static MemberDAO instance = new MemberDAO();
	private MemberDTO member;
	private static String rootFolder = "Members/";
	private static File folder = new File(rootFolder);
	private static Scanner sc = new Scanner(System.in);
	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
	BufferedReader br;
//---------------------필드선언 및 초기화 끝 --------------------
	//브랜치 테스트
	//-----------------싱글톤 작업 --------------------
	private MemberDAO() {
	}
	
	public static MemberDAO getInstance() {
		return instance;
	}
	//-----------------싱글톤 작업끝  --------------------

	//------------------------ 회원가입메서드------------------------
	public int registerId(MemberDTO member) {
		int result = 0;// 결과값 flag.. 모두 OK 1, 예외 0

		if (!folder.exists() || !folder.isDirectory())
			folder.mkdir();
		
		File newMember = new File(folder, member.getId() + ".dat");// Parent Folder 하위에 생성할 사용자 Email 정보 획득..

		if (br == null && fw == null) {
			try {
				fw = new FileWriter(newMember);
				bw = new BufferedWriter(fw);
				bw.write("Email:" + member.getEmail() + "\n");
				bw.write("Password:" + member.getPassword() + "\n");
				bw.write("LastLogin:" + member.getLastLogIn() + "\n");
				bw.write("LastLogOut:" + member.getLastLogOut() + "\n");
				bw.write("Win:" + member.getWin() + "\n");
				bw.write("Lose:" + member.getLose() + "\n");
				bw.write("Draw:" + member.getDraw() + "\n");
				bw.write("Count:" + member.getCount() + "\n");
				bw.close();
				result = 1;
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
		}
		loading();
		return result;
	}
	//------------------------ 회원가입메서드 끝 ------------------------

	//------------------------로그인 메서드------------------------
	public int logIn(MemberDTO member) {//  입력한 이메일이없으면 -1, 비밀번호 틀리면 0 , 정상로그인이면 1을 리턴합니다.
		System.out.print("멤버파일 ->");
		int result = -1; 
		File[] fileList = folder.listFiles();
		String id = divideId(member.getEmail());
		String password = null;
		File thePlayer = null;
		for (int i = 0; i < fileList.length; i++) {
			thePlayer = fileList[i];
			System.out.println(thePlayer.getName());
			if (thePlayer.getName().equals(id)) {
				result = 0;
				break;
			}
		}
		if(result == 0) {
		try {
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
	//------------------------로그인 메서드 끝------------------------
	
	//--------------------------비밀번호 변경 메서드--------------------------
	public int changePw(String nowPassword) {
		int result = -1; // -1 기존패스워드 틀림 .
		try {
		String id = divideId(Login.member.getEmail());
		File file = new File(rootFolder,id);
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
		
		}catch (Exception e) {
		}
		return result;
	}
	public int newPasswoard(String newPassword) {
		int result = -1;
		String id = divideId(Login.member.getEmail());
		File file = new File(rootFolder,id);
		try {
		br = new BufferedReader(new FileReader(file));
		String newFile = ""; //새 계정정보 DB값을 담을 문자열
		String temp;
		while((temp = br.readLine()) != null) {
			if(temp.startsWith("Password")) {
				newFile += (temp.substring(0, temp.indexOf(":")+1) + newPassword + "\n");
				result = 0;
				continue;
			}
			newFile += (temp + "\n");
		}
		
		fw = new FileWriter(file);
		fw.write(newFile);
		
		fw.close();
		br.close();
		
		}catch (Exception e) {
		}
		loading();
		return result;
	}
	//--------------------------비밀번호 변경 메서드끝 --------------------------
	
	//-----------------------아이디분리 메서드-------------------------------
	public String divideId(String email) {
		String id = email.substring(0,email.indexOf('@')) + ".dat";
		return id;
	}
	//-----------------------아이디분리 메서드 끝------------------------------

	//----------------------로딩 메서드 -----------------------
	public void loading() {
		Thread  thread = new Thread() {
			@Override
			public void run() {
				System.out.print("Loading");
				for(int i = 0;i <= 5; i++) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//----------------------로딩 메서드 끝 -----------------------
	//----------------------로딩 메서드 -----------------------
		public void booting() {
			String[] msg = {".","`","`",".","`","`",".","\n`","."," "," "," "," ",".","`","\n "," "," ","`",".","`"};
			try {
				for(int i = 0; i <= msg.length-1; i++) {
					System.out.print(msg[i]);
					TimeUnit.MILLISECONDS.sleep(70);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//----------------------로딩 메서드 끝 -----------------------
}
