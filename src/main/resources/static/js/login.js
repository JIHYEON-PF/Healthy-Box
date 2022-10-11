// 로그인 API
$(document).on("click", "#loginBtn", function () {
    let input_userId = $('#floatingInput').val();
    let input_userPassword = $('#floatingPassword').val();

    if ($('#rememberId').is(':checked')) {
        $.cookie('rememberIdCookie', input_userId, {expires:365, path:'/'});
    } else {
        $.cookie('rememberIdCookie', null, {expires: -1, path: '/'});
    }

    $.ajax({
        url: "/api/user-manage/login?userId="+input_userId+"&userPw="+input_userPassword,
        method: "POST",

        success: function (data) {
            if (data) {
                location.replace('/')
            } else {
                $('#warningAccount').removeAttr('hidden');
                $('#inputPw').val('').focus();
            }
        },
        error: function (request, status, error) {
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    })
});

/** 아이디 찾기 */
$(document).on("click", "#btnFindId", function () {
    let inputName = $("#idInputSectName").val();
    let inputEmail = $("#idInputSectEmail").val();

    $.ajax({
        url: "/api/user-manage/find-id?userName="+inputName+"&email="+inputEmail,
        method: "GET",

        success: function (data) {
            /** 아이디 찾기 완료 페이지로 이동하면서 아이디 정보를 넘겨줌 형식은 리다이렉트
             * response값 data가 공백문자열인지 데이터가 입력된 값인지에 따라 다른 행동 수행
             * 공백일 경우 alert창을 띄우면서 일치하는 아이디가 없음을 알림
             * 공백이 아닐 경우 페이지 이동
             * */
            console.log(data);
            if (data === '') {
                alert("등록된 회원이 없습니다.\n입력 데이터를 다시 한번 확인하시기 바랍니다.");
                $("#idInputSectName").val("").focus();
                $("#idInputSectEmail").val("");
            } else {
                $("#findSect").attr('hidden', true);
                $("#foundSect").removeAttr('hidden');
                $("#labelFoundName").text(inputName);
                $("#labelFoundId").text('[ ' + data + ' ]').attr('value', data);
            }
        },
        error: function (request, status, error) {
            console.log('에러');
        },
        complete: function() {
            console.log('완료');
        }
    });
});

$(document).on("click", "#btnLocationLogin", function () {
    location.replace("/user/login/" + $("#labelFoundId").attr('value'));
});

/** 비밀번호 찾기 */
$(document).on("click", "#btnFindPw", function () {
    let inputId = $("#pwInputSectId").val();
    let inputName = $("#pwInputSectName").val();
    let inputEmail = $("#pwInputSectEmail").val();

    $.ajax({
        url: "/api/user-manage/find-password?userId=" + inputId + "&name=" + inputName + "&email=" + inputEmail,
        method: "GET",

        success: function (data) {
            let msg = "";
            if (data) {
                alert(inputName + " 님의 임시 비밀번호가 " + inputEmail + "로 발송되었습니다.");
                location.replace("/user/login/");
            } else {
                alert("등록된 사용자 정보가 없습니다.");
            }
        },
        error: function (request, status, error) {
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    })
});
