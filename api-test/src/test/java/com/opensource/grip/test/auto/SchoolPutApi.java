package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 修改学校信息 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolPutApi extends BaseHttpApi {
    /**
     * 描述：封面
     * 是否必填：false
     */
    private final String coverUrl;
    /**
     * 描述：详细地址
     * 是否必填：false
     */
    private final String address;
    /**
     * 描述：省份
     * 是否必填：false
     */
    private final String province;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String macs;
    /**
     * 描述：学校名称
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：租户ID
     * 是否必填：false
     */
    private final String tenantId;
    /**
     * 描述：描述
     * 是否必填：false
     */
    private final String description;
    /**
     * 描述：位置
     * 是否必填：false
     */
    private final String location;
    /**
     * 描述：联系电话
     * 是否必填：false
     */
    private final String telephone;
    /**
     * 描述：学校项目，示例：[0,1,2]
     * 是否必填：false
     */
    private final String schoolGames;
    /**
     * 描述：学校ID
     * 是否必填：true
     */
    private final String id;
    /**
     * 描述：介绍视频
     * 是否必填：false
     */
    private final String introVideoUrl;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school")
                .method("PUT")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("coverUrl", coverUrl);
        object.put("address", address);
        object.put("province", province);
        object.put("macs", macs);
        object.put("name", name);
        object.put("tenantId", tenantId);
        object.put("description", description);
        object.put("location", location);
        object.put("telephone", telephone);
        object.put("schoolGames", schoolGames);
        object.put("id", id);
        object.put("introVideoUrl", introVideoUrl);
        return object;
    }
}
