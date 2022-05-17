package com.opensource.grip.generate.core;

/**
 * 生成器接口
 *
 * @author wangmin
 * @date 2022/1/12 11:21 上午
 */
public interface IGenerator {

    /**
     * 执行
     */
    void execute();

    /**
     * 加载
     *
     * @return IMarker
     */
    IGenerator load();
}
