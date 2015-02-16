/*
 * (C) Copyright Itude Mobile B.V., The Netherlands.
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

package com.itude.mobile.mobbl.customviewcontrollersample.application;

import com.itude.mobile.mobbl.core.controller.MBApplicationFactory;
import com.itude.mobile.mobbl.core.controller.MBApplicationFactory.ControllerMappings.Registry;
import com.itude.mobile.mobbl.customviewcontrollersample.Constants;
import com.itude.mobile.mobbl.customviewcontrollersample.Constants.Page;
import com.itude.mobile.mobbl.customviewcontrollersample.Constants.Page.Name;
import com.itude.mobile.mobbl.customviewcontrollersample.detail.PlantDetailViewController;
import com.itude.mobile.mobbl.customviewcontrollersample.list.PlantListViewController;

public class ApplicationFactory extends MBApplicationFactory
{

  protected static class ControllerRegistry extends ControllerMappings.Registry
  {

    @Override
    protected void registerMappings()
    {
      registerController(Constants.Page.Name.PLANT_LIST, PlantListViewController.class);
      registerController(Constants.Page.Name.PLANT_DETAIL, PlantDetailViewController.class);
    }

  };

  @Override
  protected Registry getControllerRegistry()
  {
    return new ControllerRegistry();
  }

}
