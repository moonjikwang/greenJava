package rockGameV3;
/**
 * 
 * @author 이유현
 *
 */
//플레이어관련 메서드2개필요 . 총플레이어수 메서드,랭킹1위 메서드

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Vector;

public class PlayerInfo {

	//폴더 내 파일을 읽어오는 메서드
//	public static void findAllFilesInFolder() {
//		File folder = new File("users");
//		for (File file : folder.listFiles()) {
//			if (!file.isDirectory()) {
//				System.out.println(file.getName());
//			} else {
//				findAllFilesInFolder();
//			}
//		}
//	}
	
public static void FileInput() {
	File file = new File("users/user1.dat");
	
	try {
		InputStream fis = new FileInputStream(file);
		
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}
}
