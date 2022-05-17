package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 设备列表 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class ManagementListGetApi extends BaseHttpApi {
    /**
     * 描述：学校ID
     * 是否必填：true
     */
    private final String schoolId;
    /**
     * 描述：设备类型，1相机 2音柱 3LED
     * 是否必填：true
     */
    private final String type;
    /**
     * 描述：页码
     * 是否必填：true
     */
    private final String pageNo;
    /**
     * 描述：页数
     * 是否必填：true
     */
    private final String pageSize;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/devices/management/list")
                .method("GET")
                .contentType("application/json")
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
