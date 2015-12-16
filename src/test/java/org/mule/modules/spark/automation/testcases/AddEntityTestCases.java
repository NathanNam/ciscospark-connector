package org.mule.modules.spark.automation.testcases;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.mule.modules.spark.automation.AbstractTestCase;
import org.mule.tools.devkit.ctf.junit.RegressionTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AddEntityTestCases extends AbstractTestCase {

  @Test
  @Category({RegressionTests.class})
  public void testFlow() throws Exception {
    assertNotNull(getConnector().addEntity("ENTITY_TYPE_1",
      new HashMap<String, Object>()));
  }
}
