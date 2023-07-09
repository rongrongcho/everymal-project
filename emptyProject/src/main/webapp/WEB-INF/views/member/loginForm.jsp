<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- 프로젝트 이름이 각각 다르므로 어느 프로젝트에 가져다 놓아도 사용할 수 있게 컨택스트 패스를 변수로 가져온다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <% request.setCharacterEncoding("utf-8"); %>
      <!DOCTYPE html>
      <html lang="ko">

      <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>로그인</title>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript">
	        $(function(){
	        	debugger;
	        	var urlParams = new URL(location.href).searchParams;
	        	var name = urlParams.get('result');
	        	if(name != null && name=='loginFailed'){
	        		alert('로그인 실패..');
	        	}
	        });
          function init() {
        	  debugger;
        	  var isLogon = '<%=session.getAttribute("isLogon")%>';
        	  var isHos = '<%=session.getAttribute("isHos")%>';
            if (isLogon != null) {
              if (isHos == 'true') {
                alert("이미 사업자로 로그인 하셨습니다");
                document.location.href = "${contextPath}/main.do";
              }else if(isHos == 'false'){
                alert("이미 로그인 하셨습니다");
                document.location.href = "${contextPath}/main.do";
              }
            }
          }
        </script>
        <link rel="stylesheet" href="${contextPath }/resources/css/reset.css" />
        <link rel="stylesheet" href="${contextPath }/resources/css/common.css" />
        <link rel="stylesheet" href="${contextPath }/resources/css/login.css" />
      </head>

      <body onload="init();">
        <div class="container">
          <!--header시작-->
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

          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
              <p class="sidetitles"></p>
            </div>
          </section>
          <div id="contentArea">
            <div id="loginArea">
              <section class="login">
                <!-- <h2>로그인</h2> -->
                <form action="${contextPath}/member/login.do" method="post" id="frmLogin">
                  <fieldset>
                    <legend>회원 로그인 폼</legend>
                    <p class="radio_btn">
                      <input type="radio" id="user_chk" name="chk" value="false" checked />
                      <label class="chk" for="user_chk">회원</label>
                      <input type="radio" id="hos_chk" name="chk" value="true" />
                      <label class="chk" for="hos_chk">병원</label>
                    </p>
                    <p>
                      <label class="log" for="user_id">아이디</label>
                      <input type="text" id="user_id" name="user_id" placeholder="아이디" required />
                    </p>
                    <p>
                      <label class="log" for="user_pwd">비밀번호</label>
                      <input type="password" id="user_pwd" name="user_pwd" placeholder="4자이상 8자이하" required />
                    </p>
                    <p>
                      <button type="submit">로그인</button>
                    </p>
                  </fieldset>
                </form>
                <div class="memberLink">
                  <a href="${contextPath }/member/join_choice.do" class="join">회원가입</a>
                  <a href="${contextPath }/member/search_id.do" class="find">아이디 찾기</a>
                  <a href="${contextPath }/member/search_pass.do" class="find">비밀번호 찾기</a>
                </div>
              </section>
            </div>
          </div>
          <!--푸터 시작-->
          <footer>
            <ul class="bottomNav">
              <li>
                <a id="footerLogo" href="${contextPath}/member/main.do"><img src="${contextPath}/resources/img/EverymalLogo_w.svg"
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
                  <c:when test="${isLogon != null}">
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

          <!--푸터끝-->
        </div>
      </body>

      </html>