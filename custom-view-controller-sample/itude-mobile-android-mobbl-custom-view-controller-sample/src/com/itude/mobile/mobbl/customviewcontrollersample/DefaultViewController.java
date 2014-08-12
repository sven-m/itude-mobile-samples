package com.itude.mobile.mobbl.customviewcontrollersample;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.itude.mobile.mobbl.core.controller.util.MBBasicViewController;

public class DefaultViewController extends MBBasicViewController {

	@Override
	protected ViewGroup buildInitialView(final LayoutInflater inflater) {
		final ViewGroup viewGroup = (ViewGroup) inflater.inflate(
				R.layout.page_default, null);

		return viewGroup;
	}

}
