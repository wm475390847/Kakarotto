package com.opensource.grip.test;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.conner.http.api.Api;
import com.opensource.grip.conner.http.config.HeadersConfig;
import com.opensource.grip.conner.http.enums.MethodEnum;
import com.opensource.grip.conner.http.logger.ResponseLog;
import com.opensource.grip.conner.talk.MarkdownTalk;
import okhttp3.Response;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author wangmin
 * @date 2022/5/17 11:28
 */

public class TestObject {

    @Test
    public void test() throws IOException {
//        ResponseLog<Response> responseLog = UicLoginApi.builder().account("18368126197@xhzy-ty").password("Aa563603").build().execute();
//        System.err.println(responseLog.toString());
//
//        HeadersConfig headersConfig = new HeadersConfig();
//        headersConfig.cookie("_sw_token=EC04F49329B3E0CEF72561F89F56DE28");
//        headersConfig.host("118.31.181.18");
//        Api api = VenuesLitesApi.builder().stadiumId(13L).build().buildApi();
//        String url = "https://test.stadium.shuwen.com/stadium-manager/vlog/venues/lists";
//        Response response = MethodEnum.POST.getCommand().execute(headersConfig, url, api);
//        String string = response.body().string();
//        System.err.println(string);
    }
}
