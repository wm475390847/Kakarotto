package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 修改账号 吕楠青
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class ApiAccountPutApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String roleIds;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String accountName;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String mobile;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String action;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String systemKey;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String userId;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String email;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String username;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/api/account")
                .method("PUT")
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
        object.put("action", action);
        object.put("systemKey", systemKey);
        object.put("userId", userId);
        object.put("email", email);
        object.put("username", username);
        return object;
    }
}
