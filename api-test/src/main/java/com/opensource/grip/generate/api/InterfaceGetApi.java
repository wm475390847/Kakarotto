package com.opensource.grip.generate.api;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.generate.pojo.Constant;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 活动列表接口
 *
 * @author wangmin
 * @date 2021/9/28 2:36 下午
 */
@Builder
public class InterfaceGetApi extends BaseHttpApi {
    private final Integer id;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .host(Constant.HOST)
                .header("Cookie", Constant.TOKEN)
                .urlParam("id", id)
                .path("api/interface/get")
                .method("GET")
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
