<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" import="java.util.*" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%
request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>등록 신청 페이지</title>
    <link
      rel="stylesheet"
      type="text/css"
      href="${contextPath }/css/reset.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="${contextPath }/css/common.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="${contextPath }/css/hosApplication.css"
    />
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
            <img
              class="proImg"
              src="${contextPath}/hosProImgDown.do?hos_pro=${hosImgVO.hos_pro}&hos_code=${hos_code}"
              alt="나의 프로필 이미지"
            />
          </div>
          <div class="speech_bubble">
            <img
              class="bubbleImg"
              src="${contextPath}/img/말풍선.svg"
              alt="말풍선 배경"
            />
            <p>
              <span class="userID" name="hos_username"
                >${hosmypageinfoVO.hos_username }</span
              >님 환영합니다!
            </p>
          </div>
        </div>
        <div class="leftCategory">
          <ul class="CatrgoryBox">
            <li class="firstMenu">
              마이페이지
              <ul>
                <li class="secondMenu">
                  <a href="${contextPath }/hosMypageInfo/myInfo.do"
                    >내 정보 관리</a
                  >
                </li>
                <li class="secondMenu">
                  <a href="${contextPath }/hosMypageInfo/myHosInfo.do"
                    >내 병원 관리</a
                  >
                </li>
                <li class="secondMenu">
                  <a href="${contextPath }/hosReview/hosReviewList.do"
                    >병원리뷰 관리</a
                  >
                </li>
                <li class="secondMenu">
                  <a href="${contextPath }/hosReply/listMyReply.do"
                    >답변 관리</a
                  >
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
            <li class="tBtn tBtn1 notChoice">
              <a href="${contextPath }/hosMypageInfo/myInfo.do"
                ><span class="pointT">내</span><span> 정보</span></a
              >
            </li>
            <li class="tBtn tBtn2 choiceTabNav">
              <a href="${contextPath }/hosMypageInfo/myHosInfo.do"
                ><span class="pointT">내</span><span> 병원</span></a
              >
            </li>
            <li class="tBtn tBtn3 notChoice">
              <a href="${contextPath }/hosReview/hosReviewList.do"
                ><span class="pointT span3">병원</span
                ><span class="span3"> 리뷰</span></a
              >
            </li>
            <li class="tBtn tBtn4 notChoice">
              <a href="${contextPath }/hosReply/listMyReply.do"
                ><span class="span4">답변</span></a
              >
            </li>
          </ul>
        </div>
        <div class="tabcontents">
          <div id="tabBox" class="tab-content">
            <div class="inputbox"></div>
            <form
              action=""
              id="imgForm"
              name="myInfoForm"
              method="post"
              enctype="multipart/form-data"
            >
              <div class="thum_add">
                <div class="formbox">
                  <p class="proImg infoTitle" for="proimgup">프로필 등록</p>
                  <div class="upload-box">
                    <div id="drop-file" class="drag-file">
                      <img
                        src="https://img.icons8.com/pastel-glyph/2x/image-file.png"
                        alt="파일 아이콘"
                        class="image"
                      />
                      <p class="message">
                        이미지 파일을 <strong>드래그</strong>하세요.
                      </p>
                    </div>
                    <label class="file-label" for="chooseFile">파일 선택</label>
                    <input
                      class="file"
                      id="chooseFile"
                      type="file"
                      multiple
                      onchange="dropFile.handleFiles(this.files)"
                    />
                  </div>
                  <div id="files" class="files"></div>
                </div>
              </div>
              <div class="agreeform formbox">
                <label for="">병원 아이디</label>
                <input
                  type="text"
                  name="hos_id"
                  value="${appVO.hos_id}"
                  readonly
                />
              </div>
              <div class="agreeform formbox">
                <label for="">병원 이름</label>
                <input
                  type="text"
                  name="hos_name"
                  value="${appVO.hos_name}"
                  readonly
                />
                <input
                  type="hidden"
                  name="hos_code"
                  value="${appVO.hos_code}"
                />
              </div>
              <c:choose>
                <c:when test="${appVO.hos_status == '승인'}">
                  <div class="adminNotice noticebox">지켜야할 수칙</div>
                  <a href="#" class="btns">썸네일 수정 요청</a>
                  <a href="#" class="btns">등록취소</a>
                </c:when>
                <c:when test="${appVO.hos_status == '미승인'}">
                  <div class="adminNotice noticebox">병원 신청시 주의사항</div>
                  <a href="#" class="btns">등록 재신청</a>
                </c:when>
                <c:when test="${appVO.hos_status == '거절'}">
                  <div class="adminNotice noticebox">
                    그냥 마음에 안들어서용; 신청 철회 후 다시 신청해주세요 !
                  </div>
                  <a href="#" class="btns">등록 재신청</a>
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
            <a id="footerLogo" href="${contextPath}/index.jsp"
              ><img
                src="${contextPath}/img/EverymalLogo_w.svg"
                alt="로고"
                style="width: 250px; height: auto"
            /></a>
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
            <a href="tel:010-111-2222"
              >T. 1111-2222 (10:00 - 19:00 / 점심시간 12:30 - 13:30)</a
            >
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
    function DropFile(dropAreaId, fileListId) {
      let dropArea = document.getElementById(dropAreaId);
      let fileList = document.getElementById(fileListId);

      function preventDefaults(e) {
        e.preventDefault();
        e.stopPropagation();
      }

      function highlight(e) {
        preventDefaults(e);
        dropArea.classList.add("highlight");
      }

      function unhighlight(e) {
        preventDefaults(e);
        dropArea.classList.remove("highlight");
      }

      function handleDrop(e) {
        unhighlight(e);
        let dt = e.dataTransfer;
        let files = dt.files;

        handleFiles(files);

        const fileList = document.getElementById(fileListId);
        if (fileList) {
          fileList.scrollTo({ top: fileList.scrollHeight });
        }
      }

      function handleFiles(files) {
        files = [...files];
        files.forEach(previewFile);
      }

      function previewFile(file) {
        console.log(file);
        fileList.appendChild(renderFile(file));
      }

      function renderFile(file) {
    	let fileName=file.name;
    	let fileSize=file.size;
        let fileDOM = document.createElement("div");
        fileDOM.className = "file";
        fileDOM.innerHTML = `
        	      <div class="thumbnail">
        	        <img src="https://img.icons8.com/pastel-glyph/2x/image-file.png" alt="파일타입 이미지" class="image">
        	      </div>
        	      <div class="details">
        	        <header class="header">
        	          <span class="name">${fileName}</span>
        	          <span class="size">${fileSize}</span>
        	        </header>
        	        <div class="progress">
        	          <div class="bar"></div>
        	        </div>
        	        <div class="status">
        	          <span class="percent">100% done</span>
        	          <span class="speed">90KB/sec</span>
        	        </div>
        	      </div>
        	    `;
        return fileDOM;
      }

      dropArea.addEventListener("dragenter", highlight, false);
      dropArea.addEventListener("dragover", highlight, false);
      dropArea.addEventListener("dragleave", unhighlight, false);
      dropArea.addEventListener("drop", handleDrop, false);

      return {
        handleFiles,
      };
    }

    const dropFile = new DropFile("drop-file", "files");
  </script>
</html>
