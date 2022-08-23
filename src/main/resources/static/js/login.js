/* 로그인 API */
$(document).on("click", "#loginBtn", function () {
    let input_userId = $('#floatingInput').val();
    let input_userPassword = $('#floatingPassword').val();

    if ($('#rememberId').is(':checked')) {
        $.removeCookie('rememberIdCookie');
        $.cookie('rememberIdCookie', input_userId, {expires:365});
    } else {
        $.removeCookie('rememberIdCookie');
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
            let errorLog = JSON.parse(request.responseText);
            console.log(errorLog.message())
        },
        complete: function () {
            console.log("완료");
        }
    })
});