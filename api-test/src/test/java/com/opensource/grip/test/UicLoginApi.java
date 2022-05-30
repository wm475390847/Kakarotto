package com.opensource.grip.test;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.conner.http.api.Api;
import com.opensource.grip.conner.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * uic平台登陆
 *
 * @author wangmin
 * @date 2021/9/28 2:36 下午
 */
@Builder
public class UicLoginApi extends BaseHttpApi {
    private final String account;
    private final String password;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .bodyContent(getCurrentBody())
                .baseUrl("https://test.account.shuwen.com")
                .path("/api/uic/login/pw")
                .method("POST")
                .build();
    }

    @Override
    protected Object buildBody() {
        JSONObject object = new JSONObject();
        object.put("passwordx", password);
        object.put("credential", account);
        return object;
    }
}
