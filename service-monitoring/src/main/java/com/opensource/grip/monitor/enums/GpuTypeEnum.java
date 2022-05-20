package com.opensource.grip.monitor.enums;

import com.google.common.base.Preconditions;
import com.opensource.grip.monitor.gpu.IGpuParse;
import com.opensource.grip.monitor.gpu.IntelGpuParse;
import com.opensource.grip.monitor.gpu.NvidiaGpuParse;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author wangmin
 * @date 2022/4/14 09:10
 */
public enum GpuTypeEnum {

    /**
     * 英特尔
     */
    INTEL("Intel", "", new IntelGpuParse()),

    /**
     * 英伟达
     */
    NVIDIA("Nvidia", "nvidia-smi -q -x", new NvidiaGpuParse()),

    ;

    GpuTypeEnum(String name, String command, IGpuParse gpuParse) {
        this.name = name;
        this.command = command;
        this.gpuParse = gpuParse;
    }

    @Getter
    private final String name;

    @Getter
    private final String command;

    @Getter
    private final IGpuParse gpuParse;

    public static GpuTypeEnum findByGpuType(String typeName) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(typeName), "gpu类型不存在");
        Optional<GpuTypeEnum> any = Arrays.stream(values()).filter(e -> e.getName().equals(typeName)).findAny();
        return any.orElse(null);
    }

    public static String findCommandByGpuParse(IGpuParse gpuParse) {
        Preconditions.checkArgument(gpuParse != null, "gpu解析类不存在");
        Optional<GpuTypeEnum> any = Arrays.stream(values()).filter(e -> e.getGpuParse().equals(gpuParse)).findAny();
        Preconditions.checkArgument(any.isPresent(), "gpu解析类不存在");
        return any.get().getCommand();
    }
}
