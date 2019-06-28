package com.nitor.iugo.po.tests;

import java.lang.reflect.Method;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.nitor.dux.common.configuration.RestConfiguration;
import com.nitor.dux.common.util.FileOperations;

/**
 * {@link BaseTest} is base class and provides common functions of all test classes. At present there are only two functions. 
 * First is to setup context before any tests starts and other is data provider function which injects the test data to 
 * methods annotated with its name in "dataProvider" attribute.
 * 
 * @author anup.manekar
 *
 */
public class BaseTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
	
	protected static String sRestConfigFile;
	protected static RestConfiguration sRestConfig;
	protected static String sDataPath;
	
	@BeforeSuite(alwaysRun = true)
	public void setContext(){
		LOGGER.debug("Setting context");
		String env = System.getProperty("environment");
		BaseTest.sRestConfigFile = "config/" + env + "_rest_config.json";
		LOGGER.debug("Set RestConfigfile:" + BaseTest.sRestConfigFile);
		JSONObject restConfig = FileOperations.getFileContentsInJson(BaseTest.sRestConfigFile);
		LOGGER.debug("Get Rest Config:" + restConfig);
		sRestConfig = new RestConfiguration(restConfig);
		BaseTest.sDataPath = "data/" + env + "/";
		LOGGER.debug("Set Data Path:" + BaseTest.sDataPath);
	}
	
	@DataProvider(name = "provideData")
	public Object[][] provideData(Method method){
		Object[][] result = new Object[1][1];
		try	{
			LOGGER.debug("Set data file for method:" + method.getName());
			String dataFile = method.getDeclaringClass().getSimpleName().concat(".json");
			LOGGER.debug("Test Data File:" + dataFile);
			JSONObject fileObj = FileOperations.getFileContentsInJson(BaseTest.sDataPath.concat(dataFile));
			result[0][0] = fileObj.get(method.getName());
		}
		catch (Exception e)	{
			
		}
		return result;
	}

	
	
}
