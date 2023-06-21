<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"
  import="java.util.*" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <c:set var="contextPath" value="${pageContext.request.contextPath}" />
      <% request.setCharacterEncoding("utf-8"); %>
        <!DOCTYPE html>
        <html>

        <head>
          <meta charset="UTF-8" />
          <title>My page</title>
          <link rel="stylesheet" type="text/css" href="${contextPath }/css/reset.css" />
          <link rel="stylesheet" type="text/css" href="${contextPath }/css/common.css" />
          <link rel="stylesheet" type="text/css" href="${contextPath }/css/hosMyPage.css" />
          <script src="${contextPath }/js/jquery-3.6.3.min.js"></script>
          <script type="text/javascript">
            $(document).ready(function () {
              $(".stop").click(function (e) {
                e.preventDefault();
                alert("비밀번호를 입력해주세요.");
              });
            });
          </script>
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
            >
            <h2 class="pagetitlte">개인정보 확인</h2>
            <section id="sidebar_Area">
              <div class="sidebars" id="sb_sidebar"></div>
            </section>

            <section id="left_Area"></section>
            <section id="tab_Area">
              <div class="tabcontents">
                <div id="tabBox" class="tab-content">
                  <form action="${contextPath }/hosMypageInfo/isValid.do" id="check_form" method="post">
                    <div id="msg_box">
                      <img src="${contextPath}/img/lock_icon.svg" alt="자물쇠이미지" />
                      <p class="msg">
                        회원님의 회원정보를 안전하게 보호하기 위해 비밀번호를 한번 더
                        확인해주세요. <br />
                        비밀번호가 타인에게 노출되지 않도록 주의해 주세요.
                      </p>
                    </div>
                    <label for="edit_pwd">비밀번호</label>
                    <div id="input_box">
                      <input type="hidden" id="edit_id" name="hos_id" value="${log_id }" />
                      <input type="password" id="edit_pwd" name="hos_pwd" placeholder="비밀번호 입력" required />
                    </div>
                    <div class="btn">
                      <a href=""><input form="check_form" type="button" value="취소" id="cancel_btn" /></a>
                      <a href=""><input form="check_form" type="submit" value="확인" id="pwd_confirm" /></a>
                    </div>
                  </form>
                </div>
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