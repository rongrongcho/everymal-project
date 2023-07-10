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
          <title>예약 상세정보 페이지</title>
          <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
          <link rel="stylesheet" href="${contextPath}/resources/css/common.css">
          <link rel="stylesheet" href="${contextPath}/resources/css/userReservationView.css">
          <script src="${contextPath}/resources/js/jquery-3.6.3.min.js"></script>
          <script type="text/javascript">
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
            <h2 class="pagetitlte">예약 상세정보</h2>
            <section id="sidebar_Area">
              <div class="sidebars" id="sb_sidebar">
              </div>
            </section>
            <section id="left_Area">
              <div class="profileBox">
                <div class="proimgBox">
                  <img class="proImg"
                    src="${contextPath}/proimgdown.do?imageFileName=${userVO.user_imgName}&user_id=${log_id}"
                    width="200" alt="사용자 프로필 사진">
                </div>
                <div class="speech_bubble">
                  <img class="bubbleImg" src="${contextPath}/resources/img/말풍선.png" alt="말풍선 배경">
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
                      <li class="secondMenu"><a href="${contextPath}/userMypage/myQuestion.so">내 질문 관리</a></li>
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
            <div class="grey_line"></div>
            <section id="tab_Area">
              <div class="tabcontents">
                <div id="tabBox" class="tab-content ">
                  <div class="greybox"></div>
                  <div class="inputbox"></div>
                  <p class="res_code"><span>예약코드 : </span>N0127149137</p>
                  <form action="${contextPath }/history" name="reservForm" id="reservForm" method="post">
                    <fieldset>
                      <legend>예약하기 폼</legend>

                      <p>
                        <label class="infoTitle" for="name">이름</label>
                        <input type="text" id="name" name="user_name" placeholder="예약자 성명" value="${
                        viewHis.user_name}" />
                      </p>
                      <p>
                        <label class="infoTitle" for="tel">연락처</label>
                        <select name="usertel_front" id="tel_front">
                          <option value="010" ${viewHis.user_tel.substring(0,3)=="010" ? 'selected' : '' }>010</option>
                          <option value="011" ${viewHis.user_tel.substring(0,3)=="011" ? 'selected' : '' }>011</option>
                          <option value="018" ${viewHis.user_tel.substring(0,3)=="018" ? 'selected' : '' }>018</option>
                        </select>
                        <input type="tel" name="usertel_end" id="tel_end" value="${viewHis.user_tel.substring(3)}">
                        <c:set var="user_tel" value="${usertel_front }${usertel_end }" />
                        <input type="hidden" name="user_tel" value="${uesr_tel }">
                      </p>
                      <p>
                        <label class="infoTitle" for="pet_name">반려동물 이름</label>
                        <input type="text" id="pet_name" name="pet_name" value="${
                        viewHis.pet_name}" />
                      </p>
                      <p>
                        <label class="infoTitle" for="race">동물 종</label>
                        <select name="pet_types" id="petRace">
                          <option>${viewHis.pet_types}</option>
                          <option value="dog">개</option>
                          <option value="cat">고양이</option>
                          <option value="rabbit">토끼</option>
                          <option value="fish">어류</option>
                          <option value="bird">조류</option>
                          <option value="hamster">햄스터 등 소동물</option>
                          <option value="direct">직접입력</option>
                        </select>

                      </p>
                      <p>
                        <label class="infoTitle" for="pet_age">나이</label>
                        <input type="number" id="pet_age" name="pet_age" value="${viewHis.pet_age}" />
                      </p>
                      <p class="pet_gender">
                        <label for="pet_sex" class="genderC">반려동물 성별</label>
                        <c:set var="pet_sex_f_checked" value="${viewHis.pet_sex eq '0' ? 'checked' : ''}" />
                        <c:set var="pet_sex_m_checked" value="${viewHis.pet_sex eq '1' ? 'checked' : ''}" />
                        <input type="radio" id="pet_sex_f" name="pet_sex" value="0" ${pet_sex_f_checked }>♀
                        <input type="radio" id="pet_sex_m" name="pet_sex" value="1" ${pet_sex_m_checked }>♂
                      </p>
                      <p class="pet_number">
                        <label for="pet_number" class="infoTitle">반려동물 등록번호</label>
                        <input type="text" id="pet_number" name="pet_number" value="${viewHis.pet_number}">
                      </p>
                      <p class="b_type">
                        <label for="b_type" class="infoTitle">반려동물 혈액형</label>
                        <select name="b_type" id="b_type">
                          <option>${viewHis.b_type}</option>
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
                        <input type="text" id="pet_weight" name="pet_weight" value="${viewHis.pet_number}">
                      </p>
                      <label class="infoTitle" for="hos_name">병원 이름</label>
                      <input type="text" id="hospitalName" name="hos_name" value="${viewHis.hos_name}" />

                      <p>
                        <label class="infoTitle" for="res_date">희망 예약날짜</label>
                        <input type="date" id="datepicker" name="res_date" value="${viewHis.res_date
                      }" />
                      </p>
                      <p>
                        <label class="infoTitle" for="res_time">희망 예약시간</label>
                        <input type="time" id="timepicker" name="res_time" value="${viewHis.res_time}" />
                      </p>
                      <p>

                        <label class="infoTitle" for="hos_sub">진료 과목</label>
                        <label class="subLa hos_sub_label" for="hos_gm">외과</label>
                        <input class="subCheck " type="checkbox" id="hos_gm" name="hos_sub" value="외과"
                          ${fn:contains(viewHis.hos_sub, '외과' ) ? 'checked' : '' }>

                        <label class="subLa hos_sub_label" for="hos_im">내과</label>
                        <input class="subCheck " type="checkbox" id="hos_im" name="hos_sub" value="내과"
                          ${fn:contains(viewHis.hos_sub, '내과' ) ? 'checked' : '' }>

                        <label class="subLa hos_sub_label" for="hos_em">응급의학과</label>
                        <input class="subCheck" type="checkbox" id="hos_em" name="hos_sub" value="응급의학과"
                          ${fn:contains(viewHis.hos_sub, '응급의학과' ) ? 'checked' : '' }>

                        <label class="subLa hos_sub_label" for="hos_rm">재활의학과</label>
                        <input class="subCheck" type="checkbox" id="hos_rm" name="hos_sub" value="재활의학과"
                          ${fn:contains(viewHis.hos_sub, '재활의학과' ) ? 'checked' : '' }>

                        <label class="subLa hos_sub_label" for="hos_vac">예방접종</label>
                        <input class="subCheck" type="checkbox" id="hos_vac" name="hos_sub" value="예방접종"
                          ${fn:contains(viewHis.hos_sub, '예방접종' ) ? 'checked' : '' }>

                      </p>


                      <p>
                        <label class="infoTitle notice" for="res_etc" id="res_etc">요구사항</label>
                        <textarea name="res_etc" style="resize: none"
                          placeholder="반려동물의 특이사항이나 주의할 점을 적어주세요.">${viewHis.res_etc}</textarea>
                      </p>
                      <button form="reservForm" class="btnOk" type="submit">목록보기</button>
                    </fieldset>
                  </form>
                </div>
              </div>
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
          <script>

          </script>
        </body>

        </html>