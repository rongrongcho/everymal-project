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
            <meta charset="UTF-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <meta name="_csrf" content="your_csrf_token_value" />
            <meta name="_csrf_header" content="your_csrf_header_value" />
            <title>병원 회원 > 등록 신청 페이지</title>
            <link rel="stylesheet" type="text/css" href="${contextPath }/resources/css/reset.css" />
            <link rel="stylesheet" type="text/css" href="${contextPath }/resources/css/common.css" />
            <link rel="stylesheet" type="text/css" href="${contextPath }/resources/css/hosApplication.css" />
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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
                          <a href="${contextPath}/login.jsp">로그인•회원가입</a>
                        </c:otherwise>
                      </c:choose>
                    </li>
                  </ul>
                </nav>
              </header>
              <h2 class="pagetitlte">병원 등록 및 관리</h2>
              <section id="sidebar_Area">
                <div class="sidebars" id="sb_sidebar"></div>
              </section>

              <section id="left_Area">
                <div class="profileBox">
                  <div class="proimgBox">
                    <img class="proImg"
                      src="${contextPath}/hos_MypageInfo/searchProfil.do?hos_id=${hosmypageinfoVO.hos_id}"
                      alt="나의 프로필 이미지" />
                  </div>
                  <div class="speech_bubble">
                    <img class="bubbleImg" src="${contextPath}/resources/img/말풍선.svg" alt="말풍선 배경" />
                    <p>
                      <span class="userID" name="hos_username">${hosmypageinfoVO.hos_username }</span>님 환영합니다!
                    </p>
                  </div>
                </div>
                <div class="leftCategory">
                  <ul class="CatrgoryBox">
                    <li class="firstMenu">
                      마이페이지
                      <ul>
                        <li class="secondMenu"><a
                            href="${contextPath }/hos_MypageInfo/hosUserInformation.do?hos_id=${hosmypageinfoVO.hos_id }">내
                            정보 관리</a></li>
                        <li class="secondMenu"><a
                            href="${contextPath }/hos_MypageInfo/hosInformation.do?hos_id=${hosmypageinfoVO.hos_id }">내
                            병원 관리</a></li>
                        <li class="secondMenu">
                          <a href="${contextPath }/hosReview/hosReviewList.do">병원리뷰 관리</a>
                        </li>
                        <li class="secondMenu">
                          <a href="${contextPath }/hosReply/listMyReply.do">답변 관리</a>
                        </li>
                      </ul>
                    </li>
                    <li class="firstMenu">
                      예약 관리
                      <ul>
                        <li class="secondMenu">
                          <a href="${contextPath}/hosres">예약관리</a>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </section>
              <div class="grey_line"></div>
              <section id="tab_Area">
                <div class="tab_nav">
                  <ul class="tab_Btns">
                    <li class="tBtn tBtn1 notChoice"><a
                        href="${contextPath }/hos_MypageInfo/hosUserInformation.do?hos_id=${hosmypageinfoVO.hos_id }"><span
                          class="pointT">내</span><span>
                          정보</span></a></li>
                    <li class="tBtn tBtn2 choiceTabNav"><a
                        href="${contextPath }/hos_MypageInfo/hosInformation.do?hos_id=${hosmypageinfoVO.hos_id }"><span
                          class="pointT">내</span><span> 병원</span></a></li>
                    <li class="tBtn tBtn3 notChoice">
                      <a href="${contextPath }/hosReview/hosReviewList.do"><span class="pointT span3">병원</span><span
                          class="span3"> 리뷰</span></a>
                    </li>
                    <li class="tBtn tBtn4 notChoice">
                      <a href="${contextPath }/hosReply/listMyReply.do"><span class="span4">답변</span></a>
                    </li>
                  </ul>
                </div>
                <div class="tabcontents">
                  <div id="tabBox" class="tab-content">
                    <div class="inputbox"></div>
                    <form id="imgForm" method="post" enctype="multipart/form-data">
                      <div class="agreeform formbox">
                        <label for="">병원 아이디</label>
                        <input type="text" name="hos_id" value="${hosmypageinfoVO.hos_id}" readonly />
                      </div>
                      <div class="agreeform formbox">
                        <label for="">병원 이름</label>
                        <input type="text" name="hos_name" value="${hosmypageinfoVO.hos_name}" readonly />
                        <input type="hidden" name="hos_code" value="${hosmypageinfoVO.hos_code}" />
                      </div>
                      <div class="formbox">
                        <p class="proImg infoTitle" for="thumimgup">썸네일 등록</p>
                        <div class="upload-box">
                          <label class="file-label" for="img_upload">파일 선택</label>
                          <input class="file" id="img_upload" name="img_upload" type="file" multiple
                            onchange="previewImage(this,'View_area')" />
                        </div>
                        <div id="imgBox">
                          <c:choose>
                            <c:when test="${not empty hosmypageinfoVO.himg1}">
                              <img
                                src="${contextPath}/resources/imgRepo/hos_images/thumbnail/${hosmypageinfoVO.hos_code }/${hosmypageinfoVO.himg1}"
                                alt="이미지1" />
                            </c:when>
                            <c:otherwise>
                              <img src="${contextPath}/resources/img/noPhoto.png" alt="No Photo" />
                            </c:otherwise>
                          </c:choose>
                          <c:choose>
                            <c:when test="${not empty hosmypageinfoVO.himg2}">
                              <img
                                src="${contextPath}/resources/imgRepo/hos_images/thumbnail/${hosmypageinfoVO.hos_code }/${hosmypageinfoVO.himg2}"
                                alt="이미지2" />
                            </c:when>
                            <c:otherwise>
                              <img src="${contextPath}/resources/img/noPhoto.png" alt="No Photo" />
                            </c:otherwise>
                          </c:choose>
                          <c:choose>
                            <c:when test="${not empty hosmypageinfoVO.himg3}">
                              <img
                                src="${contextPath}/resources/imgRepo/hos_images/thumbnail/${hosmypageinfoVO.hos_code }/${hosmypageinfoVO.himg3}"
                                alt="이미지3" />
                            </c:when>
                            <c:otherwise>
                              <img src="${contextPath}/resources/img/noPhoto.png" alt="No Photo" />
                            </c:otherwise>
                          </c:choose>
                        </div>

                        <span id='View_area'></span>
                        <div id="resultDiv">
                          <p th:text="${log }">
                        </div>
                      </div>
                      <c:choose>
                        <c:when test="${hosmypageinfoVO.hos_status == '승인'}">
                          <div class="adminNotice noticebox">지켜야할 수칙</div>
                          <input type="button" class="btns" onclick="dataSubmit();" value="썸네일 수정 요청">
                          <a href="#" class="btns">등록취소</a>
                        </c:when>
                        <c:when test="${hosmypageinfoVO.hos_status == '미승인'}">
                          <div class="adminNotice noticebox">최대 3장의 사진이 등록 가능합니다.</div>
                          <input type="button" class="btns" onclick="dataSubmit();" value="등록 신청">
                        </c:when>
                        <c:when test="${hosmypageinfoVO.hos_status == '거절'}">
                          <div class="adminNotice noticebox">
                            그냥 마음에 안들어서용; 신청 철회 후 다시 신청해주세요 !
                          </div>
                          <input type="button" class="btns" onclick="dataSubmit();" value="등록 재신청">
                        </c:when>
                        <c:otherwise>
                          <div class="noticebox">
                            담당자가 확인중입니다! 잠시만 기다려주세요
                          </div>
                          <a href="#" class="btns">신청 철회</a>
                        </c:otherwise>
                      </c:choose>
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
          </body>
          <script>
            var fileArr;
            var fileInfoArr = [];

            //파일 확장자 체크 
            var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");

            function checkExtension(fileName) {
              if (regex.test(fileName)) {
                alert("해당 종류의 파일은 썸네일로 사용할 수 없습니다.");
                return false;
              }
              return true;
            }

            //썸네일 클릭시 삭제.
            function fileRemove(index) {
              console.log("index: " + index);
              fileInfoArr.splice(index, 1);

              var imgId = "#img_id_" + index;
              $(imgId).remove();
              console.log(fileInfoArr);
            }

            //썸네일 미리보기.
            function previewImage(targetObj, View_area) {
              var files = targetObj.files;
              fileArr = Array.prototype.slice.call(files);

              var preview = document.getElementById(View_area); //div id
              var ua = window.navigator.userAgent;

              var imgBox = document.getElementById('imgBox');

              // 기존의 이미지들 삭제
              imgBox.innerHTML = '';

              //ie일때(IE8 이하에서만 작동)
              if (ua.indexOf("MSIE") > -1) {
                targetObj.select();
                try {
                  var src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
                  var ie_preview_error = document.getElementById("ie_preview_error_" + View_area);


                  if (ie_preview_error) {
                    preview.removeChild(ie_preview_error); //error가 있으면 delete
                  }

                  var img = document.getElementById(View_area); //이미지가 뿌려질 곳

                  //이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
                  img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + src + "', sizingMethod='scale')";
                } catch (e) {
                  if (!document.getElementById("ie_preview_error_" + View_area)) {
                    var info = document.createElement("<p>");
                    info.id = "ie_preview_error_" + View_area;
                    info.innerHTML = e.name;
                    preview.insertBefore(info, null);
                  }
                }
                //ie가 아닐때(크롬, 사파리, FF)
              } else {
                var files = targetObj.files;


                for (var i = 0; i < files.length; i++) {
                  var file = files[i];
                  fileInfoArr.push(file);

                  var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
                  if (!file.type.match(imageType))
                    continue;


                  var span = document.createElement('span');
                  span.id = "img_id_" + i;
                  span.style.width = '150px';
                  span.style.height = '150px';
                  span.style.marginRight = '15px';
                  preview.appendChild(span);

                  var img = document.createElement("img");
                  img.className = "addImg";
                  img.classList.add("obj");
                  img.file = file;
                  img.style.width = 'inherit';
                  img.style.height = 'inherit';
                  img.style.cursor = 'pointer';
                  const idx = i;
                  img.onclick = () => fileRemove(idx);   //이미지를 클릭했을 때.
                  span.appendChild(img);

                  if (window.FileReader) { // FireFox, Chrome, Opera 확인.
                    var reader = new FileReader();
                    reader.onloadend = (function (aImg) {
                      return function (e) {
                        aImg.src = e.target.result;
                      };
                    })(img);
                    reader.readAsDataURL(file);
                  } else { // safari is not supported FileReader
                    //alert('not supported FileReader');
                    if (!document.getElementById("sfr_preview_error_"
                      + View_area)) {
                      var info = document.createElement("p");
                      info.id = "sfr_preview_error_" + View_area;
                      info.innerHTML = "not supported FileReader";
                      preview.insertBefore(info, null);
                    }
                  }
                }
              }
            }

            //form데이터 전송
            function dataSubmit() {
              var token = $("meta[name='_csrf']").attr("content"); // 멀티 파일 아작스 무조건 의무사항 : token과 header 필수 !!! 공식임 
              var header = $("meta[name='_csrf_header']").attr("content");
              var contextPath = "${pageContext.request.contextPath}";
              var hos_id = "${hosmypageinfoVO.hos_id}";

              var formData = new FormData(); // formData 객체 생성 
              //var files = $("#img_upload")[0].files;

              // fileInfoArr 배열의 파일 정보를 formData에 추가
              for (var i = 0; i < fileInfoArr.length; i++) {
                formData.append('files', fileInfoArr[i]);
              }

              $.ajax({
                beforeSend: function (xhr) { // 멀티파일 일때 beforeSend 무조건 공식 
                  xhr.setRequestHeader(header, token);
                },
                url: contextPath + "/hos_MypageInfo/imgUpload.do", // 어따 보내줄지 경로 설정 , 매핑명과 동일해야함 (절대 경로)
                data: formData,
                processData: false, // processData , contentType 은 form 으로 보낼 때 필수 설정 
                contentType: false,
                type: "POST",
                success: function (result) { // 썸네일 업로드 하면 페이지 이동 만약 refresh 없이 화면을 변경 시키고 싶다면 참조 memberList.jsp 참조 
                  alert("썸네일 업로드가 되었습니다.");
                  window.location.href = contextPath + "/hos_MypageInfo/hosInformation.do?hos_id=" + hos_id;
                },
                error: function (err) { //console에 error 표시하려면 있어야함 
                  console.log("err", err);
                },
              });
            }





          </script>

          </html>