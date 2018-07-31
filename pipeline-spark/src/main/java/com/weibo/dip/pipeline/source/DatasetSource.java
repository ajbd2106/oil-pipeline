package com.weibo.dip.pipeline.source;

import java.util.Map;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

/**
 * Create by hongxun on 2018/7/30
 */

/**
 * kafka 不同版本的source代理
 */
class DatasetKafkaDelegate extends DatasetSource {

  /**
   * 实际的对应版本的datasetSource
   */
  private DatasetSource datasetSource;
  /**
   * kafka source生成器，当未加载到Provider时返回空
   */
  private DatasetKafkaSourceProvider kafkaSourceProvider = DatasetKafkaSourceProvider.newInstance();

  public DatasetKafkaDelegate(Map<String, Object> params) {
    super(params);
    try {
      datasetSource = kafkaSourceProvider.createDataSource(params);
    } catch (Exception e) {
      // todo: exception
      throw new RuntimeException(e);
    }
  }

  @Override
  public Dataset createSource(SparkSession sparkSession) {
    return datasetSource.createSource(sparkSession);
  }
}

/**
 * 调用 sparkSession的 read,生成Dataset
 */
class DatasetFileSource extends DatasetSource {

  /**
   * 输入源类型
   */
  protected String sourceFormat;
  /**
   * 源配置
   */
  protected Map<String, String> sourceOptions;

  public DatasetFileSource(Map<String, Object> params) {
    super(params);
  }

  @Override
  public Dataset createSource(SparkSession sparkSession) {
    return sparkSession
        .read()
        .format(sourceFormat)
        .options(sourceOptions).load();

  }
}