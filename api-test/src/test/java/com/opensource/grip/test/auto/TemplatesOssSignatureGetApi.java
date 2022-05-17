package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * oss上传认证 许祖德
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class TemplatesOssSignatureGetApi extends BaseHttpApi {
    /**
     * 描述：省份code, 目录区分
     * 是否必填：true
     */
    private final String province;
    /**
     * 描述：年级
     * 是否必填：true
     */
    private final String label;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/school/grade/templates/ossSignature")
                .method("GET")
                .contentType("application/json")
                .urlParam("province", province)
                .urlParam("label", label)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
