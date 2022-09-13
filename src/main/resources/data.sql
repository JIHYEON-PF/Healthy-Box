insert into tbl_div_product (product_group, product_category, group_name, category_name, is_used)
values ('001', '001', '정기구독', '도시락', 'Y'), --정기구독 - 도시락
('001', '002', '정기구독', '샐러드', 'Y'), --정기구독 - 샐러드
('001', '003', '정기구독', '건강식품', 'Y'), --정기구독 - 건강식품
('002', '001', '도시락', '일반 도시락', 'Y'), --도시락 - 일반 도시락
('002', '002', '도시락', '다이어트 도시락', 'Y'), --도시락 - 다이어트 도시락
('003', '001', '샐러드', '샐러드','Y'), -- 샐러드 - 샐러드
('004', '001', '샌드위치', '샌드위치', 'Y'), -- 샌드위치 - 샌드위치
('005', '001', '건강식품', '건강식품', 'Y'), --건강식품 - 건강식품
('006', '001', '간식', '간식', 'Y'), --간식 - 간식
('007', '007', '음료', '음료', 'Y'); --음료 - 음료

insert into tbl_user (user_id, user_pw, auth_div, auth_level, user_name, nickname, comp_name, zipcode, address1, address2, serial_code, phone_number, email, reco_code, is_del, created_at, updated_at)
values('testId', 'testPw', 'USE', 'USE', 'testName', 'testNickname', '', '05353', '테스트 기본 주소', '테스트 상세 주소', '9203161234567', '01012341234', 'testId@email.com', 'ABC12345', 'N', now(), now()),
      ('testId2', 'testPw', 'USE', 'USE', 'testName', '', '', '05353', '테스트 기본 주소', '테스트 상세 주소', '9010101111111', '01012341234', 'testId2@email.com', 'ABC12345', 'N', now(), now());

insert into tbl_point(content, expire_date, is_expired, occur_date, occur_point, user_id)
values ('친구추가','2022-09-13', 'N', '2022-08-29', 200, 'testId'),
       ('기간만료 소멸', '2022-08-30', 'N', '2022-08-30', 200, 'testId'),
       ('회원가입 포인트', '2022-08-30', 'Y', '2022-08-15', 200, 'testId');

insert into tbl_deliver (delivery_name, address1, address2, delivery_flag, user_id, zipcode)
values ('DB 등록 집', '테스트 기본 주소', '테스트 상세 주소', 0, 'testId', '12345'),
       ('회사', '테스트 기본 주소2', '테스트 상세 주소2', 1, 'testId', '00001'),
       ('DB등록 추가', '서울특별시 강남구 테헤란로 503', '패스트파이브 11층 1119호', 1, 'testId', '22222');

insert into tbl_product (product_code, seller_code, created_at, updated_at, detail, is_used, price, product_category, product_group, product_name, stock)
values('00001', '00001', now(), now(), 'text', 'Y', 100000, 0, 0, '테스트 상품1', 100),
      ('00002', '00001', now(), now(), 'text', 'Y', 100000, 0, 0, '테스트 상품2', 100);

insert into tbl_order (created_at, updated_at, amount, api_code, deliver_idx, order_idx, order_no, pay_method, product_code, qty, seller_code, status, unit_cost, user_id)
VALUES (now(), now(), 100000, null, 1, 1,'2209080001',0, '00001', 1, '00001', 0, 100000, 'testId'),
       (now(), now(), 100000, null, 1, 2,'2209080001',0, '00001', 1, '00001', 0, 100000, 'testId'),
       (now(), now(), 100000, null, 1, 1,'2209080002',0, '00001', 1, '00001', 0, 100000, 'testId');

