package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 场地停止检测 梦真
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolStopGetApi extends BaseHttpApi {
    /**
     * 描述：场地id
     * 是否必填：true
     */
    private final String venueId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/stop")
                .method("GET")
                .contentType("application/json")
                .urlParam("venueId", venueId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
