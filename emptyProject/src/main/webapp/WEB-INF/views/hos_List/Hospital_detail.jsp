<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <c:set var="contextPath" value="${pageContext.request.contextPath}" />
        <!DOCTYPE html>
        <html lang="ko">

        <head>
          <meta charset="UTF-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>동물병원 상세정보 페이지</title>
          <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/reset.css" />
          <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/common.css" />
          <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/hospital_detail.page.css" />
          <script src="${contextPath }/resources/js/jquery-3.6.3.min.js"></script>
          <script type="text/javascript">
            function clip() {
              var url = '';
              var textarea = document.createElement("textarea");
              document.body.appendChild(textarea);
              url = '${hosData.hos_addr1} ${hosData.hos_addr2}';
              textarea.value = url;
              textarea.select();
              document.execCommand("copy");
              document.body.removeChild(textarea);
              alert("병원 주소가 복사되었습니다!")
            };
            let hos_tel_pop = false;
            function cliptel() {
              if (hos_tel_pop) {
                $("#hos_tel").css("display", "none");
                hos_tel_pop = false;
              } else {
                $("#hos_tel").css("display", "inline");
                hos_tel_pop = true;
              }

            }
          </script>
          <style type="text/css">
            #hos_tel {
              position: absolute;
              width: 150px;
              height: 50px;
              margin-left: 95%;

              /*border: 2px solid #F27F3D;*/
              line-height: 50px;
              display: none;
            }
          </style>
        </head>

        <body>
          <div class="container">
            <!--header시작-->
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
            <section id="sidebar_Area">
              <div class="cidebars" id="hp_sidebar"> </div>
            </section>
            <!--header영역 끝-->
            <!--컨텐츠영역 시작-->
            <div class="contentArea">

              <h2>${hosData.hos_name}</h2>
              <input type="hidden" name="hos_name" id="hos_name" value="${hosData.hos_name }" />
              <input type="hidden" name="hos_code" id="hos_code" value="${hosData.hos_code }" />
              <div id="line"></div>
              <!--동물병원정보 섹션 시작-->
              <section id="hospitalInfo">
                <div class="hospitalPic">

                  <img src="${contextPath}/mainImgDown.do?hos_img=${hosImgVO.himg1}&hos_code=${hosData.hos_code}"
                    class="bigPic" alt="내부사진1">

                  <ul>
                    <li><img src="${contextPath}/mainImgDown.do?hos_img=${hosImgVO.himg1}&hos_code=${hosData.hos_code}"
                        class="smallPic" alt="내부사진1"></li>
                    <li><img src="${contextPath}/mainImgDown.do?hos_img=${hosImgVO.himg2}&hos_code=${hosData.hos_code}"
                        class="smallPic" alt="내부사진2"></li>
                    <li><img src="${contextPath}/mainImgDown.do?hos_img=${hosImgVO.himg3}&hos_code=${hosData.hos_code}"
                        class="smallPic" alt="내부사진3"></li>
                  </ul>
                </div>
                <div id="infoDetail">
                  <div class="small_p">
                    <h3>진료시간 : <span>
                        <c:if test="${hosData.hos_24 eq 1}">24시간 영업</c:if>
                        <c:if test="${hosData.hos_24 eq 0}"> ${hosData.hos_time_s}~${hosData.hos_time_e}</c:if>
                      </span></h3>
                    <h3>영업 요일 :
                      <span>
                        ${fn:contains(hosData.hos_365, 'mon') ? '월요일' : ''}/
                        ${fn:contains(hosData.hos_365, 'tue') ? '화요일' : ''}/
                        ${fn:contains(hosData.hos_365, 'wed') ? '수요일' : ''}/
                        ${fn:contains(hosData.hos_365, 'thu') ? '목요일' : ''}/
                        ${fn:contains(hosData.hos_365, 'fri') ? '금요일' : ''}/
                        ${fn:contains(hosData.hos_365, 'sat') ? '토요일' : ''}/
                        ${fn:contains(hosData.hos_365, 'sun') ? '일요일' : ''}
                      </span>
                    </h3>
                    <div id="hos_intro">
                      <p>「${hosData.hos_intro}」</p>
                    </div>
                  </div>
                  <div class="big_p">
                    <h3 class="h3_first">진료과목 : <span>
                        <c:if test="${hosData.hos_gs eq 1}">외과 /</c:if>
                        <c:if test="${hosData.hos_gs eq 0}"></c:if>
                        <c:if test="${hosData.hos_im eq 1}">내과/ </c:if>
                        <c:if test="${hosData.hos_im eq 0}"></c:if>
                        <c:if test="${hosData.hos_em eq 1}">응급의학과 /</c:if>
                        <c:if test="${hosData.hos_em eq 0}"></c:if>
                        <c:if test="${hosData.hos_rm eq 1}">재활의학과 /</c:if>
                        <c:if test="${hosData.hos_rm eq 0}"></c:if>
                        <c:if test="${hosData.hos_vac eq 1}">예방접종</c:if>
                        <c:if test="${hosData.hos_vac eq 0}"></c:if>
                      </span></h3>
                    <h3 class="h3_first">진료동물 : <span>
                        <c:if test="${hosData.hos_dog eq 1}">개/ </c:if>
                        <c:if test="${hosData.hos_dog eq 0}"></c:if>
                        <c:if test="${hosData.hos_cat eq 1}">고양이/ </c:if>
                        <c:if test="${hosData.hos_cat eq 0}"></c:if>
                        <c:if test="${hosData.hos_small eq 1}">소동물/ </c:if>
                        <c:if test="${hosData.hos_small eq 0}"></c:if>
                        <c:if test="${hosData.hos_fish eq 1}">어류/ </c:if>
                        <c:if test="${hosData.hos_fish eq 0}"></c:if>
                        <c:if test="${hosData.hos_bird eq 1}">조류/ </c:if>
                        <c:if test="${hosData.hos_bird eq 0}"></c:if>
                        <c:if test="${hosData.hos_rep eq 1}">파충류 </c:if>
                        <c:if test="${hosData.hos_rep eq 0}"></c:if>
                        <c:if test="${hosData.hos_etc eq 1}">기타 </c:if>
                        <c:if test="${hosData.hos_etc eq 0}"></c:if>
                      </span></h3>
                  </div>

                  <div class="tel fast_contact"><a href="#" onclick="cliptel();"><img src="${contextPath }/resources/img/tel.png"
                        alt="전화하기" title="전화하기"></a></div>

                  <div class="info_btn" id="reservation"><a
                      href="${contextPath}/hospital/reservationForm.do?hos_code=${hosData.hos_code}">예약하기</a></div>
                </div>
                <div id="hos_tel">
                  &nbsp;📞${hosData.hos_tel}
                </div>

              </section>
              <!--동물병원정보 섹션 시작 끝-->


              <!--리뷰 섹션 시작-->
              <section id="review">
                <span id="score_letter" class="score">병원 평점</span>
                <span id="score_num" class="score">${review_avg}/5</span>
                <div class="reviewCanvas">
                  <div class="review_panel">
                    <c:choose>
                      <c:when test="${empty hosReviewList}">
                        <p class="review_nonExist">아직 이 병원의 리뷰가 존재하지 않습니다.</p>
                      </c:when>
                      <c:when test="${!empty hosReviewList}">
                        <c:forEach var="hosreview" items="${hosReviewList}">
                          <div class="review_box">
                            <a href="${contextPath}/hos_ReviewInfo/hosviewReview.do?rv_code=${hosreview.rv_code}"
                              class="review_list">
                              <p class="star">
                                <c:choose>
                                  <c:when test="${hosreview.rv_rate==1}">&nbsp;🌟&nbsp;</c:when>
                                  <c:when test="${hosreview.rv_rate==2}">🌟🌟</c:when>
                                  <c:when test="${hosreview.rv_rate==3}">🌟🌟🌟</c:when>
                                  <c:when test="${hosreview.rv_rate==4}">🌟🌟🌟🌟</c:when>
                                  <c:when test="${hosreview.rv_rate==5}">🌟🌟🌟🌟🌟</c:when>

                                </c:choose>

                              </p>
                              <p class="rv_text text-ellipsis"> ${hosreview.rv_text}</p>
                            </a>
                          </div>
                        </c:forEach>
                      </c:when>
                    </c:choose>

                  </div>
                  <div class="left_right_control_panel">
                    <img class="left_btn" src="${contextPath }/resources/img/left_btn.png" alt="왼쪽버튼">
                    <img class="right_btn" src="${contextPath }/resources/img/right_btn.png" alt="오른쪽버튼">
                  </div>
                </div>
                <!--   <div class="review_write_btn"><a href="#">리뷰 쓰기</a></div> -->
                <div id="review_write_btn">리뷰 쓰기</div>

              </section>
              <!--리뷰 섹션 끝-->


              <!--지도 섹션 시작-->
              <section id="map_section">
                <div id="map" style="width:50%;height:350px;"></div>
                <div id="map_info">
                  <p id="address">주소 : <br>
                    <span>${hosData.hos_addr1} ${hosData.hos_addr2}</span>
                  </p>
                </div>
                <div class="map_btn">
                  <!-- <a class="map_share" href="#"><img src="${contextPath }/img/share.png" alt="공유하기">공유하기</a> -->
                  <a class="map_addr" href="#" onclick="clip();"><img src="${contextPath }/img/addr_copy.png"
                      alt="주소복사">주소복사</a>
                </div>
              </section>

              <!--지도 섹션 끝-->
            </div>
            <!--컨텐츠영역 끝-->
            <!--푸터 시작-->
            <footer>
              <ul class="bottomNav">
                <li>
                  <a id="footerLogo" href="${contextPath}/index.jsp"><img src="${contextPath}/resources/img/EverymalLogo_w.svg"
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


            <!--푸터끝-->
          </div>
       <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fce778b62d6e7a50391aeb034a647e51&libraries=services"></script>
          <script>
            $(function () {
              let hosPic;
              $('.smallPic').click(function () {
                hosPic = $(this).attr('src');
                $('.bigPic').attr('src', hosPic);
              });
            });


            //popup
            document.getElementById('review_write_btn').onclick = function () {
              function openPopup() {
                var popupWidth = 700;
                var popupHeight = 500;
                var popupX = (window.screen.width - popupWidth) / 2;
                var popupY = (window.screen.height - popupHeight) / 2;
                var options = "width=" + popupWidth + ", height=" + popupHeight + ", left=" + popupX + ", top=" + popupY + ", resizable=no";
                window.name = "reservationForm";
                openWin = window.open("${pageContext.request.contextPath}/hos_List/reviewPopup.jsp", "childForm", options);
              }
              openPopup();
            };




            var totalhosReview = '${review_count}';
            //alert('리뷰갯수:' + totalhosReview);
            if (totalhosReview == 0) {
              $('.right_btn').hide();
            }
            let index = 0;
            let sw = false;
            let auto;
            let mindex;
            $('.left_btn').hide();
            moveSlider(index);
            $('.reviewCanvas').hover(function () {
              clearInterval(auto);
            }, function () {
              autoSlider();
            });

            $('.left_btn').click(function () {
              index--;
              moveSlider(index);
              // alert(index);
              if (index == 0) {
                $('.left_btn').hide();
              }
              if (index < totalhosReview - 3) {
                $('.right_btn').show();
              }
            });

            $('.right_btn').click(function () {

              index++;
              moveSlider(index);
              // alert(index);
              if (index > 0) {
                $('.left_btn').show();
              }
              if (index >= totalhosReview - 3) {
                $('.right_btn').hide();
              }
            });

            function moveSlider(index) {
              if (index > 0 && index < 2) {
                $('.left_btn1').fadeIn(500);
                $('.right_btn1').fadeIn(500);
              }
              if (index == 0) {
                $('.left_btn1').fadeOut(500);
                $('.right_btn1').fadeIn(500);
              }
              if (index == 4) {//------
                $('.right_btn1').fadeOut(500);
                $('.left_btn1').fadeIn(500);
              }
              $('.review_panel').animate({
                left: -(index * 365)
              }, 'slow');
              $('.control_button').removeClass('active');
              $('.control_button').eq(index).addClass('active');
              $('.slider_text').hide();
              $('.slider_text').eq(index).fadeIn('slow');
            }

            var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
              mapOption = {
                center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
              };

            // 지도를 생성합니다    
            var map = new kakao.maps.Map(mapContainer, mapOption);

            // 주소-좌표 변환 객체를 생성합니다
            var geocoder = new kakao.maps.services.Geocoder();

            // 주소로 좌표를 검색합니다
            geocoder.addressSearch('${hosData.hos_addr1}', function (result, status) {

              // 정상적으로 검색이 완료됐으면 
              if (status === kakao.maps.services.Status.OK) {

                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao.maps.Marker({
                  map: map,
                  position: coords
                });

                // 인포윈도우로 장소에 대한 설명을 표시합니다
                var infowindow = new kakao.maps.InfoWindow({
                  content: '<div style="width:150px;text-align:center;padding:6px 0;">${hosData.hos_name}</div>'
                });
                infowindow.open(map, marker);

                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                map.setCenter(coords);
              }
            });

          </script>
          </body>
          </html>
          