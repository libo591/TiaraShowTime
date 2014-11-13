package com.liboapp.year2014.tiarashowtime.fragments;

import java.io.IOException;

import com.liboapp.year2014.tiarashowtime.AppProgress;
import com.liboapp.year2014.tiarashowtime.R;
import com.liboapp.year2014.tiarashowtime.adapters.DetailImageAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class PicFragment extends Fragment implements OnItemClickListener {
	private String detailInfo = "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = this.getArguments();
		detailInfo = args.getString("detail");
	}
	@Override
	public void onResume() {
		super.onResume();
		AppProgress.hideProgress();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View root = inflater.inflate(R.layout.fragment_pic, container,false);
		DetailImageAdapter dia = new DetailImageAdapter(this.getActivity(), this.detailInfo);
		GridView gv = (GridView)root.findViewById(R.id.gv1);
		gv.setAdapter(dia);
		gv.setOnItemClickListener(this);
		return root;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		Bundle args = new Bundle();
		args.putString("name", this.detailInfo);
		args.putString("file",view.getContentDescription().toString());
		try {
			args.putStringArray("files", this.getActivity().getAssets().list(this.detailInfo));
		}catch (IOException e) {
		}
		PicViewFragment pvf = new PicViewFragment();
		pvf.setArguments(args);
		getFragmentManager().beginTransaction()
		.setCustomAnimations(R.animator.fragment_slide_left_enter, 
				R.animator.fragment_slide_left_exit, 
				R.animator.fragment_slide_right_enter,
				R.animator.fragment_slide_right_exit)
			.replace(R.id.main, pvf,"picview")
			.addToBackStack(null).commit();
	}
}
