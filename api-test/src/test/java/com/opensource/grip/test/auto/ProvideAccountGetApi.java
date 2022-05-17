package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 账号绑定的角色列表 吕楠青
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class ProvideAccountGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String project;
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
    /**
     * 描述：
     * 是否必填：true
     */
    private final String roleName;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/api/role/list/provide/account")
                .method("GET")
                .contentType("application/json")
                .urlParam("project", project)
                .urlParam("pageNo", pageNo)
                .urlParam("pageSize", pageSize)
                .urlParam("roleName", roleName)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
