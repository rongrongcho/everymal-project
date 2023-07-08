<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <% request.setCharacterEncoding("utf-8"); %>
      <!DOCTYPE html>
      <html lang="ko">

      <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>비밀번호 찾기</title>
        <link rel="stylesheet" href="${contextPath}/resources/css/reset.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/search_pass.css" />
        <script src="${contextPath}/resources/js/jquery-3.6.3.min.js"></script>
        <script>
          let disa_sw = true;
          $(function () {
            $("#lb_findGG").click(function () {
              disa_sw = true;
              $(".hos_dis").prop("disabled", disa_sw);
              $(".user_dis").prop("disabled", !disa_sw);
            });
            $("#lb_findGI").click(function () {
              disa_sw = false;
              $(".hos_dis").prop("disabled", disa_sw);
              $(".user_dis").prop("disabled", !disa_sw);
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
                        <a href="${contextPath}/hos_MypageInfo/hosMypage.do">병원마이페이지</a>
                      </c:when>
                      <c:when test="${log_id eq 'admin'}">
                      	<a href="${contextPath }/administrator/memberList.do">관리자마이페이지</a>
                      </c:when>
                      <c:otherwise>
                        <a href="${contextPath}/user_Page/myInfo.do">회원마이페이지</a>
                      </c:otherwise>
                    </c:choose>
                  </c:when>
                  <c:otherwise>
                    <a href="${contextPath}/member/loginForm.do">로그인•회원가입</a>
                  </c:otherwise>
                </c:choose>
              </li>
              </ul>
            </nav>
          </header>
          <h2 class="pagetitlte">비밀번호 찾기</h2>
          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
              <p class="sidetitles"></p>
            </div>
          </section>

          <section id="tab_Area">
            <div class="tabcontents">
              <div id="tabBox01" class="tabBox">
                <h3 class="skip">가입 정보 입력</h3>
                <form name="searchPass" method="post" action="${contextPath}/member/searchPass.do">
                  <div class="memberForm_1">
                    <fieldset>
                      <legend>회원가입 정보</legend>
                      <div class="topBox">
                        <ul class="memChoice">
                          <li>
                            <input type="radio" name="chk" id="lb_findGG" checked value="false" /><label
                              for="lb_findGG">일반회원</label>
                          </li>
                          <li>
                            <input type="radio" name="chk" id="lb_findGI" value="true" /><label
                              for="lb_findGI">병원회원</label>
                          </li>
                        </ul>
                      </div>
                      <div class="dotBox">
                        <dl>
                          <dt class="dot_title" id="gmember">일반회원</dt>
                          <dt class="dot_text">
                            <label for="lb_findName">이름</label>
                          </dt>
                          <dd>
                            <input type="text" id="lb_findName" class="user_dis" name="rname1" maxlength="10" />
                          </dd>
                          <dt class="dot_text">
                            <label for="lb_findId">아이디</label>
                          </dt>
                          <dd>
                            <input type="text" id="lb_findID" class="user_dis" name="rid1" maxlength="10" />
                          </dd>
                          <dt class="dot_text">
                            <label for="lb_findIdent">이메일 주소</label>
                          </dt>
                          <dd>
                            <input type="text" id="lb_findIdent" class="user_dis" title="이메일 앞자리" name="ident"
                              class="inp3" />
                            @
                            <input type="text" class="user_dis" title="이메일 뒷자리" name="ident1" />
                          </dd>
                        </dl>
                        <dl>
                          <dt class="dot_title" id="hosmember">병원회원</dt>
                          <dt class="dot_text">
                            <label for="lb_findName2">관리자이름</label>
                          </dt>
                          <dd>
                            <input type="text" id="lb_findName2" class="hos_dis" name="rname2" maxlength="10"
                              class="inp3" disabled />
                          </dd>
                          <dt class="dot_text">
                            <label for="lb_findId">아이디</label>
                          </dt>
                          <dd>
                            <input type="text" disabled id="lb_findID" class="hos_dis" name="rid2" maxlength="10" />
                          </dd>
                          <dt class="dot_text">
                            <label for="lb_findIdent2">사업자등록번호</label>
                          </dt>
                          <dd>
                            <input type="hidden" name="C_Ident" /><input type="text" id="lb_findIdent2" class="hos_dis"
                              disabled title="사업자 등록번호 앞 세자리" name="work_no1" maxlength="3" class="inp3" />
                            -
                            <input type="text" disabled class="hos_dis" title="사업자 등록번호 가운데 두자리" name="work_no2"
                              maxlength="2" class="inp3" />
                            -
                            <input type="text" disabled class="hos_dis" title="사업자 등록번호 뒤 다섯자리" name="work_no3"
                              maxlength="5" class="inp3" />
                          </dd>
                        </dl>
                      </div>
                    </fieldset>
                    <p id="searchBtn">
                      <button type="submit">비밀번호 찾기</button>
                    </p>
                  </div>
                </form>
              </div>
            </div>
            <a href="${contextPath}/member/search_id.do" class="findId">아이디찾기</a>
          </section>

          <!-- fooooooooooooooooooooooooooooooooooooooooooooooooooooooter -->
          <footer>
            <ul class="bottomNav">
              <li>
                <a id="footerLogo" href="${contextPath}/main.do"><img src="${contextPath}/resources/img/EverymalLogo_w.svg"
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
                    <a href="${contextPath}/member/logout.do">로그아웃</a>
                  </c:when>
                  <c:otherwise>
                    <a href="${contextPath}/member/loginForm.do">로그인</a>
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