package rockGameV3;

import java.util.Scanner;

/**
 * 
 * @author 박수현
 *
 */
/*
 * 이클래스는 로그인성공 -> 게임메뉴에서 게임하기를 선택하여호출된 클래스입니다. 가위바위보 게임 로직을 구현하고, 게임을 그만하겠다고
 * 하기전까지 반복합니다. 그만한다고 하면 잘가라는 메세지를 전달하고 게임을 종료합니다. 이때, 사용자의 로그인시간과, 로그아웃기나, 그날의
 * 전적을 기존전적에 더해 승률까지 추출하여 해당 사용자파일에 저장합니다.
 * 
 */
public class GameStart {
	Scanner sc = new Scanner(System.in);

	public GameStart() {
		startLogic(Login.member);
	}
	// -----------------------------------------------------------

	public void startLogic(MemberDTO member) { // 실제 게임로직 실행 메서드
		// --------------------변수 선언및 초기화----------------------
		String selectCom = null;
		String result = null;
		String selectUser = null;
		int newGame = 0;
	}
}
