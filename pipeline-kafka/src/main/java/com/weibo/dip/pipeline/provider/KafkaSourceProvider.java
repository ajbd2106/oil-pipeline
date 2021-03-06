package com.weibo.dip.pipeline.provider;

import com.weibo.dip.pipeline.source.KafkaDataSource;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * kafka 不同版本的source生成器，由各版本分别继承生成source
 * Create by hongxun on 2018/8/1
 */
public abstract class KafkaSourceProvider extends KafkaProvider {

  /**
   * 创建source的抽象方法
   *
   * @param params 配置参数
   */
  public abstract KafkaDataSource createDataSource(Map<String, Object> params);

  /**
   * 加载所有service的类
   */
  private static ServiceLoader<KafkaSourceProvider> providerServiceLoader = ServiceLoader
      .load(KafkaSourceProvider.class);

  /**
   * 获取最大版本的provider实例
   */
  public static KafkaSourceProvider newInstance() {
    double maxVersion = 0;
    KafkaSourceProvider maxProvider = null;

    Iterator<KafkaSourceProvider> iterator = providerServiceLoader.iterator();
    while (iterator.hasNext()) {
      KafkaSourceProvider provider = iterator.next();
      if (provider.getVersion() > maxVersion) {
        maxProvider = provider;
        maxVersion = provider.getVersion();
      }
    }
    return maxProvider;
  }

}
