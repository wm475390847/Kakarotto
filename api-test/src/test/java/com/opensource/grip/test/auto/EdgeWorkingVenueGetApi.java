package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 供算法获取学校正在进行作业或考试的的场地信息. 吕楠青
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class EdgeWorkingVenueGetApi extends BaseHttpApi {
    /**
     * 描述：运动类型id
     * 是否必填：true
     */
    private final String gameDictId;
    /**
     * 描述：学习id
     * 是否必填：true
     */
    private final String schoolId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/edge/workingVenue")
                .method("GET")
                .contentType("application/x-www-form-urlencoded")
                .urlParam("gameDictId", gameDictId)
                .urlParam("schoolId", schoolId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
