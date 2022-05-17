package com.opensource.grip.generate.core;

import com.opensource.grip.generate.enums.FileFormatEnum;
import com.opensource.grip.generate.pojo.ApiInfo;
import com.opensource.grip.generate.pojo.FtlParam;
import com.opensource.grip.generate.pojo.Structure;
import com.opensource.grip.generate.util.ParseUtil;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public class ApiGenerator extends BaseGenerator<ApiInfo> {
    private final String suffix;

    public ApiGenerator(Builder builder) {
        super(builder);
        this.suffix = builder.suffix;
    }

    @Override
    public IGenerator load() {
        List<ApiInfo> list = getParse().execute();

        for (int i = 0; i < list.size(); i++) {
            ApiInfo apiInfo = list.get(i);
            String outputPath = ParseUtil.getOutputPath(getOutputPath());
            String packageName = ParseUtil.getPackageName(getOutputPath());
            String className = ParseUtil.getClassName(apiInfo.getMethod(), apiInfo.getApiPath(), suffix);

            //生成模版属性
            FtlParam ftlParam = initFtlParam(apiInfo, className, packageName);

            //生成自动生成属性
            Structure structure = new Structure().setFtlParam(ftlParam).setClassName(className)
                    .setOutputPath(outputPath).setFileSuffix(FileFormatEnum.JAVA.getSuffix());

            if (ftlParam.getApiPath() != null && ftlParam.getAttrs().size() != 0) {
                logger.info("structure: {}", structure);
                structureMap.put(i, structure);
            }
        }
        return this;
    }

    /**
     * 初始化模版属性
     *
     * @param apiInfo 接口信息
     * @return 模版属性
     */
    public FtlParam initFtlParam(ApiInfo apiInfo, String className, String packageName) {
        FtlParam ftlParam = new FtlParam();
        ftlParam.setMethod(apiInfo.getMethod());
        ftlParam.setContentType(apiInfo.getContentType());
        ftlParam.setApiName(apiInfo.getApiName());
        ftlParam.setApiAuthor(apiInfo.getApiAuthor());
        ftlParam.setApiPath(apiInfo.getApiPath());
        ftlParam.setAttrs(apiInfo.getParam());
        ftlParam.setClassName(className);
        ftlParam.setPackagePath(packageName);
        ftlParam.setDate(new Date().toString());
        return ftlParam;
    }

    @Setter
    @Accessors(chain = true, fluent = true)
    public static class Builder extends BaseBuilder<Builder, ApiInfo> {
        private String suffix = "Api";

        @Override
        protected IGenerator buildMarker() {
            return new ApiGenerator(this);
        }
    }
}
