/** 선택 부분 전체 선택 및 전체 해제 기능부 */
$(document).on("click", "#allCheck", function () {
    if ($("#allCheck").prop("checked")) {
        $("input[type=checkbox]").prop("checked", true);
    } else
        $("input[type=checkbox]").prop("checked", false);
});

/** 결제 금액 표시 부분 */
$(document).ready(function() {
    let totalWon = 0; // 배송비 계산을 위한 동일 판매자 코드의 총 금액
    let sellerCode = ''; // 배송비 계산을 위한 판매자 코드
    let deliveryCost = 0; // 배송비
    let ifDeliveryCostAdded = 0; //0 : 배송비가 부가되지 않은 상태, 1 : 배송비가 부가되고 변경되어야 하는 상태, -1 : 배송비가 부가되고 변경되면 안되는 상태
    let allAmount = 0; // 결제금액 레이어에서 금액을 출력하기 위한 금액
    $('#basketTableBody').find('.dataSectSellerCode').each(function (i, e) { //반복문 돌면서 클래스명 확인

        allAmount += Number($(this).closest('tr').find('.dataSectAmount').attr('value'));

        if (sellerCode !== $(this).text()) {
            sellerCode = $(this).text();
            totalWon = Number($(this).closest('tr').find('.dataSectAmount').attr('value'));
            ifDeliveryCostAdded = 0;
        } else {
            totalWon += Number($(this).closest('tr').find('.dataSectAmount').attr('value'));
        }

        if (ifDeliveryCostAdded === 0 && totalWon >= 50000) {
            ifDeliveryCostAdded = -1;
        } else if (ifDeliveryCostAdded === 0 && totalWon < 50000) {
            deliveryCost += Number(3000);
            ifDeliveryCostAdded = 1;
        } else if (ifDeliveryCostAdded === 1 && totalWon >= 50000) {
            deliveryCost -= Number(3000);
            ifDeliveryCostAdded = -1;
        } else if (ifDeliveryCostAdded === -1) {
            return true;
        }


    });
    console.log(deliveryCost);
    console.log(allAmount);
    console.log(allAmount + deliveryCost);
})