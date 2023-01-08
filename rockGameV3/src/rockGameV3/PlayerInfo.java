package rockGameV3;
/**
 * 
 * @author 이유현
 * 23.01.06 : 파일에서 내용을 읽어와서 트리맵으로 저장, 출력까지
 * 문제:
 * 
 * 남은과제: 출력하기 전에 소팅
 */
//플레이어관련 메서드2개필요 . 총플레이어수 메서드,랭킹1위 메서드

import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class PlayerInfo {
	TreeMap<Integer, String> info = MemberDAO.getInstance().rateList();
	public PlayerInfo() {

	}

	public void totalPlayer() {
		System.out.println("총 이용자 수 : " + MemberDAO.getInstance().fileList().length + "명 입니다.");
		new OtherMenu();
	}

	public void topPlayer() {
		System.out.println( "[현재 1위 플레이어]\n" + info.get(info.lastKey()) +"님 ( 승률 : " + info.lastKey() +"% )");
		new OtherMenu();
	}

	public void ascRank() {
		
		System.out.println("3.승률꼴지부터보기\n");
		Set<Map.Entry<Integer,String>> descSet = info.entrySet();
		
		for(Map.Entry<Integer,String> e : descSet) {
			System.out.println("승률 :" + e.getKey()+ "% ID : "+ e.getValue().substring(0, e.getValue().length()-3)+"*** 님");
		}
		new OtherMenu();
	}

	public void descRank() {
		System.out.println("4.승률1위부터보기\n");
		NavigableMap<Integer,String> descMap = info.descendingMap();
		
		Set<Map.Entry<Integer,String>> descSet = descMap.entrySet();
		
		for(Map.Entry<Integer,String> e : descSet) {
			System.out.println("승률 :" + e.getKey()+ "% ID : "+ e.getValue().substring(0, e.getValue().length()-3)+"*** 님");
		}
		new OtherMenu();
		
	}

}
