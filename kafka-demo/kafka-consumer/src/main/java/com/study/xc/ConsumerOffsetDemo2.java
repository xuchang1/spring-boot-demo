package com.study.xc;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 消费偏移量
 *
 * @author changxu13
 * @date 2021/9/10 15:20
 */
public class ConsumerOffsetDemo2 {
	// broker集合
	private static final String brokerList = "localhost:9092";
	// 主题名称
	private static final String topic = "topic-demo";

	// 消费者组名称
	private static final String groupId = "offsetdemo";

	public static void main(String[] args) throws InterruptedException {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

		// 消费者客户端
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
		// 订阅分区
		TopicPartition topicPartition = new TopicPartition(topic, 0);
		consumer.assign(Collections.singletonList(topicPartition));

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
			if (!records.isEmpty()) {
				List<ConsumerRecord<String, String>> recordList = records.records(topicPartition);
				System.out.println("拉取的消息偏移量 : " + recordList.get(recordList.size() - 1).offset());
				Map<TopicPartition, OffsetAndMetadata> committed = consumer.committed(Collections.singleton(topicPartition));
				System.out.println("提交的位移 : " + committed.get(topicPartition).offset());
				System.out.println("下一次待拉取的位移 : " + consumer.position(topicPartition));
			}
		}
	}
}
