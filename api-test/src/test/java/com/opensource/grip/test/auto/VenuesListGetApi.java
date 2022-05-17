package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 场地列表 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class VenuesListGetApi extends BaseHttpApi {
    /**
     * 描述：
     * 是否必填：true
     */
    private final String schoolId;
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
     * 描述：
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：
     * 是否必填：false
     */
    private final String id;
    /**
     * 描述：类型id
     * 是否必填：false
     */
    private final String gameDictId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/venues/list")
                .method("GET")
                .contentType("application/json")
                .urlParam("schoolId", schoolId)
                .urlParam("pageNo", pageNo)
                .urlParam("pageSize", pageSize)
                .urlParam("name", name)
                .urlParam("id", id)
                .urlParam("gameDictId", gameDictId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
