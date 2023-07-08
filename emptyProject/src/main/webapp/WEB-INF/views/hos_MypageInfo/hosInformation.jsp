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
            <link rel="stylesheet" type="text/css" href="${contextPath }/resources/css/reset.css" />
            <link rel="stylesheet" type="text/css" href="${contextPath }/resources/css/common.css" />
            <link rel="stylesheet" type="text/css" href="${contextPath }/resources/css/hosMyPageInfo2.css" />
            <script src="http://code.jquery.com/jquery-latest.min.js"></script>
            <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
            
          </head>

          <body>
            <div id="container_sub">
              <header id="header">
                <nav>
                  <ul class="topNav">
                <li>
                  <a href="${contextPath}/qna_board/qnaboardMain.do">1:1온라인 상담</a>
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
              <h2 class="pagetitlte">내 병원 관리</h2>
              <section id="sidebar_Area">
                <div class="sidebars" id="sb_sidebar">
                </div>
              </section>

              <section id="left_Area">
                <div class="profileBox">
                  <div class="proimgBox">
                    <img class="proImg"
                      src="${contextPath}/hos_MypageInfo/searchProfil.do?hos_id=${hosmypageinfoVO.hos_id}"
                      alt="나의 프로필 이미지" />
                  </div>
                  <div class="speech_bubble">
                    <img class="bubbleImg" src="${contextPath}/resources/img/말풍선.svg" alt="말풍선 배경">
                    <p><span class="userID" name="hos_username">${hosmypageinfoVO.hos_username }</span>님 환영합니다!</p>
                  </div>
                </div>
                <div class="leftCategory">
                  <ul class="CatrgoryBox">
                    <li class="firstMenu"> 마이페이지
                      <ul>
                        <li class="secondMenu"><a href="${contextPath }/hos_MypageInfo/hosUserInformation.do?hos_id=${hosmypageinfoVO.hos_id }">내 정보 관리</a></li>
                        <li class="secondMenu"><a href="${contextPath }/hos_MypageInfo/hosInformation.do?hos_id=${hosmypageinfoVO.hos_id }">내 병원 관리</a></li>
                        <li class="secondMenu"><a href="${contextPath }/hos_ReviewInfo/hosReviewList.do">병원리뷰 관리</a></li>
                        <li class="secondMenu"><a href="${contextPath }/hos_MyReplyInfo/listMyReply.do">답변 관리</a></li>
                      </ul>
                    </li>
                    <li class="firstMenu"> 예약 관리
                      <ul>
                        <li class="secondMenu"><a href="${contextPath}/hos_ResInfo/hosResList.do">예약관리</a></li>
                      </ul>
                    </li>
                    <li class="firstMenu"><a href="${contextPath }/member/withdrawal.do">회원탈퇴</a></li>
                  </ul>
                </div>
              </section>
              <div class="grey_line"></div>
              <section id="tab_Area">
                <div class="tab_nav">
                  <ul class="tab_Btns">
                    <li class="tBtn tBtn1 notChoice"><a href="${contextPath }/hos_MypageInfo/hosUserInformation.do?hos_id=${hosmypageinfoVO.hos_id }"><span
                          class="pointT">내</span><span>정보</span></a></li>
                    <li class="tBtn tBtn2 choiceTabNav"><a href="${contextPath }/hos_MypageInfo/hosInformation.do?hos_id=${hosmypageinfoVO.hos_id }"><span
                          class="pointT">내</span><span> 병원</span></a></li>
                    <li class="tBtn tBtn3 notChoice"><a href="${contextPath }/hos_ReviewInfo/hosReviewList.do"><span
                          class="pointT span3">병원</span><span class="span3"> 리뷰</span></a></li>
                    <li class="tBtn tBtn4 notChoice"><a href="${contextPath }/hos_MyReplyInfo/listMyReply.do"><span
                          class=" span4">답변</span></a>
                    </li>
                  </ul>
                </div>
                <div class="tabcontents">
                  <div id="tabBox" class="tab-content">
                    <div class="inputbox"></div>

                    <form method="post" id="myhospital_info_form" name="frmHosinfo"
                      action="${contextPath }/hos_MypageInfo/modmyHosInfo.do?hos_id=${hosmypageinfoVO.hos_id}">
                      <legend>내병원관리폼</legend>
                      
                      <div class="apphosYorN">

                        <c:choose>
                          <c:when test="${hosmypageinfoVO.hos_status == '승인'}">
                            <div class="yOrNo">
                              <p>승인된 병원 입니다.</p>
                              <a href="${contextPath }/hos_MypageInfo/hosApplication.do?hos_id=${hosmypageinfoVO.hos_id}" class="barogagy">썸네일
                                수정</a>
                            </div>
                          </c:when>
                          <c:when test="${hosmypageinfoVO.hos_status == '미승인'}">
                            <div class="yOrNo">
                              <p>미승인된 병원 입니다.</p>
                              <a href="${contextPath }/hos_MypageInfo/hosApplication.do?hos_id=${hosmypageinfoVO.hos_id}" class="barogagy">등록 신청</a>
                            </div>
                          </c:when>
                          <c:when test="${hosmypageinfoVO.hos_status == '거절'}">
                            <div class="yOrNo">
                              <p>등록 거절되었습니다.</p>
                              <a href="${contextPath }/hos_MypageInfoe/hosApplication.do?hos_id=${hosmypageinfoVO.hos_id}" class="barogagy">사유확인</a>
                            </div>
                          </c:when>
                          <c:otherwise>
                            <div class="yOrNo">
                              <p>심사중입니다.</p>
                              <a href="${contextPath }/hos_MypageInfo/hosApplication.do?hos_id=${hosmypageinfoVO.hos_id}" class="barogagy">확인</a>
                            </div>
                          </c:otherwise>
                        </c:choose>
                        ${rm.rmstatus} 



                      </div>
                      
                      <div class="name formbox2 notfilter">
                        <label for="name">병원이름</label>
                        <input type="text" name="hos_name" id="name" value="${hosmypageinfoVO.hos_name}">
                        <input type="hidden" name="hos_code" value="${hosmypageinfoVO.hos_code }">
                      </div>
                      <div class="hospital_tel formbox2 notfilter">
                        <label for="tel">병원 연락처</label>
                        <select name="tel_front" id="tel_front">
                          <option value="02" ${hosmypageinfoVO.hos_tel.substring(0,2)=="02" ? 'selected' : '' }>02
                          </option>
                          <option value="031" ${hosmypageinfoVO.hos_tel.substring(0,3)=="031" ? 'selected' : '' }>031
                          </option>
                          <option value="032" ${hosmypageinfoVO.hos_tel.substring(0,3)=="032" ? 'selected' : '' }>032
                          </option>
                        </select>
                        <c:set var="tel_end" value="${hosmypageinfoVO.hos_tel}" />
                        <c:choose>
                          <c:when test="${tel_end.startsWith('02')}">
                            <input type="tel" name="tel_end" id="tel" value="${tel_end.substring(2)}">
                          </c:when>
                          <c:when test="${not tel_end.startsWith('02')}">
                            <input type="tel" name="tel_end" id="tel" value="${tel_end.substring(3)}">
                          </c:when>
                          <c:otherwise>
                            <input type="tel" name="tel_end" id="tel" value="${tel_end}">
                          </c:otherwise>
                        </c:choose>
                        <c:set var="hos_tel" value="${tel_front }${tel_end }" />
                        <input type="hidden" name="hos_tel" value="${hos_tel }">
                      </div>
                      <div class="hospital_address formbox2 notfilter">
                        <label for="hospital_address">병원 주소</label>
                        <input class="address_input_1" name="hos_zipcode" id="zip" readonly="readonly"
                          value="${hosmypageinfoVO.hos_zipcode }">
                        <input type="button" value="우편번호 찾기" id="find_zipcode" onclick="execution_daum_address()">
                        <input type="text" class="address_input_2" name="hos_addr1" id="hos_add1" readonly="readonly"
                          value="${hosmypageinfoVO.hos_addr1 }">
                        <input type="text" class="address_input_3" name="hos_addr2" id="hos_add2" readonly="readonly"
                          value="${hosmypageinfoVO.hos_addr2 }">
                      </div>


                      <div class="list animalChk">
                        <c:set var="hos_dog_checked" value="${hosmypageinfoVO.hos_dog eq '1' ? 'checked' : ''}" />
                        <c:set var="hos_cat_checked" value="${hosmypageinfoVO.hos_cat eq '1' ? 'checked' : ''}" />
                        <c:set var="hos_small_checked" value="${hosmypageinfoVO.hos_small eq '1' ? 'checked' : ''}" />
                        <c:set var="hos_fish_checked" value="${hosmypageinfoVO.hos_fish eq '1' ? 'checked' : ''}" />
                        <c:set var="hos_bird_checked" value="${hosmypageinfoVO.hos_bird eq '1' ? 'checked' : ''}" />
                        <c:set var="hos_rep_checked" value="${hosmypageinfoVO.hos_rep eq '1' ? 'checked' : ''}" />
                        <c:set var="hos_etc_checked" value="${hosmypageinfoVO.hos_etc eq '1' ? 'checked' : ''}" />
                        <p class="subtitle">진료 동물</p>
                        <div class="form-element">
                          <input type="checkbox" class="ani_chk" name="hos_dog" id="hos_dog" value="1"
                            ${hos_dog_checked}>
                          <label for="hos_dog">
                            <div class="title">
                              개
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" class="ani_chk" name="hos_cat" id="hos_cat" value="1"
                            ${hos_cat_checked}>
                          <label for="hos_cat">
                            <div class="title">
                              고양이
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" class="ani_chk" name="hos_small" id="hos_small" value="1"
                            ${hos_small_checked}>
                          <label for="hos_small">
                            <div class="title">
                              소동물
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" class="ani_chk" name="hos_fish" id="hos_fish" value="1"
                            ${hos_fish_checked}>
                          <label for="hos_fish">
                            <div class="title">
                              어류
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" class="ani_chk" name="hos_bird" id="hos_bird" value="1"
                            ${hos_bird_checked}>
                          <label for="hos_bird">
                            <div class="title">
                              조류
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" class="ani_chk" name="hos_rep" id="hos_rep" value="1"
                            ${hos_rep_checked}>
                          <label for="hos_rep">
                            <div class="title">
                              파충류
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" name="hos_etc" id="hos_etc" value="1" ${hos_etc_checked}>
                          <label for="hos_etc">
                            <div class="title">
                              기타
                            </div>
                          </label>
                        </div>
                      </div>




                      <div class="list animalChk">
                        <c:set var="hos_gs_checked" value="${hosmypageinfoVO.hos_gs eq '1' ? 'checked' : ''}" />
                        <c:set var="hos_im_checked" value="${hosmypageinfoVO.hos_im eq '1' ? 'checked' : ''}" />
                        <c:set var="hos_em_checked" value="${hosmypageinfoVO.hos_em eq '1' ? 'checked' : ''}" />
                        <c:set var="hos_rm_checked" value="${hosmypageinfoVO.hos_rm eq '1' ? 'checked' : ''}" />
                        <c:set var="hos_vac_checked" value="${hosmypageinfoVO.hos_vac eq '1' ? 'checked' : ''}" />
                        <p class="subtitle">진료 과목</p>
                        <div class="form-element">
                          <input type="checkbox" name="hos_gs" id="hos_gs" ${hos_gs_checked } value="1">
                          <label for="hos_gs">
                            <div class="title">
                              외과
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" name="hos_im" id="hos_im" ${hos_im_checked } value="1">
                          <label for="hos_im">
                            <div class="title">
                              내과
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" name="hos_em" id="hos_em" ${hos_em_checked } value="1">
                          <label for="hos_em">
                            <div class="title">
                              응급의학과
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" name="hos_rm" id="hos_rm" ${hos_rm_checked } value="1">
                          <label for="hos_rm">
                            <div class="title">
                              재활의학과
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" name="hos_vac" id="hos_vac" ${hos_vac_checked } value="1">
                          <label for="hos_vac">
                            <div class="title">
                              예방접종
                            </div>
                          </label>
                        </div>
                      </div>

                      <div class="list dayChk">
                        <p class="subtitle">영업 요일</p>
                        <div class="form-element">
                          <input type="checkbox" class="hos_day_chk" name="hos_365" id="mon" value="mon"
                            ${fn:contains(hosmypageinfoVO.hos_365, 'mon' ) ? 'checked' : '' }>
                          <label for="mon">
                            <div class="title">
                              월요일
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" class="hos_day_chk" name="hos_365" id="tue" value="tue"
                            ${fn:contains(hosmypageinfoVO.hos_365, 'tue' ) ? 'checked' : '' }>
                          <label for="tue">
                            <div class="title">
                              화요일
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" class="hos_day_chk" name="hos_365" id="wed" value="wed"
                            ${fn:contains(hosmypageinfoVO.hos_365, 'wed' ) ? 'checked' : '' }>
                          <label for="wed">
                            <div class="title">
                              수요일
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" class="hos_day_chk" name="hos_365" id="thu" value="thu"
                            ${fn:contains(hosmypageinfoVO.hos_365, 'thu' ) ? 'checked' : '' }>
                          <label for="thu">
                            <div class="title">
                              목요일
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" class="hos_day_chk" name="hos_365" id="fri" value="fri"
                            ${fn:contains(hosmypageinfoVO.hos_365, 'fri' ) ? 'checked' : '' }>
                          <label for="fri">
                            <div class="title">
                              금요일
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" class="hos_day_chk" name="hos_365" id="sat" value="sat"
                            ${fn:contains(hosmypageinfoVO.hos_365, 'sat' ) ? 'checked' : '' }>
                          <label for="sat">
                            <div class="title">
                              토요일
                            </div>
                          </label>
                        </div>
                        <div class="form-element">
                          <input type="checkbox" class="hos_day_chk" name="hos_365" id="sun" value="sun"
                            ${fn:contains(hosmypageinfoVO.hos_365, 'sun' ) ? 'checked' : '' }>
                          <label for="sun">
                            <div class="title">
                              일요일
                            </div>
                          </label>
                        </div>
                      </div>


                      <div class="run_time formbox2">
                        <label class="timeTitle" for="run_time">영업시간</label>
                        <br>
                        <fieldset id="run_time_fieldset">
                          <label class="sub" for="hos_time_s">영업 시작 시간</label>
                          <input type="time" name="hos_time_s" id="hos_time_s" value="${hosmypageinfoVO.hos_time_s }">
                          <label class="sub" for="hos_time_e">영업 종료 시간</label>
                          <input type="time" name="hos_time_e" id="hos_time_e" value="${hosmypageinfoVO.hos_time_e }">
                          <div class="list fullChk">
                            <c:set var="hos_24_checked" value="${hosmypageinfoVO.hos_24 eq '1' ? 'checked' : ''}" />
                            <div class="form-element">
                              <input type="checkbox" name="hos_24" id="hos_24" value="1" ${hos_24_checked}>
                              <label for="hos_24">
                                <div class="title">
                                  24시간
                                </div>
                              </label>
                            </div>
                          </div>
                        </fieldset>
                      </div>
                      <div class="hos_intro formbox2">
                        <label class="timeTitle" for="run_time">병원 소개</label>
                        <br>
                        <fieldset id="run_time_fieldset">
                          <label class="sub" for="hos_time_s"></label>
                          <textarea placeholder="병원 소개글을 적어주세요" name="hos_intro">${hosmypageinfoVO.hos_intro}</textarea>

                        </fieldset>
                      </div>

                    </form>
                    <button form="myhospital_info_form" type="submit" id="edit_btn2" class="edit_btn">수정하기</button>
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
                <a href="${contextPath}/qna_board/qnaboardMain.do">1:1온라인 상담</a>
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
			<script type="text/javascript">
                //주소연동 메서드 
                function execution_daum_address() {
                  new daum.Postcode({
                    oncomplete: function (data) {
                      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                      var addr = ''; // 주소 변수
                      var extraAddr = ''; // 참고항목 변수

                      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                      if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                      } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                      }

                      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                      if (data.userSelectedType === 'R') {
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                          extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if (data.buildingName !== '' && data.apartment === 'Y') {
                          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if (extraAddr !== '') {
                          extraAddr = ' (' + extraAddr + ')';
                        }
                        // 주소변수 문자열과 참고항목 문자열 합치기
                        addr += extraAddr;

                      } else {
                        addr += ' ';
                      }

                      // 우편번호와 주소 정보를 해당 필드에 넣는다.
                      $(".address_input_1").val(data.zonecode);
                      //$("[name=memberAddr1]").val(data.zonecode);    // 대체가능
                      $(".address_input_2").val(addr);
                      //$("[name=memberAddr2]").val(addr);            // 대체가능
                      // 상세주소 입력란 disabled 속성 변경 및 커서를 상세주소 필드로 이동한다.
                      $(".address_input_3").attr("readonly", false);
                      $(".address_input_3").focus();

                    }
                  }).open();
                }


            </script>
          </html>