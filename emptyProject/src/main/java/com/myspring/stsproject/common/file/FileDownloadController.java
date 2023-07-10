package com.myspring.stsproject.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {
	private static String IMAGE_REPO_PATH="C:\\hrise\\stsproject2\\image_repo";
	
//	@RequestMapping("/download")
//	public void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse response) throws Exception{
//		OutputStream out=response.getOutputStream();
//		String downFile=IMAGE_REPO_PATH+"\\"+imageFileName;
//		File file=new File(downFile);//다운로드할 파일 객체를 생성
//		response.setHeader("Cache-Control", "no-cache");
//		//헤더에 파일 이름을 설정
//		response.addHeader("Content-disposition", "attachment;fileName="+imageFileName);
//		FileInputStream in=new FileInputStream(file);
//		byte[] buffer=new byte[1024*8];
//		while(true) {
//			int count = in.read(buffer);
//			if(count==-1) {
//				break;
//			}
//			out.write(buffer,0,count);
//		}
//		in.close();
//		out.close();
//	}
	
	
//	썸네일 라이브러리 활용
	@RequestMapping("/download")
	public void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse response) throws Exception{
		OutputStream out=response.getOutputStream();
		String downFile=IMAGE_REPO_PATH+"\\"+imageFileName;
		File file=new File(downFile);//다운로드할 파일 객체를 생성
		
		
//		썸네일 폴더를 만들어서 썸네일 이미지를 브라우저로 전송하는 방법
		
//		int lastIndex = imageFileName.lastIndexOf(".");
//		//확장자를 제외한 파일 이름 저장
//		String fileName=imageFileName.substring(0, lastIndex);
////		썸네일을 저장할 경로 설정
//		File thumnail=new File(IMAGE_REPO_PATH+"\\thumnail\\"+fileName+".png");
//		if(file.exists()) {
//			thumnail.getParentFile().mkdirs();
////			썸네일의 크기지정 후 thumnail에 png로 저장
//			Thumbnails.of(file).size(50, 50).outputFormat("png").toFile(thumnail);
//		}
////		response.setHeader("Cache-Control", "no-cache");
////		//헤더에 파일 이름을 설정
////		response.addHeader("Content-disposition", "attachment;fileName="+imageFileName);
//		FileInputStream in=new FileInputStream(thumnail);
//		byte[] buffer=new byte[1024*8];
//		while(true) {
//			int count = in.read(buffer);
//			if(count==-1) {
//				break;
//			}
//			out.write(buffer,0,count);
//		}
//		in.close();
//		out.close();
		
//		썸네일 이미지 파일을 서버에 따로 생성하지 않고 바로 브라우저에 다운로드 기능으로 처리
		if(file.exists()) {
			Thumbnails.of(file).size(50, 50).outputFormat("png").toOutputStream(out);
		}else {
			return;
		}
		byte[] buffer=new byte[1024*8];
		out.write(buffer);
		out.close();
	}
}
