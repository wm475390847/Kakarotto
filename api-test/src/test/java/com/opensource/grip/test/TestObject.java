package com.opensource.grip.test;

import com.opensource.grip.conner.http.api.Api;
import com.opensource.grip.conner.http.config.HeadersConfig;
import com.opensource.grip.conner.http.enums.MethodEnum;
import com.opensource.grip.conner.talk.MarkdownTalk;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author wangmin
 * @date 2022/5/17 11:28
 */

public class TestObject {

//    @Test
//    public void testCreateApi() {
//        String token =
//                "dingtalk_sso_jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJpZCI6IjE2MzE1ODMzNDg0ODAzMDE2IiwibmljayI6IuiKveiPnCIsInVuaW9uaWQiOiJYOWNBNVl0WUQ4MGtnaWk1aVM2MW55SkFpRWlFIiwiYXZhdGFyIjoiaHR0cHM6Ly9zdGF0aWMtbGVnYWN5LmRpbmd0YWxrLmNvbS9tZWRpYS9sQUxQRGVDMjVOdXM5cmJOQS1qTkEtZ18xMDAwXzEwMDAucG5nIn0sImlhdCI6MTY1MjM0NTc2NSwiZXhwIjoxNjUyOTUwNTY1fQ.F0_TuFdi44AA--zMZrglTsJLmGA60HwwblBuTmC8vV4; _yapi_uid=1415; _yapi_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjE0MTUsImlhdCI6MTY1Mjc1NzY5NiwiZXhwIjoxNjUzMzYyNDk2fQ.QeflsStat5MHMTbIV6PYXIfUzKSzYHN8daadmmN7luA";
//        IParse<ApiInfo> parse = new YApiParse.Builder()
//                .host("http://yapi.xinhuazhiyun.com")
//                .token(token)
//                .key(1093)
//                .build();
//
//        IGenerator generator = new ApiGenerator.Builder()
//                .parse(parse)
//                .outputPath("src/test/java/com/opensource/grip/test/auto")
//                .templatePath("src/main/resources/template/")
//                .templateName("apiTemplate.ftl")
//                .build().load();
//        generator.execute();
//
//    }

//    @Test
//    public void test() throws IOException {
//        HeadersConfig headersConfig = new HeadersConfig();
//        headersConfig.host("118.31.181.18")
//                .cookie("_sw_token=EC04F49329B3E0CEF72561F89F56DE28");
//        String url = "https://test.school.shuwen.com/ai-school-manager/school/classes/list";
//        Api api = VenuesLitesApi.builder().stadiumId(13L).build().buildApi();
//
//        String string = MethodEnum.POST.getCommand().execute(headersConfig, url, api).body().string();
//        System.err.println(string);
//    }
}
