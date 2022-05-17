package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 回合开始（批量） 梦真
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolBatchRoundStartPostApi extends BaseHttpApi {
    /**
     * 描述：坑位id
     * 是否必填：true
     */
    private final String positionIds;
    /**
     * 描述：场地id
     * 是否必填：true
     */
    private final Integer venueId;
    /**
     * 描述：回合id
     * 是否必填：true
     */
    private final String roundIds;
    /**
     * 描述：系统毫秒时间戳)
     * 是否必填：true
     */
    private final Integer endTime;
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

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/batchRoundStart")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("positionIds", positionIds);
        object.put("venueId", venueId);
        object.put("roundIds", roundIds);
        object.put("endTime", endTime);
        object.put("gameDictId", gameDictId);
        object.put("deviceId", deviceId);
        return object;
    }
}
