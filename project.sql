-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2015 at 11:37 AM
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
  PRIMARY KEY (`index`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`index`, `first_name`, `middle_name`, `last_name`, `id_no`, `email_address`, `password`, `phone_number`) VALUES
(1, 'Rodgers', 'Komen', 'Cherutich', 27997978, '', '', 0),
(4, '', '', '', 0, 'rodgekom@yahoo.com', '1234', 727162205),
(5, '', '', '', 0, 'null', 'Abc', 0),
(6, '', '', '', 0, 'mike@yahoo.com', '123', 727162545),
(7, '', '', '', 0, 'null', 'Abc', 0),
(8, '', '', '', 0, 'null', 'Abc', 0),
(9, '', '', '', 0, 'Abc', 'Abc', 0),
(10, '', '', '', 0, 'rndnc', '1234', 12456),
(11, '', '', '', 0, '', '', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
