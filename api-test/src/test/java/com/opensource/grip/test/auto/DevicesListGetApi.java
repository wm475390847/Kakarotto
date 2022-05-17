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
public class DevicesListGetApi extends BaseHttpApi {
    /**
     * 描述：学校ID
     * 是否必填：false
     */
    private final String schoolId;
    /**
     * 描述：场地ID
     * 是否必填：false
     */
    private final String venueId;
    /**
     * 描述：设备ID
     * 是否必填：false
     */
    private final String id;
    /**
     * 描述：设备名称
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：设备类型，事件类型-EVENT
     * 是否必填：false
     */
    private final String type;
    /**
     * 描述：是否可用（查询未关联场地的设备列表）
     * 是否必填：false
     */
    private final String usable;
    /**
     * 描述：当前页
     * 是否必填：true
     */
    private final String pageNo;
    /**
     * 描述：每页显示条数
     * 是否必填：true
     */
    private final String pageSize;
    /**
     * 描述：关联的边缘端id
     * 是否必填：false
     */
    private final String schIotId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/devices/list")
                .method("GET")
                .contentType("application/json")
                .urlParam("schoolId", schoolId)
                .urlParam("venueId", venueId)
                .urlParam("id", id)
                .urlParam("name", name)
                .urlParam("type", type)
                .urlParam("usable", usable)
                .urlParam("pageNo", pageNo)
                .urlParam("pageSize", pageSize)
                .urlParam("schIotId", schIotId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
