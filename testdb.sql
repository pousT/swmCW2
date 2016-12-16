-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: 2016-12-16 09:11:07
-- 服务器版本： 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `testdb`
--

-- --------------------------------------------------------

--
-- 表的结构 `CurrentDevice`
--

CREATE TABLE `CurrentDevice` (
  `CID` int(11) NOT NULL,
  `DID` int(11) NOT NULL,
  `STATE` int(11) NOT NULL,
  `DVALUE` int(11) NOT NULL,
  `TIMEV` varchar(100) DEFAULT NULL,
  `HOUR_1` int(11) NOT NULL DEFAULT '0',
  `HOUR_2` int(11) NOT NULL DEFAULT '0',
  `HOUR_3` int(11) NOT NULL DEFAULT '0',
  `HOUR_4` int(11) NOT NULL DEFAULT '0',
  `HOUR_5` int(11) NOT NULL DEFAULT '0',
  `HOUR_6` int(11) NOT NULL DEFAULT '0',
  `HOUR_7` int(11) NOT NULL DEFAULT '0',
  `HOUR_8` int(11) NOT NULL DEFAULT '0',
  `HOUR_9` int(11) NOT NULL DEFAULT '0',
  `HOUR_10` int(11) NOT NULL DEFAULT '0',
  `HOUR_11` int(11) NOT NULL DEFAULT '0',
  `HOUR_12` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `CurrentDevice`
--

INSERT INTO `CurrentDevice` (`CID`, `DID`, `STATE`, `DVALUE`, `TIMEV`, `HOUR_1`, `HOUR_2`, `HOUR_3`, `HOUR_4`, `HOUR_5`, `HOUR_6`, `HOUR_7`, `HOUR_8`, `HOUR_9`, `HOUR_10`, `HOUR_11`, `HOUR_12`) VALUES
(2, 2, 1, 0, 'Wed Dec 07 15:57:51 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(4, 3, 1, 0, 'Wed Dec 07 15:57:54 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(1, 4, 1, 1, 'Wed Dec 14 13:38:51 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(1, 3, 1, 1, 'Wed Dec 14 17:00:16 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(4, 1, 1, 1, 'Wed Dec 14 17:00:33 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(1, 2, 0, 0, 'Wed Dec 14 17:54:22 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(4, 5, 0, 1, 'Wed Dec 14 20:30:12 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(1, 8, 0, 0, 'Wed Dec 14 20:33:57 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(4, 7, 0, 0, 'Wed Dec 14 20:37:22 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(4, 10, 0, 0, 'Thu Dec 15 14:18:57 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 1, 1, 0, 'Thu Dec 15 20:43:13 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 3, 1, 0, 'Fri Dec 16 15:58:12 CST 2016', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- 表的结构 `History`
--

CREATE TABLE `History` (
  `SDVALUE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `Property`
--

CREATE TABLE `Property` (
  `CID` int(11) NOT NULL,
  `DID` int(11) NOT NULL,
  `PNAME` varchar(10) NOT NULL,
  `PVALUE` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `Property`
--

INSERT INTO `Property` (`CID`, `DID`, `PNAME`, `PVALUE`) VALUES
(2, 2, 'weight', '100'),
(4, 55, 'size', '100'),
(4, 10, 'upperBound', '22'),
(4, 1, 'temp', '2'),
(1, 3, 'name ', 'mySensor'),
(2, 2, 'color', 'red'),
(2, 1, 'temp', '33'),
(2, 3, 'color', 'yellow');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
