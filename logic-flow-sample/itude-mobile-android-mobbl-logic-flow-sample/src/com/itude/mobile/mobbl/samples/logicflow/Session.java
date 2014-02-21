package com.itude.mobile.mobbl.samples.logicflow;

import com.itude.mobile.mobbl.core.model.MBDocument;
import com.itude.mobile.mobbl.core.model.MBSession;
import com.itude.mobile.mobbl.core.model.MBSessionInterface;
import com.itude.mobile.mobbl.core.services.MBDataManagerService;

public class Session implements MBSessionInterface
{
  private MBDocument _document;

  @Override
  public MBDocument getDocument()
  {
    if (_document == null)
    {
      _document = MBDataManagerService.getInstance().loadDocument("SessionState");
    }
    return _document;
  }

  @Override
  public void logOff()
  {
    getDocument().setValue(false, "Session[0]/@loggedIn");
    MBDataManagerService.getInstance().storeDocument(getDocument());
  }

  public void logOn()
  {
    getDocument().setValue(true, "Session[0]/@loggedIn");
    MBDataManagerService.getInstance().storeDocument(getDocument());

  }

  @Override
  public boolean isLoggedOn()
  {
    return getDocument().getBooleanForPath("Session[0]/@loggedIn");
  }

}
