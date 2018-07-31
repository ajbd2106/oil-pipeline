package com.weibo.dip.pipeline.source;

import java.util.Map;

/**
 * 0.10版本kafka的Streaming source生成器
 * Create by hongxun on 2018/7/27
 */
public class StreamingKafka010SourceProvider extends StreamingKafkaSourceProvider {

  protected String version = "0.10.0";

  @Override
  public StreamingDataSource createDataSource(Map<String, Object> params) {
    return new StreamingKafka010DataSource(params);
  }
}
