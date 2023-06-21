<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" import="java.util.*" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%
request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      type="text/css"
      href="${contextPath }/css/reset.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="${contextPath }/css/common.css"
    />
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
        background-color: #64199f;
        color: #fff;
        margin: 20px;
        font-size: 16px;
        height: 30px;
        line-height: 30px;
      }

      td {
        text-align: center;
        background-color: #f1efef;
        padding: 10px;
      }

      input[type="button"] {
        margin-top: 30px;
        margin-left: 75%;
        border: none;
        background-color: #64199f;
        color: #fff;
        width: 100px;
        border-radius: 6px;
        height: 35px;
      }
    </style>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
      let path = "${pageContext.request.contextPath }";
      $(function () {
        window.setParent = function () {
          let selectedValue = $("input[name='selected_animal']:checked").val();
          if (selectedValue === undefined) {
            alert("불러오기할 반려동물 정보를 선택해주세요.");
          } else {
            let selectedRow = $("#tr_" + selectedValue);
            opener.document.querySelector('input[name="pet_name"]').value =
              selectedRow.find('input[name="pet_name"]').val();
            opener.document.querySelector('input[name="pet_age"]').value =
              selectedRow.find('input[name="pet_age"]').val();
            opener.document.querySelector('input[name="pet_number"]').value =
              selectedRow.find('input[name="pet_number"]').val();
            opener.document.querySelector('input[name="pet_sex"]').value =
              selectedRow.find('input[name="pet_sex"]').val();
            // 반려동물 성별 선택
            let selectedSex = selectedRow.find('input[name="pet_sex"]').val();
            let sex_f = opener.document.getElementById("pet_sex_f");
            let sex_m = opener.document.getElementById("pet_sex_m");
            if (selectedSex === "0") {
              sex_f.checked = true;
            } else {
              sex_m.checked = true;
            }
            opener.document.querySelector('select[name="pet_types"]').value =
              selectedRow.find('input[name="pet_types"]').val();
            // 종류 선택
            let selectedType = selectedRow
              .find('input[name="pet_types"]')
              .val();
            let petTypeSelect = opener.document.getElementById("petRace");

            for (let i = 0; i < petTypeSelect.options.length; i++) {
              if (petTypeSelect.options[i].value == selectedType) {
                petTypeSelect.selectedIndex = i;
                break;
              }
            }
            opener.document.querySelector('select[name="b_type"]').value =
              selectedRow.find('input[name="b_type"]').val();
            // 종류 선택
            let selectedType2 = selectedRow.find('input[name="b_type"]').val();
            let petTypeSelect2 = opener.document.getElementById("b_type");

            for (let i = 0; i < petTypeSelect2.options.length; i++) {
              if (petTypeSelect2.options[i].value == selectedType2) {
                petTypeSelect2.selectedIndex = i;
                break;
              }
            }
            opener.document.querySelector('input[name="pet_weight"]').value =
              selectedRow.find('input[name="pet_weight"]').val();
            window.close();
          }
        };
      });
    </script>
  </head>

  <body>
    <div class="card mt-3">
      <div class="card-body">
        <div class="table-responsive mt-2">
          <table
            id="resTb"
            class="table table-striped table-bordered text-center"
            border="1"
          >
            <caption>
              나의 반려동물 정보
            </caption>
            <colgroup>
              <col width="5%" />
              <col />
              <col />
              <col />
              <col />
            </colgroup>
            <thead>
              <tr>
                <th></th>
                <th>반려동물 이름</th>
                <th>반려동물 나이</th>
                <th>반려동물 종류</th>
                <th>반려동물 성별</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="animal" items="${animalList}" varStatus="status">
                <tr id="tr_${animal.pet_code}">
                  <input
                    type="hidden"
                    id="pet_code_${status.index}"
                    name="pet_code"
                    value="${animal.pet_code}"
                  />
                  <input
                    type="hidden"
                    id="pet_name_${status.index}"
                    name="pet_name"
                    value="${animal.pet_name}"
                  />
                  <input
                    type="hidden"
                    id="pet_age_${status.index}"
                    name="pet_age"
                    value="${animal.pet_age}"
                  />
                  <input
                    type="hidden"
                    id="pet_number_${status.index}"
                    name="pet_number"
                    value="${animal.pet_number}"
                  />
                  <input
                    type="hidden"
                    id="pet_sex_${status.index}"
                    name="pet_sex"
                    value="${animal.pet_sex}"
                  />
                  <input
                    type="hidden"
                    id="pet_types_${status.index}"
                    name="pet_types"
                    value="${animal.pet_types}"
                  />
                  <input
                    type="hidden"
                    id="pet_types_${status.index}"
                    name="b_type"
                    value="${animal.b_type}"
                  />
                  <input
                    type="hidden"
                    id="pet_weight_${status.index}"
                    name="pet_weight"
                    value="${animal.pet_weight}"
                  />

                  <td>
                    <input
                      type="radio"
                      name="selected_animal"
                      value="${animal.pet_code}"
                    />
                  </td>
                  <td class="pet_name">
                    <c:out value="${animal.pet_name}" />
                  </td>
                  <td class="pet_age">
                    <c:out value="${animal.pet_age}" />
                  </td>
                  <td class="pet_types">
                    <c:out value="${animal.pet_types}" />
                  </td>
                  <td class="pet_sex">
                    <c:out value="${animal.pet_sex == '0' ? '♀︎' : '♁'}" />
                  </td>
                </tr>
              </c:forEach>
              <c:if test="${empty animalList } ">
                <tr>
                  <td colspan="5">등록된 반려동물이 없습니다.</td>
                </tr>
              </c:if>
            </tbody>
          </table>
        </div>
        <div class="d-flex justify-content-between"></div>
        <input
          type="button"
          value="반려동물 선택"
          onclick="window.setParent()"
        />
      </div>
    </div>
  </body>
</html>
