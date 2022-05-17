package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 成绩标准模板导入 许祖德
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class TemplatesImportPostApi extends BaseHttpApi {
    /**
     * 描述：模板id
     * 是否必填：true
     */
    private final Integer id;
    /**
     * 描述：文件url
     * 是否必填：true
     */
    private final String url;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/school/grade/templates/import")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("url", url);
        return object;
    }
}
