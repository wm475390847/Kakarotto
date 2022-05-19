package com.opensource.grip.conner.http.core;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.opensource.grip.conner.http.api.Api;
import com.opensource.grip.conner.http.config.HeadersConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * okhttp的请求调用基类
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public abstract class AbstractCommand {
    private static final Logger logger = LoggerFactory.getLogger(AbstractCommand.class);

    /**
     * 执行请求
     * <p>传入公共头部配置&完整的url&每个接口的属性来调用一个http/https请求
     *
     * @param headersConfig 头部配置类
     * @param url           url
     * @param api           Api类
     * @return 响应体
     */
    public Response execute(HeadersConfig headersConfig, String url, Api api) {
        Request.Builder builder = new Request.Builder();
        if (headersConfig != null) {
            headersConfig.getRequestHeaders().forEach(builder::addHeader);
        }
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

    /**
     * 构建请求
     * <P>需要子类实现
     *
     * @param builder 请求构建者
     * @param api     Api类
     */
    protected abstract void buildRequest(Request.Builder builder, Api api);

    /**
     * 是否忽略ssl证书
     *
     * @param okHttpClientBuilder okHttpClientBuilder
     */
    private void ignoreSsl(final OkHttpClient.Builder okHttpClientBuilder) {
        logger.info("https请求忽略SSL证书");
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{TRUST_MANAGER}, new SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            logger.error("SSLContext初始化异常:{}", e.getMessage());
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
     * 判断请求utl是否为https的请求
     *
     * @param url url
     * @return 是为true，不是为false
     */
    private boolean isHttps(String url) {
        return url.contains("https://");
    }
}
