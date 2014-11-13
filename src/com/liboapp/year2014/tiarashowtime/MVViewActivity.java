package com.liboapp.year2014.tiarashowtime;

import java.lang.reflect.Field;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class MVViewActivity extends Activity {
	private VideoView vv;
	private String mvFile;
	private String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewmain);
		mvFile = this.getIntent().getStringExtra("mv");
		name = this.getIntent().getStringExtra("name");
		R.raw rr = new R.raw();
		int mvid = -1;
		try {
			Field mvField = R.raw.class.getField(mvFile);
			mvid = mvField.getInt(rr);
		} catch (Exception e) {
		}
		vv = (VideoView)findViewById(R.id.vv1);
		String uri = "android.resource://"+this.getPackageName()+"/"+mvid;
		Uri uriobj = Uri.parse(uri);
		vv.setVideoURI(uriobj);
		vv.setMediaController(new MediaController(this));
		vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer arg0) {
				vv.requestFocus();
				vv.start();
			}
		});
		vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
				new DialogFragment() {
					public Dialog onCreateDialog(Bundle savedInstanceState) {
						return new AlertDialog.Builder(MVViewActivity.this)
						.setMessage("视频播放中发生错误!")
						.setPositiveButton("ok", new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								MVViewActivity.this.finish();
							}
						})
						.create();
					};
				}.show(getFragmentManager(), "dialogFragment");
				return true;
			}
		});
       RelativeLayout.LayoutParams layoutParams=  
              new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);  
           layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
           layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);  
           layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);  
           layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);  
           vv.setLayoutParams(layoutParams);  
       if(savedInstanceState==null){
    	   initAd();
       }
	}
	private void initAd() {
		RelativeLayout adLayout = (RelativeLayout) findViewById(R.id.banner);
		AdView adView = new AdView(this, AdSize.FIT_SCREEN);
		adLayout.addView(adView);
		adView.setAdListener(new AdViewListener() {
			@Override
			public void onSwitchedAd(AdView arg0) {
				Log.i("mvviewActivity-YoumiAdDemo", "广告条切换");
			}
			@Override
			public void onReceivedAd(AdView arg0) {
				Log.i("mvviewActivity-YoumiAdDemo", "请求广告成功");

			}
			@Override
			public void onFailedToReceivedAd(AdView arg0) {
				Log.i("mvviewActivity-YoumiAdDemo", "请求广告失败");
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
	}
}
