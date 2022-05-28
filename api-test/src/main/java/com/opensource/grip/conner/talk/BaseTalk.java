package com.opensource.grip.conner.talk;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.conner.http.api.Api;
import com.opensource.grip.conner.http.enums.MethodEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author wangmin
 * @date 2022/3/12 11:31 上午
 */
public abstract class BaseTalk implements ITalk {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String webhook;

    @Getter
    private final String keyword;

    public BaseTalk(BaseBuilder baseBuilder) {
        this.webhook = baseBuilder.webhook;
        this.keyword = baseBuilder.keyword;
    }

    @Override
    public void send() {
        try {
            Api api = new Api.Builder()
                    .contentType("application/json")
                    .bodyContent(message())
                    .build();
            Response response = MethodEnum.POST.getCommand().execute(null, webhook, api);
            logger.info("==> 发送钉钉通知");
            if (response.body() != null) {
                logger.info("==> response: {}", response.body().string());
                logger.info("<== success");
            } else {
                logger.info("<== fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建信息
     *
     * @return 信息
     */
    protected abstract JSONObject message();

    @Setter
    @Accessors(chain = true, fluent = true)
    public abstract static class BaseBuilder {

        private String webhook;
        private String keyword;

        /**
         * 构建
         *
         * @return IDingTalk
         */
        public abstract ITalk build();
    }
}
