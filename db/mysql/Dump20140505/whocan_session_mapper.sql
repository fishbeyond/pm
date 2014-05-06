CREATE DATABASE  IF NOT EXISTS `whocan` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `whocan`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 117.121.25.232    Database: whocan
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.13.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `session_mapper`
--

DROP TABLE IF EXISTS `session_mapper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_mapper` (
  `mapperId` varchar(255) NOT NULL,
  `addTime` datetime DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mapperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_mapper`
--

LOCK TABLES `session_mapper` WRITE;
/*!40000 ALTER TABLE `session_mapper` DISABLE KEYS */;
INSERT INTO `session_mapper` VALUES ('8ab2a006455fd54a01455fd9dcf90007','2014-04-14 18:48:04','GH4-bNskQIe3oTuDxXsJyA','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a006455fd54a01455fd9dd050008','2014-04-14 18:48:04','GH4-bNskQIe3oTuDxXsJyA','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a0064560076e0145601309f70005','2014-04-14 19:50:31','tgE63_9-TDmPMSv1KIxxvQ','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a0064560076e014560130a0f0006','2014-04-14 19:50:31','tgE63_9-TDmPMSv1KIxxvQ','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a00645602e7301456094f633000c','2014-04-14 22:12:26','sa5IZzy3QgOEvLHWqLwoiQ','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a00645602e7301456094f63e000d','2014-04-14 22:12:26','sa5IZzy3QgOEvLHWqLwoiQ','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a00645602e7301456094f642000e','2014-04-14 22:12:26','sa5IZzy3QgOEvLHWqLwoiQ','vNuVRtWMSZmh60szbCyNbg'),('8ab2a00645644b850145644c697f0001','2014-04-15 15:31:40','l704kvGCTqOtAwTcjhWEoQ','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a00645644b850145644c69850002','2014-04-15 15:31:40','l704kvGCTqOtAwTcjhWEoQ','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a00645644b85014564547f770007','2014-04-15 15:40:30','0MhUrxxwQWqU_cRfpLYSgw','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a00645644b85014564547f7a0008','2014-04-15 15:40:30','0MhUrxxwQWqU_cRfpLYSgw','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a00645644b85014564556120000d','2014-04-15 15:41:28','5Q1kwHwrQJ-g86LAWF_MSw','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a00645644b85014564556121000e','2014-04-15 15:41:28','5Q1kwHwrQJ-g86LAWF_MSw','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a00645644b850145645695860013','2014-04-15 15:42:47','VOYMCobXT7qgkxoZfl606A','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a00645644b850145645695880014','2014-04-15 15:42:47','VOYMCobXT7qgkxoZfl606A','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a0064569f85b01456b3bfc43000b','2014-04-16 23:51:04','m6go-k60QaudSovt3rfvyQ','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a0064569f85b01456b3bfc4a000c','2014-04-16 23:51:04','m6go-k60QaudSovt3rfvyQ','9sdgaovtTLaJuLVnG4KIqw'),('8ab2a0064569f85b01456b3bfc4c000d','2014-04-16 23:51:04','m6go-k60QaudSovt3rfvyQ','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a0064569f85b01456b3bfc4e000e','2014-04-16 23:51:04','m6go-k60QaudSovt3rfvyQ','vNuVRtWMSZmh60szbCyNbg'),('8ab2a0474579b1ba01457f1adda3001e','2014-04-20 20:27:18','o2ABXCMvT1iDvqf-bFw2xg','9sdgaovtTLaJuLVnG4KIqw'),('8ab2a0474579b1ba01457f1addaa001f','2014-04-20 20:27:18','o2ABXCMvT1iDvqf-bFw2xg','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a0474579b1ba01457f1addac0020','2014-04-20 20:27:18','o2ABXCMvT1iDvqf-bFw2xg','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a0474579b1ba01457f1addae0021','2014-04-20 20:27:18','o2ABXCMvT1iDvqf-bFw2xg','vNuVRtWMSZmh60szbCyNbg'),('8ab2a0474579b1ba01458ddc6fb70036','2014-04-23 17:13:25','BKrOHwbOQF-HF9WZ-j-Wlw','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a0474579b1ba01458ddc6fbd0037','2014-04-23 17:13:25','BKrOHwbOQF-HF9WZ-j-Wlw','vNuVRtWMSZmh60szbCyNbg'),('8ab2a0474579b1ba01458ddc6fbf0038','2014-04-23 17:13:25','BKrOHwbOQF-HF9WZ-j-Wlw','9sdgaovtTLaJuLVnG4KIqw'),('8ab2a0474579b1ba01458ddc6fc00039','2014-04-23 17:13:25','BKrOHwbOQF-HF9WZ-j-Wlw','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a0474579b1ba01458ddd58b90042','2014-04-23 17:14:24','oj3f8abLQ52kZ2RHVEu7rA','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a0474579b1ba01458ddd58bb0043','2014-04-23 17:14:24','oj3f8abLQ52kZ2RHVEu7rA','vNuVRtWMSZmh60szbCyNbg'),('8ab2a0474579b1ba01458ddd58bc0044','2014-04-23 17:14:24','oj3f8abLQ52kZ2RHVEu7rA','9sdgaovtTLaJuLVnG4KIqw'),('8ab2a0474579b1ba01458ddd58be0045','2014-04-23 17:14:24','oj3f8abLQ52kZ2RHVEu7rA','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a0474579b1ba01458dded371004a','2014-04-23 17:16:01','-ssLdqtDTJ6bRiYZz4E9SQ','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a0474579b1ba01458dded372004b','2014-04-23 17:16:01','-ssLdqtDTJ6bRiYZz4E9SQ','vNuVRtWMSZmh60szbCyNbg'),('8ab2a0474579b1ba01458dded373004c','2014-04-23 17:16:01','-ssLdqtDTJ6bRiYZz4E9SQ','9sdgaovtTLaJuLVnG4KIqw'),('8ab2a0474579b1ba01458dded374004d','2014-04-23 17:16:01','-ssLdqtDTJ6bRiYZz4E9SQ','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a0474579b1ba01458eac3ec80055','2014-04-23 21:00:24','TurrtGHMR1KLK1ZT2LCaTQ','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a0474579b1ba01458eac3eca0056','2014-04-23 21:00:24','TurrtGHMR1KLK1ZT2LCaTQ','9sdgaovtTLaJuLVnG4KIqw'),('8ab2a0474579b1ba01458eac3ecb0057','2014-04-23 21:00:24','TurrtGHMR1KLK1ZT2LCaTQ','kyLKAXdqQhaehC1-pBzQfA'),('8ab2a0474579b1ba01458eac3ecd0058','2014-04-23 21:00:24','TurrtGHMR1KLK1ZT2LCaTQ','vNuVRtWMSZmh60szbCyNbg'),('8ab2a0474579b1ba01458ee3d0bf005d','2014-04-23 22:01:05','SmnxXINeTUmxB0eVpt8xFQ','cUKoYWPDSNu6Uq8wkMA5gQ'),('8ab2a0474579b1ba01458ee3d0c5005e','2014-04-23 22:01:05','SmnxXINeTUmxB0eVpt8xFQ','vNuVRtWMSZmh60szbCyNbg'),('8ab2a0474579b1ba01458ee3d0c6005f','2014-04-23 22:01:05','SmnxXINeTUmxB0eVpt8xFQ','9sdgaovtTLaJuLVnG4KIqw'),('8ab2a0474579b1ba01458ee3d0c70060','2014-04-23 22:01:05','SmnxXINeTUmxB0eVpt8xFQ','kyLKAXdqQhaehC1-pBzQfA');
/*!40000 ALTER TABLE `session_mapper` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-05 10:07:23