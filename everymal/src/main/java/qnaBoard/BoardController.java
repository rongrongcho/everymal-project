package qnaBoard;

import java.io.File;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	BoardService boardService;
	ArticleVO articleVO;
	private static String iMG_REPO="C:\\hrise\\board\\image_upload";
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
		articleVO=new ArticleVO();
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
			List<ArticleVO> articleList=new ArrayList<ArticleVO>();
			if (action==null || action.equals("/listArticles.do")) {
				String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section=Integer.parseInt((_section==null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum==null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map articleMap=boardService.listArticles(pagingMap);
				articleMap.put("section", section);
				articleMap.put("pageNum", pageNum);
				request.setAttribute("articleMap", articleMap);
				nextPage="/qna_Board/qnaboardMain.jsp";
				
				
			}else if(action.equals("/articleForm.do")) {
				nextPage="/qna_Board/qnaboardWrite.jsp";
			}
			else if(action.equals("/addArticle.do")) {
				Map<String, String> articleMap=upload(request, response);
				String questionTitle=articleMap.get("questionTitle");
				String petRace=articleMap.get("petRace");
				String questionText=articleMap.get("questionText");
				session = request.getSession();
				articleVO.setUser_id((String)session.getAttribute("log_id"));
				//====추가 
				articleVO.setUser_code((String)session.getAttribute("user_code"));
				//======
				articleVO.setQ_title(questionTitle);
				articleVO.setQ_text(questionText);
				articleVO.setPetrace(petRace);

				boardService.addArticle(articleVO);
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('새 글을 추가했습니다');");
				out.print("location.href='"+request.getContextPath()+"/board/listArticles.do';");
				out.print("</script>");
				return;
			}
			else if(action.equals("/viewArticle.do")) { 
				String q_code=request.getParameter("q_code"); 
				articleVO = boardService.viewArticle(Integer.parseInt(q_code));
				request.setAttribute("article", articleVO);
				nextPage="/qna_Board/qnaboard.jsp";
			}
			else if(action.equals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				int q_code=Integer.parseInt(articleMap.get("q_code"));
				String questionTitle=articleMap.get("questionTitle");
				String questionText=articleMap.get("questionText");
				session = request.getSession();

				articleVO.setQ_code(q_code);
				articleVO.setQ_title(questionTitle);
				articleVO.setQ_text(questionText);
				articleVO.setUser_id((String)session.getAttribute("log_id"));
				
				boardService.modArticle(articleVO);
				
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('글을 수정했습니다');");
				out.print("location.href='"+request.getContextPath()+"/board/viewArticle.do?q_code="+q_code+"';");
				out.print("</script>");
				return;
			}
			else if(action.equals("/modReply.do")) {
				Map<String, String> articleMap=upload(request, response);
				int q_code=Integer.parseInt(articleMap.get("q_code"));
				String answerTitle=articleMap.get("answerTitle");
				String answerText=articleMap.get("answerText");
				session = request.getSession();
				String hos_name=(String)session.getAttribute("hos_name");
				articleVO.setQ_code(q_code);
				articleVO.setHos_id((String)session.getAttribute("log_id"));
				articleVO.setA_title(answerTitle);
				articleVO.setA_text(answerText);
				articleVO.setHos_name(hos_name);
				boardService.addReply(articleVO);
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('답변을 수정했습니다');");
				out.print("location.href='"+request.getContextPath()+"/board/viewArticle.do?q_code="+q_code+"';");
				out.print("</script>");
				return;
			}
			else if(action.equals("/removeArticle.do")) {
				int q_code=Integer.parseInt(request.getParameter("q_code"));
				System.out.println("삭제수행");
				boardService.removeArticle(q_code);
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('글을 삭제했습니다');");
				out.print("location.href='"+request.getContextPath()+"/board/listArticles.do';");
				out.print("</script>");
				return;
			}
			else if(action.equals("/removeReply.do")) {
				int q_code=Integer.parseInt(request.getParameter("q_code"));
				System.out.println("삭제수행");
				boardService.removeReply(q_code);
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('답변을 삭제했습니다');");
				out.print("location.href='"+request.getContextPath()+"/board/listArticles.do';");
				out.print("</script>");
				return;
			}
			else if (action.equals("/addReply.do")) {
				Map<String, String> articleMap=upload(request, response);
				int q_code=Integer.parseInt(articleMap.get("q_code"));
				String answerTitle=articleMap.get("answerTitle");
				String answerText=articleMap.get("answerText");
				session = request.getSession();
				String hos_name=(String) session.getAttribute("hos_name");
				articleVO.setQ_code(q_code);
				articleVO.setHos_id((String)session.getAttribute("log_id"));
				articleVO.setA_title(answerTitle);
				articleVO.setA_text(answerText);
				articleVO.setHos_name(hos_name);
				boardService.addReply(articleVO);
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('답변을 추가했습니다');");
				out.print("location.href='"+request.getContextPath()+"/board/viewArticle.do?q_code="+q_code+"';");
				out.print("</script>");
				return;
			}
			
			RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("요청 처리중 에러");
			e.printStackTrace();
		}
	}
	
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, String> articleMap= new HashMap<String, String>();
		String encoding = "utf-8";
		File currentDirPath=new File(iMG_REPO); 
		DiskFileItemFactory factory = new DiskFileItemFactory(); 
		factory.setRepository(currentDirPath); 
		factory.setSizeThreshold(1024*1024); 
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			List items=upload.parseRequest(request); 
			for(int i=0;i<items.size();i++) {
				FileItem fileItem = (FileItem) items.get(i); //
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName()+"="+fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					System.out.println("파라미터 이름 : " + fileItem.getFieldName());
					System.out.println("파일이름 : "+fileItem.getName());
					System.out.println("이미지 크기: "+fileItem.getSize()+"bytes");
					if (fileItem.getSize()>0) {
						int idx=fileItem.getName().lastIndexOf("\\");
						if(idx==-1) {
							idx=fileItem.getName().lastIndexOf("/");
						}
						String fileName=fileItem.getName().substring(idx+1);
						articleMap.put(fileItem.getFieldName(), fileName);
						File uploadFile = new File(currentDirPath + "\\temp\\" +fileName);
						fileItem.write(uploadFile);
					}
					
				}
			}
		} catch (Exception e) {
			System.out.println("파일 업로드중 에러");
			e.printStackTrace();
		}
		return articleMap;
	}
}
