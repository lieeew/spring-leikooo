package org.spring.beanDefinition;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/18
 * @description
 */
public class AnnotatedGenericBeanDefinition implements AnnotatedBeanDefinition {
    private String scope;

    private Class<?> clazz;

    public AnnotatedGenericBeanDefinition(Class<?> clazz) {

    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
