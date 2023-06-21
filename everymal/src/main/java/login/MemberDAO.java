package login;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// db연결부터 select insert등 모든 접근을 제어하는 클래스
public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt; //Statement는 쿼리를 할떄마다 컬파일 하기 떄문에 느리다 따라서 pre를 쓴다, 인터페이스이다
	private DataSource dataFactory;//연동된 데이터를 빠르게 가져오는 변수
	
	//생성자
	public MemberDAO() {
		try {
			//JNDI에 접근하기 위해 기본 경로(java:/comp/env)를 지정
			Context ctx=new InitialContext();
			Context envContext=(Context) ctx.lookup("java:/comp/env");
			//톰켓 context.xml에 설정한 name값인 jdbc/oracle을 이용해 톰켓에 미리 연결한 DataSource를 받아 옵니다
			dataFactory=(DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("커넥션풀 연결 실패");	
			e.printStackTrace();  //
		}
	}
	
	//메서드
	
	//회원확인 메서드
	public boolean isExisted(MemberVO memVO) {
		boolean result = false;
		String id = memVO.getId();
		String pwd = memVO.getPwd();
		boolean hos_chk = memVO.getHos_chk();
		try {
			con=dataFactory.getConnection();//DataSource를 이용해 데이터베이스를 연결
			String query="";
			if(hos_chk) {
				query="select decode(count(*), 1, 'true', 'false') as result from hosjointbl where hos_id=? and hos_pwd=? and hos_enabled=0";
			}else {
				query="select decode(count(*), 1, 'true', 'false') as result from gmembertbl where user_id=? and user_pwd=? and user_enabled=0";
			}
			pstmt=con.prepareStatement(query);		//connDB단계에서 실행하지 않고 여기서 실행
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			pstmt.close();
		} catch (Exception e) {
			System.out.println("DB 연결오류=>"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	//회원정보 가져오기
	public MemberVO memberInfo(MemberVO memVO) {
		MemberVO memInfo=new MemberVO();
		String id = memVO.getId();
		boolean hos_chk = memVO.getHos_chk();
		try {
			con=dataFactory.getConnection();//DataSource를 이용해 데이터베이스를 연결
			String query="";
			if(hos_chk) {
				query="select * from hosjointbl where hos_id=? and hos_enabled=0";
			}else {
				query="select * from gmembertbl where user_id=? and user_enabled=0";
			}
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			if(hos_chk) {
				memInfo.setCode(rs.getString("hos_code"));
				memInfo.setHos_name(rs.getString("hos_name"));
			}else {
				memInfo.setCode(rs.getString("user_code"));
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("DB 연결오류=>"+e.getMessage());
			e.printStackTrace();
		}
		return memInfo;
	}
	
	//아이디 찾기
	public String searchId(MemberVO memVO) {
		String name = memVO.getName();
		String email = memVO.getEmail();
		boolean hos_chk = memVO.getHos_chk();
		String hos_dno=memVO.getHos_dno();
		String id=null;
		try {
			con=dataFactory.getConnection();//DataSource를 이용해 데이터베이스를 연결
			String query="";
			if(hos_chk) {
				query="select hos_id from hosjointbl where hos_username=? and hos_dno=?";
			}else {
				query="select user_id from gmembertbl where user_name=? and user_email=?";
			}
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, name);
			if(hos_chk) {
				pstmt.setString(2, hos_dno);
			}else {
				pstmt.setString(2, email);
			}
			
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				if(hos_chk) {
					id=rs.getString("hos_id");
				}else {
					id=rs.getString("user_id");
				}
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("DB 연결오류=>"+e.getMessage());
			e.printStackTrace();
		}
		return id;
	}
	
	//비밀번호 찾기
	public boolean searchPass(MemberVO memVO) {
		boolean result=false;
		String name = memVO.getName();
		String id = memVO.getId();
		String email = memVO.getEmail();
		boolean hos_chk = memVO.getHos_chk();
		String hos_dno=memVO.getHos_dno();
		try {
			con=dataFactory.getConnection();//DataSource를 이용해 데이터베이스를 연결
			String query="";
			if(hos_chk) {
				query="select decode(count(*), 1, 'true', 'false') as result from hosjointbl where hos_username=? and hos_id=? and hos_dno=? and hos_enabled=0";
			}else {
				query="select decode(count(*), 1, 'true', 'false') as result from gmembertbl where user_name=? and user_id=? and user_email=? and user_enabled=0";
			}
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			if(hos_chk) {
				pstmt.setString(3, hos_dno);
			}else {
				pstmt.setString(3, email);
			}
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("DB 연결오류=>"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
