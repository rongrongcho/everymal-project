package hosApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import userMypage.UserVO;

public class HosAppDAO {
	//=========================================ConnectionPool
	private Connection con; 
	private PreparedStatement pstmt; 
	private DataSource dataFactory ;
	HttpSession session;
	
	//	생성자 필요 
	public HosAppDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource) envContext.lookup("jdbc/oracle");
					
		}catch(Exception e) {
			System.out.println("커넥션풀 연결실패"); // xml 확인
			e.printStackTrace();
		}
		
		
	}
	//회원 정보 불러오기
		public HosAppVO selectAppInfo(String _hos_code) {
			HosAppVO appInfo=new HosAppVO();
		      try {
		         con=dataFactory.getConnection(); 
		         String query="SELECT * FROM hosjoinTbl JOIN hosimgtbl ON hosjoinTbl.hos_code = hosimgtbl.hos_code"
		         		+ " JOIN hosrmtbl ON hosjoinTbl.hos_code = hosrmtbl.hos_code WHERE hosjoinTbl.hos_code=?";
		         pstmt=con.prepareStatement(query);
		         pstmt.setString(1, _hos_code);
		         ResultSet rs=pstmt.executeQuery();
		         if(rs.next()) {
		        	 
		        	 appInfo.setHos_code(rs.getString("hos_code"));
		        	 appInfo.setHos_id(rs.getString("hos_id"));
		        	 appInfo.setHos_name(rs.getString("hos_name"));
		        	 appInfo.setHos_Thum(rs.getString("hos_thum"));
		        	 appInfo.setHos_status(rs.getString("hos_status"));
		        	 appInfo.setRm_say(rs.getString("rm_say"));
		        	 appInfo.setHimg1(rs.getString("himg1"));
		        	 appInfo.setHimg1(rs.getString("himg2"));
		        	 appInfo.setHimg1(rs.getString("himg3"));
		 			 	       
		         }

		         rs.close();
		         pstmt.close(); 
		         con.close();
		      } catch (Exception e) {
		         System.out.println("HosAppDAO : selectAppInfo 메서드 처리 중 에러 ");
		         e.printStackTrace();
		      }      
		      return appInfo;
		      
		   }
		
		
		
		
	
//==============================================================
		//관리자의 열람 여부 확인 
		 public void checkHos(String hos_code) {
		    	try {
		    		con = dataFactory.getConnection();
		   			String query = "UPDATE hosjointbl SET hos_status = '검토중' WHERE hos_code=?";
		   			pstmt = con.prepareStatement(query);
			        pstmt.setString(1, hos_code);
		   			pstmt.executeUpdate();
		   			pstmt.close();
		   			query = "UPDATE hosrmtbl SET rm_status = '확인' WHERE hos_code=?";
		   			pstmt = con.prepareStatement(query);
			        pstmt.setString(1, hos_code);
		   			pstmt.executeUpdate();
		   			pstmt.close();			
				} catch (Exception e) {
					System.out.println("병원 열람여부 업데이트도중 오류  ");
					e.printStackTrace();
					
				}
		    }
		
	    public void rejectionHos(String rjWhy,String hos_code) {
	    	try {
	    		con = dataFactory.getConnection();
	   			String query = "UPDATE hosjointbl SET hos_status = '거절' WHERE hos_code=?";
	   			pstmt = con.prepareStatement(query);
		        pstmt.setString(1, hos_code);
	   			pstmt.executeUpdate();
	   			pstmt.close();
	   			query = "UPDATE hosrmtbl SET rm_status = '완료',rm_say=? WHERE hos_code=?";
	   			pstmt = con.prepareStatement(query);
		        pstmt.setString(1, rjWhy);
		        pstmt.setString(2, hos_code);
	   			pstmt.executeUpdate();
	   			pstmt.close();			
			} catch (Exception e) {
				System.out.println("병원 등록 거절 업데이트도중 오류! ");
				e.printStackTrace();
				
			}
	    }

}
