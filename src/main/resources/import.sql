USE `pm`;
CREATE TABLE pm.user (
  userId      VARCHAR(50),
  userName    VARCHAR(50),
  phoneNo     VARCHAR(50) NOT NULL UNIQUE,
  mailAddress VARCHAR(50),
  password    VARCHAR(50),
  authCode    INT,
  PRIMARY KEY (userId));
CREATE TABLE pm.project_detail (
  detailId    INT NOT NULL AUTO_INCREMENT,
  detail      VARCHAR(255),
  operateTime DATETIME,
  projectId   INT NOT NULL,
  PRIMARY KEY (detailId));
CREATE TABLE pm.project (
  projectId    INT NOT NULL AUTO_INCREMENT,
  projectName  VARCHAR(50),
  content      VARCHAR(50),
  createTime   DATETIME,
  deadline     DATETIME,
  isDone       BOOLEAN,
  userId VARCHAR(50),
  PRIMARY KEY (projectId));
CREATE TABLE pm.contact (
  contactId   VARCHAR(50),
  projectId   INT NOT NULL,
  userId      VARCHAR(50),
  phoneNo     VARCHAR(50),
  mailAddress VARCHAR(50),
  userName    VARCHAR(50),
  PRIMARY KEY (contactId));
CREATE TABLE pm.work (
  workId       INT NOT NULL AUTO_INCREMENT,
  workName     VARCHAR(50),
  backup       VARCHAR(255),
  createTime   DATETIME,
  projectId    INT NOT NULL,
  userId VARCHAR(50),
  contactId    VARCHAR(50),
  deadline     DATETIME,
  isDone       BOOLEAN,
  PRIMARY KEY (workId));

CREATE TABLE pm.chat (
  chatId       INT NOT NULL AUTO_INCREMENT,
  message      VARCHAR(255),
  fromUserName VARCHAR(50),
  fromUserId   VARCHAR(50),
  projectId    INT NOT NULL,
  createTime   DATETIME,
  PRIMARY KEY (chatId));
CREATE TABLE pm.device_token (
  tokenId INT NOT NULL AUTO_INCREMENT,
  userId  VARCHAR(50),
  token   VARCHAR(64),
  PRIMARY KEY (tokenId));
#==============================================
DROP FUNCTION IF EXISTS `pm`.`save_project_detail`;
DELIMITER $$
CREATE DEFINER =`root`@`localhost` FUNCTION `save_project_detail`(operator_id  VARCHAR(50), detail VARCHAR(50),
                                                                  operate_time DATETIME, project_id VARCHAR(50))
  RETURNS INT(11)
  BEGIN
    DECLARE operator VARCHAR(50);
    DECLARE save_msg VARCHAR(255);
    SELECT
      userName
    INTO operator
    FROM user
    WHERE userId = operator_id;
SET save_msg = if((operator IS null || operator = ''), detail, concat(detail, '--变更人--', operator));
INSERT INTO pm.project_detail (detail, operateTime, projectId) VALUES (save_msg, operate_time, project_id);
RETURN 1;
END$$
DELIMITER ;
#项目===================================================================================================================

DROP PROCEDURE IF EXISTS `create_project`;
DELIMITER $$
CREATE DEFINER =`root`@`localhost` PROCEDURE `create_project`(project_name   VARCHAR(50), content VARCHAR(50),
                                                              create_time    DATETIME, dead_line DATETIME,
                                                              create_user_id VARCHAR(50))
  BEGIN
    DECLARE project_id INT;
    INSERT INTO project (projectName, content, createTime, deadline, userId)
      VALUES (project_name, content, create_time, dead_line, create_user_id);
    SET project_id = LAST_INSERT_ID();
    SELECT
      save_project_detail(create_user_id, concat('创建项目:', project_name), create_time, project_id);
  END$$
DELIMITER ;
DROP PROCEDURE IF EXISTS `update_project`;
DELIMITER $$
USE `pm`$$
CREATE DEFINER =`root`@`localhost` PROCEDURE `update_project`(project_name   VARCHAR(50), content VARCHAR(50),
                                                              create_time    DATETIME, project_id INT,
                                                              create_user_id VARCHAR(50))
  BEGIN
    UPDATE project
    SET projectName=project_name, content=content
    WHERE projectId = project_id;
    SELECT
      save_project_detail(create_user_id, concat('更改项目名为:', project_name), create_time, project_id);
  END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `delete_project`;
DELIMITER $$
CREATE DEFINER =`root`@`localhost` PROCEDURE `delete_project`(project_Id INT)
  BEGIN
    DELETE FROM pm.project
    WHERE projectId = project_Id;
    DELETE FROM pm.contact
    WHERE projectId = project_Id;
    DELETE FROM work
    WHERE projectId = project_id;
  END$$
DELIMITER ;

#用户===================================================================================================================
DROP PROCEDURE IF EXISTS `get_authCode`;
DELIMITER $$
CREATE DEFINER =`root`@`localhost` PROCEDURE `get_authCode`(IN  phone_no VARCHAR(50), OUT auth_code INT,
                                                            OUT mobile   VARCHAR(50))
  BEGIN
    SET mobile = phone_no;
    SET auth_code = FLOOR(100000 + (RAND() * 888888));
    IF ((SELECT
           userId
         FROM pm.user
         WHERE phoneNo = phone_no) IS null)
    THEN
      INSERT INTO pm.user (userId, phoneNo, authCode) VALUES (uuid(), phone_no, auth_code);
    ELSE
      UPDATE pm.user
      SET authCode=auth_code
      WHERE phoneNo = phone_no;
    END IF;
  END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS `login_byAuthCode`;
CREATE DEFINER =`root`@`localhost` PROCEDURE `login_byAuthCode`(phone_no  VARCHAR(50), auth_code VARCHAR(50),
  OUT                                                           isSuccess BOOLEAN, OUT user_id VARCHAR(50))
  BEGIN
    DECLARE result VARCHAR(50);
    SELECT
      userId
    INTO result
    FROM user
    WHERE phoneNo = phone_no AND authCode = auth_code;
    IF (result IS NOT null)
    THEN
      UPDATE contact
      SET userId=result
      WHERE phoneNo = phone_no;
      SET user_id = result;
      SET isSuccess = 1;
    ELSE
      SET isSuccess = 0;
    END IF;
  END$$
DELIMITER ;
#工作===================================================================================================================
DROP PROCEDURE IF EXISTS `create_work`;
DELIMITER $$
CREATE DEFINER =`root`@`localhost` PROCEDURE `create_work`(operator_id VARCHAR(50), operate_time DATETIME,
                                                           work_name   VARCHAR(50),
                                                           backup      VARCHAR(255), create_time DATETIME,
                                                           project_id  INT, create_user_id VARCHAR(50),
                                                           contact_id  VARCHAR(50),
                                                           deadline    DATETIME)
  BEGIN
    INSERT INTO work (workName, backup, createTime, projectId, userId, contactId, deadline, isDone)
      VALUES (work_name, backup, create_time, project_id, create_user_id, contact_id, deadline, FALSE);
    SELECT
      save_project_detail(operator_id, concat('创建工作:', work_name), operate_time, project_id);
  END$$
DELIMITER ;
#trigger===========================================
DROP TRIGGER IF EXISTS `t_contact`;
DELIMITER $$
CREATE TRIGGER t_contact BEFORE INSERT ON contact FOR EACH ROW

  BEGIN
    DECLARE user_id VARCHAR(50);
    SET user_id = (SELECT
                     userId
                   FROM user
                   WHERE phoneNo = NEW.phoneNo);
    SET NEW.userId = user_id;
  END$$
DELIMITER ;