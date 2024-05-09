-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 03, 2023 at 07:54 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `university`
--

-- --------------------------------------------------------

--
-- Table structure for table `course_details`
--

CREATE TABLE `course_details` (
  `course_code` varchar(10) NOT NULL,
  `course_name` varchar(50) NOT NULL,
  `department` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `course_details`
--

INSERT INTO `course_details` (`course_code`, `course_name`, `department`) VALUES
('CE101', 'Surveying', 'Civil Engineering'),
('CE102', 'Construction Materials', 'Civil Engineering'),
('CE201', 'Structural Analysis', 'Civil Engineering'),
('CE202', 'Geotechnical Engineering', 'Civil Engineering'),
('CE301', 'Transportation Engineering', 'Civil Engineering'),
('CS101', 'Introduction to Computer Science', 'Computer Science'),
('CS102', 'Data Structures and Algorithms', 'Computer Science'),
('CS201', 'Database Systems', 'Computer Science'),
('CS202', 'Operating Systems', 'Computer Science'),
('CS301', 'Artificial Intelligence', 'Computer Science'),
('EE101', 'Electric Circuits', 'Electrical Engineering'),
('EE102', 'Electronics', 'Electrical Engineering'),
('EE201', 'Power Systems', 'Electrical Engineering'),
('EE202', 'Control Systems', 'Electrical Engineering'),
('EE301', 'Renewable Energy Systems', 'Electrical Engineering'),
('ME101', 'Mechanics', 'Mechanical Engineering'),
('ME102', 'Thermodynamics', 'Mechanical Engineering'),
('ME201', 'Manufacturing Processes', 'Mechanical Engineering'),
('ME202', 'Fluid Mechanics', 'Mechanical Engineering'),
('ME301', 'Robotics and Automation', 'Mechanical Engineering');

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

CREATE TABLE `marks` (
  `Reg_no` varchar(8) NOT NULL,
  `Course` varchar(50) NOT NULL DEFAULT 'course_name',
  `Course_code` varchar(10) NOT NULL DEFAULT 'CourseCode',
  `CA1` int(11) NOT NULL CHECK (`CA1` >= 0 and `CA1` <= 30),
  `CA2` int(11) NOT NULL CHECK (`CA2` >= 0 and `CA2` <= 30),
  `CA3` int(11) NOT NULL CHECK (`CA3` >= 0 and `CA3` <= 30),
  `MTE` int(11) NOT NULL CHECK (`MTE` >= 0 and `MTE` <= 20),
  `ETE` int(11) NOT NULL CHECK (`ETE` >= 0 and `ETE` <= 50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `marks`
--

INSERT INTO `marks` (`Reg_no`, `Course`, `Course_code`, `CA1`, `CA2`, `CA3`, `MTE`, `ETE`) VALUES
('CE202111', 'Surveying', 'CE101', 0, 0, 0, 0, 0),
('CE202111', 'Construction Materials', 'CE102', 0, 0, 0, 0, 0),
('CE202111', 'Structural Analysis', 'CE201', 0, 0, 0, 0, 0),
('CE202111', 'Geotechnical Engineering', 'CE202', 0, 0, 0, 0, 0),
('CE202112', 'Surveying', 'CE101', 0, 0, 0, 0, 0),
('CE202112', 'Construction Materials', 'CE102', 0, 0, 0, 0, 0),
('CE202112', 'Structural Analysis', 'CE201', 0, 0, 0, 0, 0),
('CE202112', 'Geotechnical Engineering', 'CE202', 0, 0, 0, 0, 0),
('CS202111', 'Introduction to Computer Science', 'CS101', 10, 10, 10, 10, 10),
('CS202111', 'Data Structures and Algorithms', 'CS102', 27, 30, 22, 19, 45),
('CS202111', 'Database Systems', 'CS201', 25, 23, 27, 19, 47),
('CS202111', 'Operating Systems', 'CS202', 23, 23, 18, 15, 27),
('CS202111', 'Artificial Intelligence', 'CS301', 0, 30, 30, 0, 47),
('EE202111', 'Electric Circuits', 'EE101', 0, 0, 0, 0, 0),
('EE202111', 'Electronics', 'EE102', 0, 0, 0, 0, 0),
('EE202111', 'Power Systems', 'EE201', 0, 0, 0, 0, 0),
('EE202111', 'Control Systems', 'EE202', 0, 0, 0, 0, 0),
('EE202111', 'Renewable Energy Systems', 'EE301', 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `Name` varchar(255) NOT NULL,
  `Department` varchar(30) DEFAULT NULL,
  `Reg_no` varchar(8) NOT NULL CHECK (octet_length(`Reg_no`) = 8),
  `Roll_no` varchar(4) DEFAULT NULL,
  `Section` varchar(5) NOT NULL,
  `Fathers_name` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Mobile` varchar(10) NOT NULL CHECK (octet_length(`Mobile`) = 10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`Name`, `Department`, `Reg_no`, `Roll_no`, `Section`, `Fathers_name`, `Address`, `Mobile`) VALUES
('Aarav Gupta', 'Civil Engineering', 'CE202101', 'CE01', 'A', 'Sanjay Gupta', '123 Main Road, Delhi', '9876543210'),
('Riya Sharma', 'Civil Engineering', 'CE202102', 'CE02', 'A', 'Anil Sharma', '456 Park Street, Mumbai', '8765432109'),
('Rohit Singh', 'Civil Engineering', 'CE202103', 'CE03', 'A', 'Rajesh Singh', '789 Garden Lane, Pune', '7654321098'),
('Kavya Patel', 'Civil Engineering', 'CE202104', 'CE04', 'A', 'Rakesh Patel', '321 Lake View, Chennai', '6543210987'),
('Arnav Bhatia', 'Civil Engineering', 'CE202105', 'CE05', 'A', 'Sameer Bhatia', '555 Hill Top, Bangalore', '5432109876'),
('Ishika Gupta', 'Civil Engineering', 'CE202106', 'CE06', 'B', 'Suresh Gupta', '222 Skyline Road, Kolkata', '4321098765'),
('Rishi Shah', 'Civil Engineering', 'CE202107', 'CE07', 'B', 'Dinesh Shah', '333 Ocean View, Ahmedabad', '3210987654'),
('Aditi Chauhan', 'Civil Engineering', 'CE202108', 'CE08', 'B', 'Sunil Chauhan', '444 Valley View, Hyderabad', '2109876543'),
('Anushka Dubey', 'Civil Engineering', 'CE202109', 'CE09', 'B', 'Sanjay Dubey', '777 Riverside, Jaipur', '1098765432'),
('Rohan Mehra', 'Civil Engineering', 'CE202110', 'CE10', 'B', 'Arun Mehra', '888 Green Lane, Lucknow', '9876543210'),
('Sachin Vishwakarma', 'Civil Engineering', 'CE202111', 'CE11', 'B', 'Rakesh Vishwakarma', 'Gazipur, Varanasi', '9876543210'),
('Prabuddh Mani Tripathi', 'Civil Engineering', 'CE202112', 'CE12', 'B', 'V K Tripathi', 'Prayagraj', '9455290440'),
('Aarav Sharma', 'Computer Science', 'CS202101', 'CS01', 'A', 'Rahul Sharma', '456 Main Road, Delhi', '9876543210'),
('Riya Verma', 'Computer Science', 'CS202102', 'CS02', 'A', 'Rakesh Verma', '789 Park Street, Mumbai', '8765432109'),
('Rohit Gupta', 'Computer Science', 'CS202103', 'CS03', 'A', 'Sanjay Gupta', '321 Garden Lane, Pune', '7654321098'),
('Kavya Singh', 'Computer Science', 'CS202104', 'CS04', 'A', 'Rajesh Singh', '654 Lake View, Chennai', '6543210987'),
('Arnav Mehra', 'Computer Science', 'CS202105', 'CS05', 'A', 'Sameer Mehra', '777 Hill Top, Bangalore', '5432109876'),
('Ishika Reddy', 'Computer Science', 'CS202106', 'CS06', 'B', 'Suresh Reddy', '111 Skyline Road, Kolkata', '4321098765'),
('Rishi Patel', 'Computer Science', 'CS202107', 'CS07', 'B', 'Dinesh Patel', '222 Ocean View, Ahmedabad', '3210987654'),
('Aditi Singh', 'Computer Science', 'CS202108', 'CS08', 'B', 'Sunil Singh', '333 Valley View, Hyderabad', '2109876543'),
('Anushka Agarwal', 'Computer Science', 'CS202109', 'CS09', 'B', 'Sanjay Agarwal', '555 Riverside, Jaipur', '1098765432'),
('Rohan Choudhary', 'Computer Science', 'CS202110', 'CS10', 'B', 'Arun Choudhary', '888 Green Lane, Lucknow', '9876543210'),
('Javed Alam', 'Computer Science', 'CS202111', 'CS11', 'B', 'Razzak Ali', 'Pratappur, Sihorwa Bazar, Gorakhpur', '9120573582'),
('Nazia Parveen', 'Computer Science', 'CS202112', 'CS12', 'A', 'Sohail Khan', 'Gaziabad, Uttar Pradesh', '9876543210'),
('Aryan Singh', 'Electrical Engineering', 'EE202101', 'EE01', 'A', 'Rajendra Singh', '101 Main Street, Delhi', '9876543210'),
('Isha Sharma', 'Electrical Engineering', 'EE202102', 'EE02', 'A', 'Manoj Sharma', '202 Park Avenue, Mumbai', '8765432109'),
('Vivek Patel', 'Electrical Engineering', 'EE202103', 'EE03', 'A', 'Ramesh Patel', '303 Garden Road, Pune', '7654321098'),
('Kriti Gupta', 'Electrical Engineering', 'EE202104', 'EE04', 'A', 'Pradeep Gupta', '404 Lake View, Chennai', '6543210987'),
('Siddharth Singh', 'Electrical Engineering', 'EE202105', 'EE05', 'A', 'Anurag Singh', '505 Hill Top, Bangalore', '5432109876'),
('Neha Tiwari', 'Electrical Engineering', 'EE202106', 'EE06', 'B', 'Rahul Tiwari', '606 Skyline Road, Kolkata', '4321098765'),
('Varun Mehta', 'Electrical Engineering', 'EE202107', 'EE07', 'B', 'Mukesh Mehta', '707 Ocean View, Ahmedabad', '3210987654'),
('Riya Choudhary', 'Electrical Engineering', 'EE202108', 'EE08', 'B', 'Sanjay Choudhary', '808 Valley View, Hyderabad', '2109876543'),
('Shivam Kumar', 'Electrical Engineering', 'EE202109', 'EE09', 'B', 'Rajiv Kumar', '909 Riverside, Jaipur', '1098765432'),
('Nitya Dubey', 'Electrical Engineering', 'EE202110', 'EE10', 'B', 'Shyam Dubey', '1010 Green Lane, Lucknow', '9876543210'),
('Rohit Yadav', 'Electrical Engineering', 'EE202111', 'EE11', 'B', 'Gopal Yadav', 'Patna, Bihar', '6789021234'),
('Akash Patel', 'Mechanical Engineering', 'ME202101', 'ME01', 'A', 'Alok Patel', '123 Main Road, Delhi', '9876543210'),
('Nisha Singh', 'Mechanical Engineering', 'ME202102', 'ME02', 'A', 'Rakesh Singh', '456 Park Street, Mumbai', '8765432109'),
('Prakash Sharma', 'Mechanical Engineering', 'ME202103', 'ME03', 'A', 'Mukesh Sharma', '789 Garden Lane, Pune', '7654321098'),
('Rohini Gupta', 'Mechanical Engineering', 'ME202104', 'ME04', 'A', 'Raj Gupta', '321 Lake View, Chennai', '6543210987'),
('Sandeep Singh', 'Mechanical Engineering', 'ME202105', 'ME05', 'A', 'Alok Singh', '555 Hill Top, Bangalore', '5432109876'),
('Priya Sharma', 'Mechanical Engineering', 'ME202106', 'ME06', 'B', 'Rakesh Sharma', '222 Skyline Road, Kolkata', '4321098765'),
('Rahul Gupta', 'Mechanical Engineering', 'ME202107', 'ME07', 'B', 'Rajendra Gupta', '333 Ocean View, Ahmedabad', '3210987654'),
('Suresh Patel', 'Mechanical Engineering', 'ME202108', 'ME08', 'B', 'Dinesh Patel', '444 Valley View, Hyderabad', '2109876543'),
('Kavita Singh', 'Mechanical Engineering', 'ME202109', 'ME09', 'B', 'Anil Singh', '777 Riverside, Jaipur', '1098765432'),
('Aman Sharma', 'Mechanical Engineering', 'ME202110', 'ME10', 'B', 'Prakash Sharma', '888 Green Lane, Lucknow', '9876543210');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course_details`
--
ALTER TABLE `course_details`
  ADD PRIMARY KEY (`course_code`);

--
-- Indexes for table `marks`
--
ALTER TABLE `marks`
  ADD PRIMARY KEY (`Reg_no`,`Course_code`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`Reg_no`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `marks`
--
ALTER TABLE `marks`
  ADD CONSTRAINT `marks_ibfk_1` FOREIGN KEY (`Reg_no`) REFERENCES `student` (`Reg_no`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
