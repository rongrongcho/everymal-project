<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- core라이브러리를 c라는 접두사를 따와서 쓰겠다 -->

    <% request.setCharacterEncoding("utf-8"); %>
      <c:set var="contextPath" value="${pageContext.request.contextPath}" />
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${contextPath}/css/reset.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/myPage_Info.css" />
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <script>




        </script>



        <script type="text/javascript">
          function kakaopost() {
            new daum.Postcode({
              oncomplete: function (data) {
                document.querySelector("#zipcode").value = data.zonecode;
                document.querySelector("#address1").value = data.address
              }
            }).open();
          }

          function readImage(input) {
            if (input.files && input.files[0]) {
              let reader = new FileReader();
              reader.onload = function (event) {
                $("#preview").attr("src", event.target.result); //내가 선택한 이미지 이름를 가져옴
                console.log(event.target.result);
              };
              reader.readAsDataURL(input.files[0]);
            }
          }

          function fn_modSubmit(obj) {
            if (!checkPasswordMatch()) {
              return false;
            }
            alert("수정사항 등록중");
            obj.action = "${contextPath}/userMypage/modInfo.do";
            obj.submit();
          }

          function checkPasswordMatch() {
            var password = document.getElementById("password").value;
            var passwordCheck = document.getElementById("passwordCheck").value;

            if (password != passwordCheck) {
              alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
              return false;
            } else if (password.length < 4) {
              alert("비밀번호는 4자 이상이어야 합니다.");
              return false;
            } else if (password.length > 9) {
              alert("비밀번호는 8자 이하이어야 합니다.");
              return false;
            }
            return true;
          }


        </script>




        <title>일반회원 </title>
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
          <h2 class="pagetitlte">마이 페이지</h2>
          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
            </div>
          </section>

          <section id="left_Area">
            <div class="profileBox">
              <div class="proimgBox">
                <img class="proImg"
                  src="${contextPath}/proimgdown.do?imageFileName=${userVO.user_imgName}&user_id=${log_id}" width="200"
                  alt="사용자 프로필 사진">
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
                  <a href="${contextPath}/userMypage/myinfo.do"><span class="pointT">내</span><span>정보</span></a>
                </li>
                <li class="tBtn tBtn2 notChoice">
                  <a href="${contextPath}/petinfo/myPetList.do"><span class="pointT">펫</span><span>정보</span></a>
                </li>

                <li class="tBtn tBtn3 notChoice">
                  <a href="${contextPath}/userMypage/myReview.do"><span class="pointT">내</span><span>리뷰</span></a>
                </li>
                <li class="tBtn tBtn4 notChoice">
                  <a href="${contextPath}/userMypage/myQuestion.do"><span class="pointT">내</span><span>질문</span></a>
                </li>
              </ul>
            </div>
            <div class="tabcontents">
              <div id="tabBox">
                <div class="inputBck"></div>
                <div class="myInfoTitle">

                  <form action=" ${contextPath}/modInfo.do" name="myInfoForm" method="post"
                    enctype="multipart/form-data">
                    <fieldset>
                      <legend>내 정보 수정 폼</legend>
                      <p>
                        <label class="infoTitle" for="name">이름</label>
                        <input disabled class="answer" type="text" id="name" name="user_name"
                          value="${userVO.user_name}" readonly />
                      </p>
                      <p>
                        <label class="infoTitle" for="id">아이디</label>
                        <input disabled class="answer" type="text" id="id" name="user_id" value="${log_id}" readonly />
                      </p>
                      <p>
                        <label class="infoTitle" for="password">비밀번호</label>
                        <input class="answer" type="password" id="password" name="user_pwd"
                          value="${userVO.user_pwd}" />
                      </p>
                      <p>
                        <label class="infoTitle" for="passwordCheck">비밀번호 확인</label>
                        <input class="answer" type="password" id="passwordCheck" name="passwordCheck"
                          value="${userVO.user_pwd}" />
                      </p>
                      <p>
                        <label class="tel infoTitle" for="tel">연락처</label>
                        <select name="usertel_front" id="tel_front" class="answer">
                          <option value="010" ${userVO.user_tel.substring(0,3)=="010" ? 'selected' : '' }>010
                          </option>
                          <option value="011" ${userVO.user_tel.substring(0,3)=="011" ? 'selected' : '' }>011
                          </option>
                          <option value="018" ${userVO.user_tel.substring(0,3)=="018" ? 'selected' : '' }>018
                          </option>
                        </select>
                        <input class="answer answer_tel_end" type="tel" name="usertel_end" id="tel"
                          value="${userVO.user_tel.substring(3)}">

                      </p>
                      <p class="addressP">
                        <label class="labelAddress infoTitle" for="address">주소</label>
                        <input class="answer zipcode" type="text" id="zipcode" name="user_zipcode" maxlength="10"
                          size="6" readonly title="우편번호" value="${userVO.user_zipcode}" /><br>
                        <input type="button" value="우편번호찾기" id="addrbtn" onclick="kakaopost()">
                        <input class="answer " type="text" id="address1" name="user_addr1" maxlength="100" size="40"
                          value="${userVO.user_addr1}" /><br>
                        <input class="answer " type="text" id="address2" name="user_addr2" maxlength="100" size="50"
                          value="${userVO.user_addr2}" /><br>
                      </p>
                      <p>
                        <label class="email infoTitle" for="email">이메일</label>
                        <input class="answer" type="email" id="email" name="user_email" value="${userVO.user_email}" />
                      </p>

                      <c:choose>

                        <c:when test="${!empty userVO.user_imgName}">
                          <p>
                            <label class="proImg infoTitle" for="">프로필 등록</label>
                            <input type="hidden" name="originalFileName" value="${userVO.user_imgName}">

                            <input class="answer uploadImg" type="file" id="proimgup" name="user_imgName"
                              onchange="readImage(this)" />
                            <img id="preview"
                              src="${contextPath}/proimgdown.do?imageFileName=${userVO.user_imgName}&user_id=${log_id}"
                              width="200" alt="${userVO.user_imgName}">
                          </p>

                        </c:when>

                        <c:when test="${empty userVO.user_imgName}">
                          <p>
                            <label class="proImg infoTitle" for="proimgup">프로필 등록</label>
                            <input class="answer uploadImg" type="file" id="proimgup" name="user_imgName"
                              onchange="readImage(this)" />
                            <img id="preview" src="#" width="200" height="200" alt="${userVO.user_imgName}">
                          </p>
                        </c:when>
                      </c:choose>
                      <!-- <input type="hidden" name="user_code" value="user0000"> -->
                      <input class="btnOk" type="button" value="수정" onclick="fn_modSubmit(myInfoForm)">
                    </fieldset>
                  </form>
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