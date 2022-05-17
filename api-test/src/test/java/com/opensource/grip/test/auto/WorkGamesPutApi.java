package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 编辑子项 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class WorkGamesPutApi extends BaseHttpApi {
    /**
     * 描述：活动项目ID
     * 是否必填：false
     */
    private final String workGamesId;
    /**
     * 描述：0-自动、1-手动
     * 是否必填：false
     */
    private final String  operation;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/work/games")
                .method("PUT")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("workGamesId", workGamesId);
        object.put(" operation",  operation);
        return object;
    }
}
