<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"
  import="java.util.*" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <c:set var="contextPath" value="${pageContext.request.contextPath}" />
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <% request.setCharacterEncoding("utf-8"); %>
          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>My page</title>
            <link rel="stylesheet" type="text/css" href="${contextPath }/css/reset.css" />
            <link rel="stylesheet" type="text/css" href="${contextPath }/css/common.css" />
            <link rel="stylesheet" type="text/css" href="${contextPath }/css/reservation.css" />
            <script src="${contextPath }/js/jquery-3.6.3.min.js"></script>
            <script src="${contextPath }/js/jquery-ui.min.js"></script>
            <script src="http://code.jquery.com/jquery-latest.min.js"></script>
            <script type="text/javascript">

              $(function () {
                document.getElementById('bringPetInfo').onclick = function () {
                  function openPopup() {
                    var popupWidth = 700;
                    var popupHeight = 400;
                    var popupX = (window.screen.width - popupWidth) / 2;
                    var popupY = (window.screen.height - popupHeight) / 2;
                    var options = "width=" + popupWidth + ", height=" + popupHeight + ", left=" + popupX + ", top=" + popupY + ", resizable=no";
                    window.name = "reservationForm";
                    openWin = window.open("${pageContext.request.contextPath}/hospital/hos_Reservation/listPopup.jsp", "childForm", options);
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
              <h2 class="pagetitlte">예약하기</h2>
              <section id="sidebar_Area">
                <div class="sidebars" id="sb_sidebar">
                </div>
              </section>
              <div class="background"></div>
              <div class="Reservation">
                <form action="${contextPath }/hospital/reservation.do" name="reservForm" id="reservForm" method="post">
                  <input type="hidden" name="hos_code" value="${hosData.hos_code}">
                  <fieldset>
                    <legend>예약하기 폼</legend>
                    <p>
                      <label class="infoTitle" for="name">이름</label>
                      <input type="text" id="name" name="user_name" placeholder="예약자 성명" />
                      <input type="hidden" id="pet_code" name="pet_code" />
                    </p>
                    <p>
                      <label class="infoTitle" for="tel">연락처</label>
                      <select name="tel_front" id="tel1">
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="018">018</option>
                      </select>
                      <input type="tel" name="tel_end" id="tel">
                    </p>
                    <p>
                      <label class="infoTitle" for="pet_name">반려동물 이름</label>
                      <input type="text" id="pet_name" name="pet_name" />
                      <input type="button" id="bringPetInfo" onclick="openPopup()" value="내 반려동물 정보 불러오기">
                    </p>
                    <p>
                      <label class="infoTitle" for="race">동물 종</label>
                      <select name="pet_types" id="petRace">
                        <option>선택</option>
                        <option value="개">개</option>
                        <option value="고양이">고양이</option>
                        <option value="토끼">토끼</option>
                        <option value="어류">어류</option>
                        <option value="조류">조류</option>
                        <option value="소동물">햄스터 등 소동물</option>
                        <option value="직접입력">직접입력</option>
                      </select>
                      <input type="text" name="direct" id="pet_types" placeholder="목록에 없는 경우 직접 입력해주세요." size="30">
                    </p>
                    <p>
                      <label class="infoTitle" for="pet_age">나이</label>
                      <input type="number" id="pet_age" name="pet_age" />
                    </p>
                    <p class="pet_gender">
                      <label for="pet_sex" class="genderC">반려동물 성별</label>
                      <input type="radio" id="pet_sex_f" name="pet_sex" value="0">♀
                      <input type="radio" id="pet_sex_m" name="pet_sex" value="1">♂
                    </p>
                    <p class="pet_number">
                      <label for="pet_number" class="infoTitle">반려동물 등록번호</label>
                      <input type="text" id="pet_number" name="pet_number">
                    </p>
                    <p class="b_type">
                      <label for="b_type" class="infoTitle">반려동물 혈액형</label>
                      <select name="b_type" id="b_type">
                        <option>선택</option>
                        <optgroup label="개">
                          <option value="DEA1.1">DEA1.1</option>
                          <option value="DEA1.2">DEA1.2</option>
                          <option value="DEA1.3">DEA1.3</option>
                          <option value="DEA3">DEA3</option>
                          <option value="DEA4">DEA4</option>
                          <option value="DEA5">DEA5</option>
                          <option value="DEA7">DEA7</option>
                        </optgroup>
                        <optgroup label="고양이">
                          <option value="A">A</option>
                          <option value="B">B</option>
                          <option value="AB">AB</option>
                        </optgroup>
                      </select>
                    </p>
                    <p class="pet_weight">
                      <label for="pet_weight" class="infoTitle">반려동물 몸무게</label>
                      <input type="text" id="pet_weight" name="pet_weight">
                    </p>
                    <label class="infoTitle" for="hos_name">병원 이름</label>
                    <input type="text" id="hospitalName" name="hos_name" value="${hosData.hos_name }" readonly />

                    <p>
                      <label class="infoTitle" for="res_date">희망 예약날짜</label>
                      <input type="date" id="datepicker" name="res_date" />
                    </p>
                    <p>
                      <label class="infoTitle" for="res_time">희망 예약시간</label>
                      <input type="time" id="timepicker" name="res_time" />
                    </p>
                    <p>
                      <label class="infoTitle" for="hos_sub">진료 과목</label>
                      <input type="checkbox" id="hos_gm" name="hos_sub" value="외과">외과
                      <input type="checkbox" id="hos_im" name="hos_sub" value="내과">내과
                      <input type="checkbox" id="hos_em" name="hos_sub" value="응급의학과">응급의학과
                      <input type="checkbox" id="hos_rm" name="hos_sub" value="재활의학과">재활의학과
                      <input type="checkbox" id="hos_vac" name="hos_sub" value="예방접종">예방접종
                    </p>
                    <p>
                      <label class="infoTitle notice" for="res_etc" id="res_etc">요구사항</label>
                      <textarea name="res_etc" style="resize: none" placeholder="반려동물의 특이사항이나 주의할 점을 적어주세요."></textarea>
                    </p>
                    <button form="reservForm" class="btnOk" type="submit">예약하기</button>
                  </fieldset>
                </form>
              </div>



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