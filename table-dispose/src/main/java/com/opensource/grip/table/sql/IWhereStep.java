package com.opensource.grip.table.sql;

/**
 * @author wangmin
 */
public interface IWhereStep {

    /**
     * and
     *
     * @param field     字段
     * @param compareTo 比较符
     * @param value     值
     * @param <T>       T
     * @return IWhereStep
     */
    <T> IWhereStep and(String field, String compareTo, T value);

    /**
     * or
     *
     * @param field     字段
     * @param compareTo 比较符
     * @param value     值
     * @param <T>       T
     * @return IWhereStep
     */
    <T> IWhereStep or(String field, String compareTo, T value);

    /**
     * limit
     *
     * @return IOtherStep
     */
    IOtherStep limit();

    /**
     * limit
     *
     * @param limit limit
     * @return IOtherStep
     */
    IOtherStep limit(Integer limit);

    /**
     * end
     *
     * @return Sql
     */
    Sql end();
}
