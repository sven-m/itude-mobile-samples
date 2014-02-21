package com.itude.mobile.mobbl.samples.logicflow;

import com.itude.mobile.mobbl.core.controller.MBApplicationFactory;
import com.itude.mobile.mobbl.core.controller.MBApplicationFactory.ActionMappings.Registry;
import com.itude.mobile.mobbl.samples.masterdetail.actions.CheckCredentialsAction;
import com.itude.mobile.mobbl.samples.masterdetail.actions.GetFreshPlantsAction;
import com.itude.mobile.mobbl.samples.masterdetail.actions.LogOffAction;

public class LogicFlowApplicationFactory extends MBApplicationFactory
{
  @Override
  protected Registry getActionRegistry()
  {
    return new Registry()
    {

      @Override
      protected void registerMappings()
      {
        registerAction("CheckCredentials", CheckCredentialsAction.class);
        registerAction("GetFreshPlants", GetFreshPlantsAction.class);
        registerAction("LogOff", LogOffAction.class);
      }
    };
  }
}
