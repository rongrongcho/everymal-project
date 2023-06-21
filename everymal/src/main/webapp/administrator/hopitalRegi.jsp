<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- 프로젝트 이름이 각각 다르므로 어느 프로젝트에 가져다 놓아도 사용할 수 있게 컨택스트 패스를 변수로 가져온다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <c:set var="rmList" value="${adminMap.appList }" />
    <c:set var="totQ" value="${adminMap.totalApps }" />
    <c:set var="section" value="${adminMap.section }" />
    <c:set var="pageNum" value="${adminMap.pageNum }" />
    <% request.setCharacterEncoding("utf-8"); %>
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <title>병원 등록관리 </title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/hospitalRegi.css" />
        <script src="${contextPath }/js/jquery-3.6.3.min.js"></script>
        <script type="text/javascript">
          //질문 삭제
          function fn_remove_article(url, q_code) {
            if (${ article.user_id == log_id }) {
              let d_form = document.createElement("form");      //createElement=>태그를 만들어준다
              d_form.setAttribute("method", "post");
              d_form.setAttribute("action", url); //매개변수로 받은 주소로 지정
              //폼 안에 필요한 태그요소들 만들기
              let articleNoInput = document.createElement("input");
              articleNoInput.setAttribute("type", "hidden");
              articleNoInput.setAttribute("name", "q_code");
              articleNoInput.setAttribute("value", q_code);
              d_form.appendChild(articleNoInput); //만든 태그를 폼 안에 넣어준다
              document.body.appendChild(d_form);//만든 폼을 바디안에 넣어준다
              d_form.submit();
            }else {
              alert("자신이 쓴 글만 삭제할 수 있습니다!");
            }
          }

          //답변 삭제
          function fn_appHos(url, q_code) {
            if (${ article.hos_id == log_id }) {
              let d_form = document.createElement("form");      //createElement=>태그를 만들어준다
              d_form.setAttribute("method", "post");
              d_form.setAttribute("action", url); //매개변수로 받은 주소로 지정
              //폼 안에 필요한 태그요소들 만들기
              let articleNoInput = document.createElement("input");
              articleNoInput.setAttribute("type", "hidden");
              articleNoInput.setAttribute("name", "q_code");
              articleNoInput.setAttribute("value", q_code);
              d_form.appendChild(articleNoInput); //만든 태그를 폼 안에 넣어준다
              document.body.appendChild(d_form);//만든 폼을 바디안에 넣어준다
              d_form.submit();
            }else {
              alert("자신이 쓴 글만 삭제할 수 있습니다!");
            }
          }

          function toList(obj) {
            obj.action = "${contextPath}/admin/approvalHosM.do";
            obj.submit();
          }
        </script>
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
          <h2 class="pagetitlte">병원 등록 관리</h2>
          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
            </div>
          </section>

          <section id="left_Area">

          </section>
          <section id="tab_Area">
            <div class="tab_nav">
              <ul class="tab_Btns">
                <li class="tBtn tBtn1 notChoice">
                  <a href="#"><span class="pointT">회원</span><span>관리</span></a>
                </li>
                <li class="tBtn tBtn2 choiceTabNav">
                  <a href="#"><span class="pointT">등록</span><span>관리</span></a>
                </li>
                <li class="tBtn tBtn3 notChoice">
                  <a href="#"><span class="pointT">리뷰</span><span>관리</span></a>
                </li>

              </ul>
            </div>
            <div class="tabcontents">
              <!-- <p class="total_review total_p">등록한 질문 수 <span class="gatsoo">${totQ}</span class="ga">개</p> -->

              <div id="tabBox" class="tab-content">

                <form action="${contextPath}/userMypage/myQusDel.do" method="post" name="del">
                  <table>
                    <thead>
                      <tr>
                        <th>선택</th>
                        <th>신청 번호</th>
                        <th>병원명</th>
                        <th>신청일</th>
                        <th>상태</th>
                      </tr>
                    </thead>
                    <tbody>

                      <c:choose>
                        <c:when test="${empty rmList}">
                          <td colspan="5">등록 신청한 병원이 없습니다.</td>
                        </c:when>
                        <c:when test="${not empty rmList}">
                          <c:forEach var="rm" items="${ rmList }">
                            <tr class="conLink">

                              <td><input type="checkbox" name="rm_chk" id="" value="${rm.hos_code}"></td>
                              <td>
                                <a class="goTodetailPage"
                                  href="${contextPath}/app/checkApp.do?&hos_code=${rm.hos_code}">${rm.rm_code}</a>
                              </td>
                              <td>
                                <a class="goTodetailPage"
                                  href="${contextPath}/app/checkApp.do?&hos_code=${rm.hos_code}">${rm.hos_name}</a>
                              </td>
                              <td>${rm.rm_date}</td>
                              <td>${rm.rm_status}</td>

                            </tr>
                          </c:forEach>
                        </c:when>
                      </c:choose>


                    </tbody>

                  </table>
                  <button class="edit_btn" type="button" id="edit_btn3" onclick="toList(this.form);">등록 승인</button>
                </form>
              </div>
              <div id="divPaging">
                <c:if test="${totQ !=0}">
                  <c:choose>
                    <c:when test="${totQ > 40}">
                      <c:choose>
                        <c:when test="${totQ%8==0}">
                          <c:set var="tot" value="${totQ/8}" />
                        </c:when>
                        <c:otherwise>
                          <c:set var="tot" value="${totQ/8+1}" />
                        </c:otherwise>
                      </c:choose>

                      <c:forEach var="page" begin="1" end="${tot-(section-1)*5}" step="1">
                        <c:if test="${not doneLoop}">
                          <c:if test="${section>1 && page==1 }">
                            <a href="${contextPath}/userMypage/myQuestion.do?section=${section-1}&pageNum=${pageNum}">
                              prev</a>
                          </c:if>
                          <c:choose>
                            <c:when test="${page==pageNum}">
                              <a class="selPage"
                                href="${contextPath}/userMypage/myQuestion.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                            </c:when>
                            <c:otherwise>
                              <a class="noLine"
                                href="${contextPath}/userMypage/myQuestion.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                            </c:otherwise>
                          </c:choose>
                          <c:if test="${page==5}">
                            <a href="${contextPath}/userMypage/myQuestion.do?section=${section+1}&pageNum=${pageNum}">
                              next</a>
                            <c:set var="doneLoop" value="true" />
                          </c:if>
                        </c:if>
                      </c:forEach>
                    </c:when>

                    <c:when test="${totQ <= 40 }">
                      <c:choose>
                        <c:when test="${totQ%8==0}">
                          <c:set var="tot" value="${totQ/8}" />
                        </c:when>
                        <c:otherwise>
                          <c:set var="tot" value="${totQ/8+1}" />
                        </c:otherwise>
                      </c:choose>

                      <c:forEach var="page" begin="1" end="${tot}" step="1">
                        <c:choose>
                          <c:when test="${page==pageNum}">
                            <a class="selPage"
                              href="${contextPath}/userMypage/myQuestion.do?section=${section}&pageNum=${page}">${page}</a>
                          </c:when>
                          <c:otherwise>
                            <a class="noLine"
                              href="${contextPath}/userMypage/myQuestion.do?section=${section}&pageNum=${page}">${page}</a>
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