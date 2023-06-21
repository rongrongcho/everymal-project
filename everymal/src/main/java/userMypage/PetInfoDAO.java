package userMypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PetInfoDAO {
	private Connection conn;
	private PreparedStatement pstmt; 
	private DataSource dataFactory;
	
	public PetInfoDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("오라클 연결 오류");
			e.printStackTrace();
		}
	}
	
	//팻목록 불러오기 메서드
	public List<PetInfoVO> selectPetList(String user_code) {
		List<PetInfoVO> petList=new ArrayList<PetInfoVO>();
		try {
			conn=dataFactory.getConnection();
			String query="select * from petinfotbl where user_code=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, user_code);
			ResultSet rs=pstmt.executeQuery();
			while (rs.next()) {
				String pet_code=rs.getString("pet_code");
				String pet_name=rs.getString("pet_name");
				int pet_age=rs.getInt("pet_age");
				String pet_sex=rs.getString("pet_sex");
				String pet_types=rs.getString("pet_types");
				String pet_number=rs.getString("pet_number");
				String b_type=rs.getString("b_type");
				Float pet_weight=rs.getFloat("pet_weight");
				String pet_etc=rs.getString("pet_etc");
				String _user_code=rs.getString("user_code");
				
				PetInfoVO petInfoVO=new PetInfoVO(pet_code, pet_name, pet_age, pet_sex, pet_types, pet_number, b_type, pet_weight, pet_etc, _user_code);
				petList.add(petInfoVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("팻목록 DB연결중 오류 발생!");
			e.printStackTrace();
		}
		
		return petList;
	}
	
//	팻 수정하기
	public void updatePet(PetInfoVO petInfoVO) {
		try {
			conn=dataFactory.getConnection();
			String query="update petinfotbl set pet_name=?, pet_age=?, pet_sex=?, pet_types=?, pet_number=?, b_type=?, pet_weight=?, pet_etc=? where pet_code=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, petInfoVO.getPet_name());
			pstmt.setInt(2, petInfoVO.getPet_age());
			pstmt.setString(3, petInfoVO.getPet_sex());
			pstmt.setString(4, petInfoVO.getPet_types());
			pstmt.setString(5, petInfoVO.getPet_number());
			pstmt.setString(6, petInfoVO.getB_type());
			pstmt.setFloat(7, petInfoVO.getPet_weight());
			pstmt.setString(8, petInfoVO.getPet_etc());
			pstmt.setString(9, petInfoVO.getPet_code());
			System.out.println(petInfoVO.getPet_code());
			System.out.println(petInfoVO.getPet_name());
			pstmt.executeQuery();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("팻정보 수정중 오류 발생!");
			e.printStackTrace();
		}
	}
	
	//펫 삭제하기
	public void removePetInfo(String pet_code) {
		try {
			conn=dataFactory.getConnection();
			String query="delete from petinfotbl where pet_code=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pet_code);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("펫 삭제중 오류 발생!");
			e.printStackTrace();
		}

	}
	
	//펫 추가하기
	public void insertPet(PetInfoVO petInfoVO) {
		try {
			conn=dataFactory.getConnection();
			String query="insert into petinfotbl values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt=conn.prepareStatement(query);
			String pet_code=getPetCode();
			pstmt.setString(1, pet_code);
			System.out.println(getPetCode());
			System.out.println(pet_code);
			pstmt.setString(2, petInfoVO.getPet_name());
			pstmt.setInt(3, petInfoVO.getPet_age());
			pstmt.setString(4, petInfoVO.getPet_sex());
			pstmt.setString(5, petInfoVO.getPet_types());
			pstmt.setString(6, petInfoVO.getPet_number());
			pstmt.setString(7, petInfoVO.getB_type());
			pstmt.setFloat(8, petInfoVO.getPet_weight());
			pstmt.setString(9, petInfoVO.getPet_etc());
			pstmt.setString(10, petInfoVO.getUser_code());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("펫 추가중 오류 발생!");
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
