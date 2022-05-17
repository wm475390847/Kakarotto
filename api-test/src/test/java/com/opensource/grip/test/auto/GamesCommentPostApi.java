package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 添加评价 梦真
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class GamesCommentPostApi extends BaseHttpApi {
    /**
     * 描述：评语,小于200字
     * 是否必填：false
     */
    private final String comment;
    /**
     * 描述：回合ID
     * 是否必填：false
     */
    private final String roundId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/games/comment")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("comment", comment);
        object.put("roundId", roundId);
        return object;
    }
}
