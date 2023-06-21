package hosReviewInfo;


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

public class HosReviewDAO {
  
	
	 private Connection con;
     private PreparedStatement pstmt;
     private DataSource dataFactory;
	
     public HosReviewDAO() {
    	 
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
			//톰캣 context.xml에 설정한 name값인 jdbc/oracle을 이용해 톰켓에 미리 연결한 DataSource를 받아온다.
		}catch(Exception e){
			System.out.println("오라클 연결 오류");
			e.printStackTrace(); //내가 정한 오류메시지 말고 실제 에러를 출력해줌
		}
	 }
     
     //병원 코드 가져오기 메서드
     public String findhos_code(String hos_id) {
    	 String hos_code=null;
    	 try {
			con=dataFactory.getConnection();
			String query="select * from hosjointbl where hos_id=?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, hos_id);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			hos_code=rs.getString("hos_code");
			rs.close();
 			pstmt.close();
 			con.close();
		} catch (Exception e) {
			System.out.println("병원 코드 가져오기 중 에러!");
			e.printStackTrace();
		}
    	 return hos_code;
     }
     
    
     
     
     //페이징쓰는 리뷰 리스트 (리뷰가 8개씩 담김)
     public List selectAllRev(Map<String, Integer> pagingMap, String hos_code) {
         List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
         int section = pagingMap.get("section");
         int pageNum = pagingMap.get("pageNum");

         try {
             con = dataFactory.getConnection();
             String query = "SELECT * FROM (SELECT ROWNUM AS row_num, reviewtbl.* FROM "
                     + " (SELECT * FROM reviewtbl "
                     + "  WHERE hos_code = ? ORDER BY rv_code) reviewtbl)"
                     + "  WHERE row_num BETWEEN (?-1)*80+(?-1)*8+1 AND (?-1)*80+(?)*8";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, hos_code);
            pstmt.setInt(2, section);
            pstmt.setInt(3, pageNum);
            pstmt.setInt(4, section);
            pstmt.setInt(5, pageNum);
             
             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 String rv_code = rs.getString("rv_code");
                 String rv_title = rs.getString("rv_title");
                 String rv_text = rs.getString("rv_text");
                 int rv_rate = rs.getInt("rv_rate");
                 Date rv_date = rs.getDate("rv_date");

                 //=====================================

                 ReviewVO reviewVO = new ReviewVO();
                 reviewVO.setRv_code(rv_code);
                 reviewVO.setRv_title(rv_title);
                 reviewVO.setRv_text(rv_text);
                 reviewVO.setRv_rate(rv_rate);
                 reviewVO.setRv_date(rv_date);
                 reviewList.add(reviewVO);
             }
             rs.close();
             pstmt.close();
             con.close();
         } catch (Exception e) {
             System.out.println("글 목록 페이징 구현중 에러 발생");
             e.printStackTrace();
         }
         return reviewList;
     }
     
     

     
     
     //
     
     
//=========================================================================================
     //일반회원이 쓴 리뷰테이블에서 해당 병원코드를 가진 병원의 리뷰만 뜨게 하기( 해당 병원에 예약했던 회원이 남긴 리뷰!)
     
     //리뷰 전체 목록
     public List<ReviewVO> selectAllRev2(String hos_code){
    	 List<ReviewVO> hosReviewList= new ArrayList();
    	 
    	 try {
 			con=dataFactory.getConnection();
 			//String query="select * from reviewtbl natural join hosrestbl where hos_code=? order by rv_date";
 			String query="select * from reviewtbl where hos_code=? order by rv_date DESC";
 			pstmt=con.prepareStatement(query);
 			pstmt.setString(1, hos_code);
 			ResultSet rs=pstmt.executeQuery();
 			while(rs.next()) {
 				String rv_code=rs.getString("rv_code"); 
 				String rv_title=rs.getString("rv_title");
 				Date rv_date=rs.getDate("rv_date");
 				int rv_rate=rs.getInt("rv_rate"); 
	            String rv_text=rs.getString("rv_text");
 				ReviewVO reviewVO=new ReviewVO(rv_code, rv_title, rv_date, rv_rate, rv_text);
 			    hosReviewList.add(reviewVO);
 			}
 			rs.close();
 			pstmt.close();
 			con.close();
 			
 		   }catch (Exception e) {
 			System.out.println("DB조회 중 에러!");
 			e.printStackTrace();
 		 }
     	 return hosReviewList;
    	 
    	 
     }
     
     
     //리뷰 개수 불러오기 메소드
     public int reviewcount(String hos_code) {
    	 int review_count=0;
    	 try {
			con=dataFactory.getConnection();
			//String query="select count(*)as count from reviewtbl r  natural join hosrestbl where hos_code=?";
			String query="select count(*)as count from reviewtbl where hos_code=?";
			pstmt=con.prepareStatement(query);
 			pstmt.setString(1, hos_code);
 			ResultSet rs=pstmt.executeQuery();
 			rs.next();
 			review_count=rs.getInt("count");
 			rs.close();
 			pstmt.close();
 			con.close();
		} catch (Exception e) {
			System.out.println("리뷰 개수 처리중 오류!");
			e.printStackTrace();
		}
    	 return review_count;
     }
     
     
     
     
     //병원 리뷰 평점 구하기 메소드
     public float reviewAvg(String hos_code) {
    	 float review_avg=0;
    	 try {
    		 con=dataFactory.getConnection();
    		 String query="select AVG(rv_rate) as avg1 from reviewtbl where hos_code=?";
    		pstmt=con.prepareStatement(query);
  			pstmt.setString(1, hos_code);
  			ResultSet rs=pstmt.executeQuery();
  			rs.next();
 			review_avg=rs.getFloat("avg1");
 			rs.close();
 			pstmt.close();
 			con.close();
    	 }catch (Exception e) {
			System.out.println("병원 평점 구하기 처리중 에러!");
			e.printStackTrace();
		}
    	 return review_avg;
     }
     
     
     
     //리뷰 글 상세보기 메소드
     public ReviewVO selectReview(String rv_code){
    	 ReviewVO review=new ReviewVO();
    	 try {
			con=dataFactory.getConnection();
		  String query="select * from  reviewtbl where rv_code=?";
		  pstmt=con.prepareStatement(query);
		  pstmt.setString(1, rv_code);
		  ResultSet rs=pstmt.executeQuery();
		  rs.next();
		  String rv_title=rs.getString("rv_title");
		  String rv_text=rs.getString("rv_text");
		  Date rv_date=rs.getDate("rv_date");
		  int rv_rate=rs.getInt("rv_rate");
		  review.setRv_title(rv_title);
		  review.setRv_text(rv_text);
		  review.setRv_date(rv_date);
		  review.setRv_rate(rv_rate);
		    
		  rs.close();
		  pstmt.close();
		  con.close();
    	 } catch (Exception e) {
			System.out.println("상세보기 중 에러!");
			e.printStackTrace();
		}
    	 return review;
     }
     
   //삭제요청 컬럼에 반영하기 메소드
     public void requestDelRev(String[] items) {
    	 
    	 String[]rv_code=items;
    	 try {
    		 if(rv_code==null || rv_code.length==0) {
    			 System.out.println("삭제요청할 리뷰를 선택 안 함");
    		 }else {
    			 
    		 
    		 for(int i=0; i<rv_code.length; i++) {
    			con=dataFactory.getConnection();
    			String query="update reviewtbl set rv_delreq='1' where rv_code=?";
    			pstmt=con.prepareStatement(query);
    	 		pstmt.setString(1, rv_code[i]);
    	 		pstmt.executeUpdate();
    			pstmt.close();
    			con.close();
    		 }
    		 }
 			//review_count=rs.getInt("count");
		} catch (Exception e) {
			System.out.println("삭제요청 컬럼에 반영하기 처리중 오류!");
			e.printStackTrace();
		}
     }
     
     
     //삭제요청 반영된 리뷰리스트 가져오기
     public List selectAllreqDelReviews() {
    	 List <ReviewVO> reqDelReviews=new ArrayList<>();
    	 try {
    		 con=dataFactory.getConnection();
   		  String query="select * from  reviewtbl where rv_delreq=1 order by rv_date";
   		pstmt=con.prepareStatement(query);
   	    ResultSet rs=pstmt.executeQuery();
   	   while(rs.next()) {
			String rv_code=rs.getString("rv_code"); 
			String hos_code=rs.getString("hos_code");
			String hos_name=rs.getString("hos_name");
			Date rv_date=rs.getDate("rv_date");
       
			ReviewVO reviewVO=new ReviewVO(rv_code, hos_code, hos_name, rv_date);
			reqDelReviews.add(reviewVO);
		}
		 
	    
	  rs.close();
	  pstmt.close();
	  con.close();
   		
   		  
    	 }catch (Exception e) {
			System.out.println("삭제요청된 리뷰 리스트 불러오기 처리중 오류!<HosReviewDAO>");
			e.printStackTrace();
		}
    	 return reqDelReviews;
     }
     
     
     
     
     //삭제요청 리뷰 리스트 페이징(8개씩 담김)
     public List selectAllReqDelReviews(Map<String, Integer> pagingMap, int rv_delreq) {
         List<ReviewVO> relDelreviewList = new ArrayList<ReviewVO>();
         int section = pagingMap.get("section");
         int pageNum = pagingMap.get("pageNum");

         try {
             con = dataFactory.getConnection();
             String query = "SELECT * FROM (SELECT ROWNUM AS row_num, reviewtbl.* FROM "
                     + " (SELECT * FROM reviewtbl "
                     + "  WHERE rv_delreq = ? ORDER BY rv_code) reviewtbl)"
                     + "  WHERE row_num BETWEEN (?-1)*80+(?-1)*8+1 AND (?-1)*80+(?)*8";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, rv_delreq);
            pstmt.setInt(2, section);
            pstmt.setInt(3, pageNum);
            pstmt.setInt(4, section);
            pstmt.setInt(5, pageNum);
             
             ResultSet rs = pstmt.executeQuery();
             while(rs.next()) {
     			String rv_code=rs.getString("rv_code"); 
     			String hos_code=rs.getString("hos_code");
     			String hos_name=rs.getString("hos_name");
     			Date rv_date=rs.getDate("rv_date");
            
     			ReviewVO reviewVO=new ReviewVO(rv_code, hos_code, hos_name, rv_date);
     			relDelreviewList.add(reviewVO);
     		}
     		 
     	    
     	  rs.close();
     	  pstmt.close();
     	  con.close();
         } catch (Exception e) {
             System.out.println("삭제 요청된 리뷰 목록 페이징 구현중 에러 발생");
             e.printStackTrace();
         }
         return relDelreviewList;
     }
     
     

     //삭제 요청된 리뷰 개수 불러오기 메소드
     public int delReqreviewcount(int rv_delreq) {
    	 int reqDelreview_count=0;
    	 try {
			con=dataFactory.getConnection();
			//String query="select count(*)as count from reviewtbl r  natural join hosrestbl where hos_code=?";
			String query="select count(*)as count from reviewtbl where rv_delreq=?";
			pstmt=con.prepareStatement(query);
 			pstmt.setInt(1, rv_delreq);
 			ResultSet rs=pstmt.executeQuery();
 			rs.next();
 			reqDelreview_count=rs.getInt("count");
 			rs.close();
 			pstmt.close();
 			con.close();
		} catch (Exception e) {
			System.out.println("삭제요청된 리뷰 개수 처리중 오류!");
			e.printStackTrace();
		}
    	 return reqDelreview_count;
     }
     
     
     
     
     
     //관리자가 리뷰 삭제하기
     public void deleteAllReviews(String[] items) {
    	 String[]rv_code=items;
    	 
    	 try {
    		 if(rv_code==null || rv_code.length==0) {
    			 System.out.println("삭제할 리뷰를 선택 안 함");
    		 }else {
    			 
    		 
    		 for(int i=0; i<rv_code.length; i++) {
    			con=dataFactory.getConnection();
    			String query="delete from reviewtbl where rv_code=?";
    			pstmt=con.prepareStatement(query);
    	 		pstmt.setString(1, rv_code[i]);
    	 		pstmt.executeUpdate();
    			pstmt.close();
    			con.close();
    		 }
    		 }
 			//review_count=rs.getInt("count");
		} catch (Exception e) {
			System.out.println("삭제요청된 리뷰 삭제 처리중 오류!");
			e.printStackTrace();
		}
    	 
    	 
    	 
    	 
     }
  
}
