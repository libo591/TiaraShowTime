package com.liboapp.year2014.tiarashowtime.adapters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.liboapp.year2014.tiarashowtime.R;
import com.liboapp.year2014.tiarashowtime.utils.AppConfig;
import com.liboapp.year2014.tiarashowtime.utils.ImageUtils;
import com.liboapp.year2014.tiarashowtime.views.roundimage.RoundedImageView;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

public class DetailMvListAdapter extends BaseAdapter {

	private Context ctx;
	private JSONArray mvArray;
	
	public DetailMvListAdapter(Context ctx){
		this.ctx = ctx;
		try {
		//InputStream mvInfoStream = getAssets().open(this.detailInfo+"_mv.json");
			InputStream mvInfoStream = ctx.getAssets().open("tiara_mv.json");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while((len=mvInfoStream.read(buffer))!=-1){
				bos.write(buffer, 0, len);
			}
			bos.flush();
			mvArray = new JSONArray(bos.toString());
			bos.close();
			mvInfoStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		ViewHolder vh=null;
		TextView tv = null;
		RoundedImageView iv = null;
		JSONObject jo = null;
		LinearLayout ll = null;
		Resources resource = this.ctx.getResources();
		int imagew = (int)resource.getDimension(R.dimen.mv_item_width);
		int imageh = (int)resource.getDimension(R.dimen.mv_item_height);
		if(view==null){
			view = new LinearLayout(this.ctx);
			ll = (LinearLayout)view;
			ll.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
			ll.setOrientation(LinearLayout.HORIZONTAL);
			ll.setGravity(Gravity.LEFT);
			iv = new RoundedImageView(this.ctx);
			iv.setLayoutParams(new LinearLayout.LayoutParams(imagew,imageh));
			iv.setScaleType(ScaleType.CENTER_CROP);
			iv.setCornerRadius(resource.getDimension(R.dimen.detail_mvitem_round));
			iv.setBorderWidth(R.dimen.detail_mvitem_borderwidth);
			iv.setBorderColor(resource.getColor(R.color.imageBorderColor));
			iv.mutateBackground(true);//
			iv.setOval(true);//如果为true，则为椭圆。为false，按照radius来绘制
			ll.addView(iv);
			
			tv = new TextView(this.ctx);
			tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			ll.addView(tv);
			vh = new ViewHolder();
			vh.textView = tv;
			vh.imageView = iv;
			view.setTag(vh);
		}else{
			ll = (LinearLayout)view;
			vh = (ViewHolder)view.getTag();
		}
		try{
			jo = mvArray.getJSONObject(index);
			ll.setContentDescription(jo.getString("mv"));
			vh.textView.setText(jo.getString("label"));
			vh.imageView.setContentDescription(jo.getString("mv"));
			vh.imageView.setImageBitmap(AdapterImageUtil.genBitmap((RoundedImageView)(vh.imageView), ctx, 
					jo.getString("pic"), imagew, imageh,1));
		}catch(Exception ex){ex.printStackTrace();}
		return view;
	}
	
	@Override
	public long getItemId(int arg0) {
		return 0;
	}
	
	@Override
	public Object getItem(int arg0) {
		return null;
	}
	
	@Override
	public int getCount() {
		return mvArray.length();
	}
	
	class ViewHolder{
		public TextView textView;
		public RoundedImageView imageView;
	}

}
