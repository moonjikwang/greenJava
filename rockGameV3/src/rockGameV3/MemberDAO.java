package rockGameV3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


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

		if (!folder.exists() || !folder.isDirectory()) {
			folder.mkdir();
		} else {
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
		}
		return result;
	}
	//------------------------ 회원가입메서드 끝 ------------------------

	//------------------------로그인 메서드------------------------
	public int logIn(MemberDTO member) {
		System.out.print("멤버파일 ->");
		int result = -1; // ID 없음, 0 Pass 틀림, 1 OK
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
		return result;
	}
	//--------------------------비밀번호 변경 메서드끝 --------------------------
	
	//-----------------------아이디분리 메서드-------------------------------
	public String divideId(String email) {
		String id = email.substring(0,email.indexOf('@')) + ".dat";
		return id;
	}
	//-----------------------아이디분리 메서드 끝------------------------------

}
