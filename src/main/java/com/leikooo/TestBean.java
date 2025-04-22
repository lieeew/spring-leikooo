package com.leikooo;

import org.spring.annotation.Component;

/**
 * @author leikooo
 */
@Component
public class TestBean {
	private String name = "spring-test";

	public String getName() {
		return name;
	}
}
