package org.spring.beanFactory;

import org.spring.beanDefinition.AnnotatedBeanDefinition;

import java.util.Objects;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/23
 * @description
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, AnnotatedBeanDefinition beanDefinition);

    Object getBean(String beanName);

    void doScan();

    void doRegister();
}
