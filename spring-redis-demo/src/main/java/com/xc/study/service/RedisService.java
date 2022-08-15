package com.xc.study.service;

/**
 * @author changxu13
 * @date 2022/1/27 12:52
 */
public interface RedisService {
	String queryValue();

	void saveValue(String value);
}
