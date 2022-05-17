package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 根据活动状态展示回合列表tab枚举 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class WorksTabByStatusGetApi extends BaseHttpApi {
    /**
     * 描述：活动状态:WAITING("0", "未开始"),ALGO_INIT("1", "算法启动中"),PROCESSING("2", "进行中"),ALGO_STOP("3", "算法停止中"),FINISHED("4", "已结束");
     * 是否必填：true
     */
    private final String status;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/works/tabByStatus")
                .method("GET")
                .contentType("application/json")
                .urlParam("status", status)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
