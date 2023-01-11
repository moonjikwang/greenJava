package rockGameV3;


/**
 * 
 * @author 박수현
 *
 */
/*
 * 이 클래스는 로그인성공 -> 게임메뉴에서 3.마지막로그인날짜 확인하기 클릭하여 호출된클래스입니다.
 * 해당 사용자의 마지막 로그인날짜를 출력하고 다시 게임메뉴를 호출합니다.
 */
public class LastLogin {
	
	public LastLogin() {
	String Last = MemberDAO.getInstance().dataSearch(Login.member, "LastLogOut");
	System.out.println(Last);
	}
}
