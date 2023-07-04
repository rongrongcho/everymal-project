$(function () {
    var num0 = 0;
    var num1 = 1;
    $('#animalplus').click(function(){
      var a_type = $('#joinAnimalForm_'+num0).find('#pet_types').val();
      var b_type = $('#joinAnimalForm_'+num0).find('#b_type').val();
      $('#joinAnimalForm_'+num0).clone().insertAfter('#joinAnimalForm_'+num0);
      $('#joinAnimalForm_'+num0).filter(':first').attr('id','joinAnimalForm_'+num1);
      $('#joinAnimalForm_'+num0).find('#pet_types').val(a_type).prop("selected",true);
      $('#joinAnimalForm_'+num0).find('#b_type').val(b_type).prop("selected",true);
      $('#joinAnimalForm_'+num1).find("input[type='text']").val("");
      $('#joinAnimalForm_'+num1).find("input[type='number']").val("");
      $('#joinAnimalForm_'+num1).find("textarea").val("");
      $('#joinAnimalForm_'+num1).find('#pet_types').val("");
      $('#joinAnimalForm_'+num1).find('#b_type').val("");
      $('#joinAnimalForm_'+num1).find("input[name='pet_sex']").prop('checked', false);
      num0++;
      num1++;
      alert("새로운 동물의 정보도 잊지말고 입력해주세요!")
   });
   $('#animalminus').click(function(){
      if(num0 >= 1){
         $("#joinAnimalForm_"+num0).remove();
         num0--;
         num1--;
        }else{
         alert("최소 한마리 이상의 반려동물 정보를 입력해주세요.");
      }
   });
});