package com.opensource.grip.conner.generate.parse;

import com.opensource.grip.conner.generate.pojo.Constant;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象解析类
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
@Getter
public abstract class BaseApiParse<T> implements IParse<T> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Object key;
    private final String basePath;

    public BaseApiParse(BaseBuilder<?, ?, ?> baseBuilder) {
        this.key = baseBuilder.key;
        this.basePath = baseBuilder.basePath;
    }

    protected abstract static class BaseBuilder<B extends BaseBuilder<?, ?, ?>, K, T> {
        private String basePath;
        private K key;

        public B key(K key) {
            this.key = key;
            return (B) this;
        }

        public B token(String token) {
            Constant.TOKEN = token;
            return (B) this;
        }

        public B host(String host) {
            Constant.HOST = host;
            return (B) this;
        }

        public B basePath(String basePath) {
            this.basePath = basePath;
            return (B) this;
        }

        public IParse<T> build() {
            return buildParse();
        }

        /**
         * 构建解析
         *
         * @return IParse
         */
        protected abstract IParse<T> buildParse();
    }
}
