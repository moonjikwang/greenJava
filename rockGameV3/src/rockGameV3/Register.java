package rockGameV3;

import java.util.Scanner;




public class Register {
	static Scanner sc = new Scanner(System.in);
	private String email;
	private String password;
	
	public Register() {
		emailCheck();
	}
	//회원가입 후 다시 Menu 호출 
	public void emailCheck() {
		System.out.println("[회원가입] 이메일을 입력해주세요.");
		System.out.print(">");
		email = sc.next();
		Validate(email);
		System.out.println("[회원가입] 패스워드를 입력해주세요.");
		System.out.print(">");
		password = sc.next();
		MemberDTO member = new MemberDTO(email,password);
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
		new EmailCheck(email);
		
	}

}
