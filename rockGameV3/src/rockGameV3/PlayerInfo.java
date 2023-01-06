package rockGameV3;
/**
 * 
 * @author 이유현
 * 23.01.06 : 파일에서 내용을 읽어와서 트리맵으로 저장, 출력까지
 * 문제:
 * 파일 상대경로로 가져와야 함 -> 더 알아보기
 * 유저네임, 전적 계산까지 파일에 있어야함 ->요청하기
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
import java.io.SequenceInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class PlayerInfo {


public PlayerInfo() {
	File file = new File("D:\\YH\\git\\greenJava\\rockGameV3\\src\\rockGameV3\\users\\"); //상대경로 지정 방법 확인
	File[] filelist = file.listFiles();

	String readData = null;
	Map<String, String> infoMap = new TreeMap<String, String>();
	Map<String, String> info = new TreeMap<String, String>();
	
	
	try {
		
		for (File f : filelist) {
			InputStream fis = new FileInputStream(f);
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			while( (readData =  br.readLine() )!= null) {
				String[] stat = readData.split(":");
//				System.out.println(stat[1].toString());
				
				infoMap.put(stat[0], stat[1]);
				
			}
			
			info.put(f.getName(),infoMap.get("Win") );
			
			br.close();
		}
		
		
		//승률이 없으니 대신 승수로 로직 짜기
		//유저 네임과 승수를 가져와서 매핑하고 승수를 기준으로 정렬
		
		for (Map.Entry<String, String> pair : info.entrySet()) {
			 System.out.println(String.format("파일명: %s, 승수: %s", pair.getKey(), pair.getValue()));
		}
//		System.out.println(info.keySet().toString() +":"+ info.values().toString());
		
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
	
	
}
