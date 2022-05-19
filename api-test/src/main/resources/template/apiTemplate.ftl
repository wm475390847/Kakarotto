package ${packagePath};

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.conner.http.api.Api;
import com.opensource.grip.conner.http.api.BaseHttpApi;
import lombok.Builder;

/**
 * ${apiName} ${apiAuthor}
 *
 * @author wangmin
 * @date ${date}
 */
@Builder
public class ${className} extends BaseHttpApi {
    <#list attrs as attr>
    /**
     * 描述：<#if attr.description??>${attr.description}<#else>null</#if>
     * 是否必填：${attr.required}
     */
    private final ${attr.type} ${attr.value};
    </#list>

    @Override
    protected Api buildApi() {
        <#assign get="GET">
        <#assign multipart="MULTIPART">
        return new Api.Builder()
                .path("${apiPath}")
                .method("${method}")
                .contentType("${contentType}")
        <#if "${method}"?contains(get)>
            <#list attrs as attr>
                .urlParam("${attr.key}", ${attr.value})
            </#list>
        <#elseIf "${method}"?contains(multipart)>
                .partParam("${attr.key}", ${attr.value})
        <#else>
                .bodyContent(getCurrentBody())
        </#if>
                .build();
    }

    @Override
    protected JSONObject getBody() {
    <#if get=="${method}">
        return null;
    <#elseIf multipart=="${method}">
        return null;
    <#elseIf "${apiPath}"?contains("{")>
        return null;
    <#else>
        JSONObject object = new JSONObject();
        <#list attrs as attr>
        object.put("${attr.key}", ${attr.value});
        </#list>
        return object;
    </#if>
    }
}
