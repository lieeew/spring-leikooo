package org.spring.beanFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spring.beanDefinition.AnnotatedBeanDefinition;
import org.spring.beanDefinition.AnnotatedGenericBeanDefinition;

import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/23
 * @description
 */
public class DefaultListableBeanFactory implements BeanDefinitionRegistry {

    private final Logger log = LogManager.getLogger(DefaultListableBeanFactory.class);


    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    private final Map<String, AnnotatedBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);


    protected void addSingletonObjects(String beanName, Class<?> aClass) {
        if (this.beanDefinitionMap.containsKey(beanName)) {
            try {
                this.singletonObjects.put(beanName, aClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void registerBeanDefinition(String beanName, AnnotatedBeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public Object getBean(String beanName) {
        if (this.singletonObjects.containsKey(beanName)) {
            return singletonObjects.get(beanName);
        }
        if (Objects.isNull(singletonObjects.get(beanName)) && beanDefinitionMap.containsKey(beanName)) {
            AnnotatedGenericBeanDefinition beanDefinition = (AnnotatedGenericBeanDefinition) beanDefinitionMap.get(beanName);
            Class<?> clazz = beanDefinition.getClazz();
            addSingletonObjects(Introspector.decapitalize(clazz.getSimpleName()), clazz);
            return singletonObjects.get(beanName);
        }
        return singletonObjects.get(beanName);
    }

    @Override
    public void doScan() {

    }

    @Override
    public void doRegister() {

    }

    public Map<String, AnnotatedBeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }
}
