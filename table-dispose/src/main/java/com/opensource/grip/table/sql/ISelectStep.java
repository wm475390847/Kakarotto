package com.opensource.grip.table.sql;

/**
 * @author wangmin
 */
public interface ISelectStep {

    /**
     * from
     *
     * @param tableName 表名
     * @return IFromStep
     */
    IFromStep from(String tableName);

    /**
     * from
     *
     * @param clazz 类
     * @param <T>   T
     * @return IFromStep
     */
    <T> IFromStep from(Class<T> clazz);

    /**
     * end
     *
     * @return Sql
     */
    Sql end();
}