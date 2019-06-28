package com.nitor.iugo.po.serviceobjects;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nitor.dux.common.configuration.RestConfiguration;
import com.nitor.dux.common.service.RequestType;
import com.nitor.dux.common.util.FileOperations;

/**
 * {@link GooglePlacesXmlService} is page object of Google Places XML WebService API. Constructor shows setting of baseurl a
 * s well as {@link RestConfiguration} object
 * @author anup.manekar
 *
 */
public class GooglePlacesXmlService extends AppRestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GooglePlacesXmlService.class);
	private RestConfiguration restConfig;
	private String restConfigFile;
	
	public GooglePlacesXmlService(String baseUrl) {
		super(baseUrl);
		String env = System.getProperty("environment");
		this.restConfigFile = "config/" + env + "_rest_config.json";
		LOGGER.debug("Set RestConfigfile:" + this.restConfigFile);
		JSONObject restConfig = FileOperations.getFileContentsInJson(this.restConfigFile);
		LOGGER.debug("Get Rest Config:" + restConfig);
		this.restConfig = new RestConfiguration(restConfig);
		LOGGER.debug("Rest Config set:"+this.restConfig.getConfig().toString());
	}
	
	public void getFoodPlacesNearLocation(String name, String location){
		LOGGER.debug("Getting food places near location:" + location + " with name:" + name);
		this.setRequestType(RequestType.GET);
		this.setContentType(MediaType.APPLICATION_XML_TYPE);
		this.setRelativePath("/place/nearbysearch/xml");
		HashMap<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("location", location);
		queryParams.put("radius", "500");
		queryParams.put("name",name);
		queryParams.put("types", "food");
		queryParams.put("key", this.restConfig.getConfig().getString("GoogleAPIKey"));
		this.setQueryParams(queryParams);
		LOGGER.debug("Sending request");
		this.sendRequest();
	}
	
	public void getBanksNearLocation(String name, String location){
		LOGGER.debug("Getting bank places near location:" + location + " with name:" + name);
		this.setRequestType(RequestType.GET);
		this.setContentType(MediaType.APPLICATION_XML_TYPE);
		this.setRelativePath("/place/nearbysearch/xml");
		HashMap<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("location", location);
		queryParams.put("radius", "500");
		queryParams.put("name",name);
		queryParams.put("types", "bank");
		queryParams.put("key", this.restConfig.getConfig().getString("GoogleAPIKey"));
		this.setQueryParams(queryParams);
		LOGGER.debug("Sending request");
		this.sendRequest();
	}
	
		

}
