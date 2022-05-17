package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 修改密码 吕楠青
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class AccountPwPutApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String password;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String userId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/api/account/pw")
                .method("PUT")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("password", password);
        object.put("userId", userId);
        return object;
    }
}
