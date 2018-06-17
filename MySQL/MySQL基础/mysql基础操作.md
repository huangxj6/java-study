#### 1、数据库相关

- 连接命令行

    ```
    /opt/mysql5.7.12/bin/mysql -uroot -proot -h127.0.0.1
    ```

- 查看当前所有的数据库

    ```
    show databases;
    ```

- 进入某个数据库
    
    ```
    use template;
    ```

- 创建数据库
    - 语法

        ```
        CREATE {DATABASE | SCHEMA} [IF NOT EXISTS] db_name [DEFAULT] CHARACTER SET [=] charset_name
        ```
    
    - DATABASE和SCHEMA是相同的，任选其一
    
    - IF NOT EXISTS：如果创建的数据库存在，则只会报出warning，不写会报错

    - 例子
    
        ```
        CREATE DATABASE mytest DEFAULT CHARACTER SET utf8;
        ```

- 修改数据库

    ```
    ALTER DATABASE t1 CHARACTER SET gbk;
    ```
- 删除数据库

    ```
    drop database mytest;
    ```

- 查看创建数据库时所使用的sql语句

    ```
    show create database mytest;
    ```
    
#### 2、表相关

- 查看该数据库的所有表

    ```
    show tables;
    ```

- 查看某个表的结构

    ```
    describe task;
    ```
    
- 查看某个表的注释

    ```
    show full columns from tablename;
    ```


- 查看否个表创建时的sql语句

    ```
    show create table tablename\G;
    ```

#### 3、用户相关

- 创建用户

    ```
    create user 'test'@'%' identified by '123456';
    ```

- 查看当前所有用户

    ```
    select user,host from mysql.user;
    ```

- 修改用户密码
    
    ```
    alert user 'test'@'%' identified by '123456';
    ```
    
- 删除用户

    ```
    drop user 'test';
    ```

#### 4、常用命令

- 提示符修改
    - 修改方式1
    
        ```
        mysql -u用户名 -p密码 --prompt 提示符
        ```
    
    - 修改方式2，连接客户端后通过prompt修改提示符
    
        ```
        mysql> prompt \h
        ```
    
    - prompt 可以跟的参数如下：
    
        - \D 当前完整日期 
        - \d当前数据库 
        - \h当前服务器名称
        - \u当前的用户
        
- 显示当前服务器版本

    ```
    SELECT VERSION( ); 
    ```

- 显示当前日期时间
    
    ```
    SELECT NOW( );
    ```

- 显示当前用户
    
    ```
    SELECT USER( );
    ```

- 查看警告信息
    
    ```
    SHOW WARNINGS;
    ```
    
#### 5、数据库备份与恢复

- 备份
    
    ```
    mysqldump -uroot -p test2 > ./test2.sql
    ```

- 恢复

    ```
    mysql -uroot -p test2 < ./test2.sql 
    ```

