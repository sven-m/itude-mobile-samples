package com.itude.mobile.mobbl.samples.masterdetail;

import com.itude.mobile.mobbl.core.MBApplicationCore;
import com.itude.mobile.mobbl.core.controller.MBApplicationFactory;
import com.itude.mobile.mobbl.core.services.MBDataManagerService;
import com.itude.mobile.mobbl.core.services.MBMetadataService;
import com.itude.mobile.mobbl.samples.masterdetail.datahandlers.PlantDataHandler;

public class WebserviceApplicationCore extends MBApplicationCore
{
  @Override
  public void startController()
  {
    MBMetadataService.setPhoneConfigName("config/config.xml");
    MBMetadataService.setTabletConfigName("config/config_tablet.xml");
    MBDataManagerService.getInstance().registerDataHandler(new PlantDataHandler(), "PlantDataHandler");
    startApplication(MBApplicationFactory.getInstance());
  }
}
