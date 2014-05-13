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
-- Table structure for table `FriendInfo`
--

DROP TABLE IF EXISTS `FriendInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FriendInfo` (
  `userId` varchar(255) NOT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `mailAddress` varchar(255) DEFAULT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `portrait` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message_user_mapper`
--

DROP TABLE IF EXISTS `message_user_mapper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_user_mapper` (
  `mapperId` varchar(255) NOT NULL,
  `messageId` varchar(255) DEFAULT NULL,
  `receiveTime` datetime DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mapperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operate_record`
--

DROP TABLE IF EXISTS `operate_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operate_record` (
  `operateId` varchar(255) NOT NULL,
  `operateContent` varchar(255) DEFAULT NULL,
  `operateTime` datetime DEFAULT NULL,
  `projectId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`operateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `projectId` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `done` tinyint(1) DEFAULT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project_user_mapper`
--

DROP TABLE IF EXISTS `project_user_mapper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_user_mapper` (
  `mapperId` varchar(255) NOT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `projectId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mapperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project_work`
--

DROP TABLE IF EXISTS `project_work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_work` (
  `workId` varchar(255) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `createUserId` varchar(255) DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `done` tinyint(1) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `projectId` varchar(255) DEFAULT NULL,
  `workName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`workId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_access`
--

DROP TABLE IF EXISTS `security_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_access` (
  `accessId` varchar(255) NOT NULL,
  `accessTime` datetime DEFAULT NULL,
  `accessToken` varchar(255) DEFAULT NULL,
  `aliveTime` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`accessId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_authCode`
--

DROP TABLE IF EXISTS `security_authCode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_authCode` (
  `phoneId` int(11) NOT NULL AUTO_INCREMENT,
  `authCode` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`phoneId`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_deviceToken`
--

DROP TABLE IF EXISTS `security_deviceToken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_deviceToken` (
  `tokenId` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `userId` varchar(255) NOT NULL,
  PRIMARY KEY (`tokenId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `sessionId` varchar(255) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `sessionName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `session_message`
--

DROP TABLE IF EXISTS `session_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_message` (
  `messageId` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `fromUser` varchar(255) DEFAULT NULL,
  `msgType` varchar(255) DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `taskId` varchar(255) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(255) DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `groupId` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `top` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `userId` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `mailAddress` varchar(255) DEFAULT NULL,
  `phoneNo` varchar(15) NOT NULL,
  `portrait` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `UK_d7j9fvecmdk2hrftn675bx6nd` (`phoneNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_invitation`
--

DROP TABLE IF EXISTS `user_invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_invitation` (
  `invitationId` varchar(255) NOT NULL,
  `friendId` varchar(255) DEFAULT NULL,
  `invitePhoneNo` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`invitationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_linkman`
--

DROP TABLE IF EXISTS `user_linkman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_linkman` (
  `linkmanId` varchar(255) NOT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`linkmanId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_mapper`
--

DROP TABLE IF EXISTS `user_mapper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_mapper` (
  `mapperId` varchar(255) NOT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `friendId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mapperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-09 15:12:01
