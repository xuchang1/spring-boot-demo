package com.xc.study.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author changxu13
 * @date 2022/1/14 15:41
 */
// @Value 不能直接注入map、list
@Component
@Data
public class ConfigTest2 {

//	@Value("${config2.test.map-test}")
	private Map<String, String> mapTest;

//	@Value("${config2.test.map-test2}")
	private Map<String, String> mapTest2;

//	@Value("${config2.test.list-test}")
	private List<String> listTest;

	@Value("${config2.test.list-test2}")
	private String[] listTest2;

	@Value("${config2.test.string-test}")
	private String stringTest;

	@Override
	public String toString() {
		return "ConfigTest2{" +
				"mapTest=" + mapTest +
				", mapTest2=" + mapTest2 +
				", listTest=" + listTest +
				", listTest2=" + Arrays.toString(listTest2) +
				", stringTest='" + stringTest + '\'' +
				'}';
	}
}
