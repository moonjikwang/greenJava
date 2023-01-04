package rockGameV3;

import java.util.Scanner;

public class Menu {
	static Scanner sc = new Scanner(System.in);
	public Menu() {
		showMenu();
	}
	
	public void showMenu() {
		System.out.println("가위바위보 게임입니다.");
		System.out.println("1.회원가입 2.로그인 3.랭킹보기 4.종료");
		System.out.print(">");
		int select = sc.nextInt();
		switch (select) {
		case 1:register();
			break;
		case 2:login();
		break;
		case 3:ranking();
		break;
		default:
			break;
		}
	}
	
	
	public void login() {
		new Login();
	}
	public void register() {	
		new Register();
	}
	public void ranking() {
		new Ranking();
	}

}
