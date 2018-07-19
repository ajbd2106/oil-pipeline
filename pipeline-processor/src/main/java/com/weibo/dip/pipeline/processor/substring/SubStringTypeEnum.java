package com.weibo.dip.pipeline.processor.substring;

import com.google.common.collect.ImmutableMap;
import com.weibo.dip.pipeline.enums.TypeEnum;
import java.util.Map;

/**
 * 列截取处理生成器.
 * Create by hongxun on 2018/6/27
 */

public enum SubStringTypeEnum implements TypeEnum {

  Trim {
    @Override
    public SubStringer getSubStringer(Map<String, Object> params) {
      return new TrimSubStringer(params);
    }
  },
  FixedLR {
    @Override
    public SubStringer getSubStringer(Map<String, Object> params) {
      return new FixedLRSubStringer(params);
    }
  },
  FixedLen {
    @Override
    public SubStringer getSubStringer(Map<String, Object> params) {
      return new FixedLenSubStringer(params);
    }
  },
  Match {
    @Override
    public SubStringer getSubStringer(Map<String, Object> params) {

      return new MatchSubStringer(params);
    }
  }, RegexExtract {
    @Override
    public SubStringer getSubStringer(Map<String, Object> params) {
      return new RegexExtractSubStringer(params);
    }
  };


  private static final Map<String, SubStringTypeEnum> types =
      new ImmutableMap.Builder<String, SubStringTypeEnum>()
          .put("substring_trim", Trim)
          .put("substring_fixedlr", FixedLR)
          .put("substring_fixedlen", FixedLen)
          .put("substring_match", Match)
          .put("substring_regex", RegexExtract)
          .build();

  public SubStringer getSubStringer(Map<String, Object> params) {
    throw new RuntimeException("Abstract Error!!!");
  }

  public static SubStringTypeEnum getType(String typeName) {
    return types.get(typeName);
  }
}
