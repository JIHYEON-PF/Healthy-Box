$(document).on("click", "#doOrder, #doSubscribeOrder", function () {
    let IMP = window.IMP;
    IMP.init('imp24005612');

    let pathname = $(location).attr('pathname').split('?')[0].split('/')[2];


    if (pathname === 'single-item') {
        IMP.request_pay({ // param
            pg: "html5_inicis",
            pay_method: $("input[name=method]:checked").attr('value'),
            merchant_uid: "ORD" + new Date().yyyyMMdd(),
            name: getItemName(),
            amount: $("#paymentDataSectAmount").text().replace(",", "").replace(" 원", ""),
            buyer_email: $("#payerDataSectEmail").text(),
            buyer_name: $("#payerDataSectName").text(),
            buyer_tel: $("#payerDataSectTel").text(),
            buyer_addr: $("#deliveryDataSectAddress1").text() + ' ' + $("#deliveryDataSectAddress2").text(),
            buyer_postcode: $("#deliveryDataSectZipcode").text()
        }, function (rsp) { // callback
            if (rsp.success) {
                // 결제 성공 시 로직,
                /**
                 * apply_num : "60809588"
                 * bank_name : null
                 * buyer_addr : "기본 주소 출력 부분 상세 주소 출력 부분"
                 * buyer_email : "test@email.com"
                 * buyer_name : "테스트이름"
                 * buyer_postcode : "00000"
                 * buyer_tel : "010-1111-1111"
                 * card_name : "BC카드"
                 * card_number : "910025*********6"
                 * card_quota : 0
                 * currency : "KRW"
                 * custom_data : null
                 * imp_uid  "imp_377628855057"
                 * merchant_uid : "ORD20221003-195712"
                 * name : "품명출력구역 외 1 개"
                 * paid_amount : 4000
                 * paid_at : 1664794670
                 * pay_method : "card"
                 * pg_provider : "html5_inicis"
                 * pg_tid : "StdpayISP_INIpayTest20221003195750102072"
                 * pg_type : "payment"
                 * receipt_url : "https://iniweb.inicis.com/DefaultWebApp/mall/cr/cm/mCmReceipt_head.jsp?noTid=StdpayISP_INIpayTest20221003195750102072&noMethod=1"
                 * status : "paid"
                 * success : true
                 * */

                let productCodes = [];

                $(".productDataSectName").each(function (i, e) {
                    let input_data = {
                        'orderNo': rsp.merchant_uid,
                        'orderIdx': i + 1,
                        'status': 0,
                        'userId': ($("#loginBtnUnSession").attr('value') === undefined ? '비회원' : $("#loginBtnUnSession").attr('value')),
                        'deliverIdx': $("#deliveryDataSectIdx").text(),
                        'productCode': $(this).closest('tr').find('.productDataSectProductCode').text(),
                        'sellerCode': $(this).closest('tr').find('.productDataSectSellerCode').text(),
                        'qty': $(this).closest('tr').find('.qtyInputBox').val(),
                        'unitCost': $(this).closest('tr').find('.productDataSectUnitCost').text().replace(',', '').replace(' 원', ''),
                        'dcCost': $(this).closest('tr').find('.productDataSectDcCost').text().replace(',', '').replace(' 원', ''),
                        'deliveryCost': $(this).closest('tr').find('.productDataSectDeliveryCost').text().replace(',', '').replace(' 원', ''),
                        'amount': $(this).closest('tr').find('.productDataSectAmount').text().replace(',', '').replace(' 원', ''),
                        'payMethod': rsp.pay_method,
                        'apiCode': rsp.imp_uid,
                        'deliveryComp': '',
                        'deliveryCode': '',
                        "cardName": rsp.card_name,
                        "cardNum": rsp.card_number,
                        "quota": rsp.card_quota,
                        "isSubscribe": "N"
                    }

                    productCodes.push(input_data.productCode);

                    $.ajax({
                        url: "/api/order/insert-order",
                        data: JSON.stringify(input_data),
                        method: "POST",
                        contentType: "application/json; charset=utf-8",
                        async: false,

                        success: function (data) {
                            console.log(input_data);
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
                });

                $.ajax({
                    url: "/api/basket/delete-basket?userId=" + ($("#loginBtnUnSession").attr('value') === undefined ? '비회원' : $("#loginBtnUnSession").attr('value')) + "&productCode=" + productCodes.toString(),
                    method: "DELETE",
                    async: false,

                    success: function (data) {
                        console.log("성공");
                    },
                    error: function (request, status, error) {
                        console.log("에러");
                    },
                    complete: function () {
                        console.log("완료");
                    }
                });

                alert("주문이 완료되었습니다.");
                location.replace("/mypage/order-list/single");
            } else {
                // 결제 실패 시 로직,
                console.log("결제 실패")
                console.log(rsp.error_msg)
            }
        });
    } else if (pathname === 'subscribe') {
        IMP.request_pay({ // param
            pg: "html5_inicis",
            pay_method: $("input[name=method]:checked").attr('value'),
            merchant_uid: "ORD" + new Date().yyyyMMdd(),
            name: $("#orderSubscribeTitle").attr('value'),
            amount: $("#dataSectOrderSubscribePaymentAmount").attr('value'),
            buyer_email: $("#dataSectOrderSubscribePayerEmail").text(),
            buyer_name: $("#dataSectOrderSubscribePayerName").text(),
            buyer_tel: $("#dataSectOrderSubscribePayerTel").text(),
            buyer_addr: $("#dataSectOrderSubscribeDeliveryAddress1").text() + ' ' + $("#dataSectOrderSubscribeDeliveryAddress2").text(),
            buyer_postcode: $("#dataSectOrderSubscribeDeliveryZipcode").text()
        }, function (rsp) { // callback
            if (rsp.success) {
                // 결제 성공 시 로직,
                /**
                 * apply_num : "60809588"
                 * bank_name : null
                 * buyer_addr : "기본 주소 출력 부분 상세 주소 출력 부분"
                 * buyer_email : "test@email.com"
                 * buyer_name : "테스트이름"
                 * buyer_postcode : "00000"
                 * buyer_tel : "010-1111-1111"
                 * card_name : "BC카드"
                 * card_number : "910025*********6"
                 * card_quota : 0
                 * currency : "KRW"
                 * custom_data : null
                 * imp_uid  "imp_377628855057"
                 * merchant_uid : "ORD20221003-195712"
                 * name : "품명출력구역 외 1 개"
                 * paid_amount : 4000
                 * paid_at : 1664794670
                 * pay_method : "card"
                 * pg_provider : "html5_inicis"
                 * pg_tid : "StdpayISP_INIpayTest20221003195750102072"
                 * pg_type : "payment"
                 * receipt_url : "https://iniweb.inicis.com/DefaultWebApp/mall/cr/cm/mCmReceipt_head.jsp?noTid=StdpayISP_INIpayTest20221003195750102072&noMethod=1"
                 * status : "paid"
                 * success : true
                 * */


                $(".dataSectSubscribeItemListName").each(function (i, e) {
                    let input_data = {
                        'orderNo': rsp.merchant_uid,
                        'orderIdx': i+1,
                        'status': 0,
                        'userId': ($("#loginBtnUnSession").attr('value') === undefined ? '비회원' : $("#loginBtnUnSession").attr('value')),
                        'deliverIdx': $("#dataSectOrderSubscribeDeliveryIdx").text(),
                        'productCode': $(this).closest('tr').find('.dataSectSubscribeItemListNameSelectOption').attr('value').split('|')[0],
                        'sellerCode': $(this).closest('tr').find('.dataSectSubscribeItemListSellerCode').text(),
                        'qty': $(this).closest('tr').find('.dataSectSubscribeQtyBox').val(),
                        'unitCost': $(this).closest('tr').find('.dataSectSubscribeItemListUnitCost').attr('value'),
                        // 'dcCost': $(this).closest('tr').find('.productDataSectDcCost').text().replace(',','').replace(' 원', ''),
                        'dcCost' : 0,
                        // 'deliveryCost': $(this).closest('tr').find('.productDataSectDeliveryCost').text().replace(',','').replace(' 원', ''),
                        'deliveryCost': 0,
                        'amount': $(this).closest('tr').find('.dataSectSubscribeItemListAmount').attr('value'),
                        'payMethod': rsp.pay_method,
                        'apiCode': rsp.imp_uid,
                        'deliveryComp': '',
                        'deliveryCode': '',
                        "cardName": rsp.card_name,
                        "cardNum": rsp.card_number,
                        "quota": rsp.card_quota,
                        "isSubscribe": "Y",
                        "subscribeCode": $('#orderSubscribeCode').text(),
                        "deliveryDate": $(this).closest('tr').find('.dataSectSubscribeItemListDeliveryDateDetail').val() + 'T00:00:00'
                    }


                    $.ajax({
                        url: "/api/order/insert-order",
                        data: JSON.stringify(input_data),
                        method: "POST",
                        contentType: "application/json; charset=utf-8",
                        async: false,

                        success: function (data) {
                            console.log(input_data);
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
                });

                $.ajax({
                    url: "/api/basket/delete-subscribe-basket?userId="+($("#loginBtnUnSession").attr('value') === undefined ? '비회원' : $("#loginBtnUnSession").attr('value'))+"&basketNo="+$(location).attr("href").split('=')[2],
                    method: "DELETE",
                    async: false,

                    success: function (data) {
                        console.log("성공");
                    },
                    error: function (request, status, error) {
                        console.log("에러");
                    },
                    complete: function () {
                        console.log("완료");
                    }
                });

                alert("주문이 완료되었습니다.");
                window.location = "/mypage/order-list/subscribe";
            } else {
                // 결제 실패 시 로직,
                console.log("결제 실패")
                console.log(rsp.error_msg)
            }
        });
    }
});

//수량 변경 버튼 클릭
$(document).on("click", ".changeQtyBtn", function () {
    let qty = Number($(this).closest('tr').find('.qtyInputBox').val());
    let btnFlag = $(this).attr('class').split(" ")[0];

    if (qty > 0 && btnFlag === 'decreaseQtyBtn') {
        qty = qty - 1;
    } else if (qty < 999 && btnFlag === 'increaseQtyBtn') {
        qty = qty + 1;
    }

    settingPrice($(this), qty);
});

$(document).on("input change", ".qtyInputBox", function () {
    settingPrice($(this), Number($(this).val()));
});

$(document).on("click", "#deliveryChangeBtn, #orderSubscribeDeliveryChange", function () {
    /** 단일상품 배송지 변경 */
    if ($(this).attr('id') === 'deliveryChangeBtn') {
        window.open('/order/order-delivery/single/' + $('#loginBtnUnSession').val(), '_blank', 'width=460, height=800, toolbar=yes, location=no, status=no, resizable=no, scrollbars=no');
    /** 정기배송 배송지 변경 */
    } else if ($(this).attr('id') === 'orderSubscribeDeliveryChange') {
        window.open('/order/order-delivery/subscribe/' + $('#loginBtnUnSession').val(), '_blank', 'width=460, height=800, toolbar=yes, location=no, status=no, resizable=no, scrollbars=no');
    }
});

$(document).on("click", '.deliverySelect', function() {

    let pathname = $(location).attr('pathname').split('/')[3]

    /** 주소 데이터 준비 */
    let address = $(this).closest('tbody').find('.deliveryAddress').attr('value').split("^");
    let deliveryIdx = $(this).closest('tbody').find('.deliveryIdx').text();
    let deliveryName = $(this).closest('tbody').find('.deliveryName').text();

    /** 단일 상품 세팅 */
    if (pathname === 'single') {
        opener.$('#deliveryDataSectIdx').text(deliveryIdx);     /* idx */
        opener.$('#deliveryDataSectName').text(deliveryName);   /* name */
        opener.$('#deliveryDataSectZipcode').text(address[0]);  /* zipcode */
        opener.$('#deliveryDataSectAddress1').text(address[1]); /* address1 */
        opener.$('#deliveryDataSectAddress2').text(address[2]); /* address2 */
    }
    /** 정기배송 상품 세팅 */
    else if (pathname === 'subscribe') {
        opener.$('#dataSectOrderSubscribeDeliveryIdx').text(deliveryIdx);     /* idx */
        opener.$('#dataSectOrderSubscribeDeliveryName').text(deliveryName);   /* name */
        opener.$('#dataSectOrderSubscribeDeliveryZipcode').text(address[0]);  /* zipcode */
        opener.$('#dataSectOrderSubscribeDeliveryAddress1').text(address[1]); /* address1 */
        opener.$('#dataSectOrderSubscribeDeliveryAddress2').text(address[2]); /* address2 */
    }
    window.close();
});

const pad=(number, length)=> {
    let str = '' + number;
    while (str.length < length) {
        str = '0' + str;
    }
    return str;
}

Date.prototype.yyyyMMdd = function () {
    let yyyy = this.getFullYear().toString();
    let MM = pad(this.getMonth()+1, 2);
    let dd = pad(this.getDate(), 2);
    let hh = pad(this.getHours(), 2);
    let mm = pad(this.getMinutes(), 2);
    let ss = pad(this.getSeconds(), 2);

    return yyyy+MM+dd+'-'+hh+mm+ss;
}

Date.prototype.yyyyMMddFormat = function () {
    let yyyy = this.getFullYear().toString();
    let MM = pad(this.getMonth() + 1, 2);
    let dd = pad(this.getDate(), 2);

    return yyyy + '-' + MM + '-' + dd;
}

const getItemName=()=>{
    let index = -1;
    let itemName = '';
    $(".productDataSectName").each(function (i, e) {
        if (i === 0) {
            index = i;
            itemName = $(this).text();
        } else if (i > 0) {
            index = i;
        }
    })
    return index > 0 ? itemName + ' 외 ' + index + ' 개' : itemName;
}

const settingPrice=(selector, qty)=> {
    selector.closest('tr').find('.qtyInputBox').val(qty);

    let amount = qty * selector.closest('tr').find('.productDataSectUnitCost').attr('value');
    selector.closest('tr').find('.productDataSectAmount').text(String(amount).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
    selector.closest('tr').find('.productDataSectAmount').attr('value', amount);

    let codes = [];
    $(".productDataSectProductCode").each(function () {
        let productCode = $(this).text();
        let sellerCode = $(this).closest('tr').find('.productDataSectSellerCode').text();
        let qty = $(this).closest('tr').find('.qtyInputBox').val();
        codes.push(productCode+'-'+sellerCode+'-'+qty);
    })

    $.ajax({
        url: "/api/order/setting-price/" + codes,
        method: "GET",
        success: function (data) {
            $("#paymentDataSectUnitPrice").text(String(data[0]).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
            $("#paymentDataSectDeliveryCost").text(String(data[1]).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
            $("#paymentDataSectDiscountPrice").text(String(data[2]).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
            $("#paymentDataSectAmount").text(String(data[3]).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
        },
        error: function (request, status, error) {
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    });
}

/** 정기배송 관련 js */
/** 품목 변경 세팅 */
$(document).on("change", ".dataSectSubscribeItemListNameSelect", function () {
    let productCode = $(this).val().split('|')[0];
    let price = $(this).val().split('|')[1]
    let qty = $(this).closest('tr').find('.dataSectSubscribeQtyBox').val();

    $(this).closest('tr').find('.dataSectSubscribeItemListImg img').attr('src', '/image/products/' + productCode + '.jpg');
    $(this).closest('tr').find('.dataSectSubscribeItemListUnitCost').attr('value', price).text(String(price).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
    $(this).closest('tr').find('.dataSectSubscribeItemListAmount').attr('value', qty * price).text(String(qty * price).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');

    setPaymentPrice();
});

/** 수량 버튼 변경 */
$(document).on("click", ".dataSectChangeQty", function () {
    let selector = $(this).attr('class').split(' ')[0];
    let qty = $(this).closest('td').find('.dataSectSubscribeQtyBox').val();

    if (selector === 'dataSectSubscribeDecreaseQtyBtn' && qty > 0) {
        --qty;
    } else if (selector === 'dataSectSubscribeIncreaseQtyBtn' && qty < 999) {
        ++qty;
    }

    $(this).closest('td').find('.dataSectSubscribeQtyBox').val(qty);

    let unitCost = $(this).closest('tr').find('.dataSectSubscribeItemListUnitCost').attr('value');
    $(this).closest('tr').find('.dataSectSubscribeItemListAmount').attr('value', unitCost * qty).text(String(qty * unitCost).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');

    setPaymentPrice();
});

/** inputBox 수량 변경 */
$(document).on("change keyup paste", ".dataSectSubscribeQtyBox", function () {
    if ($.trim($(this).val()) === '') {
        $(this).val(0)
    }

    if ($(this).val().length > 1) {
        while ($(this).val().charAt(0) === '0') {
            $(this).val($(this).val().substring(1, $(this).val().length))
        }
    }

    let unitCost = $(this).closest('tr').find('.dataSectSubscribeItemListUnitCost').attr('value');
    $(this).closest('tr').find('.dataSectSubscribeItemListAmount').attr('value', $(this).val() * unitCost).text(String($(this).val() * unitCost).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');

    setPaymentPrice();
});

/** 배송일자 유효성 검사 */
$(document).on("change", '.dataSectSubscribeItemListDeliveryDateDetail', function () {
    let now = new Date().yyyyMMddFormat();
    let deliveryDate = $(this).val();

    if (now >= deliveryDate) {
        alert("정기배송 일자는 주문일자 이후의 날짜로 선택 가능합니다.");

        now = new Date(now);
        now.setDate(now.getDate() + 1);

        $(this).val(new Date(now).yyyyMMddFormat());
    }
});

/** 결제정보 금액 세팅 */
const setPaymentPrice = () => {
    let totalAmount = 0;
    $(".dataSectSubscribeItemListName").each(function () {
        totalAmount += Number($(this).closest('tr').find('.dataSectSubscribeItemListAmount').attr('value'));
    });

    $('#dataSectOrderSubscribePaymentAmount').attr('value', totalAmount).text(String(totalAmount).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
}
/** 배송정보 변경 >> 단일상품 주문에 분기값 추가하여 처리 */
/** 결제하기 >> 단일상품 주문에 분기값 추가하여 처리 */
