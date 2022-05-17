package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 学生任务总进度 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class StudentsAggScheduleGetApi extends BaseHttpApi {
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
     * 描述：long
     * 是否必填：false
     */
    private final String startTime;
    /**
     * 描述：long
     * 是否必填：false
     */
    private final String endTime;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/works/{workId}/students/aggSchedule")
                .method("GET")
                .contentType("application/json")
                .urlParam("pageNo", pageNo)
                .urlParam("pageSize", pageSize)
                .urlParam("startTime", startTime)
                .urlParam("endTime", endTime)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
