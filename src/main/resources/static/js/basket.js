let userId = $('#loginBtnUnSession').attr('value');

/** 선택 부분 전체 선택 및 전체 해제 기능부 */
$(document).on("click", "#allCheck", function () {
    if ($("#allCheck").prop("checked")) {
        $("input[type=checkbox]").prop("checked", true);
    } else
        $("input[type=checkbox]").prop("checked", false);
});

/** 수량 변경 버튼 클릭 이벤트 */
$(document).on("click", ".dataSectDecreaseQtyBtn", function () {
    let qty = $(this).closest('tr').find('.dataSectQtyBox').val();
    if (qty > 0)
        qty -= 1;
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

/** 선택 삭제 */
$(document).on("click", "#btnDeleteSelected", function () {
    let userId = $('#loginBtnUnSession').attr('value');
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
