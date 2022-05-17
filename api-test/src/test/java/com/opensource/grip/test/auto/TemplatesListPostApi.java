package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 成绩模板列表 许祖德
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class TemplatesListPostApi extends BaseHttpApi {
    /**
     * 描述：按省份查询
     * 是否必填：false
     */
    private final String province;
    /**
     * 描述：性别
     * 是否必填：false
     */
    private final Integer gender;
    /**
     * 描述：页码
     * 是否必填：false
     */
    private final Integer pageNo;
    /**
     * 描述：按名称筛选
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：年级
     * 是否必填：false
     */
    private final String lable;
    /**
     * 描述：页大小
     * 是否必填：false
     */
    private final Integer pageSize;
    /**
     * 描述：活动类型
     * 是否必填：false
     */
    private final Integer gameDictId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/school/grade/templates/list")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("province", province);
        object.put("gender", gender);
        object.put("pageNo", pageNo);
        object.put("name", name);
        object.put("lable", lable);
        object.put("pageSize", pageSize);
        object.put("gameDictId", gameDictId);
        return object;
    }
}
