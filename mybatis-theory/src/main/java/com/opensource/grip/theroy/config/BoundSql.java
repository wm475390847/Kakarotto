package com.opensource.grip.theroy.config;

import com.opensource.grip.theroy.util.ParameterMapping;

import java.util.List;

/**
 * @author wangmin
 */
public class BoundSql {

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    private String sqlText;

    private List<ParameterMapping> parameterMappingList;

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
