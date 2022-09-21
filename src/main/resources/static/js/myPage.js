// 비밀번호 확인
$(document).on("click", "#confirmBtn", function () {
    let login_userId = $('#loginBtnUnSession').val();
    let input_password = $('#input_pw').val();

    if (isNotEmpty(input_password)) {
        $.ajax({
            url: "/api/mypage/check-pw/" + input_password,
            method: "POST",
            success: function (data) {
                if (data) {
                    if ($('#myPageId').text() === 'modify') {
                        location.replace("/mypage/modify-user");
                    } else if ($('#myPageId').text() === 'unregister') {
                        console.log('hello');
                        if (confirm("정말 탈퇴하시겠습니까?") === true) {
                            $.ajax({
                                url: "/api/user-manage/delete-user?userId="+login_userId+"&userPw="+input_password,
                                method: "DELETE",
                                success: function (data) {
                                    alert("회원 탈퇴되었습니다.\n\n감사합니다.");
                                    location.replace("/");
                                },
                                error: function (request, status, error) {
                                    console.log("에러");
                                },
                                complete: function () {
                                    console.log("완료");
                                }
                            });
                        }
                    }
                } else {
                    alert("비밀번호가 일치하지 않습니다.");
                    $('#input_password').focus();
                }
            },
            error: function (request, status, error) {
                console.log("에러");
            },
            complete: function () {
                console.log("완료");
            }
        })
    } else if (isEmpty(input_password)) {
        alert("비밀번호를 입력해주시기 바랍니다.");
        $('#input_password').focus();
    }
});

//// 회원정보 수정
// 비밀번호 체크
$(document).on("keyup", "#passwordConfirm", function () {
    let input_password = $("#userPassword").val();
    let input_confirmPw = $("#passwordConfirm").val();

    if (input_password !== input_confirmPw) {
        $('#wrongPw').removeAttr('hidden');
    } else {
        $('#wrongPw').attr('hidden','true');
    }
});

// 회원정보 수정 전달
$(document).on("click", "#modifyUserInfo", function () {
    //데이터 입력 받은 후 JSON 으로 변환
    let user_id = $("#userId").val();
    let user_pw = $("#userPassword").val();
    let user_phoneNumber = $("#phoneNumber").val();
    let user_name = $("#userName").val();
    let user_nickname = $("#nickname").val();
    let user_email = $("#email").val();
    let user_zipcode = $("#zipcode").val();
    let user_address1 = $("#address1").val();
    let user_address2 = $("#address2").val();

    if ($('#wrongPw').attr('hidden') !== undefined) {
        let input_data = {
            'userId': user_id,
            'userPw': user_pw,
            'phoneNumber': user_phoneNumber,
            'userName': user_name,
            'nickname': user_nickname,
            'email': user_email,
            'zipcode': user_zipcode,
            'address1': user_address1,
            'address2': user_address2
        }

        let json = JSON.stringify(input_data);

        console.log(json);

        $.ajax({
            url: "/api/mypage/modify-user",
            data: json,
            method: "POST",
            contentType: "application/json; charset=utf-8",

            success: function (data) {
                alert("회원정보 수정이 완료되었습니다.")
                location.replace('/mypage/order-list')
            },
            error: function (request, status, error) {
                console.log("에러");
            },
            complete: function () {
                console.log("완료");
            }
        });
    }
});

// 마이페이지의 선택된 페이지의 값에 따라 선택 레이어 변경
$(document).ready(function () {
    let separation = $('#myPageId').text();
    console.log(separation);

    console.log($('#loginBtnUnSession').val());

    let idName = '';
    switch (separation) {
        case "ordered":
            idName = "#ordered";
            break;
        case "basket":
            idName = "#basket";
            break;
        case "delivery":
            idName = "#delivery";
            break;
        case "modify":
            idName = "#user-info";
            break;
        case "point":
            idName = "#point";
            break;
        case "unregister":
            idName = "#unregister";
            break;
    }

    $('.nav-menu').addClass('link-dark');
    $('.nav-menu').css({
        "background-color": "",
        "color": ""
    });
    $(idName).removeClass('link-dark');
    $(idName).css({
        "background-color": "rgba(83,146,91,0.8)",
        "color": "white"
    });

});

//// 공통 함수
const isEmpty=(str)=>{
    let obj = String(str);
    return obj === null || obj === undefined || obj === 'null' || obj === 'undefined' || obj === '';
}

const isNotEmpty=(str)=>{
    return !isEmpty(str);
}
