package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 新增学生信息 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolStudentsPostApi extends BaseHttpApi {
    /**
     * 描述：班级ID
     * 是否必填：true
     */
    private final String classId;
    /**
     * 描述：人脸照片
     * 是否必填：false
     */
    private final String studentFaces;
    /**
     * 描述：学号
     * 是否必填：true
     */
    private final String studentNumber;
    /**
     * 描述：性别，ALL("0", "全部"),     MAN("1", "男性"),     WOMAN("2", "女性")	
     * 是否必填：true
     */
    private final String sex;
    /**
     * 描述：学校ID
     * 是否必填：true
     */
    private final String schoolId;
    /**
     * 描述：学生名称
     * 是否必填：true
     */
    private final String name;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/students")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("classId", classId);
        object.put("studentFaces", studentFaces);
        object.put("studentNumber", studentNumber);
        object.put("sex", sex);
        object.put("schoolId", schoolId);
        object.put("name", name);
        return object;
    }
}
