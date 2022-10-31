let userId = $('#loginBtnUnSession').attr('value');

// 선택 부분 전체 선택 및 전체 해제 기능부 
$(document).on("click", "#allCheck", function () {
    if ($("#allCheck").prop("checked")) {
        $("input[type=checkbox]").prop("checked", true);
    } else
        $("input[type=checkbox]").prop("checked", false);
});

// 수량 변경 버튼 클릭 이벤트 
$(document).on("click", ".dataSectDecreaseQtyBtn", function () {
    let qty = $(this).closest('tr').find('.dataSectQtyBox').val();
    if (qty > 0) {
        qty -= 1;
    }
    $(this).closest('tr').find('.dataSectQtyBox').val(qty);

    let unitCost = $(this).closest('tr').find('.dataSectUnitCost').attr('value');
    let amount = qty * unitCost;
    $(this).closest('tr').find('.dataSectAmount').text(String(amount).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');

    updateQty(qty, $(this).closest('tr').find('.dataSectProdCode').text());
    setPrice();
});

$(document).on("click", ".dataSectIncreaseQtyBtn", function () {
    let qty = Number($(this).closest('tr').find('.dataSectQtyBox').val());
    if (qty < 999)
        qty += 1;
    $(this).closest('tr').find('.dataSectQtyBox').val(qty);

    let unitCost = $(this).closest('tr').find('.dataSectUnitCost').attr('value');
    let amount = qty * unitCost;
    $(this).closest('tr').find('.dataSectAmount').text(String(amount).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');

    updateQty(qty, $(this).closest('tr').find('.dataSectProdCode').text());
    setPrice();
});

$(document).on("input change keyup paste", ".dataSectQtyBox", function () {
    let qty = $(this).val()
    let unitCost = $(this).closest('tr').find('.dataSectUnitCost').attr('value');
    let amount = qty * unitCost;
    $(this).closest('tr').find('.dataSectAmount').text(String(amount).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');

    updateQty(qty, $(this).closest('tr').find('.dataSectProdCode').text());
    setPrice();
});

// 선택 삭제 
$(document).on("click", "#btnDeleteSelected", function () {
    let itemCodes = [];
    $("input[name=itemCheckBox]:checked").each(function() {
        itemCodes.push($(this).val());
    });

    $.ajax({
        url: "/api/basket/delete-basket?userId="+userId+"&productCode="+itemCodes.toString(),
        method: "DELETE",

        success: function (data) {
            alert("선택하신 상품이 삭제되었습니다.");
            location.reload();
            console.log("성공");
        },
        error: function (request, status, error) {
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    });
});

//선택주문
$(document).on("click",".btnOrder", function () {
    let selector = $(this).attr('id');

    if (selector === 'btnOrderAll') {
        $("input[type=checkbox]").prop("checked", true);
    }

    let codes = []

    $('.itemCheck').each(function () {
        if ($(this).prop("checked")) {
            codes.push(
                $(this).closest('tr').find('.dataSectProdCode').text() + '-' +
                $(this).closest('tr').find('.dataSectSellerCode').text() + '-' +
                $(this).closest('tr').find('.dataSectQtyBox').val()
            );
        }
    });

    $(location).attr("pathname", "/order/single-item/" + codes);
});

/** 정기배송 리스트 js */
/** 주문 삭제 */
$(document).on("click", ".btnDeleteSubscribeBasket", function () {
    let basketNo = $(this).closest('tr').find('.dataSectBasketNo').text();
    let userId = $("#loginBtnUnSession").attr("value");

    $.ajax({
        url: "/api/basket/delete-subscribe-basket?userId="+userId+"&basketNo="+basketNo,
        method: "DELETE",

        success: function (data) {
            if (data) {
                location.reload();
            }
        },
        error: function (request, status, error) {
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    });
});

/** 리스트 - 주문 */
$(document).on("click", ".btnOrderSubscribeBasket", function () {
    let basketNo = $(this).closest("tr").find(".dataSectBasketNo").text();
    let userId = $("#loginBtnUnSession").attr("value");
    window.location = "/order/subscribe?userId=" + userId + "&basketNo=" + basketNo;
});


/** 정기배송 디테일 관련 js */
/** 정기배송 리스트 > 디테일로 이동 작업 */
$(document).on("click", ".btnDetailInform", function () {
    let basketNo = $(this).closest('tr').find('.dataSectBasketNo').text();
    $(location).attr("pathname", "/mypage/basket/subscribe/" + basketNo);
});

/** 디테일 - 수량 변경 버튼 관련 조정 */
$(document).on("click", ".dataSectChangeSubscribeQty", function () {
    let selector = $(this).attr('class').split(' ')[0];
    let qty = Number($(this).closest('td').find('.dataSectSubscribeQtyBox').val());

    if (selector === 'dataSectSubscribeDecreaseQtyBtn' && qty > 0) {
        qty -= Number(1);
    } else if (selector === 'dataSectSubscribeIncreaseQtyBtn' && qty < 999) {
        qty += Number(1);
    }

    $(this).closest('td').find('.dataSectSubscribeQtyBox').val(qty);

    let unitCost = $(this).closest('tr').find('.dataSectSubscribeDetailUnitCost').attr('value');
    $(this).closest('tr').find('.dataSectSubscribeDetailAmount').attr('value', qty * unitCost).text(String(qty * unitCost).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');

    calcDetailBasketPrice();
});

/** 디테일 - 수량 직접 수정 */
$(document).on("change keyup paste", ".dataSectSubscribeQtyBox", function () {
    if ($(this).val() === '') {
        $(this).val(0)
    }

    if ($(this).val().length > 1) {
        while ($(this).val().charAt(0) === '0') {
            $(this).val($(this).val().substring(1, $(this).val().length))
        }
    }

    let unitCost = $(this).closest('tr').find('.dataSectSubscribeDetailUnitCost').attr('value');
    $(this).closest('tr').find('.dataSectSubscribeDetailAmount').attr('value', $(this).val() * unitCost).text(String($(this).val() * unitCost).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');

    calcDetailBasketPrice();
});

/** 디테일 - 품목 변경시 데이터 변경 */
$(document).on("change", ".dataSectSubscribeDetailNameSelect", function () {
    let productCode = $(this).val().split('|')[0];
    let price = $(this).val().split('|')[1];
    let qty = $(this).closest('tr').find('.dataSectSubscribeQtyBox').val();

    $(this).closest('tr').find('.dataSectSubscribeDetailImg img').attr('src', '/image/products/' + productCode + '.jpg');
    $(this).closest('tr').find('.dataSectSubscribeDetailUnitCost').attr('value', price).text(String(price).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
    $(this).closest('tr').find('.dataSectSubscribeDetailAmount').attr('value', qty * price).text(String(qty * price).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');

    calcDetailBasketPrice();
});

/** 디테일 - 확인버튼 */
$(document).on("click", "#btnConfirmSubscribeModify", function () {
    /**
     * 1. 장바구니 번호를 바탕으로 subscribeCode를 찾아서 변수로 지정
     * 2. 서버로 전송할 객체 데이터 json타입으로 형성
     *  필요 데이터
     *      basketNo, userId, subscribeCode, productCode, sellerCode, productIdx, qty, deliveryDate
     * ( deliveryDate의 경우 형변환이 필요함 )
     * */

    let basketNo = $(location).attr('pathname').split('/')[4];
    let userId = $("#loginBtnUnSession").attr('value');
    let subscribeCode = '';
    /** subscribeCode 찾기 */
    $.ajax({
        url: "/api/basket/find-subscribe-code?userId="+userId+"&basketNo="+basketNo,
        method: "GET",
        async: false,

        success: function (data) {
            subscribeCode = data;
        },
        error: function (status, request, error) {
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    });

    let errCnt = 0;

    /** 장바구니 데이터 각 데이터 로우마다 개별 처리 */
    $(".dataSectSubscribeDetailName").each(function () {
        /** 객체 생성 후 데이터 전달 */
        let modify_data = {
            "basketNo" : basketNo,
            "userId" : userId,
            "subscribeCode" : subscribeCode,
            "productCode" : $(this).closest('tr').find('.dataSectSubscribeDetailNameSelect').val().split('|')[0],
            "sellerCode" : $(this).closest('tr').find('.dataSectSubscribeDetailSellerCode').text(),
            "productIdx" : $(this).closest('tr').find('.dataSectSubscribeDetailIdx').text(),
            "qty" : $(this).closest('tr').find('.dataSectSubscribeQtyBox').val(),
            "deliveryDate" : $(this).closest('tr').find('.dataSectSubscribeDetailModifyDate').val() + 'T00:00:00'
        }


        let json_data = JSON.stringify(modify_data);

        $.ajax({
            url: "/api/basket/modify-subscribe-basket",
            data: json_data,
            method: "put",
            contentType: "application/json; charset=utf-8",
            async: false,

            success: function (data) {
                if (!data) {
                    console.log("에러");
                    ++errCnt;
                }
            },
            error: function (request, status, error) {
                ++errCnt;
                console.log("에러");
            },
            complete: function () {
                console.log("완료");
            }
        });
    });

    if (errCnt > 0) {
        alert(String(errCnt) + " 개의 항목 수정에 실패했습니다.\n관라자에게 문의해주시기 바랍니다.");
    } else {
        alert("장바구니 품목 수정이 완료되었습니다.");
        $(location).attr("pathname", "/mypage/basket/subscribe");
    }


});


const updateQty=(qty, productCode)=>{
    $.ajax({
        url: "/api/basket/update-qty?userId=" + userId + "&productCode=" + productCode + "&qty=" + qty,
        method: "PATCH",
        async: false,

        success: function (data) {
            console.log("성공")
        },
        error: function (request, status, error) {
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    })
}

const setPrice=()=>{
    $.ajax({
        url: "/api/basket/price-info/"+$('#loginBtnUnSession').attr('value'),
        method: "GET",
        async: false,

        success: function (data) {
            $('#basketPriceAmount').text(String(data[0]).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
            $('#basketPriceDeliveryCost').text(String(data[1]).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
            $('#basketPriceTotal').text(String(data[0]+data[1]).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
        },
        error: function (request, status, error) {
            console.log("에러");
        },
        complete: function () {
            console.log("완료");
        }
    })
}

/** 디테일 - 장바구니 금액 계산 */
const calcDetailBasketPrice = () => {
    let tUnitCost = 0;
    let tAmount = 0;
    $(".dataSectSubscribeDetailName").each(function () {
        tUnitCost += Number($(this).closest('tr').find('.dataSectSubscribeDetailUnitCost').attr('value'));
        tAmount += Number($(this).closest('tr').find('.dataSectSubscribeDetailAmount').attr('value'));
    });

    if (tAmount >= 50000) {
        $("#dataSectSubscribeDetailPriceDeliveryCost").attr('value',0).text('0 원');
    } else {
        $("#dataSectSubscribeDetailPriceDeliveryCost").attr('value',3000).text(String(3000).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
    }

    $("#dataSectSubscribeDetailPriceUnitCost").attr('value', tUnitCost).text(String(tUnitCost).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
    $("#dataSectSubscribeDetailPriceAmount").attr('value', tAmount).text(String(tAmount).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원');
}
