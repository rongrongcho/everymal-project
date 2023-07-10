package com.myspring.stsproject.board.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("articleVO")
public class ArticleVO {
	private int level;
	private int articleNo;
	private int parentNo;
	private String title;
	private String content;
	private String imageFileName;
	private Date writeDate;
	private String id;
	
	public ArticleVO() {
		System.out.println("ArticleVO �깮�꽦");
	}

	public ArticleVO(int level, int articleNo, int parentNo, String title, String contentString, String imageFileName,
			String id) {
		super();
		this.level = level;
		this.articleNo = articleNo;
		this.parentNo = parentNo;
		this.title = title;
		this.content = contentString;
		this.imageFileName = imageFileName;
		this.id = id;
	}
	//�씠誘몄��뒗 �븳湲��씠 �삱�닔 �엳�쑝誘�濡� �삁�쇅�쟻�쑝濡� �삤瑜섏쿂由щ�� �빐二쇱뼱�빞�븳�떎
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String contentString) {
		this.content = contentString;
	}

	public String getImageFileName() {
		try {
			if(imageFileName != null && imageFileName.length()!=0) {
				imageFileName=URLDecoder.decode(imageFileName, "utf-8");
			}else {
				imageFileName=null;
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("�씠誘몄� 濡쒕뵫以� �뿉�윭!!");
		}
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		try {
			if(imageFileName != null && imageFileName.length()!=0) {
				//���옣�룄 �븳湲��씠 �엳�쓣�닔 �엳�쑝誘�濡� �씤肄붾뵫�썑 ���옣�븳�떎
				this.imageFileName = URLEncoder.encode(imageFileName, "utf-8");
			}else {
				this.imageFileName=null;
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("�씠誘몄� ���옣以� �뿉�윭!!");
		}
		
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
