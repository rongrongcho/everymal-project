<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <c:set var="hosList" value="${hosMap.hosList }" />
    <c:set var="totalHos" value="${hosMap.totalHos }" />
    <c:set var="section" value="${hosMap.section }" />
    <c:set var="pageNum" value="${hosMap.pageNum }" />
    <c:set var="filterchk" value="${hosMap.filterchk }" />
    <% request.setCharacterEncoding("utf-8"); %>
      <!DOCTYPE html>
      <html lang="ko">

      <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>병원 찾기</title>

        <style type="text/css">

        </style>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript">
          function init() {
            if (${ filterchk.hos_dog eq '1' }) {
              $("#hos_dog").prop("checked", true);
            }
            if (${ filterchk.hos_cat eq '1' }) {
              $("#hos_cat").prop("checked", true);
            }
            if (${ filterchk.hos_small eq '1' }) {
              $("#hos_small").prop("checked", true);
            }
            if (${ filterchk.hos_fish eq '1' }) {
              $("#hos_fish").prop("checked", true);
            }
            if (${ filterchk.hos_bird eq '1' }) {
              $("#hos_bird").prop("checked", true);
            }
            if (${ filterchk.hos_rep eq '1' }) {
              $("#hos_rep").prop("checked", true);
            }
            if (${ filterchk.hos_etc eq '1' }) {
              $("#hos_etc").prop("checked", true);
            }
            if (${ filterchk.hos_gs eq '1' }) {
              $("#hos_gs").prop("checked", true);
            }
            if (${ filterchk.hos_im eq '1' }) {
              $("#hos_im").prop("checked", true);
            }
            if (${ filterchk.hos_em eq '1' }) {
              $("#hos_em").prop("checked", true);
            }
            if (${ filterchk.hos_rm eq '1' }) {
              $("#hos_rm").prop("checked", true);
            }
            if (${ filterchk.hos_vac eq '1' }) {
              $("#hos_vac").prop("checked", true);
            }
            if (${ filterchk.hos_24 eq '1' }) {
              $("#hos_24").prop("checked", true);
            }
            if (${ filterchk.hos_mon eq 'mon' }) {
              $("#hos_mon").prop("checked", true);
            }
            if (${ filterchk.hos_tue eq 'tue' }) {
              $("#hos_tue").prop("checked", true);
            }
            if (${ filterchk.hos_wed eq 'wed' }) {
              $("#hos_wed").prop("checked", true);
            }
            if (${ filterchk.hos_thu eq 'thu' }) {
              $("#hos_thu").prop("checked", true);
            }
            if (${ filterchk.hos_fri eq 'fri' }) {
              $("#hos_fri").prop("checked", true);
            }
            if (${ filterchk.hos_sat eq 'sat' }) {
              $("#hos_sat").prop("checked", true);
            }
            if (${ filterchk.hos_sun eq 'sun' }) {
              $("#hos_sun").prop("checked", true);
            }
            if (${ hosMap.orderby eq 'true' }) {
              $("#orderby").val("true").prop("selected", true);
            }
            if (${ filterchk.hos_sch_name != null }) {
              $("#hos_sch_name").attr('value', '${filterchk.hos_sch_name}');
            }
          }

          function fil_paging(obj, section, page) {
            obj.action = "${contextPath}/hos_List/hos_filter.do?section=" + section + "&pageNum=" + page;
            obj.submit();
          }
        </script>
        <link rel="stylesheet" href="${contextPath }/resources/css/reset.css" />
        <link rel="stylesheet" href="${contextPath }/resources/css/common.css" />
        <link rel="stylesheet" href="${contextPath }/resources/css/hoslist.css" />
      </head>

      <body onload="init();">
        <div id="container">
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
          <!-- 페이지 타이틀 -->
          <h2 id="hp_pageTitle">동물병원 찾기</h2>

          <section id="sidebar_Area">
            <div class="sidebars" id="hp_sidebar"></div>

            <!-- 보류 -->
          </section>


          <!-- ===============필터 시작 =============================== -->
          <section id="filter_Area">
            <h3 class="hidden">동물병원 맞춤 필터 선택하기</h3>

            <form id="filterForm" name="filterForm" action="${contextPath}/hos_List/hos_filter.do" method="post"
              class="overform">
              <fieldset>
                <div class="category AnimalChoice">
                  <span class="categoryName">동물</span>
                  <div class="filterbox">
                    <legend class="hidden">동물 분류</legend>
                    <input type="checkbox" name="hos_dog" id="hos_dog" value="1" /><span>개</span>
                    <input type="checkbox" name="hos_cat" id="hos_cat" value="1" /><span>고양이</span>
                    <input type="checkbox" name="hos_small" id="hos_small" value="1" /><span>소동물</span>
                    <input type="checkbox" name="hos_fish" id="hos_fish" value="1" /><span>어류</span>
                    <input type="checkbox" name="hos_bird" id="hos_bird" value="1" /><span>조류</span>
                    <input type="checkbox" name="hos_rep" id="hos_rep" value="1" /><span>파충류</span>
                    <input type="checkbox" name="hos_etc" id="hos_etc" value="1" /><span>기타</span>
                  </div>
                </div>
                <div class="category medicalDepartment">
                  <span class="categoryName">진료과목</span>
                  <div class="filterbox">
                    <legend class="hidden">진료 과목</legend>

                    <input type="checkbox" name="hos_gs" id="hos_gs" value="1" /><span>외과</span>


                    <input type="checkbox" name="hos_im" id="hos_im" value="1" /><span>내과</span>


                    <input type="checkbox" name="hos_em" id="hos_em" value="1" /><span>응급의학과</span>


                    <input type="checkbox" name="hos_rm" id="hos_rm" value="1" /><span>재활의학과</span>


                    <input type="checkbox" name="hos_vac" id="hos_vac" value="1" /><span>예방접종</span>

                  </div>
                </div>
                <div class="category operatingTime">
                  <span class="categoryName">진료 시간</span>
                  <div class="filterbox">
                    <legend class="hidden">진료 시간</legend>
                    <input type="checkbox" name="hos_24" id="hos_24" value="1" /><span>24시간</span>

                    <input type="checkbox" name="hos_mon" id="hos_mon" value="mon" /><span>월</span>
                    <input type="checkbox" name="hos_tue" id="hos_tue" value="tue" /><span>화</span>
                    <input type="checkbox" name="hos_wed" id="hos_wed" value="wed" /><span>수</span>
                    <input type="checkbox" name="hos_thu" id="hos_thu" value="thu" /><span>목</span>
                    <input type="checkbox" name="hos_fri" id="hos_fri" value="fri" /><span>금</span>
                    <input type="checkbox" name="hos_sat" id="hos_sat" value="sat" /><span>토</span>
                    <input type="checkbox" name="hos_sun" id="hos_sun" value="sun" /><span>일</span>
                  </div>
                </div>
                <select name="orderby" id="orderby">
                  <option value="flase" label="일반정렬">일반정렬</option>
                  <option value="true" label="리뷰순 정렬">리뷰순 정렬</option>
                </select>
                <input type="text" name="hos_sch_name" id="hos_sch_name" placeholder="병원명 검색" value=""
                  style="margin-left:30%;">
                <input type="submit" id="submitBtn" value="필터적용" />
              </fieldset>
            </form>
          </section>
          <!-- ===============필터 끝 ===================================-->
          <!-- =======================병원 목록 시작============================ -->

          <section id="hospitalList_Area">
            <h3 class="hidden">동물병원 리스트</h3>
            <div class="hp_container">
              <ul id="hp_list">
                <c:choose>
                  <c:when test="${empty hosList}">
                    <li>조건에 맞는 병원이 없습니다.</li>
                  </c:when>
                </c:choose>
                <c:choose>
                  <c:when test="${!empty hosList}">
                    <c:forEach var="hos" items="${hosList}">

                      <li class="hp_con">
                        <a href="${contextPath}/hos_List/Hospital_detail.do?hos_code=${hos.hos_code}" class="">
                          <img src="${contextPath}/hosProImgDown.do?hos_thum=${hos.hos_thum}&hos_code=${hos.hos_code}"
                            alt="00병원 이미지" class="hp_Thumbnail" />
                          <div class="hp_conInfo">
                            <p class="hp_titles">${hos.hos_name}</p>
                            <span class="hp_tellNumber">전화 | 0${hos.hos_tel}</span>
                            <span class="hp_addressInfo">주소 | ${hos.hos_addr1}, ${hos.hos_addr2}</span>
                            <span class="hp_seeMore">자세히 보기</span>
                          </div>
                        </a>
                      </li>
                    </c:forEach>
                  </c:when>
                </c:choose>
              </ul>

              <div id="paging" align="center">
                <c:if test="${totalHos !=0}">
                  <c:choose>
                    <c:when test="${totalHos > 0}">
                      <c:choose>
                        <c:when test="${totalHos%12==0}">
                          <c:set var="tot" value="${totalHos/12}" />
                        </c:when>
                        <c:otherwise>
                          <c:set var="tot" value="${totalHos/12+1}" />
                        </c:otherwise>
                      </c:choose>
                      <c:set var="lastPage" value="${(section-1)*5 + 5}" />
                      <c:if test="${lastPage > tot}">
                        <c:set var="lastPage" value="${tot}" />
                      </c:if>
                      <c:if test="${section > 1}">
                        <c:set var="num" value="1" />
                        <a class="ar1" href="#" onclick="fil_paging(filterForm, ${section-1}, ${num});">이전</a>
                      </c:if>
                      <c:forEach var="page" begin="${(section-1)*5 + 1}" end="${lastPage}" step="1">
                        <c:choose>
                          <c:when test="${section==1}">
                            <a class="selPage" href="#"
                              onclick="fil_paging(filterForm, ${section}, ${page});">${page}</a>
                          </c:when>
                          <c:otherwise>
                            <c:set var="testpage" value="${page%5}" />
                            <c:if test="${testpage==0}">
                              <c:set var="testpage" value="5" />
                            </c:if>
                            <a class="noLine" href="#"
                              onclick="fil_paging(filterForm, ${section}, ${testpage});">${page}</a>
                          </c:otherwise>
                        </c:choose>
                      </c:forEach>

                      <c:if test="${lastPage < (tot-1)}">
                        <c:set var="num" value="1" />
                        <a class="ar2" href="#" onclick="fil_paging(filterForm, ${section+1}, ${num});">다음</a>
                      </c:if>
                    </c:when>
                  </c:choose>
                </c:if>
              </div>



          </section>
          <!-- =======================병원 목록 끝============================ -->

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