<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- core라이브러리를 c라는 접두사를 따와서 쓰겠다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <% request.setCharacterEncoding("utf-8"); %>
      <!DOCTYPE html>
      <html lang="ko">

      <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>리뷰 목록 페이지</title>
        <link rel="stylesheet" href="${contextPath}/css/reset.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/hosReview.css" />
        <script src="${contextPath}/js/jquery-3.6.3.min.js"></script>

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
          <h2 class="pagetitlte">병원리뷰 관리</h2>
          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
            </div>
          </section>

          <section id="left_Area">
            <div class="profileBox">
              <div class="proimgBox">
                <img class="proImg" src="${contextPath}/img/cat2.png" alt="나의 프로필 이미지" />
              </div>
              <div class="speech_bubble">
                <img class="bubbleImg" src="${contextPath}/img/말풍선.svg" alt="말풍선 배경">
                <p><span class="userID">user01</span>님 환영합니다!</p>
              </div>
            </div>
            <div class="leftCategory">
              <ul class="CatrgoryBox">
                <li class="firstMenu">마이페이지
                  <ul>
                    <li class="secondMenu"><a href="${contextPath }/hosMypageInfo/myInfo.do">내 정보 관리</a></li>
                    <li class="secondMenu"><a href="${contextPath }/hosMypageInfo/myHosInfo.do">내 병원 관리</a></li>
                    <li class="secondMenu"><a href="${contextPath }/hosReview/hosReviewList.do">병원리뷰 관리</a></li>
                    <li class="secondMenu"><a href="${contextPath }/hosReply/listMyReply.do">답변 관리</a></li>
                  </ul>
                </li>
                <li class="firstMenu">예약 관리
                  <ul>
                    <li class="secondMenu"><a href="${contextPath}/hosres">예약관리</a></li>
                  </ul>
                </li>


              </ul>
            </div>


          </section>
          <div class="grey_line"></div>
          <section id="tab_Area">
            <div class="tab_nav">
              <ul class="tab_Btns">
                <li class="tBtn tBtn1 notChoice"><a href="${contextPath }/hosMypageInfo/myInfo.do"><span
                      class="pointT">내</span><span>
                      정보</span></a></li>
                <li class="tBtn tBtn2 notChoice"><a href="${contextPath }/hosMypageInfo/myHosInfo.do"><span
                      class="pointT">내</span><span> 병원</span></a></li>
                <li class="tBtn tBtn3 choiceTabNav"><a href="${contextPath }/hosReview/hosReviewList.do"><span
                      class="pointT span3">병원</span><span class="span3"> 리뷰</span></a></li>
                <li class="tBtn tBtn4 notChoice"><a href="${contextPath }/hosReply/listMyReply.do"><span
                      class=" span4">답변</span></a>
                </li>


              </ul>
            </div>
            <div class="tabcontents">
              <div id="tabBox" class="tab-content">
                <p class="total_score total_p">병원 총점 <span class="jumsu">4.9</span><span class="ojum">/5</span></p>
                <p class="total_review total_p">등록된 리뷰 수 <span class="gatsoo">${review_count}</span class="ga">개</p>
                <form action="${contextPath}/hosReview/hosRevDel.do" method="post">
                  <table>
                    <colgroup>
                      <col width="10%" />
                      <col width="15%" />
                      <col width="15%" />
                      <col width="45%" />
                      <col width="15%" />

                    </colgroup>
                    <thead>
                      <tr>
                        <th>선택</th>
                        <th>리뷰등록번호</th>
                        <th>별점</th>
                        <th>리뷰제목</th>
                        <th>등록일</th>

                      </tr>
                    </thead>
                    <c:choose>
                      <c:when test="${empty hosReviewList}">
                        <tr>
                          <td colspan="5" align="center">리뷰가 존재하지 않습니다.</td>
                        </tr>
                      </c:when>
                      <c:when test="${!empty hosReviewList}">
                        <c:forEach var="hosreview" items="${hosReviewList}">
                          <tr>
                            <td><input type="checkbox" name="del_chk" id="del_chk" value="${hosreview.rv_code}"></td>
                            <td><a class="res_code"
                                href="${contextPath}/hosReview/viewReview.do?rv_code=${hosreview.rv_code}">
                                ${hosreview.rv_code}</a></td>
                            <td>
                              <c:choose>
                                <c:when test="${hosreview.rv_rate==1}">
                                  🌟
                                </c:when>
                                <c:when test="${hosreview.rv_rate==2}">🌟🌟</c:when>
                                <c:when test="${hosreview.rv_rate==3}">🌟🌟🌟</c:when>
                                <c:when test="${hosreview.rv_rate==4}">🌟🌟🌟🌟</c:when>
                                <c:otherwise>🌟🌟🌟🌟🌟</c:otherwise>
                              </c:choose>
                            </td>
                            <td>
                              <<a class="res_code"
                                href="${contextPath}/hosReview/viewReview.do?rv_code=${hosreview.rv_code}">
                                ${hosreview.rv_title}</a>
                            </td>
                            <td>${hosreview.rv_date}</td>

                          </tr>
                        </c:forEach>
                      </c:when>
                    </c:choose>

                  </table>
                  <input type="submit" id="edit_btn3" class="edit_btn" value="삭제요청">
                </form>
                <div id="divPaging">



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
          </footer>
        </div>
      </body>

      </html>