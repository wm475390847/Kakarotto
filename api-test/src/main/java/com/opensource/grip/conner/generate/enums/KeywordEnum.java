package com.opensource.grip.conner.generate.enums;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public enum KeywordEnum {
    /**
     * 包名关键字package
     */
    PACKAGE("package", "Package"),

    IMPORT("import", "Import"),

    PUBLIC("public", "Public"),
    ;

    KeywordEnum(String keyword, String transfer) {
        this.keyword = keyword;
        this.transfer = transfer;
    }

    @Getter
    public final String keyword;
    @Getter
    public final String transfer;

    public static KeywordEnum findByKeyword(String keyword) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(keyword), "关键字不存在");
        Optional<KeywordEnum> any = Arrays.stream(values()).filter(e -> e.getKeyword().equals(keyword)).findAny();
        Preconditions.checkArgument(any.isPresent(), "关键字不存在");
        return any.get();
    }

    /**
     * 转换关键词
     *
     * @param str str
     * @return string
     */
    public static String transferKeyword(String str) {
        return Arrays.stream(KeywordEnum.values()).filter(e -> str.equals(e.getKeyword()))
                .map(e -> str.replace(e.getKeyword(), e.getTransfer())).findFirst().orElse(str);
    }
}
