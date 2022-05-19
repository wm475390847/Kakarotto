package com.opensource.grip.theroy.pojo;

import lombok.Data;

/**
 * @author wangmin
 */
@Data
public class MappedStatements {

    /**
     * id标识
     */
    private String id;

    /**
     * 返回值类型
     */
    private String resultType;

    /**
     * 参数值类型
     */
    private String paramType;

    /**
     * sql语句
     */
    private String sql;

}
