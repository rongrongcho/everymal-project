package withdrawal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class WithdrawalDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private PreparedStatement pstmt3;
	private PreparedStatement pstmt4;
	private DataSource dataFactory;
	HttpSession session;
	
	public WithdrawalDAO(){
		try {
			Context ctx=new InitialContext();
	        Context envContext=(Context)ctx.lookup("java:/comp/env");
	        dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("커넥션풀 연결실패");
			e.printStackTrace();
		}
	}
	
	public void updateEnabled(String id) {
		try {
			conn=dataFactory.getConnection();
			System.out.println("디에이오 아이디 받냐???????????"+id);
			if(id!=null) {
				String gmemberQuery="select * from gmembertbl where user_id=?";
				pstmt=conn.prepareStatement(gmemberQuery);
				pstmt.setString(1, id);
				ResultSet rs=pstmt.executeQuery();
				
				if(rs.next()) {
					String updateQuery="update gmembertbl set user_enabled=1 where user_id=?";
					pstmt3=conn.prepareStatement(updateQuery);
					pstmt3.setString(1, id);
					pstmt3.executeUpdate();
					pstmt3.close();
				}else {
					String hosjoinQuery="select * from hosjointbl where hos_id=?";
					pstmt2=conn.prepareStatement(hosjoinQuery);
					pstmt2.setString(1, id);
					ResultSet rs2=pstmt2.executeQuery();
					if(rs2.next()) {
						String updateQuery="update hosjointbl set hos_enabled=1 where hos_id=?";
						pstmt4=conn.prepareStatement(updateQuery);
						pstmt4.setString(1, id);
						pstmt4.executeUpdate();
						pstmt4.close();
					}
					pstmt2.close();
					rs2.close();
				}
				pstmt.close();
				rs.close();
			}
			
		}catch (Exception e) {
			System.out.println("update중 오류!!!");
			e.printStackTrace();
		} finally {
	        // 자원 해제
	        try {
	            if (pstmt3 != null) pstmt3.close();  // pstmt3 자원 해제
	            if (pstmt4 != null) pstmt4.close();  // pstmt4 자원 해제
	            if (pstmt2 != null) pstmt2.close();  // pstmt2 자원 해제
	            if (pstmt != null) pstmt.close();    // pstmt 자원 해제
	            if (conn != null) conn.close();       // conn 자원 해제
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
}
