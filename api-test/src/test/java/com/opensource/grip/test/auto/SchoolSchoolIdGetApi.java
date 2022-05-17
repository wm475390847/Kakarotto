package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 根据学校ID查询学校信息 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolSchoolIdGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String schoolId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/{schoolId}")
                .method("GET")
                .contentType("application/json")
                .urlParam("schoolId", schoolId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
