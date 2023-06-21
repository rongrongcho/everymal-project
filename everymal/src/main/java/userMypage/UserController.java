package userMypage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet("/userMypage/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO;
	UserVO userVO;
	UserService userService;

	private static String IMG_REPO="C:\\choronglee\\imgRepo\\user_profil";
	
	public void init(ServletConfig config) throws ServletException {
	   userDAO= new UserDAO(); //시작할때 객체 만들었기 때문에, doHandle에서 그냥 사용만 해도 가능
	   userVO= new UserVO();
	   userService= new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String nextPage=null; 
		String action=request.getPathInfo(); 
		System.out.println("UserC 요청매핑이름: "+action);
		HttpSession session=request.getSession();
		System.out.println("세션값:"+session.getAttribute("log_id"));
		List<UserVO> reviewList=new ArrayList<UserVO>();
		List<UserVO> qList=new ArrayList<UserVO>();
		if(action==null||action.equals("/user_Page/isValidPwd.jsp")) {
			String user_id=request.getParameter("user_id");
			userVO= new UserVO();
			userVO.setUser_id(user_id);
	        userVO=userService.calluserInfo(user_id); //회원 정보 불러옴 
	        request.setAttribute("userVO", userVO);
			
			nextPage="/user_Page/isValidPwd.jsp";

		}else if(action.equals("/isValid.do")) {

			String user_id=request.getParameter("user_id");
			String user_pwd=request.getParameter("user_pwd");
			userVO= new UserVO();
			userVO.setUser_id(user_id);
			userVO.setUser_pwd(user_pwd);	
			Boolean result=userDAO.isValid(userVO);		
			if(result) {
				session.setAttribute("isLogon", true);
				session.setAttribute("isLogon", true);
		        String _user_id= (String)session.getAttribute("log_id");
		        userVO=userService.calluserInfo(_user_id); //회원 정보 불러옴 
		        request.setAttribute("userVO", userVO);
		        nextPage= "/user_Page/myInfo.jsp";

			}else {
				out.print("<script>alert('비밀번호를 다시 확인해주세요.');window.history.back();</script>");

			}
		}else if(action.equals("/myInfo.do")) {   
			//로그인한 회원 정보 불러오기
		System.out.println("제발용" + userVO.getUser_imgName());
           String user_id= (String)session.getAttribute("log_id");
           userVO=userService.calluserInfo(user_id);
           request.setAttribute("userVO", userVO);
           nextPage= "/user_Page/myInfo.jsp"; // 횐 정 불러오기 끝 !
           }else if(action.equals("/modInfo.do") ) {
				//회원 정보 수정 반영================================================

				Map<String, String> userInfoMap=uploadProImg(request, response); //메소드 수행(폴더 생성기능 포함)
				String user_id = (String) session.getAttribute("log_id");
				String user_pwd=userInfoMap.get("user_pwd");
				String user_name = userInfoMap.get("user_name");
				String user_tel1 = userInfoMap.get("usertel_front");
				String user_tel2 = userInfoMap.get("usertel_end");
				String user_addr1=userInfoMap.get("user_addr1");
				String user_addr2=userInfoMap.get("user_addr2");
				String user_zipcode=userInfoMap.get("user_zipcode");
				String user_email=userInfoMap.get("user_email");
				String imageFileName=userInfoMap.get("user_imgName"); // 프로필 이미지 파일
				System.out.println("그게뭔데"+imageFileName);
//				----------------------------------------------
				userVO.setUser_id(user_id);
				userVO.setUser_pwd(user_pwd);
				userVO.setUser_tel(user_tel1+user_tel2);
				userVO.setUser_email(user_email);
				userVO.setUser_addr1(user_addr1);
				userVO.setUser_addr2(user_addr2);
				userVO.setUser_zipcode(user_zipcode); 
				userVO.setUser_imgName(imageFileName);
        	    userService.modUserInfo(userVO);
        	    //======================================================
        	  System.out.println("야!!!!!!!"+userVO.getUser_imgName());
        	  System.out.println("originalFileName:" +userInfoMap.get("originalFileName"));
        	    if(imageFileName !=null && imageFileName.length() !=0) {
					//이미지를 첨부했다.
					//원본 이미지 
					String originalFileName=userInfoMap.get("originalFileName");
					//변경할 이미지 
					File srcFile=new File(IMG_REPO+"\\temp\\"+imageFileName);
					File destDir=new File(IMG_REPO+"\\"+session.getAttribute("log_id"));
					destDir.mkdir();
					FileUtils.moveToDirectory(srcFile, destDir, true);
					//원래 있던 파일 
					File oldFile=new File(IMG_REPO+"\\"+session.getAttribute("log_id")+"\\"+originalFileName);
					// oldFile 삭제 
					oldFile.delete();

				}
        	    out = response.getWriter();
        	    if(session.getAttribute("log_id")!=null) {
        	    out.print("<script>");
        	    out.print("alert('글을 수정했습니다.');");
        	    out.print("location.href='" + request.getContextPath() + "/userMypage/myInfo.do'");
        	    out.print("</script>");}
        	    return;
			
	//리뷰 ================================================================		
			}else if(action.equals("/myReview.do")) {

	            String user_id= (String)session.getAttribute("log_id");
	            //회원 이미지 프사 ===============================
	            userVO=userService.calluserInfo(user_id);
	            request.setAttribute("userVO", userVO);
	            //===========================================
			    String _section=request.getParameter("section");
	            String _pageNum=request.getParameter("pageNum");
	            int section=Integer.parseInt((_section==null)?"1":_section); 
	            int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
	            Map<String, Integer> pagingMap=new HashMap<String, Integer>(); 
	            pagingMap.put("section", section);
	            pagingMap.put("pageNum", pageNum);
	            Map reviewMap=userService.listReviews(pagingMap,user_id);
	            reviewMap.put("section", section);
	            reviewMap.put("pageNum", pageNum);
	            request.setAttribute("reviewMap", reviewMap);
	            System.out.println("거기 있느냐???"+reviewMap);
		        int review_count=userService.reviewCount(user_id);
	           request.setAttribute("review_count", review_count);
				nextPage="/user_Page/myReview.jsp";
			}else if(action.equals("/viewReview.do")) {
				//리뷰 상세 창 요청 
				
				String rv_code=request.getParameter("rv_code");
				userVO=userService.viewReview(rv_code);
				request.setAttribute("hosReview", userVO);
				nextPage="/user_Page/userViewReview.jsp";
				
				
				
			}else if(action.equals("/modMyReview.do")) {
				//리뷰 상세 수정 
				String user_id= (String)session.getAttribute("log_id");
				String rv_code=request.getParameter("rv_code");
				System.out.println("어디갓니? 나의 작고 귀여운 아기 코드 " + rv_code);
				String rv_title=request.getParameter("rv_title");				
				String rv_text=request.getParameter("rv_text");
				String rv_rate=request.getParameter("rv_rate");
				System.out.println("얼마냐 인");
				//================================================
				userVO.setUser_id(user_id);
				userVO.setRv_code(rv_code);
				userVO.setRv_title(rv_title);
				userVO.setRv_text(rv_text);
				userVO.setRv_rate(Integer.parseInt(rv_rate));
				userService.modUserMyReview(userVO);
        	    out = response.getWriter();
        	    if(session.getAttribute("log_id")!=null) {
        	    out.print("<script>");
        	    out.print("alert('리뷰를 수정했습니다.');");
        	    out.print("location.href='" + request.getContextPath() + "/userMypage/viewReview.do?rv_code="+rv_code+"'");
        	    out.print("</script>");}
        	    return;
			}
			
			
			
			else if(action.equals("/myRevDel.do")) {
				String[] items=request.getParameterValues("del_chk");
				if(items==null){
        			out.print("<script>");
        			out.print("alert('삭제요청 할 리뷰를 선택해주세요!');");
        			out.print("location.href='" +request.getContextPath()+ "/userMypage/myReview.do" + "';");
        			out.print("</script>");
        			return;
        		}else {
        			
        			for(int i=0; i<items.length; i++) {
            			System.out.println( "items : " + items[i]);
            		}
        			userService.reqDelReview(items);
        			out.print("<script>");
        			out.print("alert('삭제 성공!');");
        			out.print("location.href='" +request.getContextPath() + "/userMypage/myReview.do" + "';");
        			out.print("</script>");
        			return;
        		}
			}else if(action.equals("/returnMyReview.do")) {
				
        	    out.print("<script>");
        	    out.print("location.href='" + request.getContextPath() + "/userMypage/myReview.do'");
        	    out.print("</script>");
        	    return;
			
				
				
				
				
	//질문 ======================================================================			
			}else if(action.equals("/myQuestion.do")) {
				String user_code=(String)session.getAttribute("user_code");
				//===========회원 마이 페이지 프사 
				String user_id= (String)session.getAttribute("log_id");
				userVO=userService.calluserInfo(user_id);
	            request.setAttribute("userVO", userVO);
				//===========================================
			    String _section=request.getParameter("section");
	            String _pageNum=request.getParameter("pageNum");
	            int section=Integer.parseInt((_section==null)?"1":_section); 
	            int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
	            Map<String, Integer> pagingMap=new HashMap<String, Integer>(); 
	            pagingMap.put("section", section);
	            pagingMap.put("pageNum", pageNum);
	            Map qMap=userService.listQuestions(pagingMap,user_code);
	            qMap.put("section", section);
	            qMap.put("pageNum", pageNum);
	            request.setAttribute("qMap", qMap);
		        int q_count=userService.qCount(user_code);
	            request.setAttribute("q_count", q_count);
	            nextPage="/user_Page/myQuestion.jsp";			
			}else if(action.equals("/myQusDel.do")) {
				String[] items=request.getParameterValues("del_chk");
				if(items==null){
        			out.print("<script>");
        			out.print("alert('삭제요청 할 질문글을 선택해주세요!');");
        			out.print("location.href='" +request.getContextPath()+ "/userMypage/myQuestion.do" + "';");
        			out.print("</script>");
        			return;
        		}else {
        			
        			for(int i=0; i<items.length; i++) {
            			System.out.println( "items : " + items[i]);
            		}
        			userService.reqDelQuestion(items);
        			out.print("<script>");
        			out.print("alert('삭제 성공!');");
        			out.print("location.href='" +request.getContextPath() + "/userMypage/myQuestion.do" + "';");
        			out.print("</script>");
        			return;
        		}
			}

		
		//=====포워딩
		RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage); //받은 정보 nextPage(조건문별로 다르다)로 전달
		dispatcher.forward(request, response);
	}
	
	
	
	
	
	//이미지 파일 업로드 _ 새 글 관련 정보 저장 메서드 
	private Map<String,String> uploadProImg (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		Map<String, String> userInfoMap=new HashMap<String,String>();
		String encoding="utf-8"; 
		File currentDirPath=new File(IMG_REPO); // File 변수 상수로 만들어 놓은 업로드할 경로 // 글 이미지 저장 
		DiskFileItemFactory factory=new DiskFileItemFactory(); //저장과 관련된 클래스 
		factory.setRepository(currentDirPath); // 저장소 세팅 
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload =new ServletFileUpload(factory);
		try {
			//클라이언트가 글쓰기 해서 날라온 것 
			List items = upload.parseRequest(request); //클라이언트에게 응답
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem=(FileItem)items.get(i);
				if(fileItem.isFormField()) {
					//폼을 통해 들어오는 텍스트 데이터
					System.out.println(fileItem.getFieldName() + "="
							+ fileItem.getString(encoding));
					userInfoMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					//폼 필드가 아닌 이미지 파일인 경우
					//매개변수 이름 imageFileName
					System.out.println("파라미터 이름 : " +fileItem.getFieldName());
					//실제 파일 이름 
					System.out.println("파일(이미지) 이름 : " + fileItem.getName());
					//이미지 크기 
					System.out.println("파일(이미지) 크기 : " + fileItem.getSize()+"bytes");
					if(fileItem.getSize() >0) {
						int idx=fileItem.getName().lastIndexOf("\\");
						if(idx==-1) {
							//이미지 이름을 못 찾은 경우 
							idx=fileItem.getName().lastIndexOf("/");
						}
						//순수 파일 이름
						String fileName=fileItem.getName().substring(idx+1);
						userInfoMap.put(fileItem.getFieldName(), fileName);
						File uploadFile= new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
					}
				}
				
				
			}
		}catch (Exception e) {
			System.out.println("파일 업로드 중 에러 발생");
			e.printStackTrace();		
		}
		return userInfoMap;		
		
	}

}
