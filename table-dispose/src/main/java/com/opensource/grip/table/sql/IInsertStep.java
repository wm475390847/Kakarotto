package com.opensource.grip.table.sql;

/**
 * @author wangmin
 */
public interface IInsertStep {

    /**
     * set
     *
     * @param key   键
     * @param value 值
     * @return IInsertStep
     */
    IInsertStep set(String key, Object value);

    /**
     * end
     *
     * @return Sql
     */
    Sql end();
}
