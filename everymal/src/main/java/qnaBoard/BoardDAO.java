package qnaBoard;

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

public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt; 
	private DataSource dataFactory;
	
	//생성자
	public BoardDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context) ctx.lookup("java:/comp/env");
			dataFactory=(DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("오라클 연결 오류");
			e.printStackTrace();  //
		}
	}
	//페이징 글 목록 메서드
	public List selectAllArticles(Map<String, Integer> pagingMap) {
		List<ArticleVO> articleList=new ArrayList<ArticleVO>();
		int section=pagingMap.get("section");
		int pageNum=pagingMap.get("pageNum");
		try {
			conn=dataFactory.getConnection();
//================쿼리 롱이가 수정 

			String query="SELECT * FROM "
					+ " (SELECT ROWNUM AS recNum, b.q_code, b.q_date, b.q_title, b.q_text, b.user_id, h.hos_name, b.a_title, b.a_text, b.a_date, b.petrace, h.hos_pro, h.hos_code, g.user_imgName, ROW_NUMBER() OVER (ORDER BY b.q_code DESC) AS row_num FROM boardtbl b left OUTER JOIN gmembertbl g ON b.user_id = g.user_id left OUTER JOIN hosjointbl h ON b.hos_id = h.hos_id)"
					+ " WHERE row_num BETWEEN (?-1)*50+(?-1)*5+1 AND (?-1)*50+?*5";
			
			
			

			//String query="SELECT * FROM(SELECT ROWNUM AS recNum, q_code, q_date, q_title, q_text, user_id, hos_name, a_title, a_text, a_date, petrace FROM(SELECT q_code, q_date, q_title, q_text, user_id, hos_name, a_title, a_text, a_date, petrace FROM boardtbl order by q_code)) WHERE recNum BETWEEN (?-1)*50+(?-1)*5+1 AND (?-1)*50+?*5";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs=pstmt.executeQuery();
			while (rs.next()) {
				int q_code=rs.getInt("q_code");
				String q_date=rs.getString("q_date");
				String q_title=rs.getString("q_title");
				String q_text=rs.getString("q_text");
				String user_id=rs.getString("user_id");
				String hos_name=rs.getString("hos_name");
				String a_title=rs.getString("a_title");
				String a_text=rs.getString("a_text");
				String petrace=rs.getString("petrace");
				String a_date=rs.getString("a_date");
				//이미지 ===============================
				String hos_code=rs.getString("hos_code");
				String hos_pro=rs.getString("hos_pro");
				String user_imgNmae=rs.getString("user_imgName");
				//롱이가 추가함===========================

				ArticleVO articleVO=new ArticleVO();
				articleVO.setQ_code(q_code);
				articleVO.setQ_date(q_date);
				articleVO.setQ_title(q_title);
				articleVO.setQ_text(q_text);
				articleVO.setUser_id(user_id);
				articleVO.setHos_name(hos_name);
				articleVO.setA_title(a_title);
				articleVO.setA_text(a_text);
				articleVO.setPetrace(petrace);
				articleVO.setA_date(a_date);
				//롱이가 추가함 =========================
				articleVO.setHos_code(hos_code);
				articleVO.setHos_pro(hos_pro);
				articleVO.setUser_imgName(user_imgNmae);
				
				
				articleList.add(articleVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 목록 페이징 구현중 에러 발생");
			e.printStackTrace();
		}
		return articleList;
	}
	
	//전체 글의 개수를 가져오는 메서드
	public int selectToArticles() {
		int totalCount=0;
		try {
			conn=dataFactory.getConnection();
			String query="select count(q_code) from boardtbl";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				totalCount=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("전체 글 가져오기 구현중 에러 발생");
			e.printStackTrace();
		}
		System.out.println(totalCount);
		return totalCount;
	}
	
	//글 번호 생성 메서드
	private int getNewArticleNo() {
		int aNo=1;
		try {
			conn=dataFactory.getConnection();
			String query="select max(q_code) as max_num from boardtbl";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) { 
				aNo=rs.getInt("max_num")+1; 
			}
			System.out.println(aNo);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 번호 생성중 에러");
			e.printStackTrace();
		}
		
		return aNo;
	}
	
	//새 글 추가 메서드
	public void insertNewArticle(ArticleVO articleVO) {
		
		int q_code=getNewArticleNo();
		try {
			conn=dataFactory.getConnection();
			String user_id=articleVO.getUser_id();
			String user_code=articleVO.getUser_code();
			String questionTitle=articleVO.getQ_title();
			String questionText=articleVO.getQ_text();
			String petRace=articleVO.getPetrace();
			
			String query="insert into boardtbl (q_code, user_id,user_code, Q_title, Q_text, petRace) values(?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, q_code);
			pstmt.setString(2, user_id);
			pstmt.setString(3, user_code);
			pstmt.setString(4, questionTitle);
			pstmt.setString(5, questionText);
			pstmt.setString(6, petRace);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("새 글 추가중 오류 발생");
			e.printStackTrace();
		}
		
	}

	
	//글 상세보기 메서드
	public ArticleVO selectArticle(int q_code) {
		ArticleVO article=new ArticleVO();
		try {
			conn=dataFactory.getConnection();
			String query="select * from boardtbl where q_code=?"; 
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, q_code);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int _q_code=rs.getInt("q_code"); 
			String q_title=rs.getString("q_title");
			String q_text=rs.getString("q_text");
			String petrace=rs.getString("petrace");
			String q_date=rs.getString("q_date");
			String user_id=rs.getString("user_id");
			String user_code=rs.getString("user_code");
			
			int a_code=rs.getInt("a_code");
			String a_title=rs.getString("a_title");
			String a_text=rs.getString("a_text");
			String hos_id=rs.getString("hos_id");
			String hos_name=rs.getString("hos_name");
			String a_date=rs.getString("a_date");
			
			
			article.setQ_code(_q_code);
			article.setQ_title(q_title);
			article.setQ_text(q_text);
			article.setPetrace(petrace);
			article.setQ_date(q_date);
			article.setUser_id(user_id);
			article.setUser_code(user_code);
			article.setA_code(a_code);
			article.setA_title(a_title);
			article.setA_text(a_text);
			article.setHos_id(hos_id);
			article.setHos_name(hos_name);
			article.setA_date(a_date);
			
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 상세 구현중 오류 발생");
			e.printStackTrace();
		}
		
		return article;
	}

	
	//글 수정하기 메서드
	public void updateArticle(ArticleVO articleVO) {
		int q_code=articleVO.getQ_code();
		String q_title=articleVO.getQ_title();
		String q_text=articleVO.getQ_text();
		String user_id=articleVO.getUser_id();
		try {
			conn=dataFactory.getConnection();
			String query="update boardtbl set q_title=?, q_text=? where q_code=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, q_title);
			pstmt.setString(2, q_text);
			pstmt.setInt(3, q_code);
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 수정 구현중 오류 발생");
			e.printStackTrace();
		}
	}
	//게시글 삭제 메서드
	public void deleteArticle(int q_code) {
		try {
			conn=dataFactory.getConnection();
			String query = "delete from boardtbl where q_code=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, q_code);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("게시글 삭제 구현중 오류 발생");
			e.printStackTrace();
		}
	}
	
	//답글을 삭제하는 메서드
		public void deleteReply(int q_code) {
			try {
				conn=dataFactory.getConnection();
				String query="update boardtbl set a_code=?, a_title=?, a_text=?, hos_name=?, a_date=null, hos_id=? where q_code=?";
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, null);
				pstmt.setString(2, null);
				pstmt.setString(3, null);
				pstmt.setString(4, null);
				pstmt.setString(5, null);
				pstmt.setInt(6, q_code);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.out.println("게시글 삭제 구현중 오류 발생");
				e.printStackTrace();
			}
		}



//답 글 추가 메서드
	public void insertNewReply(ArticleVO articleVO) {
		try {
			conn=dataFactory.getConnection();
			int q_code=articleVO.getQ_code();
			String hos_id=articleVO.getHos_id();
			String hos_name=articleVO.getHos_name();
			String a_title=articleVO.getA_title();
			String a_text=articleVO.getA_text();
			
			String query="update boardtbl set a_code=?, a_title=?, a_text=?, hos_name=?, a_date=sysdate, hos_id=? where q_code=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, q_code);
			pstmt.setString(2, a_title);
			pstmt.setString(3, a_text);
			pstmt.setString(4, hos_name);
			pstmt.setString(5, hos_id);
			pstmt.setInt(6, q_code);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("답글 추가중 오류 발생");
			e.printStackTrace();
		}
		
	}

}



















