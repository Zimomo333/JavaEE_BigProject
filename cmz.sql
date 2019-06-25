/*
Navicat MySQL Data Transfer
Source Host     : localhost:
Source Database : test
Target Host     : localhost:
Target Database : test
*/

SET FOREIGN_KEY_CHECKS=0;
-- 顾客账号
DROP TABLE IF EXISTS `Customer`;
CREATE TABLE `Customer`
(
  `account`  varchar(30)  NOT NULL,
  `ID`       tinyint      NOT NULL,
  `name`     varchar(24)  NULL,
  `sex`      varchar(10)  NULL,
  `phone`    char(11)     NULL,
  `address`  varchar(100) NULL,
  `password` varchar(30)  NOT NULL,
  `power`    tinyint      NOT NULL,
  `img`      varchar(30)  NOT NULL,
 )ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

-- 职工账号
DROP TABLE IF EXISTS `Staff`;
CREATE TABLE `Staff`
(
  `account`  char(12)     NOT NULL,
  `workNum`  tinyint      NOT NULL,
  `name`     char(12)     NULL,
  `sex`      char(2)      NOT NULL,
  `phone`    char(11)     NULL,
  `address`  varchar(100) NULL,
  `IDcard`   char(18)     NOT NULL,
  `password` varchar(30)  NOT NULL,
  `power`    tinyint      NOT NULL,
  `img`      varchar(30)  NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

-- 插入老板账号
insert into Staff (account, workNum, name, sex, phone, address, IDcard, password, power, img) values('540577914', 1, '陈铭昭', '男', '13144171162', '东莞理工学院', '441284199904110037', '123456', 1, '123.jpg')

-- 订单
DROP TABLE IF EXISTS `Orders`;
CREATE TABLE `Orders`
(
  `orderNumber`     tinyint      NOT NULL,
  `userAccount`     char(12)     NOT NULL,
  `staffAccount`    char(12)     NULL,
  `paid`            float        NOT NULL,
  `address`         varchar(100) NOT NULL,
  `request`         varchar(100) NULL,
  `requestMaterial` varchar(100) NULL,
  `isCompletion`    char(2)      NOT NULL,
  `isAccept`        char(2)      NOT NULL,
  `isDistribute`    char(2)      NOT NULL,
  `startDate`       TIMESTAMP    NULL,
  `realDate`        TIMESTAMP    NULL,
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

-- 材料
DROP TABLE IF EXISTS `Material`;
CREATE TABLE `Material`
(
  `ID`          tinyint     NOT NULL,
  `material`    varchar(100)NOT NULL,
  `num`         tinyint     NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

-- 产品
DROP TABLE IF EXISTS `Produce`;
CREATE TABLE `Produce`
(
  `orderNumber` tinyint  NOT NULL,
  `needPay`     float    NOT NULL,
  `ID`          tinyint  NOT NULL,
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;