package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 设备流画面截取 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class StreamSnapshotGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String deviceId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/devices/{deviceId}/stream/snapshot")
                .method("GET")
                .contentType("application/json")
                .urlParam("deviceId", deviceId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
