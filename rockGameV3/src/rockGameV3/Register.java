package rockGameV3;
/**
 * 
 * @author 백승연
 *
 */
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
		int newMember = MemberDAO.getInstance().registerId(member);
		if (newMember == 1) {
			System.out.println("회원가입이 완료되었습니다.");
				new Menu();
			} else {
				System.out.println("회원가입실패. 종료합니다.");
				System.exit(0);
			}
		
		
	}
	private void Validate(String email) {
		new EmailCheck(email);
		
	}

}
