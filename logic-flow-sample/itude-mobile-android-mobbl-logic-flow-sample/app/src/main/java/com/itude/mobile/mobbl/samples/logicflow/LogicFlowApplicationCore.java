package com.itude.mobile.mobbl.samples.logicflow;

import com.itude.mobile.mobbl.core.MBApplicationCore;
import com.itude.mobile.mobbl.core.model.MBSession;
import com.itude.mobile.mobbl.core.services.MBDataManagerService;
import com.itude.mobile.mobbl.core.services.MBMetadataService;
import com.itude.mobile.mobbl.samples.masterdetail.datahandlers.PlantDataHandler;

public class LogicFlowApplicationCore extends MBApplicationCore
{
  @Override
  public void startController()
  {
    MBMetadataService.setPhoneConfigName("config/config.xml");
    MBMetadataService.setTabletConfigName("config/config.xml");
    MBDataManagerService.getInstance().registerDataHandler(new PlantDataHandler(), "PlantDataHandler");
    MBSession.setInstance(new Session());
    startApplication(new LogicFlowApplicationFactory());
  }
}
