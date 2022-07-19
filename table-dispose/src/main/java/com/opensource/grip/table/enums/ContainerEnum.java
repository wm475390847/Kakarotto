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
            .jdbcUrl("")
            .password("").username("").build()),

    EXCEL(new ExcelContainer.Builder().

            buildContainer()),
    ;

    ContainerEnum(IContainer container) {
        this.container = container;
    }

    @Getter
    private final IContainer container;
}
