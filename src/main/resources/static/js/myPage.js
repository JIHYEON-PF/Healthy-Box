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
    });
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

// 배송정보 테이블 크기 조정
$(document).ready(function () {
    $('#registeredButtons').width($('#defaultButtons').width());
});
$(window).resize(function() {
    $('#registeredButtons').width($('#defaultButtons').width());
});

/** 주문내역 색상 조정 */
$(document).ready(function () {
    $('.orderListBody').each(function (index, item) {

        if (index % 2 === 0) {
            $(this).removeClass('orderListBodyEven');
            $(this).addClass('orderListBodyOdd');
        } else {
            $(this).removeClass('orderListBodyOdd');
            $(this).addClass('orderListBodyEven');
        }
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
