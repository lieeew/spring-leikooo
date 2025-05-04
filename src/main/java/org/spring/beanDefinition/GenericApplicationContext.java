package org.spring.beanDefinition;

import org.spring.beanFactory.BeanDefinitionRegistry;
import org.spring.beanFactory.DefaultListableBeanFactory;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/23
 * @description
 */
public abstract class GenericApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    public GenericApplicationContext() {
        this.beanFactory = new DefaultListableBeanFactory();
    }

    public void registerBeanDefinition(String beanName, AnnotatedBeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }

    public BeanDefinitionRegistry getBeanFactory() {
        return this.beanFactory;
    }

    protected void refresh() {
        // 获取 beanFactory
        DefaultListableBeanFactory beanFactory = obtainBeanFactory();
        // 扫描 appConfig 指定的 bean 目录下进行扫描
        invokeBeanFactoryPostProcessors(beanFactory);
        // Instantiate all remaining (non-lazy-init) singletons.
        finishBeanFactoryInitialization(beanFactory);
    }

    private void finishBeanFactoryInitialization(DefaultListableBeanFactory beanFactory) {
        this.getBeanFactory().preInstantiateSingletons();
    }

    protected void invokeBeanFactoryPostProcessors(BeanDefinitionRegistry beanFactory) {
        throw new UnsupportedOperationException();
    }

    protected DefaultListableBeanFactory obtainBeanFactory() {
        return this.beanFactory;
    }

}

