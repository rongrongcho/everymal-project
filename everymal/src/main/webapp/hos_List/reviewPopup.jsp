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

              h2 {
                font-size: 1.5em;
                text-align: center;
                margin: 30px 0 20px 0;
              }

              form {
                position: relative;
                margin: 0 auto;
                width: 80%;
              }

              label {
                margin: 10px 30px 20px 10px;
              }

              input,
              select,
              textarea {
                text-indent: 4px;
                border-radius: 5px;
                background-color: #F5F5F5;
                border: none;
                height: 30px;
              }

              p {
                font-size: 1.1em;
                margin: 30px 0;
              }

              .date {
                position: absolute;
                top: 0;
                right: 30px;
              }

              input[name="rv_title"] {
                width: 180px;
              }

              textarea {
                resize: none;
                width: 85%;
                height: 230px;
                margin-left: 78px;
              }

              .reviewContent {
                position: absolute;
                top: 150px;
              }

              button {
                position: absolute;
                width: 100px;
                height: 40px;
                background-color: #FBA16E;
                color: #fff;
                border: none;
                border-radius: 8px;
                right: 20px;
              }
            </style>
            <script src="http://code.jquery.com/jquery-latest.min.js"></script>
            <script type="text/javascript">
              $(function () {
                let path = "${pageContext.request.contextPath }";

              });
            </script>


          </head>

          <body>
            <div class="content">
              <h2>리뷰 작성하기</h2>
              <form action="${contextPath}/hospital/addReview.do" name="myInfoForm" method="post" id="myInfoForm">
                <fieldset>
                  <legend>리뷰작성 폼</legend>
                  <input type=hidden name="hos_code" id="hos_code">
                  <input type=hidden name="hos_name" id="hos_name">
                  <p class="title">
                    <label class="review" for="title">제목</label>
                    <input type="text" id="title" name="rv_title">
                  </p>
                  <p class="date">
                    <label class="review" for="datepicker">날짜</label>
                    <c:set var="ymd" value="<%=new java.util.Date()%>" />
                    <fmt:formatDate var="currentDate" value="${ymd}" pattern="yy-MM-dd" />
                    <input type="text" id="datepicker" name="rv_date" value="${currentDate }" readonly="readonly">
                  </p>
                  <p class="rating">
                    <label class="review fileImg" for="chooseFile">별점</label>
                    <select name="rv_rate" size="1">
                      <option value="1">🌟</option>
                      <option value="2">🌟🌟</option>
                      <option value="3">🌟🌟🌟</option>
                      <option value="4">🌟🌟🌟🌟</option>
                      <option value="5">🌟🌟🌟🌟🌟</option>
                    </select>
                  </p>
                  <p class="formContent">
                    <label class="review reviewContent" for="content">내용</label>
                    <textarea name="rv_text" id="content" placeholder="리뷰를 적어주세요."></textarea>
                  </p>


                  <button type="submit" form="myInfoForm" onclick="fn_writeR()">리뷰 등록</button>
                </fieldset>
              </form>
            </div>

          </body>
          <script>
            // 자식 창에서 부모 창의 값을 가져와서 히든 인풋 태그에 할당
            document.getElementById('hos_name').value = window.opener.document.getElementById('hos_name').value;
            document.getElementById('hos_code').value = window.opener.document.getElementById('hos_code').value;

          </script>

          </html>