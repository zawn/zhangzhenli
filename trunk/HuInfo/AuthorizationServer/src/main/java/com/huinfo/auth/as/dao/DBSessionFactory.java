/*
 * Name   DBSessionFactory.java
 * Author ZhangZhenli
 * Created on 2012-5-15, 18:02:09
 *
 * Copyright (c) 2012 NanJing YiWuXian Technology Co., Ltd. All rights reserved
 *
 */

package com.huinfo.auth.as.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author ZhangZhenli
 */
public class DBSessionFactory {

    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DBSessionFactory.class);
    private static String configFile = "mybatis-config.xml";
    private static String sqlFile = "oauth.sql";
    private static SqlSessionFactory sqlSessionFactory = null;
    private static String environment;
    private static final String ENVIRONMENT_DEV = "Development";
    private static final String ENVIRONMENT_BAE = "BAE";
    private static final String ENVIRONMENT_ALIYUN = "Aliyun";

    static {
        // 根据系统属性判定运行环境;
        /**
         * BAE:
         * java.vendor=Sun Microsystems Inc.,
         * java.specification.version=1.6,
         * line.separator=
         * ,
         * java.class.version=50.0,
         * java.util.logging.config.file=/home/bae/instanceall/instance0/jetty/etc/logging.properties,
         * java.specification.name=Java Platform API Specification,
         * java.vendor.url=http://java.sun.com/,
         * java.vm.version=20.0-b12,
         * os.name=Linux,
         * temp.dir=/tmp/0.damuzhi.duapp.com-_-8200,
         * java.version=1.6.0-internal,
         * com.huinfo.mm.oauth2.com=haha,com.huinfo.mm.oauth2,
         * java.vm.specification.version=1.0,
         * user.dir=/home/bae,
         * java.specification.vendor=Sun Microsystems Inc.,
         * java.vm.specification.name=Java Virtual Machine Specification,
         * java.vm.vendor=Sun Microsystems Inc.,
         * file.separator=/,
         * path.separator=:,
         * java.vm.specification.vendor=Sun Microsystems Inc.,
         * java.vm.name=OpenJDK 64-Bit Server VM,
         * java.io.tmpdir=/tmp/0.damuzhi.duapp.com-_-8200/tmpfs,
         * file.encoding=ANSI_X3.4-1968,
         */
        /**
         * BAE官方没有特殊声明其运行环境属性,故判断其VM类型
         */
        String property = System.getProperty("java.vm.name");
        String osName = System.getProperty("os.name");
        //BAE:java.vm.name=OpenJDK 64-Bit Server VM,
        //Dev:java.vm.name=Java HotSpot(TM) 64-Bit Server VM,
        if (property.contains("Java HotSpot(TM)")) {
            if (osName.contains("Windows Server 2008")) {
                environment = ENVIRONMENT_ALIYUN;
            } else {
                environment = ENVIRONMENT_DEV;
            }
        } else {
            environment = ENVIRONMENT_BAE;
        }
    }

    private static void doCreateTable() {
        try {
            Connection connection = sqlSessionFactory.openSession().getConnection();
            Statement statement = connection.createStatement();
            try {
                ResultSet executeQuery = statement.executeQuery("select * from ibatis");
                int version = 0;
                try {
                    while (executeQuery.next()) {
                        version = executeQuery.getInt(1);
                    }
                } catch (SQLException ex) {
                }
                logger.info("DB version:" + version);
            } catch (SQLException ex) {
                try {
                    logger.info("Create the database tables from SQL file.");
                    ScriptRunner runner = new ScriptRunner(connection);
                    runner.runScript(Resources.getResourceAsReader(sqlFile));
                } catch (IOException ex1) {
                    logger.error(null, ex1);
                }
            }
        } catch (SQLException ex) {
            logger.info(null, ex);
        }
    }

    /**
     * 不可实例化
     */
    private DBSessionFactory() {
    }

    private static synchronized void init() {
        if (sqlSessionFactory == null) {
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(configFile);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, environment);
                doCreateTable();
            } catch (IOException ex) {
                logger.error(configFile, ex);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    logger.error(configFile, ex);
                }
            }
        }
    }

    public static SqlSession getSession() {
        if (sqlSessionFactory == null) {
            init();
        }
        return sqlSessionFactory.openSession();
    }
}
