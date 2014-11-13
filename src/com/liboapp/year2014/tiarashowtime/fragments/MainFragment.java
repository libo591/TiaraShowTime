package com.liboapp.year2014.tiarashowtime.fragments;

import com.liboapp.year2014.tiarashowtime.MainActivity;
import com.liboapp.year2014.tiarashowtime.R;
import com.liboapp.year2014.tiarashowtime.adapters.MainImageAdapter;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;

public class MainFragment extends Fragment implements OnItemClickListener {
	@Override
	public void onResume() {
		super.onResume();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View mainview = inflater.inflate(R.layout.fragment_main,container,false);
		MainImageAdapter mia = new MainImageAdapter(this.getActivity());
		GridView gridView = (GridView)mainview.findViewById(R.id.gv1);
		gridView.setAdapter(mia);
		gridView.setOnItemClickListener(this);
		
		ImageView tv = (ImageView)mainview.findViewById(R.id.title);
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				gotoDetail(view.getContentDescription().toString(),0);
			}
		});
		return mainview;
	}
	
	private void gotoDetail(String detail,int tabindex){
		DetaiTabFragment dtf = (DetaiTabFragment)getFragmentManager().findFragmentByTag("detailtab");
		if(dtf==null){
			dtf=new DetaiTabFragment();
		}
		Bundle args = dtf.getArguments();
		if(args==null){
			args = new Bundle();
			dtf.setArguments(args);
		}
		args.putString("detail", detail);
		args.putInt("tabindex", tabindex);
		
		getFragmentManager().beginTransaction()
			.setCustomAnimations(R.animator.fragment_slide_left_enter, 
					R.animator.fragment_slide_left_exit, 
					R.animator.fragment_slide_right_enter,
					R.animator.fragment_slide_right_exit)
			.replace(R.id.main, dtf,"detailtab").addToBackStack(null).commit();
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		gotoDetail(arg1.getContentDescription().toString(),0);
	}
}
