/*
yeah comments!
 */

package com.itude.mobile.mobbl.customviewcontrollersample;

import com.itude.mobile.mobbl.core.controller.MBApplicationController;
import com.itude.mobile.mobbl.core.controller.MBApplicationFactory;
import com.itude.mobile.mobbl.core.services.MBMetadataService;

public class ApplicationController extends MBApplicationController {

	@Override
	public void startController() {
		MBMetadataService.setEndpointsName("config/endpoints.xml");
		MBMetadataService.setConfigName("config/config.xml");

		MBApplicationFactory.setInstance(new ApplicationFactory());

		startApplication(ApplicationFactory.getInstance());
	}
}
