package rockGameV3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MemberDAO {

	private static MemberDAO instance = new MemberDAO();
	private MemberDTO member;
	private static String rootFolder = "Members/";
	private static File folder = new File(rootFolder);
	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
	BufferedReader br;

	private MemberDAO() {
	}
	
	public static MemberDAO getInstance() {
		return instance;
	}

	// 회원가입메서드
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
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("사용자 생성 예외 발생함..");
					System.out.println(e.getMessage());
				}

			}
		}

		return result;
	}

	// 로그인 메서드
	public int logIn(MemberDTO member) {
		System.out.print("멤버파일 ->");
		int result = -1; // ID 없음, 0 Pass 틀림, 1 OK
		File[] fileList = folder.listFiles();
		String email = member.getEmail();
		String id = email.substring(0,email.indexOf('@')) + ".dat";
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
			String keySerch = null;
			while ((keySerch = br.readLine()) != null) {
				if (keySerch.startsWith("Password")) {
					password = keySerch.substring(keySerch.indexOf(":") + 1, keySerch.length());
					if (password.equals(member.getPassword())) {
						result = 1;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}}
		return result;
	}

}
