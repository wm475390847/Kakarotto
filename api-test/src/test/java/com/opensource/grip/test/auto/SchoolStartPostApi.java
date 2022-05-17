package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 场地开始检测 梦真
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolStartPostApi extends BaseHttpApi {
    /**
     * 描述：设备列表
     * 是否必填：false
     */
    private final String devices;
    /**
     * 描述：场地活动名称
     * 是否必填：false
     */
    private final String gameDictName;
    /**
     * 描述：场地名称
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：场地id
     * 是否必填：false
     */
    private final Integer id;
    /**
     * 描述：场地活动类型
     * 是否必填：false
     */
    private final Integer gameDictId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/start")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("devices", devices);
        object.put("gameDictName", gameDictName);
        object.put("name", name);
        object.put("id", id);
        object.put("gameDictId", gameDictId);
        return object;
    }
}
