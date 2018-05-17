-- --------------------------------------------------------
-- Verkkotietokone:              127.0.0.1
-- Palvelinversio:               10.2.14-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Versio:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for todo_db
CREATE DATABASE IF NOT EXISTS `todo_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `todo_db`;

-- Dumping structure for taulu todo_db.priority
CREATE TABLE IF NOT EXISTS `priority` (
  `priority_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`priority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table todo_db.priority: ~4 rows (suunnilleen)
/*!40000 ALTER TABLE `priority` DISABLE KEYS */;
INSERT INTO `priority` (`priority_id`, `description`) VALUES
	(1, 'Critical'),
	(2, 'Urgent'),
	(3, 'Normal'),
	(4, 'Whenever, wherever');
/*!40000 ALTER TABLE `priority` ENABLE KEYS */;

-- Dumping structure for taulu todo_db.todo
CREATE TABLE IF NOT EXISTS `todo` (
  `todo_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `is_done` bit(1) NOT NULL DEFAULT b'0',
  `priority_id` int(11) NOT NULL,
  `deadline` date DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`todo_id`),
  KEY `FK_todo_priority` (`priority_id`),
  CONSTRAINT `FK_todo_priority` FOREIGN KEY (`priority_id`) REFERENCES `priority` (`priority_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table todo_db.todo: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
INSERT INTO `todo` (`todo_id`, `description`, `is_done`, `priority_id`, `deadline`, `created_date`) VALUES
	(1, 'Do stuff.', b'0', 4, '2018-05-30', '2018-05-17 22:50:47'),
	(2, 'Do more stuff.', b'1', 2, '2018-05-31', '2018-05-17 22:51:23'),
	(3, 'Complete todo app.', b'0', 1, '2018-05-21', '2018-05-17 22:53:39'),
	(4, 'Old stuff.', b'1', 3, '2018-05-16', '2018-05-17 23:04:42');
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
