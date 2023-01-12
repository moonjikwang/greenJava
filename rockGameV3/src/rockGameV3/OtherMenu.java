package rockGameV3;

import java.util.Scanner;

/**
 * 
 * @author 이유현
 *
 */
/*
 * Menu클래스에서 기타메뉴보기를 클릭하여 호출된 클래스입니다. 아래 메뉴를 출력하고 입력값을 받아 해당 메서드를 호출합니다. 1.총
 * 사용자수 2.1위플레이어 3.승률꼴지부터보기 4.승률1위부터보기 id의 끝 3자리는 ***로 표시해야합니다.(승률도 표시)
 */
public class OtherMenu {
	static Scanner sc = new Scanner(System.in);
	PlayerInfo info = new PlayerInfo();

	public OtherMenu() {

		System.out.println("┌────────────────────────────────────────────────────┐");
		System.out.println("│<<기타메뉴>>　　　　　　　　　　　　　　　　　　　　　　　　　　│");
		System.out.println("│1.총 사용자수 2.1위플레이어 3.승률꼴지부터보기 4.승률1위부터보기 5.게임종료  │");
		System.out.println("└────────────────────────────────────────────────────┘");
		System.out.print(">");
		int select = sc.nextInt();
		switch (select) {
		case 1:
			info.totalPlayer();
			break;
		case 2:
			info.topPlayer();
			break;
		case 3:
			info.ascRank();
			break;
		case 4:
			info.descRank();
			break;
		case 5:
			System.out.println("안녕히 가세요~ (*ˊᵕˋ*)ﾉ");
			System.exit(0);
			break;
			
		default:
			break;
		}
	}
}
