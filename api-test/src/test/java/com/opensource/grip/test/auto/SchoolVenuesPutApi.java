package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 修改场地信息 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolVenuesPutApi extends BaseHttpApi {
    /**
     * 描述：主机ID
     * 是否必填：false
     */
    private final String computerId;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String createTime;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String ledReqInfo;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String soundReqInfo;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String devices;
    /**
     * 描述：学校ID
     * 是否必填：false
     */
    private final String schoolId;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：场地应用项目名称
     * 是否必填：false
     */
    private final String gameDictName;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String updateTime;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String id;
    /**
     * 描述：场地应用项目ID
     * 是否必填：false
     */
    private final String gameDictId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/venues")
                .method("PUT")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("computerId", computerId);
        object.put("createTime", createTime);
        object.put("ledReqInfo", ledReqInfo);
        object.put("soundReqInfo", soundReqInfo);
        object.put("devices", devices);
        object.put("schoolId", schoolId);
        object.put("name", name);
        object.put("gameDictName", gameDictName);
        object.put("updateTime", updateTime);
        object.put("id", id);
        object.put("gameDictId", gameDictId);
        return object;
    }
}
