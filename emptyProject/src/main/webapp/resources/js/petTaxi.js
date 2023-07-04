const wrapper = document.querySelector(".wrapper");
const select = document.querySelector(".select");
const options = document.querySelector(".options");
const input = document.querySelector(".filter");

let startPoint = [
  "부천시",
  "서울시",
  "김포시",
  "부산시",
  "제주시",
  "시흥시",
  "울산시",
  "대구시",
  "광주시",
];

select.addEventListener("click", function () {
  let c = wrapper.className;
  wrapper.classList.toggle("active");
});

input.addEventListener("keyup", function () {
  let arr = [];
  let searchWord = input.value;

  if (searchWord.length > 0) {
    arr = startPoint
      .filter((data) => {
        return data.startsWith(searchWord);
      })
      .map((data) => `<li onclick="changeClickedName(this)">${data}</li>`)
      .join("");

    options.innerHTML = arr ? arr : "<p>조회된 지역이 없습니다.</p>";
  } else {
    options.innerHTML = "";
    addLi();
  }
});

addLi();

function addLi() {
  startPoint.forEach((country) => {
    let li = `<li onclick="changeClickedName(this)">${country}</li>`;
    options.insertAdjacentHTML("beforeend", li);
  });
}

function changeClickedName(li) {
  input.value = "";
  addLi();
  wrapper.classList.remove("active");
  select.firstElementChild.innerText = li.innerText;
}
// ===========================================================================
$("#day").datepicker({
  language: "ko",
});


