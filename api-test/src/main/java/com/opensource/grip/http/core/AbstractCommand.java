package com.opensource.grip.http.core;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.opensource.grip.http.api.Api;
import com.opensource.grip.http.config.HeadersConfig;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * @author wangmin
 * @date 2022/5/17 13:05
 */
@Slf4j
public abstract class AbstractCommand {

    public Response execute(HeadersConfig config, String url, Api api) {
        Request.Builder builder = new Request.Builder();
        config.getRequestHeaders().forEach(builder::addHeader);

        buildRequest(builder, api);

        Request request = builder.url(url).build();
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (isHttps(url) && api.isIgnoreSsl()) {
            ignoreSsl(okHttpClientBuilder);
        }

        OkHttpClient build = okHttpClientBuilder.retryOnConnectionFailure(true)
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .build();

        Response response = null;
        try {
            response = build.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void ignoreSsl(final OkHttpClient.Builder okHttpClientBuilder) {
        log.info("https请求忽略SSL证书");
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{TRUST_MANAGER}, new SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error("SSLContext初始化异常:{}", e.getMessage());
        }
        SSLSocketFactory sslSocketFactory = sslContext != null ? sslContext.getSocketFactory() : null;
        if (sslSocketFactory != null) {
            okHttpClientBuilder.sslSocketFactory(sslSocketFactory, TRUST_MANAGER).hostnameVerifier(
                    (hostname, session) -> true);
        }
    }

    private static final X509TrustManager TRUST_MANAGER = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

    /**
     * 构建请求
     *
     * @param builder 请求构建者
     * @param api     接口参数类
     */
    protected abstract void buildRequest(Request.Builder builder, Api api);

    private boolean isHttps(String url) {
        return url.contains("https://");
    }
}
