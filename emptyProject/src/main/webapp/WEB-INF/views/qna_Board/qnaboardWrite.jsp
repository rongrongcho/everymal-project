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
        <title>1:1 온라인 상담</title>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript">
          function init() {
            if (${empty isLogon }) {
              alert("글쓰기는 회원만 가능합니다");
              document.location.href = "${contextPath}/login.jsp";
            }
          }
        </script>
        <link rel="stylesheet" href="${contextPath }/resources/css/reset.css" />
        <link rel="stylesheet" href="${contextPath }/resources/css/common.css" />
        <link rel="stylesheet" href="${contextPath }/resources/css/qnaboardWrite.css" />
      </head>

      <body onload="init();">
        <div id="container_sub">
          <header id="header">
            <nav>
              <ul class="topNav">
                <li>
                  <a href="${contextPath}/qna_Board/qnaboardMain.do">1:1온라인 상담</a>
                </li>
                <li>
                  <a href="${contextPath}/emr_Page/emergency.do">24시간 응급실</a>
                </li>
                 <li><a href="${contextPath}/pet_Taxi/petTaxiPage.do">펫택시</a></li>
                 <li><a href="${contextPath}/hos_List/hos_filter.do">병원 찾기</a></li>
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
                        <a href="${contextPath}/user_Page/isValidPwd.do">회원마이페이지</a>
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
          <h2 class="pagetitlte">1:1 온라인 상담</h2>
          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
              <p class="sidetitles"></p>
            </div>
          </section>
          <section class="content">
            <div class="topButton">
              <a href="${contextPath}/qna_Board/qnaboardMain.do" class="goToList"><span>목록으로</span></a>
            </div>
            <div class="questionContent">
              <form class="questionForm" action="${contextPath}/qna_Board/addArticle.do" name="questionForm" method="post"
                enctype="multipart/form-data">
                <fieldset>
                  <legend>질문하기 폼</legend>
                  <p class="title">
                    <label class="question" for="q_title"></label>
                    <input type="text" id="questionTitle" name="q_title" placeholder="제목을 입력해주세요." />
                  </p>
                  <p class="info">
                    <label class="infoTitle" for="race">동물 종 :</label>
                    <select name="petRace" id="petRace">
                      <option value="개">개</option>
                      <option value="고양이">고양이</option>
                      <option value="소동물">소동물</option>
                      <option value="파충류">파충류</option>
                      <option value="어류">어류</option>
                      <option value="조류">조류</option>
                      <option value="기타">기타</option>
                    </select>
                  </p>
                  <div class="line"></div>
                  <p class="text">
                    <label class="question" for="q_text"></label>
                    <textarea name="q_text" class="questionText" placeholder="내용을 입력해주세요."></textarea>
                  </p>
                  <div class="btn">
                    <button class="btnSubmit" type="submit">등록하기</button>
                  </div>
                </fieldset>
              </form>
            </div>
          </section>

          <!-- fooooooooooooooooooooooooooooooooooooooooooooooooooooooter -->
          <footer>
            <ul class="bottomNav">
              <li>
                <a id="footerLogo" href="${contextPath}/main.do"><img src="${contextPath}/resources/img/EverymalLogo_w.svg"
                    alt="로고" style="width: 250px; height: auto" /></a>
              </li>
              <li>
                <a href="${contextPath}/qna_Board/qnaboardMain.do">1:1온라인 상담</a>
              </li>
              <li>
                <a href="${contextPath}/emr_Page/emergency.do">24시간 응급실</a>
              </li>
              <li><a href="${contextPath}/pet_Taxi/petTaxiPage.do">펫택시</a></li>
              <li><a href="${contextPath}/hos_List/hos_filter.do">병원 찾기</a></li>
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