package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 下载报告 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class ReportDownloadGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String workId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/works/{workId}/report/download")
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
