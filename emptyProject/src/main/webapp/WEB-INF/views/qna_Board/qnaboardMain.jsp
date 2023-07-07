<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="articleList" value="${articleMap.articleList }" />
<c:set var="totArticles" value="${articleMap.totalArticles }" />
<c:set var="section" value="${articleMap.section }" />
<c:set var="pageNum" value="${articleMap.pageNum }" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>1:1 온라인 상담</title>
    <link rel="stylesheet" href="${contextPath }/resources/css/reset.css" />
    <link rel="stylesheet" href="${contextPath }/resources/css/common.css" />
    <link rel="stylesheet" href="${contextPath }/resources/css/qnaboardMain.css" />
    <style type="text/css"></style>
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
      <h2 class="pagetitlte">1:1 온라인 상담</h2>
      <section id="sidebar_Area">
        <div class="sidebars" id="sb_sidebar">
          <p class="sidetitles"></p>
        </div>
      </section>
      <section class="content">
        <img
          src="${contextPath}/img/megaphone.png"
          alt="확성기"
          class="megaphone"
        />
        <div class="box">
          <div class="marquee">
            <p>
              고운말 바른말만 사용해주세요!&nbsp;&nbsp;&nbsp;상대방을 배려하면서
              질문해주세요!&nbsp;&nbsp;&nbsp;상세한 질문을 할 수록 정확한 답변을
              받을 수 있습니다!
            </p>
          </div>
        </div>
        <table>
          <tbody>
            <c:choose>
              <c:when test="${empty articleList}">
                <tr>
                  <td align="center">등록된 글이 없습니다.</td>
                </tr>
              </c:when>
            </c:choose>
            <c:choose>
              <c:when test="${!empty articleList}">
                <c:forEach var="article" items="${articleList}">
                  <tr>
                    <td>
                      <a
                        href="${contextPath}/qna_Board/viewArticle.do?q_code=${article.q_code}"
                        >${article.q_title}</a
                      >
                    </td>
                    <td>
                      <img
                        src="${contextPath}/proimgdown.do?imageFileName=${article.user_imgName}&user_id=${article.user_id}"
                        alt=""
                        width="50"
                        height="50"
                        class="pro"
                      />
                    </td>
                  </tr>
                  <c:if test="${article.hos_name != null}">
                    <tr>
                      <td>
                        <span class="answerArrow">↳</span
                        ><span class="answerBox">답변</span>
                        <a
                          href="${contextPath}/qna_Board/viewArticle.do?q_code=${article.q_code}"
                        >
                          ${article.a_title}</a
                        >
                      </td>
                      <td>
                        <img
                          src="${contextPath}/hosProImgDown.do?hos_pro=${article.hos_pro}&hos_code=${article.hos_code}"
                          alt=""
                          width="50"
                          height="50"
                          class="pro"
                        />
                      </td>
                    </tr>
                  </c:if>
                </c:forEach>
              </c:when>
            </c:choose>
          </tbody>
        </table>
        <div align="center" class="divPaging">
          <c:if test="${totArticles !=0}">
            <c:choose>
              <c:when test="${totArticles > 50}">
                <c:choose>
                  <c:when test="${totArticles%5==0}">
                    <c:set var="tot" value="${totArticles/5}" />
                  </c:when>
                  <c:otherwise>
                    <c:set var="tot" value="${totArticles/5+1}" />
                  </c:otherwise>
                </c:choose>

                <c:forEach
                  var="page"
                  begin="1"
                  end="${tot-(section-1)*10}"
                  step="1"
                >
                  <c:if test="${not doneLoop}">
                    <c:if test="${section>1 && page==1 }">
                      <a
                        href="${contextPath}/qna_Board/qnaboardMain.do?section=${section-1}&pageNum=${pageNum}"
                      >
                        prev</a
                      >
                    </c:if>
                    <c:choose>
                      <%-- ${page==pageNum}을 나눈 이유는 현재 보고있는 페이지를
                      알려주기 위해 --%>
                      <c:when test="${page==pageNum}">
                        <a
                          class="selPage"
                          href="${contextPath}/qna_Board/qnaboardMain.do?section=${section}&pageNum=${page}"
                          >${(section-1)*10+page}</a
                        >
                      </c:when>
                      <c:otherwise>
                        <a
                          class="noLine"
                          href="${contextPath}/qna_Board/qnaboardMain.do?section=${section}&pageNum=${page}"
                          >${(section-1)*10+page}</a
                        >
                      </c:otherwise>
                    </c:choose>
                    <c:if test="${page==10}">
                      <a
                        href="${contextPath}/qna_Board/qnaboardMain.do?section=${section+1}&pageNum=${pageNum}"
                      >
                        next</a
                      >
                      <c:set var="doneLoop" value="true" />
                    </c:if>
                  </c:if>
                </c:forEach>
              </c:when>
              <%-- ${totArticles <=100 }로 처리하면 end="${totArticles/10+1}"
              이므로 11페이지 까지 생긴다
              <c:when test="${totArticles == 100}">
                <c:forEach var="page" begin="1" end="10" step="1">
                  <c:choose>
                    <c:when test="${page==pageNum}">
                      <a
                        class="selPage"
                        href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}"
                        >${page}</a
                      >
                    </c:when>
                    <c:otherwise>
                      <a
                        class="noLine"
                        href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}"
                        >${page}</a
                      >
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
              </c:when>
              --%>
              <c:when test="${totArticles <= 50 }">
                <c:choose>
                  <c:when test="${totArticles%5==0}">
                    <c:set var="tot" value="${totArticles/5}" />
                  </c:when>
                  <c:otherwise>
                    <c:set var="tot" value="${totArticles/5+1}" />
                  </c:otherwise>
                </c:choose>

                <c:forEach var="page" begin="1" end="${tot}" step="1">
                  <c:choose>
                    <c:when test="${page==pageNum}">
                      <a
                        class="selPage"
                        href="${contextPath}/qna_Board/qnaboardMain.do?section=${section}&pageNum=${page}"
                        >${page}</a
                      >
                    </c:when>
                    <c:otherwise>
                      <a
                        class="noLine"
                        href="${contextPath}/qna_Board/qnaboardMain.do?section=${section}&pageNum=${page}"
                        >${page}</a
                      >
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
              </c:when>
            </c:choose>
          </c:if>
        </div>
        <p align="center">
          <a href="${contextPath}/qna_Board/qnaboardWrite.do" class="writebtn"
            >글쓰기</a
          >
        </p>
      </section>

      <!-- fooooooooooooooooooooooooooooooooooooooooooooooooooooooter -->
      <footer>
        <ul class="bottomNav">
          <li>
            <a id="footerLogo" href="${contextPath}/index.jsp"
              ><img
                src="${contextPath}/img/EverymalLogo_w.svg"
                alt="로고"
                style="width: 250px; height: auto"
            /></a>
          </li>
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
                <a href="${contextPath}/logindb/logout">로그아웃</a>
              </c:when>
              <c:otherwise>
                <a href="${contextPath}/login.jsp">로그인</a>
              </c:otherwise>
            </c:choose>
          </li>
        </ul>
        <div class="table">
          <address>
            <p>Everymal</p>
            <p>서울특별시 종로구 중구 12길 33층 123호</p>
            <p>공동대표. 김경민, 이초롱, 나은비, 황치연, 김소중</p>
          </address>
          <div class="custom">
            <p>고객문의센터</p>
            <a href="tel:010-111-2222"
              >T. 1111-2222 (10:00 - 19:00 / 점심시간 12:30 - 13:30)</a
            >
            <a href="mailto:superman@test.com">E. everymal@gmail.com</a>
          </div>
          <div class="footBtn">
            <a href="#">개인정보 처리방침</a>
            <a href="#">이용약관</a>
          </div>
        </div>
      </footer>
    </div>
  </body>
</html>
