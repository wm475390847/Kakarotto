package com.opensource.grip.table.enums;

import com.opensource.grip.table.container.DbContainer;
import com.opensource.grip.table.container.ExcelContainer;
import com.opensource.grip.table.container.IContainer;
import lombok.Getter;

/**
 * 容器类型枚举
 *
 * @author wangmin
 */
public enum ContainerEnum {
    /**
     * 数据库地址
     */
    DB_BUSINESS_PORSCHE(new DbContainer.Builder().driverName("com.mysql.cj.jdbc.Driver")
            .jdbcUrl("jdbc:mysql://rm-2zeg4an1kr1437xu6no.mysql.rds.aliyuncs.com/business-porsche")
            .password("read_only").username("read_only").build()),

    DB_ONE_PIECE(new DbContainer.Builder().driverName("com.mysql.cj.jdbc.Driver")
            .jdbcUrl("jdbc:mysql://rm-2zeg4an1kr1437xu6no.mysql.rds.aliyuncs.com/onepiece")
            .password("qa_wr1234").username("qa_wr").build()),

    MALL_ONLINE(new DbContainer.Builder().driverName("com.mysql.cj.jdbc.Driver")
            .jdbcUrl("jdbc:mysql://retail-online-polardb.mysql.polardb.rds.aliyuncs.com/business-analysis")
            .password("winsense@mall123").username("mall_read_only").build()),

    EXCEL(new ExcelContainer.Builder().buildContainer()),
    ;

    ContainerEnum(IContainer container) {
        this.container = container;
    }

    @Getter
    private final IContainer container;
}
