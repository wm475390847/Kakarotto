package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 创建角色 吕楠青
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class ApiRolePostApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String name;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String privilegeIds;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/api/role")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("privilegeIds", privilegeIds);
        return object;
    }
}
