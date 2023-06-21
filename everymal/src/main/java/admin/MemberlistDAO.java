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

public class MemberlistDAO {
  
	
	 private Connection conn;
     private PreparedStatement pstmt;
     private DataSource dataFactory;
	
     public MemberlistDAO() {
    	 
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
     

  // 페이징된 회원 목록을 조회하는 메소드
     public List<MemberlistVO> selectAllList(Map<String, Integer> pagingMap) {
         List<MemberlistVO> memberList = new ArrayList<MemberlistVO>();
         int section = pagingMap.get("section"); // 페이징의 섹션 값
         int pageNum = pagingMap.get("pageNum"); // 페이징의 페이지 번호

         try {
             conn = dataFactory.getConnection();

             String query = "SELECT * FROM (SELECT row_number() OVER (ORDER BY j_date) AS row_num, " +
            	        "user_id, user_code, CASE " +
            	        "    WHEN user_code LIKE 'use%' THEN '일반회원' " +
            	        "    WHEN user_code LIKE 'hos%' THEN '병원회원' " +
            	        "END AS 회원구분, user_enabled, j_date " +
            	        "FROM (SELECT user_id, user_code, user_enabled, j_date FROM gmembertbl " +
            	        "      UNION ALL " +
            	        "      SELECT hos_id AS user_id, hos_code AS user_code, hos_enabled AS user_enabled, j_date FROM hosjointbl) combined) users " +
            	        "WHERE row_num BETWEEN (?-1)*60+(?-1)*6+1 AND (?-1)*60+(?)*6";

            	pstmt = conn.prepareStatement(query);
            	pstmt.setInt(1, section);
            	pstmt.setInt(2, pageNum);
            	pstmt.setInt(3, section);
            	pstmt.setInt(4, pageNum);


             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 // 조회된 회원 정보를 MemberlistVO 객체에 설정하여 리스트에 추가
                 String id = rs.getString("user_id");
                 String code = rs.getString("user_code");
                 String genre = rs.getString("회원구분");
                 String enabled = rs.getString("user_enabled");
                 Date j_date = rs.getDate("j_date");

                 MemberlistVO memberlistVO = new MemberlistVO();
                 memberlistVO.setId(id);
                 memberlistVO.setCode(code);
                 memberlistVO.setGenre(genre);
                 memberlistVO.setEnabled(enabled.equals("1") ? "Y" : "N"); // '1'이면 'Y', 그 외는 'N'
                 memberlistVO.setJ_date(j_date);
                 memberList.add(memberlistVO);
             }
             rs.close();
             pstmt.close();
             conn.close();
         } catch (Exception e) {
             System.out.println("글 목록 페이징 구현 중 에러 발생");
             e.printStackTrace();
         }
         return memberList;
     }

     // 전체 회원 수를 조회하는 메소드
     public int membercount() {
         int member_count = 0;
         try {
             conn = dataFactory.getConnection();
             // gmembertbl과 hosjointbl 테이블의 회원 수를 합하여 전체 회원 수를 조회하는 쿼리문
             String query = "SELECT COUNT(*) AS count FROM (SELECT user_id FROM gmembertbl UNION ALL SELECT hos_id FROM hosjointbl) users";
             pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery();
             rs.next();
             member_count = rs.getInt("count");
             rs.close();
             pstmt.close();
             conn.close();
         } catch (Exception e) {
             System.out.println("회원 수 처리중 오류!");
             e.printStackTrace();
         }
         return member_count;
     }
     
     // 탈퇴 회원 목록을 조회하는 메소드
     public List<MemberlistVO> selectAllEnabled(Map<String, Integer> pagingMap) {
         List<MemberlistVO> memberList = new ArrayList<MemberlistVO>();
         int section = pagingMap.get("section"); // 페이징의 섹션 값
         int pageNum = pagingMap.get("pageNum"); // 페이징의 페이지 번호

         try {
             conn = dataFactory.getConnection();

             String query = "SELECT * FROM (SELECT row_number() OVER (ORDER BY j_date) AS row_num, " +
            	        "user_id, user_code, CASE " +
            	        "    WHEN user_code LIKE 'use%' THEN '일반회원' " +
            	        "    WHEN user_code LIKE 'hos%' THEN '병원회원' " +
            	        "END AS 회원구분, user_enabled, j_date " +
            	        "FROM (SELECT user_id, user_code, user_enabled, j_date FROM gmembertbl where user_enabled=1 " +
            	        "      UNION ALL " +
            	        "      SELECT hos_id AS user_id, hos_code AS user_code, hos_enabled AS user_enabled, j_date FROM hosjointbl where hos_enabled=1) combined) users " +
            	        "WHERE row_num BETWEEN (?-1)*60+(?-1)*6+1 AND (?-1)*60+(?)*6";

            	pstmt = conn.prepareStatement(query);
            	pstmt.setInt(1, section);
            	pstmt.setInt(2, pageNum);
            	pstmt.setInt(3, section);
            	pstmt.setInt(4, pageNum);


             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 // 조회된 회원 정보를 MemberlistVO 객체에 설정하여 리스트에 추가
                 String id = rs.getString("user_id");
                 String code = rs.getString("user_code");
                 String genre = rs.getString("회원구분");
                 String enabled = rs.getString("user_enabled");
                 Date j_date = rs.getDate("j_date");

                 MemberlistVO memberlistVO = new MemberlistVO();
                 memberlistVO.setId(id);
                 memberlistVO.setCode(code);
                 memberlistVO.setGenre(genre);
                 memberlistVO.setEnabled(enabled.equals("1") ? "Y" : "N"); // '1'이면 'Y', 그 외는 'N'
                 memberlistVO.setJ_date(j_date);
                 memberList.add(memberlistVO);
             }
             rs.close();
             pstmt.close();
             conn.close();
         } catch (Exception e) {
             System.out.println("글 목록 페이징 구현 중 에러 발생");
             e.printStackTrace();
         }
         return memberList;
     }

     // 활동 회원 목록을 조회하는 메소드
     public List<MemberlistVO> selectAllAbled(Map<String, Integer> pagingMap) {
         List<MemberlistVO> memberList = new ArrayList<MemberlistVO>();
         int section = pagingMap.get("section"); // 페이징의 섹션 값
         int pageNum = pagingMap.get("pageNum"); // 페이징의 페이지 번호

         try {
             conn = dataFactory.getConnection();

             String query = "SELECT * FROM (SELECT row_number() OVER (ORDER BY j_date) AS row_num, " +
            	        "user_id, user_code, CASE " +
            	        "    WHEN user_code LIKE 'use%' THEN '일반회원' " +
            	        "    WHEN user_code LIKE 'hos%' THEN '병원회원' " +
            	        "END AS 회원구분, user_enabled, j_date " +
            	        "FROM (SELECT user_id, user_code, user_enabled, j_date FROM gmembertbl where user_enabled=0 " +
            	        "      UNION ALL " +
            	        "      SELECT hos_id AS user_id, hos_code AS user_code, hos_enabled AS user_enabled, j_date FROM hosjointbl where hos_enabled=0) combined) users " +
            	        "WHERE row_num BETWEEN (?-1)*60+(?-1)*6+1 AND (?-1)*60+(?)*6";

            	pstmt = conn.prepareStatement(query);
            	pstmt.setInt(1, section);
            	pstmt.setInt(2, pageNum);
            	pstmt.setInt(3, section);
            	pstmt.setInt(4, pageNum);


             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 // 조회된 회원 정보를 MemberlistVO 객체에 설정하여 리스트에 추가
                 String id = rs.getString("user_id");
                 String code = rs.getString("user_code");
                 String genre = rs.getString("회원구분");
                 String enabled = rs.getString("user_enabled");
                 Date j_date = rs.getDate("j_date");

                 MemberlistVO memberlistVO = new MemberlistVO();
                 memberlistVO.setId(id);
                 memberlistVO.setCode(code);
                 memberlistVO.setGenre(genre);
                 memberlistVO.setEnabled(enabled.equals("1") ? "Y" : "N"); // '1'이면 'Y', 그 외는 'N'
                 memberlistVO.setJ_date(j_date);
                 memberList.add(memberlistVO);
             }
             rs.close();
             pstmt.close();
             conn.close();
         } catch (Exception e) {
             System.out.println("글 목록 페이징 구현 중 에러 발생");
             e.printStackTrace();
         }
         return memberList;
     }

     // 일반 회원 목록을 조회하는 메소드
     public List<MemberlistVO> selectAllGmember(Map<String, Object> pagingMap) {
         List<MemberlistVO> memberList = new ArrayList<MemberlistVO>();
         int section = (int) pagingMap.get("section"); // 페이징의 섹션 값
         int pageNum = (int) pagingMap.get("pageNum"); // 페이징의 페이지 번호

         try {
             conn = dataFactory.getConnection();

             String query = "SELECT * FROM (SELECT row_number() OVER (ORDER BY j_date) AS row_num, " +
            	        "user_id, user_code, CASE " +
            	        "    WHEN user_code LIKE 'use%' THEN '일반회원' " +
            	        "    WHEN user_code LIKE 'hos%' THEN '병원회원' " +
            	        "END AS 회원구분, user_enabled, j_date " +
            	        "FROM (SELECT user_id, user_code, user_enabled, j_date FROM gmembertbl) combined) users " +
            	        "WHERE row_num BETWEEN (?-1)*60+(?-1)*6+1 AND (?-1)*60+(?)*6";

            	pstmt = conn.prepareStatement(query);
            	pstmt.setInt(1, section);
            	pstmt.setInt(2, pageNum);
            	pstmt.setInt(3, section);
            	pstmt.setInt(4, pageNum);


             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 // 조회된 회원 정보를 MemberlistVO 객체에 설정하여 리스트에 추가
                 String id = rs.getString("user_id");
                 String code = rs.getString("user_code");
                 String genre = rs.getString("회원구분");
                 String enabled = rs.getString("user_enabled");
                 Date j_date = rs.getDate("j_date");

                 MemberlistVO memberlistVO = new MemberlistVO();
                 memberlistVO.setId(id);
                 memberlistVO.setCode(code);
                 memberlistVO.setGenre(genre);
                 memberlistVO.setEnabled(enabled.equals("1") ? "Y" : "N"); // '1'이면 'Y', 그 외는 'N'
                 memberlistVO.setJ_date(j_date);
                 memberList.add(memberlistVO);
             }
             rs.close();
             pstmt.close();
             conn.close();
         } catch (Exception e) {
             System.out.println("글 목록 페이징 구현 중 에러 발생");
             e.printStackTrace();
         }
         return memberList;
     }
     
     // 일반 회원 수를 조회하는 메소드
     public int gmembercount() {
         int member_count = 0;
         try {
             conn = dataFactory.getConnection();
             String query = "SELECT COUNT(*) AS count FROM (SELECT user_id FROM gmembertbl) users";

             pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery();
             rs.next();
             member_count = rs.getInt("count");
             rs.close();
             pstmt.close();
             conn.close();
         } catch (Exception e) {
             System.out.println("회원 수 처리중 오류!");
             e.printStackTrace();
         }
         return member_count;
     }
     
     // 병원 회원 목록을 조회하는 메소드
     public List<MemberlistVO> selectAllHosmember(Map<String, Object> pagingMap) {
         List<MemberlistVO> memberList = new ArrayList<MemberlistVO>();
         int section = (int) pagingMap.get("section"); // 페이징의 섹션 값
         int pageNum = (int) pagingMap.get("pageNum"); // 페이징의 페이지 번호

         try {
             conn = dataFactory.getConnection();

//             String query = "SELECT * FROM (SELECT row_number() OVER (ORDER BY j_date) AS row_num, " +
//                     "hos_id AS user_id, hos_code AS user_code, '병원회원' AS 회원구분, hos_enabled AS user_enabled, j_date " +
//                     "FROM hosjointbl) users " +
//                     "WHERE row_num BETWEEN (?-1)*60+(?-1)*6+1 AND (?-1)*60+(?)*6";
//             String query = "SELECT * FROM (SELECT row_number() OVER (ORDER BY j_date) AS row_num, " +
//         	        "user_id, user_code, CASE " +
//         	        "    WHEN user_code LIKE 'use%' THEN '일반회원' " +
//         	        "    WHEN user_code LIKE 'hos%' THEN '병원회원' " +
//         	        "END AS 회원구분, user_enabled, j_date " +
//         	        "FROM (SELECT hos_id, hos_code, hos_enabled, j_date FROM hosjointbl) combined) users " +
//         	        "WHERE row_num BETWEEN (?-1)*60+(?-1)*6+1 AND (?-1)*60+(?)*6";
             String query = "SELECT * FROM (SELECT row_number() OVER (ORDER BY j_date) AS row_num, " +
              	    "hos_id AS user_id, hos_code AS user_code, CASE "+ 
              	    "    WHEN hos_code LIKE 'use%' THEN '일반회원' " +
       	            "    WHEN hos_code LIKE 'hos%' THEN '병원회원' " +
            		"END AS 회원구분, hos_enabled AS user_enabled, j_date " +
              	    "FROM hosjointbl) users " +
              	    "WHERE row_num BETWEEN (?-1)*60+(?-1)*6+1 AND (?-1)*60+(?)*6";

            	pstmt = conn.prepareStatement(query);
            	pstmt.setInt(1, section);
            	pstmt.setInt(2, pageNum);
            	pstmt.setInt(3, section);
            	pstmt.setInt(4, pageNum);


             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 // 조회된 회원 정보를 MemberlistVO 객체에 설정하여 리스트에 추가
                 String id = rs.getString("user_id");
                 String code = rs.getString("user_code");
                 String genre = rs.getString("회원구분");
                 String enabled = rs.getString("user_enabled");
                 Date j_date = rs.getDate("j_date");

                 MemberlistVO memberlistVO = new MemberlistVO();
                 memberlistVO.setId(id);
                 memberlistVO.setCode(code);
                 memberlistVO.setGenre(genre);
                 memberlistVO.setEnabled(enabled.equals("1") ? "Y" : "N"); // '1'이면 'Y', 그 외는 'N'
                 memberlistVO.setJ_date(j_date);
                 memberList.add(memberlistVO);
             }
             rs.close();
             pstmt.close();
             conn.close();
         } catch (Exception e) {
             System.out.println("병원ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ 목록 페이징 구현 중 에러 발생");
             e.printStackTrace();
         }
         return memberList;
     }
     
     // 병원 회원 수를 조회하는 메소드
     public int hosmembercount() {
         int member_count = 0;
         try {
             conn = dataFactory.getConnection();
             String query = "SELECT COUNT(*) AS count FROM (SELECT hos_id FROM hosjointbl) users";

             pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery();
             rs.next();
             member_count = rs.getInt("count");
             rs.close();
             pstmt.close();
             conn.close();
         } catch (Exception e) {
             System.out.println("회원 수 처리중 오류!");
             e.printStackTrace();
         }
         return member_count;
     }
}
