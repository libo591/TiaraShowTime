package com.liboapp.year2014.tiarashowtime.cache;


import android.content.Context;
import android.util.LruCache;

public class TxtCache {
	private static TxtCache ic=new TxtCache();
	private LruCache<String, String> lrucache = null;
	private Context ctx;
	public static TxtCache getInst(Context ctx){
		ic.ctx = ctx;
		if(ic.lrucache==null){
			ic.init();
		}
		return ic;
	}
	private void init() {
		int cacheSize = 64*1024;
		lrucache = new LruCache<String, String>(cacheSize){
			@Override
			protected int sizeOf(String key, String value) {
				return value.getBytes().length;
			}
		};
	}
	
	public void addString2MemCache(String key,String content){
		if(getStringFromMem(key)==null){
			this.lrucache.put(key, content);
		}
	}
	public String getStringFromMem(String key){
		return this.lrucache.get(key);
	}
	
}
