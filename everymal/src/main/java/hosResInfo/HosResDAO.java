package hosResInfo;

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

public class HosResDAO {
  
	
	 private Connection con;
     private PreparedStatement pstmt;
     private DataSource dataFactory;
     public HosResDAO() {
    	 
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
			
		}catch(Exception e){
			System.out.println("오라클 연결 오류");
			e.printStackTrace(); 
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
     
     
     
     
     
     //목록 메서드 
     public List<ResVO> selectAllListRes(String hos_code){
    	 List<ResVO> resList= new ArrayList();
    	 
    	 try {
 			con=dataFactory.getConnection();
 			String query="select res_code, pet_types, user_name, receipt_date from hosrestbl where hos_code=? order by receipt_date";
 			pstmt=con.prepareStatement(query);
 			pstmt.setString(1, hos_code);
 			ResultSet rs=pstmt.executeQuery();
 			while(rs.next()) {
 				String res_code=rs.getString("res_code");
 				String pet_types=rs.getString("pet_types"); //컬럼의 타입에 따라 get타입지정 정확하게 해주기
 				String user_name=rs.getString("user_name");
 				Date receipt_date=rs.getDate("receipt_date");
 				
 				ResVO resVO=new ResVO(res_code, pet_types, user_name, receipt_date); 
 			    resList.add(resVO); 
 			}
 			rs.close();
 			pstmt.close();
 			con.close();
 			
 		   }catch (Exception e) {
 			System.out.println("DB조회 중 에러!");
 			e.printStackTrace();
 		 }
     	 return resList;
    	 
    	 
     }
     
     
     //페이징쓰는 예약 리스트 (예약이 8개씩 담김)
     public List selectAllReservation(Map<String, Integer> pagingMap, String hos_code) {
         List<ResVO> resList = new ArrayList<ResVO>();
         int section = pagingMap.get("section");
         int pageNum = pagingMap.get("pageNum");

         try {
             con = dataFactory.getConnection();
             String query = "SELECT * FROM (SELECT ROWNUM AS row_num, hosrestbl.* FROM "
                     + " (SELECT * FROM hosrestbl "
                     + "  WHERE hos_code = ? ORDER BY receipt_date) hosrestbl)"
                     + "  WHERE row_num BETWEEN (?-1)*80+(?-1)*8+1 AND (?-1)*80+(?)*8";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, hos_code);
            pstmt.setInt(2, section);
            pstmt.setInt(3, pageNum);
            pstmt.setInt(4, section);
            pstmt.setInt(5, pageNum);
             
             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
            	 String res_code=rs.getString("res_code");
  				String pet_types=rs.getString("pet_types"); 
  				String user_name=rs.getString("user_name");
  				Date receipt_date=rs.getDate("receipt_date");
  				
  				//ResVO resVO=new ResVO(res_code, pet_types, user_name, receipt_date); 
  			    //resList.add(resVO); 
  			  ResVO resVO=  new ResVO();
              resVO.setRes_code(res_code);
              resVO.setPet_types(pet_types);
              resVO.setUser_name(user_name);
              resVO.setReceipt_date(receipt_date);
              
              resList.add(resVO);
                

             }
             rs.close();
             pstmt.close();
             con.close();
         } catch (Exception e) {
             System.out.println("글 목록 페이징 구현중 에러 발생");
             e.printStackTrace();
         }
         return resList;
     }
     
     
     
     
     
     
     
     
     //예약 개수 불러오기 메소드
     public int rescount(String hos_code) {
    	 int res_count=0;
    	 try {
			con=dataFactory.getConnection();
			String query="select count(*)as count from hosrestbl where hos_code=?";
			pstmt=con.prepareStatement(query);
 			pstmt.setString(1, hos_code);
 			ResultSet rs=pstmt.executeQuery();
 			rs.next();
 			res_count=rs.getInt("count");
 			rs.close();
 			pstmt.close();
 			con.close();
		} catch (Exception e) {
			System.out.println("예약 개수 처리중 오류!");
			e.printStackTrace();
		}
    	 return res_count;
     }
     
     
     
     
     
     //예약 목록 상세보기 메소드
     public ResVO selectRes(String res_code){
    	 ResVO reservation=new ResVO();
    	 try {
		  con=dataFactory.getConnection();
		  String query="select *  from  hosrestbl where res_code=?";
		  //String query2="select nvl(pet_code, '미기재') as pet_code from  hosrestbl where res_code=?";
		  pstmt=con.prepareStatement(query);
		  pstmt.setString(1, res_code);
		  ResultSet rs=pstmt.executeQuery();
		  rs.next();
		  String _res_code=rs.getString("res_code");
		  String user_name=rs.getString("user_name");
		  String user_tel=rs.getString("user_tel");
		  String pet_name=rs.getString("pet_name");
		  int pet_age=rs.getInt("pet_age");
		  String pet_sex=rs.getString("pet_sex");
		  String hos_name=rs.getString("hos_name");
		  Date res_date=rs.getDate("res_date");
		  String res_time=rs.getString("res_time");
		  String hos_sub=rs.getString("hos_sub");
		  String res_etc=rs.getString("res_etc");
		  String pet_code=rs.getString("pet_code");
		  String pet_types=rs.getString("pet_types");
		  
		  String b_type=rs.getString("b_type");
		  float pet_weight=rs.getFloat("pet_weight");	  
		  
		  //pstmt=con.prepareStatement(query2);
		  pstmt.setString(1, res_code);
		  rs=pstmt.executeQuery();
		  rs.next();
		  String pet_number=rs.getString("pet_number");
		  
		  reservation.setRes_code(_res_code);
		  reservation.setUser_name(user_name);
		  reservation.setRes_date(res_date);
		  reservation.setUser_tel(user_tel);
		  reservation.setPet_name(pet_name);
		  reservation.setPet_age(pet_age);
		  reservation.setPet_sex(pet_sex);
		  reservation.setHos_name(hos_name);
		  reservation.setRes_date(res_date);
		  reservation.setRes_time(res_time);
		  reservation.setHos_sub(hos_sub);
		  reservation.setRes_etc(res_etc);
		 reservation.setPet_number(pet_number);
		  reservation.setPet_code(pet_code);
		  reservation.setPet_types(pet_types);
		  reservation.setB_type(b_type);
		  reservation.setPet_weight(pet_weight);
		  
		 
		  
		  
		  rs.close();
		  pstmt.close();
		  con.close();
    	 } catch (Exception e) {
			System.out.println("상세보기 중 에러!");
			e.printStackTrace();
		}
    	 return reservation;
     }
     
     
}
