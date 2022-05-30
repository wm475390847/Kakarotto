package com.opensource.grip.conner.http.core;

import okhttp3.*;
import com.opensource.grip.conner.http.api.Api;
import com.opensource.grip.conner.http.config.HeadersConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * okhttp的请求调用基类
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public abstract class AbstractCommand {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 执行请求
     * <p>传入公共头部配置&完整的url&每个接口的属性来调用一个http/https请求
     *
     * @param config 头部配置类
     * @param url    url
     * @param api    Api类
     * @return 响应体
     */
    public Response execute(HeadersConfig config, String url, Api api) {
        Request.Builder builder = new Request.Builder();

        Proxy proxy = null;
        if (config != null) {
            Map<String, String> requestHeaders = config.getRequestHeaders();
            if (!requestHeaders.isEmpty()) {
                requestHeaders.forEach(builder::addHeader);
            }
            // 从配置文件中获取hostIp
            String hostIp = config.getHost();
            if (StringUtils.isNotBlank(hostIp)) {
                // 得到协议、host、端口
                Request request = new Request.Builder().url(url).build();
                String host = request.url().host();
                String scheme = request.url().scheme();
                int port = config.getPort() == null ? scheme.contains("http") ? 80 : 443 : config.getPort();
                // 绑定url
                InetAddress byAddress = null;
                try {
                    byAddress = InetAddress.getByAddress(host, ipParse(hostIp));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                InetSocketAddress inetSocketAddress = new InetSocketAddress(byAddress, port);
                proxy = new Proxy(Proxy.Type.HTTP, inetSocketAddress);
            }
        }

        buildRequest(builder, api);

        Request request = builder.url(url).build();

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (isHttps(url) && api.isIgnoreSsl()) {
            ignoreSsl(okHttpClientBuilder);
        }

        if (proxy != null) {
            okHttpClientBuilder.proxy(proxy);
        }

        okHttpClientBuilder.retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        Response response = null;
        try {
            response = okHttpClientBuilder.build().newCall(request).execute();
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
        logger.debug("https请求忽略SSL证书");
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

    /**
     * ip解析
     *
     * @param hostIp hostIp
     * @return byte[]
     */
    private byte[] ipParse(String hostIp) {
        String[] ipStr = hostIp.split("\\.");
        byte[] ipBuf = new byte[4];
        for (int i = 0; i < ipBuf.length; i++) {
            ipBuf[i] = (byte) (Integer.parseInt(ipStr[i]) & 0xff);
        }
        return ipBuf;
    }
}
