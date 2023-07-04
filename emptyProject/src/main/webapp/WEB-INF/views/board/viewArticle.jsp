<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 프로젝트 이름이 각각 다르므로 어느 프로젝트에 가져다 놓아도 사용할 수 있게 컨택스트 패스를 변수로 가져온다 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 보기</title>
<style type="text/css">
	#tr_button_modify{
		display: none;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function backToList(obj) {
		//폼을 객체로 받아와 액션을 설정하고 서밋한다
		obj.action="${contextPath}/board/listArticles.do";
		obj.submit();
	}
	function fn_enable(obj) {//폼을 오브젝트로 받는다
		document.getElementById("id_title").disabled=false;
		document.getElementById("id_content").disabled=false;
		//이미지가 없는 게시글은 이미지가 보이지 않기 때문에 따로 처리가 필요하다
		let imgName=document.getElementById("id_imgFile");
		if(imgName!=null){
			document.getElementById("id_imgFile").disabled=false;
		}

		document.getElementById("tr_button_modify").style.display="block";
		document.getElementById("tr_button").style.display="none";

		
	}
	
	//이미지 미리보기
	function readImage(input) {
		if(input.files && input.files[0]){ //true가 되면 이미지를 첨부했다는 의미
			let reader=new FileReader(); //제이쿼리에서 제공해주는 리더 메서드
			reader.onload = function (event) {
				$("#preview").attr('src', event.target.result);
				console.log(event.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	//수정반영하기
	function fn_modify_article(obj) {
		obj.action="${contextPath}/board/modArticle.do";
		//disabled된 값은 submit으로 넘어가지 않는다 따라서 hidden으로 보내준다
		obj.submit();
	}
	
	//수정 취소하기
	function toList(obj) {
		//obj.action="${contextPath}/board/listArticles.do";
		obj.action="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}";
		obj.submit();
	}
	
	//제목 내용등의 정보가 필요하지 않기 때문에 삭제는 필요한 정보만 동적으로 폼을 만들어 보내준다 
	//게시글 삭제하기
	function fn_remove_article(url, articleNo) {
		let d_form=document.createElement("form");      //createElement=>태그를 만들어준다
		d_form.setAttribute("method","post");
		d_form.setAttribute("action", url); //매개변수로 받은 주소로 지정
		//폼 안에 필요한 태그요소들 만들기
		let articleNoInput=document.createElement("input");
		articleNoInput.setAttribute("type","hidden");
		articleNoInput.setAttribute("name","articleNo");
		articleNoInput.setAttribute("value",articleNo);
		d_form.appendChild(articleNoInput); //만든 태그를 폼 안에 넣어준다
		document.body.appendChild(d_form);//만든 폼을 바디안에 넣어준다
		d_form.submit();
	}
	
	//답글쓰기
	function fn_reply_form(url, parentNo) {
		let d_form=document.createElement("form");      //createElement=>태그를 만들어준다
		d_form.setAttribute("method","post");
		d_form.setAttribute("action", url); //매개변수로 받은 주소로 지정
		//폼 안에 필요한 태그요소들 만들기
		let parentNoInput=document.createElement("input");
		parentNoInput.setAttribute("type","hidden");
		parentNoInput.setAttribute("name","parentNo");
		parentNoInput.setAttribute("value",parentNo);
		d_form.appendChild(parentNoInput); //만든 태그를 폼 안에 넣어준다
		document.body.appendChild(d_form);//만든 폼을 바디안에 넣어준다
		d_form.submit();
	}
</script>
</head>
<body>
	<form name="frmArticle" action="${contextPath}" method="post" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td width="170" align="center" bgcolor="#ff9933">글번호</td>
				<td><input type="text" value="${article.articleNo}" disabled></td>
				<!--disabled되어 submit으로 넘어가지 않으므로 hidden을 이용해 넘겨준다-->
				<input type="hidden" name="articleNo" value="${article.articleNo}">
			</tr>
			<tr>
				<td width="170" align="center" bgcolor="#ff9933">작성자 아이디</td>
				<td><input type="text" value="${article.id}" disabled></td>
				<!--disabled되어 submit으로 넘어가지 않으므로 hidden을 이용해 넘겨준다-->
				<input type="hidden" name="id" value="${article.id}">
			</tr>
			<tr>
				<td width="170" align="center" bgcolor="#ff9933">제목</td>
				<td><input type="text" id="id_title" name="title" value="${article.title}" disabled></td>
			</tr>
			<tr>
				<td width="170" align="center" bgcolor="#ff9933">내용</td>
				<%-- 내용은 textarea로 받아야한다 --%>
				<td><textarea rows="10" cols="50" name="content" id="id_content" disabled>${article.content}</textarea></td>
			</tr>
			<c:if test="${!empty article.imageFileName}">
				<tr>
					<td width="170" align="center" bgcolor="#ff9933" rowspan="2">이미지</td>
					<td>
						<!--원래 있던 파일을 삭제하기 위해 이전 파일의 이름도 넘겨준다-->
						<input type="hidden" name="orignalFileName" value="${article.imageFileName}">
						<img id="preview" src="${contextPath}/download.do?imageFileName=${article.imageFileName}&articleNo=${article.articleNo}">
					</td>
				</tr>
				<tr>
					<td><input type="file" id="id_imgFile" name="imageFileName" onchange="readImage(this);" disabled></td>
				</tr>
			</c:if>
			<tr>
				<td width="170" align="center" bgcolor="#ff9933">등록일자</td>
				<%-- 날짜 형식을 맞추기 위해 jstl 포맷을 이용한다 --%>
				<td><input type="text" value='<fmt:formatDate value="${article.writeDate}"/>' disabled></td>
			</tr>
			<tr id="tr_button_modify">
				<td align="center" colspan="2">
					<%-- 수정 삭제등 여러 기능이 있으므로 submit를 설정하지 않는다 스크립트로 처리한다 --%>
					<input type="button" value="수정반영하기" onclick="fn_modify_article(frmArticle);"><!-- ()안에 폼 이름이 들어간다 -->
					<input type="button" value="취소" onclick="toList(frmArticle);">
				</td>
			</tr>
			<tr id="tr_button">
				<td align="center" colspan="2">
					<input type="button" value="수정하기" onclick="fn_enable(this.form)"><!-- ()안에 폼 이름말고 this.form가능 -->
					<%-- 삭제와 답글쓰기를 제외하면 모두 form의 객체정보를 가져갔다
						 둘은 매핑정보를 매개변수로 가져간다 --%>
					<input type="button" value="삭제하기" onclick="fn_remove_article('${contextPath}/board/removeArticle.do',${article.articleNo})">
					<input type="button" value="리스트로 돌아가기" onclick="backToList(this.form)">
					<input type="button" value="답글쓰기" onclick="fn_reply_form('${contextPath}/board/replyForm.do',${article.articleNo})">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>




















