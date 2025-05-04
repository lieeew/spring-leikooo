package com.leikooo.bean;

import org.spring.annotation.Scope;
import org.spring.annotation.Service;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/27
 * @description
 */
@Service
@Scope("prototype")
public class TestService {
    private String name = "spring-test";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
