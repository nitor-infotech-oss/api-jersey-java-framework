package com.nitor.iugo.po.tests;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.nitor.iugo.po.serviceobjects.PostService;

/**
 * {@link RetrievePostsTests} shows collection of tests to do CRUD operations through HTTP methods (GET,PUT, POST, DELETE) on PostService. 
 * User should use this as guide to inject various data parameters, use them in request object and do assertions. Also note try catch 
 * block to first log it as error and then throw it as {@link AssertionError}. Most of the other exceptions are caught in common library and
 * thrown as {@link Error} and not {@link AssertionError}. This is to distinguish any errors thrown by library due to framework issue/test data
 * issue from actual assertion failures.
 * 
 * @author anup.manekar
 *
 */
public class RetrievePostsTests extends BaseTest{

	private static final Logger LOGGER = LoggerFactory.getLogger(RetrievePostsTests.class);
	
	/**
	 * Test that retrieves json response and does assertion on response body and status. No test data provided. 
	 * Please note additional assertions can also be done using postServ.getResponseBody()
	 */
	@Test(description="RetrievePostsTest-JSONResponses-Verify that all posts are retrieved when GET posts done without id")
	public void verifyPostsRetrievalWithoutId(){
		try{
			PostService postServ = new PostService(sRestConfig.getBaseUri());
			postServ.getPosts();
			Assert.assertTrue(postServ.getResponse().getStatus() == 200, 
					"Http Status Code returned is " + postServ.getResponse().getStatus() + " instead of 200");
			Assert.assertTrue(!postServ.getResponseBody().equals(null), "Response body is NULL instead of non-NULL");
		}catch(AssertionError e){
			LOGGER.error("Assertion Failed:" + e.getMessage());
			throw new AssertionError(e);			
		}	
	}
	
	/**
	 * Test that retrieves json response based on input provided. Underlying HTTP method is GET.
	 * @param inputData
	 */
	@Test(description="RetrieveTests-JSONResponses-Verify that correct post is retrieved with id",dataProvider = "provideData")
	public void verifyPostsWithId(Object inputData){
		try{
			JSONObject input = (JSONObject) inputData;
			PostService postServ = new PostService(sRestConfig.getBaseUri());
			postServ.getPostsById(input.getString("id"));
			Assert.assertTrue(postServ.getResponse().getStatus() == 200, 
					"Http Status Code returned is " + postServ.getResponse().getStatus() + " instead of 200");
			Assert.assertTrue(!postServ.getResponseBody().equals(null), "Response body is NULL instead of non-NULL");
		}catch(AssertionError e){
			LOGGER.error("Assertion Failed:" + e.getMessage());
			throw new AssertionError(e);			
		}	
	}
	
	/**
	 * Test that does POST operation using body from test data.
	 * @param inputData
	 */
	@Test(description="RetrievePostsTests-JSONResponses-Verify that post can be added",dataProvider = "provideData")
	public void verifyPostsCanBeAdded(Object inputData){
		try{
			JSONObject input = (JSONObject) inputData;
			PostService postServ = new PostService(sRestConfig.getBaseUri());
			postServ.addPost(input.get("body").toString());
			Assert.assertTrue(postServ.getResponse().getStatus() == 200, 
					"Http Status Code returned is " + postServ.getResponse().getStatus() + " instead of 200");
			Assert.assertTrue(!postServ.getResponseBody().equals(null), "Response body is NULL instead of non-NULL");
		}catch(AssertionError e){
			LOGGER.error("Assertion Failed:" + e.getMessage());
			throw new AssertionError(e);			
		}	
	}
	
	/**
	 * Test that does PUT operation using body from test data as well as passing other parameters to rest client.
	 * @param inputData
	 */
	@Test(description="RetrieveTests-JSONResponses-Verify that post can be updated",dataProvider = "provideData")
	public void verifyPostsCanBeUpdated(Object inputData){
		try{
			JSONObject input = (JSONObject) inputData;
			PostService postServ = new PostService(sRestConfig.getBaseUri());
			postServ.updatePost(input.get("body").toString(), input.getString("id"));
			Assert.assertTrue(postServ.getResponse().getStatus() == 200, 
					"Http Status Code returned is " + postServ.getResponse().getStatus() + " instead of 200");
			Assert.assertTrue(!postServ.getResponseBody().equals(null), "Response body is NULL instead of non-NULL");
		}catch(AssertionError e){
			LOGGER.error("Assertion Failed:" + e.getMessage());
			throw new AssertionError(e);			
		}	
	}
	
	/**
	 * Test that does DELETE operation
	 * @param inputData
	 */
	@Test(description="RetrieveTests-JSONResponses-Verify that post can be deleted",dataProvider = "provideData")
	public void verifyPostsCanBeDeleted(Object inputData){
		try{
			JSONObject input = (JSONObject) inputData;
			PostService postServ = new PostService(sRestConfig.getBaseUri());
			postServ.deletePost(input.getString("id"));
			Assert.assertTrue(postServ.getResponse().getStatus() == 200, 
					"Http Status Code returned is " + postServ.getResponse().getStatus() + " instead of 200");
			Assert.assertTrue(!postServ.getResponseBody().equals(null), "Response body is NULL instead of non-NULL");
		}catch(AssertionError e){
			LOGGER.error("Assertion Failed:" + e.getMessage());
			throw new AssertionError(e);			
		}	
	}

}
