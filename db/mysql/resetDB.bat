@echo ɾ�����ݿ�
mysql -uroot -proot -e "drop database if exists pm

@echo �½����ݿ�
mysql -uroot -proot -e "create database pm DEFAULT CHARACTER SET gbk COLLATE gbk_chinese_ci"

@echo ����changelog
call mvn package

@echo ִ�����ݿ����
call mvn dbdeploy:update
pause