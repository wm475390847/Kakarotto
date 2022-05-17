package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 坑位重置（回合结束） 梦真
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolPositionResetPostApi extends BaseHttpApi {
    /**
     * 描述：坑位id
     * 是否必填：true
     */
    private final Integer positionId;
    /**
     * 描述：场地id
     * 是否必填：true
     */
    private final Integer venueId;
    /**
     * 描述：运动类型id
     * 是否必填：true
     */
    private final Integer gameDictId;
    /**
     * 描述：设备id
     * 是否必填：true
     */
    private final Integer deviceId;
    /**
     * 描述：回合id
     * 是否必填：true
     */
    private final Integer roundId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/positionReset")
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
        object.put("gameDictId", gameDictId);
        object.put("deviceId", deviceId);
        object.put("roundId", roundId);
        return object;
    }
}
