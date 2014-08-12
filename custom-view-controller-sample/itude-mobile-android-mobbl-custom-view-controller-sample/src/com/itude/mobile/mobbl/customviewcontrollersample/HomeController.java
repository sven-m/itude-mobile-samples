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

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.itude.mobile.mobbl.core.controller.MBApplicationController;
import com.itude.mobile.mobbl.core.controller.MBOutcome;
import com.itude.mobile.mobbl.core.controller.util.MBBasicViewController;

public class HomeController extends MBBasicViewController {

	@Override
	protected ViewGroup buildInitialView(final LayoutInflater inflater) {
		final ViewGroup viewGroup = (ViewGroup) inflater.inflate(
				R.layout.page_home, null);

		final Button gotoOtherPageButton = (Button) viewGroup
				.findViewById(R.id.goto_other_page);

		gotoOtherPageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				final MBOutcome outcome = new MBOutcome(
						Constants.Outcome.Name.GOTO_OTHER_PAGE, null);
				MBApplicationController.getInstance().handleOutcome(outcome);
			}
		});

		return viewGroup;
	}

}
