package com.opensource.grip.http.enums;

import com.google.common.base.Preconditions;
import com.opensource.grip.http.core.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public enum MethodEnum {

    /**
     * post请求
     */
    POST("POST", new PostCommand()),

    GET("GET", new GetCommand()),

    DELETE("DELETE", new DeleteCommand()),

    PUT("PUT", new PutCommand()),
    ;

    MethodEnum(String type, AbstractCommand command) {
        this.type = type;
        this.command = command;
    }

    @Getter
    private final String type;

    @Getter
    private final AbstractCommand command;

    public static MethodEnum findEnumByType(String type) {
        Preconditions.checkArgument(type != null, "类型不存在");
        Optional<MethodEnum> any = Arrays.stream(MethodEnum.values()).filter(e -> e.getType().equals(type)).findAny();
        Preconditions.checkArgument(any.isPresent(), "类型不存在");
        return any.get();
    }
}
