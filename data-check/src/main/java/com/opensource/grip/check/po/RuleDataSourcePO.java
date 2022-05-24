package com.opensource.grip.check.po;

import com.opensource.grip.check.Constants;
import com.opensource.grip.table.row.IRow;
import lombok.Getter;

import java.io.Serializable;

/**
 * 数据源表结构
 *
 * @author wangmin
 * @date 2021-06-16
 */
@Getter
public class RuleDataSourcePO implements Serializable {

    private String sourceName;
    private String instancePath;
    private String tablePath;
    private String container;
    private String[] primaryKeys;

    /**
     * 配置数据源
     *
     * @param row 行
     * @return 数据源
     */
    public RuleDataSourcePO initDataSource(IRow row) {
        this.primaryKeys = parse(row.getField(Constants.DATA_SOURCE_COLUMN_KEY).getValue());
        this.sourceName = row.getField(Constants.DATA_SOURCE_COLUMN_NAME).getValue();
        this.container = row.getField(Constants.DATA_SOURCE_COLUMN_CONTAINER).getValue();
        String[] paths = row.getField(Constants.DATA_SOURCE_COLUMN_PATH).getValue().split("/");
        this.instancePath = paths[0];
        this.tablePath = paths[1];
        return this;
    }

    public static String[] parse(String primaryKey) {
        return primaryKey.replace("]", "").replace("[", "").split(",");
    }
}