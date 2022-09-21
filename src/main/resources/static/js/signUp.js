/* 회원가입 API 호출부 */
$(document).on("click", "#signUpBtn", function () {
    let input_userId = $('#userId').val();
    let input_userPassword = $('#userPassword').val();
    let input_phoneNumber = $('#phoneNumber').val();
    let input_userName = $('#userName').val();
    let input_serialCode = $('#serialCodeFirst').val() + $('#serialCodeSecond').val();
    let input_nickname = $('#nickname').val();
    let input_email = $('#email').val();
    let input_zipcode = $('#zipcode').val();
    let input_address1 = $('#address1').val();
    let input_address2 = $('#address2').val();
    let idChecked = $.trim($("label[for='checkId']").text());
    let phoneChecked = $('#checkConfirm').val();

    console.log($('#wrongPw').attr('hidden') !== undefined);

    if (idChecked === "Y" && $('#wrongPw').attr('hidden') !== undefined) {
        let input_data = {
            'userId': input_userId,
            'userPw': input_userPassword,
            'phoneNumber': input_phoneNumber,
            'userName': input_userName,
            'serialCode': input_serialCode,
            'nickname': input_nickname,
            'email': input_email,
            'zipcode': input_zipcode,
            'address1': input_address1,
            'address2': input_address2
        }

        let json = JSON.stringify(input_data);

        $.ajax({
            url: "/api/user-manage/sign-up",
            data: json,
            method: "POST",
            contentType: "application/json; charset=utf-8",

            success: function (data) {
                alert("회원가입이 완료되었습니다.")
                location.replace('/')
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
    } else if (idChecked !== "Y") {
        alert("아이디 중복확인을 해주시기 바랍니다.");
        $('#checkId').focus();
    } else if ($('#wrongPw').attr('hidden') === undefined) {
        alert("비밀번호를 확인해주시기 바랍니다.");
        $('#passwordConfirm').focus();
    }

});

/* ID 중복확인 API 호출부 */
$(document).on("click", "#checkId", function () {
    let input_userId = $('#userId').val()

    if (isEmpty(input_userId)) {
        alert("아이디를 입력해주세요.");
        $('#userId').focus();
    } else if (isNotEmpty(input_userId)) {
        $.ajax({
            url: "/api/user-manage/confirmId/" + input_userId,
            method: "GET",

            success: function (data) {
                if (data) {
                    $("label[for='checkId']").text("N");
                    alert("이미 사용중인 아이디입니다.\n다른 아이디를 사용해주세요.");
                    $('#userId').focus();
                } else {
                    $("label[for='checkId']").text("Y");
                    alert("사용가능한 아이디입니다.");
                    $('#userPassword').focus();
                }
            },
            error: function (request, status, error) {
                console.log("에러");
            },
            complete: function () {
                console.log("완료");
            }
        })
    }
});

$(document).on("keyup", "#passwordConfirm", function () {
    let input_password = $("#userPassword").val();
    let input_confirmPw = $("#passwordConfirm").val();


    if (input_password !== input_confirmPw) {
        $('#wrongPw').removeAttr('hidden');
    } else {
        $('#wrongPw').attr('hidden','true');
    }
    console.log($('#wrongPw').attr('hidden') === undefined);
});

// 공통함수
const isEmpty=(str)=>{
    let obj = String(str);
    return obj === null || obj === undefined || obj === 'null' || obj === 'undefined' || obj === '';
}

const isNotEmpty=(str)=>{
    return !isEmpty(str);
}
