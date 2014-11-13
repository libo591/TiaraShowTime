package com.liboapp.year2014.tiarashowtime.utils;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView;

public class ImageUtils {

	/**
	 * @param res
	 * @param resid
	 * @param targetWidth
	 * @param targetHeight
	 * @return
	 */
	public static Bitmap scaleImage(Resources res,int resid,int targetWidth,int targetHeight){
		BitmapFactory.Options op = new BitmapFactory.Options();
		op.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res,resid,op);
		op.inSampleSize = getInSampleSize(targetWidth,op.outWidth,targetHeight,op.outHeight);
		op.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res,resid,op);
	}
	
	public static Bitmap scaleImage(AssetManager asset,String filePath,int targetWidth,int targetHeight){
		Rect rect = new Rect();
		BitmapFactory.Options op = new BitmapFactory.Options();
		op.inJustDecodeBounds = true;
		try {
			InputStream inputstream = asset.open(filePath);
			BitmapFactory.decodeStream(inputstream,rect,op);
			inputstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		op.inSampleSize = getInSampleSize(targetWidth,op.outWidth,targetHeight,op.outHeight);
		op.inJustDecodeBounds = false;
		Bitmap bitmap = null;
		try {
			InputStream inputstream2 = asset.open(filePath);
			bitmap = BitmapFactory.decodeStream(inputstream2,rect,op);
			inputstream2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	private static int getInSampleSize(int wtarget,int wsrc,int htarget,int hsrc){
		int sampleSizeW = wsrc/wtarget<1?1:wsrc/wtarget;
		int sampleSizeH = hsrc/htarget<1?1:hsrc/htarget;
		
		int result = sampleSizeW<sampleSizeH?sampleSizeH:sampleSizeW;
		return result;
	}
	
	/** 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }
    
}
