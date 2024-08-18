
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

-- Дъмп структура за таблица gateway.rates
CREATE TABLE IF NOT EXISTS `rates` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `base_currency` varchar(3) NOT NULL,
  `currency` varchar(3) NOT NULL,
  `rate` double NOT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1369 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Изнасянето на данните беше деселектирано.

-- Дъмп структура за таблица gateway.statistics
CREATE TABLE IF NOT EXISTS `statistics` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client` varchar(255) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `request_id` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Изнасянето на данните беше деселектирано.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

