package com.liboapp.year2014.tiarashowtime.cache;

import com.liboapp.year2014.tiarashowtime.R;
import com.liboapp.year2014.tiarashowtime.utils.ImageUtils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.LruCache;

public class ImageCache {
	private static ImageCache ic=new ImageCache();
	private LruCache<String, Bitmap> lrucache = null;
	private Context ctx;
	public static ImageCache getInst(Context ctx){
		ic.ctx = ctx;
		if(ic.lrucache==null){
			ic.init();
		}
		return ic;
	}
	private void init() {
		int memSize = 
				((ActivityManager)this.ctx.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
		int cacheSize = memSize*1024*1024/8;
		lrucache = new LruCache<String, Bitmap>(cacheSize){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getByteCount();
			}
		};
		initBGCache();
	}
	public void initBGCache(){
		Resources resource = this.ctx.getResources();
		this.addBitmap2MemCache("waitbg_main", 
				ImageUtils.scaleImage(this.ctx.getResources(), R.raw.waitbg_11, 
						(int)resource.getDimension(R.dimen.main_item_width), 
						(int)resource.getDimension(R.dimen.main_item_height)));
		this.addBitmap2MemCache("waitbg_pic", 
				ImageUtils.scaleImage(this.ctx.getResources(), R.raw.waitbg_11, 
						(int)resource.getDimension(R.dimen.main_detailpic_width), 
						(int)resource.getDimension(R.dimen.main_detailpic_height)));
		this.addBitmap2MemCache("waitbg_mv", 
				ImageUtils.scaleImage(this.ctx.getResources(), R.raw.waitbg_32, 
						(int)resource.getDimension(R.dimen.mv_item_width), 
						(int)resource.getDimension(R.dimen.mv_item_height)));
	}
	
	public void addBitmap2MemCache(String key,Bitmap bitmap){
		if(getBitMapFromMem(key)==null){
			this.lrucache.put(key, bitmap);
		}
	}
	public Bitmap getBitMapFromMem(String key){
		return this.lrucache.get(key);
	}
	
}
