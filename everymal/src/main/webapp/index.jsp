<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- 프로젝트 이름이 각각 다르므로 어느 프로젝트에 가져다 놓아도 사용할 수 있게 컨택스트 패스를 변수로 가져온다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <% request.setCharacterEncoding("utf-8"); %>
      <!DOCTYPE html>
      <html lang="en">

      <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>index</title>
        <link rel="stylesheet" href="${contextPath }/css/reset.css" />
        <link rel="stylesheet" href="${contextPath }/css/common.css" />
        <link rel="stylesheet" href="${contextPath }/css/index.css" />
        <script src="js/jquery-3.6.3.min.js"></script>
        <script src="${contextPath }/js/index.js"></script>
        <script type="text/javascript">
          let chatbot_chk = false;
          function fn_chatbot() {
            if (chatbot_chk) {
              $("iframe").css({
                display: "none",
              });
              chatbot_chk = false;
            } else {
              $("iframe").css({
                display: "block",
              });
              chatbot_chk = true;
            }
          }
        </script>
      </head>

      <body>
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
        <div class="full">
          <div class="contents" id="first">
            <div class="sideTitle sideTitle1">
              <h2>병원찾기</h2>
            </div>
            <img src="${contextPath}/img/dog4.png" alt="강아지사진" />
            <h3 class="first_fadein">내 반려동물에게 딱 맞는 병원은?</h3>
            <div class="mainBtn first_fadein">
              <div class="findH" id="reviewH">
                <a href="${contextPath}/hosfilter">
                  <img src="${contextPath}/img/cross.svg" alt="버튼이미지" />
                  <span class="mainBtnText">병원 찾으러가기</span>
                </a>
              </div>
            </div>
          </div>
          <div id="ef1_b"></div>
          <div id="ef1_f"></div>
          <div id="ef2_b"></div>
          <div id="ef2_f"></div>
          <div id="efR1_b"></div>
          <div id="efR1_f"></div>
          <div id="efR2_b"></div>
          <div id="efR2_f"></div>
          <div class="contents" id="second">
            <div class="sideTitle sideTitle2">
              <h2><span>24</span>시간 응급실•펫택시</h2>
            </div>
            <img src="${contextPath}/img/mainlayout.png" alt="메인페이지" />
            <section class="emergency">
              <div class="textBtn">
                <p class="secondText">
                  <span>지금 당장 필요해!</span>
                  <span>우리 집 주변 밤에 갈 수 있는 응급실은?</span>
                  <span>응급실 전화번호를 미리 적어둘까?</span>
                </p>
                <div class="emergencyBtn">
                  <img class="sirenImg" src="${contextPath}/img/alarm.png" alt="사이렌" />
                  <button class="secondBtn" onclick="location.href = '${contextPath}/emr_Page/emergency.jsp' ">
                    내 주변 24시간 응급실 보기
                  </button>
                </div>
              </div>
              <div class="animationBox1">
                <img src="${contextPath}/img/ambulance.png" alt="구급차" />
              </div>
            </section>
            <img id="mapImg" src="${contextPath}/img/map.png" alt="지도이미지" />
            <section class="petTaxi">
              <div class="animationBox2">
                <img src="${contextPath}/img/pettaxi.png" alt="펫택시" />
              </div>
              <div class="textBtn2">
                <p class="secondText secondText2">
                  <span>내 반려동물 눈치 안보고 차 타고 싶어!</span>
                  <span>병원갈때 이용할 택시가 있을까?</span>
                  <span>내일 펫택시 가능할까?</span>
                </p>
                <div class="pettaxiBtn">
                  <img class="taxiImg" src="${contextPath}/img/taxi.png" alt="택시" />
                  <button class="secondBtn secondBtn2" onclick="location.href = '${contextPath}/petTaxiPage' ">
                    펫 택시 이용하기
                  </button>
                </div>
              </div>
            </section>
            <div class="sideTitleR sideTitleR1" id="hos_bar">
              <p class="hover_text">병원찾기</p>
              <img src="${contextPath}/img/arrow.png" alt="화살표" class="bar_arrow" />
            </div>
          </div>
          <!-- ch -->
          <div class="sideTitle sideTitle4" id="qna_bar">
            <h2 class="hover_text">실시간 상담</h2>
            <img src="${contextPath}/img/arrow.png" alt="화살표" class="bar_arrow" />
          </div>
          <div class="contents" id="third">
            <div class="sideTitle sideTitle3">
              <h2>실시간 상담</h2>
            </div>
            <!-- 크기조절 -->
            <div class="sideTitle sideTitle3">
              <h2>실시간 상담</h2>
            </div>
            <section class="message">
              <img src="${contextPath}/img/hamster.png" alt="햄스터" />
              <div class="chatBox">
                <p class="chatBoxP question">
                  <span> Q.강아지가 삼겹살을 먹었는데 괜찮을까요?</span>
                </p>
                <p class="chatBoxP answer">
                  <span>A.일시적으로는 괜찮지만 자주 주면 배탈이 날 수 있습니다.</span>
                </p>
                <p class="chatBoxP question">
                  <span> Q.고양이 발톱이 빠졌는데 바로 병원으로 가야할까요?</span>
                </p>
                <p class="chatBoxP answer">
                  <span>
                    A.고양이 손톱은 신경에 가까이 있어 꼭 병원에 와서 확인해야
                    합니다.</span>
                </p>
                <p class="chatBoxP question">
                  <span>
                    Q.토끼 이빨이 너무 많이 자랐는데 전문 병원을
                    예약해야할까요?</span>
                </p>
                <p class="chatBoxP answer">
                  <span>A.집토끼는 특히 이빨 관리에 주의를 요합니다.</span>
                </p>
              </div>
            </section>
            <section class="messageText">
              <p class="thirdTitle">
                <span class="color">24</span>
                <span>시간 전문가에게<br />
                  실시간</span>
                <span class="color">무료 상담</span>
                <span>받아보세요!</span>
              </p>
              <div class="showQ">
                <p>최근 등록된 질문</p>
                <p id="question">20</p>
              </div>
              <div class="showQ">
                <p>전문가 답변 수</p>
                <p id="answer">15</p>
              </div>
              <button class="goQ" onclick="location.href = '${contextPath}/board/listArticles.do' ">
                <p>바로 질문하기</p>
              </button>
            </section>
            <!-- <div class="sideTitleR sideTitleR1">
          <p>병원찾기</p>
        </div> -->
            <div class="sideTitleR sideTitleR2" id="eme_bar">
              <p class="hover_text">
                <span class="hover_text">24</span>시간 응급실•펫택시
              </p>
              <img src="${contextPath}/img/arrow.png" alt="화살표" class="bar_arrow" />
            </div>
          </div>
        </div>

        <div class="chatbot">
          <iframe width="350" height="430" allow="microphone;"
            src="https://console.dialogflow.com/api-client/demo/embedded/8ae56697-27ef-4b6a-9676-d20da5e8834b"></iframe>
          <a href="#" onclick="fn_chatbot()">
            <img src="${contextPath}/img/chatbot.svg" alt="챗봇이미지" />
          </a>
        </div>
      </body>
      <script type="text/javascript"></script>

      </html>