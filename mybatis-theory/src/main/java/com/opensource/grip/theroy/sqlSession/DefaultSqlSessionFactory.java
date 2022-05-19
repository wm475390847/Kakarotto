package com.opensource.grip.theroy.sqlSession;

import com.opensource.grip.theroy.pojo.Configuration;

/**
 * @author wangmin
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
