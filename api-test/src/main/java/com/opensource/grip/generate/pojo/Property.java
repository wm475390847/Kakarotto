package com.opensource.grip.generate.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 参数属性
 *
 * @author wangmin
 * @date 2021/9/30 2:47 下午
 */
@Data
@Accessors(chain = true)
public class Property implements Serializable {

    /**
     * 字段
     */
    private String key;

    /**
     * 字段值
     */
    private String value;

    /**
     * 是否必填
     */
    private String required;

    /**
     * 字段描述
     */
    private String description;

    /**
     * 字段类型
     */
    private String type;
}
