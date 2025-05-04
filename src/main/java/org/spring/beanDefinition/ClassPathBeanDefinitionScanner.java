package org.spring.beanDefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spring.annotation.ComponentScan;
import org.spring.annotation.Scope;
import org.spring.applicationContext.AnnotationConfigApplicationContext;
import org.spring.beanFactory.DefaultListableBeanFactory;

import java.beans.Introspector;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/5/4
 * @description
 */
public class ClassPathBeanDefinitionScanner {

    private final Logger log = LogManager.getLogger(ClassPathBeanDefinitionScanner.class);

    private final BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    public void scan(String... basePackages){
        doScan(basePackages);
    }

    public void doScan(String... basePackages) {
        DefaultListableBeanFactory defaultListableBeanFactory = ((AnnotationConfigApplicationContext) registry).obtainBeanFactory();
        for (Map.Entry<String, AnnotatedBeanDefinition> entry : defaultListableBeanFactory.getBeanDefinitionMap().entrySet()) {
            Class<?> aClass = ((AnnotatedGenericBeanDefinition) entry.getValue()).getClazz();
            if (aClass.isAnnotationPresent(ComponentScan.class)) {
                String packagePath = aClass.getAnnotation(ComponentScan.class).value();
                URL resource = this.getClass().getClassLoader().getResource(packagePath.replace(".", "/"));
                File file = new File(resource.getPath());
                scanBean(file, packagePath);
            }
        }
    }

    private void scanBean(File file, String packagePath) {
        if (!file.isDirectory()) {
            try {
                Class<?> aClass = Class.forName(packagePath.replace(".class", ""));
                log.error("scanBean: {}", packagePath.replace(".class", ""));
                // 把 appConfig 读取一个 BeanDefinition 注入到 beanFactory
                AnnotatedGenericBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(aClass);
                beanDefinition.setClazz(aClass);
                beanDefinition.setScope("singleton");
                // 读取 scope 配置
                if (beanDefinition.getClazz().isAnnotationPresent(Scope.class)) {
                    String scope = beanDefinition.getClazz().getAnnotation(Scope.class).value();
                    beanDefinition.setScope(scope);
                }
                // 通知进行注册逻辑
                BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinition, this.registry);
                return;
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage());
            }
        }
        for (File f : Objects.requireNonNull(file.listFiles())) {
            scanBean(f, packagePath.concat(".").concat(f.getName()));
        }
    }


}
