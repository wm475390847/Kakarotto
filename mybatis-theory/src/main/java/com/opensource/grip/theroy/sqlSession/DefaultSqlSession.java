package com.opensource.grip.theroy.sqlSession;

import com.opensource.grip.theroy.pojo.Configuration;
import com.opensource.grip.theroy.pojo.MappedStatements;

import java.util.List;

/**
 * @author wangmin
 */
public class DefaultSqlSession implements SqlSession {
    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object... params) throws Exception {
        //将要完成simpleExecutor里的query方法的调用
        MappedStatements mappedStatements = configuration.getMappedStatementsMap().get(statementId);
        Executor simpleExecutor = new SimpleExecutor();
        List<Object> list = simpleExecutor.query(configuration, mappedStatements, params);
        return (List<T>) list;
    }

    @Override
    public <E> E selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (E) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }
}
