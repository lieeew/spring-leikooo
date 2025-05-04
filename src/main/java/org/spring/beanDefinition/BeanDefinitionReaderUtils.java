package org.spring.beanDefinition;

import org.spring.annotation.Scope;

import java.beans.Introspector;
import java.util.Objects;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/20
 * @description
 */
public class BeanDefinitionReaderUtils {
    public static void registerBeanDefinition(AnnotatedBeanDefinition beanDefinition, BeanDefinitionRegistry registry) {
        String simpleName = ((AnnotatedGenericBeanDefinition) beanDefinition).getClazz().getSimpleName();
        assert simpleName.isEmpty() : "bean name must not be empty";
        String beanName = Introspector.decapitalize(simpleName);
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
