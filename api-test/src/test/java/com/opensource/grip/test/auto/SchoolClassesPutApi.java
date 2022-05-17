package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 修改班级信息 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolClassesPutApi extends BaseHttpApi {
    /**
     * 描述：班级名称
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：班级ID
     * 是否必填：false
     */
    private final String id;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String label;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/classes")
                .method("PUT")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("id", id);
        object.put("label", label);
        return object;
    }
}
