package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 回合列表 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class GamesListGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String workGamesId;
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
     * 描述：0-准备中，1-进行中(包含2-待确认），3-已完成
     * 是否必填：false
     */
    private final String status;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String studentNumber;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String studentName;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String sex;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String className;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/games/list")
                .method("GET")
                .contentType("application/json")
                .urlParam("workGamesId", workGamesId)
                .urlParam("pageNo", pageNo)
                .urlParam("pageSize", pageSize)
                .urlParam("status", status)
                .urlParam("studentNumber", studentNumber)
                .urlParam("studentName", studentName)
                .urlParam("sex", sex)
                .urlParam("className", className)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
