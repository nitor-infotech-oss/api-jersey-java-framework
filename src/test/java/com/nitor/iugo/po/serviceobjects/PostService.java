package com.nitor.iugo.po.serviceobjects;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import com.nitor.dux.common.configuration.RestConfiguration;
import com.nitor.dux.common.service.RequestType;

/**
 * {@link PostService} shows the page object of <a href='https://jsonplaceholder.typicode.com/'>JSON PlaceHolder API</a>. 
 * This service is used to imitate JSON REST api and returns mock objects. Its not real service. The intention here 
 * is to guide user on creating page objects of services-under-test. Constructor here only consists of setting url and 
 * not accessing {@link RestConfiguration} object as this object doesnt require to access that. But user should feel free on 
 * setting/accessing {@link RestConfiguration} object like done in {@link GooglePlacesXmlService}
 * 
 * @author anup.manekar
 *
 */
public class PostService extends AppRestService {

	public PostService(String baseUrl) {
		super(baseUrl);		
	}
	
	public void getPosts(){
		this.setRequestType(RequestType.GET);
		this.setContentType(MediaType.APPLICATION_JSON_TYPE);
		this.setRelativePath("/posts");
		this.sendRequest();
	}
	
	public void getPostsById(String id){
		this.setRequestType(RequestType.GET);
		this.setContentType(MediaType.APPLICATION_JSON_TYPE);
		this.setRelativePath("/posts/" + id);
		this.sendRequest();
	}
	
	public void getPostsByUserId(String userId){
		this.setRequestType(RequestType.GET);
		this.setContentType(MediaType.APPLICATION_JSON_TYPE);
		this.setRelativePath("/posts/");
		HashMap<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("userId", userId);
		this.setQueryParams(queryParams);
		this.sendRequest();
	}
	
	public void getCommentsForSpecificPost(String id){
		this.setRequestType(RequestType.GET);
		this.setContentType(MediaType.APPLICATION_JSON_TYPE);
		this.setRelativePath("/posts/" + id + "/comments");
		this.sendRequest();
	}
	
	public void addPost(String body){
		this.setRequestType(RequestType.POST);
		this.setContentType(MediaType.APPLICATION_JSON_TYPE);
		this.setRelativePath("/posts");
		this.setBody(body);
		this.sendRequest();
	}
	
	public void updatePost(String body,String id){
		this.setRequestType(RequestType.PUT);
		this.setContentType(MediaType.APPLICATION_JSON_TYPE);
		this.setRelativePath("/posts/" + id);
		this.setBody(body);
		this.sendRequest();
	}
	
	public void deletePost(String id){
		this.setRequestType(RequestType.DELETE);
		this.setContentType(MediaType.APPLICATION_JSON_TYPE);
		this.setRelativePath("/posts/" + id);
		this.sendRequest();
	}
	
	

}
