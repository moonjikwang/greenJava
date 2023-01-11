package rockGameV3;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * 
 * @author 박수현
 *
 */
/*
 *  이 클래스는 로그인성공 -> 게임메뉴에서 전적보기 클릭하여 호출된클래스입니다.
 * 해당사용자의 누적 전적을 출력하고 게임을 종료합니다.
 */
public class ShowStats {//

	static Scanner sc = new Scanner(System.in);
	public ShowStats() {
		TreeMap<String, Integer> stat = MemberDAO.getInstance().myStats(Login.member);
		System.out.println("총 플레이수 : "+stat.get("Count") + "회");
		System.out.print(stat.get("Win") + " 승 ");
		System.out.print(stat.get("Lose") + " 패 ");
		System.out.print(stat.get("Draw") + " 무 ,");
		System.out.println("플레이어 승률 : " + (int)((double)stat.get("Win")/stat.get("Count")*100)+"%");
	}
	}
