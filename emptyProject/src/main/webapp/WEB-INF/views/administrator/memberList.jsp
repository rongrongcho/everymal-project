<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- core라이브러리를 c라는 접두사를 따와서 쓰겠다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <%-- <c:set var="totUser" value="${resultMap.totalList}" />
    <c:set var="section" value="${userList.section }" />
    <c:set var="pageNum" value="${userList.pageNum }" /> --%>

    <!-- ajax로 정보 요청시 html 바깥에서 taglib 사용하면 error  html안에서 사용하는 방법은 아래에 기술되어있음 -->


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
        <link rel="stylesheet" href="${contextPath}/resources/css/userList.css" />
        <script src="${contextPath}/resources/js/jquery-3.6.3.min.js"></script>

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
          </header>>
          <h2 class="pagetitlte">관리자 페이지</h2>
          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
            </div>
          </section>
          <section id="tab_Area">
            <div class="tab_nav">
              <ul class="tab_Btns">
                <li class="tBtn tBtn1 choiceTabNav"><a
                    href="${contextPath }/hosMypageInfo/myInfo.do"><span>회원관리</span></a></li>
                <li class="tBtn tBtn2 notChoice"><a
                    href="${contextPath }/hosMypageInfo/myHosInfo.do"><span>병원등록요청</span></a></li>
                <li class="tBtn tBtn3 notChoice"><a
                    href="${contextPath }/hosReview/hosReviewList.do"><span>리뷰관리</span></a></li>
                </li>


              </ul>
            </div>
            <div class="tabcontents">
              <div id="tabBox" class="tab-content">
                <form id="memberlistForm">
                  <select name="orderby" id="orderby">
                    <option value="list" selected>전체회원</option>
                    <option value="glist">일반회원</option>
                    <option value="hlist">병원회원</option>
                    <option value="elist">탈퇴회원</option>
                    <option value="alist">활동회원</option>
                  </select>
                  <p class="total_review total_p">회원 수: <span class="gatsoo">${totalList}</span class="ga">명</p>
                  <div class="tableBox">
                    <table style="height: 100%;">
                      <colgroup>
                        <col width="20%" />
                        <col width="20%" />
                        <col width="20%" />
                        <col width="20%" />
                        <col width="20%" />

                      </colgroup>
                      <thead>
                        <tr class="member-row">
                          <th>회원코드</th>
                          <th>회원아이디</th>
                          <th>회원종류</th>
                          <th>가입일</th>
                          <th>탈퇴여부</th>
                        </tr>
                      </thead>
                      <tbody id="userListTableBody">
                        <c:choose>
                          <c:when test="${empty userList}">
                            <tr>
                              <td colspan="5" align="center">등록된 회원이 존재하지 않습니다.</td>
                            </tr>
                          </c:when>

                          <c:when test="${!empty userList}">
                            <c:forEach var="user" items="${userList}">
                              <tr>
                                <td>${user.user_code}</td>
                                <td>${user.user_id}</td>
                                <td>
                                  <c:choose>
                                    <c:when test="${user.user_code.startsWith('hos')}">
                                      병원회원
                                    </c:when>
                                    <c:otherwise>
                                      일반회원
                                    </c:otherwise>
                                  </c:choose>
                                </td>
                                <td>${user.j_date}</td>
                                <td>
                                  <c:choose>
                                    <c:when test=" ${user.user_enabled} == 0">
                                      Y
                                    </c:when>
                                    <c:otherwise>
                                      N
                                    </c:otherwise>
                                  </c:choose>
                                </td>
                              </tr>
                            </c:forEach>
                          </c:when>
                        </c:choose>
                      </tbody>
                    </table>
                  </div>


                </form>
                <!-- -페이징!! -->
                <div id="divPaging">
                  <c:if test="${resultMap.totalList !=0}">
                    <c:choose>
                      <c:when test="${resultMap.totalList > 50}">
                        <c:choose>
                          <c:when test="${resultMap.totalList%5==0}">
                            <c:set var="tot" value="${resultMap.totalList/6}" />
                          </c:when>
                          <c:otherwise>
                            <c:set var="tot" value="${resultMap.totalList/6+1}" />
                          </c:otherwise>
                        </c:choose>


                        <c:forEach var="page" begin="1" end="${tot-(section-1)*5}" step="1">

                          <c:if test="${not doneLoop}">
                            <c:if test="${section>1 && page==1 }">
                              <a
                                href="${contextPath}/memberlist/list.do?section=${section-1}&pageNum=${userList.pageNum }">
                                prev</a>
                            </c:if>
                            <c:choose>

                              <c:when test="${page==pageNum}">
                                <a class="selPage"
                                  href="${contextPath}/memberlist/list.do?section=${userList.section}&pageNum=${page}">${(section-1)*5+page}</a>
                              </c:when>
                              <c:otherwise>
                                <a class="noLine"
                                  href="${contextPath}/memberlist/list.do?section=${userList.section}&pageNum=${page}">${(section-1)*5+page}</a>
                              </c:otherwise>
                            </c:choose>
                            <c:if test="${page==5}">
                              <a
                                href="${contextPath}/memberlist/list.do?section=${section+1}&pageNum=${userList.pageNum }">
                                next</a>
                              <c:set var="doneLoop" value="true" />
                            </c:if>
                          </c:if>
                        </c:forEach>
                      </c:when>


                      <c:when test="${resultMap.totalList <= 50 }">
                        <c:choose>
                          <c:when test="${resultMap.totalList%6==0}">
                            <c:set var="tot" value="${resultMap.totalList/6}" />
                          </c:when>
                          <c:otherwise>
                            <c:set var="tot" value="${resultMap.totalList/6+1}" />
                          </c:otherwise>
                        </c:choose>


                        <c:forEach var="page" begin="1" end="${tot}" step="1">
                          <c:choose>
                            <c:when test="${page==pageNum}">
                              <a class="selPage"
                                href="${contextPath}/memberlist/list.do?section=${userList.section}&pageNum=${page}">${page}</a>
                            </c:when>
                            <c:otherwise>
                              <a class="noLine"
                                href="${contextPath}/memberlist/list.do?section=${userList.section}&pageNum=${page}">${page}</a>
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
                <a id="footerLogo" href="${contextPath}/index.jsp"><img
                    src="${contextPath}/resources/img/EverymalLogo_w.svg" alt="로고"
                    style="width: 250px; height: auto" /></a>
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


        $(document).ready(function () {
          // select 요소 값 변경 시 이벤트 처리
          $("#orderby").change(function () { // select 태그의 id 값을 가져옴 , 사용자가 샐렉트 값을 임의로 바꾸면 ajax 시작 
            var contextPath = "${pageContext.request.contextPath}";
            var selectedValue = $(this).val(); // select 태그의 옵션(gList~hList~등등) 선택된 값 가져오기
            console.log(selectedValue); // console log 확인
            $.ajax({
              type: "GET",
              url: contextPath + "/administrator/memberList.do", //절대 경로 삽입
              data: { orderby: selectedValue }, // data는 내가 다른 것은 서버로 보내지 않고 select 태그에서 선택된 옵션의 값만 서버로 보낼거야! ex) 옶ㄴ gList가 선택되었다면
              // orderby 는 gList로 서버로 전송 됨. 
              success: function (data) { //data를 서버로 전송시키는데 성공했다면 (중요) 
                // 받아온 데이터로 테이블의 tbody 내용 변경
                var tbody = $(data).find("#userListTableBody"); //data는 서버로부터 리턴받은 값이 들어있음. 그래서 data에 들어있는 값을 저장할 영역의 태그를 find로 찾는다. 
                //find(바꾸고 싶은 부분 찾기 , ex) html 속 #userListTableBody 를 가진 태그를 찾는다.) 
                $("#userListTableBody").html(tbody.html());
              },
              error: function (error) {
                alert("데이터를 가져오는데 실패했습니다.");
                console.log("error: " + error);
              }
            });
          });
        });
      </script>

      </html>