package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * OSS鉴权 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolOssSignatureGetApi extends BaseHttpApi {
    /**
     * 描述：前端上传类型，0-学生人脸，1-学生信息导入表格，2-活动相关的素材
     * 是否必填：true
     */
    private final String type;
    /**
     * 描述：班级ID
     * 是否必填：true
     */
    private final String classId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/ossSignature")
                .method("GET")
                .contentType("application/json")
                .urlParam("type", type)
                .urlParam("classId", classId)
                .build();
    }

    @Override
    protected JSONObject getBody() {
        return null;
    }
}
