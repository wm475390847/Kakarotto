package com.opensource.grip.conner.generate.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.opensource.grip.conner.generate.api.InterfaceGetApi;
import com.opensource.grip.conner.generate.api.InterfaceListApi;
import com.opensource.grip.conner.generate.api.ProjectGetApi;
import com.opensource.grip.conner.generate.pojo.ApiInfo;
import com.opensource.grip.conner.generate.pojo.Property;
import com.opensource.grip.conner.generate.enums.ValTypeEnum;
import com.opensource.grip.conner.generate.util.StringUtil;
import com.opensource.grip.conner.http.logger.ResponseInfo;
import com.opensource.grip.conner.http.logger.ResponseLog;
import okhttp3.Response;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * yapi的解析类
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public class YApiParse extends BaseApiParse<ApiInfo> {
    private static final String SIGN = "/";

    public YApiParse(Builder builder) {
        super(builder);
    }

    @Override
    public List<ApiInfo> execute() {
        Integer projectId = (Integer) getKey();

        String basePath = getBasePath();
        basePath = basePath == null ? parseBasePath(projectId) == null ? "" : parseBasePath(projectId) : basePath;

        Preconditions.checkArgument(projectId != null, "projectId 为空");

        JSONArray apiInfo = getApiInfo(projectId);

        String finalBasePath = basePath;
        return apiInfo.stream()
                .map(e -> (JSONObject) e)
                .map(e -> splitData(projectId, e, finalBasePath))
                .collect(Collectors.toList());
    }

    private JSONArray getApiInfo(Integer projectId) {
        JSONArray array = new JSONArray();
        ResponseLog<Response> responseLog = InterfaceListApi
                .builder()
                .projectId(projectId)
                .build()
                .execute();
        logger.info(responseLog.toString());
        JSONObject data = responseLog.getObjResult().getJsonData();
        Integer total = data.getInteger("total");
        for (int i = 1; i <= total; i++) {
            ResponseLog<Response> newLog = InterfaceListApi
                    .builder()
                    .projectId(projectId)
                    .page(i).build()
                    .execute();
            JSONArray list = newLog.getObjResult().getJsonData().getJSONArray("list");
            array.addAll(list);
        }
        return array;
    }

    private ApiInfo splitData(Integer projectId, JSONObject object, String basePath) {
        ApiInfo apiInfo = new ApiInfo();
        try {
            Integer catId = object.getInteger("_id");

            // 接口详情
            JSONObject data = InterfaceGetApi
                    .builder()
                    .id(catId)
                    .build()
                    .execute()
                    .getObjResult()
                    .getJsonData();

            String path = data.getString("path");
            path = basePath == null ? path : basePath + path;
            path = path.startsWith(SIGN) ? path : SIGN + path;
            path = path.endsWith(SIGN) ? path.substring(0, path.length() - 1) : path;

            // 由于开发在yapi写的不规范，所以加一个判断逻辑
            path = path.contains(".com") ? path.split(".com")[1] : path;

            String method = data.getString("method");
            String title = data.getString("title");
            String username = data.getString("username");

            JSONArray reqHeaders = data.getJSONArray("req_headers");

            String contentType;
            if (reqHeaders.size() != 0) {
                // post请求的header是有东西的
                JSONObject header = reqHeaders
                        .stream()
                        .map(req -> (JSONObject) req)
                        .filter(req -> "Content-Type".equals(req.getString("name")))
                        .findFirst()
                        .orElse(null);
                Preconditions.checkArgument(header != null, "header 为空");

                contentType = header.getString("value");
            } else {
                // get请求的header默认为get
                contentType = "application/json";
            }

            List<Property> param;
            JSONObject reqBodyOther = JSONObject.parseObject(data.getString("req_body_other"));
            JSONArray reqQuery = data.getJSONArray("req_query");

            if (reqBodyOther != null) {
                // post请求的参数字段
                param = parsePropertiesAndRequired(reqBodyOther.getJSONObject("properties"),
                        reqBodyOther.getJSONArray("required"));
            } else if (reqQuery.size() != 0) {
                // get请求的参数字段
                param = parseReqQuery(reqQuery);
            } else {
                JSONArray reqParams = data.getJSONArray("req_params");
                param = parseReqParams(reqParams);
            }
            apiInfo.setProjectId(projectId)
                    .setCatId(catId)
                    .setApiPath(path)
                    .setApiName(title)
                    .setMethod(method)
                    .setApiAuthor(username)
                    .setContentType(contentType)
                    .setIsCover(false)
                    .setParam(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiInfo;
    }

    /**
     * 解析基础路径
     *
     * @param projectId 项目id
     * @return 路径
     */
    private String parseBasePath(Integer projectId) {
        ResponseInfo objResult = ProjectGetApi
                .builder()
                .projectId(projectId)
                .build()
                .execute()
                .getObjResult();
        Preconditions.checkArgument(!"请登录...".equals(objResult.getErrMsg()), "token 过期请设置");
        return objResult.getJsonData().getString("basepath");
    }

    /**
     * 解析properties和required
     *
     * @param properties 属性集合
     * @param required   必须标记
     * @return list
     */
    private List<Property> parsePropertiesAndRequired(JSONObject properties, JSONArray required) {
        List<Property> list = new LinkedList<>();
        if (properties == null) {
            return list;
        }
        properties.forEach((key, value) -> {
            Property property = new Property();
            String d = required != null ? required.contains(key) ? "true" : "false" : "false";
            JSONObject propertyObj = (JSONObject) value;
            String description = propertyObj.getString("description");
            String type = ValTypeEnum.findByTypeName(propertyObj.getString("type")).getType().getSimpleName();
            String filedValue = key.contains("-") || key.contains("_")
                    ? StringUtil.lineToHump(key, true) : key;
            property.setDescription(description)
                    .setType(type)
                    .setKey(key)
                    .setValue(filedValue)
                    .setRequired(d);
            list.add(property);
        });
        return list;
    }

    /**
     * 解析reqQuery
     *
     * @param reqQuery 请求体具体为url？后面的参数
     * @return list
     */
    private List<Property> parseReqQuery(JSONArray reqQuery) {
        List<Property> list = new LinkedList<>();
        reqQuery.stream().map(e -> (JSONObject) e).forEach(e -> {
            Property property = new Property();
            String key = e.getString("name");
            String filedValue = key.contains("-") || key.contains("_")
                    ? StringUtil.lineToHump(key, true) : key;
            String required = "1".equals(e.getString("required")) ? "true" : "false";
            String type = ValTypeEnum.findByTypeName("string").getType().getSimpleName();
            property.setKey(key)
                    .setValue(filedValue)
                    .setType(type)
                    .setRequired(required)
                    .setDescription(e.getString("desc"));
            list.add(property);
        });
        return list;
    }

    /**
     * 解析reqParams
     *
     * @param reqParams 请求参数
     * @return dto
     */
    private List<Property> parseReqParams(JSONArray reqParams) {
        List<Property> list = new LinkedList<>();
        reqParams.stream().map(e -> (JSONObject) e).forEach(e -> {
            Property property = new Property();
            String key = e.getString("name");
            String filedValue = key.contains("-") || key.contains("_")
                    ? StringUtil.lineToHump(key, true) : key;
            String required = "true";
            String type = ValTypeEnum.findByTypeName("string").getType().getSimpleName();
            property.setKey(key)
                    .setValue(filedValue)
                    .setType(type)
                    .setRequired(required)
                    .setDescription(e.getString("desc"));
            list.add(property);
        });
        return list;
    }

    public static class Builder extends BaseBuilder<Builder, Object, ApiInfo> {

        @Override
        protected IParse<ApiInfo> buildParse() {
            return new YApiParse(this);
        }
    }
}
