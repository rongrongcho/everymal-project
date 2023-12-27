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

	@RequestMapping("/download")
	public void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse response) throws Exception{
		OutputStream out=response.getOutputStream();
		String downFile=IMAGE_REPO_PATH+"\\"+imageFileName;
		File file=new File(downFile);
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
