package com.leikooo;

import com.leikooo.bean.TestService;
import com.leikooo.bean.UserService;
import com.leikooo.config.AppConfig;
import org.spring.applicationContext.AnnotationConfigApplicationContext;

import java.util.Objects;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/17
 * @description
 */
public class SpringTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);
        TestService testBean1 = (TestService) applicationContext.getBean("testService");
        TestService testBean2 = (TestService) applicationContext.getBean("testService");
        boolean equals = Objects.equals(testBean1, testBean2);
        System.out.println("equals = " + equals);
        UserService userService1 = (UserService) applicationContext.getBean("userService");
        UserService userService2 = (UserService) applicationContext.getBean("userService");
        boolean equals2 = Objects.equals(userService1, userService2);
        System.out.println("equals = " + equals2);
    }
}
