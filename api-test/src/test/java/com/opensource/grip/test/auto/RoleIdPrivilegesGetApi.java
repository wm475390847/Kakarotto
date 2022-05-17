package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 角色权限点查询 吕楠青
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class RoleIdPrivilegesGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String roleId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/api/role/{roleId}/privileges")
                .method("GET")
                .contentType("application/json")
                .urlParam("roleId", roleId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
