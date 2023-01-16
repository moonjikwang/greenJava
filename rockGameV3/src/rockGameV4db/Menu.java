package rockGameV4db;
/**
 * 
 * @author 이유현
 *
 */
/*
 * 메뉴클래스.
 * 1.로그인하기 2.사용자계정생성 3.기타메뉴보기
 * 사용자에게 입력받고 각 메뉴클래스를 호출(인스턴스생성)
 * login 클래스, Register 클래스, OtherMenu 클래스
 */
import java.util.Scanner;

public class Menu {
	static Scanner sc = new Scanner(System.in);
	public Menu() {
		showMenu();
	}
	
	public void showMenu() {
		MemberDAO.getInstance().booting();
		System.out.println("안녕하세요 가위바위보 게임입니다.");
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│1.로그인하기 2.사용자계정생성 3.기타메뉴보기 │");
		System.out.println("└──────────────────────────────┘");
		System.out.print(">>");
		int select = sc.nextInt();
		switch (select) {
		case 1:login();
			break;
		case 2:register();
		break;
		case 3:otherMenu();
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
	public void otherMenu() {
		new OtherMenu();
	}

}
