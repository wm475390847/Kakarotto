package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 学校列表 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolListGetApi extends BaseHttpApi {
    /**
     * 描述：地理位置
     * 是否必填：false
     */
    private final String location;
    /**
     * 描述：省份
     * 是否必填：false
     */
    private final String province;
    /**
     * 描述：学校名字
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：项目类型
     * 是否必填：false
     */
    private final String schoolGames;
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
                .path("/school/list")
                .method("GET")
                .contentType("application/json")
                .urlParam("location", location)
                .urlParam("province", province)
                .urlParam("name", name)
                .urlParam("schoolGames", schoolGames)
                .urlParam("pageNo", pageNo)
                .urlParam("pageSize", pageSize)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
