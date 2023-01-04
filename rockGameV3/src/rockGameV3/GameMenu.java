package rockGameV3;

import java.util.Scanner;

/*
 * 이 페이지는 로그인성공후 -> 게임메뉴 출력 페이지 입니다.
 * 1.게임하기 2.전적보기 3.마지막 로그인날짜확인하기 4.암호변경
 * 각 메뉴번호를 입력받고,해당하는 클래스를 호출합니다.
 * GameStart , ShowStats, LastLogin,ChangePw
 */
public class GameMenu {
	static Scanner sc = new Scanner(System.in);
	public GameMenu() {
		showMenu();
	}

	private void showMenu() {
		
		System.out.println("1.게임하기 2.전적보기 3.마지막접속확인 4.암호변경");
		int userSelect = sc.nextInt();
		switch (userSelect) {
		case 1:
			new GameStart();
			break;
		case 2:
			new ShowStats();
			break;
		case 3:
			new LastLogin();
			break;
		case 4:
			new ChangePw();
			break;
		default:
			System.out.println("잘못입력했습니다. 다시 입력해주세요.");
			new GameMenu();
		}
		
	}

}
