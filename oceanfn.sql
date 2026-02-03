-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: oceanfn
-- ------------------------------------------------------
-- Server version	9.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `banners`
--

DROP TABLE IF EXISTS `banners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banners` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image_url` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `link` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `position` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'ACTIVE',
  `order_index` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banners`
--

LOCK TABLES `banners` WRITE;
/*!40000 ALTER TABLE `banners` DISABLE KEYS */;
/*!40000 ALTER TABLE `banners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `parent_id` bigint DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'ACTIVE',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_category_slug_type` (`slug`,`type`),
  KEY `fk_category_parent` (`parent_id`),
  CONSTRAINT `fk_category_parent` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'In hộp bánh kem','in-hop-banh-kem','PRODUCT',NULL,'ACTIVE','2025-12-31 08:59:22','2026-01-02 03:44:40'),(2,'In tem nhãn','in-tem-nhan','PRODUCT',NULL,'ACTIVE','2025-12-31 08:59:22','2026-01-02 03:44:40'),(3,'In thẻ bài','in-the-bai','PRODUCT',NULL,'ACTIVE','2025-12-31 08:59:22','2026-01-02 03:44:40'),(4,'In thẻ tag quần áo','in-the-tag-quan-ao','PRODUCT',NULL,'ACTIVE','2025-12-31 08:59:22','2026-01-02 03:44:40'),(5,'In thiệp cảm ơn','in-thiep-cam-on','PRODUCT',NULL,'ACTIVE','2025-12-31 08:59:22','2026-01-02 03:44:40'),(6,'In tờ rơi','in-to-roi','PRODUCT',NULL,'ACTIVE','2025-12-31 08:59:22','2026-01-02 03:44:40'),(7,'In túi giấy theo yêu cầu','in-tui-giay-theo-yeu-cau','PRODUCT',NULL,'ACTIVE','2025-12-31 08:59:22','2026-01-02 03:44:40'),(8,'Thùng Carton','thung-carton','PRODUCT',NULL,'ACTIVE','2025-12-31 08:59:22','2026-01-02 03:44:40'),(9,'Băng keo dán thùng','bang-keo-dan-thung','PRODUCT',NULL,'ACTIVE','2025-12-31 08:59:22','2026-01-02 03:44:40'),(10,'Hộp carton nắp gài','hop-carton-nap-gai','PRODUCT',NULL,'ACTIVE','2025-12-31 08:59:22','2026-01-02 03:44:40'),(11,'Màng PE','mang-pe','PRODUCT',NULL,'ACTIVE','2025-12-31 08:59:22','2026-01-02 03:44:40'),(12,'Hộp carton có quai xách','hop-carton-co-quai-xach','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(13,'Hộp carton đựng hàng','hop-carton-dung-hang','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(14,'Hộp carton đựng quần áo','hop-carton-dung-quan-ao','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(15,'Hộp carton ship COD','hop-carton-ship-cod','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(16,'In offset thùng carton','in-offset-thung-carton','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(17,'Thùng carton chống thấm','thung-carton-chong-tham','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(18,'Thùng carton có vách ngăn','thung-carton-co-vach-ngan','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(19,'Thùng carton đựng gà','thung-carton-dung-ga','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(20,'Thùng carton đựng hàng điện tử','thung-carton-dung-hang-dien-tu','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(21,'Thùng carton đựng mỹ phẩm','thung-carton-dung-my-pham','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(22,'Thùng carton đựng nước uống','thung-carton-dung-nuoc-uong','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(23,'Thùng carton đựng rượu','thung-carton-dung-ruou','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(24,'Thùng carton may mặc','thung-carton-may-mac','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(25,'Thùng carton nắp rời','thung-carton-nap-roi','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(26,'Thùng carton trắng','thung-carton-trang','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(27,'Thùng carton xuất khẩu','thung-carton-xuat-khau','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(28,'Hộp carton nhỏ','hop-carton-nho','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(29,'Thùng carton lớn','thung-carton-lon','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(30,'Thùng carton 3 lớp','thung-carton-3-lop','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(31,'Thùng carton 5 lớp','thung-carton-5-lop','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(32,'Thùng carton 7 lớp','thung-carton-7-lop','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(33,'Thùng carton âm dương','thung-carton-am-duong','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(34,'Thùng carton chuyển nhà','thung-carton-chuyen-nha','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(35,'Thùng carton đi máy bay','thung-carton-di-may-bay','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(36,'Thùng carton đựng giày','thung-carton-dung-giay','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(37,'Thùng carton đựng hồ sơ','thung-carton-dung-ho-so','PRODUCT',8,'ACTIVE','2025-12-31 09:00:03','2026-01-02 03:44:40'),(38,'Băng keo 100 yard','bang-keo-100-yard','PRODUCT',9,'ACTIVE','2025-12-31 09:00:24','2026-01-02 03:44:40'),(39,'Băng keo 200 yard','bang-keo-200-yard','PRODUCT',9,'ACTIVE','2025-12-31 09:00:24','2026-01-02 03:44:40'),(40,'Băng keo 300 yard','bang-keo-300-yard','PRODUCT',9,'ACTIVE','2025-12-31 09:00:24','2026-01-02 03:44:40'),(41,'Băng keo đục','bang-keo-duc','PRODUCT',9,'ACTIVE','2025-12-31 09:00:24','2026-01-02 03:44:40'),(42,'Băng keo trong','bang-keo-trong','PRODUCT',9,'ACTIVE','2025-12-31 09:00:24','2026-01-02 03:44:40'),(45,'Thùng carton 6 lớp','thung-carton-6-lop-moi','PRODUCT',8,'ACTIVE','2026-01-02 03:47:02','2026-01-02 03:47:02');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message` text COLLATE utf8mb4_unicode_ci,
  `source` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pages`
--

DROP TABLE IF EXISTS `pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'DRAFT',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug` (`slug`),
  KEY `idx_pages_slug` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pages`
--

LOCK TABLES `pages` WRITE;
/*!40000 ALTER TABLE `pages` DISABLE KEYS */;
/*!40000 ALTER TABLE `pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_categories`
--

DROP TABLE IF EXISTS `post_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_categories` (
  `post_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`post_id`,`category_id`),
  KEY `fk_postcat_category` (`category_id`),
  CONSTRAINT `fk_postcat_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `fk_postcat_post` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_categories`
--

LOCK TABLES `post_categories` WRITE;
/*!40000 ALTER TABLE `post_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci,
  `thumbnail` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'DRAFT',
  `published_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug` (`slug`),
  KEY `idx_posts_slug` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_categories`
--

DROP TABLE IF EXISTS `product_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_categories` (
  `product_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`product_id`,`category_id`),
  KEY `fk_pc_category` (`category_id`),
  CONSTRAINT `fk_pc_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `fk_pc_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,30),(2,30);
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `short_description` text COLLATE utf8mb4_unicode_ci,
  `content` longtext COLLATE utf8mb4_unicode_ci,
  `price` decimal(15,2) DEFAULT NULL,
  `thumbnail` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'ACTIVE',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug` (`slug`),
  KEY `idx_products_slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Thùng carton 3 lớp 40x30x30','thung-carton-3-lop-40x30x30',NULL,NULL,12000.00,'https://res.cloudinary.com/dhbc3ecmm/image/upload/v1767340033/d9hzqcuihlalfhnjutrg.jpg','ACTIVE','2025-12-31 09:57:14','2026-01-02 07:52:28',NULL),(2,'Updated name','test-product',NULL,'<p>Nội dung update OK</p>',12000.00,'https://res.cloudinary.com/dhbc3ecmm/image/upload/v1767340033/d9hzqcuihlalfhnjutrg.jpg','ACTIVE',NULL,'2026-01-08 02:24:19',NULL),(4,'Thùng carton 10 lớp','thung-carton-10-lop',NULL,NULL,12900.00,'https://res.cloudinary.com/dhbc3ecmm/image/upload/v1767842485/w1pbltf3fwqwn8sxjjtg.jpg','ACTIVE',NULL,'2026-01-08 03:24:49',NULL),(5,'Thùng carton 14 lớp','thung-carton-14-lop',NULL,NULL,123300.00,'https://res.cloudinary.com/dhbc3ecmm/image/upload/v1767842825/etwedqr4uqnbm8cumwcy.png','ACTIVE',NULL,NULL,NULL),(6,'Thùng carton 5 lớp 30x20x15','thung-carton-5-lop-30x20x15',NULL,NULL,8500.00,'https://res.cloudinary.com/dhbc3ecmm/image/upload/v1767842485/w1pbltf3fwqwn8sxjjtg.jpg','ACTIVE','2026-01-08 03:33:29','2026-01-08 03:33:29',NULL),(7,'Thùng carton 7 lớp 40x30x25','thung-carton-7-lop-40x30x25',NULL,NULL,15000.00,'https://res.cloudinary.com/dhbc3ecmm/image/upload/v1767842485/w1pbltf3fwqwn8sxjjtg.jpg','ACTIVE','2026-01-08 03:33:29','2026-01-08 03:33:29',NULL),(8,'Thùng carton 5 lớp 25x15x10','thung-carton-5-lop-25x15x10',NULL,NULL,6500.00,'https://res.cloudinary.com/dhbc3ecmm/image/upload/v1767842485/w1pbltf3fwqwn8sxjjtg.jpg','ACTIVE','2026-01-08 03:33:29','2026-01-08 03:33:29',NULL),(9,'Thùng carton 10 lớp 50x40x40','thung-carton-10-lop-50x40x40',NULL,NULL,18500.00,'https://res.cloudinary.com/dhbc3ecmm/image/upload/v1767842485/w1pbltf3fwqwn8sxjjtg.jpg','ACTIVE','2026-01-08 03:33:29','2026-01-08 03:33:29',NULL),(10,'Thùng carton 7 lớp 35x25x20','thung-carton-7-lop-35x25x20',NULL,NULL,11500.00,'https://res.cloudinary.com/dhbc3ecmm/image/upload/v1767842485/w1pbltf3fwqwn8sxjjtg.jpg','ACTIVE','2026-01-08 03:33:29','2026-01-08 03:33:29',NULL),(11,'Thùng carton 5 lớp 20x15x12','thung-carton-5-lop-20x15x12',NULL,NULL,5500.00,'https://res.cloudinary.com/dhbc3ecmm/image/upload/v1767842485/w1pbltf3fwqwn8sxjjtg.jpg','ACTIVE','2026-01-08 03:33:29','2026-01-08 03:33:29',NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'EDITOR');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seo_meta`
--

DROP TABLE IF EXISTS `seo_meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seo_meta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `entity_type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `entity_id` bigint NOT NULL,
  `meta_title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `meta_description` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `meta_keywords` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `og_image` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_seo_entity` (`entity_type`,`entity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seo_meta`
--

LOCK TABLES `seo_meta` WRITE;
/*!40000 ALTER TABLE `seo_meta` DISABLE KEYS */;
/*!40000 ALTER TABLE `seo_meta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci,
  `thumbnail` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'ACTIVE',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `config_key` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `config_value` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `config_key` (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_ur_role` (`role_id`),
  CONSTRAINT `fk_ur_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `fk_ur_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'ACTIVE',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$12$OqrungA3zBOtdsGtrTJ1.e4GdsEw5MIOHHgscAh8th/.oH04fDk8O',NULL,'ACTIVE','2025-12-31 07:57:29','2026-01-08 02:46:03');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-02  8:56:33
