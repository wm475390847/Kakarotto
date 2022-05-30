package com.opensource.grip.test;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.conner.http.api.Api;
import com.opensource.grip.conner.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * 场地列表 许祖德
 *
 * @author wangmin
 * @date 2021-12-09 13:04:56
 */
@Builder
public class VenuesLitesApi extends BaseHttpApi {
    /**
     * 描述：null
     * 是否必填：false
     */
    @Builder.Default
    private Integer pageNumber = 0;
    /**
     * 描述：null
     * 是否必填：false
     */
    @Builder.Default
    private final Integer pageSize = 10;

    /**
     * 场馆id
     */
    private final Long stadiumId;

    @Override
    protected Api buildApi() {
        return new Api.Builder()
                .path("/stadium-manager/vlog/venues/lists")
                .method("POST")
                .bodyContent(getCurrentBody())
                .build();
    }

    @Override
    protected JSONObject buildBody() {
        JSONObject object = new JSONObject();
        JSONObject page = new JSONObject();
        page.put("pageNumber", pageNumber);
        page.put("pageSize", pageSize);
        object.put("stadiumId", stadiumId);
        object.put("page", page);
        return object;
    }

    @Override
    public void setPage(Integer pageIndex) {
        this.pageNumber = pageIndex;
    }
}
