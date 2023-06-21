package hosMyReplyInfo;


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

public class HosMyReplyDAO {
  
	
	 private Connection con;
     private PreparedStatement pstmt;
     private DataSource dataFactory;
	
     public HosMyReplyDAO() {
    	 
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e){
			System.out.println("오라클 연결 오류");
			e.printStackTrace(); 
		}
	 }
     //페이징쓰는 리뷰 리스트 (리뷰가 8개씩 담김)
     public List selectAllRev(Map<String, Integer> pagingMap, String hos_id) {
         List<ReplyVO> replyList = new ArrayList<ReplyVO>();
         int section = pagingMap.get("section");
         int pageNum = pagingMap.get("pageNum");

         try {
             con = dataFactory.getConnection();
             String query = "SELECT * FROM (SELECT ROWNUM AS row_num, boardtbl.* FROM "
                     + " (SELECT * FROM boardtbl "
                     + "  WHERE hos_id = ? ORDER BY a_date) boardtbl)"
                     + "  WHERE row_num BETWEEN (?-1)*80+(?-1)*8+1 AND (?-1)*80+(?)*8";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, hos_id);
            pstmt.setInt(2, section);
            pstmt.setInt(3, pageNum);
            pstmt.setInt(4, section);
            pstmt.setInt(5, pageNum);
             
             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 int a_code = rs.getInt("a_code");
                 String a_title = rs.getString("a_title");
                 String a_date = rs.getString("a_date");
                 int q_code=rs.getInt("q_code");

                 //=====================================

                 ReplyVO replyVO = new ReplyVO();
                 replyVO.setA_code(a_code);
                 replyVO.setA_title(a_title);
                 replyVO.setA_date(a_date);
                 replyVO.setQ_code(q_code);
                
                 replyList.add(replyVO);
             }
             rs.close();
             pstmt.close();
             con.close();
         } catch (Exception e) {
             System.out.println("글 목록 페이징 구현중 에러 발생");
             e.printStackTrace();
         }
         return replyList;
     }
     
      
        
     
     //답변 목록 메서드 
     public List<ReplyVO> selectAllReply(String hos_id){
    	 List<ReplyVO> hosMyReplyList= new ArrayList();
    	 
    	 try {
 			con=dataFactory.getConnection();
 			String query="select * from boardtbl where hos_id=? order by a_date";
 			pstmt=con.prepareStatement(query);
 			pstmt.setString(1, hos_id);
 			ResultSet rs=pstmt.executeQuery();
 			while(rs.next()) {
 				int a_code=rs.getInt("a_code"); 
 				String a_title=rs.getString("a_title");
 				String a_date=rs.getString("a_date");
 				String q_code=rs.getString("q_code");
 					
 				ReplyVO replyVO=new ReplyVO(a_code, a_title, a_date); 		
 			    hosMyReplyList.add(replyVO); 
 			}
 			rs.close();
 			pstmt.close();
 			con.close();
 			
 		   }catch (Exception e) {
 			System.out.println("DB조회 중 에러!");
 			e.printStackTrace();
 		 }
     	 return hosMyReplyList;
    	 
    	 
     }
     
     
     //답변 개수 불러오기 메소드
     public int replyCount(String hos_id) {
    	 int reply_count=0;
    	 try {
			con=dataFactory.getConnection();
			String query="select count(*)as count from boardtbl where hos_id=?";
			pstmt=con.prepareStatement(query);
 			pstmt.setString(1, hos_id);
 			ResultSet rs=pstmt.executeQuery();
 			rs.next();
 			reply_count=rs.getInt("count");
 			rs.close();
 			pstmt.close();
 			con.close();
		} catch (Exception e) {
			System.out.println("답변 개수 처리중 오류!");
			e.printStackTrace();
		}
    	 return reply_count;
     }
     
     
     
              
    
    //답변 글 상세보기 메소드
    public String selectReply(String a_code){
    	 String q_code=null;
    	 try {
			con=dataFactory.getConnection();
		  String query="select q_code from  boardtbl where a_code=?";
		  pstmt=con.prepareStatement(query);
		  pstmt.setString(1, a_code);
		  ResultSet rs=pstmt.executeQuery();
		  rs.next();
		  q_code=rs.getString("q_code");
		
		  		  		 		  
		  rs.close();
		  pstmt.close();
		  con.close();
    	 } catch (Exception e) {
			System.out.println("답변 상세보기 중 에러!");
			e.printStackTrace();
		}
    	 return q_code;
     }
     
    
   //답변삭제 메소드
     public void deleteReply(String[] items) {
    	 
    	 String[]a_code=items;
    	 try {
    		 if(a_code==null || a_code.length==0) {
    			 System.out.println("삭제 요청할 리뷰를 선택 안 함");
    		 }else {
    			 
    		 
    		 for(int i=0; i<a_code.length; i++) {
    			con=dataFactory.getConnection();
    			String query="update boardtbl set hos_name=null, a_code=null, a_title=null, a_text=null, a_date=null, hos_id=null where a_code=?";
    			pstmt=con.prepareStatement(query);
    	 		pstmt.setString(1, a_code[i]);
    	 		pstmt.executeUpdate();
    			pstmt.close();
    			con.close();
    		 }
    		 }
 			//review_count=rs.getInt("count");
		} catch (Exception e) {
			System.out.println("HosMyReplyDAO : 답변 삭제 처리중 에러!");
			e.printStackTrace();
		}
     }
     
//================================================================================
      
     
     
     
}
