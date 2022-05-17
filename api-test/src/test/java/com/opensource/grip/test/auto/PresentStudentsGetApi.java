package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 根据classId和gameDictId查询当前举手的学生 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class PresentStudentsGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String classId;
    /**
     * 描述：
     * 是否必填：true
     */
    private final String gameDictId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/works/games/present/students")
                .method("GET")
                .contentType("application/json")
                .urlParam("classId", classId)
                .urlParam("gameDictId", gameDictId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
