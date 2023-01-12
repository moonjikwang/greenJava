package rockGameV3;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * 
 * @author 백승연
 *
 */
/*
 * 이메일을 입력받아서, 해당 id.data 파일의 존재여부 확인하여 존재하면 패스워드받기. 패스워드 일치시 -> id님 로그인을 환영합니다.
 * 불일치시 -> 암호가 틀립니다. (3회 기회 -> 관리자에게 연락바랍니다.프로그램종료) 존재하지 않으면 -> 회원가입해주세요
 *
 * <Login 구현> 로그인성공 -> 마지막 로그인 날짜 출력 : String(date) -> GameMenu클래스 호출(인스턴스생성)
 */

//Login 검증 후 로그인기록 추가 & GameMenu 호출
//Login 클래스 가이드
// 1.(String)email , (String)password, (String)date 를 파라미터로 받는 MemberDTO 인스턴스를 생성하고, (이메일,패스워드는 입력받고, date는 현재시각)
// 2.DB를 확인하여 로그인을 검증해주는 MemberDAO.getInstance().logIn(); 메서드에 파라미터로 위에서 생성한 인스턴스를 넘겨주세요.
// 3.해당 메서드는 int 타입으로 값을 리턴해주는데, 로그인에 성공했을경우 1을리턴, 비밀번호가 틀린경우 0을리턴, 아이디가 없는경우 -1을 리턴합니다.
// 4.만약 로그인에 성공했다면 , GameMenu 클래스를 호출해주세요(GameMenu클래스의 기본생성자호출)

public class Login {
	static Scanner sc = new Scanner(System.in);
	protected static MemberDTO member = null;
	String email;
	String password;
	String date;
	int temp = 0;

	public Login() {
		System.out.print("ID : ");
		email = sc.next();
		System.out.print("PW : ");
		password = sc.next();
		date = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss").format(Calendar.getInstance().getTime());
		member = new MemberDTO(email, password, date);
		int logInVal = MemberDAO.getInstance().logIn(member);
		logInVal(logInVal);
	}

	public void logInVal(int logInVal) {
		switch (logInVal) {
		case -1:
			// 아이디 없는경우 -> 회원가입해주세요, Register() 클래스호출
			if (email == null) {
				System.out.println("해당 아이디는 없습니다:( 회원가입해주세요.");
				new Register();
			}
			break;
		case 0:
			// 비번틀린경우 -> 3회 기회, 관리자에게 연락바랍니다(프로그램종료)
			if (temp < 3) {
				System.out.println("비밀번호가 틀렸습니다." + temp + "회 시도중");
				reInputPw();
			} else {
				System.out.println("비밀번호를 3회 틀렸습니다. 관리자에게 연락바랍니다.");
				System.exit(0);
			}
			break;
		case 1:
			// 로그인 성공한 경우
			// 마지막 로그인 날짜 출력, GameMenu()클래스 호출(인스턴스생성)
			System.out.println("로그인에 성공했습니다⸜❤︎⸝‍ " + email + "님 반갑습니다♫•*¨*•.¸¸♪✧");
			System.out.println(member.getLastLogIn());
			new GameMenu();
			break;
		default:
			break;
		}
	}

	public void reInputPw() {
		System.out.print("PW : ");
		password = sc.next();
		date = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss").format(Calendar.getInstance().getTime());
		member = new MemberDTO(email, password, date);
		int logInVal = MemberDAO.getInstance().logIn(member);
		logInVal(logInVal);
	}
}