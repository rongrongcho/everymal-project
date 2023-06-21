package hosReservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


public class ReservationDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private DataSource dataFactory;
	HttpSession session;
	
	public ReservationDAO(){
		try {
			Context ctx=new InitialContext();
	        Context envContext=(Context)ctx.lookup("java:/comp/env");
	        dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("커넥션풀 연결실패");
			e.printStackTrace();
		}
	}
	
	
	//반려동물 리스트
	public List<ReservationVO> animalList(String _user_code){
		List<ReservationVO> animalList = new ArrayList<>();
		
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM petinfotbl WHERE user_code=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _user_code);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ReservationVO reserv=new ReservationVO();
				

				
				reserv.setPet_name(rs.getString("pet_name"));
				reserv.setPet_age(rs.getInt("pet_age"));
				reserv.setPet_types(rs.getString("pet_types"));
				reserv.setPet_code(rs.getString("pet_code"));
				reserv.setPet_number(rs.getString("pet_number"));
				reserv.setPet_sex(rs.getString("pet_sex"));
				reserv.setB_type(rs.getString("b_type"));
				reserv.setPet_weight(rs.getFloat("pet_weight"));
								
				
				animalList.add(reserv);
				System.out.println("ㄴ이ㅓㄹ니ㅏ러 니ㅏ어리22222222222222 "+animalList);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DB 조회 중 에러!");
			e.printStackTrace();
		}
		return animalList;
	}
	

	
	//예약하기
	public void hosReserv(ReservationVO resVO) {
		try {
			conn=dataFactory.getConnection();
			String user_name=resVO.getUser_name();
			String user_tel=resVO.getUser_tel();
			String pet_name=resVO.getPet_name();
			String pet_types=resVO.getPet_types();
			int pet_age=(int)resVO.getPet_age();
			String pet_sex=resVO.getPet_sex();
			System.out.println("DAOpet_sex==============="+pet_sex);
			String user_code=resVO.getUser_code();
			String pet_number=resVO.getPet_number();
			String b_type=resVO.getB_type();
			System.out.println("DAOb_type==============="+b_type);
			float pet_weight=(float)resVO.getPet_weight();
			String res_date=resVO.getRes_date();
			String res_time=resVO.getRes_time();
			String hos_sub=resVO.getHos_sub();
			System.out.println("DAO진료과목==============="+hos_sub);
			String res_etc=resVO.getRes_etc();
			//병원 정보 그대로 불러와서 넣기(나중에 추가)
			String hos_name=resVO.getHos_name();
			System.out.println(resVO.getHos_name());
			String hos_code=resVO.getHos_code();
			System.out.println(resVO.getHos_code());
			String res_code=resVO.getRes_code();
			String pet_code=resVO.getPet_code();
			String user_id=resVO.getUser_id();

			
			String query="insert into hosrestbl(res_code, user_name, user_tel, pet_name, pet_age, pet_sex, hos_name, res_date,"
					+ " res_time, hos_sub, res_etc, hos_code, user_code, pet_types, pet_number, b_type, pet_weight, pet_code, user_id)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, res_code);
			pstmt.setString(2, user_name);
			pstmt.setString(3, user_tel);
			pstmt.setString(4, pet_name);
			pstmt.setInt(5, pet_age);
			pstmt.setString(6, pet_sex);
			pstmt.setString(7, hos_name);
			pstmt.setString(8, res_date);
			pstmt.setString(9, res_time);
			pstmt.setString(10, hos_sub);
			pstmt.setString(11, res_etc);
			pstmt.setString(12, hos_code);
			pstmt.setString(13, user_code);
			pstmt.setString(14, pet_types);
			pstmt.setString(15, pet_number);
			pstmt.setString(16, b_type);
			pstmt.setFloat(17, pet_weight);
			pstmt.setString(18, pet_code);
			pstmt.setString(19, user_id);
			
			pstmt.executeUpdate();
    		pstmt.close();
    		conn.close();
		} catch (Exception e) {
			System.out.println("예약 등록 중 ERROR");
			e.printStackTrace();
		}
	}

		//예약 코드 생성 
	    public String getNewReservCode() {
		       String code="";
		       try {
		           conn=dataFactory.getConnection();
		           String query="SELECT MAX(SUBSTR(res_code, 4)) AS max_num FROM hosrestbl";
		           pstmt=conn.prepareStatement(query);
		           ResultSet rs=pstmt.executeQuery();
		           if(rs.next()) {
		               int maxNum = rs.getInt("max_num") + 1;
		               code = String.format("res%04d", maxNum);
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
	  
}
