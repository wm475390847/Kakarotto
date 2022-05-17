package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 回合预开始 莫凌强
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolPreRoundStartPostApi extends BaseHttpApi {
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

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/preRoundStart")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("venueId", venueId);
        object.put("gameDictId", gameDictId);
        return object;
    }
}
