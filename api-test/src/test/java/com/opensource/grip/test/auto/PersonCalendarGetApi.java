package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 学生一段时间任务记录展示 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class PersonCalendarGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String startTime;
    /**
     * 描述：
     * 是否必填：true
     */
    private final String endTime;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String gameDictId;
    /**
     * 描述：
     * 是否必填：true
     */
    private final String studentId;
    /**
     * 描述：作业，HOMEWORK
     * 是否必填：true
     */
    private final String type;
    /**
     * 描述：
     * 是否必填：true
     */
    private final String workId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/games/person/calendar")
                .method("GET")
                .contentType("application/json")
                .urlParam("startTime", startTime)
                .urlParam("endTime", endTime)
                .urlParam("gameDictId", gameDictId)
                .urlParam("studentId", studentId)
                .urlParam("type", type)
                .urlParam("workId", workId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
