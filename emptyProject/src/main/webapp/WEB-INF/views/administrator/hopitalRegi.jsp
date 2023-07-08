<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- 프로젝트 이름이 각각 다르므로 어느 프로젝트에 가져다 놓아도 사용할 수 있게 컨택스트 패스를 변수로 가져온다 -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <%-- <c:set var="totUser" value="${rmResultMap.totalList}" />
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
                    <option value="confirmed">완료</opt /ion>
                  </select>
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
                          <th>선택</th>
                          <th>신청 번호</th>
                          <th>병원명</th>
                          <th>신청일</th>
                          <th>상태</th>
                        </tr>
                      </thead>
                      <tbody id="userListTableBody">

                      </tbody>

                    </table>

                  </div>
                  <button class="edit_btn" type="button" id="edit_btn3" onclick="toList(this.form);">등록 승인</button>
                </form>
                <div id="divPaging">
                  <!-- 페이징 링크가 여기에 동적으로 생성됩니다. -->
                </div>

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

        $("#orderby").change(function () {
          var contextPath = "${pageContext.request.contextPath}";
          var selectedValue = $(this).val(); // 선택된 값 가져오기
          console.log(selectedValue);
          $.ajax({
            type: "GET",
            url: contextPath + "/administrator/hos_pitalRegi.do",
            dataType: "json",
            data: { orderby: selectedValue },
            success: function (data) {
              updateList(data);
              updatePaging(data);

            },
            error: function (error) {
              alert("데이터를 가져오는데 실패했습니다.");
              console.log("error: " + error);
            }
          });
        });
        //처음에는 자동으로 전체회원이 나오게 하기 위해서 (이거를 해야, 처음에 전체회원이 나온다.)
        $("#orderby").val("list").change();

        function updateList(data) {
          var userList = data.userList; // 업데이트할 부분에 해당하는 데이터 추출
          var count = data.totalList;
          $("#count").text(count);

          var tbody = ""; // 업데이트할 tbody를 담을 변수

          if (userList.length === 0) {
            // userList가 비어있을 경우 메시지를 표시
            tbody = "<tr><td colspan='5' align='center'>신청 이력이 존재하지 않습니다.</td></tr>";
          } else {
            // userList를 순회하며 테이블의 각 행을 생성하여 tbody에 추가
            var contextPath = "<%= request.getContextPath() %>";
            for (var i = 0; i < userList.length; i++) {

              var rm = userList[i];
              tbody += "<tr>";
              tbody += "<td><input type='checkbox' name='rm_chk' id='' value='" + rm.hos_code + "'></td>";
              tbody += "<td><a href='" + contextPath + "/administrator/adminApplication.do?hos_code=" + rm.hos_code + "&rm_code=" + rm.rm_code + "'>" + rm.rm_code + "</a></td>";
              tbody += "<td><a href='" + contextPath + "/administrator/adminApplication.do?hos_code=" + rm.hos_code + "&rm_code=" + rm.rm_code + "'>" + rm.hos_name + "</a></td>";
              tbody += "<td><a href='" + contextPath + "/administrator/adminApplication.do?hos_code=" + rm.hos_code + "&rm_code=" + rm.rm_code + "'>" + rm.rm_date + "</a></td>";

              tbody += "<td>" + rm.rm_status + "</td>";
              tbody += "</tr>";
            }
          }

          // 업데이트된 tbody로 기존의 tbody를 교체하여 업데이트
          $("#userListTableBody").html(tbody);
        }


        function updatePaging(data) {
          var currentPage = data.currentPage;
          var totalPages = data.totalPages;
          var totalList = data.totalList;

          var pagingInfo = "전체 " + totalList + "개 중 " + currentPage + " 페이지";
          $("#pagingInfo").text(pagingInfo);
          console.log(pagingInfo);

          var pagingHtml = ""; // 페이징 링크를 담을 변수

          // 이전 페이지 링크 생성
          if (currentPage > 1) {
            pagingHtml += "<a href='#' onclick='goToPage(" + (currentPage - 1) + ")'>&lt; 이전</a> ";
          }

          // 페이지 번호 링크 생성
          var startPage = Math.max(1, currentPage - 2); // 현재 페이지를 중심으로 앞쪽에 2개 페이지 번호 출력
          var endPage = Math.min(totalPages, startPage + 4); // 시작 페이지로부터 최대 5개의 페이지 번호 출력

          if (startPage > 1) {
            pagingHtml += "<a href='#' onclick='goToPage(1)'>1</a> "; // 첫 번째 페이지로 이동하는 링크
            if (startPage > 2) {
              pagingHtml += "... "; // 중간 페이지 생략 부분 표시
            }
          }

          for (var i = startPage; i <= endPage; i++) {
            if (i === currentPage) {
              pagingHtml += "<span style='color: #9e9e9e;'>" + i + "</span> ";
            } else {
              pagingHtml += "<a href='#' onclick='goToPage(" + i + ")'>" + i + "</a> ";
            }
          }

          if (endPage < totalPages) {
            if (endPage < totalPages - 1) {
              pagingHtml += "... "; // 중간 페이지 생략 부분 표시
            }
            pagingHtml += "<a href='#' onclick='goToPage(" + totalPages + ")'>" + totalPages + "</a> "; // 마지막 페이지로 이동하는 링크
          }

          // 다음 페이지 링크 생성
          if (currentPage < totalPages) {
            pagingHtml += "<a href='#' onclick='goToPage(" + (currentPage + 1) + ")'>다음 &gt;</a> ";
          }

          // 생성된 페이징 HTML을 페이징 영역에 업데이트
          $("#divPaging").html(pagingHtml);
        };


        // 페이지 이동 함수
        function goToPage(page) {
          var contextPath = "${pageContext.request.contextPath}";
          var selectedValue = $("#orderby").val();
          console.log(selectedValue);
          $.ajax({
            type: "GET",
            url: contextPath + "/administrator/hos_pitalRegi.do",
            dataType: "json",
            data: { orderby: selectedValue, pageNum: page },
            success: function (data) {
              updateList(data);
              updatePaging(data);
            },
            error: function (error) {
              alert("데이터를 가져오는데 실패했습니다.");
              console.log("error: " + error);
            }
          });
        };

        function toList(obj) {
          obj.action = "${contextPath}/administrator/approvalHosM.do";
          obj.submit();
        }
      </script>



      </html>