package forImg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HosImgDAO {
	

	//=========================================ConnectionPool
	private Connection con; 
	private PreparedStatement pstmt; 
	private DataSource dataFactory ;

	//	생성자 필요 
	public HosImgDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("커넥션풀 연결실패"); 
			e.printStackTrace();
		}
		
		
	}
	
	
			
//	==============================================================병원 메인 이미지
	
	
	public HosImgVO getHosImg(String hos_code) {
    		HosImgVO hosImgVO=new HosImgVO();
		
	       try {

	        con=dataFactory.getConnection();
	        String query="select * from hosimgtbl where hos_code=?";
	        pstmt=con.prepareStatement(query);
	        pstmt.setString(1, hos_code);
	        ResultSet rs=pstmt.executeQuery();
	        rs.next();
	        String img_code=rs.getString("img_code");
	        String himg1=rs.getString("himg1");
	        String himg2=rs.getString("himg2");
	        String himg3=rs.getString("himg3");
	        hosImgVO.setImg_code(img_code);
	        hosImgVO.setHimg1(himg1);
	        hosImgVO.setHimg2(himg2);
	        hosImgVO.setHimg3(himg3);
	        
	        rs.close();
	        pstmt.close();
	        con.close();
	        
	        
	     
	     } catch (Exception e) {
	        System.out.println("병원 이미지 가져오기 중 에러!");
	        e.printStackTrace();
	     }
	       return hosImgVO;
		
		
	}
	
	public HosImgVO getHosPro(String hos_code) {
		HosImgVO hosImgVO=new HosImgVO();
	
       try {

        con=dataFactory.getConnection();
        String query="select hos_pro from hosjointbl where hos_code=?";
        pstmt=con.prepareStatement(query);
        pstmt.setString(1, hos_code);
        ResultSet rs=pstmt.executeQuery();
        rs.next();

        String hos_pro=rs.getString("hos_pro");
        hosImgVO.setHos_pro(hos_pro);
       


        rs.close();
        pstmt.close();
        con.close();
        
        
     
     } catch (Exception e) {
        System.out.println("병원 이미지 가져오기 중 에러!");
        e.printStackTrace();
     }
       return hosImgVO;
	
	
}
	
	
	
}
