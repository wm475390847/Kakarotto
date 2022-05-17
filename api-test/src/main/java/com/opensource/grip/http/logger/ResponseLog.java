package com.opensource.grip.http.logger;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import lombok.Data;
import lombok.experimental.Accessors;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import com.opensource.grip.http.api.Api;

import java.io.IOException;
import java.util.Map;

/**
 * 结果日志
 *
 * @author wangmin
 * @date 2021/9/28 3:41 下午
 */
@Data
@Accessors(chain = true)
public class ResponseLog<T> {
    private static final String SYSTEM_LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String BLANK = "    ";

    public String getStrResult() {
        if (StringUtils.isEmpty(strResult)) {
            try {
                if (response instanceof String) {
                    this.strResult = (String) response;
                } else if (response instanceof Response) {
                    ResponseBody responseBody = ((Response) response).body();
                    Preconditions.checkArgument(responseBody != null, "响应体不能为空");
                    this.strResult = responseBody.string();
                } else {
                    this.strResult = response.toString();
                }
            } catch (IOException e) {
                this.strResult = "日志结果不支持字符串";
            }
        }
        return this.strResult;
    }

    public ResponseInfo getObjResult() {
        String strResult = getStrResult();
        JSONObject object = JSONObject.parseObject(strResult);
        return JSONObject.toJavaObject(object, ResponseInfo.class);
    }

    public long getResponseTime() {
        return endTime - startTime;
    }

    @Override
    public String toString() {
        return buildLog();
    }

    private String buildLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("Api Request Log:").append(SYSTEM_LINE_SEPARATOR)
                .append(BLANK).append("-headers:").append(headers == null ? api.getHeaders() : headers).append(SYSTEM_LINE_SEPARATOR)
                .append(BLANK).append("-method:").append(api.getMethod()).append(SYSTEM_LINE_SEPARATOR)
                .append(BLANK).append("-url:").append(url).append(SYSTEM_LINE_SEPARATOR);

        //param
        if (!api.getPartParams().isEmpty()) {
            sb.append(BLANK).append("-partParam:").append(api.getPartParams()).append(SYSTEM_LINE_SEPARATOR);
        } else if (api.getBodyContent() != null) {
            sb.append(BLANK).append("-body:").append(api.getBodyContent()).append(SYSTEM_LINE_SEPARATOR);
        } else if (!api.getUrlParams().isEmpty()) {
            sb.append(BLANK).append("-param:");
            api.getUrlParams().forEach((key, value) -> sb.append(key).append("=").append(value).append("&"));
            sb.replace(sb.length() - 1, sb.length(), "").append(SYSTEM_LINE_SEPARATOR);
        }

        //response
        sb.append(BLANK).append("-response:").append(getStrResult()).append(SYSTEM_LINE_SEPARATOR)
                .append(BLANK).append("-response time:").append(getResponseTime()).append("ms");
        return sb.toString();
    }

    private Map<String, String> headers;
    private ResponseInfo objResult;
    private String strResult;
    private long startTime;
    private long endTime;
    private T response;
    private String url;
    private Api api;
}
