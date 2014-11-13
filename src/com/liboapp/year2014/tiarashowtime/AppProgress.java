package com.liboapp.year2014.tiarashowtime;

import com.liboapp.year2014.tiarashowtime.R;

import android.app.ProgressDialog;
import android.content.Context;


public class AppProgress {
	private static ProgressDialog pd;
	public static void hideProgress(){
		if(pd!=null){pd.dismiss();}
	}
	public static void showProgress(Context ctx){
		if(pd==null){
			pd = ProgressDialog.show(ctx, null,ctx.getResources().getString(R.string.loadingMsg));
			pd.setIndeterminate(false);
			pd.setCancelable(false);
		}else{
			pd.show();
		}
	}
	
	public static void finish(){
		pd = null;
	}
}
