package com.xc.study.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.xc.study.config.RateLimiterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * guava限流实现
 *
 * @author changxu13
 * @date 2022/1/9 15:40
 */
@Component
public class RateLimiterInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private RateLimiterConfig rateLimiterConfig;

	/**
	 * 针对url分别配置不同的限流器
	 */
	private final Map<String, RateLimiter> rateLimiterMap = new HashMap<>();

	@PostConstruct
	public void init() {

		if (!Boolean.TRUE.equals(rateLimiterConfig.getLimitSwitch()) || CollectionUtils.isEmpty(rateLimiterConfig.getLimitMap())) {
			return;
		}

		rateLimiterConfig.getLimitMap().forEach((url, limitCount) ->
				rateLimiterMap.put(url, RateLimiter.create(limitCount)));
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 根据请求的url，获取对应的限流对象
		String requestUrl = request.getServletPath();
		RateLimiter rateLimiter = rateLimiterMap.get(requestUrl);

		if (null != rateLimiter && !rateLimiter.tryAcquire()) {
			System.out.println("触发限流");
			// 触发限流
			response.getOutputStream().print("2");
			response.flushBuffer();
			return false;
		}

		return super.preHandle(request, response, handler);
	}
}
