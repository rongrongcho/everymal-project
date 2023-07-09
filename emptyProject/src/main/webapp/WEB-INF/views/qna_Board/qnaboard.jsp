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
            if (${ article.hos_name == null }) {
              $('#answer_form').css('display', 'none');
            }else {
              $('#ans_btn').css('display', 'none');
            }
          }

          function ans() {
            if (${ !empty isLogon && !empty isHos }) {
              if (${ isHos == true }) {
                $('#answer_form').css('display', 'block');
                document.getElementById("answerTitle").disabled = false;
                document.getElementById("answerText").disabled = false;
                $('.writerHospital').css('display', 'none');
                $('.check').css('display', 'none');
                document.getElementById("btn_mod_reply").style.display = "none";
                document.getElementById("btn_del_reply").style.display = "none";
                document.getElementById("btn_add_reply").style.display = "block";
              }
				else {
                alert("답변은 병원 회원만 가능합니다!");
              }
            }else {
              alert("답변은 병원 회원만 가능합니다!");
            }
          }

          function modArticle(obj) {
            if (${ article.user_id == log_id }) {
              document.getElementById("questionTitle").disabled = false;
              document.getElementById("questionText").disabled = false;

              document.getElementById("btn_mod").style.display = "block";
              document.getElementById("btn_del").style.display = "none";
            }else {
              alert("자신이 쓴 글만 수정할 수 있습니다!");
            }

          }

          function modReply(obj) {
            if (${ article.hos_id == log_id }) {
              document.getElementById("answerTitle").disabled = false;
              document.getElementById("answerText").disabled = false;

              document.getElementById("btn_mod_reply").style.display = "block";
              document.getElementById("btn_del_reply").style.display = "none";
            }else {
              alert("자신이 쓴 글만 수정할 수 있습니다!");
            }

          }

          //질문 삭제
          function fn_remove_article(url, q_code) {
            if (${ article.user_id == log_id }) {
              let d_form = document.createElement("form");      //createElement=>태그를 만들어준다
              d_form.setAttribute("method", "post");
              d_form.setAttribute("action", url); //매개변수로 받은 주소로 지정
              //폼 안에 필요한 태그요소들 만들기
              let articleNoInput = document.createElement("input");
              articleNoInput.setAttribute("type", "hidden");
              articleNoInput.setAttribute("name", "q_code");
              articleNoInput.setAttribute("value", q_code);
              d_form.appendChild(articleNoInput); //만든 태그를 폼 안에 넣어준다
              document.body.appendChild(d_form);//만든 폼을 바디안에 넣어준다
              d_form.submit();
            }else {
              alert("자신이 쓴 글만 삭제할 수 있습니다!");
            }
          }

          //답변 삭제
          function fn_remove_reply(url, q_code) {
            if (${ article.hos_id == log_id }) {
              let d_form = document.createElement("form");      //createElement=>태그를 만들어준다
              d_form.setAttribute("method", "post");
              d_form.setAttribute("action", url); //매개변수로 받은 주소로 지정
              //폼 안에 필요한 태그요소들 만들기
              let articleNoInput = document.createElement("input");
              articleNoInput.setAttribute("type", "hidden");
              articleNoInput.setAttribute("name", "q_code");
              articleNoInput.setAttribute("value", q_code);
              d_form.appendChild(articleNoInput); //만든 태그를 폼 안에 넣어준다
              document.body.appendChild(d_form);//만든 폼을 바디안에 넣어준다
              d_form.submit();
            }else {
              alert("자신이 쓴 글만 삭제할 수 있습니다!");
            }
          }

          //수정 취소하기
          function toList(obj) {
            //obj.action="${contextPath}/board/listArticles.do";
            obj.action = "${contextPath}/qna_Board/qnaboardMain.do?q_code=${article.q_code}";
            obj.submit();
          }
        </script>
        <link rel="stylesheet" href="${contextPath }/resources/css/reset.css" />
        <link rel="stylesheet" href="${contextPath }/resources/css/common.css" />
        <link rel="stylesheet" href="${contextPath }/resources/css/qnaboard.css" />
        <style type="text/css">
          #btn_mod {
            display: none;
          }

          #btn_mod_reply {
            display: none;
          }

          #btn_add_reply {
            display: none;
          }
        </style>
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
              <a href="#" class="goToAnswer" onclick="ans();" id="ans_btn"><span>답변하기</span></a>
            </div>
            <div class="questionContent">
              <form class="questionForm" action="${contextPath}/qna_Board/modArticle.do" name="questionForm" method="post"
                enctype="multipart/form-data">
                <fieldset>
                  <legend>질문하기 폼</legend>
                  <input type="hidden" name="q_code" value="${article.q_code}">
                  <p class="title">
                    <label class="question" for="q_title">질문 제목</label>
                    <input type="text" id="questionTitle" name="q_title" value="${article.q_title}" disabled />
                  </p>
                  <p class="info">
                    <span>게시물 번호: ${article.q_code }</span>
                    <span>동물: ${article.petrace}</span>
                  </p>
                  <div class="line"></div>
                  <p class="writer">
                    <span>작성자: ${article.user_id}</span>
                    <span>${article.q_date.substring(0,10) }</span>
                  </p>
                  <p class="text">
                    <label class="question" for="q_text"></label>
                    <textarea name="q_text" class="questionText" id="questionText"
                      disabled>${article.q_text}</textarea>
                  </p>
                  <div class="btn" id="btn_del">
                    <button class="btnQuestion" type="button" onclick="modArticle(this.form);">수정하기</button>
                    <button class="btnQuestion" type="button"
                      onclick="fn_remove_article('${contextPath}/qna_Board/removeArticle.do',${article.q_code})">질문삭제</button>
                  </div>
                  <div class="btn" id="btn_mod">
                    <button class="btnQuestion" type="submit">수정 반영</button>
                    <button class="btnQuestion" type="button" onclick="toList(this.form);">수정 취소</button>
                  </div>
                </fieldset>
              </form>
              <div id="answer_form">
                <div class="boldLine"></div>
                <form class="answerForm" action="${contextPath}/qna_Board/addReply.do"" name=" answerForm" method="post"
                  enctype="multipart/form-data">
                  <fieldset>
                    <legend>답변하기 폼</legend>
                    <input type="hidden" id="q_code" name="q_code" value="${article.q_code}">
                    <p class="title">
                      <label class="answer" for="a_title">답변 제목</label>
                      <input type="text" id="answerTitle" name="a_title" value="${article.a_title}" disabled />
                    </p>
                    <p class="check">
                      <span>채택된 답변</span>
                      <span class="color">✔</span>
                    </p>
                    <div class="line"></div>
                    <p class="writer writerHospital">
                      <span>작성자: ${article.hos_name}</span>
                      <span>${article.a_date.substring(0,10)}</span>
                    </p>
                    <p>
                      <label class="answer" for="a_text"></label>
                      <textarea id="answerText" name="a_text" class="questionText"
                        disabled>${article.a_text}</textarea>
                    </p>
                    <div class="btn" id="btn_add_reply">
                      <button class="btnQuestion" type="submit">제출하기</button>
                      <button class="btnQuestion" type="button" onclick="toList(this.form);">취소하기</button>
                    </div>
                    <div class="btn" id="btn_del_reply">
                      <button class="btnQuestion" type="button" onclick="modReply(this.form);">수정하기</button>
                      <button class="btnQuestion" type="button"
                        onclick="fn_remove_reply('${contextPath}/qna_Board/removeReply.do',${article.q_code});">답변삭제</button>
                    </div>
                    <div class="btn" id="btn_mod_reply">
                      <button class="btnQuestion" type="submit">수정 반영</button>
                      <button class="btnQuestion" type="button" onclick="toList(this.form);">수정 취소</button>
                    </div>
                  </fieldset>
                </form>
              </div>
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