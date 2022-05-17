package com.opensource.grip.generate.parse;

import java.util.List;

/**
 * 自动生成文件服务
 *
 * @author wangmin
 * @date 2021/12/18 5:30 下午
 */
public interface IParse<T> {

    /**
     * 解析为接口信息实体类
     *
     * @return List<T>
     */
    List<T> execute();
}
