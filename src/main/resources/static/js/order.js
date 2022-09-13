$(document).on("click",".btnOrderDetail", function () {
    console.log($(this).closest('tr').find('.orderNo').text());
    location.href='/mypage/order-list/' + $(this).closest('tr').find('.orderNo').text();
});