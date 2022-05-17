package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 学生信息表格导入 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class StudentsImportPostApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String classId;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String schoolId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/students/import")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("classId", classId);
        object.put("schoolId", schoolId);
        return object;
    }
}
