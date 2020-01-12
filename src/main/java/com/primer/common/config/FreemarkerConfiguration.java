package com.primer.common.config;

import freemarker.template.Configuration;

/**
 * freemarker配置类
 * @author HIFeng
 */
public class FreemarkerConfiguration extends Configuration {
    private String basePackagePath;

    public FreemarkerConfiguration() {
        this("");
    }

    public FreemarkerConfiguration(String basePackagePath) {
        super(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        setDefaultEncoding("UTF-8");
        setClassForTemplateLoading(getClass(), basePackagePath);
    }
}
