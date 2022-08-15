package com.xc.study.service;

/**
 * redis实现限流
 *      参考：https://mp.weixin.qq.com/s/Gaf4Vrqq0fweRcsEVW0dUQ
 *
 * @author changxu13
 * @date 2021/11/17 14:19
 */
public interface LimitService {

	/**
	 * 1、通过zset的数据结构，存储请求数据。value为随机生成的uuid或请求标识，score为当前时间戳。
	 * 2、通过range相关方法，可以计算出一定时间间隔类的数据量，即一定时间类的请求数量，判断是否达到阈值，来进行限流
	 * 3、缺点：
	 *      随着时间地址，zset的数据结构会越来越大，暂用内存。可以定期清除超出一定时间zset中的数据量。
	 *      查询然后判断是否超过阈值的操作不是原子性的，可通过lua脚本实现原子性操作。
	 *      直接返回客户端范围类数据然后取count不合适，可直接通过redis命令返回count数量。
	 */
	boolean accessByZSort();

	/**
	 * 通过setnx命令实现
	 *      没找到实现方式。
	 *      一个扯蛋的实现如下：
	 *          如果n秒内限流m，事先准备好m个固定key，请求到达时，遍历m个key分别setnx并设置过期时间为n，如果有一个成功则通过，全部失败则限流。
	 */
	boolean accessBySetNx();

	/**
	 * 令牌桶的实现方式
	 *      可以通过一个list数据结构作为令牌桶，有上线并定时往其中加入令牌。请求到来时pop出数据，取到数据则请求通过，未取到则限流。
	 */
	boolean accessByTokenBucket();
}
