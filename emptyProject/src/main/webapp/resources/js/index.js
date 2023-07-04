// function init(){
//     $("#second").delay(500).animate({
//         left : "-82%"
//     },900);
//     $("#third").delay(500).stop(1).animate({
//         left : "-88%",
//     },400);
//     setTimeout(function(){
//         $(".emergency").css({
//             display : "none"
//         });
//         $(".petTaxi").css({
//             display : "none"
//         });
//         $(".chatBox").css({
//             display : "none"
//         });
//     }, 1000);
// }

function header_move(num){
  $(".topNav").css({
    display : "none",
    marginRight : num*5+5+"%"
  });
  $(".topNav").delay(1800).fadeIn(1000);
}

//열린 페이지 확인용
let opend = [false, false];
//호버 체크용
let hover_sw = [true, true, false];
$(function () {
  //병원 찾기 클릭시
  $("#hos_bar").click(function () {
    //공통부분 초기화
    


    $("#eme_bar").stop(true).animate(
      {
        width: "6%",
        right: "0%",
      },
      700
    );
    $("#hos_bar .bar_arrow").css({
      display: "none",
    });
    $("#eme_bar .bar_arrow").attr("src", "./img/arrow.png");
    $("#eme_bar .bar_arrow").css({
      display: "block",
    });
    $("#qna_bar .bar_arrow").attr("src", "./img/arrow.png");
    $("#qna_bar .bar_arrow").css({
      display: "block",
    });

    $("#hos_bar .hover_text").css({
      color: "#f2efe9",
    });
    $(this).stop(true).animate(
      {
        width: "6%",
        right: "0px",
      },
      1000
    );

    //응급실, 실시간 상담이 모두 열려있는 경우
    if (opend[1]) {
	header_move(0);
      $("#second").stop(1).delay(500).animate(
        {
          left: "-82%",
        },
        1000
      );

      $("#third").stop(1).delay(500).animate(
        {
          left: "-88%",
        },
        1000
      );

      $(".first_fadein").css({
        display: "none",
      });

      // 전환효과 시작
      $("#efR1_b").css({
        display: "block",
      });
      $("#efR2_b").css({
        display: "block",
      });
      $("#efR2_f").css({
        display: "block",
      });

      $("#efR2_f")
        .animate(
          {
            left: "0%",
            width: "88%",
          },
          500
        )
        .animate(
          {
            left: "0%",
            width: "6%",
          },
          400
        )
        .delay(900)
        .fadeOut(1000);
      $("#efR2_b")
        .delay(100)
        .animate(
          {
            left: "6%",
            width: "88%",
          },
          600
        )
        .animate(
          {
            left: "6%",
            width: "6%",
          },
          600
        )
        .delay(400)
        .fadeOut(1000);
      $("#efR1_b")
        .delay(500)
        .animate(
          {
            left: "12%",
            width: "88%",
          },
          700
        )
        .animate(
          {
            left: "12%",
            width: "6%",
          },
          500
        )
        .fadeOut(1000);

      setTimeout(function () {
        $(".first_fadein").fadeIn(1000);
      }, 1800);

      setTimeout(function () {
        $("#efR1_b").css({
          display: "none",
          left: "94%",
          width: "6%",
        });
        $("#efR2_b").css({
          display: "none",
          left: "88%",
          width: "6%",
        });
        $("#efR2_f").css({
          display: "none",
          left: "87%",
          width: "0%",
        });
      }, 2200);
      // 전환효과 영역 끝

      //열린 페이지와 호버 여부 재설정
      opend[0] = false;
      opend[1] = false;
      hover_sw[0] = true;
      hover_sw[1] = true;
      hover_sw[2] = false;

      //응급실 페이지만 열려있는 경우
    } else if (opend[0]) {
	header_move(0);
      $("#second").stop(1).delay(500).animate(
        {
          left: "-82%",
        },
        1000
      );

      $(".first_fadein").css({
        display: "none",
      });

      // 전환효과 시작
      $("#efR1_b").css({
        display: "block",
      });
      $("#efR1_f").css({
        display: "block",
      });

      $("#efR1_f")
        .animate(
          {
            left: "6%",
            width: "88%",
          },
          700
        )
        .animate(
          {
            left: "6%",
            width: "6%",
          },
          600
        )
        .delay(500)
        .fadeOut(1000);
      $("#efR1_b")
        .delay(600)
        .animate(
          {
            left: "12%",
            width: "88%",
          },
          600
        )
        .animate(
          {
            left: "12%",
            width: "6%",
          },
          600
        )
        .fadeOut(1000);

      setTimeout(function () {
        $(".first_fadein").fadeIn(1000);
      }, 1800);

      setTimeout(function () {
        $("#efR1_b").css({
          display: "none",
          left: "94%",
          width: "6%",
        });
        $("#efR1_f").css({
          display: "none",
          left: "93%",
          width: "0%",
        });
      }, 2200);
      // 전환효과 끝

      //열린 페이지와 호버 여부 재설정
      opend[0] = false;
      hover_sw[0] = true;
      hover_sw[1] = true;
      hover_sw[2] = false;
    }
    setTimeout(function () {
      $(".emergency").css({
        display: "none",
      });
      $(".petTaxi").css({
        display: "none",
      });
      $(".chatBox").css({
        display: "none",
      });
    }, 1000);
  });

  //응급실 클릭시
  $("#eme_bar").click(function () {
    //공통부분 초기화
    


    $("#eme_bar .hover_text").css({
      color: "#f2efe9",
    });
    $("#eme_bar .bar_arrow").css({
      display: "none",
    });
    $(".emergency").css({
      display: "block",
    });
    $(".petTaxi").css({
      display: "block",
    });
    $(this).stop(true).animate(
      {
        width: "6%",
        right: "0px",
      },
      1000
    );
     $("#third").stop(true).animate(
            {
              left: "-88%",
            },
            1000
          );

    // 사이렌 여따가 추가
    let num = 1;
    setInterval(function () {
      num++;
      if (num > 2) {
        num = 1;
      }
      $(".sirenImg").attr("src", "./img/siren" + num + ".png");
    }, 1000);

    $(".petTaxi").css({
      display: "block",
    });
    $(this).stop(true).animate(
      {
        width: "6%",
        right: "0px",
      },
      1000
    );

    //병원 찾기가 열려있는 경우
    if (!opend[0]) {
	header_move(1);
      $("#second").stop(1).delay(200).animate(
        {
          left: "0%",
        },
        1000
      );

      $(".emergency").css({
        display: "none",
      });
      $(".petTaxi").css({
        display: "none",
      });

      // 전환효과 시작
      $("#ef1_b").css({
        display: "block",
      });
      $("#ef1_f").css({
        display: "block",
      });

      $("#ef1_b")
        .animate(
          {
            left: "12%",
          },
          700
        )
        .animate(
          {
            left: "94%",
            width: "6%",
          },
          500
        )
        .fadeOut(1000);
      $("#ef1_f")
        .delay(610)
        .animate(
          {
            left: "6%",
          },
          600
        )
        .animate(
          {
            left: "94%",
            width: "0%",
          },
          600
        );
      setTimeout(function () {
        $(".emergency").fadeIn(1000);
        $(".petTaxi").css("display", "block").hide().fadeIn(1000);
      }, 1800);

      setTimeout(function () {
        $("#ef1_b").css({
          display: "none",
          left: "-69.9%",
          width: "88%",
        });
        $("#ef1_f").css({
          display: "none",
          left: "-76%",
          width: "88%",
        });
      }, 2200);

      // 전환효과 끝

      $("#hos_bar .bar_arrow").attr("src", "./img/arrow_r.png");
      $("#hos_bar .bar_arrow").css({
        display: "block",
      });
      $("#qna_bar .bar_arrow").attr("src", "./img/arrow.png");
      $("#qna_bar .bar_arrow").css({
        display: "block",
      });

      //열린 페이지와 호버 여부 재설정
      opend[0] = true;
      hover_sw[0] = false;
      hover_sw[2] = true;

      //실시간 상담이 열려있는 경우
    } else if (opend[0] && opend[1]) {
	header_move(1);
      $("#third").stop(1).delay(400).animate(
        {
          left: "-88%",
        },
        1000
      );

      $(".emergency").css({
        display: "none",
      });
      $(".petTaxi").css({
        display: "none",
      });

      // 전환효과 시작

      $("#efR2_b").css({
        display: "block",
      });
      $("#efR2_f").css({
        display: "block",
      });

      $("#efR2_f")
        .animate(
          {
            left: "0%",
            width: "88%",
          },
          700
        )
        .animate(
          {
            left: "0%",
            width: "6%",
          },
          600
        )
        .delay(500)
        .fadeOut(1000);
      $("#efR2_b")
        .delay(400)
        .animate(
          {
            left: "6%",
            width: "88%",
          },
          700
        )
        .animate(
          {
            left: "6%",
            width: "6%",
          },
          700
        )
        .fadeOut(1000);

      setTimeout(function () {
        $(".emergency").fadeIn(1000);
        $(".petTaxi").css("display", "block").hide().fadeIn(1000);
      }, 1800);

      setTimeout(function () {
        $("#efR2_b").css({
          display: "none",
          left: "88%",
          width: "6%",
        });
        $("#efR2_f").css({
          display: "none",
          left: "87%",
          width: "0%",
        });
      }, 2200);

      // 전환효과 끝

      $("#qna_bar .bar_arrow").attr("src", "./img/arrow.png");
      $("#qna_bar .bar_arrow").css({
        display: "block",
      });

      //열린 페이지와 호버 여부 재설정
      opend[1] = false;
      hover_sw[1] = true;
      hover_sw[2] = true;
    }
    setTimeout(function () {
      $(".chatBox").css({
        display: "none",
      });
    }, 1000);
  });

  //실시간 상담 클릭시
  $("#qna_bar").click(function () {
    //공통부분 초기화
    



    $("#qna_bar .bar_arrow").css({
      display: "none",
    });
    $("#eme_bar .bar_arrow").attr("src", "./img/arrow_r.png");
    $("#eme_bar .bar_arrow").css({
      display: "block",
    });
    $("#hos_bar .bar_arrow").attr("src", "./img/arrow_r.png");
    $("#hos_bar .bar_arrow").css({
      display: "block",
    });
    //qna하나씩 올라가기 애니메이션(이거 다른거 클릭하면 없어지고 다시 시작하게 설정해야해)
    let arr = [0.5, 16, 32, 48, 64, 80];
    $(".chatBoxP").each(function (index) {
      $(this)
        .delay(index * 2000)
        .animate(
          {
            top: arr[index] + "%",
          },
          "slow"
        );
    });
    $("#qna_bar .hover_text").css({
      color: "#f2efe9",
    });
    $("#qna_bar").stop(true).animate(
      {
        width: "6%",
      },
      1000
    );

    //병원 찾기와 실시간 상담이 모두 열려있는 경우
    if (opend[0] && !opend[1]) {
	header_move(2);
      $("#third").stop(1).delay(200).animate(
        {
          left: "-6%",
        },
        1000
      );

      // 전환효과 시작

      $(".chatBox").css({
        display: "none",
      });
      $(".thirdTitle").css({
        display: "none",
      });
      $(".showQ").css({
        display: "none",
      });
      $(".showQ").css({
        display: "none",
      });
      $(".goQ").css({
        display: "none",
      });

      $("#ef2_b").css({
        display: "block",
      });
      $("#ef2_f").css({
        display: "block",
      });

      $("#ef2_b")
        .animate(
          {
            left: "6%",
          },
          700
        )
        .animate(
          {
            left: "88%",
            width: "6%",
          },
          500
        )
        .fadeOut(1000);
      $("#ef2_f")
        .delay(610)
        .animate(
          {
            left: "0%",
          },
          600
        )
        .animate(
          {
            left: "88%",
            width: "0%",
          },
          600
        );
      setTimeout(function () {
        $(".chatBox").fadeIn(1000);
        $(".thirdTitle").fadeIn(1000);
        $(".showQ").fadeIn(1000);
        $(".showQ").fadeIn(1000);
        $(".goQ").fadeIn(1000);
      }, 1800);

      setTimeout(function () {
        $("#ef2_b").css({
          display: "none",
          left: "-75.8%",
          width: "88%",
        });
        $("#ef2_f").css({
          display: "none",
          left: "-82%",
          width: "88%",
        });
      }, 2200);

      // 전환효과 끝

      //열린 페이지와 호버 여부 재설정
      opend[0] = true;
      opend[1] = true;
      hover_sw[0] = false;
      hover_sw[1] = false;
      hover_sw[2] = true;
    }
    //병원 찾기만 열려있는 경우
    else if (!opend[0]) {
	header_move(2);
      $("#third").stop(1).delay(200).animate(
        {
          left: "-6%",
        },
        1000
      );
      $("#second").stop(1).delay(200).animate(
        {
          left: "0%",
        },
        1000
      );

      // 전환효과 시작
      $(".chatBox").css({
        display: "none",
      });
      $(".thirdTitle").css({
        display: "none",
      });
      $(".showQ").css({
        display: "none",
      });
      $(".showQ").css({
        display: "none",
      });
      $(".goQ").css({
        display: "none",
      });

      $("#ef1_b").css({
        display: "block",
      });
      $("#ef2_b").css({
        display: "block",
      });
      $("#ef2_f").css({
        display: "block",
      });

      $("#ef1_b")
        .animate(
          {
            left: "12%",
          },
          700
        )
        .animate(
          {
            left: "94%",
            width: "6%",
          },
          500
        )
        .fadeOut(1500);
      $("#ef2_b")
        .delay(210)
        .animate(
          {
            left: "6%",
          },
          600
        )
        .animate(
          {
            left: "88%",
            width: "6%",
          },
          600
        )
        .fadeOut(1500);
      $("#ef2_f")
        .delay(620)
        .animate(
          {
            left: "0%",
          },
          500
        )
        .animate(
          {
            left: "88%",
            width: "0%",
          },
          500
        );
      setTimeout(function () {
        $(".chatBox").fadeIn(1000);
        $(".thirdTitle").fadeIn(1000);
        $(".showQ").fadeIn(1000);
        $(".showQ").fadeIn(1000);
        $(".goQ").fadeIn(1000);
      }, 1800);

      setTimeout(function () {
        $("#ef1_b").css({
          display: "none",
          left: "-69.9%",
          width: "88%",
        });
        $("#ef2_b").css({
          display: "none",
          left: "-75.8%",
          width: "88%",
        });
        $("#ef2_f").css({
          display: "none",
          left: "-82%",
          width: "88%",
        });
      }, 2200);

      // 전환효과 끝

      //열린 페이지와 호버 여부 재설정
      opend[0] = true;
      opend[1] = true;
      hover_sw[0] = false;
      hover_sw[1] = false;
      hover_sw[2] = true;
    }
  });

  $("#hos_bar").hover(
    function () {
      if (hover_sw[2]) {
        if (opend[1]) {
          $("#eme_bar").stop(true).animate(
            {
              width: "6%",
              right: "2%",
            },
            400
          );
        }
        $(this).stop(true).animate(
          {
            width: "8%",
          },
          400
        );
        $("#hos_bar .hover_text").css({
          color: "#f2efe9"
        });
      }
    },
    function () {
      if (hover_sw[2]) {
        if (opend[1]) {
          $("#eme_bar").stop(true).animate(
            {
              width: "6%",
              right: "0%",
            },
            400
          );
        }
        $(this).stop(true).animate(
          {
            width: "6%",
          },
          400
        );
        $("#hos_bar .hover_text").css({
          color: "#f2efe9",
        });
      }
    }
  );
  $("#eme_bar").hover(
    function () {
      if (hover_sw[0]) {
        if (!opend[0]) {
          $("#second").stop(true).animate(
            {
              left: "-80%",
            },
            400
          );
          $("#third").stop(true).animate(
            {
              left: "-86%",
            },
            400
          );
        }
        $(this).stop(true).animate(
          {
            width: "8%",
            right: "0%",
          },
          400
        );
        $("#eme_bar .hover_text").css({
          color: "#f2efe9",
        });
      } else if (!hover_sw[0]) {
        if (opend[1]) {
          $(this).stop(true).animate(
            {
              width: "8%",
              right: "0%",
            },
            400
          );
          $("#eme_bar .hover_text").css({
            color: "#f2efe9"
          });
        }
      }
    },
    function () {
      if (hover_sw[0]) {
        if (!opend[0]) {
          $("#second").stop(true).animate(
            {
              left: "-82%",
            },
            400
          );
        }
        $(this).stop(true).animate(
          {
            width: "6%",
            right: "0%",
          },
          400
        );
        $("#third").stop(true).animate(
            {
              left: "-88%",
            },
            400
          );
        $("#eme_bar .hover_text").css({
          color: "#f2efe9",
        });
      } else if (!hover_sw[0]) {
        if (opend[1]) {
          $(this).stop(true).animate(
            {
              width: "6%",
              right: "0%",
            },
            400
          );
          $("#eme_bar .hover_text").css({
            color: "#f2efe9",
          });
        }
      }
    }
  );
  $("#qna_bar").hover(
    function () {
      if (hover_sw[1]) {
        if (!opend[0]) {
          $("#second").stop(true).animate(
            {
              left: "-80%",
            },
            400
          );
          $("#third").stop(true).animate(
            {
              left: "-86%",
            },
            400
          );
        } else if (!opend[1]) {
          $("#third").stop(true).animate(
            {
              left: "-86%",
            },
            400
          );
        }
        $(this).stop(true).animate(
          {
            width: "8%",
          },
          400
        );
        $("#qna_bar .hover_text").css({
          color: "#f2efe9"
        });
      }
    },
    function () {
      if (hover_sw[1]) {
        if (!opend[0]) {
          $("#second").stop(true).animate(
            {
              left: "-82%",
            },
            400
          );
          $("#third").stop(true).animate(
            {
              left: "-88%",
            },
            400
          );
        } else if (!opend[1]) {
          $("#third").stop(true).animate(
            {
              left: "-88%",
            },
            400
          );
        }
        $(this).stop(true).animate(
          {
            width: "6%",
          },
          400
        );
        $("#qna_bar .hover_text").css({
          color: "#f2efe9",
        });
      }
    }
  );
});
