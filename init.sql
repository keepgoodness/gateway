-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия на сървъра:            8.0.32 - MySQL Community Server - GPL
-- ОС на сървъра:                Win64
-- HeidiSQL Версия:              12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Дъмп на структурата на БД gateway
CREATE DATABASE IF NOT EXISTS `gateway` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gateway`;

-- Дъмп структура за таблица gateway.exchange_rate
CREATE TABLE IF NOT EXISTS `exchange_rate` (
                                              `id` bigint NOT NULL AUTO_INCREMENT,
                                              `base` varchar(255) DEFAULT NULL,
    `date` varchar(255) DEFAULT NULL,
    `timestamp` bigint NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Изнасянето на данните беше деселектирано.

-- Дъмп структура за таблица gateway.rates
CREATE TABLE IF NOT EXISTS `rates` (
                                       `exchange_rate_id` bigint NOT NULL,
                                       `rate` double DEFAULT NULL,
                                       `currency` varchar(255) NOT NULL,
    PRIMARY KEY (`exchange_rate_id`,`currency`),
    CONSTRAINT `FKfd66ts3k0br18vsiywdifswas` FOREIGN KEY (`exchange_rate_id`) REFERENCES `exchange_rate` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Изнасянето на данните беше деселектирано.

-- Дъмп структура за таблица gateway.request_info
CREATE TABLE IF NOT EXISTS `request_info` (
                                              `id` bigint NOT NULL AUTO_INCREMENT,
                                              `client` varchar(255) DEFAULT NULL,
    `currency` varchar(255) DEFAULT NULL,
    `exchange_rate_id` bigint DEFAULT NULL,
    `request_id` varchar(255) DEFAULT NULL,
    `timestamp` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKai9n5hsd01998wco6rj3bg5yx` (`request_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Изнасянето на данните беше деселектирано.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
