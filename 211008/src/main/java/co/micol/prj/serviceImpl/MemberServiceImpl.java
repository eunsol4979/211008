package co.micol.prj.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import co.micol.prj.dao.DataSource;
import co.micol.prj.service.MemberService;
import co.micol.prj.service.MemberVO;

public class MemberServiceImpl implements MemberService {
	public DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt; // sql문 전달, 실행, 결과를 받음
	private ResultSet rs; // select 구문의 결과를 받음

	public List<MemberVO> selectMemberList() {
		List<MemberVO> list = new ArrayList<MemberVO>(); // 사용할 변수 정의
		MemberVO vo; // 여기까지
		String sql = "select * from member";
		try {
			conn = dao.getConnection(); // db랑 연결시 예외발생
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(); // sql문을 실행 후 결과를 받음
			while (rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));// 칼럼 네임은 테이블과 같아야함
				vo.setName(rs.getNString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setAuthor(rs.getString("author"));
				list.add(vo);

				// 여기서 값을 읽고 담아서 전달해줌
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list; // 받는 곳에도 list<MemberVO> 타입이 있어야 한다
	}

	public MemberVO selectMember(MemberVO vo) { // 한명 조회
		String sql = "select * from member where id = ?"; // 넘어온 인자의 값을 찾기 위해
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId()); // 전달 인자를 sql문에 넘겨줌. 물음표 1개 , id가 vo객체에서 넘어오기 때문에
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setAuthor(rs.getString("author"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo; // 받는 쪽에도 MemberVO객체가 필요함
	}

	public int insertMember(MemberVO vo) {
		String sql = "insert into member values(?,?,?,?,?,?)"; // 컬럼 개수만큼
		int n = 0;
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getPassword());
			psmt.setString(4, vo.getTel());
			psmt.setString(5, vo.getAddress());
			psmt.setString(6, vo.getAuthor());
			n = psmt.executeUpdate(); // 성공하면 1, 실패하면 0

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	public int updateMember(MemberVO vo) {
		int n = 0;
		String sql = "update member set name = ?, password = ?, tel = ?, address = ?, author = ? "
				+ "where id = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getPassword());
			psmt.setString(3, vo.getTel());
			psmt.setString(4, vo.getAddress());
			psmt.setString(5, vo.getAuthor());
			psmt.setString(6, vo.getId());
			n = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally  {
			close();
		}
		return n;
	}

	public int deleteMember(MemberVO vo) {
		int n = 0;
		String sql = "delete from member where id = ?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	private void close() { // 사용한 객체를 반영한다
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
