package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 根据任务ID删除任务信息 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class WorksWorkIdDeleteApi extends BaseHttpApi {
    /**
     * 描述：班级ID
     * 是否必填：false
     */
    private final String classId;
    /**
     * 描述：创建时间
     * 是否必填：false
     */
    private final String createTime;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String workGames;
    /**
     * 描述：学校ID
     * 是否必填：false
     */
    private final String schoolId;
    /**
     * 描述：任务名称
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：班级名称
     * 是否必填：false
     */
    private final String className;
    /**
     * 描述：修改时间
     * 是否必填：false
     */
    private final String updateTime;
    /**
     * 描述：开始时间
     * 是否必填：false
     */
    private final String startTime;
    /**
     * 描述：任务ID
     * 是否必填：false
     */
    private final String id;
    /**
     * 描述：结束时间
     * 是否必填：false
     */
    private final String endTime;
    /**
     * 描述：考试-EXAM，作业-HOMEWORK
     * 是否必填：false
     */
    private final String type;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/works/{workId}")
                .method("DELETE")
                .contentType("application/x-www-form-urlencoded")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
