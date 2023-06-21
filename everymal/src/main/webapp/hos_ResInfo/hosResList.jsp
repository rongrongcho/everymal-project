<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- core라이브러리를 c라는 접두사를 따와서 쓰겠다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <c:set var="resList" value="${hosResMap.hosResList }" />
    <c:set var="totRes" value="${hosResMap.totalhosRes }" />
    <c:set var="section" value="${hosResMap.section }" />
    <c:set var="pageNum" value="${hosResMap.pageNum }" />
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

        <link rel="stylesheet" href="${contextPath}/css/hosRes.css">

        <script src="js/jquery-3.6.3.min.js"></script>
        <style type="text/css">
          .res_code:hover {
            color: #91D3F9;
          }
        </style>
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
          <h2 class="pagetitlte">예약 목록</h2>
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
                <p><span class="userID" name="hos_username">${hosmypageinfoVO.hos_username }</span>님 환영합니다!</p>
              </div>
            </div>
            <div class="leftCategory">
              <ul class="CatrgoryBox">
                <li class="firstMenu">마이페이지
                  <ul>
                    <li class="secondMenu"><a href="${contextPath}/hosMypageInfo/myInfo.do">내 정보 관리</a></li>
                    <li class="secondMenu"><a href="${contextPath}/hosMypageInfo/myHosInfo.do">내 병원 관리</a></li>
                    <li class="secondMenu"><a href="${contextPath}/hosReview/hosReviewList.do">병원리뷰 관리</a></li>
                    <li class="secondMenu"><a href="${contextPath}/hosReply/listMyReply.do">답변 관리</a></li>
                  </ul>
                </li>
                <li class="firstMenu">예약 관리
                  <ul>
                    <li class="secondMenu"><a href="${contextPath}/hosres/hosResList.do">예약관리</a></li>
                  </ul>
                </li>


              </ul>
            </div>


          </section>
          <div class="grey_line"></div>
          <section id="tab_Area">
            <div class="tabcontents">
              <div id="tabBox" class="tab-content">

                <p class="total_answer">총 예약 개수&nbsp;&nbsp; <span class="answer_number">${res_count}</span></p>
                <table>
                  <thead>
                    <th>예약 등록 번호</th>
                    <th>예약자</th>
                    <th>반려동물 종</th>
                    <th>예약 날짜</th>

                  </thead>

                  <c:choose>
                    <c:when test="${empty resList}">
                      <tr>
                        <td colspan="5" align="center">예약이 존재하지 않습니다.</td>
                      </tr>
                    </c:when>
                    <c:when test="${!empty resList}">
                      <c:forEach var="hosres" items="${resList}">
                        <tr>
                          <td><a class="res_code"
                              href="${contextPath}/hosres/hosResForm.do?res_code=${hosres.res_code}">
                              ${hosres.res_code}</a></td>
                          <td>${hosres.user_name}</td>
                          <td>${hosres.pet_types}</td>

                          <td>${hosres.receipt_date}</td>

                        </tr>
                      </c:forEach>
                    </c:when>
                  </c:choose>
                </table>


                <!-- -페이징!! -->
                <div id="divPaging">
                  <c:if test="${totRes !=0}">
                    <c:choose>
                      <c:when test="${totRes > 50}">
                        <c:choose>
                          <c:when test="${totRes%5==0}">
                            <c:set var="tot" value="${totRes/8}" />
                          </c:when>
                          <c:otherwise>
                            <c:set var="tot" value="${totRes/8+1}" />
                          </c:otherwise>
                        </c:choose>


                        <c:forEach var="page" begin="1" end="${tot-(section-1)*5}" step="1">

                          <c:if test="${not doneLoop}">
                            <c:if test="${section>1 && page==1 }">
                              <a href="${contextPath}/hosres/hosResList.do?section=${section-1}&pageNum=${pageNum}">
                                prev</a>
                            </c:if>
                            <c:choose>

                              <c:when test="${page==pageNum}">
                                <a class="selPage"
                                  href="${contextPath}/hosres/hosResList.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                              </c:when>
                              <c:otherwise>
                                <a class="noLine"
                                  href="${contextPath}/hosres/hosResList.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                              </c:otherwise>
                            </c:choose>
                            <c:if test="${page==5}">
                              <a href="${contextPath}/hosres/hosResList.do?section=${section+1}&pageNum=${pageNum}">
                                next</a>
                              <c:set var="doneLoop" value="true" />
                            </c:if>
                          </c:if>
                        </c:forEach>
                      </c:when>


                      <c:when test="${totRes <= 50 }">
                        <c:choose>
                          <c:when test="${totRes%5==0}">
                            <c:set var="tot" value="${totRes/8}" />
                          </c:when>
                          <c:otherwise>
                            <c:set var="tot" value="${totRes/8+1}" />
                          </c:otherwise>
                        </c:choose>


                        <c:forEach var="page" begin="1" end="${tot}" step="1">
                          <c:choose>
                            <c:when test="${page==pageNum}">
                              <a class="selPage"
                                href="${contextPath}/hosres/hosResList.do?section=${section}&pageNum=${page}">${page}</a>
                            </c:when>
                            <c:otherwise>
                              <a class="noLine"
                                href="${contextPath}/hosres/hosResList.do?section=${section}&pageNum=${page}">${page}</a>
                            </c:otherwise>
                          </c:choose>
                        </c:forEach>
                      </c:when>
                    </c:choose>
                  </c:if>

                </div>

                <!-- 페이징끝 -->
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