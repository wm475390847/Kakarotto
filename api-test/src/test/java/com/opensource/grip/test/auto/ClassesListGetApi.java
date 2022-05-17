package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 班级列表 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class ClassesListGetApi extends BaseHttpApi {
    /**
     * 描述：学校ID
     * 是否必填：true
     */
    private final String schoolId;
    /**
     * 描述：班级名称，模糊搜索
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：
     * 是否必填：true
     */
    private final String pageNo;
    /**
     * 描述：
     * 是否必填：true
     */
    private final String pageSize;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String label;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/classes/list")
                .method("GET")
                .contentType("application/json")
                .urlParam("schoolId", schoolId)
                .urlParam("name", name)
                .urlParam("pageNo", pageNo)
                .urlParam("pageSize", pageSize)
                .urlParam("label", label)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
