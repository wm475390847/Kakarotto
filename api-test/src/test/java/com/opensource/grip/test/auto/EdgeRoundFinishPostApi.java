package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 回合结束,算法数据上传云端 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class EdgeRoundFinishPostApi extends BaseHttpApi {
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
     * 是否必填：false
     */
    private final JSONObject metaData;
    /**
     * 描述：坑位id
     * 是否必填：true
     */
    private final String positionId;
    /**
     * 描述：是否完全结束。（第一次只传成绩时为false,第二次带视频时为true），默认为true
     * 是否必填：false
     */
    private final Boolean isFinal;
    /**
     * 描述：回合id
     * 是否必填：true
     */
    private final String roundId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/edge/roundFinish")
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
        object.put("isFinal", isFinal);
        object.put("roundId", roundId);
        return object;
    }
}
