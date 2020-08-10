package com.o0u0o.missyou.simple;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName LOLConfigurationSeletor
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/6/30 下午11:58
 * @Descripton: 描述信息
 * @Version: v0.0.1
 **/
public class LOLConfigurationSeletor implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{HeroConfiguration.class.getName(), DatabaseConfiguration.class.getName()};
    }
}
