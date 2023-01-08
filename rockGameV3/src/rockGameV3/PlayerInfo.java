package rockGameV3;

import java.io.File;
import java.util.TreeMap;
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
		TreeMap<String, TreeMap<String, Integer>> playersStats = MemberDAO.getInstance().sortPlayers();
		System.out.println(playersStats.get("Jikwang123").get("Count"));
		//트리맵안에트리맵.... 
	}
	public void totalPlayers() {
		File[] fileList = MemberDAO.getInstance().fileList();
		int count = fileList.length;
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
