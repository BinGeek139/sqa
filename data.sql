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


-- Dumping database structure for mark_manage
CREATE DATABASE IF NOT EXISTS `mark_manage` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mark_manage`;

-- Dumping structure for table mark_manage.class
CREATE TABLE IF NOT EXISTS `class` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `semester_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2on56akwlwj9yp1g7ko9bl7rq` (`semester_id`),
  KEY `FKh0eyrgvqpfux7dvr8elhhdaf6` (`subject_id`),
  KEY `FKhjtee0w5l26pivuxosvmxv0ff` (`user_id`),
  CONSTRAINT `FK2on56akwlwj9yp1g7ko9bl7rq` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`),
  CONSTRAINT `FKh0eyrgvqpfux7dvr8elhhdaf6` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKhjtee0w5l26pivuxosvmxv0ff` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table mark_manage.class: ~0 rows (approximately)
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` (`id`, `name`, `semester_id`, `subject_id`, `user_id`, `code`) VALUES
	(1, 'DC', 1, 1, 3, 'D17-90');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;

-- Dumping structure for table mark_manage.class_student
CREATE TABLE IF NOT EXISTS `class_student` (
  `id` bigint(20) NOT NULL,
  `class_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgt5cku1apa8h7jdbr2np7emg8` (`class_id`),
  KEY `FK5lumuhwmxheda9h4fwwbc9jks` (`user_id`),
  CONSTRAINT `FK5lumuhwmxheda9h4fwwbc9jks` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKgt5cku1apa8h7jdbr2np7emg8` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table mark_manage.class_student: ~0 rows (approximately)
/*!40000 ALTER TABLE `class_student` DISABLE KEYS */;
INSERT INTO `class_student` (`id`, `class_id`, `user_id`) VALUES
	(1, 1, 7),
	(2, 1, 13),
	(3, 1, 14),
	(4, 1, 16),
	(5, 1, 17),
	(6, 1, 19),
	(7, 1, 5);
/*!40000 ALTER TABLE `class_student` ENABLE KEYS */;

-- Dumping structure for table mark_manage.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table mark_manage.hibernate_sequence: ~1 rows (approximately)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table mark_manage.mark
CREATE TABLE IF NOT EXISTS `mark` (
  `id` bigint(20) NOT NULL,
  `mark` float DEFAULT NULL,
  `class_student_id` bigint(20) NOT NULL,
  `spoint_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo1ym2rgydl80gae4qbolgm80f` (`class_student_id`),
  KEY `FKblojqn8y0y4gnm01vysd9ayy1` (`spoint_id`),
  CONSTRAINT `FKblojqn8y0y4gnm01vysd9ayy1` FOREIGN KEY (`spoint_id`) REFERENCES `spoint` (`id`),
  CONSTRAINT `FKo1ym2rgydl80gae4qbolgm80f` FOREIGN KEY (`class_student_id`) REFERENCES `class_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table mark_manage.mark: ~0 rows (approximately)
/*!40000 ALTER TABLE `mark` DISABLE KEYS */;
/*!40000 ALTER TABLE `mark` ENABLE KEYS */;

-- Dumping structure for table mark_manage.semester
CREATE TABLE IF NOT EXISTS `semester` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table mark_manage.semester: ~5 rows (approximately)
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` (`id`, `name`, `year`) VALUES
	(1, '1', 2020),
	(3, '2', 2019),
	(4, '1', 2019),
	(5, '2', 2020),
	(20, '1', 2021);
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;

-- Dumping structure for table mark_manage.spoint
CREATE TABLE IF NOT EXISTS `spoint` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `percent` int(11) DEFAULT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4hgg0rb6r1p0urrg6tnv4g0it` (`subject_id`),
  CONSTRAINT `FK4hgg0rb6r1p0urrg6tnv4g0it` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table mark_manage.spoint: ~0 rows (approximately)
/*!40000 ALTER TABLE `spoint` DISABLE KEYS */;
INSERT INTO `spoint` (`id`, `name`, `percent`, `subject_id`) VALUES
	(1, 'Chuyên Cần', 10, 1),
	(2, 'Bài tập lớn ', 20, 1),
	(3, 'Thi', 70, 1);
/*!40000 ALTER TABLE `spoint` ENABLE KEYS */;

-- Dumping structure for table mark_manage.subject
CREATE TABLE IF NOT EXISTS `subject` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_nopad_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table mark_manage.subject: ~8 rows (approximately)
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` (`id`, `name`) VALUES
	(1, 'Đảm bảo chất lượng phần mềm'),
	(2, 'Phát triển phần mềm hướng dịch vụ'),
	(3, 'Xây dựng các hệ thống nhúng'),
	(4, 'Mạng máy tính'),
	(5, 'An toàn và bảo mật hệ thống thông tin'),
	(6, 'Xử lý ảnh'),
	(7, 'Tiếng anh A22'),
	(8, 'Hệ điều hành');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;

-- Dumping structure for table mark_manage.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_nopad_ci DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_nopad_ci DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table mark_manage.user: ~23 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `address`, `full_name`, `dob`, `gender`, `password`, `role`, `status`, `username`, `code`) VALUES
	(1, 'Ha noi ', 'Đỗ Thị Bích Ngọc', '1991-03-25 00:00:00', 1, '$2a$10$.BKG.5RwDbJ2tZ6IXmiYqedbbgjLDBcnT.F/owAh02o0xNPzhGcDG', 'ROLE_TEACHER', 1, 'giaovien', NULL),
	(2, 'Ha noi', NULL, '2001-03-25 00:00:00', 1, '$2a$10$tWsdASj2nGhQddX1ngoVWeN7lbkJnHxjZ28a3/JyBpMKrx61WZCqC', 'ROLE_USER', 1, 'hocsinh', NULL),
	(3, 'Ninh Bình', 'Ngô Xuân Bách', NULL, 0, '$2a$10$.BKG.5RwDbJ2tZ6IXmiYqedbbgjLDBcnT.F/owAh02o0xNPzhGcDG', 'ROLE_TEACHER', 1, 'thaybach', NULL),
	(4, NULL, 'Đào Đình Hòa', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(5, NULL, 'Đỗ Văn Hùng', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(6, NULL, 'Phạm Trung Kiên', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(7, NULL, 'Đoàn Mạnh	Long', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(8, NULL, 'Nguyễn Văn Mạnh', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(9, NULL, 'Vũ Quang	Ninh', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(10, NULL, 'Lê Khắc Phúc', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(11, NULL, 'Phạm Minh	Phúc', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(13, NULL, 'Nguyễn Ngọc Quang', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(14, NULL, 'Nguyễn Tất Thắng', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(15, NULL, 'Trần Xuân	Tùng', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(16, NULL, 'Vũ Văn Tuyền	', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(17, NULL, 'Ngô Hoàng	Việt', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(18, NULL, 'Vũ TrọngTuyề n', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(19, NULL, 'Lưu Thế	Việt', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(20, NULL, 'Ngô Hoàng	Việt', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(21, NULL, 'Đinh Thế	Vinh', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(30, NULL, 'Phan Văn Ba', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(31, NULL, 'Nguyễn Tiến Đạt', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL),
	(32, NULL, 'Phạm Trung Hiếu', NULL, NULL, NULL, 'ROLE_USER', NULL, NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
