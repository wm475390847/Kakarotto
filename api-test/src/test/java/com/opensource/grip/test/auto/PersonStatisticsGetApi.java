package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 学生回合个人数据统计 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class PersonStatisticsGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String studentId;
    /**
     * 描述：
     * 是否必填：true
     */
    private final String workId;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String gameDictId;
    /**
     * 描述：作业，HOMEWORK
     * 是否必填：true
     */
    private final String type;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/games/person/statistics")
                .method("GET")
                .contentType("application/json")
                .urlParam("studentId", studentId)
                .urlParam("workId", workId)
                .urlParam("gameDictId", gameDictId)
                .urlParam("type", type)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
