package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 新建回合 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolGamesPostApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String studentId;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String positionId;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String workGamesId;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String gameDictId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/games")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("studentId", studentId);
        object.put("positionId", positionId);
        object.put("workGamesId", workGamesId);
        object.put("gameDictId", gameDictId);
        return object;
    }
}
