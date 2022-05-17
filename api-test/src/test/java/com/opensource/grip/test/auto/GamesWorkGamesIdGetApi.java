package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 查询子项信息 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class GamesWorkGamesIdGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String workGamesId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/work/games/{workGamesId}")
                .method("GET")
                .contentType("application/json")
                .urlParam("workGamesId", workGamesId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
