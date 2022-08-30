// 비밀번호 확인
$(document).on("click", "#confirmBtn", function () {
    let input_password = $('#input_pw').val();

    if (isNotEmpty(input_password)) {
        $.ajax({
            url: "/api/mypage/check-pw/" + input_password,
            method: "POST",
            success: function (data) {
                if (data) {
                    location.replace("/mypage/modify-user");
                } else {
                    alert("비밀번호가 일치하지 않습니다.");
                    $('#input_password').focus();
                }
            },
            error: function (request, status, error) {
                console.log("에러");
                console.log(JSON.parse(request.responseText));
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

        $.ajax({
            url: "/api/mypage/modify-user",
            data: json,
            method: "POST",
            contentType: "application/json; charset=utf-8",

            success: function (data) {
                alert("회원정보 수정이 완료되었습니다.")
                location.replace('/mypage/')
            },
            error: function (request, status, error) {
                console.log("에러");
                let errorLog = JSON.parse(request.responseText);
                console.log(errorLog.message())
            },
            complete: function () {
                console.log("완료");
            }
        });
    }
});

//// 포인트 관리
//포인트 발생금액 색상 조정
$(document).ready(function () {
    let color = "black"; //기본 색상을 검정색으로 지정
    $('tbody').find('td').each(function (i, e) { //반복문 돌면서 클래스명 확인
        let className = ""; //클래스 명 조정
        // 클래스가 복수개 있는지 확인하고 제일 앞에 있는 클래스만 가져옴
        if (e.className.indexOf(" ") > 0 ) {
            className = e.className.substring(0, e.className.indexOf(" "));
        } else {
            className = e.className;
        }

        // 클래스 명 판단하여 색상 지정
        if (className === "isExpired") {
            if ($(this).text() === "Y") {
                color = "red";
            } else {
                color = "black";
            }
        }


        // 색상을 지정해주고 지정된 색상에 따라 빨간색일 경우 앞에 문자열 '-' 추가해줌
        if (className === "pointValue") {
            $(this).css("color", color);
            if ($(this).css("color") === "rgb(255, 0, 0)") {
                $(this).text("-"+$(this).text());
            }
        }
    })
    // if ($.trim($('.isExpired').text()) === "Y") {
    //     console.log("asdf");
    //     $(".pointValue").css("color","red");
    // } else {
    //     $(".pointValue").css("color", "black");
    // }
});

//// 공통 함수
const isEmpty=(str)=>{
    let obj = String(str);
    return obj === null || obj === undefined || obj === 'null' || obj === 'undefined' || obj === '';
}

const isNotEmpty=(str)=>{
    return !isEmpty(str);
}
