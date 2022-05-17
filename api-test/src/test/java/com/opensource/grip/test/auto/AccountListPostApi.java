package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 账号列表 吕楠青
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class AccountListPostApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String roleIds;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String pageNo;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String pageSize;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String project;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String keyword;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/api/account/list")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("roleIds", roleIds);
        object.put("pageNo", pageNo);
        object.put("pageSize", pageSize);
        object.put("project", project);
        object.put("keyword", keyword);
        return object;
    }
}
