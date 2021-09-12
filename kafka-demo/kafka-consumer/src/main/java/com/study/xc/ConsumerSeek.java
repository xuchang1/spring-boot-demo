package com.study.xc;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

public class ConsumerSeek {
    // broker集合
    private static final String brokerList = "localhost:9092";
    // 主题名称
    private static final String topic = "offset-demo";

    // 消费者组名称
    private static final String groupId = "offset6-demo";

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        // 如果没有在内部消费主题中，找到当前消费者的消费偏移量，通过该参数指定消费逻辑，默认为latest，即从最尾部开始消费
        // latest：从尾部开始消费；earliest：从分区日志的头部开始消费，不一定是0，可能发生了日志清理；none：找不到消费位移时抛出异常
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // 消费者客户端
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton(topic));

        // 先拉取一次消息，进行分区的分配，然后再指定具体的偏移量进行消息的消费
        while (consumer.assignment().isEmpty()) {
            consumer.poll(Duration.ofMillis(10));
        }

        // seek()方法从指定的offset处开始消费
        TopicPartition topicPartition = new TopicPartition(topic, 0);
//        consumer.seek(topicPartition, 0);
//        consumer.seekToBeginning(Collections.singleton(topicPartition));

        // 获取大于或等于指定时间戳的第一条消息offset
        Map<TopicPartition, OffsetAndTimestamp> offsetAndTimestampMap =
                consumer.offsetsForTimes(Collections.singletonMap(topicPartition, System.currentTimeMillis() - 1 * 24 * 60 * 60 * 1000));
        OffsetAndTimestamp offsetAndTimestamp = offsetAndTimestampMap.get(topicPartition);
        // 从指定位置处开始消费消息
        consumer.seek(topicPartition, offsetAndTimestamp.offset());
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            Iterator<ConsumerRecord<String, String>> it = records.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
    }
}
