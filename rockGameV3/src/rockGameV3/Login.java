package rockGameV3;
/*
 * 이메일을 입력받아서, 해당 id.data 파일의 존재여부 확인하여
 * 존재하면 패스워드받기. 패스워드 일치시 -> id님 로그인을 환영합니다.
 * 불일치시 -> 암호가 틀립니다. (3회 기회 -> 관리자에게 연락바랍니다.프로그램종료)
 * ->로그인 성공시 GameMenu클래스 호출(인스턴스생성)
 */
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
