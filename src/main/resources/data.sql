insert into tbl_div_product (product_group, product_category, group_name, category_name, is_used)
values ('001', '001', '정기구독','도시락', 'Y'), -- 정기구독 - 도시락
('001', '002', '정기구독', '샐러드', 'Y'), -- 정기구독 - 샐러드
('001', '003', '정기구독', '건강식품', 'Y'), -- 정기구독 - 건강식품
('002', '001', '도시락', '일반 도시락', 'Y'), -- 도시락 - 일반 도시락
('002', '002', '도시락', '다이어트 도시락','Y'), -- 도시락 - 다이어트 도시락
('003', '001', '샐러드', '샐러드','Y'), -- 샐러드 - 샐러드
('004', '001', '샌드위치', '샌드위치','Y'), -- 샌드위치 - 샌드위치
('005', '001', '건강식품', '건강식품','Y'), -- 건강식품 - 건강식품
('006', '001', '간식', '간식','Y'), -- 간식 - 간식
('007', '007', '음료', '음료','Y'); -- 음료 - 음료

insert into tbl_subscribe_products (product_category, product_group, subscribe_code, subscribe_name, product_code, seller_code)
value ('001', '001', '001', '직장인을 위한 점심 도시락', '10001', '00001'),
    ('001', '001', '001', '직장인을 위한 점심 도시락', '10002', '00001'),
    ('001', '001', '002', '자취생을 위한 집밥 도시락', '00001', '00001'),
    ('001', '001', '002', '자취생을 위한 집밥 도시락', '10001', '00001');

insert into tbl_user (user_id, user_pw, auth_div, auth_level, user_name, nickname, comp_name, zipcode, address1, address2, serial_code, phone_number, email, reco_code, is_del, created_at, updated_at)
values('testId', 'testPw', 'USE', 'USE', 'testName', 'testNickname', '', '05353', '테스트 기본 주소', '테스트 상세 주소', '9203161234567', '01012341234', 'testId@email.com', 'ABC12345', 'N', now(), now()),
      ('testId2', 'testPw', 'USE', 'USE', 'testName', '', '', '05353', '테스트 기본 주소', '테스트 상세 주소', '9010101111111', '01012341234', 'testId2@email.com', 'ABC12345', 'N', now(), now());

insert into tbl_point(content, expire_date, is_expired, occur_date, occur_point, user_id)
values ('친구추가','2022-09-13', 'N', '2022-08-29', 200, 'testId'),
       ('기간만료 소멸', '2022-08-30', 'Y', '2022-08-30', 200, 'testId'),
       ('회원가입 포인트', '2022-08-30', 'N', '2022-08-15', 200, 'testId');

insert into tbl_deliver (delivery_name, address1, address2, delivery_flag, user_id, zipcode)
values ('DB 등록 집', '테스트 기본 주소', '테스트 상세 주소', 0, 'testId', '12345'),
       ('회사', '테스트 기본 주소2', '테스트 상세 주소2', 1, 'testId', '00001'),
       ('DB등록 추가', '서울특별시 강남구 테헤란로 503', '패스트파이브 11층 1119호', 1, 'testId', '22222');

insert into tbl_product (product_code, seller_code, created_at, updated_at, detail, is_used, price, product_category, product_group, product_name, stock, dc_cost)
values('00001', '00001', now(), now(), 'text', 'Y', 100000, 0, 0, '테스트 상품1', 100, 0),
      ('00002', '00001', now(), now(), 'text', 'Y', 100000, 0, 0, '테스트 상품2', 100, 0),
      ('00003', '00002', now(), now(), 'text', 'Y', 5000, 0, 0, '테스트 상품 3', 100, 0),
      ('00004', '00002', now(), now(), 'text', 'Y', 45000, 0, 0, '테스트 상품 4', 100, 0),
      ('10001', '00001', now(), now(), 'subscribe', 'Y', 8000, '001', '001', '정기배송 테스트 1', 5, 0),
      ('10002', '00001', now(), now(), 'subscribe', 'Y', 7500, '001', '001', '정기배송 테스트 2', 5, 0);


insert into tbl_order (created_at, updated_at, amount, api_code, deliver_idx, order_idx, order_no, pay_method, product_code, qty, seller_code, status, unit_cost, user_id, dc_cost, delivery_comp, delivery_cost, delivery_code, card_name, card_num, quota, is_subscribe, subscribe_code, delivery_date)
VALUES (now(), now(), 100000, null, 1, 1,'ORD20221003-190000',0, '00001', 1, '00001', 0, 100000, 'testId', 10000, '05', 3000, '450725434935', 'BC카드', '123456******1234', 0, 'N', '000',null),
       (now(), now(), 200000, null, 1, 2,'ORD20221003-190000',0, '00002', 2, '00001', 0, 100000, 'testId', 5000, '05', 0, '450725434935', 'BC카드', '123456******1234', 0, 'N', '000',null),
       (now(), now(), 100000, null, 1, 1,'ORD20221003-190100',0, '00001', 1, '00001', 0, 100000, 'testId', 0, '04', 0,'651122270741', 'BC카드', '123456******1234', 0, 'N', '000',null),
       (now(), now(), 8000, null, 1, 1,'ORD20220114-201200',0, '10001', 1, '00001', 0, 8000, 'testId', 0, '05', 0,'651122270741', 'BC카드', '123456******1234', 0, 'Y', '001', DATE_SUB(now(), INTERVAL 2 DAY)),
       (now(), now(), 7500, null, 1, 2,'ORD20220114-201200',0, '10002', 1, '00001', 0, 7500, 'testId', 0, '05', 0,'651122270741', 'BC카드', '123456******1234', 0, 'Y', '001', DATE_SUB(now(), INTERVAL 1 DAY)),
       (now(), now(), 6000, null, 1, 1,'ORD20220114-220800',0, '10001', 1, '00001', 0, 6000, 'testId', 0, '05', 0,'651122270741', 'BC카드', '123456******1234', 0, 'Y', '002', now()),
       (now(), now(), 7000, null, 1, 2,'ORD20220114-220800',0, '10002', 1, '00001', 0, 7000, 'testId', 0, '05', 0,'651122270741', 'BC카드', '123456******1234', 0, 'Y', '002', DATE_ADD(now(), INTERVAL 1 DAY)),
       (now(), now(), 6000, null, 1, 1,'ORD20220115-220800',0, '10001', 1, '00001', 0, 6000, 'testId', 0, '05', 0,'651122270741', 'BC카드', '123456******1234', 0, 'Y', '002', DATE_ADD(now(), INTERVAL 1 DAY)),
       (now(), now(), 7000, null, 1, 2,'ORD20220115-220800',0, '10002', 1, '00001', 0, 7000, 'testId', 0, '05', 0,'651122270741', 'BC카드', '123456******1234', 0, 'Y', '002', DATE_ADD(now(), INTERVAL 2 DAY));

insert into tbl_environment(api_name, api_key)
values('logisticsApiCode','RvVqk5XxCk43x8lOvOwwcA')
    , ('iamportApiKey', '0636024527663452')
    , ('iamportApiSecret', 'daLHd2j6oV2t0tDOAGPolrumg202pMqqxdMeVTN7g1ycWcwCboUievQJaZJxEPDgN72XMEXWyjBioIfm')
    , ('iamportIMP', 'imp24005612');

insert into tbl_basket(product_code, qty, seller_code, user_id, created_at, updated_at)
values('00001', 2, '00001', 'testId', '2022-09-08 15:00:01', '2022-09-08 15:00:01'),
      ('00003', 1, '00002', 'testId', '2022-09-08 15:00:02', '2022-09-08 15:00:02'),
      ('00002', 1, '00001', 'testId', '2022-09-08 15:00:03', '2022-09-08 15:00:03'),
      ('00004', 1, '00002', 'testId', '2022-09-08 15:00:04', '2022-09-08 15:00:04');


insert into tbl_subscribe_basket(basket_no, user_id, subscribe_code, product_code, seller_code, product_idx, qty, delivery_date, created_at, updated_at)
values (concat('SUB',substr(replace(replace(replace(cast(now() as char),'-',''),':',''),' ','-'),3,16)),'testId', '001', '10001', '00001', 1, 1, DATE_ADD(now(), INTERVAL 1 DAY), now(), now()),
       (concat('SUB',substr(replace(replace(replace(cast(now() as char),'-',''),':',''),' ','-'),3,16)),'testId', '001', '10002', '00001', 2, 2, DATE_ADD(now(), INTERVAL 2 DAY), now(), now()),
       (concat('SUB',substr(replace(replace(replace(cast(DATE_ADD(now(), INTERVAL 1 DAY) as char),'-',''),':',''),' ','-'),3,16)),'testId', '001', '10002', '00001', 1, 1, DATE_ADD(now(), INTERVAL 2 DAY), DATE_ADD(now(), INTERVAL 1 DAY), DATE_ADD(now(), INTERVAL 1 DAY)),
       (concat('SUB',substr(replace(replace(replace(cast(DATE_ADD(now(), INTERVAL 1 DAY) as char),'-',''),':',''),' ','-'),3,16)),'testId', '001', '10002', '00001', 2, 1, DATE_ADD(now(), INTERVAL 3 DAY), DATE_ADD(now(), INTERVAL 1 DAY), DATE_ADD(now(), INTERVAL 1 DAY))
;