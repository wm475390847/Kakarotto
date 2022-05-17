package com.opensource.grip.test.auto;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 修改设备信息 陈伟
 *
 * @author wangmin
 * @date Tue May 17 12:54:20 CST 2022
 */
@Builder
public class SchoolDevicesPutApi extends BaseHttpApi {
    /**
     * 描述：关联的边缘端id
     * 是否必填：false
     */
    private final String schIotId;
    /**
     * 描述：详细地址
     * 是否必填：false
     */
    private final String address;
    /**
     * 描述：设备推流地址
     * 是否必填：false
     */
    private final String pushUrl;
    /**
     * 描述：null
     * 是否必填：false
     */
    private final String schoolId;
    /**
     * 描述：设备名称
     * 是否必填：false
     */
    private final String name;
    /**
     * 描述：宽度
     * 是否必填：false
     */
    private final Integer width;
    /**
     * 描述：高度
     * 是否必填：false
     */
    private final Integer hight;
    /**
     * 描述：位置
     * 是否必填：false
     */
    private final String location;
    /**
     * 描述：设备ID
     * 是否必填：false
     */
    private final String id;
    /**
     * 描述：设备类型-自研/其他
     * 是否必填：false
     */
    private final String source;
    /**
     * 描述：设备类型，事件-EVENT
     * 是否必填：false
     */
    private final String type;
    /**
     * 描述：设备拉流地址
     * 是否必填：false
     */
    private final String pullUrl;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/school/devices")
                .method("PUT")
                .contentType("application/json")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject getBody() {
        JSONObject object = new JSONObject();
        object.put("schIotId", schIotId);
        object.put("address", address);
        object.put("pushUrl", pushUrl);
        object.put("schoolId", schoolId);
        object.put("name", name);
        object.put("width", width);
        object.put("hight", hight);
        object.put("location", location);
        object.put("id", id);
        object.put("source", source);
        object.put("type", type);
        object.put("pullUrl", pullUrl);
        return object;
    }
}
