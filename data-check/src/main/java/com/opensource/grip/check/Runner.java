package com.opensource.grip.check;

import com.alicloud.openservices.tablestore.model.PrimaryKeyValue;
import com.google.common.base.Preconditions;
import com.opensource.grip.check.po.AliYunConfigPO;
import com.opensource.grip.check.po.OtsTablePO;
import com.opensource.grip.check.po.RuleDataSourcePO;
import com.opensource.grip.table.container.ExcelContainer;
import com.opensource.grip.table.container.IContainer;
import com.opensource.grip.table.container.OtsContainer;
import com.opensource.grip.table.model.OtsPrimaryKey;
import com.opensource.grip.table.model.OtsPrimaryKeyBuilder;
import com.opensource.grip.table.row.IRow;
import com.opensource.grip.table.table.ITable;
import com.opensource.grip.util.TimeUtil;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 数据校验执行
 *
 * @author wangmin
 * @data 2021-06-17
 */
public class Runner {
    private final String rulePath;
    private final String shopId;
    private final String queryPrimaryKeyName;
    private String date;
    private IContainer ruleContainer;
    private List<OtsTablePO> otsTablePOList;

    public Runner(Builder builder) {
        this.rulePath = builder.rulePath;
        this.shopId = builder.shopId;
        this.queryPrimaryKeyName = builder.queryPrimaryKeyName;
        initRuleContainer();
    }

    /**
     * 对外提供
     * 获取结果集合
     *
     * @return 结果集合
     */
    public List<OtsTablePO> getOtsTableDataList() {
        this.load();
        return this.otsTablePOList;
    }

    /**
     * 对外提供
     * 获取字段规则表集合
     *
     * @return 规则表集合
     */
    public ITable[] getFieldRuleTables() {
        return ruleContainer.findTables(Constants.RULE_COLUMN_FIELD);
    }

    private void load() {
        ITable dataContainerTable = ruleContainer.getTable(Constants.SHEET_TITLE_CONTAINER);
        dataContainerTable.load();
        Arrays.stream(dataContainerTable.getRows()).forEach(sheetOne -> {
            AliYunConfigPO config = new AliYunConfigPO();
            config.initConfig(sheetOne.getField(Constants.CONTAINER_COLUMN_PATH).getValue());
            ITable dataSourceTable = ruleContainer.getTable(Constants.SHEET_TITLE_DATA_SOURCE);
            dataSourceTable.load();
            List<OtsTablePO> list = new ArrayList<>();
            IRow[] dataSourceRows = dataSourceTable.getRows();
            RuleDataSourcePO ruleDataSourcePO = new RuleDataSourcePO();
            Arrays.stream(dataSourceRows).map(ruleDataSourcePO::initDataSource)
                    .map(newRuleDataSourcePO -> initOtsContainer(config, newRuleDataSourcePO)).map(IContainer::getTables)
                    .forEach(iTables -> Arrays.stream(iTables).map(otsTable -> initOtsTableData(otsTable, ruleDataSourcePO)).forEach(list::add));
            otsTablePOList = list;
        });
    }

    /**
     * 初始化OTSTableData数据
     * 通过拿到的阿里云数据与规则表得到OTSTableData
     *
     * @param otsTable         阿里云读取的ots表
     * @param ruleDataSourcePO 规则文件数据
     * @return OTSTableData
     */
    private OtsTablePO initOtsTableData(ITable otsTable, RuleDataSourcePO ruleDataSourcePO) {
        initDate(ruleDataSourcePO);
        OtsTablePO otsTablePO = new OtsTablePO();
        loadTable(otsTable, ruleDataSourcePO.getPrimaryKeys());
        IRow[] otsRows = otsTable.getRows();
        otsTablePO.setInstanceName(ruleDataSourcePO.getInstancePath());
        otsTablePO.setTableName(ruleDataSourcePO.getTablePath());
        otsTablePO.setSourceName(ruleDataSourcePO.getSourceName());
        otsTablePO.setRows(otsRows);
        return otsTablePO;
    }

    /**
     * 初始化时间
     * 根据规则文件判断获取阿里云数据的时间
     *
     * @param ruleDataSourcePO 规则文件数据
     */
    private void initDate(RuleDataSourcePO ruleDataSourcePO) {
        date = ruleDataSourcePO.getSourceName().contains("实时") ? TimeUtil.getFormat(new Date(), TimeUtil.FORMAT_DAY)
                : TimeUtil.addDay(new Date(), -1, TimeUtil.FORMAT_DAY);
    }

    /**
     * 加载表
     *
     * @param table       表对象
     * @param primaryKeys 主键
     */
    private void loadTable(ITable table, String[] primaryKeys) {
        //此处不设置主键无法执行
        OtsPrimaryKeyBuilder otsPrimaryKeyBuilder = initOtsPrimaryKeyBuilder(primaryKeys, queryPrimaryKeyName, shopId, date);
        table.setKeyBuilder(otsPrimaryKeyBuilder);
        table.load();
    }

    /**
     * 初始化规则容器
     */
    private void initRuleContainer() {
        IContainer container = new ExcelContainer.Builder().path(rulePath).build();
        container.init();
        ruleContainer = container;
    }

    /**
     * 初始化ots容器
     *
     * @param config           容器配置
     * @param ruleDataSourcePO 数据源
     * @return ots容器
     */

    private IContainer initOtsContainer(AliYunConfigPO config, RuleDataSourcePO ruleDataSourcePO) {
        IContainer otsContainer = new OtsContainer.Builder().endPoint(config.getEndPoint())
                .accessKeyId(config.getAccessKeyId()).accessKeySecret(config.getAccessKeySecret())
                .instanceName(ruleDataSourcePO.getInstancePath()).path(ruleDataSourcePO.getTablePath()).build();
        otsContainer.init();
        return otsContainer;
    }

    /**
     * 初始化ots主键构造器
     *
     * @param primaryValueNames   主键名集合
     * @param queryPrimaryKeyName 需要查询的主键名
     * @param scope               规则
     * @param date                日期
     * @return OTSPrimaryKeyBuilder 主键构造器
     */
    private OtsPrimaryKeyBuilder initOtsPrimaryKeyBuilder(String[] primaryValueNames, String queryPrimaryKeyName, String scope, String date) {
        OtsPrimaryKey.Builder inclusiveBuilder = new OtsPrimaryKey.Builder();
        Arrays.stream(primaryValueNames).map(e -> inclusiveBuilder.primaryKey(e, PrimaryKeyValue.INF_MIN)).filter(e -> e.containsKey(queryPrimaryKeyName))
                .forEach(e -> e.primaryKey(queryPrimaryKeyName, PrimaryKeyValue.fromString(scopeKeyGen(scope, date))));
        OtsPrimaryKey.Builder exclusiveBuilder = new OtsPrimaryKey.Builder();
        Arrays.stream(primaryValueNames).map(e -> exclusiveBuilder.primaryKey(e, PrimaryKeyValue.INF_MAX)).filter(e -> e.containsKey(queryPrimaryKeyName))
                .forEach(e -> e.primaryKey(queryPrimaryKeyName, PrimaryKeyValue.fromString(scopeKeyGen(scope, date))));
        return new OtsPrimaryKeyBuilder().inclusiveStartPrimaryKey(inclusiveBuilder.build()).exclusiveEndPrimaryKey(exclusiveBuilder.build());
    }

    /**
     * 主键加密方法
     *
     * @param scope 规则
     * @param date  日期
     * @return 加密后数据
     */

    private String scopeKeyGen(String scope, String date) {
        return scope + date;
    }

    @Setter
    @Accessors(chain = true, fluent = true)
    public static class Builder {
        private String rulePath;
        private String shopId;
        private String queryPrimaryKeyName;

        public Runner build() {
            Preconditions.checkNotNull(rulePath, "规则文件不能为空");
            Preconditions.checkNotNull(shopId, "门店id不能为空");
            Preconditions.checkNotNull(queryPrimaryKeyName, "主键查询不能为空");
            return new Runner(this);
        }
    }
}
