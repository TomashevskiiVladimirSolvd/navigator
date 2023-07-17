package org.example.configuration;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyBatisSession {
    private static final Logger logger = LogManager.getLogger("MyBatisSession");

    public static SqlSession getSqlSession() {
        SqlSessionFactory sqlSessionFactory = MyBatisInitializer.getSqlSessionFactory();
        logger.debug("sqlSession has been opened");
        return sqlSessionFactory.openSession();
    }
}