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

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.itude.mobile.mobbl.core.services.MBDataManagerService;
import com.itude.mobile.mobbl.core.services.MBMetadataService;
import com.itude.mobile.template.datahandlers.PlantDataHandler;
import com.itude.mobile.web.handlers.SessionDataHandler;

@ApplicationScoped
public class TemplateProvider implements Serializable
{
  private static final long serialVersionUID = 1L;

  @PostConstruct
  protected void init()
  {
    MBDataManagerService.getInstance().registerDataHandler(new SessionDataHandler(), "SessionDataHandler");
    MBDataManagerService.getInstance().registerDataHandler(new PlantDataHandler(), "PlantDataHandler");

  }

  @Produces
  public MBMetadataService getMetaDataService()
  {
    return MBMetadataService.getInstance();
  }

  @Produces
  public MBDataManagerService getDataManagerService()
  {
    return MBDataManagerService.getInstance();
  }
}
