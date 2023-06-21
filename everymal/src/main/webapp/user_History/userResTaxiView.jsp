<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      <c:set var="contextPath" value="${pageContext.request.contextPath}" />
      <% request.setCharacterEncoding("utf-8"); %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
          <meta charset="UTF-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title> 펫 택시 </title>

          <link rel="stylesheet" href="${contextPath}/css/reset.css" />
          <link rel="stylesheet" href="${contextPath}/css/common.css" />
          <link rel="stylesheet" href="${contextPath}/css/petTaxiPage.css" />
          <link rel="stylesheet" href="${contextPath}/css/datepicker.min.css">
          <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
          <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
          <script src="http://code.jquery.com/jquery-latest.min.js"></script>
          <script type="text/javascript">
            $(function () {
              document.getElementById('callTaxiList').onclick = function () {
                function openPopup() {
                  let imgVar = "";
                  const tx_local = $("select[name='tx_local']").val();
                  const url = "${pageContext.request.contextPath}/petTaxiPage/pet_Taxi/taxiList.jsp?tx_local=" + encodeURIComponent(tx_local);
                  window.name = "taxiParentForm";
                  openWin = window.open(url, "childForm", "width=700, height=400, resizable=no");

                  let taxiDriverImg = document.querySelector(".taxiDriverImg"); // 클래스명이 taxiDriverImg인 img 태그 선택
                  taxiDriverImg.src = imgVal; // 선택한 img 태그의 src 속성값 변경
                }
                openPopup();
              };
            });

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
            <h2 class="pagetitlte">펫 택시</h2>
            <section id="sidebar_Area">
              <div class="sidebars" id="sb_sidebar">

              </div>
            </section>


            <section id="petTaxiArea">
              <form action="${contextPath}/petTaxiPage/reservPetTaxi.do" method="post" id="taxiParentForm">
                <div class="insertArea">
                  <div class="userInsertBox">
                    <div class="taxiInfoBox">
                      <img class="taxiDriverImg framed"
                        src="${contextPath}/taxiImgdown.do?tx_code=${viewHis.tx_code}&tx_img=${viewHis.tx_img}"
                        alt="기사님 사진 들어갈 자리"><br>
                      <div class="form-group taxi_info">
                        <span>기사 이름</span>
                        <input class="form-field" type="text" name="tx_name" value="${viewHis.tx_name}" readonly>
                      </div>
                      <div class="form-group taxi_info">
                        <span>택시 번호</span>
                        <input class="form-field" type="text" name="tx_number" value="${viewHis.tx_number}" readonly>
                      </div>
                      <div class="form-group taxi_info">
                        <span>연락처</span>
                        <input class="form-field" type="text" name="tx_tel" value="${viewHis.tx_tel}" readonly>
                      </div>
                      <div class="form-group tx_intro taxi_info">
                        <span>인삿말</span>
                        <textarea class="form-field" name="tx_intro" id="tx_intro"
                          readonly>${viewHis.tx_intro}</textarea>
                      </div>

                    </div>
                  </div>
                </div>
                <!-- ====================================================== -->
                <div class="resultArea">
                  <div class="TaxiListBox">
                    <div class="TaxiList activeList">
                      <div class="taxiResBox">
                        <h2> Reservation Form</h2>
                        <div class="form-group">
                          <span>예약자 성명</span>
                          <input class="form-field" type="text" name="res_name" value="${viewHis.res_name}" readonly>
                        </div>
                        <div class="form-group">
                          <span>희망예약날짜</span>
                          <input class="form-field" name="tx_reserv_day" type="date" value="${viewHis.tx_reserv_day}"
                            readonly>
                        </div>
                        <div class="form-group">
                          <span>희망 예약시간</span>
                          <input class="form-field" name="tx_restime" type="time" value="${viewHis.tx_restime}"
                            readonly>
                        </div>
                        <div class="form-group">
                          <span>출발지</span>
                          <input class="form-field" name="tx_dep" type="text" value="${viewHis.tx_dep}" readonly>
                        </div>
                        <div class="form-group">
                          <span>도착지</span>
                          <input class="form-field" name="tx_arr" type="text" value="${viewHis.tx_arr}" readonly>
                        </div>
                      </div>
                    </div>
                    <!-- <div class="form-group">
                      <input type="submit" id="reservationBtn" value="예약하기">
                    </div> -->
                  </div>
                </div>
              </form>
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