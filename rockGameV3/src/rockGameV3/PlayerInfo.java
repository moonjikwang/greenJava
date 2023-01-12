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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class PlayerInfo {
	
	//---------------------필드선언 및 초기화 --------------------
	TreeMap<String, Integer> info = null;
	List<Entry<String,Integer>> listRate;
	File[] total = null;
	
	int rank = 0;
	//---------------------필드선언 및 초기화 끝 --------------------
	
	
	//---------------------생성자 정의 -----------------------
	public PlayerInfo() {
		
		info = MemberDAO.getInstance().rateList();
		total = MemberDAO.getInstance().fileList();
		listRate = new ArrayList<Entry<String,Integer>>(info.entrySet());
	
	}
	//---------------------생성자 정의 끝 -----------------------

	
	//--------------------- 총 이용자 수 출력메서드 -----------------------
	public void totalPlayer() {
		System.out.println("총 이용자 수 : " + total.length + "명 입니다.");
		new OtherMenu();
	}
	
	//--------------------- 현재 1위 플레이어 출력메서드 -----------------------
	public void topPlayer() {
		Collections.sort(listRate, new Comparator<Entry<String,Integer>>() {
		public int compare(Entry<String, Integer>obj1,Entry<String,Integer>obj2) {
			return obj2.getValue().compareTo(obj1.getValue());
		}
		});
		System.out.println( "[현재 1위 플레이어]\n" + listRate.get(0).getKey().substring(0,listRate.get(0).getKey().length()-3)+"***님 ( 승률 : " + listRate.get(0).getValue() +"% )");
		new OtherMenu();
	}
	
	//--------------------- 승률꼴지부터보기 출력메서드 -----------------------
	public void ascRank() {
		System.out.println("<<승률꼴지부터보기>>");
		Collections.sort(listRate, new Comparator<Entry<String,Integer>>() {
		public int compare(Entry<String, Integer>obj1,Entry<String,Integer>obj2) {
			return obj1.getValue().compareTo(obj2.getValue());
		}
		});
		for (Entry<String, Integer> pair : listRate) {
			rank++;
			System.out.println(String.format(rank + "위... %s***님, 승률: %s", pair.getKey().substring(0, pair.getKey().length()-3), pair.getValue())+"%");
		}
		new OtherMenu();
	}

	//--------------------- 승률1위부터보기 출력메서드 -----------------------
	public void descRank() {
		System.out.println("<<승률1위부터보기>>");
		Collections.sort(listRate, new Comparator<Entry<String,Integer>>() {
		public int compare(Entry<String, Integer>obj1,Entry<String,Integer>obj2) {
			return obj2.getValue().compareTo(obj1.getValue());
		}
		});
		for (Entry<String, Integer> pair : listRate) {
			rank++;
			System.out.println(String.format(rank + "위... %s***님, 승률: %s", pair.getKey().substring(0, pair.getKey().length()-3), pair.getValue())+"%");
		}
		new OtherMenu();
	}
	
	//---------------------메서드 정의 끝 -----------------------
	
}