package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * url临时授权 梦真
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class OssStsGetApi extends BaseHttpApi {
    /**
     * 描述：文件地址
     * 是否必填：true
     */
    private final String key;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/oss/sts")
                .method("GET")
                .contentType("application/json")
                .urlParam("key", key)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
