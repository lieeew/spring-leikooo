package org.spring.beanDefinition;

import org.spring.annotation.Scope;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/18
 * @description
 */
public class AnnotatedBeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 注册我们 扫描路径 到 beanFactory 里面
     * @param componentClass 扫描路径的类
     */
    public void register(Class<?> componentClass) {
        registerBean(componentClass);
    }

    private void registerBean(Class<?> componentClass) {
        doRegisterBean(componentClass);
    }

    private void doRegisterBean(Class<?> componentClass) {
        // 把 appConfig 读取一个 BeanDefinition 注入到 beanFactory
        AnnotatedGenericBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(componentClass);
        beanDefinition.setClazz(componentClass);
        beanDefinition.setScope("singleton");
        // 读取 scope 配置
        if (beanDefinition.getClazz().isAnnotationPresent(Scope.class)) {
            String scope = beanDefinition.getClazz().getAnnotation(Scope.class).value();
            beanDefinition.setScope(scope);
        }
        // 通知进行注册逻辑
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinition, this.registry);
    }
}
