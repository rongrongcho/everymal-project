package qnaBoard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {

	}
	
	//파일 경로 설정
	private static String iMG_REPO="C:\\hrise\\board\\image_upload";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		String imageFileName=request.getParameter("imageFileName");
		String articleNo=request.getParameter("articleNo");
		//파일을 읽는건 인풋스프림 보내는건 아웃풋스트림
		OutputStream outs=response.getOutputStream();
		String path=iMG_REPO+"\\"+articleNo+"\\"+imageFileName; //저장된 경로 찾기
		File imageFile=new File(path);
		response.setHeader("Cache-Control", "no-cache");//헤더에 셋팅
		//이미지 파일을 내려받는데 필요한 response 헤더 정보 설정
		imageFileName=URLEncoder.encode(imageFileName,"utf-8");
		response.addHeader("Content-disposition", "attachment;fileName="+imageFileName);
		//이미지 폴더 안에 파일을 받아오는게 인풋 스트림의 역할
		FileInputStream fis=new FileInputStream(imageFile);
		//화면에 보여주는게 아웃풋 스트림
		byte[] buffer=new byte[1024*8]; //버퍼를 이용해 한번에 8k씩 전송
		while (true) {
			//인풋 스트림이 버퍼에 넣어서 아웃풋이 순차적 전송
			int count=fis.read(buffer);
			//읽을게 없으면 중지
			if(count==-1)break;
			outs.write(buffer,0,count);
		}
		fis.close();
		outs.close();
	}

}
