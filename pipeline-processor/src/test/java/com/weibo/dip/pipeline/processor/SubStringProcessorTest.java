package com.weibo.dip.pipeline.processor;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.weibo.dip.pipeline.exception.FieldNotExistException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SubStringProcessorTest {

  private String test_type = "processor_substring";
  private String jsonFile = "src/test/resources/sample_pipeline_substring.json";
  private List<Processor> processorList;

  @Before
  public void before() {
    try {
      processorList = JsonTestUtil.getProcessors(jsonFile);
    } catch (Exception e) {
      Assert.fail("create processorList error!!!");
    }
  }

  @Test
  public void testTrimFieldExist() {
    String fieldName = "substring_trim";

    try {
      Processor<Map<String,Object>> p1 = processorList.get(0);
      Map<String, Object> result = p1.process(Maps.newHashMap(ImmutableMap.of(fieldName, " trimdata ")));
      Assert.assertEquals("trimdata", result.get(fieldName));

    } catch (Exception e) {
      Assert.fail();
    }
  }

//  @Test(expected = FieldNotExistException.class)
  public void testTrimFieldNotExist() throws Exception {
    String fieldName = "trim";
    Map<String, Object> data = Maps.newHashMap(ImmutableMap.of("copy1", "copydata"));

    Map<String, Object> params = ImmutableMap
        .of("fieldNotExistError", true, "subType", "substring_trim", "fieldName", fieldName);
    Processor<Map<String,Object>> p = Processor.createProcessor(test_type, params);

    Map<String, Object> result = p.process(data);

    Assert.fail();
  }


  @Test
  public void testFixedSubStringer() {
    String fieldName = "substring_fixedlr";

    try {
      Processor<Map<String,Object>> p1 = processorList.get(1);
      Map<String,Object> result = p1.process(Maps.newHashMap(ImmutableMap.of(fieldName, "111_fixed_111")));
      System.out.println(result);
      Assert.assertEquals("fixed", result.get(fieldName));


    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Test
  public void testFixedLen() {
    String fieldName = "substring_fixedlen";
    try {

      Processor<Map<String,Object>> p1 = processorList.get(2);
      Map<String, Object> result = p1.process(Maps.newHashMap(ImmutableMap.of(fieldName, "111_match_111")));
      Assert.assertEquals("match", result.get(fieldName));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testDelimindex(){
    String fieldName = "substring_delimindex";
    try {

      Processor<Map<String,Object>> p1 = processorList.get(3);
      Map<String, Object> result = p1.process(Maps.newHashMap(ImmutableMap.of(fieldName, "a,b,c")));
      Assert.assertEquals("a,b", result.get(fieldName));

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
