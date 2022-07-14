package com.opensource.grip.check.po;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.opensource.grip.table.field.IField;
import com.opensource.grip.table.row.IRow;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ots表结果
 * 每个表都需要包含实例名、表名、所有行数据、数据源名称
 *
 * @author wangmin
 * @date 2021-06-18
 */
@Data
public class OtsTablePO implements Serializable {
    /**
     * 实例名称
     */
    String instanceName;

    /**
     * 表名
     */
    String tableName;

    /**
     * 表中所有行
     */
    IRow[] rows;

    /**
     * 数据源
     */
    String sourceName;

    /**
     * 行数数转为数据对象
     */
    List<OtsRowPO> rowDataList;

    /**
     * 如果想要行数据需执行此方法
     * 将所有行数据插入OTSRowData
     * 此处只拿了几个字段的数据，可以将所有字段组成json映射到OTSRowData，这样只用维护OTSRowData即可拿到想要的数据
     */
    public OtsTablePO initOtsRowData() {
        List<OtsRowPO> list = new LinkedList<>();
        Arrays.stream(rows).forEach(iRow -> {
            Preconditions.checkArgument(iRow != null, "行数据为空");
            //关键字不可为空
            IField regionField = iRow.findField("region");
            Preconditions.checkArgument(regionField != null, "字段region为空");
            //关键字不可为空
            IField startTimeField = iRow.findField("start_time");
            Preconditions.checkArgument(startTimeField != null, "字段start_time为空");
            String region = regionField.getValue();
            String startTime = startTimeField.getValue();
            String userId = iRow.findField("user_id") == null ? "N" : iRow.findField("user_id").getValue();
            JSONArray regions = JSONArray.parseArray(region);
            for (int i = 0; i < regions.size(); i++) {
                JSONObject jsonObject = regions.getJSONObject(i);
                jsonObject.put("user_id", userId);
                jsonObject.put("start_time", startTime);
                OtsRowPO otsRowPO = JSONObject.toJavaObject(jsonObject, OtsRowPO.class);
                list.add(otsRowPO);
            }
        });
        rowDataList = list;
        return this;
    }
}
