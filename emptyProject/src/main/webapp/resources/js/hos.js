window.onload = function () {
  // 서버 측에서 가져온 데이터를 저장한 배열
  var data = [
    /*...*/
  ];

  // 동적으로 생성할 HTML 코드를 저장할 변수
  var reservBox = "";

  // 데이터를 순회하면서 HTML 코드를 생성
  data.forEach(function (item) {
    html += "<div>";
    html += "<h1>" + item.title + "</h1>";
    html += "<p>" + item.content + "</p>";
    html += "</div>";
  });

  // 생성한 HTML 코드를 컨테이너에 추가
  var container = document.getElementById("recentReservView");
  container.innerHTML = html;
};
