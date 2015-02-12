package com.itude.mobile.mobbl.samples.masterdetail.actions;

import com.itude.mobile.mobbl.core.controller.MBAction;
import com.itude.mobile.mobbl.core.controller.MBOutcome;
import com.itude.mobile.mobbl.core.model.MBDocument;

public class CheckCredentialsAction implements MBAction
{

  @Override
  public MBOutcome execute(MBDocument document, String path)
  {

    String user = document.getValueForPath("Credentials[0]/@user");
    String password = document.getValueForPath("Credentials[0]/@password");

    MBOutcome oc = new MBOutcome();
    oc.setDocument(document);
    oc.setTransferDocument(true);
    oc.setNoBackgroundProcessing(true);

    if ("demo".equals(user) && "demo".equals(password)) oc.setOutcomeName("OUTCOME-LoggedIn");
    else oc.setOutcomeName("OUTCOME-LoginError");

    return oc;

  }

}
