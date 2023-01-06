package rockGameV3;

import java.util.Map.Entry;
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
public class ShowStats {
	public ShowStats() {
		System.out.println("[전적조회]");
		MemberDAO.getInstance().loading();
		TreeMap<String, Integer> stats = MemberDAO.getInstance().myStats(Login.member);
		System.out.println("총전적 : " + stats.get("Count"));
		System.out.println("승 : " + stats.get("Win"));
		System.out.println("패 : " + stats.get("Lose"));
		System.out.println("승률 : " + ((double)stats.get("Win") / stats.get("Count") * 100) + "%");
	}

}
