package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 项目列表 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class DictListGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String pageNo;
    /**
     * 描述：
     * 是否必填：true
     */
    private final String pageSize;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/game/dict/list")
                .method("GET")
                .contentType("application/json")
                .urlParam("pageNo", pageNo)
                .urlParam("pageSize", pageSize)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
