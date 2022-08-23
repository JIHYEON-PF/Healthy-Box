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

insert into tbl_user (user_id, user_pw, auth_div, auth_level, user_name, nickname, comp_name, zipcode, address1, address2, serial_code, phone_number, reco_code, is_del, created_at, updated_at)
values('testId', 'testPw', 'USE', 'USE', 'testName', 'testNickname', '', '05353', '테스트 기본 주소', '테스트 상세 주소', '9010101111111', '01012341234', 'ABC12345', 'N', now(), now());