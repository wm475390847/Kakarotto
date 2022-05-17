package com.opensource.grip.http.core;

import com.opensource.grip.http.api.Api;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public class DeleteCommand extends AbstractCommand {
    @Override
    protected void buildRequest(Request.Builder builder, Api api) {

        // 解析api类中的contentType
        MediaType mediaType = MediaType.parse(api.getContentType());

        // 发起delete请求
        Object bodyContent = api.getBodyContent();
        if (bodyContent == null) {
            builder.delete();
        } else {
            RequestBody requestBody = RequestBody.create(mediaType, bodyContent.toString());
            builder.delete(requestBody);
        }
    }
}