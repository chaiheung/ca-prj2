# 데이터베이스 생성
CREATE DATABASE prj2;
USE prj2;

# 게시판 테이블 생성
DROP TABLE board;
CREATE TABLE board
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(200)  NOT NULL,
    content  VARCHAR(3000) NOT NULL,
    writer   VARCHAR(100)  NOT NULL,
    inserted DATETIME      NOT NULL DEFAULT NOW()
);

SELECT *
FROM board;

# 회원 테이블 생성
CREATE TABLE member
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR(200) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    nickname VARCHAR(100) NOT NULL UNIQUE,
    inserted DATETIME     NOT NULL DEFAULT NOW()
);
ALTER TABLE member
    ADD COLUMN inserted DATETIME NOT NULL DEFAULT NOW();

SELECT *
FROM member;