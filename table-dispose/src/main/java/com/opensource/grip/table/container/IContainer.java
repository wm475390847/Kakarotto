package com.opensource.grip.table.container;

import com.opensource.grip.table.property.IProperty;
import com.opensource.grip.table.table.ITable;

/**
 * 容器接口
 * 每种类型对应一种容器：db、excel
 *
 * @author wangmin
 * @data 2021-05-11
 */
public interface IContainer extends IProperty {

    /**
     * 初始化
     *
     * @return boolean
     */
    boolean init();

    /**
     * 获取文件路径或者sql
     *
     * @return String 路径
     */
    String getPath();

    /**
     * 放入路径
     *
     * @param path 路径
     */
    void setPath(String path);

    /**
     * 添加一个表
     *
     * @param table 表
     * @return boolean
     */
    boolean addTable(ITable table);

    /**
     * 获取所有表
     *
     * @return tables
     */
    ITable[] getTables();

    /**
     * 获取指定表
     *
     * @param tableName 表名
     * @return org.dragon.box.table
     */
    ITable getTable(String tableName);

    /**
     * 查询表名
     *
     * @param tableName 表名
     * @return tables
     */
    ITable[] findTables(String tableName);

    /**
     * 设置表
     *
     * @param table 新表
     * @return boolean
     */
    boolean setTable(ITable table);
}
