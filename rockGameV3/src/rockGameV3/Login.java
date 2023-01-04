package rockGameV3;

import java.util.Scanner;

public class Login {
	static Scanner sc = new Scanner(System.in);
	private String email;
	//Login 검증 후 로그인기록 추가 & GameLogic 호출
	
	public Login() {
		System.out.print("ID : ");
		String id = sc.next();
		System.out.print("PW : ");
		String password = sc.next();
		MemberDTO member = null;
		member = new MemberDTO(id,password);
		System.out.println(member.getId());
		new MemberDAO().logIn(member);
		
		System.exit(0);
	}

}
