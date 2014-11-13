package com.liboapp.year2014.tiarashowtime;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;

import com.liboapp.year2014.tiarashowtime.adapters.MainImageAdapter;
import com.liboapp.year2014.tiarashowtime.fragments.MainFragment;
import com.liboapp.year2014.tiarashowtime.utils.AppConfig;
import com.liboapp.year2014.tiarashowtime.utils.ImageUtils;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 图片寻找
 * @author hp
 *
 */
public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		if(savedInstanceState==null){
			initAppConfig();
			initView(savedInstanceState);
			initAd();
		}
	}

	private void initAd() {
		// 初始化接口，应用启动的时候调用
		// 参数：appId, appSecret, 调试模式
		AdManager.getInstance(this).init("d2c6fd955cb00b7a","fe0ed8e7092f5d67",false);
		// 检查配置，SDK运行失败时可以用来检查配置是否齐全
		// SpotManager.getInstance(this).checkPermission(this);
		// 广告条接口调用（适用于应用）
		// 将广告条adView添加到需要展示的layout控件中
		FrameLayout adLayout = (FrameLayout) findViewById(R.id.banner);
		AdView adView = new AdView(this, AdSize.FIT_SCREEN);
		adLayout.addView(adView);
		// 监听广告条接口
		adView.setAdListener(new AdViewListener() {
			@Override
			public void onSwitchedAd(AdView arg0) {
				Log.i("YoumiAdDemo", "广告条切换");
			}
			@Override
			public void onReceivedAd(AdView arg0) {
				Log.i("YoumiAdDemo", "请求广告成功");

			}
			@Override
			public void onFailedToReceivedAd(AdView arg0) {
				Log.i("YoumiAdDemo", "请求广告失败");
			}
		});
	}

	private void initView(Bundle savedInstanceState) {
		if(savedInstanceState==null){
			MainFragment mainFrag = new MainFragment();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.main, mainFrag, "main");
			ft.commit();
		}
	}
	
	private void initAppConfig() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		AppConfig.screen_height = dm.heightPixels;
		AppConfig.screen_width = dm.widthPixels;
		AppConfig.height_nobanner = AppConfig.screen_height-ImageUtils.dip2px(this, getResources().getDimension(R.dimen.banner_height));
		Log.d("MainActivity","Appconfig的三个值:"+AppConfig.screen_width
				+","+AppConfig.screen_height
				+","+AppConfig.height_nobanner);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppProgress.finish();
	}

}
