package com.nitor.iugo.po.tests;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.nitor.dux.common.util.XmlParser;
import com.nitor.iugo.po.serviceobjects.GooglePlacesXmlService;

/**
 * {@link GetPlacesTest} class contains collection of tests that will guide you on how to test an XML service. The examples guide on 
 * how to retrieve xml structures as well as how to inject xml test data in tests. Author here presumes that user has 
 * knowledge on Core Java and DOM parsing functionality.
 * @author anup.manekar 
 *
 */
public class GetPlacesTest extends BaseTest{

	private static final Logger LOGGER = LoggerFactory.getLogger(GetPlacesTest.class);
	
	/**
	 * The following test code shows how to retrieve food places in xml responses using GooglePlacesXmlService and 
	 * verify one of the values in nodes. The code uses DOMParser so that response is completely parsed before any 
	 * assertions taking place
	 * @param inputData - Input data that is injected through data provider in BaseTest class
	 */
	@Test(description="GetPlacesTest-XmlResponses-Verify that 'Australian Cruise Group' is retrieved when google search is done on food places", 
			dataProvider = "provideData")
	public void verifyFoodPlacesAreRetrieved(Object inputData){
		try{
			JSONObject input = (JSONObject) inputData;
			
			GooglePlacesXmlService placesServ = new GooglePlacesXmlService(sRestConfig.getConfig().getString("XMLBASEURI"));
			placesServ.getFoodPlacesNearLocation(input.getString("term"), input.getString("location"));
			Assert.assertTrue(placesServ.getResponse().getStatus() == 200, 
					"Http Status Code returned is " + placesServ.getResponse().getStatus() + " instead of 200");
			Assert.assertTrue(!placesServ.getResponseBody().equals(null), "Response body is NULL instead of non-NULL");
			Document xmlObject = XmlParser.getXmlObjectFromString(placesServ.getResponseBody());
			NodeList nList = xmlObject.getElementsByTagName("result");
			Node firstResult = nList.item(0);
			LOGGER.info("First Result:" + firstResult.getNodeName());
			Element eElement = (Element) firstResult;
			String placeName = eElement.getElementsByTagName("name").item(0).getTextContent();
			LOGGER.info("Place name retrieved:" + placeName);
			Assert.assertTrue(placeName.contains("Australian Cruise Group"), "Australian Cruise Group not returned in food places");
			
			
		}catch(AssertionError e){
			LOGGER.error("Assertion Failed:" + e.getMessage());
			throw new AssertionError(e);			
		}	
	}

	/**
	 * The following code shows how xml body(input data) can be retrieved through JSON object. So even if your requests contains XML 
	 * input data, they have to be stored in JSON object against a key and retrieve them when injected through data provider in 
	 * BaseTest class. The actual addition of places is not done as this is google places api and may result in addition of incorrect 
	 * places during testing. 
	 * @param inputData - Input data that is injected through data provider in BaseTest class
	 */
	@Test(description="GetPlacesTest-XmlResponses-Verify that all food places can be added using Google Places XML service", dataProvider = "provideData")
	public void verifyPlacesCanBeAdded(Object inputData){
		try{
			LOGGER.info("In verify places can be added");
			JSONObject input = (JSONObject) inputData;
			LOGGER.info("Test Data:" + input.get("body").toString());
			GooglePlacesXmlService placesServ = new GooglePlacesXmlService(sRestConfig.getConfig().getString("XMLBASEURI"));			
			
		}catch(AssertionError e){
			LOGGER.error("Assertion Failed:" + e.getMessage());
			throw new AssertionError(e);			
		}	
	}
}
