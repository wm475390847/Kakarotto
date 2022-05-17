package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 根据学生ID查询学生信息 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class StudentsStudentIdGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String studentId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/students/{studentId}")
                .method("GET")
                .contentType("application/x-www-form-urlencoded")
                .urlParam("studentId", studentId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
