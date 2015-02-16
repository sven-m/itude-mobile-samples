package com.itude.mobile.mobbl.samples.masterdetail.actions;

import com.itude.mobile.mobbl.core.controller.MBAction;
import com.itude.mobile.mobbl.core.controller.MBOutcome;
import com.itude.mobile.mobbl.core.model.MBDocument;
import com.itude.mobile.mobbl.core.services.MBDataManagerService;

public class GetFreshPlantsAction implements MBAction
{

  @Override
  public MBOutcome execute(MBDocument document, String path)
  {
    // force the cache to be refreshed, regardless of the defined cache interval
    MBDocument plants = MBDataManagerService.getInstance().loadFreshDocument("CATALOG");

    MBOutcome oc = new MBOutcome("OUTCOME-Catalog", plants);
    return oc;

  }

}
