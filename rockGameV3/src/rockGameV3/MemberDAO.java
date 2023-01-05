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
	private static String theParentFolder = "Members/";
	private static File folder = new File(theParentFolder);

	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
	BufferedReader br;

	private MemberDAO() {
	}
	public static MemberDAO getInstance() {
		return instance;
	}

	public int doWork(MemberDTO member) {// 회원 가입일지, 사용자 정보를 세팅일지 한번에 처리하는 메서드
		this.member = member;
		int result = 0;// 결과값 Flag ... 예외 0, OK 1
		if (this.member instanceof MemberDTO) {// 로그인 사용자 객체인 경우 파일 생성
			result = registerId(member);
		} else if (this.member instanceof MemberDTO) {

		}
		return result;
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
					bw.write("Email : " + member.getEmail() + "\n");
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
		File[] fileList = folder.listFiles();
		String id = member.getId() + ".dat";
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
		}}
		return result;
	}

}
