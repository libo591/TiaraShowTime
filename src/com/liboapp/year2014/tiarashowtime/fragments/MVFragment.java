package com.liboapp.year2014.tiarashowtime.fragments;

import java.io.IOException;

import com.liboapp.year2014.tiarashowtime.AppProgress;
import com.liboapp.year2014.tiarashowtime.MVViewActivity;
import com.liboapp.year2014.tiarashowtime.R;
import com.liboapp.year2014.tiarashowtime.adapters.DetailImageAdapter;
import com.liboapp.year2014.tiarashowtime.adapters.DetailMvListAdapter;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MVFragment extends Fragment implements OnItemClickListener {
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
		View root = inflater.inflate(R.layout.fragment_mv, container,false);
		ListView lv = (ListView)root.findViewById(R.id.mvlist);
		DetailMvListAdapter adapater = new DetailMvListAdapter(this.getActivity());
		lv.setAdapter(adapater);
		lv.setOnItemClickListener(this);
		return root;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		String mvFile = view.getContentDescription().toString();
//		Bundle args = new Bundle();
//		args.putString("mv", mvFile);
//		args.putString("name",this.detailInfo);
//		MVViewFragment pvf = new MVViewFragment();
//		pvf.setArguments(args);
//		getFragmentManager().beginTransaction()
//		.setCustomAnimations(R.animator.fragment_slide_left_enter, 
//				R.animator.fragment_slide_left_exit, 
//				R.animator.fragment_slide_right_enter,
//				R.animator.fragment_slide_right_exit)
//			.replace(R.id.main, pvf,"mvview")
//			.addToBackStack(null).commit();
		Intent intent = new Intent(this.getActivity(),MVViewActivity.class);
		Bundle b = new Bundle();
		b.putString("mv", mvFile);
		b.putString("name",this.detailInfo);
		intent.putExtras(b);
		startActivity(intent);
	}
}
