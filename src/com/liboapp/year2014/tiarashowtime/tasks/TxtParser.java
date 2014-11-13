package com.liboapp.year2014.tiarashowtime.tasks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import com.liboapp.year2014.tiarashowtime.R;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.webkit.WebView;

public class TxtParser extends AsyncTask<String, Integer, String> {
	private WebView wv;
	private Context ctx;
	
	public TxtParser(WebView wv,Context ctx){
		this.wv = wv;
		this.ctx = ctx;
	}
	@Override
	protected String doInBackground(String... arg0) {
		String detailInfo = arg0[0];
		int res = getRawResIdByFieldName(detailInfo);
		InputStream input = this.ctx.getResources().openRawResource(res);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		try {
			while((len=input.read(buffer))!=-1){
				bos.write(buffer,0,len);
			}
			bos.flush();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bos.toString();
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		this.wv.loadDataWithBaseURL("",result, "text/html","UTF-8","");
	}
	
	private int getRawResIdByFieldName(String fieldName){
		R.raw r = new R.raw();
		int result = -1;
		try {
			Field field = R.raw.class.getField(fieldName);
			result = (Integer)field.get(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
