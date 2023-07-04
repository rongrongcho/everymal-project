const checkbox = document.querySelector('input[name=ch]');

checkbox.addEventListener('change', function() {
  if (this.checked) {
        alert("체크박스가 체크되었습니다!");
  } else {
    // 체크박스가 체크 해제되면 실행할 코드 작성
        alert("체크박스가 체크되었습니다!");
  }
});


$(document).ready(function() {
    $("#searchBtn").click(function() {
        var tx_dep = $("#tx_dep").val();

        $.ajax({
            type: "POST",
            url: "taxiList.do",
            data: {
                tx_dep: tx_dep
            },
            success: function(result) {
                // 서버에서 전송한 데이터를 JSON 객체로 변환
                var taxiList = JSON.parse(result);

                // 받아온 데이터를 HTML 코드로 구성
                var tableHtml = "<table><thead><tr><th>택시 번호</th><th>회사명</th><th>전화번호</th></tr></thead><tbody>";
                for (var i = 0; i < taxiList.length; i++) {
                    var taxi = taxiList[i];
                    tableHtml += "<tr><td>" + taxi.tx_number + "</td><td>" + taxi.tx_name + "</td><td>" + taxi.tx_tel + "</td></tr>";
                }
                tableHtml += "</tbody></table>";

                // 생성한 HTML 코드를 화면에 출력
                $("#taxiListTable").html(tableHtml);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("에러 발생 : " + textStatus + " : " + errorThrown);
            }
        });
    });
});