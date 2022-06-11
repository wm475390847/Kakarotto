package com.opensource.grip.conner.http.core;

import com.opensource.grip.conner.http.api.Api;
import com.opensource.grip.conner.http.enums.FileContentTypeEnum;
import com.opensource.grip.conner.util.FileUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author wangmin
 * @date 2022/6/11 09:43
 */
public class MultipartCommand extends AbstractCommand {
    @Override
    protected void buildRequest(Request.Builder builder, Api api) {
        MediaType mediaType = MediaType.parse(api.getContentType());
        if (mediaType == null) {
            throw new RuntimeException("mediaType is null");
        }
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(mediaType);

        api.getPartParams().forEach(multipartBuilder::addFormDataPart);

        Map<String, String> partFiles = api.getPartFiles();
        if (!partFiles.isEmpty()) {
            partFiles.forEach((key, value) -> {
                String fileName = FileUtil.getLastName(value);
                byte[] fileBytes = FileUtil.getFileBytes(value);
                if (StringUtils.isEmpty(fileName)) {
                    throw new RuntimeException("fileName is empty");
                }
                RequestBody requestBody = RequestBody.create(
                        MediaType.parse(
                                FileContentTypeEnum.findByFileName(fileName).getContentType()), fileBytes);
                multipartBuilder.addFormDataPart(key, fileName, requestBody);

            });
        }
        builder.post(multipartBuilder.build());
    }
}
