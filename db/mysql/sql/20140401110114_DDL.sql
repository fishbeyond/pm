CREATE TABLE pm_test (
  userId      VARCHAR(50),
  userName    VARCHAR(50),
  phoneNo     VARCHAR(50) NOT NULL UNIQUE,
  mailAddress VARCHAR(50),
  password    VARCHAR(50),
  authCode    INT,
  PRIMARY KEY (userId));