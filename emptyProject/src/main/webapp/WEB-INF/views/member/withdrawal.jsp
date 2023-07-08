<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 프로젝트 이름이 각각 다르므로 어느 프로젝트에 가져다 놓아도 사용할 수 있게 컨택스트 패스를 변수로 가져온다 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원 탈퇴</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript"></script>
    <link rel="stylesheet" href="${contextPath }/resources/css/reset.css" />
    <link rel="stylesheet" href="${contextPath }/resources/css/common.css" />
    <link rel="stylesheet" href="${contextPath }/resources/css/withdrawal.css" />
  </head>

  <body>
    <div id="container_sub">
      <header id="header">
        <nav>
          <ul class="topNav">
            <li>
              <a href="${contextPath}/board/listArticles.do">1:1온라인 상담</a>
            </li>
            <li>
              <a href="${contextPath}/emr_Page/emergency.jsp">24시간 응급실</a>
            </li>
            <li><a href="${contextPath}/petTaxiPage">펫택시</a></li>
            <li><a href="${contextPath}/hosfilter">병원 찾기</a></li>
            <li>
              <c:choose>
                <c:when test="${!empty isLogon}">
                  <c:choose>
                    <c:when test="${isHos}">
                      <a href="${contextPath}/hosMypageInfo">병원마이페이지</a>
                    </c:when>
                    <c:otherwise>
                      <a href="${contextPath}/userMypage">회원마이페이지</a>
                    </c:otherwise>
                  </c:choose>
                </c:when>
                <c:otherwise>
                  <a href="${contextPath}/login.jsp">로그인•회원가입</a>
                </c:otherwise>
              </c:choose>
            </li>
          </ul>
        </nav>
      </header>
      <h2 class="pagetitle">회원 탈퇴</h2>
      <section id="sidebar_Area">
        <div class="sidebars" id="sb_sidebar">
          <p class="sidetitles"></p>
        </div>
      </section>
      <div id="contentArea">
        <div id="withdrawalBox">
          <section class="infoBox">
            <h3>탈퇴 안내</h3>
            <p class="titleLine">
              회원탈퇴를 신청하기 전에 안내 사항을 꼭 확인해주세요.
            </p>
            <p class="checkLine">
              <span>✔︎사용하고 계신 아이디(</span>
              <span class="redLetter">${withdrawal.id}</span>
              <span>)는 탈퇴할 경우 재사용 및 복구가 불가능합니다.</span>
            </p>
            <p class="smallLine">
              <span class="redLetter"
                >탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구가 불가</span
              >
              <span>하오니 신중하게 선택하시기 바랍니다.</span>
            </p>
            <p class="checkLine">
              <span
                >✔︎탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두
                삭제됩니다.</span
              >
            </p>
            <p class="checkLine">
              <span
                >✔︎탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아
                있습니다.</span
              >
            </p>
            <p class="smallLine">
              <span
                >리뷰, 1:1 온라인 게시판은 탈퇴 시 자동 삭제되지 않고 그대로
                남아있습니다. 삭제를 원하는 게시물이 있다면</span
              >
              <span class="redLetter">
                반드시 탈퇴 전 삭제하시기 바랍니다.</span
              >
            </p>
            <form action="${contextPath }/member/updateEnabled.do" method="post">
            <input type="hidden" name="id" value="${log_id}">
            <input type="submit" class="btn" value="탈퇴하겠습니다">
            </form>
          </section>
        </div>
      </div>
      <!--푸터 시작-->

      <!--푸터끝-->
    </div>
  </body>
</html>
