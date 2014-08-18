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

package com.itude.mobile.mobbl.customviewcontrollersample;

import java.util.HashMap;
import java.util.Map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.itude.mobile.mobbl.core.controller.util.MBBasicViewController;

public class HomeController extends MBBasicViewController
{
  @Override
  protected ViewGroup buildInitialView(final LayoutInflater inflater)
  {
    final ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.page_home, null);

    final ListView listView = (ListView) viewGroup.findViewById(R.id.list_view);

    final Map<String, Integer> itemPathsLayoutMap = new HashMap<String, Integer>();
    itemPathsLayoutMap.put(Constants.Path.Catalog.COMMON, android.R.id.text1);
    itemPathsLayoutMap.put(Constants.Path.Catalog.BOTANICAL, android.R.id.text2);

    final DocumentAdapter documentAdapter = new DocumentAdapter(getActivity(), getPage().getDocument(), Constants.Path.Catalog.PLANT_LIST,
        android.R.layout.simple_list_item_2, itemPathsLayoutMap);

    listView.setAdapter(documentAdapter);

    listView.setOnItemClickListener(new OnItemClickListener()
    {

      @Override
      public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id)
      {
        final String path = "/" + documentAdapter.getItemPath(position);
        getPage().handleOutcome(Constants.Outcome.Name.GOTO_DETAIL_PAGE, path);
      }

    });

    return viewGroup;
  }
}
