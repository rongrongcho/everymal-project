<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- 프로젝트 이름이 각각 다르므로 어느 프로젝트에 가져다 놓아도 사용할 수 있게 컨택스트 패스를 변수로 가져온다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <%-- <c:set var="rmList" value="${adminMap.rmList }" />
    <c:set var="totQ" value="${adminMap.totalApps }" />
    <c:set var="section" value="${rmList.section }" />
    <c:set var="pageNum" value="${rmList.pageNum }" /> --%>
    <% request.setCharacterEncoding("utf-8"); %>
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <title>관리자 > 병원 등록관리 </title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/common.css" />
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/hospitalRegi.css" />
        <script src="${contextPath }/resources/js/jquery-3.6.3.min.js"></script>

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
                  <select name="orderby" id="orderby">
                    <option value="rmTotList" selected>전체</option>
                    <option value="uncheckedRM">미확인</option>
                    <option value="checkedRM">확인</option>
                    <option value="confirmed">완료</option>
                  </select>
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
                    <tbody id="hosRMListTableBody">
                      <c:choose>
                        <c:when test="${empty rmList}">
                          <td colspan="5">내역이 존재하지 않습니다. </td>
                        </c:when>

                        <c:when test="${!empty rmList}">
                          <c:forEach var="rm" items="${rmList}">
                            <tr class="conLink">
                              <td><input type="checkbox" name="rm_chk" id="" value="${rm.hos_code}"></td>
                              <td>
                                <a class="goTodetailPage"
                                  href="${contextPath}/administrator/adminApplication.do?&hos_code=${rm.hos_code}">${rm.rm_code}</a>
                              </td>
                              <td>
                                <a class="goTodetailPage"
                                  href="${contextPath}/administrator/adminApplication.do?&hos_code=${rm.hos_code}">${rm.hos_name}</a>
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
                <c:if test="${totalApps !=0}">
                  <c:choose>
                    <c:when test="${totalApps > 40}">
                      <c:choose>
                        <c:when test="${totalApps%8==0}">
                          <c:set var="tot" value="${totalApps/8}" />
                        </c:when>
                        <c:otherwise>
                          <c:set var="tot" value="${totalApps/8+1}" />
                        </c:otherwise>
                      </c:choose>

                      <c:forEach var="page" begin="1" end="${tot-(section-1)*5}" step="1">
                        <c:if test="${not doneLoop}">
                          <c:if test="${section>1 && page==1 }">
                            <a
                              href="${contextPath}/administrator/hopitalRegi.do?section=${section-1}&pageNum=${pageNum}">
                              prev</a>
                          </c:if>
                          <c:choose>
                            <c:when test="${page==pageNum}">
                              <a class="selPage"
                                href="${contextPath}/administrator/hopitalRegi.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                            </c:when>
                            <c:otherwise>
                              <a class="noLine"
                                href="${contextPath}/administrator/hopitalRegi.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                            </c:otherwise>
                          </c:choose>
                          <c:if test="${page==5}">
                            <a
                              href="${contextPath}/administrator/hopitalRegi.do?section=${section+1}&pageNum=${pageNum}">
                              next</a>
                            <c:set var="doneLoop" value="true" />
                          </c:if>
                        </c:if>
                      </c:forEach>
                    </c:when>

                    <c:when test="${totalApps <= 40 }">
                      <c:choose>
                        <c:when test="${totalApps%8==0}">
                          <c:set var="tot" value="${totalApps/8}" />
                        </c:when>
                        <c:otherwise>
                          <c:set var="tot" value="${totalApps/8+1}" />
                        </c:otherwise>
                      </c:choose>

                      <c:forEach var="page" begin="1" end="${tot}" step="1">
                        <c:choose>
                          <c:when test="${page==pageNum}">
                            <a class="selPage"
                              href="${contextPath}/administrator/hopitalRegi.do?section=${section}&pageNum=${page}">${page}</a>
                          </c:when>
                          <c:otherwise>
                            <a class="noLine"
                              href="${contextPath}/administrator/hopitalRegi.do?section=${section}&pageNum=${page}">${page}</a>
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
              url: contextPath + "/administrator/hopitalRegi.do", //절대 경로 삽입
              data: { orderby: selectedValue }, // data는 내가 다른 것은 서버로 보내지 않고 select 태그에서 선택된 옵션의 값만 서버로 보낼거야! ex) 옶ㄴ gList가 선택되었다면
              // orderby 는 value값으로 서버로 전송 됨. 
              success: function (data) { //data를 서버로 전송시키는데 성공했다면 (중요) 
                // 받아온 데이터로 테이블의 tbody 내용 변경
                var tbody = $(data).find("#hosRMListTableBody"); //data는 서버로부터 리턴받은 값이 들어있음. 그래서 data에 들어있는 값을 저장할 영역의 태그를 find로 찾는다. 
                //find(바꾸고 싶은 부분 찾기 , ex) html 속 #hosRMListTableBody 를 가진 태그를 찾는다.) 
                $("#hosRMListTableBody").html(tbody.html());
              },
              error: function (error) {
                alert("데이터를 가져오는데 실패했습니다.");
                console.log("error: " + error);
              }
            });
          });
        });

        function toList(obj) {
          obj.action = "${contextPath}/administrator/approvalHosM.do";
          obj.submit();
        }
      </script>
      <!-- <script type="text/javascript">

      </script> -->


      </html>