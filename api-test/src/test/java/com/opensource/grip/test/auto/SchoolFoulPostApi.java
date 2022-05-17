package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 犯规 梦真
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolFoulPostApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String positionId;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String venueId;
    /**
     * 描述：犯规详情
     * 是否必填：true
     */
    private final String foulDetail;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String deviceId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/foul")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("positionId", positionId);
        object.put("venueId", venueId);
        object.put("foulDetail", foulDetail);
        object.put("deviceId", deviceId);
        return object;
    }
}
