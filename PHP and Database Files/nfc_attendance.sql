-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2017 at 02:30 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nfc_attendance`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `CourseID` varchar(20) NOT NULL,
  `StudentID` varchar(20) NOT NULL,
  `Attended` int(11) NOT NULL,
  `Total` int(11) NOT NULL,
  `Enrolled` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`CourseID`, `StudentID`, `Attended`, `Total`, `Enrolled`) VALUES
('CYL101', '2014csb1001', 2, 3, 1),
('CSL201', '2014csb1001', 44, 79, 1),
('CSL202', '2014csb1001', 5, 5, 1),
('MAL111', '2014csb1002', 3, 4, 1),
('CSL343', '2014csb1002', 4, 6, 1),
('CSL603', '2014csb1003', 4, 15, 1),
('CSL343', '2014csb1004', 3, 6, 1),
('CSL202', '2014csb1005', 4, 5, 1),
('CSL201', '2014csb1004', 53, 79, 1);

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `Name` varchar(100) NOT NULL,
  `ID` varchar(20) NOT NULL,
  `ProfessorID` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`Name`, `ID`, `ProfessorID`) VALUES
('Data Structures', 'CSL201', '2010cs1001'),
('Introduction to Databases', 'CSL202', '2010cs1002'),
('Fundamentals of Chemistry', 'CYL101', '2010cs1003'),
('Computer Networks', 'CSL343', '2010cs1004'),
('Linear Algebra', 'MAL111', '2010ma1005'),
('Machine Learning', 'CSL603', '2010cs1001');

-- --------------------------------------------------------

--
-- Table structure for table `professor`
--

CREATE TABLE `professor` (
  `Name` varchar(100) NOT NULL,
  `ID` varchar(20) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `professor`
--

INSERT INTO `professor` (`Name`, `ID`, `Username`, `Password`) VALUES
('Dr. Peter', '2010cs1004', 'peter', '1'),
('Dr. Nitin', '2010cs1002', 'nitin', '1'),
('Dr. C K Narayanan', '2010cs1001', 'ckn', '1'),
('Dr. Rajendra', '2010ch1003', 'rajendra', '1'),
('Dr. Chittaranjan', '2014ma1005', 'chittaranjan', '1');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `Name` varchar(100) NOT NULL,
  `ID` varchar(20) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `ImageAddress` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`Name`, `ID`, `Username`, `Password`, `ImageAddress`) VALUES
('Aakarshan Gupta', '2014csb1001', 'aakarshan', '2', '1.jpg'),
('Snehil Ameta', '2014csb1002', 'snehil', '2', '2.jpg'),
('Jatin Garg', '2014csb1003', 'jatin', '2', '3.jpg'),
('Jasdeep Singh', '2014csb1004', 'jasdeep', '2', '4.jpg'),
('Shailendra', '2014csb1005', 'shailendra', '2', '5.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`CourseID`,`StudentID`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`ID`,`ProfessorID`);

--
-- Indexes for table `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
