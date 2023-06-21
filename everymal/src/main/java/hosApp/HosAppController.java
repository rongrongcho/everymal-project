package hosApp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import forImg.HosImgDAO;
import forImg.HosImgVO;
import forImg.HosMainImgCon;
import forImg.HosThumController;
import hosMypageInfo.HosMypageInfoController;
import hosMypageInfo.HosMypageInfoDAO;
import hosMypageInfo.HosMypageInfoService;
import hosMypageInfo.HosMypageInfoVO;

@WebServlet("/app/*")
public class HosAppController extends HttpServlet {
	HosAppDAO appDAO;
	HosAppVO appVO;
	
	//===
	HosImgDAO hosImgDAO;
	HosImgVO hosImgVO;
	HosMainImgCon hosMainImgCon;
	HosThumController hosThumController;
	//===
	HosMypageInfoVO hosMypageInfoVO;
	HosMypageInfoController hosMypageInfoController;
	HosMypageInfoDAO hosMypageInfoDAO;
	HosMypageInfoService hosMypageInfoService;
	//===
	private static String IMG_REPO="C:\\choronglee\\imgRepo\\hos_images";
	private static String imgthem ="\\imgthem"; // 병원 이미지 모음  
	private static String thumbnail="\\thumbnail"; // 병원 썸네일 
	
	
	public void init(ServletConfig config) throws ServletException {
	
		appDAO =new HosAppDAO();
		appVO=new HosAppVO();
		hosMypageInfoController =new HosMypageInfoController();
		hosMypageInfoDAO =new HosMypageInfoDAO();
		hosMypageInfoVO =new HosMypageInfoVO();
		hosMypageInfoService=new HosMypageInfoService();

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
	      PrintWriter out = response.getWriter();
	      String nextPage=""; //원래는 "";
	      String action=request.getPathInfo();
	      HttpSession session=request.getSession();
    	  System.out.println("세션값:"+session.getAttribute("hos_code"));
	      System.out.println("요청이름 :" +action);
	      
	      try {
	    	  if(action==null || action.equals("/appForm.do")) {
				//h 회원 병원등록 신청 페이지 진입
	            String hos_code= (String)session.getAttribute("hos_code");
	      		System.out.println("세션값:"+session.getAttribute("hos_code"));
	      		appVO=appDAO.selectAppInfo(hos_code);
	            request.setAttribute("appVO", appVO);
	            nextPage="/hos_MypageInfo/hosApplication.jsp";

	    	  }else if(action.equals("")) {
	    		 //h 회원 병원 등록 신청 제출 
	    		Map<String, String> hosAppMap=uploadHosImg(request, response); //메소드 수행(폴더 생성기능 포함)
				
				String hos_code=hosAppMap.get("hos_code");
				String hos_id=hosAppMap.get("hod_id");

				String hos_name=hosAppMap.get("hos_name");
				String hos_himg1=hosAppMap.get("hos_himg1");
				String hos_himg2=hosAppMap.get("hos_himg2");
				String hos_himg3=hosAppMap.get("hos_himg3");		
//				----------------------------------------------
				appVO.setHimg1(hos_himg1);
				appVO.setHimg2(hos_himg2);
				appVO.setHimg3(hos_himg3);
				appVO.setHos_name(hos_name);

				appVO.setHos_id(hos_id);
				appVO.setHos_code(hos_code);
				
				//appDAO.modUserInfo(userVO);
      	    //======================================================
;
//      	    if(imageFileName !=null && imageFileName.length() !=0) {
//					//이미지를 첨부했다.
//					//원본 이미지 
//					String originalFileName=hosAppMap.get("originalFileName");
//					//변경할 이미지 
//					File srcFile=new File(IMG_REPO+"\\temp\\"+imageFileName);
//					File destDir=new File(IMG_REPO+"\\"+session.getAttribute("hos_code"));
//					destDir.mkdir();
//					//FileUtils.moveToDirectory(srcFile, destDir, true);
//					//원래 있던 파일 
//					File oldFile=new File(IMG_REPO+"\\"+session.getAttribute("hos_code")+"\\"+originalFileName);
//					// oldFile 삭제 
//					oldFile.delete();
//
//				}
//      	    out = response.getWriter();
//      	    if(session.getAttribute("log_id")!=null) {
//      	    out.print("<script>");
//      	    out.print("alert('글을 수정했습니다.');");
//      	    out.print("location.href='" + request.getContextPath() + "/userMypage/myInfo.do'");
//      	    out.print("</script>");}
//      	    return;
      	    }else if(action.equals("/viewMyApp.do")) {
                String hos_id= (String)session.getAttribute("log_id");
                hosMypageInfoVO=hosMypageInfoService.callhosInfo(hos_id);
                request.setAttribute("hosmypageinfoVO", hosMypageInfoVO);
      	    	//h회원 신청서 상세 조회하기 
    		  	String hos_code=request.getParameter("hos_code");
    		  	appVO=appDAO.selectAppInfo(hos_code);
	            request.setAttribute("appVO", appVO);
	            nextPage="/hos_MypageInfo/hosApplication.jsp";
      	    	    	
      	    }else if(action.equals("/checkApp.do")) {
	    		  //관리자의 병원등록 신청 개별 상세 보기 
    		  	String hos_code=request.getParameter("hos_code");
    		  	appDAO.checkHos(hos_code);
    		  	appVO=appDAO.selectAppInfo(hos_code);
	            request.setAttribute("appVO", appVO);
	            nextPage="/administrator/adminApplication.jsp";
      	    	
	    	  }else if(action.equals("/rejectionRM.do")) {
	    		  // 관리자의 병원등록 신청 거부 기능 
	    		Map<String, String> hosAppMap=uploadHosImg(request, response); 
				String rm_say=hosAppMap.get("rm_say");
    		  	String hos_code=appVO.getHos_code();
    		  	appDAO.rejectionHos(rm_say,hos_code);
    		  	out.print("<script>");
    		  	out.print("alert('병원 등록 승인 거부!');");
    		  	out.print("location.href='" + request.getContextPath() + "/app/viewApp.do?hos_code=" + hos_code + "';");
    		  	out.print("</script>");
	  			return;
	    	  }else if(action.equals("/")) {
	    		  //등록 승인  
	    	  }	  
	    	  
	    	  
	    	  
	      }catch (Exception e) {
	          System.out.println("HosReviewController : 요청 처리 작업 중 오류 발생");
	          e.printStackTrace();
	       }
			RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage); //받은 정보 nextPage(조건문별로 다르다)로 전달
			dispatcher.forward(request, response);
		
	}
	//이미지 파일 업로드 _ 새 글 관련 정보 저장 메서드 
	private Map<String,String> uploadHosImg (HttpServletRequest request, HttpServletResponse response)
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
