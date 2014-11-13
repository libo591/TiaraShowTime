package com.liboapp.year2014.tiarashowtime.adapters;

import java.io.IOException;

import com.liboapp.year2014.tiarashowtime.R;
import com.liboapp.year2014.tiarashowtime.utils.AppConfig;
import com.liboapp.year2014.tiarashowtime.utils.ImageUtils;
import com.liboapp.year2014.tiarashowtime.views.roundimage.RoundedImageView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class DetailImageAdapter extends BaseAdapter {

	private Context ctx;
	private String detailInfo;
	private String[] picfiles;
	public DetailImageAdapter(Context ctx,String detailInfo){
		this.ctx = ctx;  
		this.detailInfo = detailInfo;
		try {
			picfiles = ctx.getAssets().list(this.detailInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getCount() {
		return picfiles.length;
	}

	@Override
	public Object getItem(int arg0) {
		return picfiles[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int index, View view, ViewGroup vg) {
		Resources resource = this.ctx.getResources();
		int imagew = (int)resource.getDimension(R.dimen.main_detailpic_width);
		int imageh = (int)resource.getDimension(R.dimen.main_detailpic_height);
		if(view==null){
			view = new RoundedImageView(this.ctx);
			GridView.LayoutParams lp = new GridView.LayoutParams(imagew, imageh);
			((RoundedImageView)view).setLayoutParams(lp);
			((RoundedImageView)view).setScaleType(ScaleType.CENTER_CROP);
			((RoundedImageView)view).setCornerRadius(resource.getDimension(R.dimen.detail_item_round));
			((RoundedImageView)view).setBorderWidth(R.dimen.detail_item_borderwidth);
			((RoundedImageView)view).setBorderColor(resource.getColor(R.color.imageBorderColor));
			((RoundedImageView)view).mutateBackground(false);//
			((RoundedImageView)view).setOval(false);//如果为true，则为椭圆。为false，按照radius来绘制
		}
		((RoundedImageView)view).setImageBitmap(AdapterImageUtil.genBitmap((RoundedImageView)view, ctx, 
				this.detailInfo+"/"+picfiles[index], imagew, imageh,0));
		((RoundedImageView)view).setContentDescription(picfiles[index]);
		return view;
	}

	public String[] getPicfiles() {
		return picfiles;
	}

	public void setPicfiles(String[] picfiles) {
		this.picfiles = picfiles;
	}

}
