package com.liboapp.year2014.tiarashowtime.fragments;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import com.liboapp.year2014.tiarashowtime.AppProgress;
import com.liboapp.year2014.tiarashowtime.R;
import com.liboapp.year2014.tiarashowtime.cache.TxtCache;
import com.liboapp.year2014.tiarashowtime.tasks.TxtParser;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class ZiliaoFragment extends Fragment {
	private String detailInfo = "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRetainInstance(true);
		Bundle args = this.getArguments();
		detailInfo = args.getString("detail");
		if(savedInstanceState!=null){
			this.detailInfo = savedInstanceState.getString("detail");
		}
		Log.d("ZiliaoFragment","onCreate");
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("detail", this.detailInfo);
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d("ZiliaoFragment","onAttach");
	}
	@Override
	public void onResume() {
		super.onResume();
		Log.d("ZiliaoFragment","onResume");
		AppProgress.hideProgress();
	}
	@Override
	public void onStart() {
		super.onStart();
		Log.d("ZiliaoFragment","onStart");
	}
	@Override
	public void onInflate(Activity activity, AttributeSet attrs,
			Bundle savedInstanceState) {
		super.onInflate(activity, attrs, savedInstanceState);
		Log.d("ZiliaoFragment","onInflate");
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d("ZiliaoFragment","onCreateView");
		View root = inflater.inflate(R.layout.fragment_ziliao, container,false);
		WebView wv1 = (WebView)root.findViewById(R.id.wv1);
		wv1.setBackgroundColor(0);
		wv1.setBackgroundColor(Color.argb(0, 255, 255, 255));
		String viewstr = TxtCache.getInst(this.getActivity()).getStringFromMem(this.detailInfo);
		if(viewstr!=null){
			wv1.loadDataWithBaseURL("",viewstr, "text/html","UTF-8","");
		}else{
			wv1.loadDataWithBaseURL("","正在加载....", "text/html","UTF-8","");
			TxtParser tp = new TxtParser(wv1,this.getActivity());
			tp.execute(this.detailInfo);
		}
		return root;
	}
	
}
