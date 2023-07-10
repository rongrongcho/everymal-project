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

          <link rel="stylesheet" href="${contextPath }/resources/css/reset.css" />
          <link rel="stylesheet" href="${contextPath }/resources/css/common.css" />
          <link rel="stylesheet" href="${contextPath }/resources/css/petTaxiPage.css" />
          <link rel="stylesheet" href="${contextPath }/resources/css/datepicker.min.css">
          <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
          <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
          <script src="http://code.jquery.com/jquery-latest.min.js"></script>
          <script type="text/javascript">
            $(function () {
              document.getElementById('callTaxiList').onclick = function () {
                function openPopup() {
                  let imgVar = "";
                  const tx_local = $("select[name='tx_local']").val();
                  const url = "${pageContext.request.contextPath}/pet_Taxi/taxiList.do?tx_local=" + encodeURIComponent(tx_local);
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
            <h2 class="pagetitlte">펫 택시</h2>
            <section id="sidebar_Area">
              <div class="sidebars" id="sb_sidebar">

              </div>
            </section>


            <section id="petTaxiArea">
              <form action="${contextPath}/pet_Taxi/reservPetTaxi.do" method="post" id="taxiParentForm">

                <div class="insertArea">
                  <div class="userInsertBox">
                    <!--  <h2> 입력폼</h2>-->
                    <label class="dep-label" for="tx_dep">출발 지역</label>
                    <select name="tx_local" class="tx_local w-btn w-btn-indigo">
                      <option value="">출발지를 선택해주세요</option>
                      <option value="경기도">경기도</option>
                      <option value="서울특별시">서울 특별시</option>
                      <option value="부산광역시">부산 광역시</option>
                      <option value="광주광역시">광주 광역시</option>
                      <option value="대전광역시">대전 광역시</option>
                      <option value="인천광역시">인천 광역시</option>
                      <option value="대구광역시">대구 광역시</option>
                      <option value="대구광역시">울산 광역시</option>
                    </select>
                    <input type="button" class="w-btn2 w-btn-blue" id="callTaxiList" onclick="openPopup()"
                      value="택시 조회 목록" />
                    <div class="taxiInfoBox">

                      <img class="taxiDriverImg framed" src="${contextPath}/resources/img/taxi_de.jpg"
                        alt="기사님 사진 들어갈 자리"><br>
                      <div class="form-group taxi_info">
                        <span>기사 이름</span>
                        <input class="form-field" type="text" name="tx_name" placeholder="출발지를 선택해주세요" readonly>
                        <input type="hidden" name="tx_name" placeholder="택시를 선택해주세요" />
                      </div>
                      <div class="form-group taxi_info">
                        <span>택시 번호</span>
                        <input class="form-field" type="text" name="tx_number" readonly placeholder="택시를 선택해주세요">
                        <input type="hidden" name="tx_number" />
                      </div>
                      <div class="form-group taxi_info">
                        <span>연락처</span>
                        <input class="form-field" type="text" name="tx_tel" placeholder="택시를 선택해주세요" readonly>


                      </div>
                      <div class="form-group tx_intro taxi_info">
                        <span>인삿말</span>
                        <!--   <input type="text" name="tx_intro" id="tx_intro" disabled /> -->
                        <textarea class="form-field" name="tx_intro" id="tx_intro" readonly></textarea>
                        <input type="hidden" name="tx_tel" />
                        <input type="hidden" name="tx_code" />
                        <input type="hidden" name="tx_img" />
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
                        <input type="hidden" name="user_code" value="${user_code}">
                        <div class="form-group">
                          <span>예약자 성명</span>
                          <input class="form-field" type="text" name="res_name" placeholder="홍길동" required>
                        </div>
                        <div class="form-group">
                          <span>희망예약날짜</span>
                          <input class="form-field" name="tx_reserv_day" type="date" required>
                        </div>
                        <div class="form-group">
                          <span>희망 예약시간</span>
                          <input class="form-field" name="tx_restime" type="time" required>
                        </div>
                        <div class="form-group">
                          <span>출발지</span>
                          <input class="form-field" name="tx_dep" type="text" placeholder="강남구청" required>
                        </div>
                        <div class="form-group">
                          <span>도착지</span>
                          <input class="form-field" name="tx_arr" type="text" placeholder="신사역" required>
                        </div>



                      </div>
                    </div>
                    <input type="submit" id="reservationBtn" value="예약하기">

                  </div>
                </div>


              </form>

            </section>


            <!-- fooooooooooooooooooooooooooooooooooooooooooooooooooooooter -->

            <footer>
              <ul class="bottomNav">
                <li>
                  <a id="footerLogo" href="${contextPath}/main.do"><img
                      src="${contextPath}/resources/img/EverymalLogo_w.svg" alt="로고"
                      style="width: 250px; height: auto" /></a>
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