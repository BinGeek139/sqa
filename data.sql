-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table mark_manage.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`Id`, `username`, `password`, `address`, `dob`, `gender`, `role`, `status`, `email`, `full_name`) VALUES
	(1, 'giaovien', '$2a$10$.BKG.5RwDbJ2tZ6IXmiYqedbbgjLDBcnT.F/owAh02o0xNPzhGcDG', 'Ha noi ', '1991-03-25', 1, 'ROLE_TEACHER', 1, NULL, NULL),
	(2, 'hocsinh', '$2a$10$tWsdASj2nGhQddX1ngoVWeN7lbkJnHxjZ28a3/JyBpMKrx61WZCqC', 'Ha noi', '2001-03-25', 1, 'ROLE_USER', 1, NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
