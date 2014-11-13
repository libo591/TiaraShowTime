package com.liboapp.year2014.tiarashowtime.fragments;


import com.liboapp.year2014.tiarashowtime.AppProgress;
import com.liboapp.year2014.tiarashowtime.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetaiTabFragment extends Fragment implements OnClickListener {
	private String detailInfo = "";
	private int tabindex;
	private TextView tab1;
	private TextView tab2;
	private TextView tab3;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = this.getArguments();
		detailInfo = args.getString("detail");
		tabindex = args.getInt("tabindex");
		if(savedInstanceState!=null){
			detailInfo = savedInstanceState.getString("detail");
			tabindex = savedInstanceState.getInt("tabindex");
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("detail", this.detailInfo);
		outState.putInt("tabindex", tabindex);
	}
	@Override
	public void onResume() {
		super.onResume();
		tabChange();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d("DetailtabFragment","onCreateView=="+inflater+"=="+container+"==="+savedInstanceState);
		View root = inflater.inflate(R.layout.fragment_detailtab, container,false);
		tab1 = (TextView)root.findViewById(R.id.btn1);
		tab2 = (TextView)root.findViewById(R.id.btn2);
		tab3 = (TextView)root.findViewById(R.id.btn3);
		tab1.setOnClickListener(this);
		tab2.setOnClickListener(this);
		tab3.setOnClickListener(this);
		return root;
	}
	private void showTabContent(String tagShow){
		Fragment fragShow=null;
		if(tabindex==0){
			fragShow = new ZiliaoFragment();
		}else if(tabindex==1){
			fragShow = new PicFragment();
		}else if(tabindex==2){
			fragShow = new MVFragment();
		}
		Bundle args = new Bundle();
		args.putString("detail", this.detailInfo);
		fragShow.setArguments(args);
		getFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_slide_left_enter, 
				R.animator.fragment_slide_left_exit, 
				R.animator.fragment_slide_right_enter,
				R.animator.fragment_slide_right_exit)
				.replace(R.id.tabdetail,fragShow,tagShow).commit();
	}
	private void tabChange(){
		AppProgress.showProgress(this.getActivity());
		tab1.setBackgroundResource(R.drawable.tabitem_unselect);
		tab2.setBackgroundResource(R.drawable.tabitem_unselect);
		tab3.setBackgroundResource(R.drawable.tabitem_unselect);
		if(tabindex==0){
			showTabContent("ziliao");
			tab1.setBackgroundResource(R.drawable.tabitem_select);
		}else if(tabindex==1){
			showTabContent("pic");
			tab2.setBackgroundResource(R.drawable.tabitem_select);
		}else if(tabindex==2){
			showTabContent("mv");
			tab3.setBackgroundResource(R.drawable.tabitem_select);
		}
	}

	@Override
	public void onClick(View view) {
		int showTabindex = -1;
		if(view.getId()==R.id.btn1){
			showTabindex = 0;
		}else if(view.getId()==R.id.btn2){
			showTabindex = 1;
		}else if(view.getId()==R.id.btn3){
			showTabindex = 2;
		}
		if(showTabindex!=tabindex){
			tabindex = showTabindex;
			tabChange();
		}
	}
}
