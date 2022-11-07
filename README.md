# Healthy-Box 

샐러드, 다이어트 도시락 등 제품을 판매하는 이커머스 서비스입니다.    
홈페이지 관리자, 판매자(물품 판매 관리자), 일반 사용자(구매자) 등의 레이어로 나누어 서비스 진행됩니다.   
<sub>2022.08.31 기준 일반 사용자 레이어 우선 개발 진행중입니다.</sub>

## 개발환경

* Intellij IDEA Ulitmate 2022.1.4
* Java 17
* Gradle 7.5
* Spring Boot 2.7.2

## 기술 세부 스팩

Spring Boot

* Spring Boot Actuator
* Spring Web
* Spring Data JPA
* Thymeleaf
* H2 Database
* MySQL Driver
* Lombok
* Spring Boot DevTools
* Spring Configuration Processor

그 외

* QueryDSQL 5.0.0
* Bootstrap 5.2.0-Beta1
* Heroku

## 데모 페이지
* https://healthy-box.herokuapp.com
* 현재 로그인, 회원가입, 마이페이지 조회 기능만 구현되어 있음
* 2022.11.08 기준으로 로그인, 회원가입, 마이페이지의 각 기능 및 장바구니를 통한 결제 시스템 구현됨
* 2022.11 ~ 2022.12 서비스 페이지의 앞부분(상품의 조회, 장바구니 사용, 결제 및 게시판 이용 등) 완료 예정
* 2022.01 ~ 2022.03 판매 관리자 및 서비스 관리자 페이지 작업 예정
* 개발 내역 확인을 위한 계정은 testId / testPw를 통해 간략하게 확인할 수 있음
