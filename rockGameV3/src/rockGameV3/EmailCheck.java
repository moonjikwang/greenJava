package rockGameV3;
/**
 * 
 * @author 백승연
 *
 */
// 이메일 검증 클래스
public class EmailCheck {
	private String email;

	public EmailCheck(String email) {
		validate(email);
	}
	public void validate(String email) {
		if(email.isEmpty() || email.indexOf('@') == -1) {
			System.out.println("빈 문자열이거나 @가 없습니다.");
		}
		String id = email.substring(0,email.indexOf('@'));
		String server = email.substring(email.indexOf('@'),email.length());
		if(id.length() >12 || id.length() < 8) {
			System.out.println("아이디는 8~ 12자 사이여야 합니다.");
			System.exit(0);
		}else if(id.charAt(0) < 65 || id.charAt(0) > 90) {
			System.out.println("아이디의 첫자는 영어 대문자여야 합니다.");
			System.exit(0);
		}else if(!id.matches(".*[0-9].*")) {
			System.out.println("아이디에는 숫자가 하나이상 들어가야합니다.");
			System.exit(0);
		}else {
			System.out.println("이메일 검증완료");
		}
		this.email = id + server;
	}
}
