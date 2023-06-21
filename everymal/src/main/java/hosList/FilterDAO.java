package hosList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FilterDAO {
	private Connection conn;
	private PreparedStatement pstmt; 
	private DataSource dataFactory;
	
	//생성자
	public FilterDAO() {
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
	public List selectAllArticles(Map pagingMap) {
		List<FilterVO> hosList=new ArrayList<FilterVO>();
		int section=(int) pagingMap.get("section");
		int pageNum=(int) pagingMap.get("pageNum");
		FilterVO filter=(FilterVO) pagingMap.get("filterVO");
		try {
			conn=dataFactory.getConnection();
//			String query="SELECT * FROM(SELECT ROWNUM AS recNum, res.* FROM(select * from(select a.hos_code, count(reviewtbl.hos_code) as revnum from hosfiltertbl a left OUTER JOIN reviewtbl on reviewtbl.hos_code = a.hos_code GROUP BY a.hos_code) revt NATURAL JOIN (select * from hosfiltertbl NATURAL JOIN hosjointbl1)) res where 1=1";
			String query="SELECT * FROM(SELECT ROWNUM AS recNum, res.* FROM(SELECT * from hosfiltertbl NATURAL JOIN hosjointbl WHERE hos_status = '승인' order by hos_code) res where 1=1";
			if(filter.getHos_dog()!=null) {
				query+=" and hos_dog='1'";
			}
			if(filter.getHos_cat()!=null) {
				query+=" and hos_cat='1'";
			}
			if(filter.getHos_small()!=null) {
				query+=" and hos_small='1'";
			}
			if(filter.getHos_fish()!=null) {
				query+=" and hos_fish='1'";
			}
			if(filter.getHos_bird()!=null) {
				query+=" and hos_bird='1'";
			}
			if(filter.getHos_rep()!=null) {
				query+=" and hos_rep='1'";
			}
			if(filter.getHos_etc()!=null) {
				query+=" and hos_etc='1'";
			}
			if(filter.getHos_gs()!=null) {
				query+=" and hos_gs='1'";
			}
			if(filter.getHos_im()!=null) {
				query+=" and hos_im='1'";
			}
			if(filter.getHos_em()!=null) {
				query+=" and hos_em='1'";
			}
			if(filter.getHos_rm()!=null) {
				query+=" and hos_rm='1'";
			}
			if(filter.getHos_vac()!=null) {
				query+=" and hos_vac='1'";
			}
			if(filter.getHos_24()!=null) {
				query+=" and hos_24='1'";
			}
			if(filter.getHos_mon()!=null) {
				query+=" and hos_365 like '%mon%'";
			}
			if(filter.getHos_tue()!=null) {
				query+=" and hos_365 like '%tue%'";
			}
			if(filter.getHos_wed()!=null) {
				query+=" and hos_365 like '%wed%'";
			}
			if(filter.getHos_thu()!=null) {
				query+=" and hos_365 like '%thu%'";
			}
			if(filter.getHos_fri()!=null) {
				query+=" and hos_365 like '%fri%'";
			}
			if(filter.getHos_sat()!=null) {
				query+=" and hos_365 like '%sat%'";
			}
			if(filter.getHos_sun()!=null) {
				query+=" and hos_365 like '%sun%'";
			}
			if(filter.getHos_sch_name()!=null) {
				query+=" and hos_name like '%"+filter.getHos_sch_name()+"%'";
			}
			query+=") WHERE recNum BETWEEN (?-1)*12+1 AND ?*12";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, (section-1)*5+pageNum);
			pstmt.setInt(2, (section-1)*5+pageNum+12);
			ResultSet rs=pstmt.executeQuery();
			while (rs.next()) {
				FilterVO filterHos=new FilterVO();
				filterHos.setHos_code(rs.getString("hos_code"));
				filterHos.setHos_name(rs.getString("hos_name"));
				filterHos.setHos_tel(rs.getString("hos_tel"));
				filterHos.setHos_addr1(rs.getString("hos_addr1"));
				filterHos.setHos_addr2(rs.getString("hos_addr2"));
				filterHos.setHos_thum(rs.getString("hos_thum")); // 파일명 가져오기 
				hosList.add(filterHos);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 목록 페이징 구현중 에러 발생");
			e.printStackTrace();
		}
		return hosList;
	}
	
	//리뷰순 정렬 메서드
	public List selectRevAllArticles(Map pagingMap) {
		List<FilterVO> hosList=new ArrayList<FilterVO>();
		int section=(int) pagingMap.get("section");
		int pageNum=(int) pagingMap.get("pageNum");
		FilterVO filter=(FilterVO) pagingMap.get("filterVO");
		try {
			conn=dataFactory.getConnection();
			String query="SELECT * FROM(SELECT ROWNUM AS recNum, res.* FROM(select * from(select a.hos_code, count(reviewtbl.hos_code) as revnum from hosfiltertbl a left OUTER JOIN reviewtbl on reviewtbl.hos_code = a.hos_code GROUP BY a.hos_code) revt NATURAL JOIN (select * from hosfiltertbl NATURAL JOIN hosjointbl WHERE hos_status = '승인') ORDER BY revnum DESC) res where 1=1";
			if(filter.getHos_dog()!=null) {
				query+=" and hos_dog='1'";
			}
			if(filter.getHos_cat()!=null) {
				query+=" and hos_cat='1'";
			}
			if(filter.getHos_small()!=null) {
				query+=" and hos_small='1'";
			}
			if(filter.getHos_fish()!=null) {
				query+=" and hos_fish='1'";
			}
			if(filter.getHos_bird()!=null) {
				query+=" and hos_bird='1'";
			}
			if(filter.getHos_rep()!=null) {
				query+=" and hos_rep='1'";
			}
			if(filter.getHos_etc()!=null) {
				query+=" and hos_etc='1'";
			}
			if(filter.getHos_gs()!=null) {
				query+=" and hos_gs='1'";
			}
			if(filter.getHos_im()!=null) {
				query+=" and hos_im='1'";
			}
			if(filter.getHos_em()!=null) {
				query+=" and hos_em='1'";
			}
			if(filter.getHos_rm()!=null) {
				query+=" and hos_rm='1'";
			}
			if(filter.getHos_vac()!=null) {
				query+=" and hos_vac='1'";
			}
			if(filter.getHos_24()!=null) {
				query+=" and hos_24='1'";
			}
			if(filter.getHos_mon()!=null) {
				query+=" and hos_365 like '%mon%'";
			}
			if(filter.getHos_tue()!=null) {
				query+=" and hos_365 like '%tue%'";
			}
			if(filter.getHos_wed()!=null) {
				query+=" and hos_365 like '%wed%'";
			}
			if(filter.getHos_thu()!=null) {
				query+=" and hos_365 like '%thu%'";
			}
			if(filter.getHos_fri()!=null) {
				query+=" and hos_365 like '%fri%'";
			}
			if(filter.getHos_sat()!=null) {
				query+=" and hos_365 like '%sat%'";
			}
			if(filter.getHos_sun()!=null) {
				query+=" and hos_365 like '%sun%'";
			}
			if(filter.getHos_sch_name()!=null) {
				query+=" and hos_name like '%"+filter.getHos_sch_name()+"%'";
			}
			query+=") WHERE recNum BETWEEN (?-1)*12+1 AND ?*12 ORDER BY recNum";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, (section-1)*5+pageNum);
			pstmt.setInt(2, (section-1)*5+pageNum+12);
			ResultSet rs=pstmt.executeQuery();
			while (rs.next()) {
				FilterVO filterHos=new FilterVO();
				filterHos.setHos_code(rs.getString("hos_code"));
				filterHos.setHos_name(rs.getString("hos_name"));
				filterHos.setHos_tel(rs.getString("hos_tel"));
				filterHos.setHos_addr1(rs.getString("hos_addr1"));
				filterHos.setHos_addr2(rs.getString("hos_addr2"));
				filterHos.setHos_thum(rs.getString("hos_thum"));
				
				hosList.add(filterHos);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 목록 페이징 구현중 에러 발생");
			e.printStackTrace();
		}
		return hosList;
	}
	
	
	//전체 글의 개수를 가져오는 메서드
	public int selectToArticles(Map pagingMap) {
		FilterVO filter=(FilterVO) pagingMap.get("filterVO");
		int totalCount=0;
		try {
			conn=dataFactory.getConnection();
			String query = "SELECT COUNT(hos_code) FROM hosfiltertbl WHERE 1=1 AND hos_code IN (SELECT hos_code FROM hosjointbl WHERE hos_status = '승인')";

			if(filter.getHos_dog()!=null) {
				query+=" and hos_dog='1'";
			}
			if(filter.getHos_cat()!=null) {
				query+=" and hos_cat='1'";
			}
			if(filter.getHos_small()!=null) {
				query+=" and hos_small='1'";
			}
			if(filter.getHos_fish()!=null) {
				query+=" and hos_fish='1'";
			}
			if(filter.getHos_bird()!=null) {
				query+=" and hos_bird='1'";
			}
			if(filter.getHos_rep()!=null) {
				query+=" and hos_rep='1'";
			}
			if(filter.getHos_etc()!=null) {
				query+=" and hos_etc='1'";
			}
			if(filter.getHos_gs()!=null) {
				query+=" and hos_gs='1'";
			}
			if(filter.getHos_im()!=null) {
				query+=" and hos_im='1'";
			}
			if(filter.getHos_em()!=null) {
				query+=" and hos_em='1'";
			}
			if(filter.getHos_rm()!=null) {
				query+=" and hos_rm='1'";
			}
			if(filter.getHos_vac()!=null) {
				query+=" and hos_vac='1'";
			}
			if(filter.getHos_24()!=null) {
				query+=" and hos_24='1'";
			}
			if(filter.getHos_mon()!=null) {
				query+=" and hos_365 like '%mon%'";
			}
			if(filter.getHos_tue()!=null) {
				query+=" and hos_365 like '%tue%'";
			}
			if(filter.getHos_wed()!=null) {
				query+=" and hos_365 like '%wed%'";
			}
			if(filter.getHos_thu()!=null) {
				query+=" and hos_365 like '%thu%'";
			}
			if(filter.getHos_fri()!=null) {
				query+=" and hos_365 like '%fri%'";
			}
			if(filter.getHos_sat()!=null) {
				query+=" and hos_365 like '%sat%'";
			}
			if(filter.getHos_sun()!=null) {
				query+=" and hos_365 like '%sun%'";
			}
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
	
}



















