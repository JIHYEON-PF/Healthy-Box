$(document).on("click",".btnOrderDetail", function () {
    location.href='/mypage/order-list/' + $(this).closest('tr').find('.orderNo').text();
});

$(document).on("click", "#deleteOrderDetail", function () {
    let orderNo = $(location).attr('pathname').split('/')[3];
    let userId = $('#loginBtnUnSession').attr('value')

    $.ajax({
        url: "/api/order/deleteOrder?userId="+userId+"&orderNo="+orderNo,
        method: "DELETE",
        success: function (data) {
            alert('주문내역이 삭제되었습니다.');
            location.replace('/mypage/order-list');
        },
        error: function (request, status, error) {
            alert('주문내역 삭제에 실패하였습니다.');
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    });
});

$(document).on("click", "#changeStatus", function () {
    location.replace("/mypage/change-status/"+$(location).attr('pathname').split('/')[3]);
});

$(document).on("click", "#searchDeliveryInfo", function () {
    let code = $('#deliveryCompData').attr('value');
    let invoice = $('#deliveryCodeData').text();
    if (code !== '' && invoice !== '') {
        window.open('/mypage/tracking?code='+code+'&invoice='+invoice, 'Healthy-Box', 'width=460, height=680, toolbar=yes, location=no, status=no, resizable=no, scrollbars=no');
    } else {
        alert("배송정보가 등록되지 않았습니다.\n업체에 문의해보시기 바랍니다.");
    }
});

$(document).on("click", "#changeStatusDetail", function () {
    let option = $('#changeStatusOptions').val();
    let title = $('#changeStatusTitle').val();
    let content = $('#changeStatusContent').val();
    let orderNo = $(location).attr('pathname').split('/')[3];

    let input_data = {
        'orderNo': orderNo,
        'sellerCode': '',
        'division': option,
        'title': title,
        'content': content
    }

    let json = JSON.stringify(input_data);

    $.ajax({
        url: "/api/order/change-status",
        data: json,
        method: "POST",
        contentType: "application/json; charset=utf-8",

        success: function (data) {
            switch (option) {
                case 'cancel':
                    alert('취소신청이 완료되었습니다.');
                    break;
                case 'exchange':
                    alert('교환신청이 완료되었습니다.');
                    break;
                case 'return':
                    alert('반품신청이 완료되었습니다.');
                    break;
            }
            console.log('성공');
        },
        error: function (request, status, error) {
            alert('주문 상태 변경에 실패했습니다.\n관리자에게 문의해주시기 바랍니다.');
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    });
});
