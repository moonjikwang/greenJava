package rockGameV3;

import java.util.Scanner;

/*
 * 이 클래스는 로그인성공-> 게임메뉴에서 암호변경을 클릭하여 호출된 클래스입니다.
 * 기존 암호를 물어보고 일치하다면 신규암호를 받아서 업데이트 합니다.
 * 이후 변경된 암호로 다시로그인할수 있도록 로그인 클래스를 호출합니다.
 */
public class ChangePw {
	static Scanner sc = new Scanner(System.in);
	public ChangePw() {
		System.out.println("[비밀번호 변경]현재 비밀번호를 입력해주세요.");
		System.out.print(">");
		String password = sc.next();
		int result = MemberDAO.getInstance().changePw(password);
		switch (result) {
		case 0:newPassword();
			break;
		case -1:System.out.println("기존비밀번호가 틀렸습니다.");
		break;
		default:
			break;
		}
	}
	private void newPassword() {
		System.out.println("기존비밀번호 일치. 새 비밀번호를 입력하세요.");
		System.out.print(">");
		String newPassword = sc.next();
		int result = MemberDAO.getInstance().newPasswoard(newPassword);
		switch (result) {
		case 0:System.out.println("비밀번호가 정상적으로 변경되었습니다.");
			break;
		default:
			System.out.println("비밀번호변경 실패");
		}
	}

}
