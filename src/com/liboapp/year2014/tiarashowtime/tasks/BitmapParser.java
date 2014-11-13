package com.liboapp.year2014.tiarashowtime.tasks;

import com.liboapp.year2014.tiarashowtime.cache.ImageCache;
import com.liboapp.year2014.tiarashowtime.utils.ImageUtils;
import com.liboapp.year2014.tiarashowtime.views.roundimage.RoundedDrawable;
import com.liboapp.year2014.tiarashowtime.views.roundimage.RoundedImageView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapParser extends AsyncTask {
	private ImageView iv;
	
	public BitmapParser(ImageView iv){
		this.iv = iv;
	}
	
	@Override
	protected Object doInBackground(Object... arg0) {
		Object param = arg0[0];
		Bitmap bitmap = null;
		if(param instanceof String){
			bitmap = ImageUtils.scaleImage(((Context)arg0[1]).getAssets(),(String)arg0[0], 
					(Integer)arg0[2],(Integer)arg0[3]);
		}else{
			bitmap = ImageUtils.scaleImage(((Context)arg0[1]).getResources(),(Integer)arg0[0], 
					(Integer)arg0[2],(Integer)arg0[3]);
		}
		ImageCache.getInst((Context)arg0[1]).addBitmap2MemCache(arg0[0]+"", bitmap);
		return bitmap;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		this.iv.setImageBitmap((Bitmap)result);
	}
	
}
