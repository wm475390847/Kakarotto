package com.opensource.grip.theroy.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangmin
 */
@Data
public class Configuration {
    /**
     * 数据源对象
     */
    private DataSource dataSource;

    /**
     * 一个个的mapper配置文件
     * key:statementId（namespace+id） value：封装好的mappedStatement对象
     */
    Map<String, MappedStatements> mappedStatementsMap = new HashMap<>();

}
