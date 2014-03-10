USE `pm`;
CREATE TABLE pm.user (
  userId      INT         NOT NULL AUTO_INCREMENT,
  userName    VARCHAR(50),
  phoneNo     VARCHAR(50) NOT NULL UNIQUE,
  mailAddress VARCHAR(50),
  password    VARCHAR(50),
  PRIMARY KEY (userId));
CREATE TABLE pm.user_authCode (
  authCodeId INT NOT NULL AUTO_INCREMENT,
  phoneNo    VARCHAR(50) UNICODE,
  authCode   INT,
  PRIMARY KEY (authCodeId));
CREATE TABLE pm.linkman (
  linkManId   INT NOT NULL AUTO_INCREMENT,
  ownerId     INT,
  phoneNo     VARCHAR(50),
  mailAddress VARCHAR(50),
  linkManName VARCHAR(50),
  PRIMARY KEY (linkManId));
CREATE TABLE pm.project_alteration (
  alterationId INT NOT NULL AUTO_INCREMENT,
  message      VARCHAR(100),
  alterTime    DATETIME,
  projectId    INT NOT NULL,
  PRIMARY KEY (alterationId));
CREATE TABLE pm.project (
  projectId    INT NOT NULL AUTO_INCREMENT,
  projectName  VARCHAR(50),
  content      VARCHAR(50),
  createTime   DATETIME,
  deadline     DATETIME,
  isDone       BOOLEAN,
  createUserId INT,
  PRIMARY KEY (projectId));
CREATE TABLE pm.crew (
  crewId      INT NOT NULL AUTO_INCREMENT,
  projectId   INT NOT NULL,
  userId      INT,
  phoneNo     VARCHAR(50),
  mailAddress VARCHAR(50),
  userName    VARCHAR(50),
  PRIMARY KEY (crewId));
CREATE TABLE pm.work (
  workId       INT NOT NULL AUTO_INCREMENT,
  workName     VARCHAR(50),
  backup       VARCHAR(255),
  createTime   DATETIME,
  projectId    INT NOT NULL,
  createUserId INT,
  crewId       INT,
  deadline     DATETIME,
  isDone       BOOLEAN,
  PRIMARY KEY (workId));

CREATE TABLE pm.chat (
  chatId       INT NOT NULL AUTO_INCREMENT,
  content      VARCHAR(255),
  fromUserName VARCHAR(30),
  fromUserId   INT,
  projectId    INT NOT NULL,
  createTime   DATETIME,
  PRIMARY KEY (chatId));
#==============================================
DROP FUNCTION IF EXISTS `pm`.`get_authCode`;
DELIMITER $$
USE `pm`$$
CREATE FUNCTION `get_authCode`()
  RETURNS INTEGER
  BEGIN
    RETURN (SELECT
              FLOOR(100000 + (RAND() * 888888)));
  END$$
DELIMITER ;
DROP FUNCTION IF EXISTS `pm`.`find_userNameById`;
DELIMITER $$
CREATE DEFINER =`root`@`localhost` FUNCTION `find_userNameById`(user_id INT)
  RETURNS VARCHAR(50)
  CHARSET utf8
  BEGIN
    DECLARE user VARCHAR(50);
    SELECT
      userName
    INTO user
    FROM user
    WHERE userId = user_id;
    RETURN user;
  END$$
DELIMITER ;
DROP FUNCTION IF EXISTS `pm`.`save_project_alteration`;
DELIMITER $$
CREATE DEFINER =`root`@`localhost` FUNCTION `save_project_alteration`(operator_id INT, message VARCHAR(50),
                                                                      alter_time  DATETIME, project_id VARCHAR(50))
  RETURNS INT(11)
  BEGIN
    DECLARE operator VARCHAR(50);
    DECLARE save_msg VARCHAR(100);
    SET operator = find_userNameById(operator_id);
    SET save_msg = if((operator IS null || @operator = ''), message, concat(message, '--变更人--', operator));
    INSERT INTO pm.project_alteration (message, alterTime, projectId) VALUES (save_msg, alter_time, project_id);
    RETURN 1;
  END$$
DELIMITER ;
#项目===================================================================================================================
DROP PROCEDURE IF EXISTS `update_project`;
DELIMITER $$
USE `pm`$$
CREATE DEFINER =`root`@`localhost` PROCEDURE `update_project`(project_name VARCHAR(50), content VARCHAR(50),
                                                              create_time  DATETIME, project_id INT, create_user_id INT)
  BEGIN
    UPDATE project
    SET projectName=project_name, content=content
    WHERE projectId = project_id;
    SELECT
      save_project_alteration(create_user_id, concat('更改项目名为:', project_name), create_time, project_id);
  END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `project_add_member`;
DELIMITER $$
CREATE DEFINER =`root`@`localhost` PROCEDURE `project_add_member`(project_id   INT, phone_no VARCHAR(50),
                                                                  mail_address VARCHAR(50), user_name VARCHAR(50),
                                                                  operator_id  INT, operate_time DATETIME)
  BEGIN
    DECLARE user_id INT;
    SET user_id = (SELECT
                     userId
                   FROM user
                   WHERE phoneNo = phone_no);
    INSERT INTO crew (projectId, userId, phoneNo, mailAddress, userName) VALUES (project_id, user_id, phone_no, mail_address, user_name);
    SELECT
      save_project_alteration(operator_id, concat(user_name, '加入项目'), operate_time, project_id);
  END$$

DELIMITER ;
DROP PROCEDURE IF EXISTS `delete_project`;
DELIMITER $$
CREATE DEFINER =`root`@`localhost` PROCEDURE `delete_project`(project_Id INT)
  BEGIN
    DELETE FROM pm.project
    WHERE projectId = project_Id;
    DELETE FROM pm.crew
    WHERE projectId = project_Id;
    DELETE FROM work
    WHERE projectId = project_id;
  END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `create_project`;
DELIMITER $$
CREATE DEFINER =`root`@`localhost` PROCEDURE `create_project`(project_name VARCHAR(50), content VARCHAR(50),
                                                              create_time  DATETIME, create_user_id INT)
  BEGIN
    DECLARE project_id INT;
    INSERT INTO project (projectName, content, createTime, createUserId)
      VALUES (project_name, content, create_time, create_user_id);
    SET project_id = LAST_INSERT_ID();
    SELECT
      save_project_alteration(create_user_id, concat('创建项目:', project_name), create_time, project_id);
  END$$
DELIMITER ;
#用户===================================================================================================================
DELIMITER $$
DROP PROCEDURE IF EXISTS `login_byAuthCode`;
CREATE DEFINER =`root`@`localhost` PROCEDURE `login_byAuthCode`(phone_no  VARCHAR(50), auth_code VARCHAR(50),
  OUT                                                           isSuccess BOOLEAN)
  BEGIN
    SELECT
      @authCode := authCode
    FROM user_authCode
    WHERE phoneNo = phone_no;
    IF @authCode = auth_code
    THEN
      INSERT INTO user (phoneNo) VALUES (phone_no);
      UPDATE crew
      SET userId=@userId
      WHERE phoneNo = phone_no;
      SET isSuccess = 1;
    ELSE
      SET isSuccess = 0;
    END IF;
  END$$
DELIMITER ;
#工作===================================================================================================================
DROP PROCEDURE IF EXISTS `create_work`;
DELIMITER $$
CREATE DEFINER =`root`@`localhost` PROCEDURE `create_work`(operator_id INT, operate_time DATETIME,
                                                           work_name   VARCHAR(50),
                                                           backup      VARCHAR(255), create_time DATETIME,
                                                           project_id  INT, create_user_id INT, crew_id INT,
                                                           deadline    DATETIME)
  BEGIN
    INSERT INTO work (workName, backup, createTime, projectId, createUserId, crewId, deadline, isDone)
      VALUES (work_name, backup, create_time, project_id, create_user_id, crew_id, deadline, FALSE);
    SELECT
      save_project_alteration(operator_id, concat('创建工作:', work_name), operate_time, project_id);
  END$$
DELIMITER ;