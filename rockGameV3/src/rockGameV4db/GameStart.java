package rockGameV4db;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

	// -----------------------생성자 정의---------------------------
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
		// --------------------------------------------------------

		// --------------------게임시작 종료하기전까지 반복--------------
		while (true) {
			boolean flag = false;
			do {
				if (flag)
					System.out.println("[틀린값입니다. 다시입력해주세요.]");
				System.out.println("[가위바위보 중에 입력하세요]");
				System.out.print(">");
				selectUser = sc.next();
				flag = true;
			} while (!selectUser.equals("가위") && !selectUser.equals("바위") && !selectUser.equals("보"));

			String[] com = { "가위", "바위", "보" };
			selectCom = com[(int) (Math.random() * com.length)];

			if (selectUser.equals("보") && selectCom.equals("바위") || selectUser.equals("가위") && selectCom.equals("보")
					|| selectUser.equals("바위") && selectCom.equals("가위")) {
				result = "축하합니다! 유저의 승리 ✿ܓ✿ܓ "; // 이기거나
				member.setWin(member.getWin()+1);
			} else if (selectUser.equals(selectCom)) {
				result = "비겼습니다.ʕʘ̅͜ʘ̅̅ʔ" ;     //비기거나
				member.setDraw(member.getDraw()+1);
			} else {
				result = "졌습니다.(ुŏ̥̥̥̥םŏ̥̥̥̥) ु" ; //졌습니다.
				member.setLose(member.getLose()+1);
			}
			member.setCount(member.getCount()+1); // 게임 카운트 + 1
			System.out.println(result + "( 컴퓨터: " + selectCom + " 유저 :" + selectUser + ")");
			do {
			System.out.println("┌─────────────┐");
			System.out.println("│1.뉴게임 2.게임종료│");
			System.out.println("└─────────────┘");
			System.out.print(">>");
			try {
				newGame = sc.nextInt();
			} catch (Exception e) {
				System.out.println("예외발생 : " + e.getMessage() + "\n 잘못 입력했습니다. 그냥 게임을 종료합니다.");
				newGame = 2;
			}
			switch (newGame) {
			case 1:
				break;
			case 2:
				String date = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss").format(Calendar.getInstance().getTime());
				MemberDAO.getInstance().logOut(member,date);
				System.out.println("게임기록이 저장되었습니다.안녕히가세요.₍ᐢ.ˬ.ᐢ₎❤️");
				System.exit(0);
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
			}while(newGame != 1 && newGame != 2);
			// ------------------------------------------------------------------
		}

	}
}