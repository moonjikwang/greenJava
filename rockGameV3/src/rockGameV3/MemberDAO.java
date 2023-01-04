package rockGameV3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemberDAO {

	private MemberDTO member;
	private static String theParentFolder = "Members/";
	private static File root = new File(theParentFolder);

	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
	BufferedReader br;

	public MemberDAO() {

	}

	public MemberDAO(MemberDTO member) {
		this.member = member;
	}

	public int doWork() {// 회원 가입일지, 사용자 정보를 세팅일지 한번에 처리하는 메서드
		int result = 0;// 결과값 Flag ... 예외 0, OK 1
		if (this.member instanceof MemberDTO) {// 로그인 사용자 객체인 경우 파일 생성
			result = registerId((MemberDTO) member);
		} else if (this.member instanceof MemberDTO) {

		}
		return result;
	}

	// 회원가입메서드
	public int registerId(MemberDTO member) {
		int result = 0;// 결과값 flag.. 모두 OK 1, 예외 0

		if (!root.exists() || !root.isDirectory()) {
			root.mkdir();
		} else {
			File newMember = new File(root, member.getId() + ".dat");// Parent Folder 하위에 생성할 사용자 Email 정보 획득..

			if (br == null && fw == null) {
				try {
					fw = new FileWriter(newMember);
					bw = new BufferedWriter(fw);
					bw.write("Email : " + member.getId() + "\n");
					bw.write("Password : " + member.getPassword());
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
		File[] fileList = root.listFiles();
		String id = member.getId() + ".dat";
		String password = null;
		int differ = 0;
		File thePlayer = null;
		for (int i = 0; i < fileList.length; i++) {
			thePlayer = fileList[i];
			System.out.println(thePlayer.getName());
			if (thePlayer.getName().equals(id)) {
				result = 0;
				break;
			}
		}

		try {
			FileReader fr = new FileReader(thePlayer.getAbsolutePath());
			BufferedReader br = new BufferedReader(fr);
			String msg = null;
			while ((msg = br.readLine()) != null) {
				if (msg.startsWith("Password")) {
					password = msg.substring(msg.indexOf(":") + 2, msg.length());
					if (password.equals(member.getPassword())) {
						result = 1;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
