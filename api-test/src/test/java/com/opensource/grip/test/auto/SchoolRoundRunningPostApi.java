package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 回合中间结果 梦真
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolRoundRunningPostApi extends BaseHttpApi {
    /**
     * 描述：坑位id
     * 是否必填：true
     */
    private final String positionId;
    /**
     * 描述：当前分数
     * 是否必填：true
     */
    private final String currentScore;
    /**
     * 描述：场地id
     * 是否必填：true
     */
    private final String venueId;
    /**
     * 描述：活动类型id
     * 是否必填：true
     */
    private final String gameDictId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/roundRunning")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("positionId", positionId);
        object.put("currentScore", currentScore);
        object.put("venueId", venueId);
        object.put("gameDictId", gameDictId);
        return object;
    }
}
