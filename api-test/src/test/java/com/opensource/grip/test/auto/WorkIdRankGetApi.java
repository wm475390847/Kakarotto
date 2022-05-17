package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 任务成绩等级数据统计 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class WorkIdRankGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String workId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/works/{workId}/rank")
                .method("GET")
                .contentType("application/json")
                .urlParam("workId", workId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
