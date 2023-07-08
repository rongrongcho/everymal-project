<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"
  import="java.util.*" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
          <link rel="stylesheet" type="text/css" href="${contextPath }/resources/css/hosMyPageInfo.css" />
          <script src="http://code.jquery.com/jquery-latest.min.js"></script>
          <script type="text/javascript">
            $(function () {
              //비밀번호 변경 확인 메서드
              function test() {
                var p1 = document.getElementById('pwd').value;
                var p2 = document.getElementById('pwd_confirm').value;
                if (p1 !== p2) {
                  alert("비밀번호가 일치하지 않습니다.");
                  return false;
                } else if (p1.length < 4) {
                  alert('비밀번호는 4자 이상이어야 합니다.');
                  return false;
                } else if (p1.length > 8) {
                  alert('비밀번호는 8자 이하여야 합니다.');
                  return false;
                }
                return true;
              }

              $("#my_info_form").submit(function () {
                return test();
              });


              function previewImage(input) {
                var reader = new FileReader();
                reader.onload = function (e) {
                  var profileImg = document.getElementById('usedimg');
                  profileImg.innerHTML = '';
                  var imgElement = document.createElement('img');
                  imgElement.setAttribute('id', 'preview');
                  imgElement.setAttribute('src', e.target.result);
                  imgElement.setAttribute('alt', '미리보기 이미지');
                  imgElement.style.width = '100px'; 
                  imgElement.style.height = '100px'; 
                  imgElement.style.borderRadius = '100px'; 
                  imgElement.style.position = 'relative'; 
                  imgElement.style.top = '-15px'; 
                  var viewArea = document.getElementById('View_area');
                  viewArea.innerHTML = ''; // 이미지 미리보기 영역 초기화
                  viewArea.appendChild(imgElement);
                };
                reader.readAsDataURL(input.files[0]);
              }

              var updateImg = document.getElementById('updateImg');
              updateImg.addEventListener('change', function () {
                previewImage(this);
              });

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
                        <a href="${contextPath}/hos_MypageInfo/hosMypage.do">병원마이페이지</a>
                      </c:when>
                      <c:when test="${log_id eq 'admin'}">
                      	<a href="${contextPath }/administrator/memberList.do">관리자마이페이지</a>
                      </c:when>
                      <c:otherwise>
                        <a href="${contextPath}/userMypage">회원마이페이지</a>
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
            <h2 class="pagetitlte">내 정보 관리</h2>
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
                    <li class="tBtn tBtn1 choiceTabNav"><a href="${contextPath }/hos_MypageInfo/hosUserInformation.do?hos_id=${hosmypageinfoVO.hos_id }"><span
                          class="pointT">내</span><span>정보</span></a></li>
                    <li class="tBtn tBtn2 notChoice"><a href="${contextPath }/hos_MypageInfo/hosInformation.do?hos_id=${hosmypageinfoVO.hos_id }"><span
                          class="pointT">내</span><span> 병원</span></a></li>
                    <li class="tBtn tBtn3 notChoice"><a href="${contextPath }/hos_ReviewInfo/hosReviewList.do"><span
                          class="pointT span3">병원</span><span class="span3"> 리뷰</span></a></li>
                    <li class="tBtn tBtn4 notChoice"><a href="${contextPath }/hos_MyReplyInfo/listMyReply.do"><span
                          class=" span4">답변</span></a>
                    </li>
                  </ul>
                </div>
              <div class="tabcontents">
                <div id="tabBox" class="tab-content current">
                  <div class="inputbox"></div>
                  <form name="frmHosinfo" id="my_info_form" method="post"
                    action='${contextPath}/hos_MypageInfo/modhosInfo.do?hos_id=${hosmypageinfoVO.hos_id}' enctype="multipart/form-data">

                    <legend>정보관리폼</legend>
                    <div class="name_box formbox">
                      <label for="name">이름</label>
                      <input type="text" name="hos_username" id="name" value="${hosmypageinfoVO.hos_username}">
                      <input type="hidden" name="hos_code" value="${hosmypageinfoVO.hos_code }">
                    </div>

                    <div class="id_box formbox">
                      <label for="id">아이디</label>
                      <input type="text" name="hos_id" id="id" disabled style="background-color:#d9d9d9;"
                        value="${hosmypageinfoVO.hos_id}">
                    </div>

                    <div class="pwd_box formbox">
                      <label for="pwd">비밀번호</label>
                      <input type="password" name="hos_pwd" id="pwd" value="${hosmypageinfoVO.hos_pwd}">
                    </div>

                    <div class="pwd_confirm_box formbox">
                      <label for="pwd_confirm">비밀번호 확인</label>
                      <input type="password" name="hos_pwdChk" id="pwd_confirm" required>
                    </div>

                    <div class="tel_box formbox">
                      <label for="tel">연락처</label>
                      <select name="usertel_front" id="usertel_front">
                        <option value="010" ${hosmypageinfoVO.hos_usertel.substring(0,3)=="010" ? 'selected' : '' }>010
                        </option>
                        <option value="011" ${hosmypageinfoVO.hos_usertel.substring(0,3)=="011" ? 'selected' : '' }>011
                        </option>
                        <option value="018" ${hosmypageinfoVO.hos_usertel.substring(0,3)=="018" ? 'selected' : '' }>018
                        </option>
                      </select>
                      <input type="tel" name="usertel_end" id="tel" value="${hosmypageinfoVO.hos_usertel.substring(3)}">
                      <input type="hidden" name="hos_usertel" value="${hosmypageinfoVO.hos_usertel }">
                    </div>

                    <div class="address_box formbox">
                      <label for="company_number">사업자 등록번호</label>
                      <input type="text" name="hos_dno" id="company_number" value="${hosmypageinfoVO.hos_dno}">
                    </div>

                    <div class="email_box formbox">
                      <label for="email">이메일</label>
                      <input type="email" name="hos_email" id="email" value="${hosmypageinfoVO.hos_email}">
                    </div>
                    <div class="profil_box formbox">
                      <label for="Img" id="proimg">프로필 이미지</label>
                      <label for="updateImg" class="updateImg">파일 선택</label>
                      <span id="usedimg">
                      <c:choose>
                        <c:when test="${not empty hosmypageinfoVO.hos_pro}">
                          <img id="profileImg" src="${contextPath}/resources/imgRepo/hos_images/profile/${hosmypageinfoVO.hos_code}/${hosmypageinfoVO.hos_pro}" alert="프로필 이미지"/>
                        </c:when>
                        <c:otherwise>
                          <img id="profileImg" src="${contextPath}/resources/img/blankProfil.png" alert="no profile"/>
                        </c:otherwise>
                      </c:choose>
                      </span>
                      <input type="file" name="file" id="updateImg" class="file" value="${hosmypageinfoVO.hos_pro}" onchange="previewImage(this)">
                      <span id='View_area'></span>
                    </div>

                  </form>
                  <button form="my_info_form" type="submit" class="edit_btn" onclick="test()">수정하기</button>
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