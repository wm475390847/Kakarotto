package com.opensource.grip.http.core;

import com.opensource.grip.http.api.Api;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public class PutCommand extends AbstractCommand {
    @Override
    protected void buildRequest(Request.Builder builder, Api api) {

        // 解析api类中的contentType
        MediaType mediaType = MediaType.parse(api.getContentType());

        // 发起put请求
        Object bodyContent = api.getBodyContent();
        RequestBody requestBody = RequestBody.create(mediaType, bodyContent.toString());
        builder.put(requestBody);
    }
}