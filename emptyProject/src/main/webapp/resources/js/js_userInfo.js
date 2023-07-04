window.onload = function () {
  function fn_modSubmit(obj) {
    obj.action = "${contetxtPath}/userMypage/modInfo.do";
    obj.submit();
  }

  function readImage(input) {
    if (input.files && input.files[0]) {
      let reader = new FileReader();
      reader.onload = function (event) {
        $("#preview").attr("src", event.target.result); //내가 선택한 이미지 이름를 가져옴
        console.log(event.target.result);
      };
      reader.readAsDataURL(input.files[0]);
    }
  }
};
