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

    let status = $("#orderStatus").text();
    let optionName = '';

    switch (option) {
        case 'cancel':
            optionName = '취소';
            break;
        case 'exchange':
            optionName = '교환';
            break;
        case 'return':
            optionName = '반품';
            break;
    }

    if (option === 'exchange' && (status === '취소' || status === '반품')) {
        alert(status + '된 주문은 ' + optionName + '할 수 없습니다.');
        return;
    } else if (option === 'cancel' && status !== '주문') {
        alert(status + '된 주문은 취소할 수 없습니다.\n교환 또는 반품으로 진행 부탁드립니다.');
        return;
    }

    let continueFlag = false;
    let msg = '';


    /** 결제 취소 정보 Iamport 서버 전송 api 실행 */
    let cancel_data = {
        /** 아임포트 필요 파라메터 목록
         * imp_uid : 취소할 거래의 아임포트 고유 번호
         * merchant_uid : 가맹점에서 전달한 거래 고유번호. imp_uid, merchant_uid 중 하나는 필수이어야 합니다. 두 값이 모두 넘어오면 imp_uid를 우선 적용합니다.
         * amount : 취소 요청 금액(누락이면 전액 취소) -> 정기구독 상품에서만 처리
         * tax_free : 취소 요청 금액 중 면세 금액 (누락되면 0원 처리)
         * vat_amount : 취소요청 금액 중 부가세 금액 (결제 시 부가세를 지정했던 경우 필수)
         * checksum : 취소 트랜잭션 수행 전, 현재 시점의 취소 가능한 잔액
         * reason : 취소 사유
         * ---------------------- 상태 변경 페이지에서 현금결제 여부에 따라 마크업 페이지 출력 변경 후 적용
         * refund_holder : 환불계좌 예금주(가상계좌 취소시 필수)
         * refund_bank : 환불계좌 은행 코드(가장계좌 취소시 필수) | 국민은행(04), SC제일은행(23), 경남은행(39), 광주은행(34), 기업은행(03), 농협(11), 대구은행(31), 부산은행(32), 산업은행(02), 새마을금고(45), 수협(07), 신한은행(88), 신협(48), 외환은행(05), 우리은행(20), 우체국(71), 전북은행(37), 축협(16), 카카오뱅크(90), 케이뱅크(89), 하나은행(서울은행)(81), 한국씨티은행(한미은행)(53)
         * refund_account : 환불계좌 계좌번호(가상계좌 취소시 필수)
         * refund_tel: 환불계좌 예금주 연락처(가장계좌 취소시 필수)
         *  */
        "merchantUid" : orderNo,
        "reason" : title,
        "refundHolder" : "",
        "refundBank" : "",
        "refundAccount" : "",
        "refundTel" : ""
    }
    let payment_json = JSON.stringify(cancel_data);

    if (option.toLowerCase() === 'cancel' || option.toLowerCase() === 'return') {
        $.ajax({
            url: "/iamport/cancel-payment",
            data: payment_json,
            method: "POST",
            contentType: "application/json; charset=utf-8",
            async: false,

            success: function (data) {
                console.log(data);
                if (data.code === 0) {
                    continueFlag = true;
                } else {
                    msg = "결제 취소에 실패하였습니다.\n실패 사유 : " + data.msg;
                }
            },
            error: function (request, status, error) {
                console.log('에러');
            },
            complete: function () {
                console.log('완료');
            }
        });
    }

    if (!continueFlag && (option.toLowerCase() === 'cancel' || option.toLowerCase() === 'return')) {
        alert(msg);
        return;
    }

    msg += '주문번호 [ ' + orderNo + ' ] 의 ';

    /** 주문 상태 DB값 변경 */
    let input_data = {
        'orderNo': orderNo,
        'sellerCode': '',
        'division': option,
        'title': title,
        'content': content
    }
    let status_json = JSON.stringify(input_data);

    $.ajax({
        url: "/api/order/change-status",
        data: status_json,
        method: "POST",
        contentType: "application/json; charset=utf-8",
        async: false,

        success: function (data) {
            switch (option) {
                case 'cancel':
                    msg += '취소신청이 완료되었습니다.';
                    continueFlag = true;
                    break;
                case 'exchange':
                    msg += '교환신청이 완료되었습니다.';
                    break;
                case 'return':
                    msg += '반품신청이 완료되었습니다.';
                    break;
            }
            alert(msg);
            location.replace("/mypage/order-list");
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
