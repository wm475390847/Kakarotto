package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 删除模板 许祖德
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class TemplatesTemplateIdDeleteApi extends BaseHttpApi {
    /**
     * 描述：模板主键id
     * 是否必填：true
     */
    private final String templateId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/school/grade/templates/{templateId}")
                .method("DELETE")
                .contentType("application/x-www-form-urlencoded")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
