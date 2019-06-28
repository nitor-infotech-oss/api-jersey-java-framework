package com.nitor.iugo.po.serviceobjects;

import com.nitor.dux.common.service.RestService;

/**
 * {@link AppRestService} base page object within the application. User may want use this as wrapper of certain common actions for 
 * services under test. For eg: for all services of this application, user may want to authenticate before giving any calls. 
 * The authentication function can be written here and used in all derived service objects. 
 * 
 * @author anup.manekar
 *
 */
public class AppRestService extends RestService{

	public AppRestService(String baseUrl){
		this.setBaseUri(baseUrl);
	}
	
	
}
