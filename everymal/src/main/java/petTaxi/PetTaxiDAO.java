package petTaxi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PetTaxiDAO {
	private Connection con; 
	private PreparedStatement pstmt; 
	private DataSource dataFactory ;
	
	//	생성자 필요 
	public PetTaxiDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("커넥션풀 연결실패"); 
			e.printStackTrace();
		}
		
		
	}
	
	//출발지 택시 리스트 
	
	
	public List<PetTaxiVO> taxiList(PetTaxiVO petTaxiVO, String _tx_local) {
		System.out.println("그새끼보다 못한게 먼데 "+_tx_local);
	    List<PetTaxiVO> taxiList=new ArrayList<PetTaxiVO>();

	    try {
	        con=dataFactory.getConnection();
	        String query="SELECT * from taxiinfotbl where tx_local LIKE '%' || ? || '%'";
	        pstmt=con.prepareStatement(query);
	        pstmt.setString(1,_tx_local);
	        ResultSet rs=pstmt.executeQuery();
	        while(rs.next()) {
	            String tx_tel=rs.getString("tx_tel");
	            String tx_number=rs.getString("tx_number");
	            String tx_name=rs.getString("tx_name");
	            String tx_local=rs.getString("tx_local");
	            String tx_code=rs.getString("tx_code");
	            String tx_img=rs.getString("tx_img");
	            String tx_intro=rs.getString("tx_intro");

	            PetTaxiVO taxiVO=new PetTaxiVO();
	            taxiVO.setTx_code(tx_code);
	            taxiVO.setTx_tel(tx_tel);
	            taxiVO.setTx_local(tx_local);
	            taxiVO.setTx_name(tx_name);
	            taxiVO.setTx_number(tx_number);
	            taxiVO.setTx_img(tx_img);
	            taxiVO.setTx_intro(tx_intro);
	            taxiList.add(taxiVO);			 
	        }
	        rs.close();
	        pstmt.close();
	        con.close();
	    } catch (Exception e) {
	        System.out.println("택시 조회중 에러  ");
	        e.printStackTrace();
	    }
	    return taxiList;
	}
	
	public void taxiReserv(PetTaxiVO petTaxiVO) {
		try {
			con=dataFactory.getConnection();
			String tx_res_code=petTaxiVO.getTx_res_code();
    		String user_code=petTaxiVO.getUser_code();
    		String tx_tel=petTaxiVO.getTx_tel();
    		String tx_number=petTaxiVO.getTx_number();
    		String tx_name=petTaxiVO.getTx_name();
    		String tx_dep=petTaxiVO.getTx_dep();
    		String tx_arr=petTaxiVO.getTx_arr();
    		String res_name=petTaxiVO.getRes_name();
    		String tx_code=petTaxiVO.getTx_code();         		
    		String tx_restime=petTaxiVO.getTx_restime();
    		String tx_reserv_day=petTaxiVO.getTx_reserv_day();
    		String query="insert into taxirestbl"
    				+ "(tx_res_code,user_code,tx_tel,tx_number,tx_name "
    				+ " ,tx_dep,tx_arr,res_name,tx_code,tx_restime,tx_reserv_day )"
					+ " values(?,?,?,?,?,?,?,?,?,?,?)";
    		
			pstmt=con.prepareStatement(query);
			pstmt.setString(1 ,tx_res_code);
			pstmt.setString(2, user_code);
			pstmt.setString(3, tx_tel);
			pstmt.setString(4, tx_number);
			pstmt.setString(5, tx_name);
			pstmt.setString(6, tx_dep);
			pstmt.setString(7, tx_arr);
			pstmt.setString(8, res_name);
			pstmt.setString(9, tx_code);
			pstmt.setString(10, tx_restime);
			pstmt.setString(11, tx_reserv_day);
			pstmt.executeUpdate();
    		pstmt.close();
    		con.close();
    		
    		
    		
		} catch (Exception e) {
			System.out.println(" 펫 택시 예약 접수 중 오류");
			e.printStackTrace();
		}
		
	}
	

	
	public String getNewTxrCode() {
	    String code="";
	    try {
	        con=dataFactory.getConnection();
	        String query="SELECT MAX(SUBSTR(tx_res_code, 4)) AS max_num FROM taxirestbl";
	        pstmt=con.prepareStatement(query);
	        ResultSet rs=pstmt.executeQuery();
	        if(rs.next()) {
	            int maxNum = rs.getInt("max_num") + 1;
	            code = String.format("txr%04d", maxNum);
	        }
	        System.out.println(code);
	        rs.close();
	        pstmt.close();
	        con.close();
	    } catch (Exception e) {
	        System.out.println("코드 생성중 에러");
	        e.printStackTrace();
	    }

	    return code;
	}
	
	




}
