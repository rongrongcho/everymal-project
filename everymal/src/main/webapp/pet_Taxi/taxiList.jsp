<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"
  import="java.util.*" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <c:set var="contextPath" value="${pageContext.request.contextPath}" />
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <% request.setCharacterEncoding("utf-8"); %>
          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" type="text/css" href="${contextPath }/css/reset.css" />
            <link rel="stylesheet" type="text/css" href="${contextPath }/css/common.css" />
            <style>
              @charset "utf-8";

              body {
                width: 100%;
                height: auto;
              }

              table {
                position: relative;
                margin-left: 50%;
                transform: translateX(-50%);
                width: 80%;
              }

              caption {
                font-size: 20px;
                margin: 20px 0;
              }

              th {
                background-color: #e89714;
                color: #fff;
                margin: 20px;
                font-size: 16px;
                width: 25%;
                height: 30px;
                line-height: 30px;
              }

              td {
                text-align: center;
                background-color: #f1efef;
                padding: 10px;

                width: 25%;
              }

              input {
                width: 100%;
                background-color: #f1efef;
                border: none;
              }

              input[type="button"] {
                margin-top: 30px;
                margin-left: 78%;
                border: none;
                background-color: #e89714;
                color: #fff;
                width: 100px;
                border-radius: 6px;
                height: 35px;
              }
            </style>
            <script src="http://code.jquery.com/jquery-latest.min.js"></script>
            <script type="text/javascript">
              let path = "${pageContext.request.contextPath }";
              function setParent() {
                let selectedTx_code = $("input[name='chk']:checked").val();
                if (selectedTx_code === undefined) {
                  alert("택시를 선택해주세요 ");
                } else {
                  let selectedRow = $("input[name='chk']:checked").closest("tr");
                  opener.document.querySelector('input[name="tx_name"]').value = selectedRow.find('input[name="tx_name"]').val();
                  opener.document.querySelector('input[name="tx_number"]').value = selectedRow.find('input[name="tx_number"]').val();
                  opener.document.querySelector('input[name="tx_img"]').value = selectedRow.find('input[name="tx_img"]').val();
                  opener.document.querySelector('textarea[name="tx_intro"]').value = selectedRow.find('input[name="tx_intro"]').val();
                  opener.document.querySelector('input[name="tx_code"]').value = selectedRow.find('input[name="tx_code"]').val();
                  opener.document.querySelector('input[name="tx_tel"]').value = selectedRow.find('input[name="tx_tel"]').val();
                  let imgVar = "${contextPath}/taxiImgdown.do?tx_code=" + selectedRow.find('input[name="tx_code"]').val() + "&tx_img=" + selectedRow.find('input[name="tx_img"]').val();
                  opener.imgVar = imgVar;
                  opener.document.querySelector(".taxiDriverImg").src = imgVar;
                  window.close();
                }
              };

            </script>


          </head>

          <body>
            <div class="card mt-3">
              <div class="card-body">
                <div class="table-responsive mt-2 ">
                  <table id="resTb" class="table table-striped table-bordered text-center" border="1">
                    <caption>지역별 펫 택시 목록</caption>
                    <colgroup>
                      <col width="5%" />
                      <col />
                      <col />
                      <col />
                      <col />
                    </colgroup>
                    <thead>
                      <tr>

                        <th>택시 번호</th>
                        <th>기사님 성함</th>
                        <th>택시 번호</th>
                        <th>연락처</th>

                      </tr>
                    </thead>
                    <tbody>
                      <c:choose>
                        <c:when test="${empty taxiList}">
                          <td colspan="3">배차 가능한 택시가 없습니다.</td>
                        </c:when>
                        <c:when test="${not empty taxiList}">
                          <c:forEach var="tx" items="${taxiList}">
                            <tr>
                              <td><input type="checkbox" name="chk" />

                              <td>
                                <input type="text" name="tx_number" value="${tx.tx_number}">
                              </td>
                              <td>
                                <input type="text" name="tx_name" value="${tx.tx_name}">
                              </td>
                              <td>
                                <input type="text" name="tx_tel" value="${tx.tx_tel}">
                              </td>
                              <td> <input type="hidden" name="tx_code" value="${tx.tx_code}"></td>
                              <td><input type="hidden" name="tx_img" value="${tx.tx_img}"></td>
                              <td><input type="hidden" name="tx_intro" value="${tx.tx_intro}"></td>

                            </tr>
                          </c:forEach>
                        </c:when>
                      </c:choose>
                    </tbody>
                  </table>
                </div>
                <div class="d-flex justify-content-between">
                </div>
                <input type="button" value="택시선택" onclick="window.setParent()">
              </div>
            </div>
          </body>

          </html>