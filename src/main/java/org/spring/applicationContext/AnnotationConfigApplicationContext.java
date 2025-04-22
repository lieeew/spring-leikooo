package org.spring.applicationContext;

import com.leikooo.config.AppConfig;
import org.spring.beanDefinition.AnnotatedBeanDefinitionReader;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/17
 * @description
 */
public class AnnotationConfigApplicationContext {

    private final AnnotatedBeanDefinitionReader reader;

    public AnnotationConfigApplicationContext() {
        this.reader = new AnnotatedBeanDefinitionReader();
    }

    /**
     * 1.读取扫描路径的类读取 componentClass 扫描路径的类
     * 2.想把 bean 注入到 beanFactory 中（beanDefinition + registerBeanDefinition + FactoryBean）
     * 3.扫描路径，提取路径下面所有的 bean 然后注册到 bean 工程（单例 bean 的初始化）
     */
    public AnnotationConfigApplicationContext(Class<?> componentClass) {
        // 1.读取扫描路径的类读取 componentClass 扫描路径的类
        this();
        // 2.想把 bean 注入到 beanFactory 中（beanDefinition + registerBeanDefinition + FactoryBean）
        register(componentClass);
        // 3.扫描路径，提取路径下面所有的 bean 然后注册到 bean 工程（单例 bean 的初始化）
    }

    private void register(Class<?> componentClass) {
        this.reader.register(componentClass);
    }

    public Object getBean(String testBean) {
        return null;
    }
}
