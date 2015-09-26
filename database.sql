-- --------------------------------------------------------
-- 主机:                           192.168.37.128
-- 服务器版本:                        5.5.44-MariaDB - MariaDB Server
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 wedding 的数据库结构
CREATE DATABASE IF NOT EXISTS `wedding` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wedding`;


-- 导出  表 wedding.t_contract 结构
CREATE TABLE IF NOT EXISTS `t_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(45) DEFAULT NULL,
  `person` int(11) DEFAULT NULL,
  `fee` decimal(10,2) DEFAULT NULL,
  `service_times` int(11) DEFAULT NULL,
  `checker` int(11) DEFAULT NULL,
  `vat_no` varchar(45) DEFAULT NULL,
  `start_date` varchar(20) DEFAULT NULL,
  `end_date` varchar(20) DEFAULT NULL,
  `alive_flag` varchar(2) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 wedding.t_match 结构
CREATE TABLE IF NOT EXISTS `t_match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_date` varchar(20) DEFAULT NULL COMMENT '配对日期',
  `name` int(11) NOT NULL DEFAULT '0' COMMENT '配对人',
  `name_contract` int(11) NOT NULL DEFAULT '0' COMMENT '配对人合同',
  `service_employee` int(11) NOT NULL DEFAULT '0' COMMENT '服务老师',
  `match_person` int(11) NOT NULL DEFAULT '0' COMMENT '配对对象',
  `match_person_contract` int(11) NOT NULL DEFAULT '0' COMMENT '配对对象合同',
  `visit_result` varchar(20) DEFAULT '0' COMMENT '回访结果',
  `visit_remark` varchar(500) DEFAULT '0' COMMENT '回访情况说明',
  `alive_flag` varchar(2) NOT NULL DEFAULT '0',
  `create_by` int(11) NOT NULL DEFAULT '0',
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 wedding.t_person 结构
CREATE TABLE IF NOT EXISTS `t_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `birth_date` varchar(45) DEFAULT NULL,
  `constellation` varchar(45) DEFAULT NULL,
  `height` decimal(10,2) DEFAULT NULL,
  `education` varchar(45) DEFAULT NULL,
  `census` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `family` varchar(45) DEFAULT NULL,
  `house` varchar(45) DEFAULT NULL,
  `married_his` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `requirement` varchar(45) DEFAULT NULL,
  `alive_flag` varchar(45) DEFAULT NULL,
  `create_by` int(11) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 wedding.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `staff` varchar(45) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  `alive_flag` varchar(2) NOT NULL,
  `creation_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
