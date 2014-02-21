package com.itude.mobile.mobbl.samples.masterdetail.actions;

import com.itude.mobile.mobbl.core.controller.MBAction;
import com.itude.mobile.mobbl.core.controller.MBOutcome;
import com.itude.mobile.mobbl.core.model.MBDocument;
import com.itude.mobile.mobbl.core.model.MBSession;
import com.itude.mobile.mobbl.core.util.MBCacheManager;

public class LogOffAction implements MBAction
{

  @Override
  public MBOutcome execute(MBDocument document, String path)
  {
    // Logoff and flush the cache
    MBSession.getInstance().logOff();
    MBCacheManager.expireDocumentForKey("CATALOG");
    
    // Tell the framework where to go next
    MBOutcome oc = new MBOutcome("OUTCOME-Login", null);
    return oc;
  }

}
