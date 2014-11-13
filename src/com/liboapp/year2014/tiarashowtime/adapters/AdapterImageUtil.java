package com.liboapp.year2014.tiarashowtime.adapters;

import com.liboapp.year2014.tiarashowtime.cache.ImageCache;
import com.liboapp.year2014.tiarashowtime.tasks.BitmapParser;
import com.liboapp.year2014.tiarashowtime.utils.ImageUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class AdapterImageUtil {
	public static Bitmap genBitmap(ImageView iv,Context ctx,int resid,
			int imagew,int imageh){
		ImageCache imageCache = ImageCache.getInst(ctx);
		Bitmap bitmap=null;
		if((bitmap = imageCache.getBitMapFromMem(resid+""))!=null){
			return bitmap;
		}else{
			bitmap = imageCache.getBitMapFromMem("waitbg_main");
			if(bitmap==null){imageCache.initBGCache();bitmap = imageCache.getBitMapFromMem("waitbg_main");}
			BitmapParser bp = new BitmapParser(iv);
			bp.execute(resid,ctx,imagew,imageh);
		}
		return bitmap;
	}
	
	public static Bitmap genBitmap(ImageView iv,Context ctx,String imagepath,
			int imagew,int imageh,int keyflag){
		ImageCache imageCache = ImageCache.getInst(ctx);
		Bitmap bitmap=null;
		if((bitmap = imageCache.getBitMapFromMem(imagepath))!=null){
			return bitmap;
		}else{
			String key = keyflag==0?"waitbg_pic":"waitbg_mv";
			bitmap = imageCache.getBitMapFromMem(key);
			if(bitmap==null){imageCache.initBGCache();bitmap = imageCache.getBitMapFromMem(key);}
			BitmapParser bp = new BitmapParser(iv);
			bp.execute(imagepath,ctx,imagew,imageh);
		}
		return bitmap;
	}
}
