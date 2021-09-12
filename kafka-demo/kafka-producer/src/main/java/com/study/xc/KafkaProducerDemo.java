package com.study.xc;

import com.study.xc.interceptor.ProducerInterceptorPrefix;
import com.study.xc.interceptor.ProducerInterceptorPrefixPlus;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaProducerDemo {

    // broker集合
    private static final String brokerList = "localhost:9092";
    // 主题名称
    private static final String topic = "topic-demo";

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class  .getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorPrefix.class.getName() + "," +
                ProducerInterceptorPrefixPlus.class.getName());

        // 生产者客户端
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 消息（创建消息时，可以指定消息的topic、partition）
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "Hello, kafka!");

        // 同步发送消息
	    for (int i = 0; i < 100; i++) {
		    try {
			    Future<RecordMetadata> future = producer.send(record);
			    RecordMetadata recordMetadata = future.get();
			    System.out.println("同步消息发送成功 : " + recordMetadata.offset() + "," + recordMetadata.partition());
		    } catch (Exception e) {
			    e.printStackTrace();
		    }
	    }

        // 异步发送消息
        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
            } else {
                System.out.println("异步消息发送成功 : " + metadata.offset() + "," + metadata.partition());
            }
        });

        producer.close();
        Thread.sleep(2000);
    }
}
