package hosList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/hosfilter/*")
public class HosfilterController extends HttpServlet {
	FilterService filterService;
	FilterVO filterVO;

	
	@Override
	public void init() throws ServletException {
		filterService = new FilterService();
		filterVO=new FilterVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet호출");
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost호출");
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		String nextPage="";
		PrintWriter out;
		HttpSession session;
		String action=request.getPathInfo();
		System.out.println("요청 이름 : "+action);
		try {
			List<FilterVO> filterList=new ArrayList<FilterVO>();
			if (action==null || action.equals("/listHos.do")) {
				String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section=Integer.parseInt((_section==null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
				Map pagingMap=new HashMap<>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				
				filterVO.setHos_dog(request.getParameter("hos_dog"));
				filterVO.setHos_cat(request.getParameter("hos_cat"));
				filterVO.setHos_small(request.getParameter("hos_small"));
				filterVO.setHos_fish(request.getParameter("hos_fish"));
				filterVO.setHos_bird(request.getParameter("hos_bird"));
				filterVO.setHos_rep(request.getParameter("hos_rep"));
				filterVO.setHos_etc(request.getParameter("hos_etc"));
				filterVO.setHos_gs(request.getParameter("hos_gs"));
				filterVO.setHos_im(request.getParameter("hos_im"));
				filterVO.setHos_em(request.getParameter("hos_em"));
				filterVO.setHos_rm(request.getParameter("hos_rm"));
				filterVO.setHos_vac(request.getParameter("hos_vac"));
				filterVO.setHos_24(request.getParameter("hos_24"));
				filterVO.setHos_mon(request.getParameter("hos_mon"));
				filterVO.setHos_tue(request.getParameter("hos_tue"));
				filterVO.setHos_wed(request.getParameter("hos_wed"));
				filterVO.setHos_thu(request.getParameter("hos_thu"));
				filterVO.setHos_fri(request.getParameter("hos_fri"));
				filterVO.setHos_sat(request.getParameter("hos_sat"));
				filterVO.setHos_sun(request.getParameter("hos_sun"));
				filterVO.setHos_sch_name(request.getParameter("hos_sch_name"));
				
				pagingMap.put("filterVO", filterVO);
				boolean orderby=Boolean.parseBoolean(request.getParameter("orderby"));
				Map hosMap=new HashMap<>();
				System.out.println(orderby);
				if(orderby) {
					hosMap=filterService.revListArticles(pagingMap);
				}else {
					hosMap=filterService.listArticles(pagingMap);
				}
				hosMap.put("section", section);
				hosMap.put("pageNum", pageNum);
				hosMap.put("filterchk", filterVO);
				hosMap.put("orderby", orderby);
				request.setAttribute("hosMap", hosMap);
				
				//===============================================
				


				nextPage="/hos_List/hos_filter.jsp";
			}
			
			RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("요청 처리중 에러");
			e.printStackTrace();
		}
	}
	
}
