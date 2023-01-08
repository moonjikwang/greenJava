package rockGameV3;
/**
 * 
 * @author 백승연
 *
 */
import java.util.Scanner;

/*
 * 이메일을 입력받고 입력받은 값을 파라미터로 받는 Validate() 메서드를 호출합니다.
 * 검증에 이상이없으면, 패스워드를 입력받고, 이메일과 패스워드를 파라미터로 받는 MemberDTO 인스턴스 생성합니다.
 * 이메일과 패스워드정보가 담긴 MemberDTO 객체를 DB에 저장하기위해 해당 객체를 파라미터로 받는 MemberDAO.getInstance().registerId()메서드를 호출합니다.
 * 해당 메서드는 회원가입이 정상적으로 이루어지면 정수 1을 리턴하고, 오류가 발생하면 0을 리턴합니다.
 */


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
