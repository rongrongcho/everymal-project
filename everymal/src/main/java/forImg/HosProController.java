package forImg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userHistory.HistoryDAO;
import userHistory.HistoryVO;

@WebServlet("/hosProImgDown.do")
public class HosProController extends HttpServlet {
	private static String IMG_REPO="C:\\choronglee\\imgRepo\\hos_images";
	private static String profilefolder="\\profile"; //병원 프로필 

	private static final long serialVersionUID = 1L;
	HistoryDAO historyDAO;
	HistoryVO historyVO;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}
	
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String hos_code=request.getParameter("hos_code");
		System.out.println("발발이 치와와 " + hos_code);
		String hos_pro=request.getParameter("hos_pro");
		System.out.println("발발이 치와와 " + hos_pro);
		OutputStream outs=response.getOutputStream();
		String path=IMG_REPO+profilefolder+"\\"+hos_code+"\\"+hos_pro;
		File imageFile=new File(path);
		//이미지 파일 다운로드시 필요한 response 헤더 정보를 설정 
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-diposition", hos_pro);
		FileInputStream fis=new FileInputStream(imageFile);
		byte[] buffer = new byte[1024*8]; //버퍼를 이용해 한번에 8K 씩 전송
		while(true) {
			int count=fis.read(buffer);
			if(count == -1) break;
			outs.write(buffer,0,count); // buffer에 있는거 0부터 count 만큼
		}
		fis.close();
		outs.close();
	}


}
