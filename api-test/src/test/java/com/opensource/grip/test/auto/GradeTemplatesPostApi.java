package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 标准成绩模板创建 许祖德
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class GradeTemplatesPostApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String province;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String gender;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String name;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final String label;
    /**
     * 描述：null
     * 是否必填：true
     */
    private final Integer gameDictId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/school/grade/templates")
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
        object.put("name", name);
        object.put("label", label);
        object.put("gameDictId", gameDictId);
        return object;
    }
}
