package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 结束任务 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class WorkIdFinishedPutApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String workId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/works/{workId}/finished")
                .method("PUT")
                .contentType("application/x-www-form-urlencoded")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
