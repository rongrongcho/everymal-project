<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- core라이브러리를 c라는 접두사를 따와서 쓰겠다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <c:set var="hosReqDelList" value="${reqDelhosReviewMap.hosReqDelList }" />  
     <c:set var="totReview" value="${reqDelhosReviewMap.totalhosReviewDelReq }" />
    <c:set var="section" value="${reqDelhosReviewMap.section }" />
    <c:set var="pageNum" value="${reqDelhosReviewMap.pageNum }" />


    <% request.setCharacterEncoding("utf-8"); %>

      <!DOCTYPE html>
      <html lang="ko">

      <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My page</title>
        <link rel="stylesheet" href="${contextPath}/resources/css/reset.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/adminReview.css?ver=1" />
        <script src="${contextPath}/resources/js/jquery-3.6.3.min.js"></script>
        <script>
          var newCell_2 = newRow.insertCell(2);
          newCell_2.width = 350;   // td size 지정
          newCell_2.style.wordBreak = "break-all";

        </script>
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
          </header>>
          <h2 class="pagetitlte">관리자 페이지</h2>
          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
            </div>
          </section>

          <section id="left_Area">
            
            


          </section>
          <div class="grey_line"></div>
          <section id="tab_Area">
            <div class="tab_nav">
                 <ul class="tab_Btns">
                    <li class="tBtn tBtn1 notChoice"><a href="${contextPath }/hosMypageInfo/myInfo.do"><span class="pointT">회원</span><span >관리</span></a></li>
                    <li class="tBtn tBtn2 notChoice"><a href="${contextPath }/hosMypageInfo/myHosInfo.do"><span class="pointT">등록</span><span >관리</span></a></li>
                    <li class="tBtn tBtn3 choiceTabNav" ><a href="${contextPath }/administrator/reqDelReviewList.do"><span class="pointT">리뷰</span><span> 관리</span></a></li>
               </ul>
            </div>
            <div class="tabcontents">
              <div id="tabBox" class="tab-content">
                
                <form action="${contextPath}/administrator/reviewDelete.do" method="post" id="reviewDelForm">
                  <div class="tableBox">
                    <table style="height: 100%;">
                      <colgroup>
                         <col width="20%"/>
                         <col width="20%"/>
                         <col width="20%"/>
                         <col width="20%"/>
                         <col width="20%"/>

                      </colgroup>
                      <thead>
                        <tr>
                          <th>선택</th>
                          <th>리뷰등록번호</th>
                          <th>병원코드</th>
                          <th>병원명</th>
                          <th>등록일</th>

                        </tr>
                      </thead>
                      <c:choose>
                        <c:when test="${empty hosReqDelList}">
                          <tr>
                            <td colspan="5" align="center">삭제 요청된 리뷰가 존재하지 않습니다.</td>
                          </tr>
                        </c:when>
                        <c:when test="${!empty hosReqDelList}">
                          <c:forEach var="reqDelreview" items="${hosReqDelList}">
                            <tr>
                              <td><input type="checkbox" name="del_chk" id="del_chk" value="${reqDelreview.rv_code}"></td>
                              <td><a class="res_code"
                                  href="${contextPath}/hos_ReviewInfo/hosviewReview.do?rv_code=${reqDelreview.rv_code}">
                                  ${reqDelreview.rv_code}</a></td>
                              <td>
                                ${reqDelreview.hos_code}
                              </td>
                              <td>
                                <div class="text-ellipsis"><a class="res_code"
                                    href="${contextPath}/hos_ReviewInfo/hosviewReview.do?rv_code=${reqDelreview.rv_code}">${reqDelreview.hos_name}</a>
                                </div>
                              </td>
                              <td>${reqDelreview.rv_date}</td>

                            </tr>
                          </c:forEach>
                        </c:when>
                      </c:choose>

                    </table>
                  </div>


                </form>
                <input type="submit" value="삭제하기" id="edit_btn3" class="edit_btn" form="reviewDelForm" />
                <!-- -페이징!! -->
                 <div id="divPaging">
                  <c:if test="${totReview !=0}">
                    <c:choose>
                      <c:when test="${totReview > 50}">
                        <c:choose>
                          <c:when test="${totReview%5==0}">
                            <c:set var="tot" value="${totReview/8}" />
                          </c:when>
                          <c:otherwise>
                            <c:set var="tot" value="${totReview/8+1}" />
                          </c:otherwise>
                        </c:choose>


                        <c:forEach var="page" begin="1" end="${tot-(section-1)*5}" step="1">

                          <c:if test="${not doneLoop}">
                            <c:if test="${section>1 && page==1 }">
                              <a
                                href="${contextPath}/administrator/reqDelReviewList.do?section=${section-1}&pageNum=${pageNum}">
                                prev</a>
                            </c:if>
                            <c:choose>

                              <c:when test="${page==pageNum}">
                                <a class="selPage"
                                  href="${contextPath}/administrator/reqDelReviewList.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                              </c:when>
                              <c:otherwise>
                                <a class="noLine"
                                  href="${contextPath}/administrator/reqDelReviewList.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                              </c:otherwise>
                            </c:choose>
                            <c:if test="${page==5}">
                              <a
                                href="${contextPath}/administrator/reqDelReviewList.do?section=${section+1}&pageNum=${pageNum}">
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
                                href="${contextPath}/administrator/reqDelReviewList.do?section=${section}&pageNum=${page}">${page}</a>
                            </c:when>
                            <c:otherwise>
                              <a class="noLine"
                                href="${contextPath}/administrator/reqDelReviewList.do?section=${section}&pageNum=${page}">${page}</a>
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
                <a id="footerLogo" href="${contextPath}/main.do"><img src="${contextPath}/resources/img/EverymalLogo_w.svg"
                    alt="로고" style="width: 250px; height: auto" /></a>
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