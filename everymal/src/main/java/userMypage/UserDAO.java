package userMypage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



public class UserDAO {
	//=========================================ConnectionPool
	private Connection con; 
	private PreparedStatement pstmt; 
	private DataSource dataFactory ;
	HttpSession session;
	
	//	생성자 필요 
	public UserDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource) envContext.lookup("jdbc/oracle");
					
		}catch(Exception e) {
			System.out.println("커넥션풀 연결실패"); // xml 확인
			e.printStackTrace();
		}
		
		
	}
	
//==============================================================
	
	//=========마이페이지 접속 전 비밀번호 확인 
	public boolean isValid(UserVO userVO) {
		boolean result=false;
		String user_id =userVO.getUser_id();
		String user_pwd=userVO.getUser_pwd();
		String password=null; 
		try {
			con=null;
			con=dataFactory.getConnection();
			String query="select user_pwd from Gmembertbl where user_id=? and user_pwd=?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pwd);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				password=rs.getString("user_pwd");
				 if (password == null) {
				        System.out.println("도대체 값이 있는데 왜 받지를 못하니?");
				    }
			}
			if(password != null && password.equals(user_pwd)) {
				result=true;
			   
			}
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("비밀번호 유효성 검사 중 에러 발생");
			e.printStackTrace();
		}

		
		//result=true;
		System.out.println("메서드 수행 결과 =========="+result);
		return result;
	}
	
	
	

//==========정보 불러오기 
	//회원 정보 불러오기
		public UserVO selectUserInfo(String _user_id) {
		      UserVO userInfo=new UserVO();
		      try {
		         con=dataFactory.getConnection(); 
		         String query="SELECT *"
		         		+ " FROM GMemberTbl WHERE user_id=?";
		         pstmt=con.prepareStatement(query);
		         pstmt.setString(1, _user_id);
		         ResultSet rs=pstmt.executeQuery();
		         if(rs.next()) {
		        	 userInfo.setUser_code(rs.getString("user_code"));
		        	 userInfo.setUser_id(rs.getString("user_id"));
		        	 userInfo.setUser_pwd(rs.getString("user_pwd"));
		        	 userInfo.setUser_email(rs.getString("user_email"));
		        	 userInfo.setUser_name(rs.getString("user_name"));
		        	 userInfo.setUser_addr1(rs.getString("user_addr1"));
		        	 userInfo.setUser_addr2(rs.getString("user_addr2"));
		        	 userInfo.setUser_zipcode(rs.getString("user_zipcode"));
		        	 userInfo.setUser_tel(rs.getString("user_tel"));
		 			 String imageFileName = rs.getString("user_imgName");
		 			 userInfo.setUser_imgName(imageFileName); // 프로필 사진 파일명
		       
		         }

		         rs.close();
		         pstmt.close();
		         con.close();
		      } catch (Exception e) {
		         System.out.println("UserDAO : selectUserInfo 메서드 처리 중 에러 ");
		         e.printStackTrace();
		      }      
		      return userInfo;
		      
		   }
	
	
//==========회원 정보 수정 
		
		public void updateUserInfo(UserVO userVO) {
			String imageFileName=userVO.getUser_imgName();
			System.out.println("이미지파일네임 :" + imageFileName);
			String user_id=userVO.getUser_id();

		    try {		    	
		    	con=dataFactory.getConnection();
				String query="update GmemberTbl set user_pwd=?,user_tel=?"
						+ " ,user_email=?,user_addr1=?,user_addr2=?,user_zipcode=?";
				//이미지는 유무에 따라 쿼리 달리함 
				if(imageFileName !=null && imageFileName.length() !=0) {
					query+=", user_imgName=? where user_id=?";
				}else {
					query+=" where user_id=?";
				}
				pstmt=con.prepareStatement(query);
		        pstmt.setString(1, userVO.getUser_pwd());
		        pstmt.setString(2, userVO.getUser_tel());
		        pstmt.setString(3, userVO.getUser_email());
		        pstmt.setString(4, userVO.getUser_addr1());
		        pstmt.setString(5, userVO.getUser_addr2());
		        pstmt.setString(6, userVO.getUser_zipcode());
				if(imageFileName != null && imageFileName.length() != 0) {
					pstmt.setString(7, imageFileName);
					pstmt.setString(8,userVO.getUser_id());
				}else {
					pstmt.setString(7, userVO.getUser_id());
				}		  
		        pstmt.executeUpdate();
		        pstmt.close();
		        con.close();
		    } 
	         catch (Exception e) {
		    	System.out.println("회원정보 수정 업데이트 중 오류 발생");
		        e.printStackTrace(); 
		    }
		}

				
		
//================나의 리뷰 목록 조회 (+페이징뷰)
		public List selectAllReview(Map<String, Integer> pagingMap, String user_id) {
		    List<UserVO> reviewList = new ArrayList<UserVO>();
		    int section = pagingMap.get("section");
		    int pageNum = pagingMap.get("pageNum");

		    try {
		        con = dataFactory.getConnection();
		        String query = "SELECT * FROM (SELECT ROWNUM AS row_num, reviewtbl.* FROM "
		                + " (SELECT * FROM reviewtbl "
		                + "  WHERE user_id = ? ORDER BY rv_code) reviewtbl)"
		                + "  WHERE row_num BETWEEN (?-1)*80+(?-1)*8+1 AND (?-1)*80+(?)*8";
			    pstmt = con.prepareStatement(query);
			    pstmt.setString(1, user_id);
			    pstmt.setInt(2, section);
			    pstmt.setInt(3, pageNum);
			    pstmt.setInt(4, section);
			    pstmt.setInt(5, pageNum);
		        
		        ResultSet rs = pstmt.executeQuery();
		        while (rs.next()) {
		            String rv_code = rs.getString("rv_code");
		            String rv_title = rs.getString("rv_title");
		            String rv_text = rs.getString("rv_text");
		            int rv_rate = rs.getInt("rv_rate");
		            Date rv_date = rs.getDate("rv_date");

		            //=====================================

		            UserVO userVO = new UserVO();
		            userVO.setRv_code(rv_code);
		            userVO.setRv_title(rv_title);
		            userVO.setRv_text(rv_text);
		            userVO.setRv_rate(rv_rate);
		            userVO.setRv_date(rv_date);
		            reviewList.add(userVO);
		        }
		        rs.close();
		        pstmt.close();
		        con.close();
		    } catch (Exception e) {
		        System.out.println("글 목록 페이징 구현중 에러 발생");
		        e.printStackTrace();
		    }
		    return reviewList;
		}
		
		

		

		
//================나의 리뷰 개수 
		
	   
	     public int reviewcount(String user_id) {
	        int review_count=0;
	        try {
	         con=dataFactory.getConnection();
	         String query="select count(*)as count from reviewtbl where user_id=?";
	         pstmt=con.prepareStatement(query);
	          pstmt.setString(1, user_id);
	          ResultSet rs=pstmt.executeQuery();
	          rs.next();
	          review_count=rs.getInt("count");
	          rs.close();
	          pstmt.close();
	          con.close();
	      } catch (Exception e) {
	         System.out.println("예약 개수 처리중 오류!");
	         e.printStackTrace();
	      }
	        return review_count;
	     }
	     
	     
//===============선택 리뷰 삭제 메서드
	     
	     
	     public void requestDelRev(String[] items) {
	    	 
	    	 String[] rv_code=items;
	    	 System.out.println("dsdsadsadsadsad"+rv_code[0]);
	    	 try {
	    		 
	    		 if(rv_code==null || rv_code.length==0) {
	    			 System.out.println("삭제할 리뷰 미선택");
	    		 }
	    		 else {
	 				for(int i=0; i<rv_code.length; i++) {
						con=dataFactory.getConnection();
						String query="delete from reviewtbl where rv_code=?";
						pstmt=con.prepareStatement(query);
						pstmt.setString(1, rv_code[i]);
						pstmt.executeUpdate();
						pstmt.close();
						con.close();
					}
	    		 }

			} catch (Exception e) {
				System.out.println("리뷰 선택 삭제 도중 오류 ");
				e.printStackTrace();
			}
			
		}
	     
	     
	     
	     
	     

//================상세 리뷰 보기 selectReview
		public UserVO selectReview(String rv_code) {
			UserVO userVO=new UserVO();
			try {
				con=dataFactory.getConnection();
		        String query="select * from reviewtbl where rv_code=?";
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, rv_code); 
				ResultSet rs=pstmt.executeQuery();
				rs.next();
				String rv_title=rs.getString("rv_title");
				String rv_text=rs.getString("rv_text");
				Date rv_date=rs.getDate("rv_date");
				int rv_rate=rs.getInt("rv_rate");
				//=====================================
				userVO.setRv_title(rv_title);
				userVO.setRv_text(rv_text);
				userVO.setRv_date(rv_date);
				userVO.setRv_rate(rv_rate);
				userVO.setRv_code(rv_code);
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				System.out.println("리뷰 상세 보기 구현 중 에러");
				e.printStackTrace();
			}
			
			return userVO;
		}
		
		
		
		
		public void updateUserMyreview(UserVO userVO) {


		    try {		    	
		    	con=dataFactory.getConnection();
				String query="update reviewtbl set rv_title=?,rv_text=?"
						+ "  ,rv_rate=?"
						+ " where user_id=? and rv_code=?";
				pstmt=con.prepareStatement(query);
		        pstmt.setString(1, userVO.getRv_title());
		        pstmt.setString(2, userVO.getRv_text());
		        pstmt.setInt(3, userVO.getRv_rate());
		        pstmt.setString(4, userVO.getUser_id());
		        pstmt.setString(5, userVO.getRv_code());
		        pstmt.executeUpdate();
		        pstmt.close();
		        con.close();
		    } 
	         catch (Exception e) {
		    	System.out.println("회원정보 수정 업데이트 중 오류 발생");
		        e.printStackTrace(); 
		    }
		}
		
		
		
		
	     
//질문 =================================================================
		//================나의 질문 목록 조회 (페이징뷰)
		
		public List selectAllQ(Map<String, Integer> pagingMap, String user_code) {
		    List<UserVO> qList = new ArrayList<UserVO>();
		    int section = pagingMap.get("section");
		    int pageNum = pagingMap.get("pageNum");

		    try {
		        con = dataFactory.getConnection();
		        String query = "SELECT * FROM (SELECT ROWNUM AS row_num, boardtbl.* FROM "
		                + " (SELECT * FROM boardtbl "
		                + "  WHERE user_code = ? ORDER BY q_code) boardtbl)"
		                + "  WHERE row_num BETWEEN (?-1)*80+(?-1)*8+1 AND (?-1)*80+(?)*8";
			    pstmt = con.prepareStatement(query);
			    pstmt.setString(1, user_code);
			    pstmt.setInt(2, section);
			    pstmt.setInt(3, pageNum);
			    pstmt.setInt(4, section);
			    pstmt.setInt(5, pageNum);
		        
		        ResultSet rs = pstmt.executeQuery();
		        while (rs.next()) {
					String q_code=rs.getString("q_code"); 
					String q_title=rs.getString ("q_title");
					String q_text=rs.getString("q_text");
					String a_code=rs.getString("a_code");
					Date q_date=rs.getDate("q_date");			
					
					//=====================================
					
					UserVO userVO= new UserVO();
					userVO.setQ_code(q_code);
					userVO.setQ_date(q_date);
					userVO.setQ_title(q_title);
					userVO.setQ_text(q_text);
					if(a_code==null) {
						userVO.setIsAnswer("미답변");
					}else {
						userVO.setIsAnswer("답변완료");
					}
					qList.add(userVO);
		        }
		        rs.close();
		        pstmt.close();
		        con.close();
		    } catch (Exception e) {
		        System.out.println("글 목록 페이징 구현중 에러 발생");
		        e.printStackTrace();
		    }
		    return qList;
		}
		
				
		
	     public void requestDelQus(String[] items) {
	    	 
	    	 String[] qv_code=items;
	    	 System.out.println("dsdsadsadsadsad"+qv_code[0]);
	    	 try {
	    		 
	    		 if(qv_code==null || qv_code.length==0) {
	    			 System.out.println("삭제할 리뷰 미선택!");
	    		 }
	    		 else {
	 				for(int i=0; i<qv_code.length; i++) {
						con=dataFactory.getConnection();
						String query="delete from boardtbl where q_code=?";
						pstmt=con.prepareStatement(query);
						pstmt.setString(1, qv_code[i]);
						pstmt.executeUpdate();
						pstmt.close();
						con.close();
					}
	    		 }

			} catch (Exception e) {
				System.out.println("리뷰 선택 삭제 도중 오류 ");
				e.printStackTrace();
			}
			
		}
		
		
		
	//================나의 질문 개수 
		
	   
	     public int qCount(String user_code) {
	        int q_count=0;
	        try {
	         con=dataFactory.getConnection();
	         String query="select count(*)as count from boardtbl where user_code=?";
	         pstmt=con.prepareStatement(query);
	          pstmt.setString(1, user_code);
	          ResultSet rs=pstmt.executeQuery();
	          rs.next();
	          q_count=rs.getInt("count");
	          rs.close();
	          pstmt.close();
	          con.close();
	      } catch (Exception e) {
	         System.out.println("예약 개수 처리중 오류!");
	         e.printStackTrace();
	      }
	        return q_count;
	     }
			     

}
