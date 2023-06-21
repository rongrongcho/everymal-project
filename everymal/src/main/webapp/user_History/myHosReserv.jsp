<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- core라이브러리를 c라는 접두사를 따와서 쓰겠다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <% request.setCharacterEncoding("utf-8"); %>
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>예약 > 동물병원</title>

        <link rel="stylesheet" href="${contextPath}/css/reset.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/my_hos_res.css" />
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
          <h2 class="pagetitlte">예약관리 이용내역</h2>
          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
              <p class="sidetitles">

              </p>
            </div>
          </section>

          <section id="left_Area">
            <div class="profileBox">
              <div class="proimgBox">
                <img class="proImg"
                  src="${contextPath}/proimgdown.do?imageFileName=${userVO.user_imgName}&user_id=${log_id}">
              </div>
              <div class="speech_bubble">
                <img class="bubbleImg" src="${contextPath}/img/말풍선.png" alt="말풍선 배경">
                <p><span class="userID">${userVO.user_name}</span>님 환영합니다!</p>
              </div>
            </div>
            <div class="leftCategory">
              <ul class="CatrgoryBox">
                <li class="firstMenu">마이페이지

                  <ul>
                    <li class="secondMenu"><a href="${contextPath}/userMypage/myInfo.do">내 정보 관리</a></li>
                    <li class="secondMenu"><a href="${contextPath}/petinfo/myPetList.do">내 동물 관리</a></li>
                    <li class="secondMenu"><a href="${contextPath}/userMypage/myReview.do">내 리뷰 관리</a></li>
                    <li class="secondMenu"><a href="${contextPath}/userMypage/myQuestion.do">내 질문 관리</a></li>
                  </ul>
                </li>
                <li class="firstMenu">
                  예약관리•이용내역
                  <ul>
                    <li class="secondMenu"><a href="${contextPath}/history">병원 예약 관리</a></li>
                    <li class="secondMenu"><a href="${contextPath}/history/myPetTaxiReserv.do">택시 예약 관리</a></li>
                  </ul>
                </li>
              </ul>
            </div>


          </section>
          <div id="verticalLine"></div>
          <section id="tab_Area">



            <div class="tab_nav">
              <ul class="tab_Btns">
                <li class="tBtn tBtn1 choiceTabNav">
                  <a href="${contextPath}/history"><span class="pointT word">동물</span><span class="word">병원</span></a>
                </li>
                <li class="tBtn tBtn2 notChoice">
                  <a href="${contextPath}/history/myPetTaxiReserv.do"><span class="pointT">펫</span><span>택시</span></a>
                </li>
              </ul>
            </div>
            <div class="tabcontents">
              <div id="tabBox">
                <div class="reservationInfoArea">
                  <h3 class="boxTitles bT01">최근 예약 정보</h3>
                  <div class="rInfoBox">
                    <c:choose>
                      <c:when test="${empty recentList}">
                        <p>최근 예약 정보가 없습니다.</p>
                      </c:when>
                      <c:when test="${not empty recentList}">
                        <c:forEach var="rec" items="${recentList}">
                          <div class="reservBox">
                            <a href="${contextPath}/history/viewHistory.do?res_code=${rec.r_res_code}"
                              style="display: flex">
                              <img
                                src="${contextPath}/hosThumDown.do?hos_code=${rec.r_hos_code}&hos_thum=${rec.hos_thum}"
                                alt="병원 이미지">
                              <div class="r_smallBox" style="display: flex; flex-direction: column">
                                <p class="r_info_text"><span class="r_infotit">예약 일시</span>${rec.r_res_date}</p>
                                <p class="r_info_text"><span class="r_infotit">예약 접수</span>${rec.r_receipt_date}</p>
                                <p class="r_info_text"><span class="r_infotit">예약 병원</span> ${rec.r_hos_name}</p>

                              </div>
                            </a>
                          </div>
                        </c:forEach>
                      </c:when>
                    </c:choose>
                  </div>
                </div>
                <div class="historyArea">
                  <h3 class="boxTitles bT02">지난 이용 내역</h3>

                  <!-- ==================================================== -->
                  <div class="historyBox">
                    <div class="historyBoard">
                      <form action="">
                        <table width="100%">
                          <tr>
                            <th>예약번호</th>
                            <th>접수일</th>
                            <th>예약병원</th>
                            <th>예약날짜</th>

                          </tr>
                          <c:choose>
                            <c:when test="${empty historyList}">
                              <!-- 이용내역 x   -->
                              <tr>
                                <td colspan="5">이용 내역이 없습니다.</td>
                              </tr>
                            </c:when>
                            <c:when test="${!empty historyList}">
                              <!-- 이용내역 > 0 -->
                              <c:forEach var="his" items="${historyList}">

                                <tr class="historyLink">

                                  <td><a
                                      href="${contextPath}/history/viewHistory.do?res_code=${his.res_code}">${his.res_code}</a>
                                  </td>
                                  <td><a
                                      href="${contextPath}/history/viewHistory.do?res_code=${his.res_code}">${his.receipt_date}</a>
                                  </td>
                                  <td><a
                                      href="${contextPath}/history/viewHistory.do?res_code=${his.res_code}">${his.hos_name}
                                    </a> </td>
                                  <td><a
                                      href="${contextPath}/history/viewHistory.do?res_code=${his.res_code}">${his.res_date}</a>
                                  </td>


                                </tr>

                              </c:forEach>
                            </c:when>
                          </c:choose>
                        </table>
                      </form>

                    </div>
                  </div>
                </div>
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