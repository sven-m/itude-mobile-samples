/*
 * (C) Copyright Itude Mobile B.V., The Netherlands
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.itude.mobile.template.jsf;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.itude.mobile.mobbl.core.controller.MBOutcome;
import com.itude.mobile.web.annotations.HttpParam;
import com.itude.mobile.web.controllers.ApplicationController;
import com.itude.mobile.web.controllers.CurrentView;
import com.itude.mobile.web.jsf.AbstractLinkHandleBean;
import com.itude.mobile.web.util.ComparisonUtil;

@RequestScoped
@Named
public class LinkHandleBean extends AbstractLinkHandleBean
{
  @Inject
  @HttpParam("outcome")
  private String                _outcome;

  @Inject
  @HttpParam("path")
  private String                _path;

  @Inject
  @HttpParam("tab")
  private String                _tab;

  @Inject
  private CurrentView           _view;

  @Inject
  private ApplicationController _controller;

  @Override
  protected void doInit(FacesContext fc, boolean newSession)
  {
    NavigationHandler nav = fc.getApplication().getNavigationHandler();

    if (_view.getView() != null && _outcome != null)
    {
      _view.getView().handleOutcome(_outcome, _path);
      nav.handleNavigation(fc, null, "default");
    }

    else if (_tab != null)
    {
      boolean reInit = false;
      if (ComparisonUtil.safeEquals(_tab, _view.getCurrentDialog()) || "HOME".equals(_tab)) reInit = true;
      _view.setCurrentDialog(_tab);
      if (_view.getView() == null || reInit) _controller.initializeTab();
      nav.handleNavigation(fc, null, "default");
    }
    else if (_outcome != null)
    {
      MBOutcome outcome = new MBOutcome(_outcome, null);
      _controller.handleOutcome(outcome);
      nav.handleNavigation(fc, null, "default");
    }
  }
}
