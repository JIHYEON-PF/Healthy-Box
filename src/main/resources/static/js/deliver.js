/** 저장버튼 */
$(document).on("click", "#insertBtn", function () {
    let userId = $('#userIdHidden').text();
    console.log(userId);
    let delivery_name = $('#deliverInfoName').val();
    let flag = $(location).attr('pathname').indexOf('deliverInfoDetail')

    /** 배송지 이름을 입력하지 않을 경우 이름을 자동으로 배정 */
    if ($.trim(delivery_name) === '') {
        $.ajax({
            url: "/api/deliver/get-count/" + userId,
            method: "GET",
            async: false,
            success: function (data) {
                delivery_name = '등록 배송지 ' + data;
            },
            error: function (request, status, error) {
                console.log("에러");
            },
            complete: function () {
                console.log("완료");
            }
        });
    }

    /** 데이터 DB 등록 */
    let delivery_zipcode = $('#deliverInfoZipcode').val();
    let delivery_address1 = $('#deliverInfoAddress1').val();
    let delivery_address2 = $('#deliverInfoAddress2').val();
    let delivery_idx = null;

    if (flag >= 0) {
        delivery_idx = $('#insertBtn').val();
    }

    if (delivery_zipcode !== '' && delivery_address1 !== '') {
        let inform_data = {
            'idx':delivery_idx,
            'deliveryName':delivery_name,
            'zipcode':delivery_zipcode,
            'address1':delivery_address1,
            'address2':delivery_address2,
            'userId':userId,
            'deliveryFlag':1
        }
        let json_data = JSON.stringify(inform_data);

        if (flag < 0) {
            $.ajax({
                url: "/api/deliver/register",
                data: json_data,
                method: "POST",
                async: false,
                contentType: "application/json; charset=utf-8",

                success: function (data) {
                    console.log(json_data);
                    alert('배송지 등록이 완료되었습니다.');
                    window.open('about:blank', '_self').close();
                    window.opener.location.reload();
                },
                error: function (request, status, error) {
                    console.log("에러");
                },
                complete: function () {
                    console.log("완료");
                }
            });
        } else {
            $.ajax({
                url: "/api/deliver/modify",
                data: json_data,
                method: "PUT",
                async: false,
                contentType: "application/json; charset=utf-8",

                success: function (data) {
                    console.log(json_data);
                    alert('배송지 수정이 완료되었습니다.');
                    window.open('about:blank', '_self').close();
                    window.opener.location.reload();
                },
                error: function (request, status, error) {
                    console.log("에러");
                },
                complete: function () {
                    console.log("완료");
                }
            });
        }
    } else {
        alert('우편번호 또는 기본 주소를 입력해주시기 바랍니다.');
    }
});

/** 취소버튼 */
$(document).on("click", "#cancelBtn", function () {
    window.open('about:blank', '_self').close();
});

/** 배송지 등록 팝업 등록 후 페이지 리로드 */
function openPopup() {
    window.open('/mypage/deliverInfo', 'Healthy-Box', 'width=460, height=240, toolbar=yes, location=no, status=no, resizable=no, scrollbars=no');
}

/** 배송지 수정 팝업 등록 후 페이지 리로드 */
$(document).on("click", ".modifyBtn", function () {
    window.open('/mypage/deliverInfoDetail?userId=' + $('#loginBtnUnSession').val() + '&idx=' + $(this).attr('value'), 'Healthy-Box', 'width=460, height=240, toolbar=yes, location=no, status=no, resizable=no, scrollbars=no');
});

/** 기본배송지 해제 */
$(document).on("click", "#unDefaulting", function () {
    if ($('#defaultName').text() ==='등록정보 없음') {
        alert('기본 배송지로 지정된 내역이 없습니다.');
        return;
    }
    $.ajax({
        url: "/api/deliver/undefault/" + $('#loginBtnUnSession').val(),
        method: "PATCH",

        success: function (data) {
            alert('기본 배송지가 해제되었습니다.');
            location.reload();
        },
        error: function (request, status, error) {
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    });
});

/** 기본 배송지로 지정 */
$(document).on("click", ".doDefaulting", function () {
    let idx = $(this).val();
    let userId = $('#loginBtnUnSession').val();

    $.ajax({
        url: "/api/deliver/dodefault?userId=" + userId + '&idx=' + idx,
        method: "PATCH",

        success: function (data) {
            alert('기본 배송지로 지정되었습니다.');
            location.reload();
        },
        error: function (request, status, error) {
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    });
});

/** 기본배송지 수정 */
$(document).on("click", "#insertBtn", function () {
    let idx = $(this).val()
    console.log(idx)
});


/** 기본배송지 삭제 */
$(document).on("click", ".deleteBtn", function () {
    if ($(this).val() === '0') {
        alert('저장된 배송지 내역이 없습니다.');
        return;
    }
    if (confirm('배송지를 삭제하시겠습니까?') === true) {
        $.ajax({
            url: "/api/deliver/delete?userId=" + $('#loginBtnUnSession').val() + '&idx=' + $(this).val(),
            method: "DELETE",

            success: function (data) {
                alert("배송지가 삭제되었습니다.");
                location.reload();
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
