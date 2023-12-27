package com.myspring.stsproject.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//모든 매핑은 여기로(전처리)

public class ViewNameInterceptor extends HandlerInterceptorAdapter{

//	컨트롤러 가기전 수행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			String viewName=getViewName(request);
			request.setAttribute("viewName", viewName);
			
		} catch (Exception e) {
			System.out.println("인터셉터 중 오류");
			e.printStackTrace();
		}
		return true;
	}

//	컨트롤러 실행후 뷰로 보내기 전 호출
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

//	뷰까지 수행후 호출할때 사용
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	private String getViewName(HttpServletRequest request) throws Exception {

		String contextPath = request.getContextPath();

		String uri=(String) request.getAttribute("javax.servlet.include.request_uri");
		if(uri==null||uri.trim().equals("")) {

			uri = request.getRequestURI();
		}


		int begin=0, end;
		if(!( (contextPath==null) || "".equals(contextPath) )) {
			begin=contextPath.length();
		}
		if(uri.indexOf(";")!=-1) {
			end=uri.indexOf(";");
		}else if(uri.indexOf("?")!=-1) {
			end=uri.indexOf("?");
		}else {
			end=uri.length();
		}
		String fileName=uri.substring(begin, end); 
		if(fileName.lastIndexOf(".")!=-1) {
			fileName=fileName.substring(0,fileName.lastIndexOf("."));
		}
		if(fileName.lastIndexOf("/")!=-1) {
//			member, board등 폴더에 들어가므로 ',1'를 추가한다
			fileName=fileName.substring(fileName.lastIndexOf("/",1), fileName.length());
		}
		
		return fileName;
	}
}
