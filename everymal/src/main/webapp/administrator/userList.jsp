<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- core라이브러리를 c라는 접두사를 따와서 쓰겠다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <c:set var="userList" value="${memberlistMap.memberList }" />
    <c:set var="totUser" value="${memberlistMap.totalList}" />
    <c:set var="section" value="${memberlistMap.section }" />
    <c:set var="pageNum" value="${memberlistMap.pageNum }" />


    <% request.setCharacterEncoding("utf-8"); %>

      <!DOCTYPE html>
      <html lang="ko">

      <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My page</title>
        <link rel="stylesheet" href="${contextPath}/css/reset.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/userList.css" />
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
          </header>>
          <h2 class="pagetitlte">관리자 페이지</h2>
          <section id="sidebar_Area">
            <div class="sidebars" id="sb_sidebar">
            </div>
          </section>
          <section id="tab_Area">
            <div class="tab_nav">
              <ul class="tab_Btns">
                <li class="tBtn tBtn1 choiceTabNav"><a href="${contextPath }/hosMypageInfo/myInfo.do"><span>회원관리</span></a></li>
                <li class="tBtn tBtn2 notChoice"><a href="${contextPath }/hosMypageInfo/myHosInfo.do"><span>병원등록요청</span></a></li>
                <li class="tBtn tBtn3 notChoice"><a href="${contextPath }/hosReview/hosReviewList.do"><span>리뷰관리</span></a></li>
                </li>


              </ul>
            </div>
            <div class="tabcontents">
              <div id="tabBox" class="tab-content">
              <form id="memberlistForm">
              <select name="orderby" id="orderby" onchange="sortMembers()">
    			<option value="list">전체회원</option>
    			<option value="glist">일반회원</option>
    			<option value="hlist">병원회원</option>
    			<option value="elist">탈퇴회원</option>
   				<option value="alist">활동회원</option>
			  </select>
                <p class="total_review total_p">총 회원 수: <span class="gatsoo">${totUser}</span class="ga">명</p>  
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
                              <td>${user.code}</td>
                              <td>${user.id}</td>
                              <td>${user.genre}</td>
                              <td>${user.j_date}</td>
                              <td>${user.enabled}</td>
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
                  <c:if test="${totUser !=0}">
                    <c:choose>
                      <c:when test="${totUser > 50}">
                        <c:choose>
                          <c:when test="${totUser%5==0}">
                            <c:set var="tot" value="${totUser/6}" />
                          </c:when>
                          <c:otherwise>
                            <c:set var="tot" value="${totUser/6+1}" />
                          </c:otherwise>
                        </c:choose>


                        <c:forEach var="page" begin="1" end="${tot-(section-1)*5}" step="1">

                          <c:if test="${not doneLoop}">
                            <c:if test="${section>1 && page==1 }">
                              <a
                                href="${contextPath}/memberlist/list.do?section=${section-1}&pageNum=${pageNum}">
                                prev</a>
                            </c:if>
                            <c:choose>

                              <c:when test="${page==pageNum}">
                                <a class="selPage"
                                  href="${contextPath}/memberlist/list.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                              </c:when>
                              <c:otherwise>
                                <a class="noLine"
                                  href="${contextPath}/memberlist/list.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
                              </c:otherwise>
                            </c:choose>
                            <c:if test="${page==5}">
                              <a
                                href="${contextPath}/memberlist/list.do?section=${section+1}&pageNum=${pageNum}">
                                next</a>
                              <c:set var="doneLoop" value="true" />
                            </c:if>
                          </c:if>
                        </c:forEach>
                      </c:when>


                      <c:when test="${totUser <= 50 }">
                        <c:choose>
                          <c:when test="${totUser%6==0}">
                            <c:set var="tot" value="${totUser/6}" />
                          </c:when>
                          <c:otherwise>
                            <c:set var="tot" value="${totUser/6+1}" />
                          </c:otherwise>
                        </c:choose>


                        <c:forEach var="page" begin="1" end="${tot}" step="1">
                          <c:choose>
                            <c:when test="${page==pageNum}">
                              <a class="selPage"
                                href="${contextPath}/memberlist/list.do?section=${section}&pageNum=${page}">${page}</a>
                            </c:when>
                            <c:otherwise>
                              <a class="noLine"
                                href="${contextPath}/memberlist/list.do?section=${section}&pageNum=${page}">${page}</a>
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
      </body>
<script>
// 옵션 선택 시 목록 갱신
function sortMembers() {
  var selectedOption = document.getElementById("orderby").value;
  var data = $("#memberlistForm").serialize();
  $.ajax({
    type: "post",
    dataType: "json",
    url: "${contextPath}/memberlist/" + selectedOption + ".do",
    data: data,
    success: function (data) {
      updateList(data); // 목록을 갱신하는 함수 호출
    },
    error: function (xhr, status, error) {
      console.error("목록 갱신 중 오류 발생: " + error);
    }
  });
}
//목록을 갱신하는 함수
function updateList(data) {
  var userList = data.userList; // 응답 데이터에 userList가 포함되어 있다고 가정합니다.

  var tableBody = document.getElementById("userListTableBody");
  tableBody.innerHTML = ""; // 기존의 테이블 본문을 비웁니다.

  if (userList.length === 0) {
    // userList가 비어있을 경우 메시지를 표시합니다.
    var row = document.createElement("tr");
    var cell = document.createElement("td");
    cell.colSpan = "5";
    cell.align = "center";
    cell.textContent = "등록된 회원이 존재하지 않습니다.";
    row.appendChild(cell);
    tableBody.appendChild(row);
  } else {
    // userList를 순회하며 테이블에 행을 추가합니다.
    for (var i = 0; i < userList.length; i++) {
      var user = userList[i];
      var row = document.createElement("tr");

      var codeCell = document.createElement("td");
      codeCell.textContent = user.code;
      row.appendChild(codeCell);

      var idCell = document.createElement("td");
      idCell.textContent = user.id;
      row.appendChild(idCell);

      var genreCell = document.createElement("td");
      genreCell.textContent = user.genre;
      row.appendChild(genreCell);

      var jDateCell = document.createElement("td");
      jDateCell.textContent = user.j_date;
      row.appendChild(jDateCell);

      var enabledCell = document.createElement("td");
      enabledCell.textContent = user.enabled;
      row.appendChild(enabledCell);

      tableBody.appendChild(row);
    }
  }
}
</script>

      </html>