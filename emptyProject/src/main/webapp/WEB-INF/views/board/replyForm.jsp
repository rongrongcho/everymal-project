<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 프로젝트 이름이 각각 다르므로 어느 프로젝트에 가져다 놓아도 사용할 수 있게 컨택스트 패스를 변수로 가져온다 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 쓰기 창</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
//이미지 미리보기 구현
	function readImage(input) {
		if(input.files && input.files[0]){ //true가 되면 이미지를 첨부했다는 의미
			let reader=new FileReader();
			reader.onload = function (event) {
				$("#preview").attr('src', event.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	//다른 액션을 submit하기 위해
	function toList(obj) {
		obj.action="${contextPath}/board/listArticles.do";
		obj.submit();
	}
</script>
</head>
<body>
	<h2 align="center">답글쓰기</h2>
	<form name="frmReply" action="${contextPath}/board/addReply.do" method="post" enctype="multipart/form-data">
		<!--이미지는 용량이 크기때문에 따로 저장해놓고 이름을 받아 찾아서 처리한다
			multipart/form-data는 파일을 업로드 할떄 이미지와 이미지의 이름을 따로 전송하는데 필수적으로 필요하다-->
		<table align="center">
			<tr>
				<td align="right">글쓴이 : </td>
				<td><input type="text" value="a" disabled></td>
			</tr>
			<tr>
				<td align="right">글제목 : </td>
				<td colspan="2"><input type="text" size="50" name="title"></td>
			</tr>
			<tr>
				<td align="right">글내용 : </td>
				<td colspan="2"><textarea rows="10" cols="50" name="content" maxlength="4000"></textarea></td>
			</tr>
			<tr>
				<td align="right">이미지 파일 첨부 : </td>
				<td><input type="file" name="imageFileName" onchange="readImage(this);"></td><!-- this는 이 input태그를 의미한다 -->
				<td><img src="#" id="preview" width="200" height="200" ></td>
			</tr>
			<tr>
				<td align="right">&nbsp;</td>
				<td colspan="2">
					<input type="submit" value="답글쓰기">
					<input type="button" value="취소" onclick="toList(this.form);">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>