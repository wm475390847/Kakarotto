package com.opensource.grip.table.sql;

/**
 * @author wangmin
 */
public interface IFromStep {

    /**
     * where
     *
     * @param field     字段
     * @param compareTo 比较符
     * @param value     值
     * @param <T>       T
     * @return IWhereStep
     */
    <T> IWhereStep where(String field, String compareTo, T value);

    /**
     * end
     *
     * @return Sql
     */
    Sql end();
}
