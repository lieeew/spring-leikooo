package org.spring.beanFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spring.beanDefinition.AnnotatedBeanDefinition;
import org.spring.beanDefinition.AnnotatedGenericBeanDefinition;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
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

    private final List<String> beanDefinitionNames = new ArrayList<>(256);

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    private final Map<String, AnnotatedBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    @Override
    public void registerBeanDefinition(String beanName, AnnotatedBeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName);
    }

    private Object doGetBean(String beanName) {
        Object bean = singletonObjects.get(beanName);
        if (Objects.nonNull(bean)) {
            return bean;
        }
        AnnotatedGenericBeanDefinition bd = (AnnotatedGenericBeanDefinition) beanDefinitionMap.get(beanName);
        Object cBean = createBean(beanName, bd);
        if ("singleton".equals(bd.getScope())) {
            singletonObjects.put(beanName, cBean);
        } else {
            return createBean(beanName, bd);
        }
        return null;
    }

    private Object createBean(String beanName, AnnotatedGenericBeanDefinition bd) {
        try {
            return bd.getClazz().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void preInstantiateSingletons() {
        // 为什么不直接进行遍历还要 new 一下多此一举呢?
        // 首先 beanDefinitionNames 处于一个「并发」环境之中
        // 当我们遍历的时候如果其他线程进行了 add 操作,那么就会导致 for 循环失败
        ArrayList<String> beanNames = new ArrayList<>(beanDefinitionNames);
        for (String beanName : beanNames) {
            getBean(beanName);
        }
    }

    public Map<String, AnnotatedBeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }

    public List<String> getBeanDefinitionNames() {
        return beanDefinitionNames;
    }
}
