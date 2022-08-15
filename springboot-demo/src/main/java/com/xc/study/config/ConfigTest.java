package com.xc.study.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author changxu13
 * @date 2022/1/14 15:41
 */
@Component
@ConfigurationProperties(prefix = "config.test")
@Data
public class ConfigTest {

	private Map<String, String> mapTest;

	private Map<String, String> mapTest2;

	private List<String> listTest;

	private String stringTest;

	@Override
	public String toString() {
		return "ConfigTest{" +
				"mapTest=" + mapTest +
				", mapTest2=" + mapTest2 +
				", listTest=" + listTest +
				", stringTest='" + stringTest + '\'' +
				'}';
	}
}
