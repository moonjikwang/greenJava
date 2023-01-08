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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.SequenceInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class PlayerInfo {
//	File file = new File("D:\\YH\\git\\greenJava\\rockGameV3\\src\\rockGameV3\\users\\"); //상대경로 지정 방법 확인

	File file = new File("Members/");
	File[] filelist = file.listFiles();

	String readData = null;
	TreeMap<String, String> infoMap = null;
	TreeMap<String, String> info = null;
	
	TreeMap<String, String> desInfo = null;

	
	int rank = 0;

	
	
	public PlayerInfo() {
		infoMap = new TreeMap<String, String>();
		info = new TreeMap<String, String>();

		try {

			for (File f : filelist) {
				InputStream fis = new FileInputStream(f);
				BufferedReader br = new BufferedReader(new FileReader(f));

				while ((readData = br.readLine()) != null) {
					String[] stat = readData.split(":");
//				System.out.println(stat[1].toString());

					infoMap.put(stat[0], stat[1]);

				}

				info.put( infoMap.get("Win"), f.getName());
				

				br.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void totalPlayer() {
		// 승률이 없으니 대신 승수로 로직 짜기
		// 유저 네임과 승수를 가져와서 매핑하고 승수를 기준으로 정렬

		
	}

	public void topPlayer() {
		System.out.println( "현재 1위 플레이어 " + info.get(info.lastKey()) +"님 ( 승률 : " + info.lastKey() +" )");
	}

	public void ascRank() {
		System.out.println("3.승률꼴지부터보기");

		for (Map.Entry<String, String> pair : info.entrySet()) {
			rank++;
			System.out.println(String.format(rank + "위... %s님, 승률: %s", pair.getValue(), pair.getKey()));
			
		}
		
	}

	public void descRank() {
		System.out.println("4.승률1위부터보기");

		for (Map.Entry<String, String> pair : info.descendingMap().entrySet()) {
			rank++;
			System.out.println(String.format(rank + "위... %s님, 승률: %s" , pair.getValue(), pair.getKey()));
			
		}
	}

}
