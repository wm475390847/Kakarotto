package com.opensource.grip.theroy.sqlSession;


import com.opensource.grip.theroy.pojo.Configuration;
import com.opensource.grip.theroy.pojo.MappedStatements;

import java.util.List;

/**
 * @author wangmin
 */
public interface Executor {

    <T> List<T> query(Configuration configuration, MappedStatements mappedStatements, Object... params) throws Exception;
}
