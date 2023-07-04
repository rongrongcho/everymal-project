<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- core라이브러리를 c라는 접두사를 따와서 쓰겠다 -->

    <% request.setCharacterEncoding("utf-8"); %>
      <c:set var="contextPath" value="${pageContext.request.contextPath}" />
      <c:set var="reviewList" value="${reviewMap.reviewList }" />
      <c:set var="totReview" value="${reviewMap.totalReviews }" />
      <c:set var="section" value="${reviewMap.section }" />
      <c:set var="pageNum" value="${reviewMap.pageNum }" />
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> 내 리뷰 관리 </title>

        <link rel="stylesheet" href="${contextPath}/css/reset.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/myReview.css" />
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
          <h2 class="pagetitlte">나의 리뷰 관리</h2>
          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
            </div>
          </section>

          <section id="left_Area">
            <div class="profileBox">
              <div class="proimgBox">
                <img class="proImg"
                  src="${contextPath}/proimgdown.do?imageFileName=${userVO.user_imgName}&user_id=${log_id}">
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
                <li class="tBtn tBtn1 notChoice">
                  <a href="${contextPath}/userMypage/myInfo.do"><span class="pointT">내</span><span>정보</span></a>
                </li>
                <li class="tBtn tBtn2 notChoice">
                  <a href="${contextPath}/petinfo/myPetList.do"><span class="pointT">펫</span><span>정보</span></a>
                </li>

                <li class="tBtn tBtn3 choiceTabNav">
                  <a href="${contextPath}/userMypage/myReview.do"><span class="pointT">내</span><span>리뷰</span></a>
                </li>
                <li class="tBtn tBtn4 notChoice">
                  <a href="${contextPath}/userMypage/myQuestion.do"><span class="pointT">내</span><span>질문</span></a>
                </li>
              </ul>
            </div>
            <div class="tabcontents">
              <p class="total_review total_p">등록한 리뷰 수 <span class="gatsoo">${totReview}</span class="ga">개</p>

              <div id="tabBox" class="tab-content">
                <!-- reviewList -->
                <form action="${contextPath}/userMypage/myRevDel.do" method="post" name="del">
                  <table>
                    <thead>
                      <tr>
                        <th>선택</th>
                        <th>등록 번호</th>
                        <th>별점</th>
                        <th>리뷰제목</th>
                        <th>등록일</th>
                      </tr>
                    </thead>
                    <tbody>

                      <c:choose>
                        <c:when test="${empty reviewList}">
                          <td colspan="5">작성하신 리뷰가 없습니다.</td>
                        </c:when>
                        <c:when test="${not empty reviewList}">
                          <c:forEach var="review" items="${reviewList}">
                            <tr>
                              <td><input type="checkbox" name="del_chk" id="del_chk" value="${review.rv_code}"></td>
                              <td>
                                <a class="goTodetailPage"
                                  href="${contextPath}/userMypage/viewReview.do?rv_code=${review.rv_code}">${review.rv_code}</a>
                              </td>
                              <td>
                                <c:choose>
                                  <c:when test="${review.rv_rate==1}"> 🌟 </c:when>
                                  <c:when test="${review.rv_rate==2}">🌟🌟</c:when>
                                  <c:when test="${review.rv_rate==3}">🌟🌟🌟</c:when>
                                  <c:when test="${review.rv_rate==4}">🌟🌟🌟🌟</c:when>
                                  <c:otherwise>🌟🌟🌟🌟🌟</c:otherwise>
                                </c:choose>
                              </td>

                              <td class="nono"><a class="goTodetailPage"
                                  href="${contextPath}/userMypage/viewReview.do?rv_code=${review.rv_code}">${review.rv_title}</a>
                              </td>
                              <td>${review.rv_date}</td>
                            </tr>
                          </c:forEach>
                        </c:when>
                      </c:choose>


                    </tbody>

                  </table>
                  <input type="submit" id="edit_btn3" class="edit_btn" value="삭제하기">

                </form>


              </div>
              <div id="divPaging">
                <c:if test="${totReview !=0}">
                  <c:choose>
                    <c:when test="${totReview > 50}">
                      <c:choose>
                        <c:when test="${totReview%8==0}">
                          <c:set var="tot" value="${totReview/8}" />
                        </c:when>
                        <c:otherwise>
                          <c:set var="tot" value="${totReview/8+1}" />
                        </c:otherwise>
                      </c:choose>

                      <c:forEach var="page" begin="1" end="${tot-(section-1)*5}" step="1">
                        <c:if test="${not doneLoop}">
                          <c:if test="${section>1 && page==1 }">
                            <a href="${contextPath}/userMypage/myReview.do?section=${section-1}&pageNum=${pageNum}">
                              prev</a>
                          </c:if>
                          <c:choose>
                            <c:when test="${page==pageNum}">
                              <a class="selPage"
                                href="${contextPath}/userMypage/myReview.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                            </c:when>
                            <c:otherwise>
                              <a class="noLine"
                                href="${contextPath}/userMypage/myReview.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                            </c:otherwise>
                          </c:choose>
                          <c:if test="${page==5}">
                            <a href="${contextPath}/userMypage/myReview.do?section=${section+1}&pageNum=${pageNum}">
                              next</a>
                            <c:set var="doneLoop" value="true" />
                          </c:if>
                        </c:if>
                      </c:forEach>
                    </c:when>

                    <c:when test="${totReview <= 50 }">
                      <c:choose>
                        <c:when test="${totReview%8==0}">
                          <c:set var="tot" value="${totReview/8}" />
                        </c:when>
                        <c:otherwise>
                          <c:set var="tot" value="${totReview/8+1}" />
                        </c:otherwise>
                      </c:choose>

                      <c:forEach var="page" begin="1" end="${tot}" step="1">
                        <c:choose>
                          <c:when test="${page==pageNum}">
                            <a class="selPage"
                              href="${contextPath}/userMypage/myReview.do?section=${section}&pageNum=${page}">${page}</a>
                          </c:when>
                          <c:otherwise>
                            <a class="noLine"
                              href="${contextPath}/userMypage/myReview.do?section=${section}&pageNum=${page}">${page}</a>
                          </c:otherwise>
                        </c:choose>
                      </c:forEach>
                    </c:when>
                  </c:choose>
                </c:if>


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