package org.spring.applicationContext;

import org.spring.beanDefinition.AnnotatedBeanDefinitionReader;
import org.spring.beanDefinition.BeanDefinitionRegistry;
import org.spring.beanDefinition.ClassPathBeanDefinitionScanner;
import org.spring.beanDefinition.GenericApplicationContext;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/17
 * @description
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements BeanDefinitionRegistry {

    private final AnnotatedBeanDefinitionReader reader;

    private final ClassPathBeanDefinitionScanner scanner;


    public AnnotationConfigApplicationContext() {
        super();
        this.reader = new AnnotatedBeanDefinitionReader(this);
        this.scanner = new ClassPathBeanDefinitionScanner(this);
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
        //  refresh 方法作为核心方法，需要放到父类之中，让所有的子类都能使用 refresh 方法是核心方法
        refresh();
    }

    private void register(Class<?> componentClass) {
        this.reader.register(componentClass);
    }

    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    protected void invokeBeanFactoryPostProcessors(org.spring.beanFactory.BeanDefinitionRegistry beanFactory) {
        this.scanner.scan();
    }
}
