package com.opensource.grip.conner.http.enums;

import com.google.common.base.Preconditions;
import com.opensource.grip.conner.http.core.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 请求枚举
 * <P>请求映射相应的请求命令类
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public enum MethodEnum {

    /**
     * post请求
     */
    POST("POST", new PostCommand()),

    /**
     * get请求
     */
    GET("GET", new GetCommand()),

    /**
     * delete请求
     */
    DELETE("DELETE", new DeleteCommand()),

    /**
     * put请求
     */
    PUT("PUT", new PutCommand()),

    /**
     * multipart请求
     */
    MULTIPART("multipart", new MultipartCommand()),
    ;

    MethodEnum(String methodType, AbstractCommand command) {
        this.methodType = methodType;
        this.command = command;
    }

    @Getter
    private final String methodType;

    @Getter
    private final AbstractCommand command;

    /**
     * 通过请求方法类型查询对应的枚举值
     *
     * @param methodType 请求类型
     * @return 对应的MethodEnum
     */
    public static MethodEnum findEnumByType(String methodType) {
        Preconditions.checkArgument(methodType != null, "类型不存在");
        Optional<MethodEnum> any = Arrays.stream(MethodEnum.values())
                .filter(e -> e.getMethodType().equals(methodType)).findAny();
        Preconditions.checkArgument(any.isPresent(), "类型不存在");
        return any.get();
    }
}
