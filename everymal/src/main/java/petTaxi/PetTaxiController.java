package petTaxi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/petTaxiPage/*")
public class PetTaxiController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    PetTaxiVO petTaxiVO;
    PetTaxiDAO petTaxiDAO;
    PetTaxiService petTaxiService;

    public void init(ServletConfig config) throws ServletException {
        petTaxiVO = new PetTaxiVO();
        petTaxiDAO = new PetTaxiDAO();
        petTaxiService = new PetTaxiService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doHandle(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doHandle(request, response);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String nextPage = "";
		PrintWriter out;
        String action = request.getPathInfo();
        System.out.println("PTC 요청 이름 : " + action);
        HttpSession session = request.getSession();


        

        try {
            List<PetTaxiVO> taxiList = new ArrayList<PetTaxiVO>();
            if (action == null || action.equals("/")) {       	
                nextPage = "/pet_Taxi/petTaxiPage.jsp";
            } 
                    
            else if (action.equals("/pet_Taxi/taxiList.jsp")) {
                String tx_local = request.getParameter("tx_local");
                System.out.println("내 심장의 색깔을 블랙" + tx_local);
                taxiList = petTaxiDAO.taxiList(petTaxiVO, tx_local);
                request.setAttribute("taxiList", taxiList);
                nextPage="/pet_Taxi/taxiList.jsp"; // 팝업 페이지로 이동         
            }else if(action.equals("/reservPetTaxi.do")) {

                	String log_id=(String)session.getAttribute("log_id");
                	
                if(log_id==null) {
            	    out=response.getWriter();
        		    out.print("<script>");
        			out.print("alert('로그인 먼저해'); ");
        			out.print("location.href='" +request.getContextPath() + "/user_History/myPetTaxiReserv.jsp';");
        		    out.print("</script>");
        		    return;
          
                	
                }else {
            		String tx_res_code=petTaxiDAO.getNewTxrCode();
            		String user_code=request.getParameter("user_code");
            		String tx_tel=request.getParameter("tx_tel");
            		String tx_number=request.getParameter("tx_number");
            		String tx_name=request.getParameter("tx_name");
            		String tx_dep=request.getParameter("tx_dep");
            		String tx_arr=request.getParameter("tx_arr");
            		String res_name=request.getParameter("res_name");
            		String tx_code=request.getParameter("tx_code");            		
            		String tx_restime=request.getParameter("tx_restime");
            		String tx_reserv_day=request.getParameter("tx_reserv_day");
//======================
            		petTaxiVO = new PetTaxiVO();
            		petTaxiVO.setTx_res_code(tx_res_code);
            	    petTaxiVO.setUser_code(user_code);
            	    petTaxiVO.setTx_tel(tx_tel);
            	    petTaxiVO.setTx_number(tx_number);
            	    petTaxiVO.setTx_name(tx_name);
            	    petTaxiVO.setTx_dep(tx_dep);
            	    petTaxiVO.setTx_arr(tx_arr);
            	    petTaxiVO.setRes_name(res_name);
            	    petTaxiVO.setTx_code(tx_code);
            	    petTaxiVO.setTx_restime(tx_restime);
            	    petTaxiVO.setTx_reserv_day(tx_reserv_day);
            	    petTaxiDAO.taxiReserv(petTaxiVO);
            	    out=response.getWriter();
        		    out.print("<script>");
        			out.print("alert('펫 택시 예약을 완료했습니다.마이페이지로 이동합니다.'); ");
        			out.print("location.href='" +request.getContextPath() + "/history/myPetTaxiReserv.do';");
        		    out.print("</script>");
        		    return;
                }	

                   
            	
            	    
            	
            	
            	
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("PetTaxiController : 요청 처리 중 에러");
            e.printStackTrace();
        }
    }
}
