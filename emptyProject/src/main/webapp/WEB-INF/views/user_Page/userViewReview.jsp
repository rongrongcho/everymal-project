<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- core라이브러리를 c라는 접두사를 따와서 쓰겠다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <c:set var="hosRev" value="${hosReview}" />
    <% request.setCharacterEncoding("utf-8"); %>

      <!DOCTYPE html>
      <html lang="ko">

      <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>리뷰 작성</title>
        <link rel="stylesheet" href="${contextPath}/css/reset.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/review.css" />
        <link rel="shortcut icon" href="${contextPath}/img/favicon.ico">
        <link rel="icon" type="image/png" href="${contextPath}/img/EverymalLogo.svg">
        <script type="text/javascript">

          function fn_modSubmit(obj) {
            obj.action = "${contextPath}/userMypage/modMyReview.do";
            obj.submit();
          }

          function fn_delSubmit(obj) {
            obj.action = "${contextPath}/userMypage/returnMyReview.do";
            obj.submit();
          }

        </script>
      </head>

      <body>
        <div id="container_sub">
          <header id="header">
            <nav>
              <ul class="topNav">
                <li><a href="${contextPath}/board/listArticles.do">1:1온라인 상담</a></li>
                <li><a href="${contextPath}/emr_Page/emergency.jsp">24시간 응급실</a></li>
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
          <h2 class="pagetitlte">리뷰 작성</h2>
          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
              <p class="sidetitles">
              </p>
            </div>
          </section>

          <section class="contentWrap">
            <div class="content">
              <form action=" ${contextPath}/userMypage/modMyReview.do" name="myInfoForm" method="post">
                <fieldset>
                  <legend>예약하기 폼</legend>
                  <p class="title">
                    <label class="review" for="title">제목</label>
                    <input type="hidden" id="rv_code" name="rv_code" value="${hosRev.rv_code}">
                    <input type="text" id="title" name="rv_title" value="${hosRev.rv_title}">
                  </p>
                  <p class="date">
                    <label class="review" for="datepicker">날짜</label>
                    <input type="text" id="datepicker" name="rv_date" value="${hosRev.rv_date}" disabled>
                  </p>

                  <p class="rating">
                    <label class="review fileImg" for="chooseFile">별점</label>
                    <select name="rv_rate" size="1">
                      <option value="1" ${hosRev.rv_rate==1 ? "selected" : "" }>🌟</option>
                      <option value="2" ${hosRev.rv_rate==2 ? "selected" : "" }>🌟🌟</option>
                      <option value="3" ${hosRev.rv_rate==3 ? "selected" : "" }>🌟🌟🌟</option>
                      <option value="4" ${hosRev.rv_rate==4 ? "selected" : "" }>🌟🌟🌟🌟</option>
                      <option value="5" ${hosRev.rv_rate==5 ? "selected" : "" }>🌟🌟🌟🌟🌟</option>
                    </select>
                  </p>

                  <p class="formContent">
                    <label class="review reviewContent" for="content">내용</label>
                    <textarea name="rv_text" id="content">${hosRev.rv_text}</textarea>
                  </p>
                  <button id="mod_btn" class="btns" type="submit" onclick="fn_modSubmit(this.form)">수정</button>
                  <button id="del_btn" class="btns" type="submit" onclick="fn_delSubmit(this.form)">목록</button>
                </fieldset>
              </form>
            </div>
          </section>







          <!-- fooooooooooooooooooooooooooooooooooooooooooooooooooooooter -->
          <footer>
            <ul class="bottomNav">
              <li>
                <a id="footerLogo" href="${contextPath}/index.jsp"><img src="${contextPath}/img/EverymalLogo_w.svg"
                    alt="로고" style="width: 250px; height: auto" /></a>
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
                <a href="tel:010-111-2222">T. 1111-2222 (10:00 - 19:00 / 점심시간 12:30 - 13:30)</a>
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