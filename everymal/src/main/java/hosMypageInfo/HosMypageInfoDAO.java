package hosMypageInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class HosMypageInfoDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private DataSource dataFactory;
	HttpSession session;
	
	public HosMypageInfoDAO(){
		try {
			Context ctx=new InitialContext();
	        Context envContext=(Context)ctx.lookup("java:/comp/env");
	        dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("커넥션풀 연결실패");
			e.printStackTrace();
		}
	}
	
	
	//비밀번호 확인 후 들어가기 
	public boolean isValid(HosMypageInfoVO hosmypageinfoVO){
		boolean result=false;
		String hos_id=hosmypageinfoVO.getHos_id();
		String hos_pwd=hosmypageinfoVO.getHos_pwd();
		String password=null;
		try {
			conn=dataFactory.getConnection();
			String query="select hos_pwd from hosjointbl where hos_id=? and hos_pwd=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, hos_id);
			pstmt.setString(2, hos_pwd);
			ResultSet rs=pstmt.executeQuery();
	        if(rs.next()) {
	        	password=rs.getString("hos_pwd");
	        }
	        if(password!=null&&password.equals(hos_pwd)) {
        		result=true;
	        }
	        rs.close();
	        pstmt.close();
	        conn.close();
		} catch (Exception e) {
			System.out.println("비밀번호 확인 중 ERROR");
			e.printStackTrace();
		}
		return result;
	}
	
	//회원 정보 불러오기
	public HosMypageInfoVO selecthosInfo(String _hos_id) {
	      HosMypageInfoVO hosInfo=new HosMypageInfoVO();
	      try {
	         conn=dataFactory.getConnection(); 
	         String query="select * from hosjointbl j, hosfiltertbl f where j.hos_code=f.hos_code and j.hos_id=?";
	         pstmt=conn.prepareStatement(query);
	         pstmt.setString(1, _hos_id);
	         ResultSet rs=pstmt.executeQuery();
	         if(rs.next()) {
	        	 hosInfo.setHos_username(rs.getString("hos_username"));
	        	 hosInfo.setHos_id(rs.getString("hos_id"));
	        	 hosInfo.setHos_pwd(rs.getString("hos_pwd"));
	        	 hosInfo.setHos_usertel(rs.getString("hos_usertel")); //전화번호 수정: check
	        	 hosInfo.setHos_dno(rs.getLong("hos_dno"));
	        	 hosInfo.setHos_email(rs.getString("hos_email"));  //이메일 형식 맞도록 수정 
	        	 //=======================롱 추가 
	        	 hosInfo.setHos_status(rs.getString("hos_status"));
	        	 
	        	 hosInfo.setHos_zipcode(rs.getString("hos_zipcode"));
	        	 hosInfo.setHos_addr1(rs.getString("hos_addr1"));
	        	 hosInfo.setHos_addr2(rs.getString("hos_addr2"));
	        	 
	        	 hosInfo.setHos_intro(rs.getString("hos_intro"));
	        	 hosInfo.setHos_name(rs.getString("hos_name"));
	        	 hosInfo.setHos_365(rs.getString("hos_365"));
	        	 hosInfo.setHos_time_s(rs.getString("hos_time_s"));
	        	 hosInfo.setHos_time_e(rs.getString("hos_time_e"));
	        	 hosInfo.setHos_24(rs.getString("hos_24"));
	        	 hosInfo.setHos_tel(rs.getString("hos_tel"));
	        	 hosInfo.setHos_dog(rs.getString("hos_dog"));
	        	 hosInfo.setHos_cat(rs.getString("hos_cat"));
	        	 hosInfo.setHos_small(rs.getString("hos_small"));
	        	 hosInfo.setHos_fish(rs.getString("hos_fish"));
	        	 hosInfo.setHos_bird(rs.getString("hos_bird"));
	        	 hosInfo.setHos_rep(rs.getString("hos_rep"));
	        	 hosInfo.setHos_etc(rs.getString("hos_etc"));
	        	 hosInfo.setHos_gs(rs.getString("hos_gs"));
	        	 hosInfo.setHos_im(rs.getString("hos_im"));
	        	 hosInfo.setHos_em(rs.getString("hos_em"));
	        	 hosInfo.setHos_rm(rs.getString("hos_rm"));
	        	 hosInfo.setHos_vac(rs.getString("hos_vac"));
	         }
	         rs.close();
	         pstmt.close();
	         conn.close();
	      } catch (Exception e) {
	         System.out.println("UserDAO : selectUserInfo 메서드 처리 중 에러 ");
	         e.printStackTrace();
	      }      
	      return hosInfo;
	      
	   }
	//병원 정보 수정
	public void modhosInfo(HosMypageInfoVO _hosmypageinfoVO) {
		try {
			conn=dataFactory.getConnection();
			String query="update hosjointbl set hos_username=?, hos_pwd=?, hos_usertel=?, hos_dno=?, hos_email=? where hos_id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, _hosmypageinfoVO.getHos_username());
			pstmt.setString(2, _hosmypageinfoVO.getHos_pwd());
			pstmt.setString(3, _hosmypageinfoVO.getUsertel_front()+_hosmypageinfoVO.getUsertel_end());
			pstmt.setLong(4, _hosmypageinfoVO.getHos_dno());
			pstmt.setString(5, _hosmypageinfoVO.getHos_email());
			pstmt.setString(6, _hosmypageinfoVO.getHos_id());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("ERRRORRRR");
			e.printStackTrace();
		}
		
	}
	
	//병원 정보 수정
	public void modmyHosInfo(HosMypageInfoVO _hosmypageinfoVO) {
	    	//getter이용해 값 가져오기
//			HosMypageInfoVO hosInfo=new HosMypageInfoVO();
	    	try {
	    		conn=dataFactory.getConnection();
	    		String query="update hosjointbl set hos_name=?, hos_tel=?, hos_intro=?, hos_zipcode=?, hos_addr1=?, hos_addr2=? where hos_id=?";
	    		pstmt=conn.prepareStatement(query);
	    		pstmt.setString(1, _hosmypageinfoVO.getHos_name());
	    		pstmt.setString(2, _hosmypageinfoVO.getTel_front()+_hosmypageinfoVO.getTel_end());
	    		pstmt.setString(3, _hosmypageinfoVO.getHos_intro());
	    		pstmt.setString(4, _hosmypageinfoVO.getHos_zipcode());
	    		pstmt.setString(5, _hosmypageinfoVO.getHos_addr1());
	    		pstmt.setString(6, _hosmypageinfoVO.getHos_addr2());
	    		pstmt.setString(7, _hosmypageinfoVO.getHos_id());
	    		pstmt.executeUpdate();
	    		
	    		String query2="update hosfiltertbl set hos_dog=?, hos_cat=?, hos_small=?, hos_fish=?, hos_bird=?, hos_rep=?, hos_etc=?, hos_365=?, hos_gs=?, hos_im=?, hos_em=?, hos_rm=?, hos_vac=?, hos_24=?, hos_time_s=?, hos_time_e=? where hos_code=?";
	    		pstmt2=conn.prepareStatement(query2);
	    		pstmt2.setString(1, _hosmypageinfoVO.getHos_dog());
	    		pstmt2.setString(2, _hosmypageinfoVO.getHos_cat());
	    		pstmt2.setString(3, _hosmypageinfoVO.getHos_small());
	    		pstmt2.setString(4, _hosmypageinfoVO.getHos_fish());
	    		pstmt2.setString(5, _hosmypageinfoVO.getHos_bird());
	    		pstmt2.setString(6, _hosmypageinfoVO.getHos_rep());
	    		pstmt2.setString(7, _hosmypageinfoVO.getHos_etc());
	    		pstmt2.setString(8, _hosmypageinfoVO.getHos_365());
	    		pstmt2.setString(9, _hosmypageinfoVO.getHos_gs());
	    		pstmt2.setString(10, _hosmypageinfoVO.getHos_im());
	    		pstmt2.setString(11, _hosmypageinfoVO.getHos_em());
	    		pstmt2.setString(12, _hosmypageinfoVO.getHos_rm());
	    		pstmt2.setString(13, _hosmypageinfoVO.getHos_vac());
	    		pstmt2.setString(14, _hosmypageinfoVO.getHos_24());
	    		pstmt2.setString(15, _hosmypageinfoVO.getHos_time_s());
	    		pstmt2.setString(16, _hosmypageinfoVO.getHos_time_e());
	    		pstmt2.setString(17, _hosmypageinfoVO.getHos_code());
	    		pstmt2.executeUpdate();
	    		
	    		pstmt.close();
	    		pstmt2.close();
	    		conn.close();
	    	} catch(Exception e) {
	    		System.out.println("회원정보 수정 중 ERROR");
	    		e.printStackTrace();
	    	}
	    }
}
