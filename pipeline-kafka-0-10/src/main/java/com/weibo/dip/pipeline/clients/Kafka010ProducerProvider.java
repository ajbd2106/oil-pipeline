package com.weibo.dip.pipeline.clients;

import com.weibo.dip.pipeline.provider.KafkaProducerProvider;
import java.util.Map;

/**
 * kafka 0.10版本producer生成器
 * Create by hongxun on 2018/8/12
 */
public class Kafka010ProducerProvider extends KafkaProducerProvider {

  @Override
  public PipelineKafkaProducer createProducer(Map<String, Object> params) {
    return new Kafka010Producer(params);
  }

  @Override
  public double getVersion() {
    return 0.10;
  }
}
