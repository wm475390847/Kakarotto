package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 新增班级信息 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolClassesPostApi extends BaseHttpApi {
    /**
     * 描述：学校ID
     * 是否必填：false
     */
    private final String schoolId;
    /**
     * 描述：班级名称
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：分组类型：1-一年级，2-二年级
     * 是否必填：false
     */
    private final String label;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/classes")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("schoolId", schoolId);
        object.put("name", name);
        object.put("label", label);
        return object;
    }
}
