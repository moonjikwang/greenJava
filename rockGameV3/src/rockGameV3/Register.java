package rockGameV3;
/**
 * 
 * @author 백승연
 *
 */
import java.util.Scanner;




public class Register {
	static Scanner sc = new Scanner(System.in);
	private String email;
	private String password;
	
	public Register() {
		emailCheck();
	}
	//회원가입 후 다시 Menu 호출 
	public void emailCheck() {
		boolean flag = false;
		do {
			email = null;
			if(flag) System.out.println("이미 존재하는 계정입니다.");
			System.out.println("[회원가입] 이메일을 입력해주세요.");
			System.out.print(">>");
			email = sc.next();
			Validate(email);
			flag = true;
		}while(MemberDAO.getInstance().duplicateVal(email) == -1);
		System.out.println("[회원가입] 패스워드를 입력해주세요.");
		System.out.print(">>");
		password = sc.next();
		MemberDTO member = new MemberDTO(email,password);
		int newMember = MemberDAO.getInstance().registerId(member);
		if (newMember == 1) {
			System.out.println("회원가입이 완료되었습니다⸜❤︎⸝‍ ");
			System.out.println("로그인하시겠습니까?");
			System.out.println("로그인하시려면 Y, 메뉴로 돌아가려면 N을 입력하세요.");
			while (true) {
				String ynCheck = sc.next();
				if(ynCheck.equals("Y")||ynCheck.equals("y")) {
					new Login();
				}else if (ynCheck.equals("N")||ynCheck.equals("n")) {
					new Menu();
				}
			}
			} else {
				// Yes 가 아닌 다른 경우는 일단 종료 시키는 로직으로 했으나 여러분이 다른걸로 바꿔보세요~
				System.out.println("회원가입실패. 종료합니다.");
				System.exit(0);
			}
		
		
	}
	private void Validate(String email) {
		new EmailCheck(email);
		
	}

}