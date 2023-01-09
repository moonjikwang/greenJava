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

import java.io.File;
import java.util.Map.Entry;
import java.util.TreeMap;

public class PlayerInfo {
	
	//---------------------필드선언 및 초기화 --------------------
	TreeMap<Integer, String> info = null;
	File[] total = null;
	
	int rank = 0;
	//---------------------필드선언 및 초기화 끝 --------------------
	
	
	//---------------------생성자 정의 -----------------------
	public PlayerInfo() {
		
		info = MemberDAO.getInstance().rateList();
		total = MemberDAO.getInstance().fileList();
	
	}
	//---------------------생성자 정의 끝 -----------------------

	
	//--------------------- 총 이용자 수 출력메서드 -----------------------
	public void totalPlayer() {
		System.out.println("총 이용자 수 : " + total.length + "명 입니다.");
		new OtherMenu();
	}
	
	//--------------------- 현재 1위 플레이어 출력메서드 -----------------------
	public void topPlayer() {
		System.out.println( "현재 1위 플레이어 " + info.get(info.lastKey()).substring(0, info.get(info.lastKey()).length()-3) +"***님 ( 승률 : " + info.lastKey() +" )");
		new OtherMenu();
	}
	
	//--------------------- 승률꼴지부터보기 출력메서드 -----------------------
	public void ascRank() {
		System.out.println("<<승률꼴지부터보기>>");

		for (Entry<Integer, String> pair : info.entrySet()) {
			rank++;
			System.out.println(String.format(rank + "위... %s***님, 승률: %s", pair.getValue().substring(0, pair.getValue().length()-3), pair.getKey()));
		}
		new OtherMenu();
	}

	//--------------------- 승률1위부터보기 출력메서드 -----------------------
	public void descRank() {
		System.out.println("<<승률1위부터보기>>");

		for (Entry<Integer, String> pair : info.descendingMap().entrySet()) {
			rank++;
			System.out.println(String.format(rank + "위... %s***님, 승률: %s" , pair.getValue().substring(0, pair.getValue().length()-3), pair.getKey()));
		}
		new OtherMenu();
	}
	
	//---------------------메서드 정의 끝 -----------------------
	
}