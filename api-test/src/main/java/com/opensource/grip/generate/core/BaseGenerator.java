package com.opensource.grip.generate.core;

import com.google.common.base.Preconditions;
import com.opensource.grip.generate.enums.FileFormatEnum;
import com.opensource.grip.generate.pojo.Structure;
import com.opensource.grip.generate.parse.IParse;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成器的基类
 *
 * @param <P> 对P类型解析器进行生成
 * @author wangmin
 * @date 2022/5/17 13:05
 */
@Getter
public abstract class BaseGenerator<P> implements IGenerator {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String templatePath;
    private final String templateName;
    private final String outputPath;
    private final IParse<P> parse;

    protected Map<Integer, Structure> structureMap = new HashMap<>(64);

    public BaseGenerator(BaseBuilder<?, ?> baseBuilder) {
        this.templateName = baseBuilder.templateName;
        this.templatePath = baseBuilder.templatePath;
        this.outputPath = baseBuilder.outputPath;
        this.parse = (IParse<P>) baseBuilder.parse;
    }

    @Override
    public void execute() {
        structureMap.forEach((key, value) -> {
            if (value != null) {
                if (StringUtils.isEmpty(value.getOutputPath()) && StringUtils.isEmpty(value.getClassName())) {
                    return;
                }
                String outputPath = value.getOutputPath();
                File file = new File(outputPath);
                if (!file.exists()) {
                    logger.info("开始创建文件所在文件夹 :{}", outputPath);
                    Preconditions.checkArgument(file.mkdirs(), "文件夹创建失败 !");
                    logger.info("文件所在文件夹创建成功 !");
                }
                try {
                    // step1 创建freeMarker配置实例
                    Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
                    //step2 获取模版路径
                    configuration.setDirectoryForTemplateLoading(new File(templatePath));
                    //step3 创建数据模型
                    Template template = configuration.getTemplate(templateName);
                    //step4 加载模版文件
                    Map<String, Object> dataMap = value.initDataMap();
                    //step5 生成数据
                    File docFile = new File(outputPath + value.getClassName() + FileFormatEnum.findBySuffix(value.getFileSuffix()).getSuffix());
                    String info = !docFile.exists() ? writerFile(template, dataMap, docFile) : "文件已存在 !";
                    logger.info("----------{}----------", info);
                } catch (IOException | TemplateException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 写文件
     *
     * @param template 模版
     * @param dataMap  数据结构
     * @param docFile  输出文件
     * @return 输出结果
     */
    private String writerFile(Template template, Map<String, Object> dataMap, File docFile) throws TemplateException, IOException {
        //增强try，应用与自动关闭资源，被自动关闭的资源必须实现Closeable或AutoCloseable接口
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)))) {
            template.process(dataMap, writer);
        }
        return "恭喜~ 文件创建成功 !";
    }

    /**
     * 加载
     *
     * @return IGenerator
     */
    @Override
    public abstract IGenerator load();

    protected abstract static class BaseBuilder<T extends BaseBuilder<?, ?>, P> {
        private String templatePath;
        private String templateName;
        private String outputPath;
        private IParse<P> parse;

        public T parse(IParse<P> parse) {
            this.parse = parse;
            return (T) this;
        }

        public T templatePath(String templatePath) {
            this.templatePath = templatePath;
            return (T) this;
        }

        public T templateName(String templateName) {
            this.templateName = templateName;
            return (T) this;
        }

        public T outputPath(String outputPath) {
            this.outputPath = outputPath;
            return (T) this;
        }

        public IGenerator build() {
            Preconditions.checkNotNull(templateName, "模版名称不能为空");
            Preconditions.checkNotNull(templatePath, "模版路径不能为空");
            return buildMarker();
        }

        /**
         * 构建
         *
         * @return IMarker
         */
        protected abstract IGenerator buildMarker();
    }
}
