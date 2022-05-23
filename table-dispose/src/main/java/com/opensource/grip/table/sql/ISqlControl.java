package com.opensource.grip.table.sql;

/**
 * @author wangmin
 */
public interface ISqlControl extends ISqlStep, ISelectStep, IFromStep, IWhereStep, IOtherStep, IInsertStep, IUpdateStep {

    /**
     * 查询
     *
     * @param fields 文件
     * @return ISelectStep
     */
    @Override
    ISelectStep select(String... fields);

    /**
     * insert
     *
     * @param tableName 表名
     * @return IInsertStep
     */
    @Override
    IInsertStep insert(String tableName);

    /**
     * insert
     *
     * @param clazz 类
     * @param <T>   T
     * @return IInsertStep
     */
    @Override
    <T> IInsertStep insert(Class<T> clazz);

    /**
     * update
     *
     * @param tableName 表名
     * @return IUpdateStep
     */
    @Override
    IUpdateStep update(String tableName);

    /**
     * from
     *
     * @param tableName 表名
     * @return IFromStep
     */
    @Override
    IFromStep from(String tableName);

    /**
     * from
     *
     * @param clazz 类
     * @param <T>   T
     * @return IFromStep
     */
    @Override
    <T> IFromStep from(Class<T> clazz);

    /**
     * where
     *
     * @param field     字段
     * @param compareTo 比较符
     * @param value     值
     * @param <T>       T
     * @return IWhereStep
     */
    @Override
    <T> IWhereStep where(String field, String compareTo, T value);

    /**
     * and
     *
     * @param field     字段
     * @param compareTo 比较符号
     * @param value     值
     * @param <T>       T
     * @return IWhereStep
     */
    @Override
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
    @Override
    <T> IWhereStep or(String field, String compareTo, T value);

    /**
     * set
     *
     * @param field     字段
     * @param compareTo 比较符
     * @param value     值
     * @return IUpdateStep
     */
    @Override
    IUpdateStep set(String field, String compareTo, Object value);

    /**
     * set
     *
     * @param key   键
     * @param value 值
     * @return IInsertStep
     */
    @Override
    IInsertStep set(String key, Object value);

    /**
     * limit
     *
     * @return IOtherStep
     */
    @Override
    IOtherStep limit();

    /**
     * limit
     *
     * @param limit limit
     * @return IOtherStep
     */
    @Override
    IOtherStep limit(Integer limit);
}
