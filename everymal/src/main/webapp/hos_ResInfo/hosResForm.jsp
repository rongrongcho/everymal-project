<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

      <% request.setCharacterEncoding("utf-8"); %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
          <meta charset="UTF-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>My page</title>
          <link rel="stylesheet" href="${contextPath}/css/reset.css">
          <link rel="stylesheet" href="${contextPath}/css/common.css">
          <link rel="stylesheet" href="${contextPath}/css/hosResDetail.css">
          <script src="${contextPath}/js/jquery-3.6.3.min.js"></script>

        </head>
        <!--hosre.뭐뭐로 하면됨 -->

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
            </header>>
            <h2 class="pagetitlte">예약 상세정보</h2>
            <section id="sidebar_Area">
              <div class="sidebars" id="sb_sidebar">
              </div>
            </section>

            <section id="left_Area">
              <div class="profileBox">
                <div class="proimgBox">
                  <img class="proImg"
                    src="${contextPath}/hosProImgDown.do?hos_pro=${hosImgVO.hos_pro}&hos_code=${hos_code}"
                    alt="나의 프로필 이미지" />
                </div>
                <div class="speech_bubble">
                  <img class="bubbleImg" src="${contextPath}/img/말풍선.svg" alt="말풍선 배경">
                  <!--  <p><span class="userID">${hosres.hos_id}</span>님 환영합니다!</p>-->
                  <p><span class="userID" name="hos_username">${hosmypageinfoVO.hos_username }</span>님 환영합니다!</p>
                </div>
              </div>
              <div class="leftCategory">
                <ul class="CatrgoryBox">
                  <li class="firstMenu">마이페이지
                    <ul>
                      <li class="secondMenu"><a href="${contextPath }/hosMypageInfo/myInfo.do">내 정보 관리</a></li>
                      <li class="secondMenu"><a href="${contextPath }/hosMypageInfo/myHosInfo.do">내 병원 관리</a></li>
                      <li class="secondMenu"><a href="${contextPath }/hosReview/hosReviewList.do">병원리뷰 관리</a></li>
                      <li class="secondMenu"><a href="${contextPath }/hosReply/listMyReply.do">답변 관리</a></li>
                    </ul>
                  </li>
                  <li class="firstMenu">예약
                    관리
                    <ul>
                      <li class="secondMenu"><a
                          href="${contextPath }/hosres/hosResList.do?hos_code=${hosres.hos_code}">예약관리</a></li>
                    </ul>
                  </li>


                </ul>
              </div>


            </section>
            <div class="grey_line"></div>
            <section id="tab_Area">
              <div class="tabcontents">
                <div id="tabBox" class="tab-content ">
                  <div class="greybox"></div>
                  <div class="inputbox"></div>
                  <p class="res_code"><span>예약코드 : </span>${hosres.res_code}</p>
                  <form action="${contextPath }/hosres" name="reservForm" id="reservForm" method="post">
                    <fieldset>
                      <legend>예약 상세보기 폼</legend>

                      <p>
                        <label class="infoTitle" for="name">이름</label>
                        <input type="text" id="name" name="user_name" value="${hosres.user_name}" disabled />
                      </p>
                      <p>
                        <label class="infoTitle" for="tel">연락처</label>

                        <input type="tel" name="tel_end" id="tel" value="${hosres.user_tel}" disabled />
                      </p>
                      <p>
                        <label class="infoTitle" for="pet_name">반려동물 이름</label>
                        <input type="text" id="pet_name" name="pet_name" value="${hosres.pet_name}" disabled />

                      </p>
                      <p>
                        <label class="infoTitle" for="pet_types">동물 종</label>
                        <input type="text" id="pet_types" name="pet_types" value="${hosres.pet_types}" disabled />
                      </p>
                      <p>
                        <label class="infoTitle" for="pet_age">나이</label>
                        <input type="number" id="pet_age" name="pet_age" value="${hosres.pet_age }" disabled />
                      </p>
                      <p class="pet_gender">
                        <label for="pet_sex" class="genderC">반려동물 성별</label>
                        <c:choose>
                          <c:when test="${hosres.pet_sex=='0'}">
                            <input type="radio" id="pet_sex_f" name="pet_sex" value="0" checked disabled>♀
                            <input type="radio" id="pet_sex_m" name="pet_sex" value="1" disabled>♂
                          </c:when>
                          <c:when test="${hosres.pet_sex=='1'}">
                            <input type="radio" id="pet_sex_f" name="pet_sex" value="0" disabled>♀
                            <input type="radio" id="pet_sex_m" name="pet_sex" value="1" checked disabled>♂
                          </c:when>
                        </c:choose>

                      </p>
                      <p class="pet_number">
                        <label for="pet_number" class="infoTitle">반려동물 등록번호</label>
                        <input type="text" id="pet_number" name="pet_number" value="${hosres.pet_number}" disabled>
                      </p>
                      <p class="b_type">
                        <label for="b_type" class="infoTitle">반려동물 혈액형</label>
                        <input type="test" name="b_type" id="b_type" value="${hosres.b_type}" disabled />

                      </p>
                      <p class="pet_weight">
                        <label for="pet_weight" class="infoTitle">반려동물 몸무게</label>
                        <input type="text" id="pet_weight" name="pet_weight" value="${hosres.pet_weight}" disabled>
                      </p>
                      <label class="infoTitle" for="hos_name">병원 이름</label>
                      <input type="text" id="hospitalName" name="hos_name" value="${hosres.hos_name }" disabled />

                      <p>
                        <label class="infoTitle" for="res_date">희망 예약날짜</label>
                        <input type="date" id="datepicker" name="res_date" value="${hosres.res_date }" disabled />
                      </p>
                      <p>
                        <label class="infoTitle" for="res_time">희망 예약시간</label>
                        <input type="text" id="timepicker" name="res_time" value="${hosres.res_time } 시" disabled />
                      </p>
                      <p>
                        <label class="infoTitle" for="hos_sub">진료 과목</label>
                        <label for="hos_gm" class="hos_sub_label">외과</label>
                        <input type="checkbox" id="hos_gm" name="hos_sub" value="외과" ${fn:contains(hosres.hos_sub, '외과'
                          ) ? 'checked' : '' } disabled>

                        <label for="hos_im" class="hos_sub_label">내과</label>
                        <input type="checkbox" id="hos_im" name="hos_sub" value="내과" ${fn:contains(hosres.hos_sub, '내과'
                          ) ? 'checked' : '' } disabled>

                        <label for="hos_em" class="hos_sub_label">응급의학과</label>
                        <input type="checkbox" id="hos_em" name="hos_sub" value="응급의학과"
                          ${fn:contains(hosres.hos_sub, '응급의학과' ) ? 'checked' : '' } disabled>

                        <label for="hos_rm" class="hos_sub_label">재활의학과</label>
                        <input type="checkbox" id="hos_rm" name="hos_sub" value="재활의학과"
                          ${fn:contains(hosres.hos_sub, '재활의학과' ) ? 'checked' : '' } disabled>

                        <label for="hos_vac" class="hos_sub_label">예방접종</label>
                        <input type="checkbox" id="hos_vac" name="hos_sub" value="예방접종"
                          ${fn:contains(hosres.hos_sub, '예방접종' ) ? 'checked' : '' } disabled>

                      </p>
                      <p>
                        <label class="infoTitle notice" for="res_etc" id="res_etc">요구사항</label>
                        <textarea name="res_etc" style="resize: none" disabled>${hosres.res_etc}</textarea>
                      </p>
                      <button form="reservForm" class="btnOk" type="submit">목록으로 돌아가기</button>
                    </fieldset>
                  </form>
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
          <script>

          </script>
        </body>

        </html>