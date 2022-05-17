package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 修改任务信息 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolWorksPutApi extends BaseHttpApi {
    /**
     * 描述：密码
     * 是否必填：false
     */
    private final String password;
    /**
     * 描述：班级ID集合
     * 是否必填：false
     */
    private final String classIds;
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
     * 描述：考试-EXAM，作业-HOMEWORK，比赛-RACE
     * 是否必填：false
     */
    private final String type;
    /**
     * 描述：准入方式，"0"为公共，"1"为需要密码
     * 是否必填：false
     */
    private final String accessMode;
    /**
     * 描述：活动配置
     * 是否必填：false
     */
    private final String descriptions;
    /**
     * 描述：性别，ALL("0", "全部"),     MAN("1", "男性"),     WOMAN("2", "女性")
     * 是否必填：false
     */
    private final String genderType;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/works")
                .method("PUT")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("password", password);
        object.put("classIds", classIds);
        object.put("workGames", workGames);
        object.put("schoolId", schoolId);
        object.put("name", name);
        object.put("startTime", startTime);
        object.put("id", id);
        object.put("endTime", endTime);
        object.put("type", type);
        object.put("accessMode", accessMode);
        object.put("descriptions", descriptions);
        object.put("genderType", genderType);
        return object;
    }
}
