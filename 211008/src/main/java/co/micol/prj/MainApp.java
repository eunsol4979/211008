package co.micol.prj;

import co.micol.prj.exe.MainMenu;

//import java.util.ArrayList;
//import java.util.List;
//
//import co.micol.prj.service.MemberService;
//import co.micol.prj.service.MemberVO;
//import co.micol.prj.dao.DataSource;
//import co.micol.prj.serviceImpl.MemberServiceImpl;

public class MainApp {
	public static void main(String[] args) {
		
		MainMenu mainMenu = new MainMenu();
		mainMenu.run();

//		DataSource dao = DataSource.getInstance(); //싱글톤 클래스의 인스턴스를 가져옴 
//		dao.getConnection(); //연결객체 호출 
//		System.out.println("hello");

//		MemberService memberService = new MemberServiceImpl(); // 인터페이스라 상속받은 클래스로 객체생성 해야함
//		List<MemberVO> members = new ArrayList<MemberVO>();
//
//		MemberVO vo = new MemberVO();

//		vo.setId("luna"); // id가 luna인 사람 조회
//		vo = memberService.selectMember(vo); // 한명분의 데이터 조회
//		vo.toString();
		
		// 생성하기 -> 오라클에서도 조회가능! 
//		vo.setId("kill"); //프라이머리키라 중복 x
//		vo.setPassword("4567");
//		vo.setName("김치볶음밥");
//		vo.setTel(null);
//		vo.setAddress(null);
//		vo.setAuthor("USER");
//		int n = memberService.insertMember(vo);
//		if(n!=0) {
//			System.out.println("잘 입력되었습니다.");
//		}else {
//			System.out.println("입력이 실패했습니다");
//		}
//	
//
//		System.out.println("-----------------------");
//		members = memberService.selectMemberList();
//
//		for (MemberVO member : members) {
//			member.toString();
//		}

	}

}
