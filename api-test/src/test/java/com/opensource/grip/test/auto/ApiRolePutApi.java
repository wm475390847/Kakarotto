package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 修改角色信息 吕楠青
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class ApiRolePutApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String userIds;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String name;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String id;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String type;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String privilegeIds;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String key;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String desc;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/api/role")
                .method("PUT")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("userIds", userIds);
        object.put("name", name);
        object.put("id", id);
        object.put("type", type);
        object.put("privilegeIds", privilegeIds);
        object.put("key", key);
        object.put("desc", desc);
        return object;
    }
}
