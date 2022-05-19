package com.opensource.grip.conner.generate.enums;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * 文件类型枚举
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public enum FileFormatEnum {
    /**
     * java
     */
    JAVA(".java"),

    HTML(".html"),

    XML(".xml"),
    ;

    FileFormatEnum(String suffix) {
        this.suffix = suffix;
    }

    @Getter
    private final String suffix;

    public static FileFormatEnum findBySuffix(String suffix) {
        Preconditions.checkArgument(!StringUtils.isEmpty(suffix), "后缀不存在");
        Optional<FileFormatEnum> any = Arrays.stream(values()).filter(e -> e.getSuffix().equals(suffix)).findAny();
        Preconditions.checkArgument(any.isPresent(), "后缀不存在");
        return any.get();
    }
}
