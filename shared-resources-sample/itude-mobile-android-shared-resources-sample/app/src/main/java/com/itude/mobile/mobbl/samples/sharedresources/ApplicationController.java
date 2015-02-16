package com.itude.mobile.mobbl.samples.sharedresources;

import com.itude.mobile.mobbl.core.controller.MBApplicationController;
import com.itude.mobile.mobbl.core.services.MBMetadataService;

public class ApplicationController extends MBApplicationController {
	
	@Override
	public void startController() {
		MBMetadataService.setEndpointsName("shared-resources/config/endpoints.xml");
		MBMetadataService.setConfigName("config/master_config.xml");
		
		startApplication(ApplicationFactory.getInstance());
	}
}
