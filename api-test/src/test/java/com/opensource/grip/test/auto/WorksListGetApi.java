package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 任务列表 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class WorksListGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：班级集合
     * 是否必填：false
     */
    private final String classIds;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String queryStartTime;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String queryEndTime;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String status;
    /**
     * 描述：
     * 是否必填：true
     */
    private final String schoolId;
    /**
     * 描述：任务类型，考试-EXAM，作业-HOMEWORK，比赛-RACE
     * 是否必填：true
     */
    private final String type;
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

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/works/list")
                .method("GET")
                .contentType("application/json")
                .urlParam("name", name)
                .urlParam("classIds", classIds)
                .urlParam("queryStartTime", queryStartTime)
                .urlParam("queryEndTime", queryEndTime)
                .urlParam("status", status)
                .urlParam("schoolId", schoolId)
                .urlParam("type", type)
                .urlParam("pageNo", pageNo)
                .urlParam("pageSize", pageSize)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
