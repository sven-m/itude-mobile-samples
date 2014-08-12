/*
yeah comments!
 */
package com.itude.mobile.mobbl.customviewcontrollersample;

import com.itude.mobile.mobbl.core.controller.MBApplicationFactory;
import com.itude.mobile.mobbl.core.controller.MBApplicationFactory.ControllerMappings.Registry;

public class ApplicationFactory extends MBApplicationFactory {

	protected static class ControllerRegistry extends
			ControllerMappings.Registry {

		@Override
		protected void registerMappings() {
			registerController(Constants.Page.Name.HOME, HomeController.class);

			setDefaultController(DefaultViewController.class);
		}

	};

	@Override
	protected Registry getControllerRegistry() {
		return new ControllerRegistry();
	}

}
