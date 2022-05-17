package com.opensource.grip.generate.core;

/**
 * 生成器接口
 *
 * @author wangmin
 * @date 2022/5/17 13:05
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
