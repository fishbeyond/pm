
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `message_user_mapper` (
  `mapperId` varchar(255) NOT NULL,
  `messageId` varchar(255) DEFAULT NULL,
  `receiveTime` datetime DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mapperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `operate_record` (
  `operateId` varchar(255) NOT NULL,
  `operateContent` varchar(255) DEFAULT NULL,
  `operateTime` datetime DEFAULT NULL,
  `projectId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`operateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `project` (
  `projectId` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `done` tinyint(1) DEFAULT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `project_user_mapper` (
  `mapperId` varchar(255) NOT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `projectId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mapperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `security_access` (
  `accessId` varchar(255) NOT NULL,
  `accessTime` datetime DEFAULT NULL,
  `accessToken` varchar(255) DEFAULT NULL,
  `aliveTime` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`accessId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `security_authCode` (
  `phoneId` int(11) NOT NULL AUTO_INCREMENT,
  `authCode` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`phoneId`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8
/
CREATE TABLE `security_deviceToken` (
  `tokenId` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `userId` varchar(255) NOT NULL,
  PRIMARY KEY (`tokenId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8
/
CREATE TABLE `session` (
  `sessionId` varchar(255) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `sessionName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `session_mapper` (
  `mapperId` varchar(255) NOT NULL,
  `addTime` datetime DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mapperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `session_message` (
  `messageId` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `fromUser` varchar(255) DEFAULT NULL,
  `msgType` varchar(255) DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `user_invitation` (
  `invitationId` varchar(255) NOT NULL,
  `friendId` varchar(255) DEFAULT NULL,
  `invitePhoneNo` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`invitationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `user_linkman` (
  `linkmanId` varchar(255) NOT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`linkmanId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `user_mapper` (
  `mapperId` varchar(255) NOT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `friendId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mapperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
