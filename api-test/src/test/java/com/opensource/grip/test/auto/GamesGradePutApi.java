package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 修改回合成绩 吕楠青
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class GamesGradePutApi extends BaseHttpApi {
    /**
     * 描述：算法元数据
     * 是否必填：true
     */
    private final String metaData;
    /**
     * 描述：成绩
     * 是否必填：true
     */
    private final Double grade;
    /**
     * 描述：回合id
     * 是否必填：true
     */
    private final String id;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/games/grade")
                .method("PUT")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("metaData", metaData);
        object.put("grade", grade);
        object.put("id", id);
        return object;
    }
}
