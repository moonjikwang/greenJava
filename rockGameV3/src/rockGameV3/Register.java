package rockGameV3;

/**
 * 
 * @author 백승연
 *
 */
import java.util.Scanner;
/*
 * <Register 구현>
 * 이메일을 입력받고 입력받은 값을 파라미터로 받는 Validate() 메서드를 호출합니다./
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

	// 회원가입 후 다시 Menu 호출
	public void emailCheck() {
		System.out.println("[회원가입] 이메일을 입력해주세요.");
		System.out.print(">");
		email = sc.next();
		Validate(email);

		/*
		 * ID가 존재하면 -> "사용할 수 없는 ID입니다" 출력&재입력받기 ID가 존재하지 않으면 ->ID.data 파일 생성 -> 첫 생성시
		 * 파일 아래 내용으로 저장시키기 regDate : 가입한 날짜의 yyyy-MM-dd apm HH:mm:dd
		 */

		// ID생성완료후 PW입력받기
		System.out.println("[회원가입] 패스워드를 입력해주세요.");
		System.out.print(">");
		password = sc.next();
		MemberDTO member = new MemberDTO(email, password);
		int newMember = MemberDAO.getInstance().registerId(member);
		if (newMember == 1) {
			System.out.println("회원가입이 완료되었습니다.");
			/*
			 * ID와 PW 생성완료 되면 -> 환영메세지 "V3RockGame에 오신걸 환영합니다!" -> ID와 PW 입력받고 저장 -> 로그인할지
			 * 메세지 "로그인 하시겠습니까? y/n" -> y 선택하면 로그인메세지 띄우기 -> n 선택하면 메인 첫화면으로
			 */

			new Menu();
		} else {
			// Yes 가 아닌 다른 경우는 일단 종료 시키는 로직으로 했으나 여러분이 다른걸로 바꿔보세요~
			System.out.println("회원가입실패. 종료합니다.");
			System.exit(0);
		}

	}

	private void Validate(String email) {
		new EmailCheck(email);
		/* ID가 존재하면 -> "사용할 수 없는 ID입니다" 출력&재입력받기
		 * ID가 존재하지 않으면 ->ID.data 파일 생성 
		 *-> 첫 생성시 파일 아래 내용으로 저장시키기
		 *regDate : 가입한 날짜의 yyyy-MM-dd apm HH:mm:dd
		 */
		switch (email) {
		case -1://String 생각해보기
			// ID가 존재하면 -> "사용할 수 없는 ID입니다" 출력&재입력받기
			if (email in dataSearch )
			break;

		default:
			break;
		}
		
	}

}
