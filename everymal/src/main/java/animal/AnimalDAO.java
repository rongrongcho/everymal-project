package animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AnimalDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public AnimalDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("오라클 연결 오류");
			e.printStackTrace();
		}
	}
	public void addAnimal(AnimalVO animalVO) {
		String code = getPetCode();
		try {
			conn = dataFactory.getConnection();
			String user_code = animalVO.getUser_code();
			String pet_name = animalVO.getPet_name();
			int pet_age = animalVO.getPet_age();
			String pet_sex = animalVO.getPet_sex();
			String pet_types = animalVO.getPet_types();
			String pet_number = animalVO.getPet_number();
			String b_type = animalVO.getB_type();
			float pet_weight = animalVO.getPet_weight();
			String pet_etc = animalVO.getPet_etc();
			String query = "insert into petinfotbl (pet_code, user_code, pet_name, pet_age, pet_sex, pet_types, pet_number, b_type, pet_weight, pet_etc) values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, code);
			pstmt.setString(2, user_code);
			pstmt.setString(3, pet_name);
			pstmt.setInt(4, pet_age);
			pstmt.setString(5, pet_sex);
			pstmt.setString(6, pet_types);
			pstmt.setString(7, pet_number);
			pstmt.setString(8, b_type);
			pstmt.setFloat(9, pet_weight);
			pstmt.setString(10, pet_etc);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DB 등록 중 에러!!");
			e.printStackTrace();
		}
	}
	
	public String getPetCode() {
	       String code="";
	       try {
	           conn=dataFactory.getConnection();
	           String query="SELECT MAX(SUBSTR(pet_code, 4)) AS max_num FROM petinfotbl";
	           pstmt=conn.prepareStatement(query);
	           ResultSet rs=pstmt.executeQuery();
	           if(rs.next()) {
	               int maxNum = rs.getInt("max_num") + 1;
	               code = String.format("pet%04d", maxNum);
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
