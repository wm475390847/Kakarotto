package com.opensource.grip.theroy.sqlSession;

import java.util.List;

/**
 * @author wangmin
 */
public interface SqlSession {

    <T> List<T> selectList(String statementId, Object... params) throws Exception;

    <E> E selectOne(String statementId, Object... params) throws Exception;
}
