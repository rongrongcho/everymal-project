<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 프로젝트 이름이 각각 다르므로 어느 프로젝트에 가져다 놓아도 사용할 수 있게 컨택스트 패스를 변수로 가져온다 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- 
<c:set var="articleList" value="${articleMap.articleList }"/>
<c:set var="totArticles" value="${articleMap.totalArticles }"/>
<c:set var="section" value="${articleMap.section }"/>
<c:set var="pageNum" value="${articleMap.pageNum }"/> -->
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록 조회</title>
<script type="text/javascript">
	function fn_articleForm(isLogOn, articleForm, loginForm) {
		if(isLogOn != '' && isLogOn != 'false'){
			location.href=articleForm;
		}else{
			alert("로그인 후 글쓰기가 가능합니다.");
			location.href=loginForm + "?action=/board/articleForm.do";//로그인 완료 후 글쓰기 화면으로 이동 가능하게 설정
		}
	}

</script>
<style type="text/css">
   a{
      text-decoration: none;
      color: #333;
   }

   .selPage{
      color: red;
      font-weight: bold;
   }

   .noLine{
      color: #333;
      font-style: normal;
   }
</style>
</head>
<body>
   <table align="center" border="1" width="80%">
      <tr align="center" bgcolor="pink">
         <th>글번호</th><th>작성자</th><th>제목</th><th>작성일</th>
      </tr>
      <c:choose>
         <c:when test="${empty aList}">
            <tr>
               <td colspan="4" align="center">등록된 글이 없습니다.</td>
            </tr>
         </c:when>
      </c:choose>
      <c:choose>
         <c:when test="${!empty aList}">
            <c:forEach var="article" items="${aList}" varStatus="articleNum">
               <tr align="center">
               	  <!-- <td width="5%">${(pageNum-1)*10+articleNum.count}</td> -->
                  <td width="5%">${articleNum.count}</td>
                  <td width="10%">${article.id}</td>
                  <td align="left" width="50%">
                     <span style="padding-left: 10px"></span>
                     <c:choose>
                        <c:when test="${article.level > 1}">
                           <c:forEach begin="1" end="${article.level}" step="1">
                              <span style="padding-left: 20px"></span>
                           </c:forEach>
                           [답변]<a href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
                        </c:when>
                        <%-- 답변글이 아닌 애들 --%>
                        <c:otherwise>
                           <a href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
                        </c:otherwise>
                     </c:choose>
                  </td>
                  <td width="10%">${article.writeDate}</td>
               </tr>
            </c:forEach>
         </c:when>
      </c:choose>
   </table>
   <!-- 
   <div align="center">
   	   <c:if test="${totArticles !=0}">
   	   		<c:choose >
   	   		<%--글이 100개가 넘으면 섹션을 나누어 처리한다 --%>
   	   			<c:when test="${totArticles > 100}">
   	   				<%-- 딱 10으로 나누어 떨어질때 페이지가 하나 더 추가되는 것을 방지 --%>
   	   				<c:choose>
   	   					<c:when test="${totArticles%10==0}">
   	   						<c:set var="tot" value="${totArticles/10}"/>
   	   					</c:when>
   	   					<c:otherwise>
   	   						<c:set var="tot" value="${totArticles/10+1}"/>
   	   					</c:otherwise>
   	   				</c:choose>
   	   				
   	   				
   	   				<c:forEach var="page" begin="1" end="${tot-(section-1)*10}" step="1">
   	   				
	   	   				<c:if test="${not doneLoop}">
							<c:if test="${section>1 && page==1 }">
	   	   						<a href="${contextPath}/board/listArticles.do?section=${section-1}&pageNum=${pageNum}"> prev</a>
	   	   					</c:if>
	   	   					<c:choose>
	   	   					<%-- ${page==pageNum}을 나눈 이유는 현재 보고있는 페이지를 알려주기 위해 --%>
	   	   						<c:when test="${page==pageNum}">
	   	   							<a class="selPage" href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
	   	   						</c:when>
	   	   						<c:otherwise>
	   	   							<a class="noLine" href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
	   	   						</c:otherwise>
	   	   					</c:choose>
	   	   					<c:if test="${page==10}">
	   	   						<a href="${contextPath}/board/listArticles.do?section=${section+1}&pageNum=${pageNum}"> next</a>
	   	   						<c:set var="doneLoop" value="true"/>
	   	   					</c:if>
	   					</c:if>
   	   				</c:forEach>
   	   			</c:when>
   	   			<%-- ${totArticles <= 100 }로 처리하면 end="${totArticles/10+1}"이므로 11페이지 까지 생긴다
   	   			<c:when test="${totArticles == 100}">
   	   				<c:forEach var="page" begin="1" end="10" step="1">
   	   					<c:choose>
   	   						<c:when test="${page==pageNum}">
   	   							<a class="selPage" href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${page}</a>
   	   						</c:when>
   	   						<c:otherwise>
   	   							<a class="noLine" href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${page}</a>
   	   						</c:otherwise>
   	   					</c:choose>
   	   				</c:forEach>
   	   			</c:when>
   	   			--%>
   	   			<c:when test="${totArticles <= 100 }">
   	   				<%-- 딱 10으로 나누어 떨어질때 페이지가 하나 더 추가되는 것을 방지 --%>
   	   				<c:choose>
   	   					<c:when test="${totArticles%10==0}">
   	   						<c:set var="tot" value="${totArticles/10}"/>
   	   					</c:when>
   	   					<c:otherwise>
   	   						<c:set var="tot" value="${totArticles/10+1}"/>
   	   					</c:otherwise>
   	   				</c:choose>
   	   				
   	   				
   	   				<c:forEach var="page" begin="1" end="${tot}" step="1">
   	   					<c:choose>
   	   					<%-- ${page==pageNum}을 나눈 이유는 현재 보고있는 페이지를 알려주기 위해 --%>
   	   						<c:when test="${page==pageNum}">
   	   							<a class="selPage" href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${page}</a>
   	   						</c:when>
   	   						<c:otherwise>
   	   							<a class="noLine" href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${page}</a>
   	   						</c:otherwise>
   	   					</c:choose>
   	   				</c:forEach>
   	   			</c:when>
   	   		</c:choose>
   	   </c:if>
   </div> -->
   <p align="center"><a href="javascript:fn_articleForm('${isLogOn}', '${contextPath}/board/articleForm.do' ,'${contextPath}/member/loginForm.do')">글쓰기</a></p>
</body>
<%-- 
<body>
	<table align="center" border="1" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<th>글번호</th><th>작성자</th><th>제목</th><th>작성일</th>
		</tr>
		<c:choose>
			<c:when test="${empty articleList }">
				<tr>
					<td colspan="4" align="center">등록된 글이 없습니다.</td>
				</tr>
			</c:when>
			<c:when test="${!empty articleList }">
				<c:forEach var="article" items="${articleList}" varStatus="articleNum">
					<tr>
						<td width="5%">${articleNum.count }</td><!--article.articleNo로 값을 가져오게 되면 답변글로 인해 글번호가 중간에 빈다 따라서 varStatus를 이용한다 -->
						<td width="10%">${article.id}</td>
						<td align="left" width="50%">
							<span style="padding-left:10px">${article.title}</span>
							<c:choose>
								<c:when test="${article.level>1 }">
									<c:forEach begin="1" end="${article.level}" step="1">
										<span style="padding-left:20px">${article.title}</span>
									</c:forEach>
									[답변]<a href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
                              <!-- 제목 클릭하면 해당글 상세보기로 이동 -->
                          </c:when> 
                          <c:otherwise><!-- level없는 애들, 답변글이 아닌 애들 -->
                            <a href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
                          </c:otherwise>
                       </c:choose>
                    </td>
                    <td width="10%"> ${article.writeDate}</td>
                  </tr>
               </c:forEach>
           </c:when>
        </c:choose>
      </table>
      <p align="center"><a href="${contextPath}/board/articleForm.do">글쓰기</a></p>
</body>
--%>
</html>