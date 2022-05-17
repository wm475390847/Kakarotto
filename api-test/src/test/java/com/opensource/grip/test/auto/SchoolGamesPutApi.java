package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 修改回合 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolGamesPutApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String studentId;
    /**
     * 描述：回合ID
     * 是否必填：true
     */
    private final String id;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String gameDictId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/games")
                .method("PUT")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("studentId", studentId);
        object.put("id", id);
        object.put("gameDictId", gameDictId);
        return object;
    }
}
