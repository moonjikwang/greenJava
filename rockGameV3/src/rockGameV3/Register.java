package rockGameV3;

import java.util.Scanner;




public class Register {
	static Scanner sc = new Scanner(System.in);
	private String id;
	private String password;
	
	public Register() {
		emailCheck();
	}
	//회원가입 후 다시 Menu 호출 
	public void emailCheck() {
		System.out.println("[회원가입] 아이디를 입력해주세요.");
		System.out.print(">");
		id = sc.next();
		Validate(id);
		System.out.println("[회원가입] 패스워드를 입력해주세요.");
		System.out.print(">");
		password = sc.next();
		MemberDTO member = new MemberDTO(id,password);
		int newMember = new MemberDAO(member).doWork();
		if (newMember == 1) {
			System.out.println("회원가입이 완료되었습니다.");
				new Menu();
			} else {
				// Yes 가 아닌 다른 경우는 일단 종료 시키는 로직으로 했으나 여러분이 다른걸로 바꿔보세요~
				System.out.println("회원가입실패. 종료합니다.");
				System.exit(0);
			}
		
		
	}
	private void Validate(String email) {
//		if(email.isEmpty() || email.indexOf('@') == -1) {
//			System.out.println("빈 문자열이거나 @가 없습니다.");
//		}
//		String id = email.substring(0,email.indexOf('@'));
//		String server = email.substring(email.indexOf('@'),email.length());
//		if(id.length() >12 || id.length() < 8) {
//			System.out.println("아이디는 8~ 12자 사이여야 합니다.");
//		}else if(id.charAt(0) < 65 || id.charAt(0) > 90) {
//			System.out.println("아이디의 첫자는 영어 대문자여야 합니다.");
//		}else if(!id.matches(".*[0-9].*")) {
//			System.out.println("아이디에는 숫자가 하나이상 들어가야합니다.");
//		}else {
//			System.out.println("로그인 되었습니다.");
//		}
//		this.email = id + server;
		
	}

}
