package com.opensource.grip.table.sql;

/**
 * @author wangmin
 */
public interface ISqlStep {

    /**
     * select
     *
     * @param fields 字段
     * @return ISelectStep
     */
    ISelectStep select(String... fields);

    /**
     * insert
     *
     * @param tableName 表名
     * @return IInsertStep
     */
    IInsertStep insert(String tableName);

    /**
     * insert
     *
     * @param clazz 类
     * @param <T>   T
     * @return IInsertStep
     */
    <T> IInsertStep insert(Class<T> clazz);

    /**
     * update
     *
     * @param tableName 表名
     * @return IUpdateStep
     */
    IUpdateStep update(String tableName);
}