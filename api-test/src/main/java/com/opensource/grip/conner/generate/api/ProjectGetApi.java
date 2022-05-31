package com.opensource.grip.conner.generate.api;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.conner.generate.pojo.Constant;
import com.opensource.grip.conner.http.api.Api;
import com.opensource.grip.conner.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 活动列表接口
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
@Builder
public class ProjectGetApi extends BaseHttpApi {
    private final Integer projectId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .baseUrl(Constant.BASE_URL)
                .host(Constant.HOST)
                .header("Cookie", Constant.TOKEN)
                .urlParam("id", projectId)
                .path("api/project/get")
                .method("GET")
                .build();
    }

    @Override
    protected JSONObject buildBody() {
        return null;
    }
}
