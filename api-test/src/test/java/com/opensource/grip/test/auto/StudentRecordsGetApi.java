package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 查询学生的活动项目记录 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class StudentRecordsGetApi extends BaseHttpApi {
    /**
     * 描述：学生ID
     * 是否必填：true
     */
    private final String studentId;
    /**
     * 描述：活动项目ID
     * 是否必填：true
     */
    private final String workGamesId;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String startTime;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String endTime;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/games/student/records")
                .method("GET")
                .contentType("application/json")
                .urlParam("studentId", studentId)
                .urlParam("workGamesId", workGamesId)
                .urlParam("startTime", startTime)
                .urlParam("endTime", endTime)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
