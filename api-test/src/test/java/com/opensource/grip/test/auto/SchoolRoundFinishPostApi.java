package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 回合结束 梦真
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolRoundFinishPostApi extends BaseHttpApi {
    /**
     * 描述：视频地址(url)
     * 是否必填：true
     */
    private final String videoPath;
    /**
     * 描述：分数
     * 是否必填：true
     */
    private final Integer score;
    /**
     * 描述：算法元数据
     * 是否必填：true
     */
    private final JSONObject metaData;
    /**
     * 描述：坑位id
     * 是否必填：true
     */
    private final String positionId;
    /**
     * 描述：回合id
     * 是否必填：true
     */
    private final String roundId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/roundFinish")
                .method("POST")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("videoPath", videoPath);
        object.put("score", score);
        object.put("metaData", metaData);
        object.put("positionId", positionId);
        object.put("roundId", roundId);
        return object;
    }
}
