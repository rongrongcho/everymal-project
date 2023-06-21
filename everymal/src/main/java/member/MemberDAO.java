package member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
			
		} catch (Exception e) {
			System.out.println("오라클 연결 오류");
			e.printStackTrace();
		}
	}
	public List<MemberVO> listMembers(){
		List<MemberVO> memberList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from gmembertbl order by j_Date desc";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String user_id = rs.getString("user_id");
				String user_pwd = rs.getString("user_pwd");
				String user_name = rs.getString("user_name");
				String user_tel = rs.getString("user_tel");
				String user_email = rs.getString("user_email");
				String user_addr1 = rs.getString("user_addr1");
				String user_addr2 = rs.getString("user_addr2");
				Date j_date = rs.getDate("j_date");
				MemberVO memberVO = new MemberVO(user_id, user_pwd, user_name, user_tel, user_email, user_addr1, user_addr2, j_date);
				memberList.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DB 조회 중 에러!");
			e.printStackTrace();
		}
		return memberList;
	}
//	회원 등록
	public void addMember(MemberVO memberVO) {
		String code = getUserCode();
		try {
			conn = dataFactory.getConnection();
			String user_id = memberVO.getUser_id();
			String user_pwd = memberVO.getUser_pwd();
			String user_name = memberVO.getUser_name();
			String user_tel = memberVO.getUser_tel();
			String user_email = memberVO.getUser_email();
			String user_addr1 = memberVO.getUser_addr1();
			String user_addr2= memberVO.getUser_addr2();
			String user_zipcode = memberVO.getUser_zipcode();
			String query = "insert into gmembertbl (USER_CODE, USER_ID, USER_PWD, USER_NAME, USER_TEL, USER_EMAIL, USER_ADDR1,USER_ADDR2,USER_ZIPCODE) values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, code);
			pstmt.setString(2, user_id);
			pstmt.setString(3, user_pwd);
			pstmt.setString(4, user_name);
			pstmt.setString(5, user_tel);
			pstmt.setString(6, user_email);
			pstmt.setString(7, user_addr1);
			pstmt.setString(8, user_addr2);
			pstmt.setString(9, user_zipcode);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DB 등록 중 에러!!");
			e.printStackTrace();
		}
	}
	
	public String selectUserCode(MemberVO memberVO){
		String USER_CODE = "";
		try {
			conn = dataFactory.getConnection();
			String user_id = memberVO.getUser_id();
			String query = "select USER_CODE from gmembertbl where USER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
				USER_CODE = rs.getString("USER_CODE");
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DB 조회 중 에러!");
			e.printStackTrace();
		}
		return USER_CODE;
	}
	
	public String getUserCode() {
	       String code="";
	       try {
	           conn=dataFactory.getConnection();
	           String query="SELECT MAX(SUBSTR(user_code, 4)) AS max_num FROM gmembertbl";
	           pstmt=conn.prepareStatement(query);
	           ResultSet rs=pstmt.executeQuery();
	           if(rs.next()) {
	               int maxNum = rs.getInt("max_num") + 1;
	               code = String.format("use%04d", maxNum);
	           }
	           System.out.println(code);
	           rs.close();
	           pstmt.close();
	           conn.close();
	       } catch (Exception e) {
	           System.out.println("코드 생성중 에러");
	           e.printStackTrace();
	       }

	       return code;
	   }
//	ID중복 체크(DB활용)
	public boolean overlappedID(String user_id) {
		boolean result=false;
		//중복체크
		try {
			conn=dataFactory.getConnection();
			String query="select decode(count(*),1,'true','false') as result from gmembertbl where user_id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result=Boolean.parseBoolean(rs.getString("result"));
			pstmt.close();
		} catch (Exception e) {
			System.out.println("DB에서 ID중복 체크 중 에러");
		}
		return result;
	}
}
