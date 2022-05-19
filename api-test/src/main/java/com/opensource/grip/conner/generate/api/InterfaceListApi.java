package com.opensource.grip.conner.generate.api;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.conner.generate.pojo.Constant;
import com.opensource.grip.conner.http.api.Api;
import com.opensource.grip.conner.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 接口列表
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
@Builder
public class InterfaceListApi extends BaseHttpApi {
    @Builder.Default
    private final Integer page = 1;
    @Builder.Default
    private final Integer limit = 20;
    private final Integer projectId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .host(Constant.HOST)
                .header("Cookie", Constant.TOKEN)
                .path("/api/interface/list")
                .urlParam("page", page)
                .urlParam("limit", limit)
                .urlParam("project_id", projectId)
                .method("GET")
                .build();
    }

    @Override
    protected JSONObject buildBody() {
        return null;
    }
}
