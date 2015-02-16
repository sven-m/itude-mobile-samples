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

package com.itude.mobile.mobbl.customviewcontrollersample.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itude.mobile.mobbl.core.controller.util.MBBasicViewController;
import com.itude.mobile.mobbl.core.model.MBDocument;
import com.itude.mobile.mobbl.core.model.MBElement;
import com.itude.mobile.mobbl.customviewcontrollersample.Constants;
import com.itude.mobile.mobbl.customviewcontrollersample.R;
import com.itude.mobile.mobbl.customviewcontrollersample.Constants.Path;
import com.itude.mobile.mobbl.customviewcontrollersample.Constants.Path.Catalog;
import com.itude.mobile.mobbl.customviewcontrollersample.R.id;
import com.itude.mobile.mobbl.customviewcontrollersample.R.layout;

public class PlantDetailViewController extends MBBasicViewController {

	@Override
	protected ViewGroup buildInitialView(final LayoutInflater inflater) {
		final ViewGroup viewGroup = (ViewGroup) inflater.inflate(
				R.layout.page_detail, null);

    final MBDocument document = getPage().getDocument();
    final String path = getPage().getAbsoluteDataPath();
    final MBElement element = document.<MBElement> getValueForPath(path);

    final TextView commonTextView = (TextView) viewGroup.findViewById(R.id.text_view_common);
    final TextView botanicalTextView = (TextView) viewGroup.findViewById(R.id.text_view_botanical);
    final TextView zoneTextView = (TextView) viewGroup.findViewById(R.id.text_view_zone);
    final TextView lightTextView = (TextView) viewGroup.findViewById(R.id.text_view_light);
    final TextView priceTextView = (TextView) viewGroup.findViewById(R.id.text_view_price);
    final TextView availabilityTextView = (TextView) viewGroup.findViewById(R.id.text_view_availability);

    commonTextView.setText("Name: " + element.<String> getValueForPath(Constants.Path.Catalog.COMMON));
    botanicalTextView.setText("Botanical name: " + element.<String> getValueForPath(Constants.Path.Catalog.BOTANICAL));
    zoneTextView.setText("Zone: " + element.<String> getValueForPath(Constants.Path.Catalog.ZONE));
    lightTextView.setText("Light: " + element.<String> getValueForPath(Constants.Path.Catalog.LIGHT));
    priceTextView.setText("Price: " + element.<String> getValueForPath(Constants.Path.Catalog.PRICE));
    availabilityTextView.setText("Availability: " + element.<String> getValueForPath(Constants.Path.Catalog.AVAILABILITY));

		return viewGroup;
	}

}
