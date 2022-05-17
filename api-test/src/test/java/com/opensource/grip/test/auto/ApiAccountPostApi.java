package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 新增账号 吕楠青
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class ApiAccountPostApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String roleIds;
    /**
     * 描述：账号名
     * 是否必填：true
     */
    private final String accountName;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String mobile;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String email;
    /**
     * 描述：用户名
     * 是否必填：true
     */
    private final String username;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/api/account")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("roleIds", roleIds);
        object.put("accountName", accountName);
        object.put("mobile", mobile);
        object.put("email", email);
        object.put("username", username);
        return object;
    }
}
