package com.xc.study.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 限流配置类
 *
 * @author changxu13
 * @date 2022/1/9 16:25
 */
@Component
@ConfigurationProperties(prefix = "ratelimiter")
public class RateLimiterConfig {

	/**
	 * url -> 限流数量的映射(注意map注入是，url的前缀 / 会被去掉)
	 */
	private Map<String, Integer> limitMap;

	/**
	 * 限流开关
	 */
	private Boolean limitSwitch;

	public Map<String, Integer> getLimitMap() {
		return limitMap;
	}

	public void setLimitMap(Map<String, Integer> limitMap) {
		this.limitMap = limitMap;
	}

	public Boolean getLimitSwitch() {
		return limitSwitch;
	}

	public void setLimitSwitch(Boolean limitSwitch) {
		this.limitSwitch = limitSwitch;
	}
}
