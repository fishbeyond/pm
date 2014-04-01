@echo 删除数据库
mysql -uroot -proot -e "drop database if exists pm

@echo 新建数据库
mysql -uroot -proot -e "create database pm DEFAULT CHARACTER SET gbk COLLATE gbk_chinese_ci"

@echo 创建changelog
call mvn package

@echo 执行数据库更新
call mvn dbdeploy:update
pause