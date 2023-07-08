<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <!DOCTYPE html>
    <html lang="ko">

    <head>
      <meta charset="UTF-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>개인회원 회원가입</title>
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
        }

        function maxLengthCheck(object) {
          if (object.value.length > object.maxLength) {
            object.value = object.value.slice(0, object.maxLength);
          }
        }

        function submit() {
          //회원정보 저장
          data = $("#joinForm").serialize();
          $.ajax({
            type: "post",
            async: false,
            dataType: "text",
            url: "${contextPath}/member/addMember.do",
            data: data,
            success: function (result) {
              var user_code = result;
              //동물정보 저장
              $("form[name=joinAnimalForm]").each(function (index, item) {
                data = $("#joinAnimalForm_" + index).serialize();
                data = data + "&user_code=" + user_code;
                $.ajax({
                  type: "post",
                  async: false,
                  dataType: "text",
                  url: "${contextPath}/animal/addAnimal.do",
                  data: data
                });
              });
            }
          });
          next();
        }

        function next() {
          alert('회원가입완료.');
          location.href = "${contextPath}/main.do";
        }

        function reset() {
          $("#memberArea").find(':text').val('');
          $("#memberArea").find(':password').val('');
          $("#user_tel1").val('선택').prop("selected", true);
          $("#user_tel2").val("");
          $("#user_tel3").val("");
          $("input[type='number']").val("");
          $("#email").val("")
          $("textarea").val("");
          $('select[name="pet_types"]').val('').prop("selected", true);
          $('select[name="b_type"]').val('').prop("selected", true);
          $('input[name="pet_sex"]').prop('checked', false);
        }
        function check() {
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
          /* 이름 유효성 검사 */
          if (joinForm.user_name.value.length == 0) {
            alert("이름을 입력해주세요.");
            joinForm.user_name.focus();
            return false;
          }
          /* 비밀번호 및 비밀번호 확인 유효성 검사 */
          if (joinForm.user_pwd.value.length == 0) {
            alert("비밀번호를 입력해주세요.");
            joinForm.user_pwd.focus(); // 포커스를 이동시켜 바로 비밀번호를 입력할 수 있게
            return false;
          }
          var pwdRegExp = /^[a-zA-z0-9]{4,8}$/; //비밀번호 유효성 검사
          if (!pwdRegExp.test(joinForm.user_pwd.value)) {
            alert("비밀번호는 영문 대소문자와 숫자 4~8자리로 입력해야합니다.");
            joinForm.user_pwd.focus();
            return false;
          }
          if (joinForm.confirmPassword.value.length == 0) {
            alert("비밀번호 확인을 입력해주세요.");
            joinForm.confirmPassword.focus();
            return false;
          }
          if (joinForm.user_pwd.value != joinForm.confirmPassword.value) {
            alert("비밀번호가 일치하지 않습니다.");
            joinForm.confirmPassword.select(); //이걸로 하면 focus 이동하면서 입력한게 블록으로 선택됨
            return false;
          }
          /* 전화번호 유효성 검사 */
          if (joinForm.user_tel1.value == "") {
            alert("번호를 선택해주세요.");
            joinForm.user_tel1.focus();
            return false;
          }
          if (joinForm.user_tel2.value.length == 0) {
            alert("전화번호를 입력해주세요.");
            joinForm.user_tel2.focus();
            return false;
          }
          if (joinForm.user_tel3.value.length == 0) {
            alert("전화번호를 입력해주세요.");
            joinForm.user_tel3.focus();
            return false;
          }
          if (isNaN(joinForm.user_tel2.value)) {
            alert("전화번호는 숫자만 입력가능합니다.");
            joinForm.user_tel2.focus();
            return false;
          }
          if (isNaN(joinForm.user_tel3.value)) {
            alert("전화번호는 숫자만 입력가능합니다.");
            joinForm.user_tel3.focus();
            return false;
          }
          /* 주소 유효성 검사 */
          if (joinForm.user_zipcode.value.length == 0) {
            alert("주소를 입력해주세요.");
            joinForm.user_zipcode.focus();
            return false;
          }
          if (joinForm.user_addr1.value.length == 0) {
            alert("주소를 입력해주세요.");
            joinForm.user_addr1.focus();
            return false;
          }
          if (joinForm.user_addr2.value.length == 0) {
            alert("주소를 입력해주세요.");
            joinForm.user_addr2.focus();
            return false;
          }
          /* 이메일 유효성 검사 */
          if (joinForm.user_email.value.length == 0) {
            alert("이메일을 입력해주세요.");
            joinForm.user_email.focus();
            return false;
          }

          /* 동물 유효성 */
          try {
            $("form[name=joinAnimalForm]").each(function (index, item) {
              if ($("#joinAnimalForm_" + index).find('#pet_name').val() == '') {
                alert("반려동물의 이름을 입력해주세요.");
                $("#joinAnimalForm_" + index).find('#pet_name').focus();
                throw 'finish';
              }
              if ($("#joinAnimalForm_" + index).find('#pet_age').val() == '') {
                alert("반려동물의 나이을 입력해주세요.");
                $("#joinAnimalForm_" + index).find('#pet_age').focus();
                throw 'finish';
              }
              flag = false;
              for (i = 0; i < $("#joinAnimalForm_" + index).find('input[name="pet_sex"]').length; i++) {
                if ($("#joinAnimalForm_" + index).find('input[name="pet_sex"]')[i].checked) {
                  flag = true;
                  break; // 라디오박스에서는 1개만 선택될 수 있으므로 사용가능
                }
              }
              if (flag == false) {
                $("#joinAnimalForm_" + index).find('#pet_sex_f').focus();
                alert("반려동물 성별을 체크해주세요.");
                throw 'finish';
              }
              if ($("#joinAnimalForm_" + index).find('#pet_types').val() == '') {
                alert("반려동물 종을 선택해주세요.");
                $("#joinAnimalForm_" + index).find('#pet_types').focus();
                throw 'finish';
              }
              if ($("#joinAnimalForm_" + index).find('#pet_number').val() == '') {
                alert("반려동물 등록번호를 입력해주세요.");
                $("#joinAnimalForm_" + index).find('#pet_number').focus();
                throw 'finish';
              }
              if (isNaN($("#joinAnimalForm_" + index).find('#pet_number').val())) {
                alert("반려동물 등록번호는 숫자만 입력가능합니다.");
                $("#joinAnimalForm_" + index).find('#pet_number').select();
                throw 'finish';
              }
              if ($("#joinAnimalForm_" + index).find('#b_type').val() == '') {
                alert("반려동물의 혈액형을 선택해주세요.");
                $("#joinAnimalForm_" + index).find('#b_type').focus();
                throw 'finish';
              }
              if ($("#joinAnimalForm_" + index).find('#pet_weight').val() == '') {
                alert("반려동물의 몸무게를 입력해주세요.");
                $("#joinAnimalForm_" + index).find('#pet_weight').focus();
                throw 'finish';
              }
              if (isNaN($("#joinAnimalForm_" + index).find('#pet_weight').val())) {
                alert("반려동물 몸무게는 숫자만 입력가능합니다.");
                $("#joinAnimalForm_" + index).find('#pet_weight').select();
                throw 'finish';
              }
            });
          } catch (Exception) {
            if (Exception !== 'finish') throw Exception;
            else return false;
          }
          submit();
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
            url: "${contextPath}/member/idChk.do",
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
      </script>
      <script src="${contextPath }/resources/js/join.js"></script>
      <link rel="stylesheet" href="${contextPath }/resources/css/reset.css" />
      <link rel="stylesheet" href="${contextPath }/resources/css/common.css" />
      <link rel="stylesheet" href="${contextPath }/resources/css/join.css" />
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
        <section id="memberArea" class="memberJoin memberArea">
          <form action="${contextPath}/member/addMember.do" method="post" id="joinForm" name="joinForm">
            <fieldset>
              <legend>회원가입 정보 입력 폼</legend>
              <div id="user_form" class="formBox">
                <div class="formSign"><strong class="require">필수</strong>는 반드시 입력하여야 하는 항목입니다.</div>
                <h2>회원정보</h2>
                <p>
                  <label for="name">성명<strong class="require">필수</strong></label>
                  <input type="text" id="name" name="user_name" required placeholder="홍길동">
                </p>
                <p>
                  <label for="id">아이디<strong class="require">필수</strong></label>
                  <input type="text" id="id" name="user_id" required placeholder="everymal">
                  <input type="button" id="idck" class="greenbox" value="ID중복체크" onclick="fn_idCheck();"><span
                    id="resultMessage"></span>
                </p>
                <p>
                  <label for="password">비밀번호<strong class="require">필수</strong></label>
                  <input type="password" id="password" name="user_pwd" required placeholder="영문/숫자 4~8자 이내">
                </p>
                <p>
                  <label for="confirmPassword">비밀번호 확인<strong class="require">필수</strong></label>
                  <input type="password" id="confirmPassword=" name="confirmPassword" required
                    placeholder="한번 더 입력해주세요">
                </p>
                <p>
                  <label for="user_tel1">연락처<strong class="require">필수</strong></label>
                  <select name="user_tel1" id="user_tel1" required>
                    <option value="" selected="selected">선택</option>
                    <option value="010" checked>010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="017">017</option>
                    <option value="018">018</option>
                    <option value="019">019</option>
                  </select>
                  <input type="tel" id="user_tel2" name="user_tel2" maxlength="4" size="10" required>
                  <input type="tel" id="user_tel3" name="user_tel3" maxlength="4" size="10" required>
                </p>
                <p>
                  <label for="addrbtn" class="labelAddress">주소<strong class="require">필수</strong></label>
                  <input type="button" class="greenbox" value="우편번호찾기" id="addrbtn" onclick="kakaopost()">
                  <input type="text" name="user_zipcode" id="zipcode" size="10" readonly required><br>
                <div id="address">
                  <input type="text" name="user_addr1" id="address1" size="100" required>
                  <input type="text" name="user_addr2" id="address2" size="100" required>
                </div>
                </p>

                <p>
                  <label for="email">이메일<strong class="require">필수</strong></label>
                  <input type="email" id="email" name="user_email" size="30" required
                    placeholder="입력 예) everymal@gmail.com">
                </p>

              </div>
            </fieldset>
          </form>
          <div id="animalinsert">
            <div id="animalplus">+</div>
            <div id="animalminus">-</div>
          </div>
          <!-- 동물정보 -->
          <form action="${contextPath}/member/addAnimal.do" method="post" id="joinAnimalForm_0" name="joinAnimalForm"
            class="joinAnimalForm">
            <fieldset>

              <div id="pet_form">
                <div class="formSign"><strong class="require">필수</strong>는 반드시 입력하여야 하는 항목입니다.</div>
                <h2>반려동물 정보</h2>
                <p class="pet_name">
                  <label for="pet_name">반려동물 이름<strong class="require">필수</strong></label>
                  <input type="text" id="pet_name" name="pet_name" required placeholder="토리">
                </p>
                <p class="pet_age">
                  <label for="pet_age">반려동물 나이<strong class="require">필수</strong></label>
                  <input type="number" id="pet_age" name="pet_age" maxlength="3" oninput="maxLengthCheck(this)">
                </p>
                <p class="pet_gender">
                  <label for="pet_gender">반려동물 성별<strong class="require">필수</strong></label>
                  ♀<input type="radio" id="pet_sex_f" name="pet_sex" value="0" required>
                  ♂<input type="radio" id="pet_sex_m" name="pet_sex" value="1">
                </p>
                <p class="zip">
                  <label for="pet_types">반려동물 종류<strong class="require">필수</strong></label>
                  <select name="pet_types" id="pet_types" required>
                    <option value="" selected="selected">선택</option>
                    <option value="개">개</option>
                    <option value="고양이">고양이</option>
                    <option value="소동물">소동물</option>
                    <option value="어류">어류</option>
                    <option value="조류">조류</option>
                    <option value="파충류">파충류</option>
                    <option value="기타">기타</option>
                  </select>
                </p>
                <p class="pet_number">
                  <label for="pet_number">반려동물 등록번호<strong class="require">필수</strong></label>
                  <input type="text" id="pet_number" name="pet_number" maxlength="15" required>
                </p>
                <p class="b_type">
                  <label for="b_type">반려동물 혈액형<strong class="require">필수</strong></label>
                  <select name="b_type" id="b_type" required>
                    <option value="" selected="selected">선택</option>
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
                    <optgroup label="기타">
                      <option value="기타">기타</option>
                    </optgroup>
                  </select>
                </p>
                <p class="pet_weight">
                  <label for="pet_weight">반려동물 몸무게<strong class="require">필수</strong></label>
                  <input type="text" id="pet_weight" name="pet_weight" maxlength="3">
                </p>
                <p class="pet_etc">
                  <label for="pet_etc">반려동물 특이사항</label>
                  <textarea name="pet_etc" id="pet_etc" cols="50" rows="5"></textarea>
                </p>
              </div>

            </fieldset>
          </form>
        </section>

        <div class="btnJoinArea">
          <button type="submit" class="btnOk" onclick="return check()">회원가입</button>
          <button type="reset" class="btnCancle" onclick="reset()">초기화</button>
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