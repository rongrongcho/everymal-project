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
            <title>관리자 > 등록 신청서 열람 페이지 </title>
            <link rel="stylesheet" type="text/css" href="${contextPath }/resources/css/reset.css" />
            <link rel="stylesheet" type="text/css" href="${contextPath }/resources/css/common.css" />
            <link rel="stylesheet" type="text/css" href="${contextPath }/resources/css/adminApplication.css" />
            <script src="http://code.jquery.com/jquery-latest.min.js"></script>
            <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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
              <h2 class="pagetitlte">병원 등록 신청서</h2>
              <div class="grey_line"></div>
              <section id="tab_Area">
                <div class="tabcontents">
                  <div id="tabBox" class="tab-content">

                    <div id="imgBox">
                      <c:choose>
                        <c:when test="${not empty appVO.himg1}">
                          <img
                            src="${contextPath}/resources/imgRepo/hos_images/thumbnail/${appVO.hos_code }/${appVO.himg1}"
                            alt="이미지1" />

                        </c:when>
                        <c:otherwise>
                          <img src="${contextPath}/resources/img/noPhoto.png" alt="No Photo" />
                        </c:otherwise>
                      </c:choose>
                      <c:choose>
                        <c:when test="${not empty appVO.himg2}">
                          <img
                            src="${contextPath}/resources/imgRepo/hos_images/thumbnail/${appVO.hos_code }/${appVO.himg2}"
                            alt="이미지2" />
                        </c:when>
                        <c:otherwise>
                          <img src="${contextPath}/resources/img/noPhoto.png" alt="No Photo" />
                        </c:otherwise>
                      </c:choose>
                      <c:choose>
                        <c:when test="${not empty appVO.himg3}">
                          <img
                            src="${contextPath}/resources/imgRepo/hos_images/thumbnail/${appVO.hos_code }/${appVO.himg3}"
                            alt="이미지3" />
                        </c:when>
                        <c:otherwise>
                          <img src="${contextPath}/resources/img/noPhoto.png" alt="No Photo" />
                        </c:otherwise>
                      </c:choose>
                      <div id="mainInfo">
                        <p class="rmdate"> ${appVO.rm_date}</p>
                        <p class="rmInfo">신청인 ID : <span class="rminfonames">${appVO.hos_id}</span></p>
                        <p class="rmInfo"><span class="rminfonames">${appVO.hos_name}</span></p>
                      </div>

                    </div>

                    <div class="rmInfoBox">




                      <form action="" method="post">
                        <c:choose>
                          <c:when test="${appVO.hos_status == '승인'}">
                            <div class="adminNotice">
                              <p>승인완료 된 건입니다. </p>
                            </div>
                          </c:when>
                          <c:when test="${appVO.hos_status == '미승인'}">

                            <button class="btns" onclick="approval(this.form);">등록 승인</button>
                            <button class="btns" onclick="rjRM(this.form);">등록 거절</button>
                            <textarea name="rm_say">${appVO.rm_say}</textarea>
                            <input type="hidden" name="hos_code" value="${appVO.hos_code}">
                          </c:when>
                          <c:when test="${appVO.hos_status == '거절'}">

                            <div class="adminNotice">
                              <p>반려 된 건 입니다. </p>
                              <p>반려 사유 : ${appVO.rm_say}</p>
                            </div>
                          </c:when>
                          <c:otherwise>
                            <!-- 아마 검토중 -->
                            <button class="btns" onclick="approval(this.form);">등록 승인</button>
                            <button class="btns" onclick="rjRM(this.form);">등록 거절</button>
                            <textarea name="rm_say">${appVO.rm_say}</textarea>
                            <input type="hidden" name="hos_code" value="${appVO.hos_code}">

                          </c:otherwise>
                        </c:choose>

                      </form>

                    </div>



                  </div>
                </div>
              </section>

              <script type="text/javascript">
                function rjRM(obj) {
                  obj.action = "${contextPath}/adminstrator/rjRM.do";
                  obj.submit();
                }
                function approval(obj) {
                  obj.action = "${contextPath}/adminstrator/approval.do";
                  obj.submit();
                }
              </script>

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
          </body>

          </html>