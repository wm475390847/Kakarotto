package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 学生列表 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class StudentsListGetApi extends BaseHttpApi {
    /**
     * 描述：姓名
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：学号
     * 是否必填：false
     */
    private final String studentNumber;
    /**
     * 描述：班级ID集合
     * 是否必填：false
     */
    private final String classIds;
    /**
     * 描述：班级名称
     * 是否必填：false
     */
    private final String className;
    /**
     * 描述：学校ID
     * 是否必填：true
     */
    private final String schoolId;
    /**
     * 描述：性别，ALL("0", "全部"),     MAN("1", "男性"),     WOMAN("2", "女性")	
     * 是否必填：false
     */
    private final String sex;
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
    /**
     * 描述：提供给前端对学生进行姓名或学号的模糊匹配，注意：使用keyword则不能传name和studentNumber
     * 是否必填：false
     */
    private final String keyword;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/students/list")
                .method("GET")
                .contentType("application/json")
                .urlParam("name", name)
                .urlParam("studentNumber", studentNumber)
                .urlParam("classIds", classIds)
                .urlParam("className", className)
                .urlParam("schoolId", schoolId)
                .urlParam("sex", sex)
                .urlParam("pageNo", pageNo)
                .urlParam("pageSize", pageSize)
                .urlParam("keyword", keyword)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
