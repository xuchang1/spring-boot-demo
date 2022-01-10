package com.xc.study;

import com.xc.study.config.RateLimiterConfig;
import com.xc.study.interceptor.RateLimiterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author changxu13
 * @date 2022/1/9 15:54
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Resource
	private RateLimiterConfig rateLimiterConfig;

	@Resource
	private RateLimiterInterceptor rateLimiterInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(rateLimiterInterceptor)
				.addPathPatterns(new ArrayList<>(rateLimiterConfig.getLimitMap().keySet()));
	}
}
