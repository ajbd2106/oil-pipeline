package com.weibo.dip.pipeline.processor;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class FieldRemoveProcessorTest {

  private String test_type = "processor_fieldremove";

  @Test
  public void testRemove() {
    String fields = "remove,other";
    Map<String, Object> data = Maps
        .newHashMap(ImmutableMap.of("remove", "remove", "other", 222, "ddd", "ddd"));

    Map<String, Object> params = ImmutableMap
        .of("subType", "remove_remove", "params", ImmutableMap
            .of("fields", fields));
    Processor p = ProcessorTypeEnum.getType(test_type)
        .getProcessor(params);

    try {
      Map<String, Object> result = p.process(data);
      System.out.println(result);
      Assert.assertFalse(result.containsKey("remove"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Test
  public void testKeep() {
    String fields = "remove,other";
    Map<String, Object> data = Maps
        .newHashMap(ImmutableMap.of("remove", "remove", "other", 222, "ddd", "ddd"));

    Map<String, Object> params = ImmutableMap
        .of("subType", "remove_keep", "params", ImmutableMap
            .of("fields", fields));
    Processor p = ProcessorTypeEnum.getType(test_type)
        .getProcessor(params);

    try {
      Map<String, Object> result = p.process(data);
      System.out.println(result);
      Assert.assertFalse(result.containsKey("ddd"));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testRemoveNull() {
    String fields = "remove,other1";
    Map<String, Object> data = Maps
        .newHashMap(ImmutableMap.of("remove", "remove", "other", 222));

    Map<String, Object> params = ImmutableMap
        .of("subType", "remove_remove_null", "params", ImmutableMap
            .of("fields", fields));
    Processor p = ProcessorTypeEnum.getType(test_type)
        .getProcessor(params);

    try {
      Map<String, Object> result = p.process(data);
      System.out.println(result);
      Assert.assertTrue(result.containsKey("other") && result.containsKey("remove"));

    } catch (Exception e) {
      Assert.fail();
    }
  }

  private String jsonFile = "src/test/resources/sample_pipeline_fieldremove.json";

  @Test
  public void testJsonRemove() {
    Map<String, Object> data = Maps.newHashMap(ImmutableMap.of("a", "aa", "b", "bb", "c", "cc"));
    try {
      List<Processor> processorList = JsonTestUtil.getProcessors(jsonFile);

      Processor p = processorList.get(0);
      data = p.process(data);
      System.out.println(data);
      Assert.assertFalse(data.containsKey("a"));
      Assert.assertEquals(1, data.size());

    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void testJsonKeep() {
    Map<String, Object> data = Maps.newHashMap(ImmutableMap.of("a", "aa", "b", "bb", "c", "cc"));
    try {
      List<Processor> processorList = JsonTestUtil.getProcessors(jsonFile);

      Processor p = processorList.get(1);
      data = p.process(data);
      Assert.assertFalse(data.containsKey("c"));
      Assert.assertEquals(2, data.size());

    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void testJsonRemoveNull() {
    Map<String, Object> data = Maps.newHashMap(ImmutableMap.of("b", "bb", "c", "cc"));
    try {
      List<Processor> processorList = JsonTestUtil.getProcessors(jsonFile);

      Processor p = processorList.get(1);
      data = p.process(data);
      Assert.assertTrue(data.containsKey("b"));
      Assert.assertEquals(2, data.size());

    } catch (Exception e) {
      Assert.fail();
    }
  }
}