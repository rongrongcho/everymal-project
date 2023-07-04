<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <!DOCTYPE html>
    <html lang="ko">

    <head>
      <meta charset="UTF-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>병원 회원가입</title>
      <script src="http://code.jquery.com/jquery-latest.min.js"></script>
      <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
      <script>
        function kakaopost() {
          new daum.Postcode({
            oncomplete: function (data) {
              document.querySelector("#zipcode").value = data.zonecode;
              document.querySelector("#address1").value = data.address
            }
          }).open();
        };

        $(function () {
          if (document.getElementById("input_check").checked) {
            document.getElementById("input_check_hidden").disabled = true;
          }
        });

        function submit() {
          //회원정보 저장
          data = $("#joinForm").serialize();
          $.ajax({
            type: "post",
            async: false,
            dataType: "text",
            url: "${contextPath}/hospital/addHospital.do",
            data: data,
            success: function () {
              alert('회원가입완료.');
              location.href = "${contextPath}/index.jsp";
            }
          });
        }
        var a = '';
        function fn_idCheck() {
          let _id = $("#id").val();
          if (_id == "") {
            alert("중복체크할 ID를 입력하세요");
            return; //함수를 종료한다
          }
          $.ajax({
            type: "post",
            async: true, //비동기로 수행
            dataType: "text",
            url: "${contextPath}/hospital/idChk.do",
            data: { id: _id }, //서버에 전송할 자료= > 매개변수이름:값 _id(체크할ID)를 id이름으로 서버에 전송
            success: function (idChkVal, textStatus) { //idChkVal는 서버에서 받은 자료
              if (idChkVal == 'usable') {
                a = idChkVal;
                $("#resultMessage").text("사용할 수 있는 ID입니다.");
              } else {
                a = idChkVal;
                $("#resultMessage").text("사용할 수 없는 ID입니다.");
              }
            },
            error: function () {
              alert("서버와 통신 중 에러 발생");
            }
          });
        }
        
        function reset() {
           $("input[type='text']").val("");
            $("input[type='password']").val("");
            $("input[type='number']").val("");
            $("input[type='tel']").val("");
            $("#hos_time_s").val("");
            $("#hos_time_e").val("");
            $("textarea").val("");
            $("input[type='checkbox']").prop('checked', false);
            $("#hos_usertel1").val('선택').prop("selected", true);
            $("#hos_tel1").val('선택').prop("selected", true);
            $("#email").val("")
            
          }
       
        function check() {
        /* 병원명 유효성 검사 */
          if (joinForm.hos_name.value.length == 0) {
            alert("병원명을 입력해주세요.");
            joinForm.hos_name.focus();
            return false;
          }
          /* 아이디 유효성 검사 */
          if (a == '') {
            alert('중복체크를 해주세요');
            joinForm.user_id.focus();
            return false;
          }
          if (a == 'not_usable') {
            alert('사용할 수 없는 ID입니다. 다시 중복체크를 해주세요');
            joinForm.user_id.focus();
            return false;
          }

          /* 병원 관리자 이름 유효성 검사 */
          if (joinForm.hos_username.value.length == 0) {
            alert("병원관리자 이름을 입력해주세요.");
            joinForm.hos_username.focus();
            return false;
          }

          /* 관리자 전화번호 유효성 검사 */
          if (joinForm.hos_usertel1.value == "") {
            alert("번호를 선택해주세요.");
            joinForm.hos_usertel1.focus();
            return false;
          }

          if (joinForm.hos_usertel2.value.length == 0) {
            alert("전화번호를 입력해주세요.");
            joinForm.hos_usertel2.focus();
            return false;
          }

          if (joinForm.hos_usertel3.value.length == 0) {
            alert("전화번호를 입력해주세요.");
            joinForm.hos_usertel3.focus();
            return false;
          }

          if (isNaN(joinForm.hos_usertel2.value)) {
            alert("전화번호는 숫자만 입력가능합니다.");
            joinForm.hos_usertel2.focus();
            return false;
          }

          if (isNaN(joinForm.hos_usertel3.value)) {
            alert("전화번호는 숫자만 입력가능합니다.");
            joinForm.hos_usertel3.focus();
            return false;
          }

          /* 비밀번호 및 비밀번호 확인 유효성 검사 */
          if (joinForm.hos_pwd.value.length == 0) {
            alert("비밀번호를 입력해주세요.");
            joinForm.hos_pwd.focus(); // 포커스를 이동시켜 바로 비밀번호를 입력할 수 있게
            return false;
          }

          var pwdRegExp = /^[a-zA-z0-9]{4,8}$/; //비밀번호 유효성 검사
          if (!pwdRegExp.test(joinForm.hos_pwd.value)) {
            alert("비밀번호는 영문 대소문자와 숫자 4~8자리로 입력해야합니다.");
            joinForm.hos_pwd.focus();
            return false;
          }

          if (joinForm.h_confirmPassword.value.length == 0) {
            alert("비밀번호 확인을 입력해주세요.");
            joinForm.h_confirmPassword.focus();
            return false;
          }

          if (joinForm.hos_pwd.value != joinForm.h_confirmPassword.value) {
            alert("비밀번호가 일치하지 않습니다.");
            joinForm.h_confirmPassword.select(); //이걸로 하면 focus 이동하면서 입력한게 블록으로 선택됨
            return false;
          }

          /* 사업자 등록번호 유효성 검사 */
          if (joinForm.hos_dno1.value.length == 0) {
            alert("사업자 등록 번호를 입력해주세요.");
            joinForm.hos_dno1.focus();
            return false;
          }
          if (joinForm.hos_dno2.value.length == 0) {
            alert("사업자 등록 번호를 입력해주세요.");
            joinForm.hos_dno2.focus();
            return false;
          }
          if (joinForm.hos_dno3.value.length == 0) {
            alert("사업자 등록 번호를 입력해주세요.");
            joinForm.hos_dno3.focus();
            return false;
          }
          if (isNaN(joinForm.hos_dno1.value)) {
            alert("사업자 등록 번호는 숫자만 입력가능합니다.");
            joinForm.hos_dno1.focus();
            return false;
          }
          if (isNaN(joinForm.hos_dno2.value)) {
            alert("사업자 등록 번호는 숫자만 입력가능합니다.");
            joinForm.hos_dno2.focus();
            return false;
          }
          if (isNaN(joinForm.hos_dno3.value)) {
            alert("사업자 등록 번호는 숫자만 입력가능합니다.");
            joinForm.hos_dno3.focus();
            return false;
          }

          /* 병원 전화번호 유효성 검사 */
          if (joinForm.hos_tel1.value == "") {
            alert("번호를 선택해주세요.");
            joinForm.hos_tel1.focus();
            return false;
          }

          if (joinForm.hos_tel2.value.length == 0) {
            alert("전화번호를 입력해주세요.");
            joinForm.hos_tel2.focus();
            return false;
          }

          if (joinForm.hos_tel3.value.length == 0) {
            alert("전화번호를 입력해주세요.");
            joinForm.hos_tel3.focus();
            return false;
          }

          if (isNaN(joinForm.hos_tel2.value)) {
            alert("전화번호는 숫자만 입력가능합니다.");
            joinForm.hos_tel2.focus();
            return false;
          }

          if (isNaN(joinForm.hos_tel3.value)) {
            alert("전화번호는 숫자만 입력가능합니다.");
            joinForm.hos_tel3.focus();
            return false;
          }

          /* 주소 유효성 검사 */
          if (joinForm.hos_zipcode.value.length == 0) {
            alert("주소를 입력해주세요.");
            joinForm.hos_zipcode.focus();
            return false;
          }
          if (joinForm.hos_addr1.value.length == 0) {
            alert("주소를 입력해주세요.");
            joinForm.hos_addr1.focus();
            return false;
          }
          if (joinForm.hos_addr2.value.length == 0) {
            alert("주소를 입력해주세요.");
            joinForm.hos_addr2.focus();
            return false;
          }

          /* 이메일 유효성 검사 */
          if (joinForm.hos_email.value.length == 0) {
            alert("이메일을 입력해주세요.");
            joinForm.hos_email.focus();
            return false;
          }
          submit();
        }

      </script>
      <link rel="stylesheet" href="${contextPath }/resources/css/reset.css" />
      <link rel="stylesheet" href="${contextPath }/resources/css/common.css" />
      <link rel="stylesheet" href="${contextPath }/resources/css/join.css" />
    </head>

    <body>
      <div id="container_sub">
        <header id="header">
          <nav>
            <ul class="topNav">
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
                    <a href="${contextPath}/member/loginForm.do">로그인•회원가입</a>
                  </c:otherwise>
                </c:choose>
              </li>
            </ul>
          </nav>
        </header>
        <h2 class="pagetitlte">회원가입</h2>
        <section id="sidebar_Area">
          <div class="sidebars" id="sb_sidebar">
          </div>
        </section>
        <section class="memberArea memberJoin memberArea2">
          <p class="formSign"><strong class="require">필수</strong>는 반드시 입력하여야 하는 항목입니다.</p>
          <form action="${contextPath}/hospital/addHospital.do" method="post" id="joinForm" name="joinForm">
            <fieldset>
              <legend>회원가입 정보 입력 폼</legend>
              <div id="hospital_form" class="formBox">
                <h2>병원정보</h2>
                <p>
                  <label for="hos_name">병원명<strong class="require">필수</strong></label>
                  <input type="text" id="hos_name" name="hos_name" required placeholder="A동물병원">
                </p>
                <p>
                  <label for="id">관리자아이디<strong class="require">필수</strong></label>
                  <input type="text" id="id" name="hos_id" required placeholder="everymal">
                  <input type="button" id="idck" class="greenbox bluebox" value="ID중복체크" onclick="fn_idCheck();"><span
                    id="resultMessage"></span>
                </p>
                <p>
                  <label for="hos_username">관리자이름<strong class="require">필수</strong></label>
                  <input type="text" id="hos_username" name="hos_username" required>
                </p>
                <p>
                  <label for="hos_usertel1">관리자연락처<strong class="require">필수</strong></label>
                  <select name="hos_usertel1" id="hos_usertel1">
                    <option value="" selected="selected">선택</option>
                    <option value="010">010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="017">017</option>
                    <option value="018">018</option>
                    <option value="019">019</option>
                  </select>
                  <input type="tel" id="hos_usertel2" name="hos_usertel2" maxlength="4" size="10">
                  <input type="tel" id="hos_usertel3" name="hos_usertel3" maxlength="4" size="10">
                </p>
                <p>
                  <label for="hos_pwd">비밀번호<strong class="require">필수</strong></label>
                  <input type="password" id="hos_pwd" name="hos_pwd" required placeholder="영문/숫자 4~8자 이내">
                </p>
                <p>
                  <label for="h_confirmPassword">비밀번호 확인<strong class="require">필수</strong></label>
                  <input type="password" id="h_confirmPassword=" name="h_confirmPassword" required
                    placeholder="한번 더 입력해주세요">
                </p>
                <p>
                  <label for="hos_dno1">사업자등록번호<strong class="require">필수</strong></label>
                  <input type="text" id="hos_dno1" placeholder="사업자 등록번호 세자리" name="hos_dno1" maxlength="3"
                    class="work_no1"> -
                  <input type="text" placeholder="사업자 등록번호 두자리" name="hos_dno2" maxlength="2" class="work_no2"> -
                  <input type="text" placeholder="사업자 등록번호 다섯자리" name="hos_dno3" maxlength="5" class="work_no3">
                </p>
                <p>
                  <label for="hos_tel1">병원 연락처<strong class="require">필수</strong></label>
                  <select name="hos_tel1" id="hos_tel1">
                    <option value="" selected="selected">선택</option>
                    <option value="02">02</option>
                    <option value="031">031</option>
                    <option value="032">032</option>
                    <option value="033">033</option>
                    <option value="041">041</option>
                    <option value="042">042</option>
                    <option value="043">043</option>
                    <option value="044">044</option>
                    <option value="051">051</option>
                    <option value="052">052</option>
                    <option value="053">053</option>
                    <option value="054">054</option>
                    <option value="055">055</option>
                    <option value="061">061</option>
                    <option value="062">062</option>
                    <option value="063">063</option>
                    <option value="064">064</option>
                  </select>
                  <input type="tel" id="hos_tel2" name="hos_tel2" maxlength="4" size="10">
                  <input type="tel" id="hos_tel3" name="hos_tel3" maxlength="4" size="10">
                </p>
                <p>
                   <label for="addrbtn" class="labelAddress">병원주소<strong class="require">필수</strong></label>
                  <input type="button" class="bluebox greenbox" value="우편번호찾기" id="addrbtn" onclick="kakaopost()">
                  <input type="text" name="hos_zipcode" id="zipcode" size="10" readonly><br>
                <div id="address">
                  <input type="text" name="hos_addr1" id="address1" size="100">
                  <input type="text" name="hos_addr2" id="address2" size="100">
                </div>
                </p>
                <p>
                  <label for="email">이메일<strong class="require">필수</strong></label>
                  <input type="email" id="email" name="hos_email" size="30" required
                    placeholder="입력 예) everymal@gmail.com">
                </p>
                <p>
                  <label>병원영업일<strong class="require">필수</strong>
                    </label>
                      <label for="hos_24">24시간</label>
                      <input type="checkbox" name="hos_24" id="input_check" value="1" class="blue">
                      <input type="hidden" name="hos_24" value="0" id="input_check_hidden" />
                </p>
                <p>
                  <label for="hos_365" class="needS">영업일 선택<strong class="require">필수</strong></label>
                  <!-- <input type="checkbox" name="hos_365" id="input_check" value="1">
                      <input type="hidden" name="hos_365" id="input_check_hidden" value="0"/> -->
                  월<input type="checkbox" name="hos_365_1" id="input_check_1" value="mon" class="blue">
                  화<input type="checkbox" name="hos_365_2" id="input_check_2" value=",tue" class="blue">
                  수<input type="checkbox" name="hos_365_3" id="input_check_3" value=",wed" class="blue">
                  목<input type="checkbox" name="hos_365_4" id="input_check_4" value=",thu" class="blue">
                  금<input type="checkbox" name="hos_365_5" id="input_check_5" value=",fri" class="blue">
                  토<input type="checkbox" name="hos_365_6" id="input_check_6" value=",sat" class="blue">
                  일<input type="checkbox" name="hos_365_7" id="input_check_7" value=",sun" class="blue">
                </p>
                <p>
                  <label for="hos_time_s">영업시간<strong class="require">필수</strong></label>
                  진료시작 <input type="time" id="hos_time_s" name="hos_time_s">
                  진료종료 <input type="time" id="hos_time_e" name="hos_time_e">
                </p>
                <p>
                  <label>진료동물 종류<strong class="require">필수</strong></label>
                  <label for="hos_dog">개</label>
                  <input type="checkbox" name="hos_dog" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_dog" value="0" id="input_check_hidden" />
                  <label for="hos_cat">고양이</label>
                  <input type="checkbox" name="hos_cat" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_cat" value="0" id="input_check_hidden" />
                  <label for="hos_small">소동물</label>
                  <input type="checkbox" name="hos_small" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_small" value="0" id="input_check_hidden" />
                  <label for="hos_fish">어류</label>
                  <input type="checkbox" name="hos_fish" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_fish" value="0" id="input_check_hidden" />
                  <label for="hos_bird">조류</label>
                  <input type="checkbox" name="hos_bird" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_bird" value="0" id="input_check_hidden" />
                  <label for="hos_rep">파충류</label>
                  <input type="checkbox" name="hos_rep" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_rep" value="0" id="input_check_hidden" />
                  <label for="hos_etc">기타</label>
                  <input type="checkbox" name="hos_etc" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_etc" value="0" id="input_check_hidden" />
                </p>
                <p>
                  <label>진료과목<strong class="require">필수</strong></label>
                  <label for="hos_gs">외과</label>
                  <input type="checkbox" name="hos_gs" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_gs" value="0" id="input_check_hidden" />
                  <label for="hos_im">내과</label>
                  <input type="checkbox" name="hos_im" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_im" value="0" id="input_check_hidden" />
                  <label for="hos_em">응급의학과</label>
                  <input type="checkbox" name="hos_em" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_em" value="0" id="input_check_hidden" />
                  <label for="hos_rm">재활의학과</label>
                  <input type="checkbox" name="hos_rm" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_rm" value="0" id="input_check_hidden" />
                  <label for="hos_vac">예방접종</label>
                  <input type="checkbox" name="hos_vac" id="input_check" value="1" class="blue">
                  <input type="hidden" name="hos_vac" value="0" id="input_check_hidden" />
                </p>
                <p class="hos_intro">
                  <label for="hos_intro">병원 소개글</label>
                  <textarea name="hos_intro" id="hos_intro" cols="50" rows="5" maxlength="1000"></textarea>
                </p>
              </div>
            </fieldset>
          </form>
        </section>

        <div class="btnJoinArea">
          <button type="submit" class="btnOk bluebox" id="submit" onclick="return check()">회원가입</button>
          <button type="reset" class="btnCancle bluebox" onclick="reset()">초기화</button>
        </div>

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