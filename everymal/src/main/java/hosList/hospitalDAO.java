package hosList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class hospitalDAO {
   private Connection conn;
   private PreparedStatement pstmt;
   private DataSource dataFactory;
   
   public hospitalDAO() {
      try {
         Context ctx = new InitialContext();
         Context envContext = (Context)ctx.lookup("java:/comp/env");
         dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
      } catch (Exception e) {
         System.out.println("오라클 연결 오류");
         e.printStackTrace();
      }
   }
//   병원 등록
   public void addHos(hospitalVO hosVO) {
      String code = getHosCode();
      try {
         conn = dataFactory.getConnection();
         String hos_id = hosVO.getHos_id();
         String hos_username = hosVO.getHos_username();
         String hos_usertel = hosVO.getHos_usertel();
         String hos_pwd = hosVO.getHos_pwd();
         long hos_dno = hosVO.getHos_dno();
         String hos_name = hosVO.getHos_name();
         String hos_tel = hosVO.getHos_tel();
         String hos_zipcode = hosVO.getHos_zipcode();
         String hos_addr1 = hosVO.getHos_addr1();
         String hos_addr2 = hosVO.getHos_addr2();
         String hos_email = hosVO.getHos_email();
         String hos_intro = hosVO.getHos_intro();
         
         String hos_24 = hosVO.getHos_24();
         String hos_365 = hosVO.getHos_365();
         String hos_time_s = hosVO.getHos_time_s();
         String hos_time_e = hosVO.getHos_time_e();
         String hos_dog = hosVO.getHos_dog();
         String hos_cat = hosVO.getHos_cat();
         String hos_small = hosVO.getHos_small();
         String hos_fish = hosVO.getHos_fish();
         String hos_bird = hosVO.getHos_bird();
         String hos_rep = hosVO.getHos_rep();
         String hos_etc = hosVO.getHos_etc();
         String hos_gs = hosVO.getHos_gs();
         String hos_im = hosVO.getHos_im();
         String hos_em = hosVO.getHos_em();
         String hos_rm = hosVO.getHos_rm();
         String hos_vac = hosVO.getHos_vac();

//         ---------------------------------------------------
         
         String query = "insert all " + 
                     "into hosjointbl " +
                     "(hos_code, hos_id, hos_username, hos_usertel, hos_pwd, hos_dno, hos_name, hos_tel, hos_zipcode, hos_addr1, hos_addr2, hos_email, hos_intro) " +
                     "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " + 
                     "into hosfiltertbl " +
                     "(hos_code, hos_24, hos_365, hos_time_s, hos_time_e, hos_dog, hos_cat, hos_small, hos_fish, hos_bird, hos_rep, hos_etc, hos_gs, hos_im, hos_em, hos_rm, hos_vac) " +
                     "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) SELECT * FROM DUAL";
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, code);
         pstmt.setString(2, hos_id);
         pstmt.setString(3, hos_username);
         pstmt.setString(4, hos_usertel);
         pstmt.setString(5, hos_pwd);
         pstmt.setLong(6, hos_dno);
         pstmt.setString(7, hos_name);
         pstmt.setString(8, hos_tel);
         pstmt.setString(9, hos_zipcode);
         pstmt.setString(10, hos_addr1);
         pstmt.setString(11, hos_addr2);
         pstmt.setString(12, hos_email);
         pstmt.setString(13, hos_intro);
   
         pstmt.setString(14, code);
         pstmt.setString(15, hos_24);
         pstmt.setString(16, hos_365);
         pstmt.setString(17, hos_time_s);
         pstmt.setString(18, hos_time_e);
         pstmt.setString(19, hos_dog);
         pstmt.setString(20, hos_cat);
         pstmt.setString(21, hos_small);
         pstmt.setString(22, hos_fish);
         pstmt.setString(23, hos_bird);
         pstmt.setString(24, hos_rep);
         pstmt.setString(25, hos_etc);
         pstmt.setString(26, hos_gs);
         pstmt.setString(27, hos_im);
         pstmt.setString(28, hos_em);
         pstmt.setString(29, hos_rm);
         pstmt.setString(30, hos_vac);
         
         pstmt.executeUpdate();
         pstmt.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("DB 등록 중 에러!!");
         e.printStackTrace();
      }
   }
   
   //   회원 조회
      public hospitalVO selectHos(String _hos_code){
         hospitalVO selectHos = null;
         try {
            conn = dataFactory.getConnection();
            String query = "select * from HOSFILTERTBL left join HOSJOINTBL on HOSFILTERTBL.HOS_CODE =  HOSJOINTBL.HOS_CODE  where HOSFILTERTBL.HOS_CODE = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, _hos_code);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            System.out.println(rs);
            String hos_code = rs.getString("hos_code");
            String hos_name = rs.getString("hos_name");
            String hos_tel = rs.getString("hos_tel");
            String hos_addr1 = rs.getString("hos_addr1");
            String hos_addr2 = rs.getString("hos_addr2");
            String hos_email = rs.getString("hos_email");
            String hos_intro = rs.getString("hos_intro");
            String hos_24 = rs.getString("hos_24");
            String hos_365 = rs.getString("hos_365");
            String hos_time_s = rs.getString("hos_time_s");
            String hos_time_e = rs.getString("hos_time_e");
            String hos_dog = rs.getString("hos_dog");
            String hos_cat = rs.getString("hos_cat");
            String hos_small = rs.getString("hos_small");
            String hos_fish = rs.getString("hos_fish");
            String hos_bird = rs.getString("hos_bird");
            String hos_rep = rs.getString("hos_rep");
            String hos_etc = rs.getString("hos_etc");
            String hos_gs = rs.getString("hos_gs");
            String hos_im = rs.getString("hos_im");
            String hos_em = rs.getString("hos_em");
            String hos_rm = rs.getString("hos_rm");
            String hos_vac = rs.getString("hos_vac");
            selectHos = new hospitalVO(hos_code, hos_name, hos_tel, hos_addr1, hos_addr2, hos_email, hos_intro, hos_24, hos_365, hos_time_s, hos_time_e, hos_dog, hos_cat, hos_small, hos_fish, hos_bird, hos_rep, hos_etc, hos_gs, hos_im, hos_em, hos_rm, hos_vac);
            
            pstmt.close();
            conn.close();
            rs.close();
         } catch (Exception e) {
            System.out.println("DB 조회 중 에러!");
            e.printStackTrace();
         }
         return selectHos;
      }
      
      public String getHosCode() {
          String code="";
          try {
              conn=dataFactory.getConnection();
              String query="SELECT MAX(SUBSTR(hos_code, 4)) AS max_num FROM hosjointbl";
              pstmt=conn.prepareStatement(query);
              ResultSet rs=pstmt.executeQuery();
              if(rs.next()) {
                  int maxNum = rs.getInt("max_num") + 1;
                  code = String.format("hos%04d", maxNum);
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
      
//      ID중복 체크(DB활용)
      public boolean overlappedID(String hos_id) {
         boolean result=false;
         //중복체크
         try {
            conn=dataFactory.getConnection();
            String query="select decode(count(*),1,'true','false') as result from hosjointbl where hos_id=?";
            pstmt=conn.prepareStatement(query);
            pstmt.setString(1, hos_id);
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
      
      
      
      
      //리뷰=================
      //리뷰쓰기 
            public void addreview(hospitalVO hosVO) {
               try {
                  conn=dataFactory.getConnection();
                  String rv_code=hosVO.getRv_code();
                  String rv_title=hosVO.getRv_title();
                  String rv_text=hosVO.getRv_text();
                  int rv_rate= hosVO.getRv_rate();
                  String user_id=hosVO.getUser_id();
                  String hos_name=hosVO.getHos_name();
                  String res_code=hosVO.getRes_code();
                  String hos_code=hosVO.getHos_code();
                  
                  String query="insert into reviewtbl(rv_code, rv_title, rv_text, rv_rate, hos_name, user_id, res_code, hos_code) values(?,?,?,?,?,?,?,?)";
                  pstmt=conn.prepareStatement(query);
                  pstmt.setString(1, rv_code);
                  pstmt.setString(2, rv_title);
                  pstmt.setString(3, rv_text);
                  pstmt.setInt(4, rv_rate);
                  pstmt.setString(5, hos_name);
                  pstmt.setString(6, user_id);
                  pstmt.setString(7, res_code);
                  pstmt.setString(8, hos_code);
                  pstmt.executeUpdate();
                  pstmt.close();
                  conn.close();
               } catch (Exception e) {
                  System.out.println("review 등록 중 에러!");
                  e.printStackTrace();
               }
            }
            
            //리뷰 코드 생성 
             public String getNewRevCode() {
                   String code="";
                   try {
                       conn=dataFactory.getConnection();
                       String query="SELECT MAX(SUBSTR(rv_code, 4)) AS max_num FROM reviewtbl";
                       pstmt=conn.prepareStatement(query);
                       ResultSet rs=pstmt.executeQuery();
                       if(rs.next()) {
                           int maxNum = rs.getInt("max_num") + 1;
                           code = String.format("rv%04d", maxNum);
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
             
             //예약 코드를 찾아보자 
             public String getResCode(String _user_code, String _hos_code) {
                 String rescode="";
                 try {
                     conn=dataFactory.getConnection();
                     String query="SELECT MAX(res_code) FROM hosrestbl WHERE user_code=? AND hos_code=?";
                     pstmt=conn.prepareStatement(query);
                     pstmt.setString(1, _user_code);
                     pstmt.setString(2, _hos_code);
                     ResultSet rs = pstmt.executeQuery();
                     if (rs.next()) {
                         rescode = rs.getString(1);
                     }
                     System.out.println("==============================힘내"+rescode);
                     rs.close();
                     pstmt.close();
                     conn.close();
                 } catch (Exception e) {
                     System.out.println("코드 찾는중 에러");
                     e.printStackTrace();
                 }
                 return rescode;}
			}


         