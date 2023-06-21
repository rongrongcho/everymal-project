package admin;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public AdminDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("커넥션풀 연결실패");
			e.printStackTrace();
		}
	}

	// 병원 등록 신청 페이지 목록 조회
	
	

	public List<AdminVO> selectRMList(AdminVO adVO) {
		List<AdminVO> rmList = new ArrayList<AdminVO>();
		
		try {
			con = dataFactory.getConnection();
			String query = "select * from hosrmtbl";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				String rm_code=rs.getString("rm_code");
				String hos_name=rs.getString("hos_name");
				Date rm_date=rs.getDate("rm_date");
				String rm_status=rs.getString("rm_status");
				String hos_id=rs.getString("hos_id");
				String hos_code=rs.getString("hos_code");
				
				AdminVO adminVO= new AdminVO();
				adminVO.setHos_id(hos_id);
				adminVO.setHos_name(hos_name);
				adminVO.setRm_code(rm_code);
				adminVO.setRm_status(rm_status);
				adminVO.setRm_date(rm_date);
				adminVO.setHos_code(hos_code);
				rmList.add(adminVO);
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("AdminDAO : 병원 등록 신청 목록 조회중 에러 발생");
			e.printStackTrace();
		}

		return rmList;
	}
	
	
	
	public List selectRMList(Map<String, Integer> pagingMap) {
		List<AdminVO> rmList = new ArrayList<AdminVO>();
	    int section = pagingMap.get("section");
	    int pageNum = pagingMap.get("pageNum");

	    try {
	        con = dataFactory.getConnection();
	        
	        String query = "SELECT * FROM (SELECT t.*, ROWNUM rnum FROM (SELECT * FROM hosrmtbl) t WHERE ROWNUM <= (?-1)*80+(?)*8) WHERE rnum >= (?-1)*80+(?-1)*8+1";
		    pstmt = con.prepareStatement(query);
		    pstmt.setInt(1, section);
		    pstmt.setInt(2, pageNum);
		    pstmt.setInt(3, section);
		    pstmt.setInt(4, pageNum);
	        
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()) {
				String rm_code=rs.getString("rm_code");
				String hos_name=rs.getString("hos_name");
				Date rm_date=rs.getDate("rm_date");
				String rm_status=rs.getString("rm_status");
				String hos_id=rs.getString("hos_id");
				String hos_code=rs.getString("hos_code");
				
				AdminVO adminVO= new AdminVO();
				adminVO.setHos_id(hos_id);
				adminVO.setHos_name(hos_name);
				adminVO.setRm_code(rm_code);
				adminVO.setRm_status(rm_status);
				adminVO.setRm_date(rm_date);
				adminVO.setHos_code(hos_code);
				rmList.add(adminVO);
			}
	        rs.close();
	        pstmt.close();
	        con.close();
	    }  catch (Exception e) {
			System.out.println("AdminDAO : 병원 등록 신청 목록 조회중 에러 발생");
			e.printStackTrace();
		}
	    return rmList;
	}
	
	 public int appCount() {
	        int app_count=0;
	        try {
	         con=dataFactory.getConnection();
	         String query="select count(*)as count from hosrmtbl";
	         pstmt=con.prepareStatement(query);
	          ResultSet rs=pstmt.executeQuery();
	          rs.next();
	          app_count=rs.getInt("count");
	          rs.close();
	          pstmt.close();
	          con.close();
	      } catch (Exception e) {
	         System.out.println("등록 신청 전체 수 세기 중 오류");
	         e.printStackTrace();
	      }
	        return app_count;
	     }
	
	
	
	
	
	//선택 병원 등록 승인 메서드 
	
	
    public void appManyHos(String[] chk) { 
   	 String[] chkArray=chk;
   	 try {	 
   		 if(chkArray==null || chkArray.length==0) {
   			 System.out.println("등록할 병원을 선택하지 않았습니다.");
   		 }
   		 else {
   			con = dataFactory.getConnection();
   			String query = "UPDATE hosjointbl SET hos_status = '승인' WHERE hos_code IN (";

   			for (int i = 0; i < chkArray.length; i++) {
   			    query += "?";
   			    if (i != chkArray.length - 1) {
   			        query += ",";
   			    }
   			}

   			query += ")";
   			pstmt = con.prepareStatement(query);

   			for (int i = 0; i < chkArray.length; i++) {
   			    pstmt.setString(i + 1, chkArray[i]);
   			}

   			pstmt.executeUpdate();
   			pstmt.close();

   			query = "UPDATE hosrmtbl SET rm_status = '완료' WHERE hos_code IN (";

   			for (int i = 0; i < chkArray.length; i++) {
   			    query += "?";
   			    if (i != chkArray.length - 1) {
   			        query += ",";
   			    }
   			}

   			query += ")";
   			pstmt = con.prepareStatement(query);

   			for (int i = 0; i < chkArray.length; i++) {
   			    pstmt.setString(i + 1, chkArray[i]);
   			}

   			pstmt.executeUpdate();
   			pstmt.close();
   			con.close();
   		 }

		} catch (Exception e) {
			System.out.println("여러개의 병원 등록 승인 중 오류! ");
			e.printStackTrace();
		}
		
	}
    
    
    //병원 등록 거절 
    
//    
//    public void rejectionHos(String rjWhy,String hos_code) {
//    	try {
//    		con = dataFactory.getConnection();
//   			String query = "UPDATE hosjointbl SET hos_status = '거절' WHERE hos_code=?";
//   			pstmt = con.prepareStatement(query);
//	        pstmt.setString(1, hos_code);
//   			pstmt.executeUpdate();
//   			pstmt.close();
//   			query = "UPDATE hosrmtbl SET rm_status = '완료',rm_say=? WHERE hos_code=?";
//   			pstmt = con.prepareStatement(query);
//	        pstmt.setString(1, rjWhy);
//	        pstmt.setString(2, hos_code);
//   			pstmt.executeUpdate();
//   			pstmt.close();			
//		} catch (Exception e) {
//			System.out.println("병원 등록 거절 업데이트도중 오류! ");
//			e.printStackTrace();
//			
//		}
//    }
    
}
