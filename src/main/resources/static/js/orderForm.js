
$(document).on("click", "#doOrder", function () {
    let IMP = window.IMP;
    IMP.init('imp24005612');

    IMP.request_pay({ // param
        pg: "html5_inicis",
        pay_method: $("input[name=method]:checked").attr('value'),
        merchant_uid: "ORD" + new Date().yyyyMMdd(),
        name: getItemName(),
        amount: $("#paymentDataSectAmount").text().replace(",","").replace(" 원", ""),
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
                    'orderIdx': i+1,
                    'status': 0,
                    'userId': ($("#loginBtnUnSession").attr('value') === undefined ? '비회원' : $("#loginBtnUnSession").attr('value')),
                    'deliverIdx': $("#deliveryDataSectIdx").text(),
                    'productCode': $(this).closest('tr').find('.productDataSectProductCode').text(),
                    'sellerCode': $(this).closest('tr').find('.productDataSectSellerCode').text(),
                    'qty': $(this).closest('tr').find('.qtyInputBox').val(),
                    'unitCost': $(this).closest('tr').find('.productDataSectUnitCost').text().replace(',','').replace(' 원', ''),
                    'dcCost': $(this).closest('tr').find('.productDataSectDcCost').text().replace(',','').replace(' 원', ''),
                    'deliveryCost': $(this).closest('tr').find('.productDataSectDeliveryCost').text().replace(',','').replace(' 원', ''),
                    'amount': $(this).closest('tr').find('.productDataSectAmount').text().replace(',','').replace(' 원', ''),
                    'payMethod': rsp.pay_method,
                    'apiCode': rsp.imp_uid,
                    'deliveryComp': '',
                    'deliveryCode': '',
                    "cardName": rsp.card_name,
                    "cardNum": rsp.card_number,
                    "quota": rsp.card_quota
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
                url: "/api/basket/delete-basket?userId="+($("#loginBtnUnSession").attr('value') === undefined ? '비회원' : $("#loginBtnUnSession").attr('value'))+"&productCode="+productCodes.toString(),
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

$(document).on("click", "#deliveryChangeBtn", function () {
    window.open('/order/order-delivery/' + $('#loginBtnUnSession').val(), '_blank', 'width=460, height=800, toolbar=yes, location=no, status=no, resizable=no, scrollbars=no');
});

$(document).on("click", '.deliverySelect', function() {
    /** 주소 준비 */
    let address = $(this).closest('tbody').find('.deliveryAddress').attr('value').split("^");
    /** 배송주소 idx 세팅 */
    opener.$('#deliveryDataSectIdx').text($(this).closest('tbody').find('.deliveryIdx').text());
    /** 배송주소 명칭세팅 */
    opener.$('#deliveryDataSectName').text($(this).closest('tbody').find('.deliveryName').text());

    /** 준비한 주소 부모 페이지에 세팅 */
    opener.$('#deliveryDataSectZipcode').text(address[0]);
    opener.$('#deliveryDataSectAddress1').text(address[1]);
    opener.$('#deliveryDataSectAddress2').text(address[2]);
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