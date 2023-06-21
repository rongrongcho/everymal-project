package userHistory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HistoryDAO {
   private static String IMG_REPO="D:\\everymal_0517final\\imgRepo\\hos_images";
   //=========================================ConnectionPool
   private Connection con; 
   private PreparedStatement pstmt; 
   private DataSource dataFactory ;

   //   생성자 필요 
   public HistoryDAO() {
      try {
         Context ctx=new InitialContext();
         Context envContext=(Context)ctx.lookup("java:/comp/env");
         dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
      }catch(Exception e) {
         System.out.println("커넥션풀 연결실패"); 
         e.printStackTrace();
      }
      
      
   }
   
         
//   ==============================================================

   

      
   //병원 코드 가져오기 메서드
    public String findhos_code(String hos_name) {
       String hos_code=null;
       try {
        con=dataFactory.getConnection();
        String query="select * from hosjointbl where hos_name=?";
        pstmt=con.prepareStatement(query);
        pstmt.setString(1, hos_name);
        ResultSet rs=pstmt.executeQuery();
        rs.next();
        hos_code=rs.getString("hos_code");
     
     } catch (Exception e) {
        System.out.println("병원 코드 가져오기 중 에러!");
        e.printStackTrace();
     }
       return hos_code;
    }

   
   
   
   
   
   
//===============================================================
   
   //병원 예약 이용내역 목록 조회 
   
   public List<HistoryVO> hosResList(HistoryVO hisVO) {
      List<HistoryVO> hrList=new ArrayList<HistoryVO>();
      String user_id=hisVO.getUser_id();
      System.out.println("시간 낭비 하지말자 " + user_id);
      try {
         con=dataFactory.getConnection();
         String query="SELECT RES_CODE, receipt_date,hos_name,res_date,hos_code"
               + "  from hosrestbl where user_id=? And res_Date < SYSDATE";
       pstmt=con.prepareStatement(query);
       pstmt.setString(1,user_id );
       ResultSet rs=pstmt.executeQuery();
       while(rs.next()) {
          String resCode=rs.getString("res_code");
          Date reciptDate=rs.getDate("receipt_date");
          String hosName=rs.getString("hos_name");
          Date resDate=rs.getDate("res_date");
          String hos_code =rs.getString("hos_code");

         //===================================
          HistoryVO historyVO=new HistoryVO();
          historyVO.setRes_code(resCode);
          historyVO.setHos_name(hosName);
          historyVO.setRes_date(resDate);
          historyVO.setReceipt_date(reciptDate);
          historyVO.setHos_code(hos_code); 
          hrList.add(historyVO);          
       }//while문 끝 
       rs.close();
       pstmt.close();
       con.close();
       
      } catch (Exception e) {
         System.out.println("HistoryDAO : 병원 예약 이용내역 조회 중 에러 ");
         e.printStackTrace();
      }
            
      return hrList;
      
   }
   
   //==============병원 최근 예약 목록 
   
   public List<HistoryVO>    hosRecent(HistoryVO hisVO) {
      List<HistoryVO> recentList=new ArrayList<HistoryVO>();
      String user_id=hisVO.getUser_id();
      try {
         con=dataFactory.getConnection();
         String query="SELECT h.res_code, h.receipt_date, h.hos_name, h.res_date,"
               + " h.hos_code, j.hos_thum"
               + " FROM hosrestbl h"
               + " JOIN hosjointbl j ON h.hos_code = j.hos_code"
               + " WHERE h.user_id = ?"
               + " AND h.res_date > SYSDATE"
               + " ORDER BY h.res_date";
         
         
       pstmt=con.prepareStatement(query);
       pstmt.setString(1,user_id );
       ResultSet rs=pstmt.executeQuery();
       while(rs.next()) {
          String r_resCode=rs.getString("res_code");
          Date r_reciptDate=rs.getDate("receipt_date");
          String r_hosName=rs.getString("hos_name");
          Date r_resDate=rs.getDate("res_date");
          String hos_code=rs.getString("hos_code");
          String hos_thum=rs.getString("hos_thum");

          
          HistoryVO historyVO=new HistoryVO();
          historyVO.setR_res_code(r_resCode);
          historyVO.setR_hos_name(r_hosName);
          historyVO.setR_res_date(r_resDate);
          historyVO.setR_receipt_date(r_reciptDate);
          historyVO.setHos_thum(hos_thum);
          historyVO.setR_hos_code(hos_code);
          recentList.add(historyVO);         
          
       }//while문 끝 
       rs.close();
       pstmt.close();
       con.close();
       
      } catch (Exception e) {
         System.out.println("HistoryDAO : 병원 최근예약 조회 중 에러 ");
         e.printStackTrace();
      }
            
      return recentList;
      
   }
   
   //최신 예약 정보 상세보기
   
   public HistoryVO selectHis(String _res_code) {
       HistoryVO historyVO=new HistoryVO();
       try {
           con=dataFactory.getConnection();
           String query="select * from hosrestbl where res_code=?";
           pstmt=con.prepareStatement(query);
           pstmt.setString(1, _res_code); 
           ResultSet rs=pstmt.executeQuery();
           rs.next();
           String res_code=rs.getString("res_code");
           String user_name=rs.getString("user_name");
           String user_id=rs.getString("user_id");
           String user_code=rs.getString("user_code");
           String res_time=rs.getString("res_time");
           String res_etc=rs.getString("res_etc");
           String user_tel=rs.getString("user_tel");

           Date res_date =rs.getDate("res_date");
           Date receipt_date=rs.getDate("receipt_date");
           float pet_weight=rs.getFloat("pet_weight");
           String pet_types=rs.getString("pet_types");
           String pet_sex=rs.getString("pet_sex");
           String pet_name=rs.getString("pet_name");

           String pet_code=rs.getString("pet_code");
           int pet_age=rs.getInt("pet_age");
           String hos_name=rs.getString("hos_name");
           String hos_code=rs.getString("hos_code");
           String b_type=rs.getString("b_type");
           String pet_number=rs.getString("pet_number");
           String hos_sub=rs.getString("hos_sub");
           
           historyVO.setHos_code(hos_code); 
           historyVO.setUser_name(user_name);
           historyVO.setUser_id(user_id);
           historyVO.setUser_tel(user_tel);
           historyVO.setUser_code(user_code);
           historyVO.setRes_time(res_time);
           historyVO.setRes_etc(res_etc);
           historyVO.setRes_date(res_date);
           historyVO.setReceipt_date(receipt_date);
           historyVO.setPet_weight(pet_weight);
           historyVO.setPet_types(pet_types);
           historyVO.setPet_sex(pet_sex);
           historyVO.setPet_name(pet_name);
           historyVO.setPet_code(pet_code);
           historyVO.setPet_age(pet_age);
           historyVO.setHos_name(hos_name);
           historyVO.setHos_code(hos_code);
           historyVO.setB_type(b_type);
           historyVO.setPet_number(pet_number);
           historyVO.setHos_sub(hos_sub);
           System.out.println("이거지!!!!" + (historyVO.getHos_sub()));
           
           rs.close();
           pstmt.close();
           con.close();
       } catch (Exception e) {
           System.out.println("유저의 병원 최근예약 상세 보기 구현 중 에러");
           e.printStackTrace();
       }
       return historyVO;
   }

   
   

   
   //지난 예약 정보 상세보기 
   
   
   
//=======================================================================
   
   
   
   
   
   //택시 지난 예약 이용내역 조회 
   
   public List<HistoryVO> txResList(HistoryVO hisVO) {
      List<HistoryVO> hrList=new ArrayList<HistoryVO>();
      String user_code=hisVO.getUser_code();
      System.out.println(" 택시 시간 낭비 하지말자 " + user_code);
      try {
         con=dataFactory.getConnection();
         String query="SELECT * "
               + "  from TAXIRESTBL where user_code=? And TX_RESERV_DAY < SYSDATE";
       pstmt=con.prepareStatement(query);
       pstmt.setString(1,user_code );
       ResultSet rs=pstmt.executeQuery();
       while(rs.next()) {
          
          String tx_res_code=rs.getString("tx_res_code"); // 택시 예약 코드 
          Date tx_reserv_day=rs.getDate("tx_reserv_day"); // 택시 예약 날짜
          String tx_dep=rs.getString("tx_dep"); //  출발지 
          String tx_arr=rs.getString("tx_arr"); // 도착지 
          String tx_code=rs.getString("tx_code"); // 택시 코드 
          String tx_number=rs.getString("tx_number"); // 택시 번호판
          String tx_name  =rs.getString("tx_name"); // 택시 기사 이름
          String tx_tel =rs.getString("tx_tel"); // 택시 연락처 
         //===================================
          HistoryVO historyVO=new HistoryVO();
          historyVO.setTx_res_code(tx_res_code);
          historyVO.setTx_reserv_day(tx_reserv_day);
          historyVO.setTx_dep(tx_dep);
          historyVO.setTx_arr(tx_arr);
          historyVO.setTx_code(tx_code);
          historyVO.setTx_number(tx_number);
          historyVO.setTx_name(tx_name);
          historyVO.setTx_tel(tx_tel);
          hrList.add(historyVO);          
       }//while문 끝 
       rs.close();
       pstmt.close();
       con.close();
       
      } catch (Exception e) {
         System.out.println("HistoryDAO : 택시 예약 이용내역 조회 중 에러 ");
         e.printStackTrace();
      }
            
      return hrList;
      
   }
   
   //==============택시 최근 예약 목록 
   
   public List<HistoryVO>    txRecent(HistoryVO hisVO) {
      List<HistoryVO> recentList=new ArrayList<HistoryVO>();
      String user_code=hisVO.getUser_code();
      try {
         con=dataFactory.getConnection();
         
         
         String query="SELECT tr.* , ti.tx_img"
               + " FROM TAXIRESTBL tr"
               + " JOIN TAXIINFOTBL ti"
               + " ON tr.TX_CODE = ti.TX_CODE"
               + " WHERE tr.USER_CODE = ? AND tr.TX_RESERV_DAY > SYSDATE"
               + " ORDER BY tr.TX_RESERV_DAY";
                              

       pstmt=con.prepareStatement(query);
       pstmt.setString(1,user_code );
       ResultSet rs=pstmt.executeQuery();
       while(rs.next()) {
          String tx_res_code=rs.getString("tx_res_code"); // 택시 예약 코드 
          Date tx_reserv_day=rs.getDate("tx_reserv_day"); // 택시 예약 날짜
          String tx_dep=rs.getString("tx_dep"); //  출발지 
          String tx_arr=rs.getString("tx_arr"); // 도착지 
          String tx_code=rs.getString("tx_code"); // 택시 코드 
          String tx_number=rs.getString("tx_number"); // 택시 번호판
          String tx_name  =rs.getString("tx_name"); // 택시 기사 이름
          String tx_tel =rs.getString("tx_tel"); // 택시 연락처 
          String tx_img=rs.getString("tx_img");

         //===================================
          HistoryVO historyVO=new HistoryVO();
          historyVO.setTtx_res_code(tx_res_code);
          historyVO.setTtx_reserv_day(tx_reserv_day);
          historyVO.setTtx_dep(tx_dep);
          historyVO.setTtx_arr(tx_arr);
          historyVO.setTtx_code(tx_code);
          historyVO.setTtx_number(tx_number);
          historyVO.setTtx_name(tx_name);
          historyVO.setTtx_tel(tx_tel);
          historyVO.setTx_img(tx_img);

          recentList.add(historyVO);         
          
       }//while문 끝 
       rs.close();
       pstmt.close();
       con.close();
       
      } catch (Exception e) {
         System.out.println("HistoryDAO : 택시 최근예약 조회 중 에러 ");
         e.printStackTrace();
      }
            
      return recentList;
      
   }
   
   
   
   public HistoryVO selectTaxiHis(String tx_res_code) {
	   HistoryVO historyVO=new HistoryVO();
	   try {
	       con=dataFactory.getConnection();
	       String query="select tr.*, ti.* from taxirestbl tr join taxiinfotbl ti on tr.tx_code=ti.tx_code where tr.tx_res_code=?";
	       pstmt=con.prepareStatement(query);
	       pstmt.setString(1, tx_res_code); 
	       ResultSet rs=pstmt.executeQuery();
	       
	       if (rs.next()) {
	           String taxi_res_code=rs.getString("tx_res_code");
	           Date tx_reserv_day=rs.getDate("tx_reserv_day");
	           String tx_dep=rs.getString("tx_dep");
	           String tx_arr=rs.getString("tx_arr");
	           String user_code=rs.getString("user_code");
	           String tx_code=rs.getString("tx_code");
	           String tx_number=rs.getString("tx_number");
	           String tx_name=rs.getString("tx_name");
	           String tx_tel=rs.getString("tx_tel");
	           String res_name=rs.getString("res_name");
	           String tx_restime=rs.getString("tX_restime");
	           String tx_img=rs.getString("tx_img");
	           String tx_intro=rs.getString("tx_intro");
	           
	           historyVO.setTx_res_code(taxi_res_code);
	           historyVO.setTx_reserv_day(tx_reserv_day);
	           historyVO.setTx_dep(tx_dep);
	           historyVO.setTx_arr(tx_arr);
	           historyVO.setUser_code(user_code);
	           historyVO.setTx_code(tx_code);
	           historyVO.setTx_number(tx_number);
	           historyVO.setTx_name(tx_name);
	           historyVO.setTx_tel(tx_tel);
	           historyVO.setRes_name(res_name);
	           historyVO.setRes_time(tx_restime);
	           historyVO.setTx_img(tx_img);
	           historyVO.setTx_intro(tx_intro);
	       }
	                  
	       rs.close();
	       pstmt.close();
	       con.close();
	   } catch (Exception e) {
	       System.out.println("유저의 택시 최근예약 상세 보기 구현 중 에러");
	       e.printStackTrace();
	   }
	   return historyVO;
	}


   
   
   
   
   
   
   
   

   
}