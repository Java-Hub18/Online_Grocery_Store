-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 02, 2020 at 02:59 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.5.37

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `general_stores`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL,
  `added_date` datetime NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `added_date`, `email`, `password`, `name`) VALUES
(1, '2020-05-16 00:46:08', 'javahub018@gmail.com', '123456', 'Dharmesh');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `total_price` double NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `mrp_price` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `price`, `quantity`, `total_price`, `customer_id`, `product_id`, `mrp_price`) VALUES
(5, 190, 1, 190, 1, 11, 200);

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `id` bigint(20) NOT NULL,
  `contact_date` datetime NOT NULL,
  `email` varchar(50) NOT NULL,
  `message` varchar(1000) NOT NULL,
  `name` varchar(50) NOT NULL,
  `subject` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`id`, `contact_date`, `email`, `message`, `name`, `subject`) VALUES
(2, '2020-07-25 19:45:20', 'dharmeshmourya043@gmail.com', 'Hi, This is testing email contacts.', 'Dharmesh Mourya', 'Testing');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `added_date` datetime NOT NULL,
  `email` varchar(100) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `valid` bit(1) NOT NULL,
  `pin_code` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `address`, `added_date`, `email`, `gender`, `name`, `password`, `phone`, `valid`, `pin_code`) VALUES
(1, 'Ram Nagar,Vrindavan Society, Mumbai', '2020-05-10 11:11:01', 'dharmeshmourya043@gmail.com', 'Male', 'Dharmesh', '$2a$10$QQeMRbMjnXys3cDIvvozH.dpxxCLD9d0b5Pz092djiwkSLW1YJ/Vy', '1223334560', b'1', '400330');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `amount` double NOT NULL,
  `customer_address` varchar(255) NOT NULL,
  `customer_address_type` varchar(15) NOT NULL,
  `customer_email` varchar(50) NOT NULL,
  `customer_name` varchar(30) NOT NULL,
  `customer_phone` varchar(10) NOT NULL,
  `order_date` datetime NOT NULL,
  `order_num` int(11) NOT NULL,
  `pin_code` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `active`, `amount`, `customer_address`, `customer_address_type`, `customer_email`, `customer_name`, `customer_phone`, `order_date`, `order_num`, `pin_code`) VALUES
(237, b'1', 284, 'Ram Nagar,Vrindavan Society, Mumbai', 'Home', 'dharmeshmourya043@gmail.com', 'Dharmesh', '1223334560', '2020-07-25 19:59:26', 1000, '400330'),
(238, b'1', 95, 'Ram Nagar,Vrindavan Society, Mumbai', 'Home', 'dharmeshmourya043@gmail.com', 'Dharmesh', '1223334560', '2020-07-25 19:59:26', 1001, '400330'),
(239, b'1', 65, 'Ram Nagar,Vrindavan Society, Mumbai', 'Home', 'dharmeshmourya043@gmail.com', 'Dharmesh', '1223334560', '2020-07-25 19:59:26', 1002, '400330');

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `payment_id` int(11) NOT NULL,
  `order_status` varchar(25) NOT NULL,
  `mrp_price` double NOT NULL,
  `payment_mode` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_detail`
--

INSERT INTO `order_detail` (`id`, `amount`, `price`, `quantity`, `order_id`, `product_id`, `payment_id`, `order_status`, `mrp_price`, `payment_mode`) VALUES
(232, 284, 142, 2, 237, 10, 1000, 'Delivered', 150, 'COD'),
(233, 95, 95, 1, 238, 9, 1000, 'Delivered', 100, 'COD'),
(234, 65, 65, 1, 239, 5, 1000, 'Delivered', 72, 'COD');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `code` varchar(5) NOT NULL,
  `create_date` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `price` double NOT NULL,
  `mrp_price` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `active`, `code`, `create_date`, `description`, `image`, `name`, `price`, `mrp_price`) VALUES
(1, b'1', 'P01', '2020-05-15 02:58:36', 'Chana Dal 1Kg', '/resources/JTurDal1kgPRMING65XX50717_1_P.jpg', 'Chana Dal 1Kg', 99, 105),
(2, b'1', 'P02', '2020-05-15 02:59:56', 'Moong Dal 1kg', '/resources/JMoongDal1kgPRMING58XX50717_1_P.jpg', 'Moong Dal 1kg', 160, 168),
(3, b'1', 'P03', '2020-05-15 03:02:21', 'Premium Toor Dal 1kg', '/resources/JTurDal1kgPRMING65XX50717_1_P.jpg', 'Premium Toor Dal 1kg', 120, 125),
(4, b'1', 'P04', '2020-05-15 03:03:41', 'Premium Urad Dal 1kg', '/resources/JUradDal500gmPRMING72XX50717_1_P.jpg', 'Urad Dal 1kg', 160, 165),
(5, b'1', 'P05', '2020-05-15 03:05:06', 'Best Quality Masoor Dal 500gm', '/resources/JMasoorDal1kgPRMING55XX50717_1_P.jpg', 'Masoor Dal 500gm', 65, 72),
(6, b'1', 'P06', '2020-05-15 03:12:16', 'Premium Quality Singdana', '/resources/images.jpg', 'Singdana(Groundnut) 1kg', 160, 165),
(7, b'1', 'P07', '2020-05-15 03:13:44', 'Premium Quality singdana', '/resources/images.jpg', 'Singdana(Groundnut) 500gm', 70, 75),
(8, b'1', 'P08', '2020-05-29 01:01:54', 'Kabuli Chana 500gm', '/resources/download.jpg', 'Kabuli Chana 500gm', 70, 75),
(9, b'1', 'P09', '2020-07-25 19:42:35', 'Green Moong Dal 500gm', '/resources\\Green Moong.jpg', 'Green Moong Dal', 95, 100),
(10, b'1', 'P10', '2020-07-25 19:43:27', 'Sabudana 1kg', '/resources\\Webp.net-resizeimage (4).jpg', 'Sabudana ', 142, 150),
(11, b'1', 'P11', '2020-07-25 20:04:32', 'Rajma 1kg', '/resources\\Webp.net-resizeimage.jpg', 'Rajma', 190, 200);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_c0r9atamxvbhjjvy5j8da1kam` (`email`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `CART_CUST_FK` (`customer_id`),
  ADD KEY `CART_PROD_FK` (`product_id`);

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_dwk6cx0afu8bs9o4t536v1j5v` (`email`),
  ADD UNIQUE KEY `UK_o3uty20c6csmx5y4uk2tc5r4m` (`phone`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_o6e714ot0hclyvhlcte6vc4mr` (`order_num`),
  ADD UNIQUE KEY `UKo6e714ot0hclyvhlcte6vc4mr` (`order_num`);

--
-- Indexes for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ORDER_DETAIL_ORD_FK` (`order_id`),
  ADD KEY `ORDER_DETAIL_PROD_FK` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_h3w5r1mx6d0e5c6um32dgyjej` (`code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=240;
--
-- AUTO_INCREMENT for table `order_detail`
--
ALTER TABLE `order_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=235;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
