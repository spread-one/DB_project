-- 데이터베이스 생성
CREATE DATABASE SpaceBookingDB;
USE SpaceBookingDB;

-- 생성 공간 테이블
CREATE TABLE 생성공간 (
    공간_고유_id INT AUTO_INCREMENT PRIMARY KEY,
    공간_설명 VARCHAR(255) NOT NULL
    예약_가능_시작_시간 TIME NOT NULL,
    예약_가능_종료_시간 TIME NOT NULL
);

-- 예약 테이블
CREATE TABLE 예약 (
    예약_고유_id INT AUTO_INCREMENT PRIMARY KEY,
    예약자_이름 VARCHAR(100) NOT NULL,
    공간_고유_id INT,
    시작_시간 TIME NOT NULL,
    종료_시간 TIME NOT NULL,
    FOREIGN KEY (공간_고유_id) REFERENCES 생성공간(공간_고유_id)
);
