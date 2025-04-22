package com.leikooo;

import com.leikooo.config.AppConfig;
import org.spring.applicationContext.AnnotationConfigApplicationContext;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/17
 * @description
 */
public class SpringTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);
        TestBean testBean = (TestBean) applicationContext.getBean("testBean");
    }
}
