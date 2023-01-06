package rockGameV3;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author 이유현
 *
 */
public class PlayerInfo {
// 플레이어관련 메서드2개필요 . 총플레이어수 메서드,랭킹1위 메서드
	public PlayerInfo() {
	}
	public void topPlayer() {
		
	}
	public void totalPlayers() {
		int count = MemberDAO.getInstance().countPlayers();
		System.out.println("총 이용자수는 " + count + "명 입니다.");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		System.out.println("");
		System.out.println("");
		new OtherMenu();
	}
}
