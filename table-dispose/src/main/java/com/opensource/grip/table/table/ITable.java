package com.opensource.grip.table.table;

import com.opensource.grip.table.model.IKeyBuilder;
import com.opensource.grip.table.property.IProperty;
import com.opensource.grip.table.row.IRow;

/**
 * @author wangmin
 */
public interface ITable extends IProperty {

    String getTableName();

    /**
     * 数据表加载
     *
     * @return boolean
     */
    boolean load();

    /**
     * 添加一行
     *
     * @param row 行
     * @return boolean
     */
    boolean addRow(IRow row);

    /**
     * 获取表的路径
     *
     * @return String 路径信息
     */
    String getPath();

    /**
     * 设置表的路径，由实现类赋予实际含义
     *
     * @param path 路径信息
     */
    void setPath(String path);

    /**
     * 获取指定的行
     *
     * @param key 行的key，大小写不敏感
     * @return IRow
     */
    IRow getRow(String key);

    /**
     * 返回所有的行数据
     *
     * @return IRow[] 所有行数据，有顺序
     */
    IRow[] getRows();

    /**
     * 清空表
     */
    void clear();

    /**
     * 设置主键构造器
     *
     * @param keyBuilder 主键构造器
     */
    void setKeyBuilder(IKeyBuilder keyBuilder);
}
