-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2015 at 02:27 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `driving_license`
--

CREATE TABLE IF NOT EXISTS `driving_license` (
  `ref_no` varchar(10) NOT NULL,
  `id_no` int(11) DEFAULT NULL,
  `class` varchar(8) DEFAULT NULL,
  `reg_no` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`ref_no`),
  UNIQUE KEY `id_no` (`id_no`),
  UNIQUE KEY `reg_no` (`reg_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `insurance`
--

CREATE TABLE IF NOT EXISTS `insurance` (
  `policy_no` varchar(20) NOT NULL,
  `reg_no` varchar(7) DEFAULT NULL,
  `issued_by` varchar(20) DEFAULT NULL,
  `start_date` varchar(12) DEFAULT NULL,
  `expiry_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`policy_no`),
  UNIQUE KEY `reg_no` (`reg_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `offence`
--

CREATE TABLE IF NOT EXISTS `offence` (
  `offence_no` int(20) NOT NULL AUTO_INCREMENT,
  `id_no` int(11) DEFAULT NULL,
  `reg_no` varchar(7) DEFAULT NULL,
  `offence` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`offence_no`),
  UNIQUE KEY `reg_no` (`reg_no`),
  KEY `id_no` (`id_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `middle_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `id_no` int(11) NOT NULL,
  `email_address` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone_number` int(11) NOT NULL,
  PRIMARY KEY (`index`),
  UNIQUE KEY `id_no` (`id_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`index`, `first_name`, `middle_name`, `last_name`, `id_no`, `email_address`, `password`, `phone_number`) VALUES
(1, 'Rodgers', 'Komen', 'Cherutich', 27997978, 'rodgekom@yahoo.com', '1234', 727162205);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `driving_license`
--
ALTER TABLE `driving_license`
  ADD CONSTRAINT `driving_license_ibfk_1` FOREIGN KEY (`id_no`) REFERENCES `users` (`id_no`);

--
-- Constraints for table `insurance`
--
ALTER TABLE `insurance`
  ADD CONSTRAINT `insurance_ibfk_1` FOREIGN KEY (`reg_no`) REFERENCES `driving_license` (`reg_no`);

--
-- Constraints for table `offence`
--
ALTER TABLE `offence`
  ADD CONSTRAINT `offence_ibfk_1` FOREIGN KEY (`reg_no`) REFERENCES `driving_license` (`reg_no`),
  ADD CONSTRAINT `offence_ibfk_2` FOREIGN KEY (`id_no`) REFERENCES `users` (`id_no`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
