/**
 * Copyright � 1992-2016 Cisco, Inc.
 */
package org.mule.modules.spark.automation.functional;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.spark.SparkConnector;
import org.mule.modules.spark.automation.runner.FunctionalTestSuite;
import org.mule.modules.spark.bean.MessagesPostRequest;
import org.mule.modules.spark.bean.MessagesPostResponse;

public class PostMessageTestCases extends CiscoSparkAbstractTestCases {

	public PostMessageTestCases() {
		super(SparkConnector.class);
		
	}

	@Test
	@Category({FunctionalTestSuite.class})
	public void testPostMessage()
	{
		
		  MessagesPostRequest postReq = TestDataBuilder.getMessagesPostRequest();   
		  MessagesPostResponse  msg = getConnector().postMessages(postReq);
		  assertNotNull(msg);
		
	}
}
